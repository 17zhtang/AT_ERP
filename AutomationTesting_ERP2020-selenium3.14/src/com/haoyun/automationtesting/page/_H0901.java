package com.haoyun.automationtesting.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.haoyun.automationtesting.framework.action;
import com.haoyun.automationtesting.framework.log;

/***
 * @功能模块:仓库管理
 * @作用:页面元素定位,包括属性数据及方法
 * @author 
 *
 */
public class _H0901 extends action {

	public _H0901() {
		super();
		// TODO 自动生成的构造函数存根
	}

	public _H0901(WebDriver driver) {
		super(driver);

	}

	/* 下面为页面所有元素 */
	/* * 点击设备管理-场景配置*/
	public static void step1() throws Exception {
		PM.return_sy();
		PM.menu_text("设备管理");
		PM.menu_text("场景配置");
	}


	/**
	 * 业务步骤 新增场景
	 * @例子 h740_CJXZ("自动化基础数据", "自动化343564场景名称", "自动化343564场景名称", "是", "开")
	 * 
	 * @param ssjg
	 *            :所属机构 
	 * @param cjmc
	 *            ：场景名称
	 * @param cjsm
	 *            :场景说明           
	 * @param sfzxkk
	 *            :是否中心可控    
	 * @param zt
	 *          :状态                   
	 * @throws Exception
	 */
	public static void h740_CJXZ(String ssjg, String cjmc, String cjsm, String sfzxkk, String zt) throws Exception {
		
		step1();
		
		PM.normalize_texts_clicks("新增场景");
		action.sleep(1);
		
		PM.normalize_inputs_clicks("所属机构");
		PM.pop_click_textspan("所有机构");
		PM.normalize_texts_clicks(ssjg);
		PM.normalize_texts_clicks("所属机构");//收起下拉框
		
		PM.normalize_inputs_sendkeys("场景名称", cjmc);
		PM.normalize_inputs_sendkeys("场景说明", cjsm);
		action.findElement_click(By.xpath(".//*[normalize-space(text()) and normalize-space(.)='是否中心可控']/following::input[@placeholder='请选择'][1]"));
		//action.findElement_sendkeys(By.xpath(".//*[normalize-space(text()) and normalize-space(.)='是否中心可控']/following::input[@placeholder='请选择'][1]"), sfzxkk);
		PM.normalize_texts_clicks(sfzxkk);
		action.sleep(1);
		PM.normalize_inputs_clicks("状态");
		PM.normalize_texts_clicks(zt);
		
		
		PM.click_QD();
		action.sleep(1);

		log.logInfo("新增场景信息,场景名称:" + cjmc);

	}
	/**
	 * 业务步骤 新增场景
	 * @例子 h740_CJXZ("自动化343564场景名称")
	 * 
	 * @param cjmc
	 *            ：场景名称
	 *                  
	 * @throws Exception
	 */
	public static void h740_CJXZ( String cjmc) throws Exception {
	
		h740_CJXZ("自动化基础数据",  cjmc,  cjmc,  "是",  "开");
		
	}
	/**
	 * 业务步骤 场景查询
	 * @例子 h740_CJCX("自动化基础数据", "自动化343564场景名称")
	 * 
	 * @param jgmc
	 *            :机构名称
	 * @param cjmc
	 *            ：场景名称
	 * @throws Exception
	 */
	public static void h740_CJCX(String jgmc, String cjmc) throws Exception {
		
		step1();
		
		if(!jgmc.isEmpty()){
			
			action.findElements_click(By.xpath(".//*[@role='tree']//*[normalize-space(text()) and normalize-space(.)='"
					+ jgmc + "']"), 0);
			
		}
		if(!cjmc.isEmpty()){
			
			PM.normalize_inputs_sendkeys("场景名称", cjmc);
		}
		
		action.sleep(1);
		PM.click_CX();
	   
		log.logInfo("查询场景配置信息,场景查询:" + cjmc);

	}
	
	/**
	 * 业务步骤 关联按键
	 * @例子 h740_GLAJ("自动化343564场景名称", "自动化343564关联设备名称", "1.89.89.89",  "IN1")
	 * 
	 * @param cjmc
	 *            :场景名称
	 * @param glsbmc
	 *            ：关联设备名称
	 * @param sbIP
	 *            :设备IP   
	 * @param kglsr           
	 *            :开关量输入   
	 * @throws Exception
	 */
	public static void h740_GLAJ(String cjmc,String glsbmc,String sbIP, String kglsr) throws Exception {
		
		h740_CJCX("", cjmc);
		action.sleep(1);
		
		PM.normalize_texts_clicks("关联按键");
		action.sleep(1);
		
		PM.normalize_inputs_sendkeys("关联设备名称", glsbmc);
		PM.normalize_inputs_sendkeys("设备IP", sbIP);
		PM.normalize_inputs_clicks("开关量输入");
		PM.normalize_texts_clicks(kglsr);
		PM.click_QD();
		
		action.sleep(1);
		
		log.logInfo(" 关联按键信息,关联设备名称:" + glsbmc);

	}
	/**
	 * 业务步骤 修改场景
	 * @param cjmc
	 *            :场景名称
	 * @param cjsmxg
	 *            ：场景说明修改
	 * @throws Exception
	 */
	public static void h740_CJXG(String cjmc, String cjsmxg) throws Exception {
		
		h740_CJCX("", cjmc);//先查询刚新建的场景，再修改
		action.sleep(1);
		PM.click_XG();
		PM.normalize_inputs_sendkeys("场景说明", cjsmxg);
		PM.click_QD();
		
		log.logInfo("修改场景配置信息,场景说明:" + cjsmxg);

	}
	/**
	 * 业务步骤 场景重置
	 * @param cjmc
	 *            :场景名称
	 * @throws Exception
	 */
	public static void h740_CJCZ(String cjmc) throws Exception {
		
		h740_CJCX("", cjmc);//查询不存在的场景名称
		action.sleep(1);
		PM.click_CZ();
		
	}
	
	/**
	 * 业务步骤 场景删除
	 * @param cjmc
	 *            ：场景名称
	 * @throws Exception
	 */
	public static void h740_CJSC(String cjmc) throws Exception {
		
		h740_CJCX("", cjmc);//查询要删除的场景
		action.sleep(1);
		
		PM.normalize_texts_clicks("删除场景");
		action.sleep(1);
		
		if (action.isTextPresent("确认删除选中记录吗?")) {
			PM.click_QD();//确定
		}
		action.sleep(1);
			
	}

	

}
