/**
 * apple
 */
package com.jeeplus.modules.sys.entity;

import javax.validation.constraints.NotNull;

import com.jeeplus.common.persistence.DataEntity;
import com.jeeplus.common.utils.excel.annotation.ExcelField;

/**
 * 玩家账号Entity
 * @author asd
 * @version 2018-01-24
 */
public class PPlayer extends DataEntity<PPlayer> {
	
	private static final long serialVersionUID = 1L;
	private String playerName;		// 名字
	private String password;		// 密码
	private String payPassword;		// 支付密码
	private Integer playerType;		// 玩家类型：1，普通玩家，2，推广员
	private String createUser;		// 创建者
	private String updateUser;		// 更新者
	private Long parent;		// 上家
	private String parentName;		// 上家名字
	private Double balanceVal;		//余额
	
	
	
	public Double getBalanceVal() {
		return balanceVal;
	}

	public void setBalanceVal(Double balanceVal) {
		this.balanceVal = balanceVal;
	}

	public PPlayer() {
		super();
	}

	public PPlayer(String id){
		super(id);
	}
	
	
	@ExcelField(title="上家名字", align=2, sort=10)
	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	@ExcelField(title="名字", align=2, sort=1)
	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	
	@ExcelField(title="密码", align=2, sort=2)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@ExcelField(title="支付密码", align=2, sort=3)
	public String getPayPassword() {
		return payPassword;
	}

	public void setPayPassword(String payPassword) {
		this.payPassword = payPassword;
	}
	
	@ExcelField(title="玩家类型：1，普通玩家，2，推广员", dictType="", align=2, sort=4)
	public Integer getPlayerType() {
		return playerType;
	}

	public void setPlayerType(Integer playerType) {
		this.playerType = playerType;
	}
	
	@ExcelField(title="创建者", align=2, sort=6)
	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	
	@ExcelField(title="更新者", align=2, sort=8)
	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	
	@ExcelField(title="上家", dictType="", align=2, sort=9)
	public Long getParent() {
		return parent;
	}

	public void setParent(Long parent) {
		this.parent = parent;
	}
	
}