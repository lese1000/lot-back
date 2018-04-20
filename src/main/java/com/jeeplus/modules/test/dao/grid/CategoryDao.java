/**
 * apple
 */
package com.jeeplus.modules.test.dao.grid;

import com.jeeplus.common.persistence.CrudDao;
import com.jeeplus.common.persistence.annotation.MyBatisDao;
import com.jeeplus.modules.test.entity.grid.Category;

/**
 * 商品分类DAO接口
 * @author liugf
 * @version 2016-10-04
 */
@MyBatisDao
public interface CategoryDao extends CrudDao<Category> {

	
}