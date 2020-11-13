package com.haoyun.automationtesting.test.aadomain;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Navigation;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.haoyun.automationtesting.framework.Config;
import com.haoyun.automationtesting.framework.CreateWordTest;
import com.haoyun.automationtesting.framework.ExcelOperate;
import com.haoyun.automationtesting.framework.FileOperate;
import com.haoyun.automationtesting.framework.action;
import com.haoyun.automationtesting.framework.log;
import com.haoyun.automationtesting.framework.aop.LogAdvice;
import com.haoyun.automationtesting.framework.aop.LogAdviceSecond;
import com.haoyun.automationtesting.page.PM;

/***
 * 主程序入口,自动执行在com.haoyun.automationtesting.test下的所有类
 * 
 * @author lisheng
 *
 */

public class MainStart {
	public static String starttime = "00000000-000000";// 执行开始时间
	public static String endtime = "00000000-000000"; // 执行完成时间
	public static WebDriver driver;

	public static String timenow = action.time();// 定义时间随机数

	// public static String jgmc="省联社机构"+timenow;//定义新增机构名称
	// public static String rymc="自动化"+timenow;//定义新增人员名称
	// public static String gh = new SimpleDateFormat("HHmmss").format(new
	// Date());//工号

	public WebDriver getDriver() {
		return driver;
	}

	@Test(priority = 0)
	public static void main() {
		System.out.printf("main,Thrad Id : %s%n", Thread.currentThread()
				.getId());

		LogAdvice.mainfile();
		try {// 执行次数,但结果报告只保存执行的最后一次.
			String id = ExcelOperate.getexcel_sheet0(3, 2);
			if (!id.isEmpty()) {

				int index = Integer.valueOf(id);
				if (index <= 0) {

				} else {
					for (int i = 0; i < index; i++) {
						log.logWarn("再次执行第" + index
								+ "次--------------------------------");
						LogAdvice.mainfile();
						log.logWarn("第" + index
								+ "次执行完成--------------------------------");
						endtime = new SimpleDateFormat("yyyyMMdd-HHmmss")
								.format(new Date());
						ExcelOperate.cellexcel_main();
						if (ExcelOperate.getexcel_sheet0(8, 4).contains("是")) {// excel中配置为“是”，执行
							CreateWordTest.ReportWord();
						}
					}
				}
			}

		} catch (Exception e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}

		try {
			// log.logInfo(ExcelOperate.getexcel_sheet0(8, 2));
			if (ExcelOperate.getexcel_sheet0(8, 2).contains("是")) {// excel中配置为“是”，执行
				LogAdviceSecond.mainfile();// 执行出错的用例，再次执行一次。
			}
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}

		try {
			ExcelOperate.writeExcelReprot();// 写入结果到excel每一个测试用例行
		} catch (IOException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}//

	}

