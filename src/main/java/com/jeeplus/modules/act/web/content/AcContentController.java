/**
 * apple
 */
package com.jeeplus.modules.act.web.content;

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
import com.jeeplus.modules.act.entity.content.AcContent;
import com.jeeplus.modules.act.service.content.AcContentService;

/**
 * 公告和活动Controller
 * @author asd
 * @version 2018-01-23
 */
@Controller
@RequestMapping(value = "${adminPath}/act/content/acContent")
public class AcContentController extends BaseController {

	@Autowired
	private AcContentService acContentService;
	
	@ModelAttribute
	public AcContent get(@RequestParam(required=false) String id) {
		AcContent entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = acContentService.get(id);
		}
		if (entity == null){
			entity = new AcContent();
		}
		return entity;
	}
	
	/**
	 * 公告和活动列表页面
	 */
	@RequiresPermissions("act:content:acContent:list")
	@RequestMapping(value = {"list", ""})
	public String list(AcContent acContent, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<AcContent> page = acContentService.findPage(new Page<AcContent>(request, response), acContent); 
		model.addAttribute("page", page);
		return "modules/act/content/acContentList";
	}

	/**
	 * 查看，增加，编辑公告和活动表单页面
	 */
	@RequiresPermissions(value={"act:content:acContent:view","act:content:acContent:add","act:content:acContent:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(AcContent acContent, Model model) {
		model.addAttribute("acContent", acContent);
		return "modules/act/content/acContentForm";
	}

	/**
	 * 保存公告和活动
	 */
	@RequiresPermissions(value={"act:content:acContent:add","act:content:acContent:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public String save(AcContent acContent, Model model, RedirectAttributes redirectAttributes) throws Exception{
		if (!beanValidator(model, acContent)){
			return form(acContent, model);
		}
		if(!acContent.getIsNewRecord()){//编辑表单保存
			AcContent t = acContentService.get(acContent.getId());//从数据库取出记录的值
			MyBeanUtils.copyBeanNotNull2Bean(acContent, t);//将编辑表单中的非NULL值覆盖数据库记录中的值
			acContentService.save(t);//保存
		}else{//新增表单保存
			acContentService.save(acContent);//保存
		}
		addMessage(redirectAttributes, "保存公告和活动成功");
		return "redirect:"+Global.getAdminPath()+"/act/content/acContent/?repage";
	}
	
	/**
	 * 删除公告和活动
	 */
	@RequiresPermissions("act:content:acContent:del")
	@RequestMapping(value = "delete")
	public String delete(AcContent acContent, RedirectAttributes redirectAttributes) {
		acContentService.delete(acContent);
		addMessage(redirectAttributes, "删除公告和活动成功");
		return "redirect:"+Global.getAdminPath()+"/act/content/acContent/?repage";
	}
	
	/**
	 * 批量删除公告和活动
	 */
	@RequiresPermissions("act:content:acContent:del")
	@RequestMapping(value = "deleteAll")
	public String deleteAll(String ids, RedirectAttributes redirectAttributes) {
		String idArray[] =ids.split(",");
		for(String id : idArray){
			acContentService.delete(acContentService.get(id));
		}
		addMessage(redirectAttributes, "删除公告和活动成功");
		return "redirect:"+Global.getAdminPath()+"/act/content/acContent/?repage";
	}
	
	/**
	 * 导出excel文件
	 */
	@RequiresPermissions("act:content:acContent:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(AcContent acContent, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "公告和活动"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<AcContent> page = acContentService.findPage(new Page<AcContent>(request, response, -1), acContent);
    		new ExportExcel("公告和活动", AcContent.class).setDataList(page.getList()).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出公告和活动记录失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/act/content/acContent/?repage";
    }

	/**
	 * 导入Excel数据

	 */
	@RequiresPermissions("act:content:acContent:import")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<AcContent> list = ei.getDataList(AcContent.class);
			for (AcContent acContent : list){
				try{
					acContentService.save(acContent);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条公告和活动记录。");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条公告和活动记录"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入公告和活动失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/act/content/acContent/?repage";
    }
	
	/**
	 * 下载导入公告和活动数据模板
	 */
	@RequiresPermissions("act:content:acContent:import")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "公告和活动数据导入模板.xlsx";
    		List<AcContent> list = Lists.newArrayList(); 
    		new ExportExcel("公告和活动数据", AcContent.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/act/content/acContent/?repage";
    }
	
	
	

}