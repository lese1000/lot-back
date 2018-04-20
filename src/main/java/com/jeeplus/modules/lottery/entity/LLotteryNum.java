/**
 * apple
 */
package com.jeeplus.modules.lottery.entity;

import javax.validation.constraints.NotNull;
import java.util.Date;

import com.jeeplus.common.persistence.DataEntity;
import com.jeeplus.common.utils.excel.annotation.ExcelField;

/**
 * 彩票期号Entity
 * @author asd
 * @version 2018-01-29
 */
public class LLotteryNum extends DataEntity<LLotteryNum> {
	
	private static final long serialVersionUID = 1L;
	private Long lotteryTypeId;		// 彩票类型id
	private String lotteryTypeName;		// 彩票类型id
	private String lotteryNum;		// 期号
	private String winNum;		// 中奖号码
	private String createUser;		// create_user
	private String updateUser;		// update_user
	private Date beginCreateDate;		// 开始 create_date
	private Date endCreateDate;		// 结束 create_date
	private Date openTime;		// openTime
	
	
	
	public String getLotteryTypeName() {
		return lotteryTypeName;
	}

	public void setLotteryTypeName(String lotteryTypeName) {
		this.lotteryTypeName = lotteryTypeName;
	}

	public Date getOpenTime() {
		return openTime;
	}

	public void setOpenTime(Date openTime) {
		this.openTime = openTime;
	}

	public LLotteryNum() {
		super();
	}

	public LLotteryNum(String id){
		super(id);
	}

	
	@NotNull(message="彩票类型id不能为空")
	@ExcelField(title="彩票类型id", dictType="", align=2, sort=1)
	public Long getLotteryTypeId() {
		return lotteryTypeId;
	}

	public void setLotteryTypeId(Long lotteryTypeId) {
		this.lotteryTypeId = lotteryTypeId;
	}
	
	@ExcelField(title="期号", align=2, sort=2)
	public String getLotteryNum() {
		return lotteryNum;
	}

	public void setLotteryNum(String lotteryNum) {
		this.lotteryNum = lotteryNum;
	}
	
	@ExcelField(title="中奖号码", align=2, sort=3)
	public String getWinNum() {
		return winNum;
	}

	public void setWinNum(String winNum) {
		this.winNum = winNum;
	}
	
	@ExcelField(title="create_user", align=2, sort=6)
	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	
	@ExcelField(title="update_user", align=2, sort=7)
	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	
	public Date getBeginCreateDate() {
		return beginCreateDate;
	}

	public void setBeginCreateDate(Date beginCreateDate) {
		this.beginCreateDate = beginCreateDate;
	}
	
	public Date getEndCreateDate() {
		return endCreateDate;
	}

	public void setEndCreateDate(Date endCreateDate) {
		this.endCreateDate = endCreateDate;
	}
		
}