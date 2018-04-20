/**
 * apple
 */
package com.jeeplus.modules.lottery.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.common.persistence.Page;
import com.jeeplus.common.service.CrudService;
import com.jeeplus.modules.lottery.entity.POrderDetail;
import com.jeeplus.modules.lottery.dao.POrderDetailDao;

/**
 * 订单明细Service
 * @author asd
 * @version 2018-01-29
 */
@Service
@Transactional(readOnly = true)
public class POrderDetailService extends CrudService<POrderDetailDao, POrderDetail> {

	public POrderDetail get(String id) {
		return super.get(id);
	}
	
	public List<POrderDetail> findList(POrderDetail pOrderDetail) {
		return super.findList(pOrderDetail);
	}
	
	public Page<POrderDetail> findPage(Page<POrderDetail> page, POrderDetail pOrderDetail) {
		return super.findPage(page, pOrderDetail);
	}
	
	@Transactional(readOnly = false)
	public void save(POrderDetail pOrderDetail) {
		super.save(pOrderDetail);
	}
	
	@Transactional(readOnly = false)
	public void delete(POrderDetail pOrderDetail) {
		super.delete(pOrderDetail);
	}
	
	
	
	
}