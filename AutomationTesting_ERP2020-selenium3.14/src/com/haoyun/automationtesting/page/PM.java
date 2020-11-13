package com.haoyun.automationtesting.page;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.JavascriptExecutor;

import com.haoyun.automationtesting.framework.Config;
import com.haoyun.automationtesting.framework.ExcelOperate;
import com.haoyun.automationtesting.framework.action;
import com.haoyun.automationtesting.framework.log;
import com.haoyun.automationtesting.test.aadomain.MainStart;

/**
 * 公共方法以及公共控件的封装
 * 
 * @author lisheng
 *
 */
public class PM {
	public static WebDriver driver = MainStart.driver;

	public PM(WebDriver driver) {
		super();
		// action.driver = driver;
		// driver = mainStart.driver;
	}

	// public PM() {
	// driver = MainStart.driver;
	// }
	//
	// static {
	// driver = MainStart.driver;
	// }
	/** 时间随机数，不会重复，返回当天日期。格式“20170828120101” **/
	public static String sjs = MainStart.timenow;// 随机数14位
	public static String sjs6 = new SimpleDateFormat("ssSSS")
			.format(new Date());

	/** 基础数据名称：自动化+6位随机数 **/
	public static String jcsjmc = "自动化" + sjs6;

	/** 关联下单项目**/
	public static String GLXDXM = "JRHY18920201111001";

	/** 开票单位**/
	public static String KPDW = "浩云科技股份有限公司（销售票）";

	/** 工程商名称**/
	public static String GCSMC = "GCS";

	/** 工程内容**/
	public static String GCNR = "监控系统&防盗&联网";

	/** 业务负责人**/
	public static String YWFZR = "唐展宏";

	/** 网点名称**/
	public static String WDMC = "WD";

	/** 线下售前单号**/
	public static String XXSQDH = "SQ";

	/** 生产售前单号**/
	public static String SCSQDH = "XSXDHY189_000064-00" ;

	/** 预计交付时间**/
	public static String YJJFSJ = "2077-12-03 14:44:31";

	/** 机构名称**/
	public static String jgmc = "好运科技";//曼顿设备id：12位
	/** 曼顿设备返回数据 **/
	public static String MDReturnData = "";
	/** 曼顿设备发送数据 **/
	public static String MDSendData = "";

	/** 优柏设备ID **/
	public static String YBDeviceID = "11111111";//优柏设备id：8位
	/** 优柏设备- 电流预警 **/
	public static byte YBDeviceCurrentAlarmStatus = (byte) 0x00;
	/** 优柏设备- 过流预警 **/
	public static byte YBDeviceCurrentWarningStatus = (byte) 0x00;
	/** 优柏设备- 温度上限预警 **/
	public static byte YBDeviceTemperatureStatus = (byte) 0x00;
	/** 优柏设备- 温度上限报警 **/
	public static byte YBDevicemainModule_AlarmStatus = (byte) 0x00;
	/** 优柏设备- 过压欠压报警 **/
	public static byte YBDeviceVoltageStatus = (byte) 0x00;
	/** 优柏设备- 输入缺相 **/
	public static byte YBDevicephaseLossStatus = (byte) 0x00;
	/** 优柏设备- 无源输入(开关量)-包括设备水浸报警，设备门磁报警 **/
	public static byte YBDeviceinputIO = (byte) 0x00;
	/** 优柏设备- 剩余电流报警 **/
	public static byte YBDeviceleakageStatus = (byte) 0x00;

	/** 优柏设备- 状态 **/
	public static byte moduleStatus = (byte) 0x00;

	/** 优柏设备返回数据 **/
	public static String YBReturnData = "";
	/** 优柏设备发送数据 **/
	public static String YBSendData = "";

	// 下面是首次获取页面数据
	/** 首页上方铃铛-待处理报警 **/
	public static String HEAD_DCLBJ = "";
	/** 电子地图-今日报警数 **/
	public static String H110_JRBJS = "";
	/** 电子地图-今日故障数 **/
	public static String H110_JRGZS = "";
	/** 电子地图-本月报警数 **/
	public static String H110_BYBJS = "";
	/** 电子地图-本月故障数 **/
	public static String H110_BYGZS = "";
	/** 电子地图-未处理报警数 **/
	public static String H110_WCLBJS = "";
	/** 电子地图-未处理故障数 **/
	public static String H110_WCLGZS = "";

