package com.jeeplus.modules.lottery.vo;

import java.util.Date;

import com.jeeplus.common.utils.excel.annotation.ExcelField;

public class DayOrderVo {
	@ExcelField(title="下单时间", align=0, sort=1)
	private String orderDay;
	@ExcelField(title="总下注金额", align=0, sort=2)
	private Double totalBettingMoney;
	@ExcelField(title="总中奖金额", align=0, sort=3)
	private Double totalWinMoney;
	private Date createDateStart;
	private Date createDateEnd;
	
	public String getOrderDay() {
		return orderDay;
	}
	public void setOrderDay(String orderDay) {
		this.orderDay = orderDay;
	}
	public Double getTotalBettingMoney() {
		return totalBettingMoney;
	}
	public void setTotalBettingMoney(Double totalBettingMoney) {
		this.totalBettingMoney = totalBettingMoney;
	}
	public Double getTotalWinMoney() {
		return totalWinMoney;
	}
	public void setTotalWinMoney(Double totalWinMoney) {
		this.totalWinMoney = totalWinMoney;
	}
	public Date getCreateDateStart() {
		return createDateStart;
	}
	public void setCreateDateStart(Date createDateStart) {
		this.createDateStart = createDateStart;
	}
	public Date getCreateDateEnd() {
		return createDateEnd;
	}
	public void setCreateDateEnd(Date createDateEnd) {
		this.createDateEnd = createDateEnd;
	}
	
	
	
	
	
}
