/**
 * apple
 */
package com.jeeplus.modules.lottery.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.common.persistence.Page;
import com.jeeplus.common.service.CrudService;
import com.jeeplus.modules.lottery.entity.PLoginRecord;
import com.jeeplus.modules.lottery.dao.PLoginRecordDao;

/**
 * 登录记录Service
 * @author asd
 * @version 2018-01-29
 */
@Service
@Transactional(readOnly = true)
public class PLoginRecordService extends CrudService<PLoginRecordDao, PLoginRecord> {

	public PLoginRecord get(String id) {
		return super.get(id);
	}
	
	public List<PLoginRecord> findList(PLoginRecord pLoginRecord) {
		return super.findList(pLoginRecord);
	}
	
	public Page<PLoginRecord> findPage(Page<PLoginRecord> page, PLoginRecord pLoginRecord) {
		return super.findPage(page, pLoginRecord);
	}
	
	@Transactional(readOnly = false)
	public void save(PLoginRecord pLoginRecord) {
		super.save(pLoginRecord);
	}
	
	@Transactional(readOnly = false)
	public void delete(PLoginRecord pLoginRecord) {
		super.delete(pLoginRecord);
	}
	
	
	
	
}