/**
 * apple
 */
package com.jeeplus.modules.lottery.entity;

import javax.validation.constraints.NotNull;

import com.jeeplus.common.persistence.DataEntity;
import com.jeeplus.common.utils.excel.annotation.ExcelField;

/**
 * JoinBuyDetailEntity
 * @author asd
 * @version 2018-01-29
 */
public class PJoinBuyDetail extends DataEntity<PJoinBuyDetail> {
	
	private static final long serialVersionUID = 1L;
	private Long joinBuyId;		// join_buy_id
	private Long playerId;		// player_id
	private Long orderId;		// orderId
	private String playerName;		// playerName
	private Integer buyCount;		// 购买份数
	private Double buyMoney;		// 购买金额
	private Double winPrize;		// 中奖金额
	
	public PJoinBuyDetail() {
		super();
	}

	public PJoinBuyDetail(String id){
		super(id);
	}

	
	
	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	@ExcelField(title="join_buy_id", align=2, sort=1)
	public Long getJoinBuyId() {
		return joinBuyId;
	}

	public void setJoinBuyId(Long joinBuyId) {
		this.joinBuyId = joinBuyId;
	}
	
	@ExcelField(title="player_id", align=2, sort=2)
	public Long getPlayerId() {
		return playerId;
	}

	public void setPlayerId(Long playerId) {
		this.playerId = playerId;
	}
	
	@ExcelField(title="购买份数", align=2, sort=3)
	public Integer getBuyCount() {
		return buyCount;
	}

	public void setBuyCount(Integer buyCount) {
		this.buyCount = buyCount;
	}
	
	@ExcelField(title="购买金额", align=2, sort=4)
	public Double getBuyMoney() {
		return buyMoney;
	}

	public void setBuyMoney(Double buyMoney) {
		this.buyMoney = buyMoney;
	}
	
	@ExcelField(title="中奖金额", align=2, sort=5)
	public Double getWinPrize() {
		return winPrize;
	}

	public void setWinPrize(Double winPrize) {
		this.winPrize = winPrize;
	}
	
}