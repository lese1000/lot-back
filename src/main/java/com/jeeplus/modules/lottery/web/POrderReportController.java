/**
 * apple
 */
package com.jeeplus.modules.lottery.web;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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
import com.jeeplus.modules.lottery.service.POrderService;
import com.jeeplus.modules.lottery.vo.DayOrderVo;
import com.jeeplus.modules.lottery.vo.OrderLotteryNumVo;
import com.jeeplus.modules.lottery.vo.PlayerOrderVo;

/**
 * 余额记录Controller
 * @author asd
 * @version 2018-01-29
 */
@Controller
@RequestMapping(value = "${adminPath}/lottery/pOrderReport")
public class POrderReportController extends BaseController {

	@Autowired
	private POrderService pOrderService;
	
	
	
	/**
	 * 页面
	 */
	@RequiresPermissions("lottery:pOrderReport:list")
	@RequestMapping(value = "toPage")
	public String toPage(OrderLotteryNumVo orderLotteryNumVo, HttpServletRequest request, HttpServletResponse response, Model model) {
		model.addAttribute("dataList", new ArrayList<OrderLotteryNumVo>());
		model.addAttribute("orderLotteryNumVo", orderLotteryNumVo);
		return "modules/report/pOrderLotteryNumReport";
	}
	
	@RequiresPermissions("lottery:pOrderReport:list")
	@RequestMapping(value = "list")
	public String list(OrderLotteryNumVo orderLotteryNumVo, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<OrderLotteryNumVo> dataList=pOrderService.findOrderSumMoneyGroupByLotteryNum(orderLotteryNumVo);
		model.addAttribute("dataList", dataList);
		model.addAttribute("orderLotteryNumVo", orderLotteryNumVo);
		model.addAttribute("sumData", sumList(dataList));
		return "modules/report/pOrderLotteryNumReport";
	}
	
	/**
	 * 导出excel文件
	 */
	@RequiresPermissions("lottery:pOrderReport:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(OrderLotteryNumVo orderLotteryNumVo, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "期号统计订单报表"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            List<OrderLotteryNumVo> dataList=pOrderService.findOrderSumMoneyGroupByLotteryNum(orderLotteryNumVo);
            if(dataList!=null && dataList.size()>0){
            	dataList.add(sumList(dataList));
            }
    		new ExportExcel("期号统计订单报表", OrderLotteryNumVo.class).setDataList(dataList).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出期号统计订单报表失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/lottery/pOrderReport/toPage";
    }
	
	/**
	 * 页面
	 */
	@RequiresPermissions("lottery:pOrderReport:listFP")
	@RequestMapping(value = "toPageFP")
	public String toPageFP(PlayerOrderVo playerOrderVo, HttpServletRequest request, HttpServletResponse response, Model model) {
		model.addAttribute("dataList", new ArrayList<PlayerOrderVo>());
		model.addAttribute("playerOrderVo", playerOrderVo);
		return "modules/report/pPlayerOrderReport";
	}
	
	@RequiresPermissions("lottery:pOrderReport:listFP")
	@RequestMapping(value = "listFP")
	public String listFP(PlayerOrderVo playerOrderVo, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<PlayerOrderVo> dataList=pOrderService.findOrderSumMoneyGroupByPlauer(playerOrderVo);
		model.addAttribute("dataList", dataList);
		model.addAttribute("orderLotteryNumVo", playerOrderVo);
		model.addAttribute("sumData", sumList(dataList));
		return "modules/report/pPlayerOrderReport";
	}
	
	/**
	 * 导出excel文件
	 */
	@RequiresPermissions("lottery:pOrderReport:exportFP")
    @RequestMapping(value = "exportFP", method=RequestMethod.POST)
    public String exportFileFP(PlayerOrderVo playerOrderVo, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "玩家订单统计报表"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            List<PlayerOrderVo> dataList=pOrderService.findOrderSumMoneyGroupByPlauer(playerOrderVo);
            if(dataList!=null && dataList.size()>0){
            	dataList.add(sumList(dataList));
            }
    		new ExportExcel("玩家订单统计报表", PlayerOrderVo.class).setDataList(dataList).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出玩家订单统计报表失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/lottery/pOrderReport/toPageFP";
    }
	
