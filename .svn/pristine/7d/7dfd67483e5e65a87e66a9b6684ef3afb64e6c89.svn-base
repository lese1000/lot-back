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
import com.jeeplus.modules.lottery.entity.PIntegral;
import com.jeeplus.modules.lottery.service.PIntegralService;

/**
 * 积分Controller
 * @author asd
 * @version 2018-01-29
 */
@Controller
@RequestMapping(value = "${adminPath}/lottery/pIntegral")
public class PIntegralController extends BaseController {

	@Autowired
	private PIntegralService pIntegralService;
	
	@ModelAttribute
	public PIntegral get(@RequestParam(required=false) String id) {
		PIntegral entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = pIntegralService.get(id);
		}
		if (entity == null){
			entity = new PIntegral();
		}
		return entity;
	}
	
	/**
	 * 积分列表页面
	 */
	@RequiresPermissions("lottery:pIntegral:list")
	@RequestMapping(value = {"list", ""})
	public String list(PIntegral pIntegral, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PIntegral> page = pIntegralService.findPage(new Page<PIntegral>(request, response), pIntegral); 
		model.addAttribute("page", page);
		return "modules/lottery/pIntegralList";
	}

	/**
	 * 查看，增加，编辑积分表单页面
	 */
	@RequiresPermissions(value={"lottery:pIntegral:view","lottery:pIntegral:add","lottery:pIntegral:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(PIntegral pIntegral, Model model) {
		model.addAttribute("pIntegral", pIntegral);
		return "modules/lottery/pIntegralForm";
	}

	/**
	 * 保存积分
	 */
	@RequiresPermissions(value={"lottery:pIntegral:add","lottery:pIntegral:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public String save(PIntegral pIntegral, Model model, RedirectAttributes redirectAttributes) throws Exception{
		if (!beanValidator(model, pIntegral)){
			return form(pIntegral, model);
		}
		if(!pIntegral.getIsNewRecord()){//编辑表单保存
			PIntegral t = pIntegralService.get(pIntegral.getId());//从数据库取出记录的值
			MyBeanUtils.copyBeanNotNull2Bean(pIntegral, t);//将编辑表单中的非NULL值覆盖数据库记录中的值
			pIntegralService.save(t);//保存
		}else{//新增表单保存
			pIntegralService.save(pIntegral);//保存
		}
		addMessage(redirectAttributes, "保存积分成功");
		return "redirect:"+Global.getAdminPath()+"/lottery/pIntegral/?repage";
	}
	
	/**
	 * 删除积分
	 */
	@RequiresPermissions("lottery:pIntegral:del")
	@RequestMapping(value = "delete")
	public String delete(PIntegral pIntegral, RedirectAttributes redirectAttributes) {
		pIntegralService.delete(pIntegral);
		addMessage(redirectAttributes, "删除积分成功");
		return "redirect:"+Global.getAdminPath()+"/lottery/pIntegral/?repage";
	}
	
	/**
	 * 批量删除积分
	 */
	@RequiresPermissions("lottery:pIntegral:del")
	@RequestMapping(value = "deleteAll")
	public String deleteAll(String ids, RedirectAttributes redirectAttributes) {
		String idArray[] =ids.split(",");
		for(String id : idArray){
			pIntegralService.delete(pIntegralService.get(id));
		}
		addMessage(redirectAttributes, "删除积分成功");
		return "redirect:"+Global.getAdminPath()+"/lottery/pIntegral/?repage";
	}
	
	/**
	 * 导出excel文件
	 */
	@RequiresPermissions("lottery:pIntegral:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(PIntegral pIntegral, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "积分"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<PIntegral> page = pIntegralService.findPage(new Page<PIntegral>(request, response, -1), pIntegral);
    		new ExportExcel("积分", PIntegral.class).setDataList(page.getList()).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出积分记录失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/lottery/pIntegral/?repage";
    }

	/**
	 * 导入Excel数据

	 */
	@RequiresPermissions("lottery:pIntegral:import")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<PIntegral> list = ei.getDataList(PIntegral.class);
			for (PIntegral pIntegral : list){
				try{
					pIntegralService.save(pIntegral);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条积分记录。");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条积分记录"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入积分失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/lottery/pIntegral/?repage";
    }
	
	/**
	 * 下载导入积分数据模板
	 */
	@RequiresPermissions("lottery:pIntegral:import")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "积分数据导入模板.xlsx";
    		List<PIntegral> list = Lists.newArrayList(); 
    		new ExportExcel("积分数据", PIntegral.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/lottery/pIntegral/?repage";
    }
	
	
	

}