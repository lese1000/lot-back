/**
 * apple
 */
package com.jeeplus.modules.lottery.entity;

import javax.validation.constraints.NotNull;

import com.jeeplus.common.persistence.DataEntity;
import com.jeeplus.common.utils.excel.annotation.ExcelField;

/**
 * 积分Entity
 * @author asd
 * @version 2018-01-29
 */
public class PIntegral extends DataEntity<PIntegral> {
	
	private static final long serialVersionUID = 1L;
	private Double integralVal;		// 积分值
	private Long playerId;		// player_id
	
	public PIntegral() {
		super();
	}

	public PIntegral(String id){
		super(id);
	}

	
	@ExcelField(title="积分值", align=2, sort=1)
	public Double getIntegralVal() {
		return integralVal;
	}

	public void setIntegralVal(Double integralVal) {
		this.integralVal = integralVal;
	}
	
	@ExcelField(title="player_id", align=2, sort=4)
	public Long getPlayerId() {
		return playerId;
	}

	public void setPlayerId(Long playerId) {
		this.playerId = playerId;
	}
	
}