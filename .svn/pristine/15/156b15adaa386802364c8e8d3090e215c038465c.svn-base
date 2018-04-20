/**
 * apple
 */
package com.jeeplus.modules.lottery.entity;

import javax.validation.constraints.NotNull;

import com.jeeplus.common.persistence.DataEntity;
import com.jeeplus.common.utils.excel.annotation.ExcelField;

/**
 * 余额Entity
 * @author asd
 * @version 2018-01-29
 */
public class PBalance extends DataEntity<PBalance> {
	
	private static final long serialVersionUID = 1L;
	private Long playerId;		// player_id
	private Double balanceVal;		// 余额
	
	public PBalance() {
		super();
	}

	public PBalance(String id){
		super(id);
	}

	
	@ExcelField(title="player_id", align=2, sort=1)
	public Long getPlayerId() {
		return playerId;
	}

	public void setPlayerId(Long playerId) {
		this.playerId = playerId;
	}
	
	@ExcelField(title="余额", align=2, sort=2)
	public Double getBalanceVal() {
		return balanceVal;
	}

	public void setBalanceVal(Double balanceVal) {
		this.balanceVal = balanceVal;
	}
	
}