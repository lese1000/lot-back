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
import com.jeeplus.modules.lottery.entity.PBalance;
import com.jeeplus.modules.lottery.service.PBalanceService;

/**
 * 余额Controller
 * @author asd
 * @version 2018-01-29
 */
@Controller
@RequestMapping(value = "${adminPath}/lottery/pBalance")
public class PBalanceController extends BaseController {

	@Autowired
	private PBalanceService pBalanceService;
	
	@ModelAttribute
	public PBalance get(@RequestParam(required=false) String id) {
		PBalance entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = pBalanceService.get(id);
		}
		if (entity == null){
			entity = new PBalance();
		}
		return entity;
	}
	
	/**
	 * 余额列表页面
	 */
	@RequiresPermissions("lottery:pBalance:list")
	@RequestMapping(value = {"list", ""})
	public String list(PBalance pBalance, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PBalance> page = pBalanceService.findPage(new Page<PBalance>(request, response), pBalance); 
		model.addAttribute("page", page);
		return "modules/lottery/pBalanceList";
	}

	/**
	 * 查看，增加，编辑余额表单页面
	 */
	@RequiresPermissions(value={"lottery:pBalance:view","lottery:pBalance:add","lottery:pBalance:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(PBalance pBalance, Model model) {
		model.addAttribute("pBalance", pBalance);
		return "modules/lottery/pBalanceForm";
	}

	/**
	 * 保存余额
	 */
	@RequiresPermissions(value={"lottery:pBalance:add","lottery:pBalance:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public String save(PBalance pBalance, Model model, RedirectAttributes redirectAttributes) throws Exception{
		if (!beanValidator(model, pBalance)){
			return form(pBalance, model);
		}
		if(!pBalance.getIsNewRecord()){//编辑表单保存
			PBalance t = pBalanceService.get(pBalance.getId());//从数据库取出记录的值
			MyBeanUtils.copyBeanNotNull2Bean(pBalance, t);//将编辑表单中的非NULL值覆盖数据库记录中的值
			pBalanceService.save(t);//保存
		}else{//新增表单保存
			pBalanceService.save(pBalance);//保存
		}
		addMessage(redirectAttributes, "保存余额成功");
		return "redirect:"+Global.getAdminPath()+"/lottery/pBalance/?repage";
	}
	
	/**
	 * 删除余额
	 */
	@RequiresPermissions("lottery:pBalance:del")
	@RequestMapping(value = "delete")
	public String delete(PBalance pBalance, RedirectAttributes redirectAttributes) {
		pBalanceService.delete(pBalance);
		addMessage(redirectAttributes, "删除余额成功");
		return "redirect:"+Global.getAdminPath()+"/lottery/pBalance/?repage";
	}
	
	/**
	 * 批量删除余额
	 */
	@RequiresPermissions("lottery:pBalance:del")
	@RequestMapping(value = "deleteAll")
	public String deleteAll(String ids, RedirectAttributes redirectAttributes) {
		String idArray[] =ids.split(",");
		for(String id : idArray){
			pBalanceService.delete(pBalanceService.get(id));
		}
		addMessage(redirectAttributes, "删除余额成功");
		return "redirect:"+Global.getAdminPath()+"/lottery/pBalance/?repage";
	}
	
	/**
	 * 导出excel文件
	 */
	@RequiresPermissions("lottery:pBalance:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(PBalance pBalance, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "余额"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<PBalance> page = pBalanceService.findPage(new Page<PBalance>(request, response, -1), pBalance);
    		new ExportExcel("余额", PBalance.class).setDataList(page.getList()).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出余额记录失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/lottery/pBalance/?repage";
    }

	/**
	 * 导入Excel数据

	 */
	@RequiresPermissions("lottery:pBalance:import")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<PBalance> list = ei.getDataList(PBalance.class);
			for (PBalance pBalance : list){
				try{
					pBalanceService.save(pBalance);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条余额记录。");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条余额记录"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入余额失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/lottery/pBalance/?repage";
    }
	
	/**
	 * 下载导入余额数据模板
	 */
	@RequiresPermissions("lottery:pBalance:import")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "余额数据导入模板.xlsx";
    		List<PBalance> list = Lists.newArrayList(); 
    		new ExportExcel("余额数据", PBalance.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/lottery/pBalance/?repage";
    }
	
	
	

}