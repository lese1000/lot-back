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
public class LLotteryType extends DataEntity<LLotteryType> {
	
	private static final long serialVersionUID = 1L;
	private Long ruleId;		// rule_id
	private String lotteryTypeName;		// 彩票种类名称
	private Double singlePrice;
	
	
	
	public Double getSinglePrice() {
		return singlePrice;
	}

	public void setSinglePrice(Double singlePrice) {
		this.singlePrice = singlePrice;
	}

	public LLotteryType() {
		super();
	}

	public LLotteryType(String id){
		super(id);
	}

	
	@ExcelField(title="rule_id", dictType="", align=2, sort=1)
	public Long getRuleId() {
		return ruleId;
	}

	public void setRuleId(Long ruleId) {
		this.ruleId = ruleId;
	}
	
	@ExcelField(title="彩票种类名称", align=2, sort=2)
	public String getLotteryTypeName() {
		return lotteryTypeName;
	}

	public void setLotteryTypeName(String lotteryTypeName) {
		this.lotteryTypeName = lotteryTypeName;
	}
	
}