/**
 * apple
 */
package com.jeeplus.modules.lottery.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.common.persistence.Page;
import com.jeeplus.common.service.CrudService;
import com.jeeplus.modules.lottery.dao.POrderDao;
import com.jeeplus.modules.lottery.entity.POrder;
import com.jeeplus.modules.lottery.vo.DayOrderVo;
import com.jeeplus.modules.lottery.vo.OrderLotteryNumVo;
import com.jeeplus.modules.lottery.vo.PlayerOrderVo;

/**
 * 订单Service
 * @author asd
 * @version 2018-01-29
 */
@Service
@Transactional(readOnly = true)
public class POrderService extends CrudService<POrderDao, POrder> {

	public POrder get(String id) {
		return super.get(id);
	}
	
	public List<POrder> findList(POrder pOrder) {
		return super.findList(pOrder);
	}
	
	public Page<POrder> findPage(Page<POrder> page, POrder pOrder) {
		return super.findPage(page, pOrder);
	}
	
	@Transactional(readOnly = false)
	public void save(POrder pOrder) {
		super.save(pOrder);
	}
	
	@Transactional(readOnly = false)
	public void delete(POrder pOrder) {
		super.delete(pOrder);
	}
	
	public List<OrderLotteryNumVo> findOrderSumMoneyGroupByLotteryNum(OrderLotteryNumVo vo) {
		return dao.findOrderSumMoneyGroupByLotteryNum(vo);
	}
	
	public List<PlayerOrderVo> findOrderSumMoneyGroupByPlauer(PlayerOrderVo vo) {
		return dao.findOrderSumMoneyGroupByPlauer(vo);
	}
	public List<DayOrderVo> findOrderSumMoneyGroupByDay(DayOrderVo vo) {
		return dao.findOrderSumMoneyGroupByDay(vo);
	}
	
	
	
	
}