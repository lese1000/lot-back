/**
 * apple
 */
package com.jeeplus.modules.lottery.entity;

import javax.validation.constraints.NotNull;

import com.jeeplus.common.persistence.DataEntity;
import com.jeeplus.common.utils.excel.annotation.ExcelField;

/**
 * 登录记录Entity
 * @author asd
 * @version 2018-01-29
 */
public class PLoginRecord extends DataEntity<PLoginRecord> {
	
	private static final long serialVersionUID = 1L;
	private Long playerId;		// player_id
	private String ipAddr;		// ip_addr
	private String playerName;
	
	
	
	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public PLoginRecord() {
		super();
	}

	public PLoginRecord(String id){
		super(id);
	}

	
	@ExcelField(title="player_id", align=2, sort=1)
	public Long getPlayerId() {
		return playerId;
	}

	public void setPlayerId(Long playerId) {
		this.playerId = playerId;
	}
	
	@ExcelField(title="ip_addr", align=2, sort=2)
	public String getIpAddr() {
		return ipAddr;
	}

	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}
	
}