	/** 电子地图-在线数： **/
	public static String H110_ZXS = "";
	/** 电子地图-设备状态连接统计：离线数： **/
	public static String H110_LXS = "";
	/** 电子地图-正常数： **/
	public static String H110_ZCS = "";
	/** 电子地图-报警数： **/
	public static String H110_BJS = "";
	/** 电子地图-故障数： **/
	public static String H110_GZS = "";
	/** 电子地图-UPS闭合： **/
	public static String H110_USBBH = "";
	/** 电子地图-UPS断开： **/
	public static String H110_USBDK = "";
	/** 电子地图-市电闭合： **/
	public static String H110_SDBH = "";
	/** 电子地图-市电断开： **/
	public static String H110_SDDK = "";

	/** 实时监控-设备总数 **/
	public static String H210_SBZS = "";
	/** 实时监控-正常 **/
	public static String H210_ZC = "";
	/** 实时监控-报警 **/
	public static String H210_BJ = "";
	/** 实时监控-故障 **/
	public static String H210_GZ = "";

	/** 实时监控-获取列表中按钮状态，1为绿色正常，2为红色报警，3为橙色故障 **/
	public static String H210_MDdevicestatus = "";

	/** 报警管理-报警信息页面数据条数 **/
	public static String H310_getPaginationTotal = "";

	/** 报警管理-故障信息页面数据条数 **/
	public static String H320_getPaginationTotal = "";

	/** 日志管理-事件查询页面数据条数 **/
	public static String H810_getPaginationTotal = "";
	/** 日志管理-操作日志-软件操作日志页面数据条数 **/
	public static String H820_getPaginationTotal = "";
	/** 日志管理-操作日志-设备操作日志页面数据条数 **/
	public static String H830_getPaginationTotal = "";
	/** 日志管理-设备日志页面数据条数 **/
	public static String H840_getPaginationTotal = "";

	/***
	 * 首次登陆
	 * 
	 * @throws Exception
	 */
	public static void Login_sc() throws Exception {// 首次登陆

		// 登陆
		String username = ExcelOperate.getexcel_sheet0(6, 4);
		String pw = ExcelOperate.getexcel_sheet0(6, 5);
		action.sleep(1);
		MainStart.driver.findElement(By.xpath("//*[@type='text']")).clear();
		MainStart.driver.findElement(By.xpath("//*[@type='text']")).sendKeys(
				username);
		MainStart.driver.findElement(By.xpath("//*[@type='password']")).clear();
		MainStart.driver.findElement(By.xpath("//*[@type='password']"))
				.sendKeys(pw);
		MainStart.driver.findElement(By.id("loginBtn")).click();
	 //PM.normalize_texts_clicks("登 录");
	  //  action.isplay_text("智慧用电安全监控系统", 10);
		action.sleep(3);

	}

	/***
	 * 退出登录
	 * 
	 * @throws Exception
	 */
	public static void Logout() throws Exception {// 退出登录
		// action.sleep(1);
		// action.actions_moveto(By.xpath("//*[@title='退出系统']"));
		MainStart.driver.navigate().refresh();
		action.sleep(5);
		MainStart.driver.findElement(
				By.xpath("//*[@class='toolbar']//*[@title='退出系统']")).click();
		action.sleep(2);
		PM.normalize_texts_clicks("确定");
		action.sleep(1);
		action.JS_wait(200);

	}

	/***
	 * 登陆
	 * 
	 * 1/登录后验证登录是否成功，如果不成功停止测试 2如果已经登录就先退出
	 */
	public static void Login(String username, String password) throws Exception {// 登陆
		// 登陆
		MainStart.driver.navigate().refresh();
		action.sleep(2);
		if (action.isdisplay(By.xpath("//*[@title='退出系统']"), 10)) {
			Logout();
			action.sleep(1);
			action.JS_wait(200);
		}

		MainStart.driver.findElement(By.xpath("//*[@type='text']")).clear();
		MainStart.driver.findElement(By.xpath("//*[@type='text']")).sendKeys(
				username);
		MainStart.driver.findElement(By.xpath("//*[@type='password']")).clear();
		MainStart.driver.findElement(By.xpath("//*[@type='password']"))
				.sendKeys(password);
		//PM.normalize_texts_clicks("登 录");
		MainStart.driver.findElement(By.id("loginBtn")).click();//20201111

		action.sleep(1);
		action.JS_wait(200);
		if (action.isdisplay(By.xpath("//*[@title='退出系统']"), 10)) {

		} else {
			log.logWarn("登录失败:" + username);

		}

	}

	/** 点击搜索按钮 */
	public static void click_SS() throws Exception {//

		PM.normalize_texts_clicks("搜索");
		Thread.sleep(1000);
	}

	/** 点击清空按钮 */
	public static void click_QK() throws Exception {//

		PM.normalize_texts_clicks("清空");
		Thread.sleep(1000);
	}

	/** 点击确定按钮 */
	public static void click_QD() throws Exception {//

		PM.normalize_texts_clicks("确定");
		Thread.sleep(1000);
	}

