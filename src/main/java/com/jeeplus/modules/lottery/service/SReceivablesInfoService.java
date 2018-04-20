/**
 * apple
 */
package com.jeeplus.modules.lottery.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.common.persistence.Page;
import com.jeeplus.common.service.CrudService;
import com.jeeplus.modules.lottery.entity.SReceivablesInfo;
import com.jeeplus.modules.lottery.dao.SReceivablesInfoDao;

/**
 * ReceivablesInfoService
 * @author asd
 * @version 2018-01-29
 */
@Service
@Transactional(readOnly = true)
public class SReceivablesInfoService extends CrudService<SReceivablesInfoDao, SReceivablesInfo> {

	public SReceivablesInfo get(String id) {
		return super.get(id);
	}
	
	public List<SReceivablesInfo> findList(SReceivablesInfo sReceivablesInfo) {
		return super.findList(sReceivablesInfo);
	}
	
	public Page<SReceivablesInfo> findPage(Page<SReceivablesInfo> page, SReceivablesInfo sReceivablesInfo) {
		return super.findPage(page, sReceivablesInfo);
	}
	
	@Transactional(readOnly = false)
	public void save(SReceivablesInfo sReceivablesInfo) {
		super.save(sReceivablesInfo);
	}
	
	@Transactional(readOnly = false)
	public void delete(SReceivablesInfo sReceivablesInfo) {
		super.delete(sReceivablesInfo);
	}
	
	
	
	
}