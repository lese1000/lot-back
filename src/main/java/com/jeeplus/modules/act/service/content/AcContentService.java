/**
 * apple
 */
package com.jeeplus.modules.act.service.content;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.common.persistence.Page;
import com.jeeplus.common.service.CrudService;
import com.jeeplus.modules.act.entity.content.AcContent;
import com.jeeplus.modules.act.dao.content.AcContentDao;

/**
 * 公告和活动Service
 * @author asd
 * @version 2018-01-23
 */
@Service
@Transactional(readOnly = true)
public class AcContentService extends CrudService<AcContentDao, AcContent> {

	public AcContent get(String id) {
		return super.get(id);
	}
	
	public List<AcContent> findList(AcContent acContent) {
		return super.findList(acContent);
	}
	
	public Page<AcContent> findPage(Page<AcContent> page, AcContent acContent) {
		return super.findPage(page, acContent);
	}
	
	@Transactional(readOnly = false)
	public void save(AcContent acContent) {
		super.save(acContent);
	}
	
	@Transactional(readOnly = false)
	public void delete(AcContent acContent) {
		super.delete(acContent);
	}
	
	
	
	
}