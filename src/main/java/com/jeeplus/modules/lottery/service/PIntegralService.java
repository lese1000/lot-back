/**
 * apple
 */
package com.jeeplus.modules.lottery.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.common.persistence.Page;
import com.jeeplus.common.service.CrudService;
import com.jeeplus.modules.lottery.entity.PIntegral;
import com.jeeplus.modules.lottery.dao.PIntegralDao;

/**
 * 积分Service
 * @author asd
 * @version 2018-01-29
 */
@Service
@Transactional(readOnly = true)
public class PIntegralService extends CrudService<PIntegralDao, PIntegral> {

	public PIntegral get(String id) {
		return super.get(id);
	}
	
	public List<PIntegral> findList(PIntegral pIntegral) {
		return super.findList(pIntegral);
	}
	
	public Page<PIntegral> findPage(Page<PIntegral> page, PIntegral pIntegral) {
		return super.findPage(page, pIntegral);
	}
	
	@Transactional(readOnly = false)
	public void save(PIntegral pIntegral) {
		super.save(pIntegral);
	}
	
	@Transactional(readOnly = false)
	public void delete(PIntegral pIntegral) {
		super.delete(pIntegral);
	}
	
	
	
	
}