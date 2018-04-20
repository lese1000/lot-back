/**
 * apple
 */
package com.jeeplus.modules.lottery.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.common.persistence.Page;
import com.jeeplus.common.service.CrudService;
import com.jeeplus.modules.lottery.dao.LLotteryRuleDao;
import com.jeeplus.modules.lottery.entity.LLotteryRule;
import com.jeeplus.modules.lottery.entity.LLotteryRule;
import com.jeeplus.modules.lottery.entity.LRule;

/**
 * 彩票种类Service
 * @author asd
 * @version 2018-01-29
 */
@Service
@Transactional(readOnly = true)
public class LLotteryRuleService extends CrudService<LLotteryRuleDao, LLotteryRule> {

	public LLotteryRule get(String id) {
		return super.get(id);
	}
	
	public List<LLotteryRule> findList(LLotteryRule LLotteryRule) {
		return super.findList(LLotteryRule);
	}
	
	public List<LRule> findRuleList(Long lotteryTypeId) {
		return dao.findRuleList(lotteryTypeId);
	}
	
	public Page<LLotteryRule> findPage(Page<LLotteryRule> page, LLotteryRule LLotteryRule) {
		return super.findPage(page, LLotteryRule);
	}
	
	@Transactional(readOnly = false)
	public void save(LLotteryRule LLotteryRule) {
		super.save(LLotteryRule);
	}
	
	@Transactional(readOnly = false)
	public void delete(LLotteryRule LLotteryRule) {
		super.delete(LLotteryRule);
	}
	
	@Transactional(readOnly = false)
	public void deleteByLogic(LLotteryRule LLotteryRule) {
		super.deleteByLogic(LLotteryRule);
	}
	
	
	
	
}