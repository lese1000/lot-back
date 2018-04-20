/**
 * apple
 */
package com.jeeplus.modules.lottery.entity;

import javax.validation.constraints.NotNull;

import java.util.Date;

import com.jeeplus.common.persistence.DataEntity;
import com.jeeplus.common.utils.excel.annotation.ExcelField;

/**
 * CashRecordEntity
 * @author asd
 * @version 2018-01-29
 */
public class SCashRecord extends DataEntity<SCashRecord> {
	
	private static final long serialVersionUID = 1L;
	private Long playerId;		// player_id
	private String fullName;		// full_name
	private String bank;		// bank
	private String account;		// account
	private Double cashVal;		// cash_val
	private Long updateUser;		// update_user
	private Integer status;		// 状态：1待审核，2，提现成功，3，提现失败
	private Date beginCreateDate;		// 开始 create_date
	private Date endCreateDate;		// 结束 create_date
	private String auditor;		// auditor
	private Date auditTime;		// auditTime
	private String auditOpinion;		// auditOpinion
	private String playerName;		// playerName
	
	public SCashRecord() {
		super();
	}

	public SCashRecord(String id){
		super(id);
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
	
	@ExcelField(title="bank", align=2, sort=3)
	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}
	
	@ExcelField(title="account", align=2, sort=4)
	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}
	
	@ExcelField(title="cash_val", align=2, sort=5)
	public Double getCashVal() {
		return cashVal;
	}

	public void setCashVal(Double cashVal) {
		this.cashVal = cashVal;
	}
	
	@ExcelField(title="update_user", align=2, sort=8)
	public Long getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(Long updateUser) {
		this.updateUser = updateUser;
	}
	
	@ExcelField(title="状态：1待审核，2，提现成功，3，提现失败", align=2, sort=9)
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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

	public String getAuditor() {
		return auditor;
	}

	public void setAuditor(String auditor) {
		this.auditor = auditor;
	}

	public Date getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}

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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
		
}