	/** 点击删除按钮 */
	public static void click_SC() throws Exception {//

		PM.normalize_texts_clicks("删除");
		Thread.sleep(1000);
	}

	/** 点击新建按钮 */
	public static void click_XJ() throws Exception {//

		PM.normalize_texts_clicks("新建");
		Thread.sleep(1000);
	}

	/** 点击新增按钮 */
	public static void click_XZ() throws Exception {//

		PM.normalize_texts_clicks("新增");
		Thread.sleep(1000);
	}

	/** 点击修改按钮 */
	public static void click_XG() throws Exception {//

		PM.normalize_texts_clicks("修改");
		Thread.sleep(1000);
	}

	/** 点击全部按钮 */
	public static void click_QB() throws Exception {//

		PM.normalize_texts_clicks("全部");
		Thread.sleep(1000);
	}

	/** 点击编辑按钮 */
	public static void click_BJ() throws Exception {//

		PM.normalize_texts_clicks("编辑");
		Thread.sleep(1000);
	}

	/** 点击详情按钮 */
	public static void click_XQ() throws Exception {//
		PM.normalize_texts_clicks("详情");
		Thread.sleep(2000);

	}

	/** 点击启用按钮 */
	public static void click_QY() throws Exception {//

		PM.normalize_texts_clicks("启用");
		Thread.sleep(2000);
	}

	/** 点击禁用按钮 */
	public static void click_JY() throws Exception {//

		PM.normalize_texts_clicks("禁用");
		Thread.sleep(2000);
	}

	/** 点击提交按钮 */
	public static void click_TJ() throws Exception {//

		PM.normalize_texts_clicks("提交");
		Thread.sleep(4000);
	}

	/** 点击查询按钮 */
	public static void click_CX() throws Exception {//

		PM.normalize_texts_clicks("查询");
		Thread.sleep(2000);
	}

	/** 点击重置按钮 */
	public static void click_CZ() throws Exception {//

		PM.normalize_texts_clicks("重置");
		Thread.sleep(2000);
	}

	/** 点击导入按钮 */
	public static void click_DR() throws Exception {//

		PM.normalize_texts_clicks("导入");
		Thread.sleep(2000);
	}

	/** 点击导出按钮 */
	public static void click_DC() throws Exception {//

		PM.normalize_texts_clicks("导出");
		Thread.sleep(2000);
	}

	/** 点击取消按钮 */
	public static void click_QX() throws Exception {//

		PM.normalize_texts_clicks("取消");
		Thread.sleep(1000);
	}

	/** 清除操作：文本旁边的输入框内容 */
	public static void clear(String text) throws Exception {//
		action.isdisplay(
				By.xpath("//*[normalize-space(text()) and normalize-space(.)='"
						+ text + "']/following::input[1]"), 10);
		MainStart.driver.findElement(
				By.xpath("//*[normalize-space(text()) and normalize-space(.)='"
						+ text + "']/following::input[1]")).clear();
		Thread.sleep(100);
	}

	// /**清空输入框按钮*/
	// public static void XpathClear(By by) throws Exception {//
	// driver.findElement(by).clear();
	// }
	//

	/** 弹出窗口中的控件-点击操作：文本左边的单选框 ，如果有多个选择第一个 */
	public static void pop_click_textradio(String text) throws Exception {//

		MainStart.driver
				.findElements(
						By.xpath("//*[normalize-space(text()) and normalize-space(.)='"
								+ text
								+ "']/preceding::span[@class='button chk radio_false_full'][1]"))
				.get(0).click();
		Thread.sleep(100);
		action.JS_wait(200);
	}

	/** 弹出窗口中的控件点击操作：文本左边的span，如果有多个选择第一个 */
	public static void pop_click_textspan(String text) throws Exception {//

		MainStart.driver
				.findElements(
						By.xpath("//*[@role='tooltip']//*[normalize-space(text()) and normalize-space(.)='"
								+ text + "']/preceding::span[1]")).get(0)
				.click();
		Thread.sleep(100);
		action.JS_wait(200);
	}

