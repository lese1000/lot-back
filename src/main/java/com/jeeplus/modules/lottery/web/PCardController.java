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
import com.jeeplus.modules.lottery.entity.PCard;
import com.jeeplus.modules.lottery.service.PCardService;

/**
 * CARTController
 * @author asd
 * @version 2018-01-29
 */
@Controller
@RequestMapping(value = "${adminPath}/lottery/pCard")
public class PCardController extends BaseController {

	@Autowired
	private PCardService pCardService;
	
	@ModelAttribute
	public PCard get(@RequestParam(required=false) String id) {
		PCard entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = pCardService.get(id);
		}
		if (entity == null){
			entity = new PCard();
		}
		return entity;
	}
	
	/**
	 * CART列表页面
	 */
	@RequiresPermissions("lottery:pCard:list")
	@RequestMapping(value = {"list", ""})
	public String list(PCard pCard, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PCard> page = pCardService.findPage(new Page<PCard>(request, response), pCard); 
		model.addAttribute("page", page);
		model.addAttribute("pCard", pCard);
		return "modules/lottery/pCardList";
	}

	/**
	 * 查看，增加，编辑CART表单页面
	 */
	@RequiresPermissions(value={"lottery:pCard:view","lottery:pCard:add","lottery:pCard:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(PCard pCard, Model model) {
		model.addAttribute("pCard", pCard);
		return "modules/lottery/pCardForm";
	}

	/**
	 * 保存CART
	 */
	@RequiresPermissions(value={"lottery:pCard:add","lottery:pCard:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public String save(PCard pCard, Model model, RedirectAttributes redirectAttributes) throws Exception{
		if (!beanValidator(model, pCard)){
			return form(pCard, model);
		}
		if(!pCard.getIsNewRecord()){//编辑表单保存
			PCard t = pCardService.get(pCard.getId());//从数据库取出记录的值
			MyBeanUtils.copyBeanNotNull2Bean(pCard, t);//将编辑表单中的非NULL值覆盖数据库记录中的值
			pCardService.save(t);//保存
		}else{//新增表单保存
			pCardService.save(pCard);//保存
		}
		addMessage(redirectAttributes, "保存CART成功");
		return "redirect:"+Global.getAdminPath()+"/lottery/pCard/?repage";
	}
	
	/**
	 * 删除CART
	 */
	@RequiresPermissions("lottery:pCard:del")
	@RequestMapping(value = "delete")
	public String delete(PCard pCard, RedirectAttributes redirectAttributes) {
		pCardService.delete(pCard);
		addMessage(redirectAttributes, "删除CART成功");
		return "redirect:"+Global.getAdminPath()+"/lottery/pCard/?repage";
	}
	
	/**
	 * 批量删除CART
	 */
	@RequiresPermissions("lottery:pCard:del")
	@RequestMapping(value = "deleteAll")
	public String deleteAll(String ids, RedirectAttributes redirectAttributes) {
		String idArray[] =ids.split(",");
		for(String id : idArray){
			pCardService.delete(pCardService.get(id));
		}
		addMessage(redirectAttributes, "删除CART成功");
		return "redirect:"+Global.getAdminPath()+"/lottery/pCard/?repage";
	}
	
	/**
	 * 导出excel文件
	 */
	@RequiresPermissions("lottery:pCard:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(PCard pCard, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "CART"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<PCard> page = pCardService.findPage(new Page<PCard>(request, response, -1), pCard);
    		new ExportExcel("CART", PCard.class).setDataList(page.getList()).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出CART记录失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/lottery/pCard/?repage";
    }

	/**
	 * 导入Excel数据

	 */
	@RequiresPermissions("lottery:pCard:import")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<PCard> list = ei.getDataList(PCard.class);
			for (PCard pCard : list){
				try{
					pCardService.save(pCard);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条CART记录。");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条CART记录"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入CART失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/lottery/pCard/?repage";
    }
	
	/**
	 * 下载导入CART数据模板
	 */
	@RequiresPermissions("lottery:pCard:import")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "CART数据导入模板.xlsx";
    		List<PCard> list = Lists.newArrayList(); 
    		new ExportExcel("CART数据", PCard.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/lottery/pCard/?repage";
    }
	
	
	

}