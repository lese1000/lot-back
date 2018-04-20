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
import com.jeeplus.modules.lottery.entity.LLotteryType;
import com.jeeplus.modules.lottery.service.LLotteryTypeService;

/**
 * 彩票种类Controller
 * @author asd
 * @version 2018-01-29
 */
@Controller
@RequestMapping(value = "${adminPath}/lottery/lLotteryType")
public class LLotteryTypeController extends BaseController {

	@Autowired
	private LLotteryTypeService lLotteryTypeService;
	
	@ModelAttribute
	public LLotteryType get(@RequestParam(required=false) String id) {
		LLotteryType entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = lLotteryTypeService.get(id);
		}
		if (entity == null){
			entity = new LLotteryType();
		}
		return entity;
	}
	
	/**
	 * 彩票种类列表页面
	 */
	@RequiresPermissions("lottery:lLotteryType:list")
	@RequestMapping(value = {"list", ""})
	public String list(LLotteryType lLotteryType, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<LLotteryType> page = lLotteryTypeService.findPage(new Page<LLotteryType>(request, response), lLotteryType); 
		model.addAttribute("page", page);
		model.addAttribute("lLotteryType", lLotteryType);
		return "modules/lottery/lLotteryTypeList";
	}

	/**
	 * 查看，增加，编辑彩票种类表单页面
	 */
	@RequiresPermissions(value={"lottery:lLotteryType:view","lottery:lLotteryType:add","lottery:lLotteryType:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(LLotteryType lLotteryType, Model model) {
		model.addAttribute("lLotteryType", lLotteryType);
		return "modules/lottery/lLotteryTypeForm";
	}

	/**
	 * 保存彩票种类
	 */
	@RequiresPermissions(value={"lottery:lLotteryType:add","lottery:lLotteryType:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public String save(LLotteryType lLotteryType, Model model, RedirectAttributes redirectAttributes) throws Exception{
		if (!beanValidator(model, lLotteryType)){
			return form(lLotteryType, model);
		}
		if(!lLotteryType.getIsNewRecord()){//编辑表单保存
			LLotteryType t = lLotteryTypeService.get(lLotteryType.getId());//从数据库取出记录的值
			MyBeanUtils.copyBeanNotNull2Bean(lLotteryType, t);//将编辑表单中的非NULL值覆盖数据库记录中的值
			lLotteryTypeService.save(t);//保存
		}else{//新增表单保存
			lLotteryTypeService.save(lLotteryType);//保存
		}
		addMessage(redirectAttributes, "保存彩票种类成功");
		return "redirect:"+Global.getAdminPath()+"/lottery/lLotteryType/?repage";
	}
	
	/**
	 * 删除彩票种类
	 */
	@RequiresPermissions("lottery:lLotteryType:del")
	@RequestMapping(value = "delete")
	public String delete(LLotteryType lLotteryType, RedirectAttributes redirectAttributes) {
		lLotteryTypeService.deleteByLogic(lLotteryType);
		addMessage(redirectAttributes, "删除彩票种类成功");
		return "redirect:"+Global.getAdminPath()+"/lottery/lLotteryType/?repage";
	}
	
	/**
	 * 批量删除彩票种类
	 */
	@RequiresPermissions("lottery:lLotteryType:del")
	@RequestMapping(value = "deleteAll")
	public String deleteAll(String ids, RedirectAttributes redirectAttributes) {
		String idArray[] =ids.split(",");
		for(String id : idArray){
			lLotteryTypeService.deleteByLogic(lLotteryTypeService.get(id));
		}
		addMessage(redirectAttributes, "删除彩票种类成功");
		return "redirect:"+Global.getAdminPath()+"/lottery/lLotteryType/?repage";
	}
	
	/**
	 * 导出excel文件
	 */
	@RequiresPermissions("lottery:lLotteryType:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(LLotteryType lLotteryType, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "彩票种类"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<LLotteryType> page = lLotteryTypeService.findPage(new Page<LLotteryType>(request, response, -1), lLotteryType);
    		new ExportExcel("彩票种类", LLotteryType.class).setDataList(page.getList()).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出彩票种类记录失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/lottery/lLotteryType/?repage";
    }

	/**
	 * 导入Excel数据

	 */
	@RequiresPermissions("lottery:lLotteryType:import")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<LLotteryType> list = ei.getDataList(LLotteryType.class);
			for (LLotteryType lLotteryType : list){
				try{
					lLotteryTypeService.save(lLotteryType);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条彩票种类记录。");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条彩票种类记录"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入彩票种类失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/lottery/lLotteryType/?repage";
    }
	
	/**
	 * 下载导入彩票种类数据模板
	 */
	@RequiresPermissions("lottery:lLotteryType:import")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "彩票种类数据导入模板.xlsx";
    		List<LLotteryType> list = Lists.newArrayList(); 
    		new ExportExcel("彩票种类数据", LLotteryType.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/lottery/lLotteryType/?repage";
    }
	
	
	

}