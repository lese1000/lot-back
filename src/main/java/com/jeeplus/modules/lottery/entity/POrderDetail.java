/**
 * apple
 */
package com.jeeplus.modules.lottery.entity;

import javax.validation.constraints.NotNull;

import com.jeeplus.common.persistence.DataEntity;
import com.jeeplus.common.utils.excel.annotation.ExcelField;

/**
 * 订单明细Entity
 * @author asd
 * @version 2018-01-29
 */
public class POrderDetail extends DataEntity<POrderDetail> {
	
	private static final long serialVersionUID = 1L;
	private String bettingNum;		// 投注号码
	private Integer bettingCount;		// 投注注数
	private Double bettingMoney;		// 投注金额
	private Double winPrize;		// 中奖金额
	private Long orderId;		// player_id
	private Integer rate;
	
	public POrderDetail() {
		super();
	}

	public POrderDetail(String id){
		super(id);
	}

	
	@ExcelField(title="投注号码", align=2, sort=1)
	public String getBettingNum() {
		return bettingNum;
	}

	public void setBettingNum(String bettingNum) {
		this.bettingNum = bettingNum;
	}
	
	@ExcelField(title="投注注数", align=2, sort=2)
	public Integer getBettingCount() {
		return bettingCount;
	}

	public void setBettingCount(Integer bettingCount) {
		this.bettingCount = bettingCount;
	}
	
	@ExcelField(title="投注金额", align=2, sort=3)
	public Double getBettingMoney() {
		return bettingMoney;
	}

	public void setBettingMoney(Double bettingMoney) {
		this.bettingMoney = bettingMoney;
	}
	
	@ExcelField(title="中奖金额", align=2, sort=4)
	public Double getWinPrize() {
		return winPrize;
	}

	public void setWinPrize(Double winPrize) {
		this.winPrize = winPrize;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Integer getRate() {
		return rate;
	}

	public void setRate(Integer rate) {
		this.rate = rate;
	}
	
}