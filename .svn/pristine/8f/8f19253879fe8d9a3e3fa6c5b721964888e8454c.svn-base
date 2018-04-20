/**
 * apple
 */
package com.jeeplus.modules.lottery.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.common.persistence.Page;
import com.jeeplus.common.service.CrudService;
import com.jeeplus.modules.lottery.entity.PJoinBuyDetail;
import com.jeeplus.modules.lottery.dao.PJoinBuyDetailDao;

/**
 * JoinBuyDetailService
 * @author asd
 * @version 2018-01-29
 */
@Service
@Transactional(readOnly = true)
public class PJoinBuyDetailService extends CrudService<PJoinBuyDetailDao, PJoinBuyDetail> {

	public PJoinBuyDetail get(String id) {
		return super.get(id);
	}
	
	public List<PJoinBuyDetail> findList(PJoinBuyDetail pJoinBuyDetail) {
		return super.findList(pJoinBuyDetail);
	}
	
	public Page<PJoinBuyDetail> findPage(Page<PJoinBuyDetail> page, PJoinBuyDetail pJoinBuyDetail) {
		return super.findPage(page, pJoinBuyDetail);
	}
	
	@Transactional(readOnly = false)
	public void save(PJoinBuyDetail pJoinBuyDetail) {
		super.save(pJoinBuyDetail);
	}
	
	@Transactional(readOnly = false)
	public void delete(PJoinBuyDetail pJoinBuyDetail) {
		super.delete(pJoinBuyDetail);
	}
	
	
	
	
}