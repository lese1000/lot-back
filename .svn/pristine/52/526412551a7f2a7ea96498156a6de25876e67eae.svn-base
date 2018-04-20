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
import com.jeeplus.common.config.Global;
import com.jeeplus.common.persistence.Page;
import com.jeeplus.common.utils.DateUtils;
import com.jeeplus.common.utils.MyBeanUtils;
import com.jeeplus.common.utils.StringUtils;
import com.jeeplus.common.utils.excel.ExportExcel;
import com.jeeplus.common.utils.excel.ImportExcel;
import com.jeeplus.common.web.BaseController;
import com.jeeplus.modules.lottery.entity.LLotteryRule;
import com.jeeplus.modules.lottery.service.LLotteryRuleService;

/**
 * 彩票规则Controller
 * @author asd
 * @version 2018-01-29
 */
@Controller
@RequestMapping(value = "${adminPath}/lottery/lLotteryRule")
public class LLotteryRuleController extends BaseController {

	@Autowired
	private LLotteryRuleService lLotteryRuleService;
	
	@ModelAttribute
	public LLotteryRule get(@RequestParam(required=false) String id) {
		LLotteryRule entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = lLotteryRuleService.get(id);
		}
		if (entity == null){
			entity = new LLotteryRule();
		}
		return entity;
	}
	
	/**
	 * 彩票规则列表页面
	 */
	@RequiresPermissions("lottery:lLotteryRule:list")
	@RequestMapping(value = {"list", ""})
	public String list(LLotteryRule lLotteryRule, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<LLotteryRule> page = lLotteryRuleService.findPage(new Page<LLotteryRule>(request, response), lLotteryRule); 
		model.addAttribute("page", page);
		model.addAttribute("lLotteryRule", lLotteryRule);
		return "modules/lottery/lLotteryRuleList";
	}

	/**
	 * 查看，增加，编辑彩票规则表单页面
	 */
	@RequiresPermissions(value={"lottery:lLotteryRule:view","lottery:lLotteryRule:add","lottery:lLotteryRule:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(LLotteryRule lLotteryRule, Model model) {
		model.addAttribute("lLotteryRule", lLotteryRule);
		model.addAttribute("ruleList", lLotteryRuleService.findRuleList(lLotteryRule.getLotteryTypeId()));
		return "modules/lottery/lLotteryRuleForm";
	}

	/**
	 * 保存彩票规则
	 */
	@RequiresPermissions(value={"lottery:lLotteryRule:add","lottery:lLotteryRule:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public String save(LLotteryRule lLotteryRule, Model model, RedirectAttributes redirectAttributes) throws Exception{
		if (lLotteryRule.getRuleId()==null){
			return form(lLotteryRule, model);
		}
		if(!lLotteryRule.getIsNewRecord()){//编辑表单保存
			LLotteryRule t = lLotteryRuleService.get(lLotteryRule.getId());//从数据库取出记录的值
			MyBeanUtils.copyBeanNotNull2Bean(lLotteryRule, t);//将编辑表单中的非NULL值覆盖数据库记录中的值
			if(t.getPlusAwards()==null){
				t.setPlusAwards(0d);
			}
			lLotteryRuleService.save(t);//保存
		}else{//新增表单保存
			if(lLotteryRule.getPlusAwards()==null){
				lLotteryRule.setPlusAwards(0d);
			}
			lLotteryRuleService.save(lLotteryRule);//保存
		}
		addMessage(redirectAttributes, "保存彩票规则成功");
		return "redirect:"+Global.getAdminPath()+"/lottery/lLotteryRule/?lotteryTypeId="+lLotteryRule.getLotteryTypeId();
	}
	
	/**
	 * 删除彩票规则
	 */
	@RequiresPermissions("lottery:lLotteryRule:del")
	@RequestMapping(value = "delete")
	public String delete(LLotteryRule lLotteryRule, RedirectAttributes redirectAttributes) {
		lLotteryRuleService.delete(lLotteryRule);
		addMessage(redirectAttributes, "删除彩票规则成功");
		return "redirect:"+Global.getAdminPath()+"/lottery/lLotteryRule/?lotteryTypeId="+lLotteryRule.getLotteryTypeId();
	}
	
	/**
	 * 批量删除彩票规则
	 */
	@RequiresPermissions("lottery:lLotteryRule:del")
	@RequestMapping(value = "deleteAll")
	public String deleteAll(String ids,Long lotteryTypeId, RedirectAttributes redirectAttributes) {
		String idArray[] =ids.split(",");
		for(String id : idArray){
			lLotteryRuleService.delete(lLotteryRuleService.get(id));
		}
		addMessage(redirectAttributes, "删除彩票规则成功");
		return "redirect:"+Global.getAdminPath()+"/lottery/lLotteryRule/?lotteryTypeId"+lotteryTypeId;
	}
	
	/**
	 * 导出excel文件
	 */
	@RequiresPermissions("lottery:lLotteryRule:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(LLotteryRule lLotteryRule, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "彩票规则"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<LLotteryRule> page = lLotteryRuleService.findPage(new Page<LLotteryRule>(request, response, -1), lLotteryRule);
    		new ExportExcel("彩票规则", LLotteryRule.class).setDataList(page.getList()).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出彩票规则记录失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/lottery/lLotteryRule/?repage";
    }

	/**
	 * 导入Excel数据

	 */
	@RequiresPermissions("lottery:lLotteryRule:import")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<LLotteryRule> list = ei.getDataList(LLotteryRule.class);
			for (LLotteryRule lLotteryRule : list){
				try{
					lLotteryRuleService.save(lLotteryRule);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条彩票规则记录。");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条彩票规则记录"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入彩票规则失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/lottery/lLotteryRule/?repage";
    }
	
	/**
	 * 下载导入彩票规则数据模板
	 */
	@RequiresPermissions("lottery:lLotteryRule:import")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "彩票规则数据导入模板.xlsx";
    		List<LLotteryRule> list = Lists.newArrayList(); 
    		new ExportExcel("彩票规则数据", LLotteryRule.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/lottery/lLotteryRule/?repage";
    }
	
	
	

}