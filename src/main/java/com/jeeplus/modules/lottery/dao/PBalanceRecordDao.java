/**
 * apple
 */
package com.jeeplus.modules.lottery.dao;

import java.util.List;

import com.jeeplus.common.persistence.CrudDao;
import com.jeeplus.common.persistence.annotation.MyBatisDao;
import com.jeeplus.modules.lottery.entity.PBalanceRecord;
import com.jeeplus.modules.lottery.vo.PlayerBalanceVo;

/**
 * 余额记录DAO接口
 * @author asd
 * @version 2018-01-29
 */
@MyBatisDao
public interface PBalanceRecordDao extends CrudDao<PBalanceRecord> {
	List<PlayerBalanceVo> findBalanceByVo(PlayerBalanceVo vo);
	List<PlayerBalanceVo> findBalanceByVoForParent(PlayerBalanceVo vo);
	
}