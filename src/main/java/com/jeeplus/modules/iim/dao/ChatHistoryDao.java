/**
 * apple
 */
package com.jeeplus.modules.iim.dao;

import java.util.List;

import com.jeeplus.common.persistence.CrudDao;
import com.jeeplus.common.persistence.annotation.MyBatisDao;
import com.jeeplus.modules.iim.entity.ChatHistory;

/**
 * 聊天记录DAO接口
 * @author jeeplus
 * @version 2015-12-29
 */
@MyBatisDao
public interface ChatHistoryDao extends CrudDao<ChatHistory> {
	
	
	/**
	 * 查询列表数据
	 * @param entity
	 * @return
	 */
	public List<ChatHistory> findLogList(ChatHistory entity);
	

	/**
	 * 查询群组列表数据
	 * @param entity
	 * @return
	 */
	public List<ChatHistory> findGroupLogList(ChatHistory entity);
	
	public int findUnReadCount(ChatHistory chatHistory);
	
}