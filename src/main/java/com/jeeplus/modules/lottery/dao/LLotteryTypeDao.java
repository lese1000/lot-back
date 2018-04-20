/**
 * apple
 */
package com.jeeplus.modules.lottery.dao;

import java.util.List;

import com.jeeplus.common.persistence.CrudDao;
import com.jeeplus.common.persistence.annotation.MyBatisDao;
import com.jeeplus.modules.lottery.entity.LLotteryType;

/**
 * 彩票种类DAO接口
 * @author asd
 * @version 2018-01-29
 */
@MyBatisDao
public interface LLotteryTypeDao extends CrudDao<LLotteryType> {

	
	public List<LLotteryType> findSelectList();
}