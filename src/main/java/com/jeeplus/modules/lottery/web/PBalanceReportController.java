/**
 * apple
 */
package com.jeeplus.modules.lottery.web;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jeeplus.common.config.Global;
import com.jeeplus.common.utils.DateUtils;
import com.jeeplus.common.utils.excel.ExportExcel;
import com.jeeplus.common.web.BaseController;
import com.jeeplus.modules.lottery.service.PBalanceRecordService;
import com.jeeplus.modules.lottery.vo.PlayerBalanceVo;

/**
 * 余额记录Controller
 * @author asd
 * @version 2018-01-29
 */
@Controller
@RequestMapping(value = "${adminPath}/lottery/pBalanceReport")
public class PBalanceReportController extends BaseController {

	@Autowired
	private PBalanceRecordService pBalanceRecordService;
	
	
	
	/**
	 * 余额记录列表页面
	 */
	@RequiresPermissions("lottery:pBalanceReport:list")
	@RequestMapping(value = "toPage")
	public String toPage(PlayerBalanceVo playerBalanceVo, HttpServletRequest request, HttpServletResponse response, Model model) {
		model.addAttribute("dataList", new ArrayList<PlayerBalanceVo>());
		model.addAttribute("playerBalanceVo", playerBalanceVo);
		return "modules/report/pBalanceRecordReport";
	}
	
	@RequiresPermissions("lottery:pBalanceReport:list")
	@RequestMapping(value = "list")
	public String list(PlayerBalanceVo playerBalanceVo, HttpServletRequest request, HttpServletResponse response, Model model) {
		model.addAttribute("dataList", pBalanceRecordService.findBalanceByVo(playerBalanceVo));
		model.addAttribute("playerBalanceVo", playerBalanceVo);
		return "modules/report/pBalanceRecordReport";
	}
	
	/**
	 * 导出excel文件
	 */
	@RequiresPermissions("lottery:pBalanceReport:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(PlayerBalanceVo playerBalanceVo, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "余额记录报表"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
    		new ExportExcel("余额记录报表", PlayerBalanceVo.class).setDataList(pBalanceRecordService.findBalanceByVo(playerBalanceVo)).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出余额记录报表失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/lottery/pBalanceReport/toPage";
    }
	
	/**
	 * 余额记录列表页面
	 */
	@RequiresPermissions("lottery:pBalanceReport:listFP")
	@RequestMapping(value = "toPageFP")
	public String toPageFP(PlayerBalanceVo playerBalanceVo, HttpServletRequest request, HttpServletResponse response, Model model) {
		model.addAttribute("dataList", new ArrayList<PlayerBalanceVo>());
		model.addAttribute("playerBalanceVo", playerBalanceVo);
		return "modules/report/pBalanceRecordForParentReport";
	}
	
	@RequiresPermissions("lottery:pBalanceReport:listFP")
	@RequestMapping(value = "listFP")
	public String listFP(PlayerBalanceVo playerBalanceVo, HttpServletRequest request, HttpServletResponse response, Model model) {
		model.addAttribute("dataList", pBalanceRecordService.findBalanceByVoForParent(playerBalanceVo));
		model.addAttribute("playerBalanceVo", playerBalanceVo);
		return "modules/report/pBalanceRecordForParentReport";
	}
	
	/**
	 * 导出excel文件
	 */
	@RequiresPermissions("lottery:pBalanceReport:exportFP")
    @RequestMapping(value = "exportFP", method=RequestMethod.POST)
    public String exportFileFP(PlayerBalanceVo playerBalanceVo, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "余额记录报表"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
    		new ExportExcel("余额记录报表", PlayerBalanceVo.class).setDataList(pBalanceRecordService.findBalanceByVoForParent(playerBalanceVo)).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出余额记录报表失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/lottery/pBalanceReport/toPageFP";
    }

	
	

}