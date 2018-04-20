/**
 * apple
 */
package com.jeeplus.modules.lottery.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.common.persistence.Page;
import com.jeeplus.common.service.CrudService;
import com.jeeplus.modules.lottery.entity.LLotteryType;
import com.jeeplus.modules.lottery.dao.LLotteryTypeDao;

/**
 * 彩票种类Service
 * @author asd
 * @version 2018-01-29
 */
@Service
@Transactional(readOnly = true)
public class LLotteryTypeService extends CrudService<LLotteryTypeDao, LLotteryType> {

	public LLotteryType get(String id) {
		return super.get(id);
	}
	
	public List<LLotteryType> findList(LLotteryType lLotteryType) {
		return super.findList(lLotteryType);
	}
	
	public List<LLotteryType> findSelectList() {
		return dao.findSelectList();
	}
	
	public Page<LLotteryType> findPage(Page<LLotteryType> page, LLotteryType lLotteryType) {
		return super.findPage(page, lLotteryType);
	}
	
	@Transactional(readOnly = false)
	public void save(LLotteryType lLotteryType) {
		super.save(lLotteryType);
	}
	
	@Transactional(readOnly = false)
	public void delete(LLotteryType lLotteryType) {
		super.delete(lLotteryType);
	}
	
	@Transactional(readOnly = false)
	public void deleteByLogic(LLotteryType lLotteryType) {
		super.deleteByLogic(lLotteryType);
	}
	
	
	
	
}