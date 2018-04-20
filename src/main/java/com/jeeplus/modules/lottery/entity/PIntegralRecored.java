/**
 * apple
 */
package com.jeeplus.modules.lottery.entity;

import javax.validation.constraints.NotNull;

import com.jeeplus.common.persistence.DataEntity;
import com.jeeplus.common.utils.excel.annotation.ExcelField;

/**
 * 积分记录Entity
 * @author asd
 * @version 2018-01-29
 */
public class PIntegralRecored extends DataEntity<PIntegralRecored> {
	
	private static final long serialVersionUID = 1L;
	private Integer integralType;		// 1.充值奖励，2，积分兑换
	private Long playerId;		// player_id
	
	public PIntegralRecored() {
		super();
	}

	public PIntegralRecored(String id){
		super(id);
	}

	
	@ExcelField(title="1.充值奖励，2，积分兑换", dictType="", align=2, sort=1)
	public Integer getIntegralType() {
		return integralType;
	}

	public void setIntegralType(Integer integralType) {
		this.integralType = integralType;
	}
	
	@ExcelField(title="player_id", align=2, sort=2)
	public Long getPlayerId() {
		return playerId;
	}

	public void setPlayerId(Long playerId) {
		this.playerId = playerId;
	}
	
}