package com.haoyun.automationtesting.page;

import org.openqa.selenium.WebDriver;

import com.haoyun.automationtesting.framework.action;
import com.haoyun.automationtesting.framework.log;

/***
 * @功能模块:ERP 资产管理/基础信息管理/办公用品建档
 * @作用:页面元素定位,包括属性数据及方法
 * @author admin
 *
 */

public class _H002 extends action {
	
	public _H002() {
		super();
		// TODO 自动生成的构造函数存根
	}
	
	public _H002(WebDriver driver) {
		super(driver);
		
	}
	/**
	 * 点击进入办公用品建档页面
	 */
	public static void H002_clickMenu() throws Exception {
		action.sleep(1);
		PM.normalize_texts_clicks("资产管理");
		PM.normalize_texts_clicks("基础信息管理");
		PM.normalize_texts_clicks("办公用品建档");
		action.sleep(1);
	}
	
	
	/**
	 * 用例执行结束，返回办公用品建档的首页,收起菜单栏
	 */
	public static void H002_returnMenu() throws Exception {
		action.JS_refresh();
		action.sleep(1);
	}
	
	
	/**
	 * 新建办公用品
	 * @param name 办公用品名称
	 * @param specification 规格型号
	 * @param unit 单位
	 * @throws Exception
	 */
	public static void H002_addNewOfficeSupplies(String name, String specification, String unit) throws Exception {
		PM.normalize_texts_clicks("新建");
		PM.normalize_inputs_sendkeys("办公用品名称*", name);
		PM.normalize_inputs_sendkeys("规格型号*", specification);
		PM.normalize_inputs_sendkeys("单位*", unit);
		PM.click_QD();
		
	}
	
	/**
	 * 根据办公用品名称搜索
	 * @param name 办公用品名称
	 * @throws Exception
	 */
	public static void H002_searchByName(String name) throws Exception {
		PM.normalize_inputs_sendkeys("办公用品名称", name);
		PM.normalize_texts_clicks("搜索");
		action.sleep(2);
	}
	
	
	/**
	 * 根据办公用品编号搜索
	 * @param number 办公用品名称
	 * @throws Exception
	 */
	public static void H002_searchByNumber(String number) throws Exception {
		PM.normalize_inputs_sendkeys("办公用品编号", number);
		PM.normalize_texts_clicks("搜索");
	}
	
	
	/**
	 * 删除办公用品
	 * @param name 办公用品名称
	 * @throws Exception
	 */
	public static void H002_delete(String name) throws Exception {
		H002_searchByName(name);
		PM.normalize_texts_clicks(name);
		PM.click_SC();
		PM.click_QD();
	}
	
	
	/**
	 * 修改办公用品信息
	 * @param name 办公用品名称
	 * @param newName 新名称
	 * @param newSpecification 新规格
	 * @param newUnit 新单位
	 * @throws Exception
	 */
	public static void H002_modify(String name, String newName, String newSpecification,String newUnit)throws Exception {
		try{
			H002_searchByName(name);
		}catch(Exception e){
			log.logInfo("未找到该办公用品");
		}
		PM.click_XG();
		PM.normalize_inputs_sendkeys("办公用品名称*", newName);
		PM.normalize_inputs_sendkeys("规格型号*", newSpecification);
		PM.normalize_inputs_sendkeys("单位*", newUnit);
		PM.normalize_texts_clicks("确定");
	}
	
	
}