	@BeforeTest
	public static void init() throws InterruptedException, Exception {

		try {
			FileOutputStream testfile = new FileOutputStream("log\\Sit.log");// 清空sit。log文件中日志内容
			String du = ExcelOperate.getexcel_sheet0(7, 4);
			if (du.equals("执行全部用例")) {
				ExcelOperate.cellexcelinit_all();//
			} else if (du.equals("全部不执行")) {
				ExcelOperate.cellexcelinit_noall();//
			} else if (du.equals("当前默认")) {
				ExcelOperate.cellexcelinit_sheet();// 初始化excel中除第一个sheet页后的所有sheet页中的“运行时间”和“是否通过”
			} else {
				ExcelOperate.cellexcelinit_sheet();// 初始化excel中除第一个sheet页后的所有sheet页中的“运行时间”和“是否通过”
			}

			FileOperate.copyFolder("snapshot", "snapshotbak");// 复制截图文件到备份文件夹
			FileOperate.delAllFile("snapshot");// 删除本文件夹下的截图文件
			Thread.sleep(500);

			// AppiumTools appiumtools = new AppiumTools();
			// appiumtools.startAppium(Config.appiumpath);// 启动appium服务
			// Thread.sleep(5000);
			//
			webStart();// 启动web

		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	public static void webStart() throws InterruptedException, Exception {

		try {
			String llq = ExcelOperate.getexcel_sheet0(4, 2);

			String weburl = ExcelOperate.getexcel_sheet0(6, 2);
			System.out.println("\"" + weburl.trim() + "\"");
			starttime = new SimpleDateFormat("yyyyMMdd-HHmmss")
					.format(new Date());

			if (llq.trim().equals("火狐")) {
				// System.setProperty ( "webdriver.firefox.bin" ,
				// "C:/Program Files (x86)/Mozilla Firefox/firefox.exe" );
				String staturl = System.setProperty("webdriver.gecko.driver",
						ExcelOperate.getexcel_sheet0(5, 2));
				if (staturl == "null") {
					log.logInfo("浏览器打开状态:" + staturl + "浏览器配置地址错误");
				}

				driver = new FirefoxDriver();
				Navigation navigation = driver.navigate();
				navigation.to(weburl.trim());

				driver.manage()
						.timeouts()
						.implicitlyWait(Config.appiumconfig_waitTime,
								TimeUnit.SECONDS);
				driver.manage().window().maximize();
				// driver.get(weburl.trim());
				log.logInfo("火狐浏览器打开，web连接成功");
			}
			if (llq.trim().equals("IE")) {
				// System.setProperty("webdriver.ie.driver",
				// "C:/Program Files (x86)/Internet Explorer/IEDriverServer.exe");
				System.setProperty("webdriver.ie.driver",
						"C:/Program Files (x86)/Internet Explorer/iedriver_32.exe");
				driver = new InternetExplorerDriver();
				driver.get(weburl.trim());
				driver.manage()
						.timeouts()
						.implicitlyWait(Config.appiumconfig_waitTime,
								TimeUnit.SECONDS);
				driver.manage().window().maximize();

				log.logInfo("IE浏览器打开，web连接成功");
			}

			if (llq.trim().equals("谷歌")) {

				// System.setProperty("webdriver.chrome.driver",
				// "C:/Program Files/Chrome/chromedriver.exe");
//				System.setProperty("webdriver.chrome.driver",
//						"C:/Users/Administrator/AppData/Local/Google/Chrome/Application/chromedriver.exe");
//				ChromeOptions options = new ChromeOptions();
//				options.setBinary("C:/Users/Administrator/AppData/Local/Google/Chrome/Application/Chrome.exe");
				
				System.setProperty("webdriver.chrome.driver",
						ExcelOperate.getexcel_sheet0(5, 2) + "chromedriver.exe");
				ChromeOptions options = new ChromeOptions();
				options.setBinary(ExcelOperate.getexcel_sheet0(5, 2)
						+ "chrome.exe");
				// options.addArguments("--headless");
				// options.addArguments("--silent");
				// options.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR,
				// UnexpectedAlertBehaviour.IGNORE);
				// options.addArguments("screenshot");// 每打开一个页面就截图

				// 1) NONE: 当html下载完成之后，不等待解析完成，selenium会直接返回
				// (2) EAGER:
				// 要等待整个dom树加载完成，即DOMContentLoaded这个事件完成，仅对html的内容进行下载解析
				// (3) NORMAL:
				// 即正常情况下，selenium会等待整个界面加载完成（指对html和子资源的下载与解析,如JS文件，图片等，不包括ajax）
				options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
				driver = new ChromeDriver(options);
				// driver = new ChromeDriver();

				driver.manage()
						.timeouts()
						.implicitlyWait(Config.appiumconfig_waitTime,
								TimeUnit.SECONDS);// 定位对象时给30s 的时间, 如果30s
													// 内还定位不到则抛出异常

				driver.manage().timeouts()
						.pageLoadTimeout(60, TimeUnit.SECONDS);// 页面加载超时时间设置为5s

				// driver.manage().timeouts().setScriptTimeout(30,
				// TimeUnit.SECONDS);//异步脚本的超时时间设置成3s
				driver.manage().deleteAllCookies();// 清除浏览器所有缓存
				driver.manage().window().maximize();// 浏览器最大化
				driver.get(weburl.trim());// 打开被测地址

				log.logInfo("谷歌浏览器打开，web连接成功");
			}

			Thread.sleep(500);

		} catch (Exception e) {
			e.printStackTrace();
			log.logInfo("web连接失败");
			System.exit(0);

		}

		PM.Login_sc();// 首次登陆

	}

	@AfterTest
	public static void after() throws Exception {
		try {
			endtime = new SimpleDateFormat("yyyyMMdd-HHmmss")
					.format(new Date());

			ExcelOperate.cellexcel_main();

		} catch (Exception e) {
			e.printStackTrace();

		}
		// AppiumTools appiumtools = new AppiumTools();
		// appiumtools.closeAppium();
		try {
			driver.quit();
		} catch (Exception e) {
			e.printStackTrace();

		}
		// System.out.println("全部用例执行完成");
		try {
			log.logInfo("全部用例执行完成");
			if (ExcelOperate.getexcel_sheet0(8, 4).contains("是")) {// excel中配置为“是”，执行
				log.logInfo("正在生成word版报告。。。。。。");
				CreateWordTest.ReportWord();
				log.logInfo("word版报告生成完成。请到工程目录下/resource下面查看");
			} else {
				log.logInfo("Excel结果统计页中已配置为不生成word版报告！本次执行无报告产生");
			}

		} catch (Exception e) {
			e.printStackTrace();

		}

	}
}