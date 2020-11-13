package com.haoyun.automationtesting.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.haoyun.automationtesting.framework.action;
import com.haoyun.automationtesting.framework.log;

/***
 * @功能模块:ERP 生产管理/生产任务管理
 * @作用:页面元素定位,包括属性数据及方法
 * @author admin
 *
 */

public class _H0701 extends action {
	
	public _H0701() {
		super();
		// TODO 自动生成的构造函数存根
	}
	
	public _H0701(WebDriver driver) {
		super(driver);
		
	}
	/**
	 * 点击进入生产任务管理页面
	 */
	public static void H0701_clickMenu() throws Exception {
		action.sleep(1);
		PM.clickMenu("生产管理");
		PM.clickMenu("生产任务管理");
//		PM.normalize_texts_clicks("生产管理");
//		PM.normalize_texts_clicks("生产管理任务");
		action.sleep(1);
	}

	
	/**
	 * 用例执行结束，返回首页
	 */
	public static void H002_returnMenu() throws Exception {
		action.JS_refresh();
		action.sleep(1);
	}
	
	
	/**
	 * 根据售前单号设置预计交付时间
	 * @param number
	 * @throws Exception
	 */
	public static void H0701_YJJFSJ(String number) throws Exception {
		PM.normalize_texts_clicks(number);
		PM.normalize_texts_clicks("预期交付时间");
		driver.findElement(By.xpath("//input[@id='CONTROL_TIME_1591759720995__0_']")).clear();
		action.findElement_sendkeys(By.xpath("//input[@id='CONTROL_TIME_1591759720995__0_']"), PM.YJJFSJ);
		PM.normalize_texts_clicks("确认");
		PM.normalize_texts_clicks("取消");
	}


}