	/**
	 * 
	 * 下拉框中的控件点击操作：点击下拉框中文字,如果有多个就依次点击
	 * 
	 * @throws InterruptedException
	 * @throws ParseException
	 * */
	public static void pop_click_text(String text) throws Exception {
		Thread.sleep(100);
		// if (action.isdisplay(By
		// .xpath(".//*[normalize-space(text()) and normalize-space(.)='"
		// + text + "']"), 5)) {
		int size = 0;

		try {
			action.isdisplay(
					By.xpath("//*[@role='tooltip' or @class='el-scrollbar']//*[normalize-space(text()) and normalize-space(.)='"
							+ text + "']"), 5);
			size = MainStart.driver
					.findElements(
							By.xpath("//*[@role='tooltip' or @class='el-scrollbar']//*[normalize-space(text()) and normalize-space(.)='"
									+ text + "']")).size();
			// log.logInfo("normalize_texts_clicks:size:"+size);
		} catch (Exception e) {
		}

		if (size == 0) {
			log.logWarn("页面中没有找到文字:" + text + ",可能不是必选项");
		}

		for (int i = 0; i < size; i++) {

			if (MainStart.driver
					.findElements(
							By.xpath("//*[@role='tooltip' or @class='el-scrollbar']//*[normalize-space(text()) and normalize-space(.)='"
									+ text + "']")).get(i).isDisplayed()) {
				Thread.sleep(100);
				try {
					MainStart.driver
							.findElements(
									By.xpath("//*[@role='tooltip' or @class='el-scrollbar']//*[normalize-space(text()) and normalize-space(.)='"
											+ text + "']")).get(i).click();
				} catch (Exception e) {
					// e.printStackTrace();
					// log.logWarn("有多个，当前没找到第："+ i+"个");
				}
				// break;
			}

		}

		Thread.sleep(100);
		action.JS_wait(200);
	}

	/**
	 * 菜单名称
	 * 
	 * @param text
	 * @throws Exception
	 */
	public static void menu_text(String text) throws Exception {//
		Thread.sleep(200);
		// *[@class="el-submenu__title"]//*[normalize-space(text()) and
		// normalize-space(.)='系统设置']
		action.JS_MoveWebElement(By
				.xpath("//*[@class='el-menu menu-bar-width']//*[normalize-space(text()) and normalize-space(.)='"
						+ text + "']"));
		action.isdisplay(
				By.xpath("//*[@class='el-menu menu-bar-width']//*[normalize-space(text()) and normalize-space(.)='"
						+ text + "']"), 10);
		MainStart.driver
				.findElement(
						By.xpath("//*[@class='el-menu menu-bar-width']//*[normalize-space(text()) and normalize-space(.)='"
								+ text + "']")).click();
		Thread.sleep(1000);
		action.JS_wait(200);
	}

	// /**
	// * 点击页面中的文字
	// *
	// * @param string
	// * @throws InterruptedException
	// * @throws ParseException
	// */
	// public static void pop_normalize_text_click(String string)
	// throws InterruptedException, ParseException {
	// action.JS_wait(200);
	// action.isdisplay(By
	// .xpath("//*[@class='el-dialog__body']//*[normalize-space(text()) and normalize-space(.)='"
	// + string + "']"), 5);
	// WebElement dr = driver.findElement(By
	// .xpath("//*[@class='el-dialog__body']//*[normalize-space(text()) and normalize-space(.)='"
	// + string + "']"));
	// dr.click();
	// Thread.sleep(100);
	// action.JS_wait(200);
	// }

	// /**
	// * 弹出窗口-页面中文字旁边的输入框
	// *
	// * @param string
	// * 页面中文字
	// * @param string2
	// * 输入的内如
	// * @throws InterruptedException
	// * @throws ParseException
	// */
	// public static void pop_normalize_input_sendkeys(String string, String
	// string2)
	// throws InterruptedException, ParseException {
	// action.JS_wait(200);
	// WebElement dr = driver.findElement(By
	// .xpath("//*[@class='el-dialog__body']//*[normalize-space(text()) and normalize-space(.)='"
	// + string + "']/following::input[1]"));
	// dr.clear();
	// dr.sendKeys(string2);
	// Thread.sleep(100);
	// action.JS_wait(200);
	// }

	// /**
	// * 弹出窗口-点击页面中文字旁边的输入框
	// *
	// * @param string
	// * 页面中文字
	// * @throws InterruptedException
	// * @throws ParseException
	// */
	// public static void pop_normalize_input_click(String string)
	// throws InterruptedException, ParseException {
	// action.JS_wait(200);
	// WebElement dr = driver.findElement(By
	// .xpath("//*[@class='el-dialog__body']//*[normalize-space(text()) and normalize-space(.)='"
	// + string + "'][1]/following::input[1]"));
	// dr.click();
	// Thread.sleep(100);
	// action.JS_wait(200);
	// }

	// /**
	// * 弹出窗口-页面中文字旁边的选择框el-dialog__body
	// *
	// * @param string
	// * 页面中文字
	// * @param string2
	// * 下拉框中选择的内容
	// * @throws InterruptedException
	// * @throws ParseException
	// */
	// public static void pop_normalize_select_sendkeys(String string, String
	// string2)
	// throws InterruptedException, ParseException {
	// action.JS_wait(200);
	// WebElement dr = driver.findElement(By
	// .xpath("//*[@class='el-dialog__body']//*[normalize-space(text()) and normalize-space(.)='"
	// + string + "']/following::select[1]"));
	// new Select(dr).selectByVisibleText(string2);
	// Thread.sleep(100);
	// action.JS_wait(200);
	// }

