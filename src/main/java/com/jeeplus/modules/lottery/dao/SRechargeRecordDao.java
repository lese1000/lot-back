/**
 * apple
 */
package com.jeeplus.modules.lottery.dao;

import com.jeeplus.common.persistence.CrudDao;
import com.jeeplus.common.persistence.annotation.MyBatisDao;
import com.jeeplus.modules.lottery.entity.SRechargeRecord;

/**
 * RechargeRecordDAO接口
 * @author asd
 * @version 2018-01-29
 */
@MyBatisDao
public interface SRechargeRecordDao extends CrudDao<SRechargeRecord> {
	public int auditRecord(SRechargeRecord entity);
	
}