	/**
	 * 页面
	 */
	@RequiresPermissions("lottery:pOrderReport:listFD")
	@RequestMapping(value = "toPageFD")
	public String toPageFD(DayOrderVo dayOrderVo, HttpServletRequest request, HttpServletResponse response, Model model) {
		model.addAttribute("dataList", new ArrayList<PlayerOrderVo>());
		model.addAttribute("dayOrderVo", dayOrderVo);
		return "modules/report/pOrderDayReport";
	}
	
	@RequiresPermissions("lottery:pOrderReport:listFD")
	@RequestMapping(value = "listFD")
	public String listFD(DayOrderVo dayOrderVo, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<DayOrderVo> dataList=pOrderService.findOrderSumMoneyGroupByDay(dayOrderVo);
		model.addAttribute("dataList", dataList);
		model.addAttribute("dayOrderVo", dayOrderVo);
		model.addAttribute("sumData", sumList(dataList));
		return "modules/report/pOrderDayReport";
	}
	
	/**
	 * 导出excel文件
	 */
	@RequiresPermissions("lottery:pOrderReport:exportFD")
    @RequestMapping(value = "exportFD", method=RequestMethod.POST)
    public String exportFileFD(DayOrderVo dayOrderVo, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "订单每天统计报表"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            List<DayOrderVo> dataList=pOrderService.findOrderSumMoneyGroupByDay(dayOrderVo);
            if(dataList!=null && dataList.size()>0){
            	dataList.add(sumList(dataList));
            }
    		new ExportExcel("订单每天统计报表报表", DayOrderVo.class).setDataList(dataList).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出订单每天统计报表失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/lottery/pOrderReport/toPageFD";
    }
	
	private PlayerOrderVo sumList(List<PlayerOrderVo> dataList){
		PlayerOrderVo rdata=new PlayerOrderVo();
		BigDecimal totalBettingMoney=BigDecimal.ZERO;
		BigDecimal totalWinMoney=BigDecimal.ZERO;
		for(PlayerOrderVo data:dataList){
			if(data.getTotalBettingMoney()!=null){
				totalBettingMoney=totalBettingMoney.add(new BigDecimal(data.getTotalBettingMoney()));
			}
			if(data.getTotalWinMoney()!=null){
				totalWinMoney=totalWinMoney.add(new BigDecimal(data.getTotalWinMoney()));
			}
		}
		rdata.setPlayerName("合计：");
		rdata.setTotalBettingMoney(totalBettingMoney.doubleValue());
		rdata.setTotalWinMoney(totalWinMoney.doubleValue());
		return rdata;
	}
	
	private OrderLotteryNumVo sumList(List<OrderLotteryNumVo> dataList){
		OrderLotteryNumVo rdata=new OrderLotteryNumVo();
		BigDecimal totalBettingMoney=BigDecimal.ZERO;
		BigDecimal totalWinMoney=BigDecimal.ZERO;
		for(OrderLotteryNumVo data:dataList){
			if(data.getTotalBettingMoney()!=null){
				totalBettingMoney=totalBettingMoney.add(new BigDecimal(data.getTotalBettingMoney()));
			}
			if(data.getTotalWinMoney()!=null){
				totalWinMoney=totalWinMoney.add(new BigDecimal(data.getTotalWinMoney()));
			}
		}
		rdata.setLotteryNum("合计：");
		rdata.setTotalBettingMoney(totalBettingMoney.doubleValue());
		rdata.setTotalWinMoney(totalWinMoney.doubleValue());
		return rdata;
	}
	
	private DayOrderVo sumList(List<DayOrderVo> dataList){
		DayOrderVo rdata=new DayOrderVo();
		BigDecimal totalBettingMoney=BigDecimal.ZERO;
		BigDecimal totalWinMoney=BigDecimal.ZERO;
		for(DayOrderVo data:dataList){
			if(data.getTotalBettingMoney()!=null){
				totalBettingMoney=totalBettingMoney.add(new BigDecimal(data.getTotalBettingMoney()));
			}
			if(data.getTotalWinMoney()!=null){
				totalWinMoney=totalWinMoney.add(new BigDecimal(data.getTotalWinMoney()));
			}
		}
		rdata.setOrderDay("合计：");
		rdata.setTotalBettingMoney(totalBettingMoney.doubleValue());
		rdata.setTotalWinMoney(totalWinMoney.doubleValue());
		return rdata;
	}

	
	

}