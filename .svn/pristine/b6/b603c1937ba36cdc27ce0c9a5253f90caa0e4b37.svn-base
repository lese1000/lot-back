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
import com.jeeplus.modules.lottery.entity.PJoinBuy;
import com.jeeplus.modules.lottery.service.PJoinBuyService;

/**
 * JoinBuyController
 * @author asd
 * @version 2018-01-29
 */
@Controller
@RequestMapping(value = "${adminPath}/lottery/pJoinBuy")
public class PJoinBuyController extends BaseController {

	@Autowired
	private PJoinBuyService pJoinBuyService;
	
	@ModelAttribute
	public PJoinBuy get(@RequestParam(required=false) String id) {
		PJoinBuy entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = pJoinBuyService.get(id);
		}
		if (entity == null){
			entity = new PJoinBuy();
		}
		return entity;
	}
	
	/**
	 * JoinBuy列表页面
	 */
	@RequiresPermissions("lottery:pJoinBuy:list")
	@RequestMapping(value = {"list", ""})
	public String list(PJoinBuy pJoinBuy, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PJoinBuy> page = pJoinBuyService.findPage(new Page<PJoinBuy>(request, response), pJoinBuy); 
		model.addAttribute("page", page);
		model.addAttribute("pJoinBuy", pJoinBuy);
		return "modules/lottery/pJoinBuyList";
	}

	/**
	 * 查看，增加，编辑JoinBuy表单页面
	 */
	@RequiresPermissions(value={"lottery:pJoinBuy:view","lottery:pJoinBuy:add","lottery:pJoinBuy:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(PJoinBuy pJoinBuy, Model model) {
		model.addAttribute("pJoinBuy", pJoinBuy);
		return "modules/lottery/pJoinBuyForm";
	}

	/**
	 * 保存JoinBuy
	 */
	@RequiresPermissions(value={"lottery:pJoinBuy:add","lottery:pJoinBuy:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public String save(PJoinBuy pJoinBuy, Model model, RedirectAttributes redirectAttributes) throws Exception{
		if (!beanValidator(model, pJoinBuy)){
			return form(pJoinBuy, model);
		}
		if(!pJoinBuy.getIsNewRecord()){//编辑表单保存
			PJoinBuy t = pJoinBuyService.get(pJoinBuy.getId());//从数据库取出记录的值
			MyBeanUtils.copyBeanNotNull2Bean(pJoinBuy, t);//将编辑表单中的非NULL值覆盖数据库记录中的值
			pJoinBuyService.save(t);//保存
		}else{//新增表单保存
			pJoinBuyService.save(pJoinBuy);//保存
		}
		addMessage(redirectAttributes, "保存JoinBuy成功");
		return "redirect:"+Global.getAdminPath()+"/lottery/pJoinBuy/?repage";
	}
	
	/**
	 * 删除JoinBuy
	 */
	@RequiresPermissions("lottery:pJoinBuy:del")
	@RequestMapping(value = "delete")
	public String delete(PJoinBuy pJoinBuy, RedirectAttributes redirectAttributes) {
		pJoinBuyService.delete(pJoinBuy);
		addMessage(redirectAttributes, "删除JoinBuy成功");
		return "redirect:"+Global.getAdminPath()+"/lottery/pJoinBuy/?repage";
	}
	
	/**
	 * 批量删除JoinBuy
	 */
	@RequiresPermissions("lottery:pJoinBuy:del")
	@RequestMapping(value = "deleteAll")
	public String deleteAll(String ids, RedirectAttributes redirectAttributes) {
		String idArray[] =ids.split(",");
		for(String id : idArray){
			pJoinBuyService.delete(pJoinBuyService.get(id));
		}
		addMessage(redirectAttributes, "删除JoinBuy成功");
		return "redirect:"+Global.getAdminPath()+"/lottery/pJoinBuy/?repage";
	}
	
	/**
	 * 导出excel文件
	 */
	@RequiresPermissions("lottery:pJoinBuy:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(PJoinBuy pJoinBuy, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "JoinBuy"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<PJoinBuy> page = pJoinBuyService.findPage(new Page<PJoinBuy>(request, response, -1), pJoinBuy);
    		new ExportExcel("JoinBuy", PJoinBuy.class).setDataList(page.getList()).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出JoinBuy记录失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/lottery/pJoinBuy/?repage";
    }

	/**
	 * 导入Excel数据

	 */
	@RequiresPermissions("lottery:pJoinBuy:import")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<PJoinBuy> list = ei.getDataList(PJoinBuy.class);
			for (PJoinBuy pJoinBuy : list){
				try{
					pJoinBuyService.save(pJoinBuy);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条JoinBuy记录。");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条JoinBuy记录"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入JoinBuy失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/lottery/pJoinBuy/?repage";
    }
	
	/**
	 * 下载导入JoinBuy数据模板
	 */
	@RequiresPermissions("lottery:pJoinBuy:import")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "JoinBuy数据导入模板.xlsx";
    		List<PJoinBuy> list = Lists.newArrayList(); 
    		new ExportExcel("JoinBuy数据", PJoinBuy.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/lottery/pJoinBuy/?repage";
    }
	
	
	

}