/**
 * apple
 */
package com.jeeplus.modules.lottery.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.jeeplus.common.utils.DateUtils;
import com.jeeplus.common.utils.MyBeanUtils;
import com.jeeplus.common.config.Global;
import com.jeeplus.common.persistence.Page;
import com.jeeplus.common.web.BaseController;
import com.jeeplus.common.utils.StringUtils;
import com.jeeplus.common.utils.excel.ExportExcel;
import com.jeeplus.common.utils.excel.ImportExcel;
import com.jeeplus.modules.lottery.entity.LLotteryNum;
import com.jeeplus.modules.lottery.service.LLotteryNumService;
import com.jeeplus.modules.lottery.service.LLotteryTypeService;

/**
 * 彩票期号Controller
 * @author asd
 * @version 2018-01-29
 */
@Controller
@RequestMapping(value = "${adminPath}/lottery/lLotteryNum")
public class LLotteryNumController extends BaseController {

	@Autowired
	private LLotteryNumService lLotteryNumService;
	
	@Autowired
	private LLotteryTypeService lLotteryTypeService;
	
	@ModelAttribute
	public LLotteryNum get(@RequestParam(required=false) String id) {
		LLotteryNum entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = lLotteryNumService.get(id);
		}
		if (entity == null){
			entity = new LLotteryNum();
		}
		return entity;
	}
	
	/**
	 * 彩票期号列表页面
	 */
	@RequiresPermissions("lottery:lLotteryNum:list")
	@RequestMapping(value = {"list", ""})
	public String list(LLotteryNum lotteryNum, HttpServletRequest request, HttpServletResponse response, Model model) {
		if(lotteryNum.getLotteryNum()!=null){
			lotteryNum.setLotteryNum(lotteryNum.getLotteryNum().replaceAll(",", "").trim());
		}
		Page<LLotteryNum> page = lLotteryNumService.findPage(new Page<LLotteryNum>(request, response), lotteryNum); 
		model.addAttribute("page", page);
		model.addAttribute("lotteryNum", lotteryNum);
		model.addAttribute("lLotteryTyle", lLotteryTypeService.findSelectList());
		return "modules/lottery/lLotteryNumList";
	}

	/**
	 * 查看，增加，编辑彩票期号表单页面
	 */
	@RequiresPermissions(value={"lottery:lLotteryNum:view","lottery:lLotteryNum:add","lottery:lLotteryNum:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(LLotteryNum lLotteryNum, Model model) {
		model.addAttribute("lLotteryNum", lLotteryNum);
		model.addAttribute("LotteryTypeId", lLotteryNum.getLotteryTypeId());
		model.addAttribute("lLotteryTyle", lLotteryTypeService.findSelectList());
		return "modules/lottery/lLotteryNumForm";
	}

	/**
	 * 保存彩票期号
	 */
	@RequiresPermissions(value={"lottery:lLotteryNum:add","lottery:lLotteryNum:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public String save(LLotteryNum lLotteryNum, Model model, RedirectAttributes redirectAttributes) throws Exception{
		if (!beanValidator(model, lLotteryNum)){
			return form(lLotteryNum, model);
		}
		if(!lLotteryNum.getIsNewRecord()){//编辑表单保存
			LLotteryNum t = lLotteryNumService.get(lLotteryNum.getId());//从数据库取出记录的值
			MyBeanUtils.copyBeanNotNull2Bean(lLotteryNum, t);//将编辑表单中的非NULL值覆盖数据库记录中的值
			lLotteryNumService.save(t);//保存
		}else{//新增表单保存
			lLotteryNumService.save(lLotteryNum);//保存
		}
		addMessage(redirectAttributes, "保存彩票期号成功");
		return "redirect:"+Global.getAdminPath()+"/lottery/lLotteryNum/?repage";
	}
	
	/**
	 * 删除彩票期号
	 */
	@RequiresPermissions("lottery:lLotteryNum:del")
	@RequestMapping(value = "delete")
	public String delete(LLotteryNum lLotteryNum, RedirectAttributes redirectAttributes) {
		lLotteryNumService.delete(lLotteryNum);
		addMessage(redirectAttributes, "删除彩票期号成功");
		return "redirect:"+Global.getAdminPath()+"/lottery/lLotteryNum/?repage";
	}
	
	/**
	 * 批量删除彩票期号
	 */
	@RequiresPermissions("lottery:lLotteryNum:del")
	@RequestMapping(value = "deleteAll")
	public String deleteAll(String ids, RedirectAttributes redirectAttributes) {
		String idArray[] =ids.split(",");
		for(String id : idArray){
			lLotteryNumService.delete(lLotteryNumService.get(id));
		}
		addMessage(redirectAttributes, "删除彩票期号成功");
		return "redirect:"+Global.getAdminPath()+"/lottery/lLotteryNum/?repage";
	}
	
	/**
	 * 导出excel文件
	 */
	@RequiresPermissions("lottery:lLotteryNum:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(LLotteryNum lLotteryNum, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "彩票期号"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<LLotteryNum> page = lLotteryNumService.findPage(new Page<LLotteryNum>(request, response, -1), lLotteryNum);
    		new ExportExcel("彩票期号", LLotteryNum.class).setDataList(page.getList()).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出彩票期号记录失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/lottery/lLotteryNum/?repage";
    }

	/**
	 * 导入Excel数据

	 */
	@RequiresPermissions("lottery:lLotteryNum:import")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<LLotteryNum> list = ei.getDataList(LLotteryNum.class);
			for (LLotteryNum lLotteryNum : list){
				try{
					lLotteryNumService.save(lLotteryNum);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条彩票期号记录。");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条彩票期号记录"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入彩票期号失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/lottery/lLotteryNum/?repage";
    }
	
	/**
	 * 下载导入彩票期号数据模板
	 */
	@RequiresPermissions("lottery:lLotteryNum:import")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "彩票期号数据导入模板.xlsx";
    		List<LLotteryNum> list = Lists.newArrayList(); 
    		new ExportExcel("彩票期号数据", LLotteryNum.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/lottery/lLotteryNum/?repage";
    }
	
	
	

}