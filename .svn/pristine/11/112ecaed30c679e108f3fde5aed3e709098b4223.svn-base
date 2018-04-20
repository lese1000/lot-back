/**
 * apple
 */
package com.jeeplus.modules.lottery.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.jeeplus.common.utils.DateUtils;
import com.jeeplus.common.utils.MyBeanUtils;
import com.jeeplus.common.config.Global;
import com.jeeplus.common.persistence.Page;
import com.jeeplus.common.web.BaseController;
import com.jeeplus.common.utils.StringUtils;
import com.jeeplus.common.utils.excel.ExportExcel;
import com.jeeplus.common.utils.excel.ImportExcel;
import com.jeeplus.modules.lottery.entity.POrderDetail;
import com.jeeplus.modules.lottery.service.POrderDetailService;

/**
 * 订单明细Controller
 * @author asd
 * @version 2018-01-29
 */
@Controller
@RequestMapping(value = "${adminPath}/lottery/pOrderDetail")
public class POrderDetailController extends BaseController {

	@Autowired
	private POrderDetailService pOrderDetailService;
	
	@ModelAttribute
	public POrderDetail get(@RequestParam(required=false) String id) {
		POrderDetail entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = pOrderDetailService.get(id);
		}
		if (entity == null){
			entity = new POrderDetail();
		}
		return entity;
	}
	
	/**
	 * 订单明细列表页面
	 */
	@RequiresPermissions("lottery:pOrderDetail:list")
	@RequestMapping(value = {"list", ""})
	public String list(POrderDetail pOrderDetail, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<POrderDetail> page = pOrderDetailService.findPage(new Page<POrderDetail>(request, response), pOrderDetail); 
		model.addAttribute("page", page);
		model.addAttribute("pOrderDetail", pOrderDetail);
		return "modules/lottery/pOrderDetailList";
	}

	/**
	 * 查看，增加，编辑订单明细表单页面
	 */
	@RequiresPermissions(value={"lottery:pOrderDetail:view","lottery:pOrderDetail:add","lottery:pOrderDetail:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(POrderDetail pOrderDetail, Model model) {
		model.addAttribute("pOrderDetail", pOrderDetail);
		return "modules/lottery/pOrderDetailForm";
	}

	/**
	 * 保存订单明细
	 */
	@RequiresPermissions(value={"lottery:pOrderDetail:add","lottery:pOrderDetail:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public String save(POrderDetail pOrderDetail, Model model, RedirectAttributes redirectAttributes) throws Exception{
		if (!beanValidator(model, pOrderDetail)){
			return form(pOrderDetail, model);
		}
		if(!pOrderDetail.getIsNewRecord()){//编辑表单保存
			POrderDetail t = pOrderDetailService.get(pOrderDetail.getId());//从数据库取出记录的值
			MyBeanUtils.copyBeanNotNull2Bean(pOrderDetail, t);//将编辑表单中的非NULL值覆盖数据库记录中的值
			pOrderDetailService.save(t);//保存
		}else{//新增表单保存
			pOrderDetailService.save(pOrderDetail);//保存
		}
		addMessage(redirectAttributes, "保存订单明细成功");
		return "redirect:"+Global.getAdminPath()+"/lottery/pOrderDetail/?repage";
	}
	
	/**
	 * 删除订单明细
	 */
	@RequiresPermissions("lottery:pOrderDetail:del")
	@RequestMapping(value = "delete")
	public String delete(POrderDetail pOrderDetail, RedirectAttributes redirectAttributes) {
		pOrderDetailService.delete(pOrderDetail);
		addMessage(redirectAttributes, "删除订单明细成功");
		return "redirect:"+Global.getAdminPath()+"/lottery/pOrderDetail/?repage";
	}
	
	/**
	 * 批量删除订单明细
	 */
	@RequiresPermissions("lottery:pOrderDetail:del")
	@RequestMapping(value = "deleteAll")
	public String deleteAll(String ids, RedirectAttributes redirectAttributes) {
		String idArray[] =ids.split(",");
		for(String id : idArray){
			pOrderDetailService.delete(pOrderDetailService.get(id));
		}
		addMessage(redirectAttributes, "删除订单明细成功");
		return "redirect:"+Global.getAdminPath()+"/lottery/pOrderDetail/?repage";
	}
	
	/**
	 * 导出excel文件
	 */
	@RequiresPermissions("lottery:pOrderDetail:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(POrderDetail pOrderDetail, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "订单明细"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<POrderDetail> page = pOrderDetailService.findPage(new Page<POrderDetail>(request, response, -1), pOrderDetail);
    		new ExportExcel("订单明细", POrderDetail.class).setDataList(page.getList()).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出订单明细记录失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/lottery/pOrderDetail/?repage";
    }

	/**
	 * 导入Excel数据

	 */
	@RequiresPermissions("lottery:pOrderDetail:import")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<POrderDetail> list = ei.getDataList(POrderDetail.class);
			for (POrderDetail pOrderDetail : list){
				try{
					pOrderDetailService.save(pOrderDetail);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条订单明细记录。");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条订单明细记录"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入订单明细失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/lottery/pOrderDetail/?repage";
    }
	
	/**
	 * 下载导入订单明细数据模板
	 */
	@RequiresPermissions("lottery:pOrderDetail:import")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "订单明细数据导入模板.xlsx";
    		List<POrderDetail> list = Lists.newArrayList(); 
    		new ExportExcel("订单明细数据", POrderDetail.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/lottery/pOrderDetail/?repage";
    }
	
	
	

}