/**
 * apple
 */
package com.jeeplus.modules.sys.dao;

import com.jeeplus.common.persistence.CrudDao;
import com.jeeplus.common.persistence.annotation.MyBatisDao;
import com.jeeplus.modules.sys.entity.PPlayer;

/**
 * 玩家账号DAO接口
 * @author asd
 * @version 2018-01-24
 */
@MyBatisDao
public interface PPlayerDao extends CrudDao<PPlayer> {

	
}