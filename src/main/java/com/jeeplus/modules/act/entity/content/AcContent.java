/**
 * apple
 */
package com.jeeplus.modules.act.entity.content;


import com.jeeplus.common.persistence.DataEntity;
import com.jeeplus.common.utils.excel.annotation.ExcelField;

/**
 * 公告和活动Entity
 * @author asd
 * @version 2018-01-23
 */
public class AcContent extends DataEntity<AcContent> {
	
	private static final long serialVersionUID = 1L;
	private String acType;		// 类型 1：公告 2：活动
	private String title;		// 标题
	private String content;		// 内容
	
	public AcContent() {
		super();
	}

	public AcContent(String id){
		super(id);
	}

	@ExcelField(title="类型 ", dictType="", align=2, sort=5)
	public String getAcType() {
		return acType;
	}

	public void setAcType(String acType) {
		this.acType = acType;
	}
	
	@ExcelField(title="标题", align=2, sort=7)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@ExcelField(title="内容", align=2, sort=8)
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
}