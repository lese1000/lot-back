package com.jeeplus.modules.lottery.vo;

import java.util.Date;

import com.jeeplus.common.utils.excel.annotation.ExcelField;

public class PlayerBalanceVo {
	private Long playerId;
	@ExcelField(title="玩家名称", align=0, sort=1)
	private String playerName;
	@ExcelField(title="充值金额", align=0, sort=2)
	private Double bt1;
	@ExcelField(title="提现金额", align=0, sort=3)
	private Double bt2;
	@ExcelField(title="消费金额", align=0, sort=4)
	private Double bt3;
	@ExcelField(title="中奖金额", align=0, sort=5)
	private Double bt4;
	@ExcelField(title="活动奖励金额", align=0, sort=6)
	private Double bt5;
	@ExcelField(title="积分兑换金额", align=0, sort=7)
	private Double bt6;
	private Date createDateStart;
	private Date createDateEnd;
	
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
	public Double getBt1() {
		return bt1;
	}
	public void setBt1(Double bt1) {
		this.bt1 = bt1;
	}
	public Double getBt2() {
		return bt2;
	}
	public void setBt2(Double bt2) {
		this.bt2 = bt2;
	}
	public Double getBt3() {
		return bt3;
	}
	public void setBt3(Double bt3) {
		this.bt3 = bt3;
	}
	public Double getBt4() {
		return bt4;
	}
	public void setBt4(Double bt4) {
		this.bt4 = bt4;
	}
	public Double getBt5() {
		return bt5;
	}
	public void setBt5(Double bt5) {
		this.bt5 = bt5;
	}
	public Double getBt6() {
		return bt6;
	}
	public void setBt6(Double bt6) {
		this.bt6 = bt6;
	}
	
}