	// *[@class="el-dialog__body"]

	/**
	 * 
	 * 下拉框操作：文本后面的选择框,如果有多个就依次选择
	 * 
	 * @param string
	 *            页面中文字
	 * @param string2
	 *            下拉框中选择的内容
	 * @throws InterruptedException
	 * @throws ParseException
	 */
	public static void normalize_selects_sendkeys(String string, String string2)
			throws InterruptedException, ParseException {
		Thread.sleep(100);
		// if (action.isdisplay(By
		// .xpath(".//*[normalize-space(text()) and normalize-space(.)='"
		// + string + "']/following::select[1]"), 5)) {
		int size = 0;
		MainStart.driver.manage().timeouts()
				.implicitlyWait(2, TimeUnit.SECONDS);
		try {
			action.isdisplay(
					By.xpath(".//*[normalize-space(text()) and normalize-space(.)='"
							+ string + "']/following::select[1]"), 5);
			size = MainStart.driver
					.findElements(
							By.xpath(".//*[normalize-space(text()) and normalize-space(.)='"
									+ string + "']/following::select[1]"))
					.size();

		} catch (Exception e) {

		}
		MainStart.driver.manage().timeouts()
				.implicitlyWait(Config.appiumconfig_waitTime, TimeUnit.SECONDS);
		// log.logInfo("元素个数：" + size);
		if (size == 0) {
			log.logWarn("页面中没有找到select文字:" + string + ",可能不是必选项");
		}
		for (int i = 0; i < size; i++) {

			if (MainStart.driver
					.findElements(
							By.xpath(".//*[normalize-space(text()) and normalize-space(.)='"
									+ string + "']/following::select[1]"))
					.get(i).isDisplayed()) {
				WebElement dr = MainStart.driver
						.findElements(
								By.xpath(".//*[normalize-space(text()) and normalize-space(.)='"
										+ string + "']/following::select[1]"))
						.get(i);
				new Select(dr).selectByVisibleText(string2);
				break;
			}

		}

		Thread.sleep(100);
		action.JS_wait(200);
	}

	/**
	 * 
	 * 点击：页面文字,如果有多个就依次选择
	 * 
	 * @throws InterruptedException
	 * @throws ParseException
	 * */
	public static void normalize_texts_clicks(String text) throws Exception {
		Thread.sleep(100);
		// if (action.isdisplay(By
		// .xpath(".//*[normalize-space(text()) and normalize-space(.)='"
		// + text + "']"), 5)) {
		int size = 0;

		try {
			action.isdisplay(
					By.xpath(".//*[normalize-space(text()) and normalize-space(.)='"
							+ text + "']"), 5);
			size = MainStart.driver
					.findElements(
							By.xpath(".//*[normalize-space(text()) and normalize-space(.)='"
									+ text + "']")).size();
			// log.logInfo("normalize_texts_clicks:size:"+size);
		} catch (Exception e) {
		}

		if (size == 0) {
			log.logWarn("页面中没有找到文字:" + text + ",可能不是必选项");
		}

		for (int i = 0; i < size; i++) {

			if (MainStart.driver
					.findElements(
							By.xpath(".//*[normalize-space(text()) and normalize-space(.)='"
									+ text + "']")).get(i).isDisplayed()) {
				Thread.sleep(100);
				try {
					MainStart.driver
							.findElements(
									By.xpath(".//*[normalize-space(text()) and normalize-space(.)='"
											+ text + "']")).get(i).click();
				} catch (Exception e) {
					// e.printStackTrace();
					// log.logWarn("有多个，当前没找到第："+ i+"个");
				}
				// break;
			}

		}

		Thread.sleep(100);
		action.JS_wait(200);
	}

