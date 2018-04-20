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
import com.jeeplus.modules.lottery.entity.PLoginRecord;
import com.jeeplus.modules.lottery.service.PLoginRecordService;

/**
 * 登录记录Controller
 * @author asd
 * @version 2018-01-29
 */
@Controller
@RequestMapping(value = "${adminPath}/lottery/pLoginRecord")
public class PLoginRecordController extends BaseController {

	@Autowired
	private PLoginRecordService pLoginRecordService;
	
	@ModelAttribute
	public PLoginRecord get(@RequestParam(required=false) String id) {
		PLoginRecord entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = pLoginRecordService.get(id);
		}
		if (entity == null){
			entity = new PLoginRecord();
		}
		return entity;
	}
	
	/**
	 * 登录记录列表页面
	 */
	@RequiresPermissions("lottery:pLoginRecord:list")
	@RequestMapping(value = {"list", ""})
	public String list(PLoginRecord pLoginRecord, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PLoginRecord> page = pLoginRecordService.findPage(new Page<PLoginRecord>(request, response), pLoginRecord); 
		model.addAttribute("page", page);
		model.addAttribute("pLoginRecord", pLoginRecord);
		return "modules/lottery/pLoginRecordList";
	}

	/**
	 * 查看，增加，编辑登录记录表单页面
	 */
	@RequiresPermissions(value={"lottery:pLoginRecord:view","lottery:pLoginRecord:add","lottery:pLoginRecord:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(PLoginRecord pLoginRecord, Model model) {
		model.addAttribute("pLoginRecord", pLoginRecord);
		return "modules/lottery/pLoginRecordForm";
	}

	/**
	 * 保存登录记录
	 */
	@RequiresPermissions(value={"lottery:pLoginRecord:add","lottery:pLoginRecord:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public String save(PLoginRecord pLoginRecord, Model model, RedirectAttributes redirectAttributes) throws Exception{
		if (!beanValidator(model, pLoginRecord)){
			return form(pLoginRecord, model);
		}
		if(!pLoginRecord.getIsNewRecord()){//编辑表单保存
			PLoginRecord t = pLoginRecordService.get(pLoginRecord.getId());//从数据库取出记录的值
			MyBeanUtils.copyBeanNotNull2Bean(pLoginRecord, t);//将编辑表单中的非NULL值覆盖数据库记录中的值
			pLoginRecordService.save(t);//保存
		}else{//新增表单保存
			pLoginRecordService.save(pLoginRecord);//保存
		}
		addMessage(redirectAttributes, "保存登录记录成功");
		return "redirect:"+Global.getAdminPath()+"/lottery/pLoginRecord/?repage";
	}
	
	/**
	 * 删除登录记录
	 */
	@RequiresPermissions("lottery:pLoginRecord:del")
	@RequestMapping(value = "delete")
	public String delete(PLoginRecord pLoginRecord, RedirectAttributes redirectAttributes) {
		pLoginRecordService.delete(pLoginRecord);
		addMessage(redirectAttributes, "删除登录记录成功");
		return "redirect:"+Global.getAdminPath()+"/lottery/pLoginRecord/?repage";
	}
	
	/**
	 * 批量删除登录记录
	 */
	@RequiresPermissions("lottery:pLoginRecord:del")
	@RequestMapping(value = "deleteAll")
	public String deleteAll(String ids, RedirectAttributes redirectAttributes) {
		String idArray[] =ids.split(",");
		for(String id : idArray){
			pLoginRecordService.delete(pLoginRecordService.get(id));
		}
		addMessage(redirectAttributes, "删除登录记录成功");
		return "redirect:"+Global.getAdminPath()+"/lottery/pLoginRecord/?repage";
	}
	
	/**
	 * 导出excel文件
	 */
	@RequiresPermissions("lottery:pLoginRecord:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(PLoginRecord pLoginRecord, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "登录记录"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<PLoginRecord> page = pLoginRecordService.findPage(new Page<PLoginRecord>(request, response, -1), pLoginRecord);
    		new ExportExcel("登录记录", PLoginRecord.class).setDataList(page.getList()).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出登录记录记录失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/lottery/pLoginRecord/?repage";
    }

	/**
	 * 导入Excel数据

	 */
	@RequiresPermissions("lottery:pLoginRecord:import")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<PLoginRecord> list = ei.getDataList(PLoginRecord.class);
			for (PLoginRecord pLoginRecord : list){
				try{
					pLoginRecordService.save(pLoginRecord);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条登录记录记录。");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条登录记录记录"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入登录记录失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/lottery/pLoginRecord/?repage";
    }
	
	/**
	 * 下载导入登录记录数据模板
	 */
	@RequiresPermissions("lottery:pLoginRecord:import")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "登录记录数据导入模板.xlsx";
    		List<PLoginRecord> list = Lists.newArrayList(); 
    		new ExportExcel("登录记录数据", PLoginRecord.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/lottery/pLoginRecord/?repage";
    }
	
	
	

}