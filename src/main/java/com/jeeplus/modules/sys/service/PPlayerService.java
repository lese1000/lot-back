/**
 * apple
 */
package com.jeeplus.modules.sys.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.common.persistence.Page;
import com.jeeplus.common.service.CrudService;
import com.jeeplus.modules.sys.entity.PPlayer;
import com.jeeplus.modules.sys.dao.PPlayerDao;

/**
 * 玩家账号Service
 * @author asd
 * @version 2018-01-24
 */
@Service
@Transactional(readOnly = true)
public class PPlayerService extends CrudService<PPlayerDao, PPlayer> {

	public PPlayer get(String id) {
		return super.get(id);
	}
	
	public List<PPlayer> findList(PPlayer pPlayer) {
		return super.findList(pPlayer);
	}
	
	public Page<PPlayer> findPage(Page<PPlayer> page, PPlayer pPlayer) {
		return super.findPage(page, pPlayer);
	}
	
	@Transactional(readOnly = false)
	public void save(PPlayer pPlayer) {
		super.save(pPlayer);
	}
	
	@Transactional(readOnly = false)
	public void update(PPlayer pPlayer) {
		super.update(pPlayer);
	}
	
	@Transactional(readOnly = false)
	public void insert(PPlayer pPlayer) {
		super.insert(pPlayer);
	}
	
	@Transactional(readOnly = false)
	public void delete(PPlayer pPlayer) {
		super.delete(pPlayer);
	}
	
	
	
	
}