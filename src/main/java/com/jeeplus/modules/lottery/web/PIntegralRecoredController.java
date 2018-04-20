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
import com.jeeplus.modules.lottery.entity.PIntegralRecored;
import com.jeeplus.modules.lottery.service.PIntegralRecoredService;

/**
 * 积分记录Controller
 * @author asd
 * @version 2018-01-29
 */
@Controller
@RequestMapping(value = "${adminPath}/lottery/pIntegralRecored")
public class PIntegralRecoredController extends BaseController {

	@Autowired
	private PIntegralRecoredService pIntegralRecoredService;
	
	@ModelAttribute
	public PIntegralRecored get(@RequestParam(required=false) String id) {
		PIntegralRecored entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = pIntegralRecoredService.get(id);
		}
		if (entity == null){
			entity = new PIntegralRecored();
		}
		return entity;
	}
	
	/**
	 * 积分记录列表页面
	 */
	@RequiresPermissions("lottery:pIntegralRecored:list")
	@RequestMapping(value = {"list", ""})
	public String list(PIntegralRecored pIntegralRecored, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PIntegralRecored> page = pIntegralRecoredService.findPage(new Page<PIntegralRecored>(request, response), pIntegralRecored); 
		model.addAttribute("page", page);
		model.addAttribute("pIntegralRecored", pIntegralRecored);
		return "modules/lottery/pIntegralRecoredList";
	}

	/**
	 * 查看，增加，编辑积分记录表单页面
	 */
	@RequiresPermissions(value={"lottery:pIntegralRecored:view","lottery:pIntegralRecored:add","lottery:pIntegralRecored:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(PIntegralRecored pIntegralRecored, Model model) {
		model.addAttribute("pIntegralRecored", pIntegralRecored);
		return "modules/lottery/pIntegralRecoredForm";
	}

	/**
	 * 保存积分记录
	 */
	@RequiresPermissions(value={"lottery:pIntegralRecored:add","lottery:pIntegralRecored:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public String save(PIntegralRecored pIntegralRecored, Model model, RedirectAttributes redirectAttributes) throws Exception{
		if (!beanValidator(model, pIntegralRecored)){
			return form(pIntegralRecored, model);
		}
		if(!pIntegralRecored.getIsNewRecord()){//编辑表单保存
			PIntegralRecored t = pIntegralRecoredService.get(pIntegralRecored.getId());//从数据库取出记录的值
			MyBeanUtils.copyBeanNotNull2Bean(pIntegralRecored, t);//将编辑表单中的非NULL值覆盖数据库记录中的值
			pIntegralRecoredService.save(t);//保存
		}else{//新增表单保存
			pIntegralRecoredService.save(pIntegralRecored);//保存
		}
		addMessage(redirectAttributes, "保存积分记录成功");
		return "redirect:"+Global.getAdminPath()+"/lottery/pIntegralRecored/?repage";
	}
	
	/**
	 * 删除积分记录
	 */
	@RequiresPermissions("lottery:pIntegralRecored:del")
	@RequestMapping(value = "delete")
	public String delete(PIntegralRecored pIntegralRecored, RedirectAttributes redirectAttributes) {
		pIntegralRecoredService.delete(pIntegralRecored);
		addMessage(redirectAttributes, "删除积分记录成功");
		return "redirect:"+Global.getAdminPath()+"/lottery/pIntegralRecored/?repage";
	}
	
	/**
	 * 批量删除积分记录
	 */
	@RequiresPermissions("lottery:pIntegralRecored:del")
	@RequestMapping(value = "deleteAll")
	public String deleteAll(String ids, RedirectAttributes redirectAttributes) {
		String idArray[] =ids.split(",");
		for(String id : idArray){
			pIntegralRecoredService.delete(pIntegralRecoredService.get(id));
		}
		addMessage(redirectAttributes, "删除积分记录成功");
		return "redirect:"+Global.getAdminPath()+"/lottery/pIntegralRecored/?repage";
	}
	
	/**
	 * 导出excel文件
	 */
	@RequiresPermissions("lottery:pIntegralRecored:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(PIntegralRecored pIntegralRecored, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "积分记录"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<PIntegralRecored> page = pIntegralRecoredService.findPage(new Page<PIntegralRecored>(request, response, -1), pIntegralRecored);
    		new ExportExcel("积分记录", PIntegralRecored.class).setDataList(page.getList()).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出积分记录记录失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/lottery/pIntegralRecored/?repage";
    }

	/**
	 * 导入Excel数据

	 */
	@RequiresPermissions("lottery:pIntegralRecored:import")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<PIntegralRecored> list = ei.getDataList(PIntegralRecored.class);
			for (PIntegralRecored pIntegralRecored : list){
				try{
					pIntegralRecoredService.save(pIntegralRecored);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条积分记录记录。");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条积分记录记录"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入积分记录失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/lottery/pIntegralRecored/?repage";
    }
	
	/**
	 * 下载导入积分记录数据模板
	 */
	@RequiresPermissions("lottery:pIntegralRecored:import")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "积分记录数据导入模板.xlsx";
    		List<PIntegralRecored> list = Lists.newArrayList(); 
    		new ExportExcel("积分记录数据", PIntegralRecored.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/lottery/pIntegralRecored/?repage";
    }
	
	
	

}