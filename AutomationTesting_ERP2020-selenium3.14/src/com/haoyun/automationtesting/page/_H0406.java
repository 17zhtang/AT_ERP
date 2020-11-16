package com.haoyun.automationtesting.page;

import com.haoyun.automationtesting.framework.Robots;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.haoyun.automationtesting.framework.action;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * 方案清单管理-新增
 * @author Administrator
 *
 */
public class _H0406 extends action {
	public _H0406(){
		super();
	}

	public _H0406(WebDriver driver){
		super(driver);
	}

	/* 下面为页面所有元素 */
	public static void step1() throws Exception {
		PM.return_sy();
//		MainStart.driver.navigate().refresh();
		action.sleep(2);
		PM.menu_text("方案项目管理");
		action.sleep(1);
		PM.menu_text("清单管理");
		PM.menu_text("方案清单管理");
		action.sleep(1);
	}


	/**
	 * 新建
	 * @param FAQDMC 方案清单名称
	 * @param KHLX 客户类型
	 * @param KHMC 客户名称
	 * @param CSLX 场所类型
	 * @param ZXTLX 子系统类型
	 * @param BZ 备注
	 * @throws Exception
	 */
	public static void h0406_XZFAQD(String FAQDMC,String KHLX,String KHMC,String CSLX,String ZXTLX,String BZ)
			throws Exception {
		step1();
		action.sleep(1);//20200506
		PM.click_XJ();

		//输入方案清单名称
		sleep(2);
		action.findElement_sendkeys(By.xpath("//input[@data-id='CONTROL_TEXT_6']"), FAQDMC);
		//客户类型
		PM.normalize_inputs_clicks("客户类型*");
		PM.normalize_clickAndSendkeys(KHLX);
		PM.normalize_searchIcon();
		PM.normalize_text_clicks(KHLX);
		PM.normalize_text_clicks("确定");

		//选择客户名称
		action.sleep(2);
		PM.normalize_inputs_clicks("客户名称*");
		PM.normalize_clickAndSendkeys(KHMC);
		PM.normalize_searchIcon();
		PM.normalize_text_clicks(KHMC);
		PM.normalize_text_clicks("确定");

		//场所类型
		PM.option_select(CSLX);

		//子系统类型
		PM.normalize_inputs_clicks("子系统类型 *");
		action.findElement_click(By.xpath("//*[@title='"+ ZXTLX + "']/span"));
		sleep(1);

		//关闭下拉弹窗
//		action.JS_EXE("document.getElementByClass('selectList')[0].removeChild(document.getElementByClass('checkbox-selectList')[0])");
		action.JS_EXE("document.querySelector('.selectList').style.display = 'none'");
//		action.JS_EXE("document.querySelector('.modal hy-modal fade ng-scope ng-isolate-scope in').style.display = 'block'");

		PM.mouse_click(500,500);

		//备注
		sleep(1);
		action.findElement_sendkeys(By.xpath("//textarea"), BZ);

		sleep(1);
//		PM.normalize_text_clicks("确定");
		PM.click_QD();
		PM.click_QD();
		sleep(5);

	}

	/**
	 * 修改
	 * @param FAQDMC 方案清单名称
	 * @param New_FAQDMC 新方案清单名称
	 * @throws Exception
	 */
	public static void h0406_XGFAQD(String FAQDMC,String New_FAQDMC)
			throws Exception {
		step1();
		action.sleep(1);//20200506

		//搜索出需要修改的数据
		action.findElement_sendkeys(By.xpath("//*[@data-id='CONTROL_TEXT_35']"), FAQDMC);
		PM.click_SS();
		action.findElement_click(By.xpath("//span[@title='"+FAQDMC+"']"));
		PM.click_XG();

		//清空并输入方案清单名称
		PM.clear("方案清单名称*");
		sleep(2);
		action.findElement_sendkeys(By.xpath("//input[@data-id='CONTROL_TEXT_6']"), New_FAQDMC);

		PM.click_QD();
		sleep(5);

	}