	/**
	 * 点击：页面中文字后面的输入框,如果有多个就依次点击
	 * 
	 * @param string
	 *            页面中文字
	 * @throws InterruptedException
	 * @throws ParseException
	 */
	public static void normalize_inputs_clicks(String string)
			throws InterruptedException, ParseException {
		Thread.sleep(100);
		// if (action.isdisplay(By
		// .xpath(".//*[normalize-space(text()) and normalize-space(.)='"
		// + string + "']/following::input[1]"), 5)) {
		int size = 0;
		MainStart.driver.manage().timeouts()
				.implicitlyWait(2, TimeUnit.SECONDS);
		try {
			action.isdisplay(
					By.xpath(".//*[normalize-space(text()) and normalize-space(.)='"
							+ string + "']/following::input[1]"), 5);
			size = MainStart.driver
					.findElements(
							By.xpath(".//*[normalize-space(text()) and normalize-space(.)='"
									+ string + "']/following::input[1]"))
					.size();

		} catch (Exception e) {

		}
		MainStart.driver.manage().timeouts()
				.implicitlyWait(Config.appiumconfig_waitTime, TimeUnit.SECONDS);
		if (size == 0) {
			log.logWarn("页面中没有找到input旁的文字:" + string + ",可能不是必选项");
		}
		for (int i = 0; i < size; i++) {

			if (MainStart.driver
					.findElements(
							By.xpath(".//*[normalize-space(text()) and normalize-space(.)='"
									+ string + "']/following::input[1]"))
					.get(i).isDisplayed()) {
				try {
					MainStart.driver
							.findElements(
									By.xpath(".//*[normalize-space(text()) and normalize-space(.)='"
											+ string + "']/following::input[1]"))
							.get(i).click();
				} catch (Exception e) {
					// log.logWarn("有多个，当前没找到第："+ i+"个");
				}
				// break;
			}

		}
		Thread.sleep(100);
		action.JS_wait(200);
	}

	/**
	 * 输入：文本后面的输入框,如果有多个就依次点击
	 * 
	 * @param string
	 *            页面中文字
	 * @param string2
	 *            输入的内容
	 * @throws InterruptedException
	 * @throws ParseException
	 */
	public static void normalize_inputs_sendkeys(String string, String string2)
			throws InterruptedException, ParseException {
		Thread.sleep(100);
		// if (action.isdisplay(By
		// .xpath(".//*[normalize-space(text()) and normalize-space(.)='"
		// + string + "']/following::input[1]"), 5)) {
		int size = 0;
		MainStart.driver.manage().timeouts()
				.implicitlyWait(2, TimeUnit.SECONDS);
		try {
			action.isdisplay(
					By.xpath(".//*[normalize-space(text()) and normalize-space(.)='"
							+ string + "']//following::input[1]"), 5);
			size = MainStart.driver
					.findElements(
							By.xpath(".//*[normalize-space(text()) and normalize-space(.)='"
									+ string + "']//following::input[1]"))
					.size();

			// log.logInfo("normalize_inputs_sendkeys:size:"+size);
		} catch (Exception e) {

		}
		MainStart.driver.manage().timeouts()
				.implicitlyWait(Config.appiumconfig_waitTime, TimeUnit.SECONDS);
		if (size == 0) {
			log.logWarn("页面中没有找到input旁的文字:" + string + ",可能不是必选项");
		}
		for (int i = 0; i < size; i++) {

			if (MainStart.driver
					.findElements(
							By.xpath(".//*[normalize-space(text()) and normalize-space(.)='"
									+ string + "']//following::input[1]"))
					.get(i).isDisplayed()) {

				try {
					MainStart.driver
							.findElements(
									By.xpath(".//*[normalize-space(text()) and normalize-space(.)='"
											+ string
											+ "']//following::input[1]"))
							.get(i).clear();
					MainStart.driver
							.findElements(
									By.xpath(".//*[normalize-space(text()) and normalize-space(.)='"
											+ string
											+ "']//following::input[1]"))
							.get(i).sendKeys(string2);
				} catch (Exception e1) {

				}
				// break;
			}

		}

		Thread.sleep(100);
		action.JS_wait(200);
	}

	/***
	 * 返回首页（每个用例的第一个步骤）
	 * 
	 * @throws Exception
	 *             1、判断是否登录状态，如未登录重新登。
	 * @throws Exception
	 *             2、判断是否有弹出窗口，如果有就通过刷新页面的方式去掉弹出框。
	 * @throws Exception
	 *             3、判断首页菜单是否存在，如不存在就刷新再登录一次系统，否则停止执行。
	 */
	public static void return_sy() throws Exception {// 返回首页

		if (action.isdisplay(By.xpath("//*[@type='password']"), 1)) {
			String username = ExcelOperate.getexcel_sheet0(6, 4);
			String pw = ExcelOperate.getexcel_sheet0(6, 5);
			PM.Login(username, pw);
			action.sleep(1);
			action.JS_wait(200);
		}
		try {
			if (action.isdisplay(By.xpath("//*[@title='退出系统']"), 10)) {

				MainStart.driver.navigate().refresh();
				action.sleep(1);
				action.JS_wait(200);

			}

		} catch (Exception e) {
			e.printStackTrace();
			log.logWarn("无法看到首页，刷新再登录一次！");
			MainStart.driver.navigate().refresh();
			action.sleep(1);
			String username = ExcelOperate.getexcel_sheet0(6, 4);
			String pw = ExcelOperate.getexcel_sheet0(6, 5);
			PM.Login(username, pw);
			if (action.isdisplay(By.xpath("//*[@title='退出系统']"), 2)) {
			} else {
				log.logWarn("再登录也不能正常进入首页，退出执行！");
				System.exit(0);
			}

		}
		Thread.sleep(100);
		action.JS_wait(10);
	}

