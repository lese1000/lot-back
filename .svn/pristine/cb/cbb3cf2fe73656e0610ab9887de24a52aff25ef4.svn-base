/**
 * apple
 */
package com.jeeplus.modules.lottery.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.common.persistence.Page;
import com.jeeplus.common.service.CrudService;
import com.jeeplus.modules.lottery.entity.PCard;
import com.jeeplus.modules.lottery.dao.PCardDao;

/**
 * CARTService
 * @author asd
 * @version 2018-01-29
 */
@Service
@Transactional(readOnly = true)
public class PCardService extends CrudService<PCardDao, PCard> {

	public PCard get(String id) {
		return super.get(id);
	}
	
	public List<PCard> findList(PCard pCard) {
		return super.findList(pCard);
	}
	
	public Page<PCard> findPage(Page<PCard> page, PCard pCard) {
		return super.findPage(page, pCard);
	}
	
	@Transactional(readOnly = false)
	public void save(PCard pCard) {
		super.save(pCard);
	}
	
	@Transactional(readOnly = false)
	public void delete(PCard pCard) {
		super.delete(pCard);
	}
	
	
	
	
}