/**
 * apple
 */
package com.jeeplus.modules.lottery.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.common.config.Global;
import com.jeeplus.common.persistence.Page;
import com.jeeplus.common.service.CrudService;
import com.jeeplus.modules.lottery.entity.PBalance;
import com.jeeplus.modules.lottery.entity.PBalanceRecord;
import com.jeeplus.modules.lottery.entity.SRechargeRecord;
import com.jeeplus.modules.lottery.dao.PBalanceDao;
import com.jeeplus.modules.lottery.dao.PBalanceRecordDao;
import com.jeeplus.modules.lottery.dao.SRechargeRecordDao;
import com.jeeplus.modules.sys.utils.UserUtils;

/**
 * RechargeRecordService
 * @author asd
 * @version 2018-01-29
 */
@Service
@Transactional(readOnly = true)
public class SRechargeRecordService extends CrudService<SRechargeRecordDao, SRechargeRecord> {
	@Autowired
	protected PBalanceDao balanceDao;
	@Autowired
	protected PBalanceRecordDao balanceRecordDao;
	

	public SRechargeRecord get(String id) {
		return super.get(id);
	}
	
	public List<SRechargeRecord> findList(SRechargeRecord sRechargeRecord) {
		return super.findList(sRechargeRecord);
	}
	
	public Page<SRechargeRecord> findPage(Page<SRechargeRecord> page, SRechargeRecord sRechargeRecord) {
		return super.findPage(page, sRechargeRecord);
	}
	
	@Transactional(readOnly = false)
	public void save(SRechargeRecord sRechargeRecord) {
		super.save(sRechargeRecord);
	}
	
	@Transactional(readOnly = false)
	public void delete(SRechargeRecord sRechargeRecord) {
		super.delete(sRechargeRecord);
	}
	@Transactional(readOnly = false)
	public boolean auditRecord(SRechargeRecord sRechargeRecord) {
		 boolean isOk=false;
		if(sRechargeRecord.getStatus()==2){
			SRechargeRecord t=get(sRechargeRecord.getId());
			PBalance pBalance =balanceDao.findByPlayerId(t.getPlayerId());
			int i=0;
			if(pBalance==null){
				pBalance=new PBalance();
				pBalance.setPlayerId(sRechargeRecord.getPlayerId());
				pBalance.setBalanceVal(t.getActualAccount());
				i=balanceDao.insert(pBalance);
			}else{
				pBalance.setBalanceVal(sRechargeRecord.getActualAccount());
				i=balanceDao.update(pBalance);
			}
			if(i>0){
				PBalanceRecord pBalanceRecord=new PBalanceRecord();
				pBalanceRecord.setOrderId(Long.parseLong(t.getId()));
				pBalanceRecord.setPlayerId(t.getPlayerId());
				pBalanceRecord.setBalanceType(1);
				pBalanceRecord.setBalanceVal(sRechargeRecord.getActualAccount());
				pBalanceRecord.setCreateUser(sRechargeRecord.getAuditor());
				if(balanceRecordDao.insert(pBalanceRecord)>0){
					isOk=dao.auditRecord(sRechargeRecord)>0;
				};
			}
		}else{
			isOk=dao.auditRecord(sRechargeRecord)>0;
		}
		return isOk;
	}	
	
	
	
}