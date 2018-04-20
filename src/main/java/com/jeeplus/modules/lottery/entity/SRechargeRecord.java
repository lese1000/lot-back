/**
 * apple
 */
package com.jeeplus.modules.lottery.entity;

import java.util.Date;

import com.jeeplus.common.persistence.DataEntity;
import com.jeeplus.common.utils.excel.annotation.ExcelField;

/**
 * RechargeRecordEntity
 * @author asd
 * @version 2018-01-29
 */
public class SRechargeRecord extends DataEntity<SRechargeRecord> {
	
	private static final long serialVersionUID = 1L;
	private Long playerId;		// player_id
	private String fullName;		// full_name
	private String payAccount;		// pay_account
	private String payTypeId;		// pay_type_id
	private Double rechargeVal;		// recharge_val
	private Double actualAccount;		// recharge_val
	private Integer status;		// 状态
	private String auditor;		// auditor
	private Date auditTime;		// auditTime
	private String auditOpinion;		// auditOpinion
	private String playerName;		// playerName
	private Date beginCreateDate;		// 开始 create_date
	private Date endCreateDate;		// 结束 create_date
	
	public SRechargeRecord() {
		super();
	}

	public SRechargeRecord(String id){
		super(id);
	}
	
	

	
	

	public Double getActualAccount() {
		return actualAccount;
	}

	public void setActualAccount(Double actualAccount) {
		this.actualAccount = actualAccount;
	}

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

	@ExcelField(title="player_id", align=2, sort=1)
	public Long getPlayerId() {
		return playerId;
	}

	public void setPlayerId(Long playerId) {
		this.playerId = playerId;
	}
	
	@ExcelField(title="full_name", align=2, sort=2)
	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	@ExcelField(title="pay_account", align=2, sort=3)
	public String getPayAccount() {
		return payAccount;
	}

	public void setPayAccount(String payAccount) {
		this.payAccount = payAccount;
	}
	
	@ExcelField(title="pay_type_id", align=2, sort=4)
	public String getPayTypeId() {
		return payTypeId;
	}

	public void setPayTypeId(String payTypeId) {
		this.payTypeId = payTypeId;
	}
	
	@ExcelField(title="recharge_val", align=2, sort=5)
	public Double getRechargeVal() {
		return rechargeVal;
	}

	public void setRechargeVal(Double rechargeVal) {
		this.rechargeVal = rechargeVal;
	}
	
	@ExcelField(title="状态", dictType="", align=2, sort=8)
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	@ExcelField(title="auditor", align=2, sort=9)
	public String getAuditor() {
		return auditor;
	}

	public void setAuditor(String auditor) {
		this.auditor = auditor;
	}
	@ExcelField(title="auditTime", align=2, sort=10)
	public Date getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}
	@ExcelField(title="auditOpinion", align=2, sort=11)
	public String getAuditOpinion() {
		return auditOpinion;
	}

	public void setAuditOpinion(String auditOpinion) {
		this.auditOpinion = auditOpinion;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	
	
	
}