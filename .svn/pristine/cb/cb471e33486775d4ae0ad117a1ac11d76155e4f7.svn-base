/**
 * apple
 */
package com.jeeplus.modules.lottery.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.common.persistence.Page;
import com.jeeplus.common.service.CrudService;
import com.jeeplus.modules.lottery.entity.PJoinBuy;
import com.jeeplus.modules.lottery.dao.PJoinBuyDao;

/**
 * JoinBuyService
 * @author asd
 * @version 2018-01-29
 */
@Service
@Transactional(readOnly = true)
public class PJoinBuyService extends CrudService<PJoinBuyDao, PJoinBuy> {

	public PJoinBuy get(String id) {
		return super.get(id);
	}
	
	public List<PJoinBuy> findList(PJoinBuy pJoinBuy) {
		return super.findList(pJoinBuy);
	}
	
	public Page<PJoinBuy> findPage(Page<PJoinBuy> page, PJoinBuy pJoinBuy) {
		return super.findPage(page, pJoinBuy);
	}
	
	@Transactional(readOnly = false)
	public void save(PJoinBuy pJoinBuy) {
		super.save(pJoinBuy);
	}
	
	@Transactional(readOnly = false)
	public void delete(PJoinBuy pJoinBuy) {
		super.delete(pJoinBuy);
	}
	
	
	
	
}