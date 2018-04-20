/**
 * apple
 */
package com.jeeplus.modules.lottery.dao;

import java.util.List;

import com.jeeplus.common.persistence.CrudDao;
import com.jeeplus.common.persistence.annotation.MyBatisDao;
import com.jeeplus.modules.lottery.entity.POrder;
import com.jeeplus.modules.lottery.vo.DayOrderVo;
import com.jeeplus.modules.lottery.vo.OrderLotteryNumVo;
import com.jeeplus.modules.lottery.vo.PlayerOrderVo;

/**
 * 订单DAO接口
 * @author asd
 * @version 2018-01-29
 */
@MyBatisDao
public interface POrderDao extends CrudDao<POrder> {

	List<PlayerOrderVo> findOrderSumMoneyGroupByPlauer(PlayerOrderVo vo);
	List<OrderLotteryNumVo> findOrderSumMoneyGroupByLotteryNum(OrderLotteryNumVo vo);
	List<DayOrderVo> findOrderSumMoneyGroupByDay(DayOrderVo vo);
}