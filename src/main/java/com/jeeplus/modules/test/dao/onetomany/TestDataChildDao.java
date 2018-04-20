/**
 * apple
 */
package com.jeeplus.modules.test.dao.onetomany;

import com.jeeplus.common.persistence.CrudDao;
import com.jeeplus.common.persistence.annotation.MyBatisDao;
import com.jeeplus.modules.test.entity.onetomany.TestDataChild;

/**
 * 票务代理DAO接口
 * @author liugf
 * @version 2016-10-06
 */
@MyBatisDao
public interface TestDataChildDao extends CrudDao<TestDataChild> {

	
}