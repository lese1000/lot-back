/**
 * apple
 */
package com.jeeplus.modules.lottery.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.common.persistence.Page;
import com.jeeplus.common.service.CrudService;
import com.jeeplus.modules.lottery.entity.LLotteryNum;
import com.jeeplus.modules.lottery.dao.LLotteryNumDao;

/**
 * 彩票期号Service
 * @author asd
 * @version 2018-01-29
 */
@Service
@Transactional(readOnly = true)
public class LLotteryNumService extends CrudService<LLotteryNumDao, LLotteryNum> {

	public LLotteryNum get(String id) {
		return super.get(id);
	}
	
	public List<LLotteryNum> findList(LLotteryNum lLotteryNum) {
		return super.findList(lLotteryNum);
	}
	
	public Page<LLotteryNum> findPage(Page<LLotteryNum> page, LLotteryNum lLotteryNum) {
		return super.findPage(page, lLotteryNum);
	}
	
	@Transactional(readOnly = false)
	public void save(LLotteryNum lLotteryNum) {
		super.save(lLotteryNum);
	}
	
	@Transactional(readOnly = false)
	public void delete(LLotteryNum lLotteryNum) {
		super.delete(lLotteryNum);
	}
	
	
	
	
}