	/**
	 * 获取页面表单下面数据条数（例子：共7条）
	 * 
	 * @param n
	 *            第n个
	 * @return
	 */
	public static String getPaginationTotal(int n) {
		String total = "";
		try {

			String pagination__total = MainStart.driver
					.findElements(
							By.xpath("//*[@class='el-pagination__total']"))
					.get(n).getText();
			// log.logInfo("pagination__total:"+pagination__total);
			int length = pagination__total.trim().length();
			total = (String) pagination__total.subSequence(2, length - 2);
			// log.logInfo("total:"+total);
			return total;
		} catch (Exception e) {
			e.printStackTrace();
			return total;
		}

	}

	/**
	 * 表单中,找到含有特定文字的那一行,点击行按钮
	 * 
	 * @param text
	 *            特定文字
	 * @param buttonType
	 *            一行中的第几个按钮(角色管理中对应的是:按钮类型：1是权限，2是修改，3是删除)
	 * */
	public static void click_TableRowButton(String text, String buttonType) {
		try {
			String str = new StringBuffer(
					"//*[@class='el-table__body']//*[normalize-space(text())  and  normalize-space(.)='")
					.append(text).append("']").append("/following::button[")
					.append(buttonType).append("]").toString();

			int size = MainStart.driver.findElements(By.xpath(str)).size();
			for (int i = 0; i < size; i++) {
				action.sleep(1);
				if (MainStart.driver.findElements(By.xpath(str)).get(i)
						.isDisplayed()) {
					MainStart.driver.findElements(By.xpath(str)).get(i).click();
				}
				action.sleep(1);
			}
		} catch (Exception e) {
			log.logWarn("click_TableRowButton方法未点击到按钮!");
			e.printStackTrace();
		}
	}

	/**
	 * 点击菜单栏
	 * @param text
	 */
	public static void clickMenu(String text){
		MainStart.driver.findElement(By.xpath("//a[@data-text='" +text+ "']")).click();
	}

	/**
	 * 用例执行结束，返回首页
	 */
	public static void returnMenu(){
		action.JS_refresh();
		action.sleep(1);
	}




	/**
	 * 在带搜索图标的搜索框搜索并选中指定内容
	 * @param text  输入框前的文本
	 * @param text2 要搜索并选中的内容
	 */
	public static void search_select(String text, String text2) throws Exception{
		try {
			//PM.normalize_inputs_clicks(text);
			PM.input_click(text);
			MainStart.driver.findElement(By.xpath("//input[@ng-model='searchValue']")).sendKeys(text2);
			MainStart.driver.findElement(By.xpath("//i[@class='glyphicon glyphicon-search']")).click();
			PM.normalize_texts_clicks(text2);
			//PM.normalize_texts_clicks("确定")
			//action.findElements_click(By.xpath("//button[@class='hy-btn hy-btn-primary hy-bg hy-theme-border hy-bg-hover2 ng-scope']"),0);
			action.findElement_click(By.xpath("//input[@ng-model='searchValue']/ancestor::*//div[@ng-controller='BaseModalController']//button[@ng-click='save()']"));
		}
		catch (Exception e ){
			log.logWarn("search_select执行失败！");
			e.printStackTrace();
		}
	}

