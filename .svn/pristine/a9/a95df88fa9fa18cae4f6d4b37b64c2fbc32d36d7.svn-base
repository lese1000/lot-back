/**
 * apple
 */
package com.jeeplus.modules.lottery.dao;

import java.util.List;

import com.jeeplus.common.persistence.CrudDao;
import com.jeeplus.common.persistence.annotation.MyBatisDao;
import com.jeeplus.modules.lottery.entity.LLotteryRule;
import com.jeeplus.modules.lottery.entity.LLotteryType;
import com.jeeplus.modules.lottery.entity.LRule;

/**
 * 积分DAO接口
 * @author asd
 * @version 2018-03-26
 */
@MyBatisDao
public interface LLotteryRuleDao extends CrudDao<LLotteryRule> {

	public List<LRule> findRuleList(Long lotteryTypeId);
}