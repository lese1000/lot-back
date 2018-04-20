/**
 * apple
 */
package com.jeeplus.modules.lottery.entity;

import java.util.Date;

import javax.validation.constraints.NotNull;

import com.jeeplus.common.persistence.DataEntity;
import com.jeeplus.common.utils.excel.annotation.ExcelField;

/**
 * JoinBuyEntity
 * @author asd
 * @version 2018-01-29
 */
public class PJoinBuy extends DataEntity<PJoinBuy> {
	
	private static final long serialVersionUID = 1L;
	private Long orderId;		// order_id
	private Double singlePieceMoney;		// single_piece_money
	private Integer totalPieceNum;		// total_piece_num
	private Integer remainingPieceNum;		// 剩余份数
	private Integer planType;		// 方案类型：1，发布即公开，2，截止后公开，3完全保密
	private Integer leastNum;		// 保底份数
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

	public PJoinBuy() {
		super();
	}

	public PJoinBuy(String id){
		super(id);
	}

	
	@ExcelField(title="order_id", align=2, sort=1)
	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	
	@ExcelField(title="single_piece_money", align=2, sort=2)
	public Double getSinglePieceMoney() {
		return singlePieceMoney;
	}

	public void setSinglePieceMoney(Double singlePieceMoney) {
		this.singlePieceMoney = singlePieceMoney;
	}
	
	@ExcelField(title="total_piece_num", align=2, sort=3)
	public Integer getTotalPieceNum() {
		return totalPieceNum;
	}

	public void setTotalPieceNum(Integer totalPieceNum) {
		this.totalPieceNum = totalPieceNum;
	}
	
	@ExcelField(title="剩余份数", align=2, sort=4)
	public Integer getRemainingPieceNum() {
		return remainingPieceNum;
	}

	public void setRemainingPieceNum(Integer remainingPieceNum) {
		this.remainingPieceNum = remainingPieceNum;
	}
	
	@ExcelField(title="方案类型：1，发布即公开，2，截止后公开，3完全保密", dictType="", align=2, sort=5)
	public Integer getPlanType() {
		return planType;
	}

	public void setPlanType(Integer planType) {
		this.planType = planType;
	}
	
	@ExcelField(title="保底份数", align=2, sort=6)
	public Integer getLeastNum() {
		return leastNum;
	}

	public void setLeastNum(Integer leastNum) {
		this.leastNum = leastNum;
	}
	
}