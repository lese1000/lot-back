/**
 * apple
 */
package com.jeeplus.modules.lottery.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.common.persistence.Page;
import com.jeeplus.common.service.CrudService;
import com.jeeplus.modules.lottery.entity.PBalance;
import com.jeeplus.modules.lottery.entity.PBalanceRecord;
import com.jeeplus.modules.lottery.entity.SCashRecord;
import com.jeeplus.modules.lottery.entity.SRechargeRecord;
import com.jeeplus.modules.lottery.dao.PBalanceDao;
import com.jeeplus.modules.lottery.dao.PBalanceRecordDao;
import com.jeeplus.modules.lottery.dao.SCashRecordDao;

/**
 * CashRecordService
 * @author asd
 * @version 2018-01-29
 */
@Service
@Transactional(readOnly = true)
public class SCashRecordService extends CrudService<SCashRecordDao, SCashRecord> {
	@Autowired
	protected PBalanceDao balanceDao;
	@Autowired
	protected PBalanceRecordDao balanceRecordDao;
	

	public SCashRecord get(String id) {
		return super.get(id);
	}
	
	public List<SCashRecord> findList(SCashRecord sCashRecord) {
		return super.findList(sCashRecord);
	}
	
	public Page<SCashRecord> findPage(Page<SCashRecord> page, SCashRecord sCashRecord) {
		return super.findPage(page, sCashRecord);
	}
	
	@Transactional(readOnly = false)
	public void save(SCashRecord sCashRecord) {
		super.save(sCashRecord);
	}
	
	@Transactional(readOnly = false)
	public void delete(SCashRecord sCashRecord) {
		super.delete(sCashRecord);
	}
	
	@Transactional(readOnly = false)
	public boolean auditRecord(SCashRecord sCashRecord) {
		 boolean isOk=false;
		if(sCashRecord.getStatus()==2){
			SCashRecord t=get(sCashRecord.getId());
			PBalance pBalance =balanceDao.findByPlayerId(t.getPlayerId());
			int i=0;
			if(pBalance!=null){
				pBalance.setBalanceVal(t.getCashVal()*-1);
				i=balanceDao.update(pBalance);
			}
			if(i>0){
				PBalanceRecord pBalanceRecord=new PBalanceRecord();
				pBalanceRecord.setOrderId(Long.parseLong(t.getId()));
				pBalanceRecord.setPlayerId(t.getPlayerId());
				pBalanceRecord.setBalanceType(2);
				pBalanceRecord.setBalanceVal(t.getCashVal());
				pBalanceRecord.setCreateUser(sCashRecord.getAuditor());
				if(balanceRecordDao.insert(pBalanceRecord)>0){
					isOk=dao.auditRecord(sCashRecord)>0;
				};
			}
		}else{
			isOk=dao.auditRecord(sCashRecord)>0;
		}
		return isOk;
	}	
	
	
	
	
}