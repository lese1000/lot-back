/**
 * apple
 */
package com.jeeplus.modules.lottery.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jeeplus.common.persistence.DataEntity;
import com.jeeplus.common.utils.excel.annotation.ExcelField;

/**
 * 订单Entity
 * @author asd
 * @version 2018-01-29
 */
public class POrder extends DataEntity<POrder> {
	
	private static final long serialVersionUID = 1L;
	private String orderNum;		// order_num
	private Long lotteryNumId;		// lottery_num_id
	private Long playerId;		// player_id
	private Integer isJoinBuy;		// 是否合买
	private Integer orderStatus;		// 订单状态:0,未开奖，1，已派奖
	private Date createTime;		// create_time
	private String playerName;
	private String lotteryType;
	private String lotteryNum;
	private Double totalWinMoney;
	private Double totalBettingMoney;
	private Date beginCreateDate;
	private Date endCreateDate;
	
	
	
	public Date getBeginCreateDate() {
		return beginCreateDate;
	}

	public void setBeginCreateDate(Date beginCreateDate) {
		this.beginCreateDate = beginCreateDate;
	}

	public Date getEndCreateDate() {
		return endCreateDate;
	}

	public void setEndCreateDate(Date endCreateDate) {
		this.endCreateDate = endCreateDate;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public String getLotteryType() {
		return lotteryType;
	}

	public void setLotteryType(String lotteryType) {
		this.lotteryType = lotteryType;
	}

	public String getLotteryNum() {
		return lotteryNum;
	}

	public void setLotteryNum(String lotteryNum) {
		this.lotteryNum = lotteryNum;
	}

	public Double getTotalWinMoney() {
		return totalWinMoney;
	}

	public void setTotalWinMoney(Double totalWinMoney) {
		this.totalWinMoney = totalWinMoney;
	}

	public Double getTotalBettingMoney() {
		return totalBettingMoney;
	}

	public void setTotalBettingMoney(Double totalBettingMoney) {
		this.totalBettingMoney = totalBettingMoney;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public POrder() {
		super();
	}

	public POrder(String id){
		super(id);
	}

	
	@ExcelField(title="order_num", align=2, sort=1)
	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	
	@ExcelField(title="lottery_num_id", align=2, sort=2)
	public Long getLotteryNumId() {
		return lotteryNumId;
	}

	public void setLotteryNumId(Long lotteryNumId) {
		this.lotteryNumId = lotteryNumId;
	}
	
	@ExcelField(title="player_id", align=2, sort=3)
	public Long getPlayerId() {
		return playerId;
	}

	public void setPlayerId(Long playerId) {
		this.playerId = playerId;
	}
	
	@ExcelField(title="是否合买", align=2, sort=4)
	public Integer getIsJoinBuy() {
		return isJoinBuy;
	}

	public void setIsJoinBuy(Integer isJoinBuy) {
		this.isJoinBuy = isJoinBuy;
	}
	
	@ExcelField(title="订单状态:0,未开奖，1，已派奖", align=2, sort=5)
	public Integer getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="create_time", align=2, sort=6)
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}