	/**
	 * 搜索
	 * @param New_FAQDMC 方案清单名称
	 * @param KHLX 客户类型
	 * @param KHMC 客户名称
	 * @param CSLX 场所类型
	 * @param ZXTLX 子系统类型
	 * @param ZZRY 制作人员
	 * @throws Exception
	 */
	public static void h0406_SSFAQD(String New_FAQDMC,String KHLX,String KHMC,String CSLX,String ZXTLX,String ZZRY)
			throws Exception {
		step1();
		action.sleep(1);//20200506
		//方案清单名称
		action.findElement_sendkeys(By.xpath("//*[@data-id='CONTROL_TEXT_35']"), New_FAQDMC);

		//客户类型
		PM.normalize_inputs_clicks("客户类型");
		PM.normalize_clickAndSendkeys(KHLX);
		PM.normalize_searchIcon();
		PM.normalize_text_clicks(KHLX);
		PM.normalize_text_clicks("确定");

		//客户名称
		action.sleep(2);
		PM.normalize_inputs_clicks("客户名称");
		PM.normalize_clickAndSendkeys(KHMC);
		PM.normalize_searchIcon();
		PM.normalize_text_clicks(KHMC);
		PM.normalize_text_clicks("确定");

		//场所类型
		PM.option_select(CSLX);

		//子系统类型
		PM.normalize_inputs_clicks("子系统类型");
		PM.normalize_clickAndSendkeys(ZXTLX);
		PM.normalize_searchIcon();
		PM.normalize_text_clicks(ZXTLX);
		PM.normalize_text_clicks("确定");

		//制作人员
		PM.normalize_inputs_clicks("制作人员");
		PM.normalize_clickAndSendkeys(ZZRY);
		PM.normalize_searchIcon();
		PM.normalize_text_clicks(ZZRY);
		PM.normalize_text_clicks("确定");

		PM.click_SS();
		PM.click_QK();
//		action.findElement_click(By.xpath("//span[@title='"+FAQDMC+"']"));
//		PM.click_SC();


//		PM.click_QD();
		sleep(1);

	}

	/**
	 * 删除
	 * @param New_FAQDMC 新方案清单名称
	 * @throws Exception
	 */
	public static void h0406_SCFAQD(String New_FAQDMC)
			throws Exception {
		step1();
		action.sleep(1);//20200506
		//搜索出需要删除的数据
		action.findElement_sendkeys(By.xpath("//*[@data-id='CONTROL_TEXT_35']"), New_FAQDMC);
		PM.click_SS();
		action.findElement_click(By.xpath("//span[@title='"+New_FAQDMC+"']"));
		PM.click_SC();
		PM.normalize_yes();


//		PM.click_QD();
		sleep(5);

	}

	/**
	 * 方案清单-配置清单
	 * @param SL
	 * @param DJ
	 * @throws Exception
	 */
	public static void h0406_PZQD(String SL,String DJ,String New_FAQDMC)
			throws Exception{
		step1();
		action.sleep(1);//20200506
		//搜索出需要删除的数据
		action.findElement_sendkeys(By.xpath("//*[@data-id='CONTROL_TEXT_35']"), New_FAQDMC);
		PM.click_SS();
		action.findElement_click(By.xpath("//span[@title='"+New_FAQDMC+"']"));
		//点击配置清单
		action.findElement_click(By.xpath("//td//button[contains(text(),'配置清单')]"));
		//点击【添加单一货物】
		action.findElement_click(By.xpath("//*[@data-id='SYS_CONTROL_TABLE_HEADER_BUTTON_22']"));
		PM.normalize_inputs_sendkeys(" 货物编号","060114-1096");
		PM.click_SS();
		sleep(5);
		PM.normalize_text_clicks("060114-1096");
		PM.click_QD();
		sleep(2);

	}
}



