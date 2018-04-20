/**
 * apple
 */
package com.jeeplus.modules.lottery.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.common.persistence.Page;
import com.jeeplus.common.service.CrudService;
import com.jeeplus.modules.lottery.entity.PBalance;
import com.jeeplus.modules.lottery.dao.PBalanceDao;

/**
 * 余额Service
 * @author asd
 * @version 2018-01-29
 */
@Service
@Transactional(readOnly = true)
public class PBalanceService extends CrudService<PBalanceDao, PBalance> {

	public PBalance get(String id) {
		return super.get(id);
	}
	
	public PBalance findByPlayerId(Long playerId) {
		return dao.findByPlayerId(playerId);
	}
	
	public List<PBalance> findList(PBalance pBalance) {
		return super.findList(pBalance);
	}
	
	public Page<PBalance> findPage(Page<PBalance> page, PBalance pBalance) {
		return super.findPage(page, pBalance);
	}
	
	@Transactional(readOnly = false)
	public void save(PBalance pBalance) {
		super.save(pBalance);
	}
	
	@Transactional(readOnly = false)
	public int saveR(PBalance pBalance) {
		return super.saveR(pBalance);
	}
	
	@Transactional(readOnly = false)
	public void delete(PBalance pBalance) {
		super.delete(pBalance);
	}
	
	
	
	
}