	/**
	 * option 下拉选择 (主要用于产品基础信息模块)
	 * text :下拉选项中的内容,如果有多个，依次点击
	 */
	public static void option_select(String text){
		int size=0;
		try {
			action.findElement_click(
					By.xpath("//option[normalize-space(text()) and normalize-space(.)='"
							+ text + "']"));
			size = MainStart.driver
					.findElements(
							By.xpath("//option[normalize-space(text()) and normalize-space(.)='"
									+ text + "']")).size();

		} catch (InterruptedException e) {
			if (size <= 0) {
				log.logWarn("下拉选择中没有找到文字:" + text + ",请重新定位");
			}else{
				log.logInfo("找到"+size+"个"+text+"==========");

				for (int i = 0; i < size; i++) {

					if (MainStart.driver
							.findElements(
									By.xpath("//option[normalize-space(text()) and normalize-space(.)='"
											+ text + "']"))
							.get(i).isDisplayed()) {

						try {
							MainStart.driver
									.findElements(
											By.xpath("//option[normalize-space(text()) and normalize-space(.)='"
													+ text + "']"))
									.get(i).clear();
							MainStart.driver
									.findElements(
											By.xpath("//option[normalize-space(text()) and normalize-space(.)='"
													+ text + "']"))
									.get(i).click();
						} catch (Exception e1) {

						}
						// break;
					}
				}
			}

			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}

	/**
	 * 在输入框输入文本
	 * @param text 输入框前的标题
	 * @param text2 要输入的文本
	 */
	public static void input_sendKeys(String text, String text2) throws Exception{
		//MainStart.driver.findElement(By.xpath("//span[contains(.,'"+ text +"')]/parent::*/parent::*//input")).sendKeys(text2);
		try {
			MainStart.driver.findElement(By.xpath("//span[contains(.,'" + text + "')]/parent::div//input")).sendKeys(text2);
		}catch(Exception e){

		}
	}

	/**
	 * 输入框前的标题
	 * @param text
	 */
	public static void input_click(String text) throws Exception{
		MainStart.driver.findElement(By.xpath("//span[contains(.,'"+text  +"')]/parent::div//input")).click();
	}

	/**
	 * 下拉多选框
	 * @param text 输入框前的标题
	 * @param text2  要选择的项，多个则用 & 分开
	 */
	public static void multiple_select(String text, String text2) throws Exception{
		input_click(text);
		String strings[] = text2.split("&");
		for (int i=0; i<strings.length; i++){
			//System.out.println(strings[i]);
			MainStart.driver.findElement(By.xpath("//span[contains(.,'"+text+"')]/parent::div//li[@title='"+ strings[i] +"']")).click();
			action.sleep(1);
		}
		//隐藏下拉多选框
		((JavascriptExecutor) MainStart.driver)
				.executeScript("document.querySelector('.selectList').style.display = 'none'");
	}

	/**
	 * 点击指定表格标题栏中的操作按钮
	 * @param text 表格标题
	 * @param text2 操作按钮名称
	 */
	public static void click_operation_button_top_right(String text,String text2) throws Exception{
		try {
			MainStart.driver.findElement(By.xpath("//span[contains(.,'"+text+"')]/parent::*//button[contains(.,'"+text2+"')]")).click();
			action.sleep(1);
		}catch(Exception e){

		}
	}

	/**
	 * 点击表格指定行后的操作按钮
	 * @param text  行标识文本，可以是售前单号，货物编号等
	 * @param text2  操作按钮名称
	 */
	public static void click_operation_button(String text, String text2) throws Exception{
		try {
			MainStart.driver.findElement(By.xpath("//span[contains(.,'" + text + "')]/ancestor::tr//button[contains(.,'"+ text2 +"')]")).click();
			action.sleep(1);
		}catch(Exception e){

		}
	}


	/**
	 * 点击表格指定行后的操作按钮
	 * @param text  行标识文本，可以是售前单号，货物编号等
	 * @param text2  行标识文本2，可以是售前单号，货物编号等
	 * @param text3  操作按钮名称
	 *
	 */
	public static void click_operation_button(String text, String text2, String text3) throws Exception{
		try {
			MainStart.driver.findElement(By.xpath("//span[contains(.,'"+text+"')]/parent::*/parent::*//span[contains(.,'"+text2+"')]/ancestor::tr//button[contains(.,'"+text3+"')]")).click();
			action.sleep(1);
		}catch(Exception e){

		}
	}

	/**
	 * 判断表格内有没有行（根据下方统计）
	 * 待调度安排页面适用
	 * @param text
	 */
	public static boolean judge_form(String text) throws Exception{
		try{
			String string = MainStart.driver.findElement(By.xpath("//div[contains(.,'需调度货物') and @class='hy-table-wrap']//li[contains(.,'总共')]")).getText();
			String string1=string.substring(3,4);
			int sum =Integer.parseInt(string1);
			if(sum>0){
				return true;
			}
			else{
				return false;
			}
		}catch (Exception e){

		}
		finally {
			return false;
		}
	}

	/**
	 * 点击表格左上角的全选框
	 * 待调度安排页面适用
	 * @param text 表格左上角标题
	 */
	public static void select_form_all(String text) throws Exception{
		try {
			MainStart.driver.findElement(By.xpath("//div[contains(.,'" + text + "') and @class='hy-table-wrap']//input[@ng-change='selectAllClick()']")).click();
			action.sleep(1);
		}catch (Exception e ){

		}
	}


	public static void click_text(String text) throws Exception{
		MainStart.driver.findElement(By.xpath("//*[contains(.,'" + text + "')]"));
		action.sleep(1);
	}

	/**
	 * 点击填写表格的确定按钮
	 * 用在报错 element is not clickable 的情况
	 */
	public static void click_form_QD() throws Exception{
//		MainStart.driver.findElement(By.xpath("//button[@ng-click='formSave()']")).click();
		action.JS_EXE("arguments[0].click();",By.xpath("//button[@ng-click='formSave()']"));
		action.sleep(2);
	}

}
