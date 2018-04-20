/**
 * apple
 */
package com.jeeplus.modules.lottery.entity;

import javax.validation.constraints.NotNull;

import com.jeeplus.common.persistence.DataEntity;
import com.jeeplus.common.utils.excel.annotation.ExcelField;

/**
 * 彩票种类Entity
 * @author asd
 * @version 2018-01-29
 */
public class LLotteryRule extends DataEntity<LLotteryRule> {
	
	private static final long serialVersionUID = 1L;
	private Long ruleId;		// rule_id
	private Long lotteryTypeId;		// lotteryTypeId
	private Integer status;
	private String lotteryTypeName;
	private String ruleName;
	private Double winPrize;
	private Double plusAwards;
	private Integer playType;
	private Integer selectNumber; 
	private String remark;
	
	public Double getPlusAwards() {
		return plusAwards;
	}

	public void setPlusAwards(Double plusAwards) {
		this.plusAwards = plusAwards;
	}

	public String getLotteryTypeName() {
		return lotteryTypeName;
	}

	public void setLotteryTypeName(String lotteryTypeName) {
		this.lotteryTypeName = lotteryTypeName;
	}

	public String getRuleName() {
		return ruleName;
	}

	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}

	public Double getWinPrize() {
		return winPrize;
	}

	public void setWinPrize(Double winPrize) {
		this.winPrize = winPrize;
	}

	public Integer getPlayType() {
		return playType;
	}

	public void setPlayType(Integer playType) {
		this.playType = playType;
	}

	public Integer getSelectNumber() {
		return selectNumber;
	}

	public void setSelectNumber(Integer selectNumber) {
		this.selectNumber = selectNumber;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Long getRuleId() {
		return ruleId;
	}

	public void setRuleId(Long ruleId) {
		this.ruleId = ruleId;
	}

	public Long getLotteryTypeId() {
		return lotteryTypeId;
	}

	public void setLotteryTypeId(Long lotteryTypeId) {
		this.lotteryTypeId = lotteryTypeId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public LLotteryRule() {
		super();
	}

	public LLotteryRule(String id){
		super(id);
	}

	
	
}