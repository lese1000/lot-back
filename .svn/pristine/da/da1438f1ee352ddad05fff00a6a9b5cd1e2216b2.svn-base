/**
 * apple
 */
package com.jeeplus.modules.lottery.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.common.persistence.Page;
import com.jeeplus.common.service.CrudService;
import com.jeeplus.modules.lottery.entity.PIntegralRecored;
import com.jeeplus.modules.lottery.dao.PIntegralRecoredDao;

/**
 * 积分记录Service
 * @author asd
 * @version 2018-01-29
 */
@Service
@Transactional(readOnly = true)
public class PIntegralRecoredService extends CrudService<PIntegralRecoredDao, PIntegralRecored> {

	public PIntegralRecored get(String id) {
		return super.get(id);
	}
	
	public List<PIntegralRecored> findList(PIntegralRecored pIntegralRecored) {
		return super.findList(pIntegralRecored);
	}
	
	public Page<PIntegralRecored> findPage(Page<PIntegralRecored> page, PIntegralRecored pIntegralRecored) {
		return super.findPage(page, pIntegralRecored);
	}
	
	@Transactional(readOnly = false)
	public void save(PIntegralRecored pIntegralRecored) {
		super.save(pIntegralRecored);
	}
	
	@Transactional(readOnly = false)
	public void delete(PIntegralRecored pIntegralRecored) {
		super.delete(pIntegralRecored);
	}
	
	
	
	
}