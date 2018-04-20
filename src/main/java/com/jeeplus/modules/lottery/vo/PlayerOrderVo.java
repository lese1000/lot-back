package com.jeeplus.modules.lottery.vo;

import java.util.Date;

import com.jeeplus.common.utils.excel.annotation.ExcelField;

public class PlayerOrderVo {
	private Long playerId;
	@ExcelField(title="玩家名称", align=0, sort=1)
	private String playerName;
	@ExcelField(title="总下注金额", align=0, sort=2)
	private Double totalBettingMoney;
	@ExcelField(title="总中奖金额", align=0, sort=3)
	private Double totalWinMoney;
	private Date createDateStart;
	private Date createDateEnd;
	private String lotteryNum;
	
	public Long getPlayerId() {
		return playerId;
	}
	public void setPlayerId(Long playerId) {
		this.playerId = playerId;
	}
	public String getPlayerName() {
		return playerName;
	}
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
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
	public String getLotteryNum() {
		return lotteryNum;
	}
	public void setLotteryNum(String lotteryNum) {
		this.lotteryNum = lotteryNum;
	}
	
	
	
}
