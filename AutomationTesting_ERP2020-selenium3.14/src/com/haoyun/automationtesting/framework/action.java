package com.haoyun.automationtesting.framework;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.haoyun.automationtesting.test.aadomain.MainStart;

public class action {

	public static WebDriver driver;

	public action(WebDriver driver) {
		super();
		// action.driver = driver;
		// driver = mainStart.driver;
	}

	public action() {
		driver = MainStart.driver;
		// TODO 自动生成的构造函数存根

	}

	/***
	 * 点击，by方式
	 * 
	 * @param by
	 * @throws InterruptedException
	 * @throws Exception
	 */

	public void tap(By by) throws InterruptedException, Exception {
		try {
			MainStart.driver.findElement(by).click();

		} catch (Exception e) {
			// log.logError("元素未找到！");
			e.printStackTrace();

		}

	}

	/***
	 * 输入，by方式
	 * 
	 * @param by
	 *            by对象
	 * @param string3
	 *            输入的数据
	 */
	public static void sendkey_by(By by, String string3) {

		WebElement obj = MainStart.driver.findElement(by);
		obj.sendKeys(string3);

	}

	/**
	 * 获取by对象的text值
	 * 
	 * @param by
	 * @return
	 */
	public static String getText(By by) {
		WebElement obj = MainStart.driver.findElement(by);
		return obj.getText();
	}

	/***
	 * 输入，xpath方式
	 * 
	 * @param string1
	 *            :class值
	 * @param string2
	 *            :name值
	 * @param string3
	 *            ：输入的数据
	 */

	public static void sendkey_xpath_text(String string1, String string2,
			String string3) {
		WebElement obj = MainStart.driver.findElement(By.xpath("//" + string1
				+ "[contains(@text,'" + string2 + "')]"));
		obj.sendKeys(string3);
	}

	/**
	 * 输入 WebElement ae
	 * 
	 * @param ae
	 *            WebElement对象
	 * @param string1
	 *            输入的数据
	 */
	public static void sendkey_AndroidElement(WebElement ae, String string1) {
		ae.sendKeys(string1);
	}

	/***
	 * 点击,xpath方式
	 * 
	 * @throws InterruptedException
	 * @throws ParseException
	 */

	// @Resource
	public static void tap_xpath_text(String string1, String string2)
			throws InterruptedException, ParseException {

		WebElement dr = MainStart.driver.findElement(By.xpath("//" + string1
				+ "[contains(@text,'" + string2 + "')]"));
		dr.click();
		// driver.tap(1, dr, 200);
	}

	/***
	 * 点击，by
	 * 
	 * @param by对象
	 */
	public static void tap_by(By by) {
		WebElement dr = MainStart.driver.findElement(by);
		// driver.tap(1, dr, 200);
		dr.click();
	}

	/***
	 * 点击，AndroidElement对象
	 * 
	 * @param AndroidElement
	 *            对象
	 */
	public static void tap_AndroidElement(WebElement ae) {
		// AndroidElement dr = driver.findElement(by);
		// driver.tap(1, ae, 200);
		ae.click();
	}

	/**
	 * \ 动态等待判断
	 * 
	 * @param by
	 *            byduix
	 * @param time
	 *            等待次数
	 * @throws InterruptedException
	 */
	public static void isdisplay_wait(By by, int time)
			throws InterruptedException {
		for (int i = 0; i < time; i++) {
			if (!MainStart.driver.findElement(by).isDisplayed()) {
				// action.sleep(1);
				MainStart.driver.findElement(by).notifyAll();
				log.logInfo("false次数" + i);
			} else {
				break;
			}
		}
	}

	/**
	 * 显示等待页面元素出现，参数为页面元素xpath定位表达式 presence_of_element_located：
	 * 当我们不关心元素是否可见，只关心元素是否存在在页面中。 visibility_of_element_located：
	 * 当我们需要找到元素，并且该元素也可见。
	 * 
	 * @param driver
	 * @param xpathExpression
	 * @param text
	 * @param timeOutInSeconds
	 */
	public static void iswaitvisibilityOfElementLocated(By by,
			int timeOutInSeconds) {
		WebDriverWait waitElement = new WebDriverWait(MainStart.driver,
				timeOutInSeconds);
		// Boolean element =
		// waitElement.until(ExpectedConditions.textToBePresentInElementValue(By.xpath(xpathExpression),
		// text));
		waitElement.until(ExpectedConditions.visibilityOfElementLocated(by));
		// waitElement.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
		// waitElement.ignoring(UnhandledAlertException.class).until(ExpectedConditions.visibilityOfElementLocated(by));
		// return element;
	}

	/**
	 * 点击by对象多个点第一个，如果不存在一直点击，直到30秒后超时
	 * 
	 * @param by
	 * @throws InterruptedException
	 */
	public static void iswaitclicks(By by) throws InterruptedException {
		try {
			MainStart.driver.findElements(by).get(0).click();
		} catch (Exception e) {
			Thread.sleep(2000);
			try {
				MainStart.driver.findElements(by).get(0).click();
			} catch (Exception e1) {
				Thread.sleep(2000);
				try {
					MainStart.driver.findElements(by).get(0).click();
				} catch (Exception e2) {
					Thread.sleep(2000);
					try {
						MainStart.driver.findElements(by).get(0).click();
					} catch (Exception e3) {
						Thread.sleep(2000);
						try {
							MainStart.driver.findElements(by).get(0).click();
						} catch (Exception e4) {
							Thread.sleep(2000);
							try {
								MainStart.driver.findElements(by).get(0)
										.click();
							} catch (Exception e5) {
								Thread.sleep(2000);
								try {
									MainStart.driver.findElements(by).get(0)
											.click();
								} catch (Exception e6) {
									Thread.sleep(2000);
									try {
										MainStart.driver.findElements(by)
												.get(0).click();
									} catch (Exception e7) {
										Thread.sleep(2000);
										try {
											MainStart.driver.findElements(by)
													.get(0).click();
										} catch (Exception e8) {
											Thread.sleep(4000);
											try {
												MainStart.driver
														.findElements(by)
														.get(0).click();
											} catch (Exception e9) {
												Thread.sleep(5000);
												try {
													MainStart.driver
															.findElements(by)
															.get(0).click();
												} catch (Exception e10) {
													Thread.sleep(5000);

													MainStart.driver
															.findElements(by)
															.get(0).click();

												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}

	/**
	 * 点击by对象，如果不存在一直点击，直到30秒后超时
	 * 
	 * @param by
	 * @throws InterruptedException
	 */
	public static void iswaitclick(By by) throws InterruptedException {
		try {
			MainStart.driver.findElement(by).click();
		} catch (Exception e) {
			Thread.sleep(2000);
			try {
				MainStart.driver.findElement(by).click();
			} catch (Exception e1) {
				Thread.sleep(2000);
				try {
					MainStart.driver.findElement(by).click();
				} catch (Exception e2) {
					Thread.sleep(2000);
					try {
						MainStart.driver.findElement(by).click();
					} catch (Exception e3) {
						Thread.sleep(2000);
						try {
							MainStart.driver.findElement(by).click();
						} catch (Exception e4) {
							Thread.sleep(2000);
							try {
								MainStart.driver.findElement(by).click();
							} catch (Exception e5) {
								Thread.sleep(2000);
								try {
									MainStart.driver.findElement(by).click();
								} catch (Exception e6) {
									Thread.sleep(2000);
									try {
										MainStart.driver.findElement(by)
												.click();
									} catch (Exception e7) {
										Thread.sleep(2000);
										try {
											MainStart.driver.findElement(by)
													.click();
										} catch (Exception e8) {
											Thread.sleep(4000);
											try {
												MainStart.driver
														.findElement(by)
														.click();
											} catch (Exception e9) {
												Thread.sleep(5000);
												try {
													MainStart.driver
															.findElement(by)
															.click();
												} catch (Exception e10) {
													Thread.sleep(5000);

													MainStart.driver
															.findElement(by)
															.click();

												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}

	/**
	 * 输入by对象，如果不存在一直点击，直到30秒后超时
	 * 
	 * @param by
	 * @throws InterruptedException
	 */
	public static void iswaitsendkeys(By by, String text)
			throws InterruptedException {
		try {
			MainStart.driver.findElement(by).sendKeys(text);
		} catch (Exception e) {
			Thread.sleep(2000);
			try {
				MainStart.driver.findElement(by).sendKeys(text);
			} catch (Exception e1) {
				Thread.sleep(2000);
				try {
					MainStart.driver.findElement(by).sendKeys(text);
				} catch (Exception e2) {
					Thread.sleep(2000);
					try {
						MainStart.driver.findElement(by).sendKeys(text);
					} catch (Exception e3) {
						Thread.sleep(2000);
						try {
							MainStart.driver.findElement(by).sendKeys(text);
						} catch (Exception e4) {
							Thread.sleep(2000);
							try {
								MainStart.driver.findElement(by).sendKeys(text);
							} catch (Exception e5) {
								Thread.sleep(2000);
								try {
									MainStart.driver.findElement(by).sendKeys(
											text);
								} catch (Exception e6) {
									Thread.sleep(2000);
									try {
										MainStart.driver.findElement(by)
												.sendKeys(text);
									} catch (Exception e7) {
										Thread.sleep(2000);
										try {
											MainStart.driver.findElement(by)
													.sendKeys(text);
										} catch (Exception e8) {
											Thread.sleep(4000);
											try {
												MainStart.driver
														.findElement(by)
														.sendKeys(text);
											} catch (Exception e9) {
												Thread.sleep(5000);
												try {
													MainStart.driver
															.findElement(by)
															.sendKeys(text);
												} catch (Exception e10) {
													Thread.sleep(5000);

													MainStart.driver
															.findElement(by)
															.sendKeys(text);

												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}

	/***
	 * 判断元素是否存在
	 * 
	 * @param by
	 *            元素定位
	 * @param time
	 *            等待时间（秒）
	 * @return
	 * @throws InterruptedException
	 */
	public static boolean isdisplay(By by, int time)
			throws InterruptedException {
		boolean isdp = false;
		try {
			MainStart.driver.manage().timeouts()
					.implicitlyWait(time, TimeUnit.SECONDS);
			isdp = MainStart.driver.findElement(by).isDisplayed();

			MainStart.driver
					.manage()
					.timeouts()
					.implicitlyWait(Config.appiumconfig_waitTime,
							TimeUnit.SECONDS);

		} catch (Exception e) {
			isdp = false;
			try {
				MainStart.driver
						.manage()
						.timeouts()
						.implicitlyWait(Config.appiumconfig_waitTime,
								TimeUnit.SECONDS);
			} catch (Exception e1) {

			}
			Thread.sleep(100);
			// log.logInfo("isdp:"+isdp);
			return isdp;
		}
		Thread.sleep(100);
		// log.logInfo("isdp:"+isdp);
		return isdp;
	}

	/**
	 * 判断文本是否存在
	 * 
	 * @param text
	 *            文本
	 * @param time
	 *            等待时间
	 * @return
	 */
	public static boolean isplay_text(String text, int time) {
		boolean isdp = false;
		try {
			MainStart.driver.manage().timeouts()
					.implicitlyWait(time, TimeUnit.SECONDS);
			isdp = MainStart.driver
					.findElement(
							By.xpath(".//*[normalize-space(text()) and normalize-space(.)='"
									+ text + "']")).isDisplayed();
			MainStart.driver
					.manage()
					.timeouts()
					.implicitlyWait(Config.appiumconfig_waitTime,
							TimeUnit.SECONDS);

		} catch (Exception e) {
			isdp = false;
			try {
				MainStart.driver
						.manage()
						.timeouts()
						.implicitlyWait(Config.appiumconfig_waitTime,
								TimeUnit.SECONDS);
			} catch (Exception e1) {

			}
			return isdp;
		}
		return isdp;
	}

	/***
	 * by对象存在为通过 判断元素是否存在,在用例过程中用。不写入excel报告
	 * 
	 * @param by
	 *            by对象
	 * @param errlog
	 *            ： 日志信息
	 * @throws InterruptedException
	 * @throws Exception
	 */
	public static void isObjectExist(By by, String errlog)
			throws InterruptedException, Exception {
		if (isdisplay(by, 20)) {
			log.logInfo(errlog + " 成功！！");
		} else {
			log.logWarn(errlog + " 失败！！");
		}
	}

	/***
	 * 判断元素是否存在,在用例过程中用。不写入excel报告
	 * 
	 * @param sql
	 *            sql查询语句
	 * @param name
	 *            期望查询结果
	 * @param errlog
	 *            错误日志信息
	 * @throws InterruptedException
	 * @throws Exception
	 */
	public static void isObjectExist(String sql, String name, String oklog,
			String errlog) throws InterruptedException, Exception {

		String sqlname = "";
		sqlname = mysql.select(sql);// 数据库查询
		if (name.equals(sqlname)) { // 查询结果和name对比
			log.logInfo(oklog);

		} else {
			log.logError(errlog);

		}
	}

	/***
	 * 判断元素是否存在,在用例过程中用。不写入excel报告 根据body体中存在的文本文字判断
	 * 
	 * @param what
	 *            body体中的文字，不能为纯数字
	 * @param oklog
	 *            正确日志信息
	 * @param errlog
	 *            错误日志信息
	 * @param index
	 *            写入对应的行数
	 * @throws InterruptedException
	 * @throws Exception
	 */
	public static void isObjectExist(String what, String oklog, String errlog)
			throws InterruptedException, Exception {

		if (isTextPresent(what)) {
			log.logInfo(oklog);

		} else {
			log.logError(errlog);

		}
	}

	/***
	 * 断言并写入报告： 根据数据库查询结果对比 写入通过或不通过、不通过会截图、并将结果写入excel对应sheet页
	 * 
	 * @param sql
	 *            sql查询语句
	 * @param name
	 *            期望查询结果
	 * @param errlog
	 *            错误日志信息
	 * @param index
	 *            写入对应的行数
	 * @throws InterruptedException
	 * @throws Exception
	 */
	public static void assertReport(String sql, String name, String oklog,
			String errlog, String sheetname, int index)
			throws InterruptedException, Exception {

		String sqlname = "";
		sqlname = mysql.select(sql);// 数据库查询
		if (name.equals(sqlname)) { // 查询结果和name对比
			log.logInfo(oklog);
			// ExcelOperate.cellexcelok(sheetname, index);
			ExcelOperate.writecache("通过", sheetname, index);
		} else {
			log.logError(errlog);
			// ExcelOperate.cellexcelerr(sheetname, index);
			ExcelOperate.writecache("未通过", sheetname, index);
		}
	}

	/***
	 * 断言并写入报告： n>0 ,通过 写入通过或不通过、不通过会截图、并将结果写入excel对应sheet页
	 * 
	 * @param n
	 *            int型
	 * @param oklog
	 *            正确日志信息
	 * @param errlog
	 *            错误日志信息
	 * @param index
	 *            写入对应的行数
	 * @throws InterruptedException
	 * @throws Exception
	 */
	public static void assertReport(int n, String oklog, String errlog,
			String sheetname, int index) throws InterruptedException, Exception {

		if (n > 0) {
			log.logInfo(oklog);
			// ExcelOperate.cellexcelok(sheetname, index);
			ExcelOperate.writecache("通过", sheetname, index);
		} else {
			log.logError(errlog);
			// ExcelOperate.cellexcelerr(sheetname, index);
			ExcelOperate.writecache("未通过", sheetname, index);
		}
	}

	/***
	 * 断言并写入报告： 判断by对象存在,写入通过或不通过、不通过会截图、并将结果写入excel对应sheet页
	 * 
	 * @param by
	 *            by对象
	 * @param oklog
	 *            正确日志信息
	 * @param errlog
	 *            错误日志信息
	 * @param index
	 *            写入对应的行数
	 * @throws InterruptedException
	 * @throws Exception
	 */
	public static void assertReport(By by, String oklog, String errlog,
			String sheetname, int index) throws InterruptedException, Exception {

		if (isdisplay(by, 20)) {
			log.logInfo(oklog);
			// ExcelOperate.cellexcelok(sheetname, index);
			ExcelOperate.writecache("通过", sheetname, index);
		} else {
			log.logError(errlog);
			// ExcelOperate.cellexcelerr(sheetname, index);
			ExcelOperate.writecache("未通过", sheetname, index);
		}
	}

	/***
	 * 断言并写入报告： 页面中存在文本,为通过 写入通过或不通过、不通过会截图、并将结果写入excel对应sheet页
	 * 
	 * @param what
	 *            body体中的文字，不能为纯数字
	 * @param oklog
	 *            正确日志信息
	 * @param errlog
	 *            错误日志信息
	 * @param index
	 *            写入对应的行数
	 * @throws InterruptedException
	 * @throws Exception
	 */
	public static void assertReport(String what, String oklog, String errlog,
			String sheetname, int index) throws InterruptedException, Exception {

		if (isTextPresent(what)) {
			log.logInfo(oklog);
			// ExcelOperate.cellexcelok(sheetname, index);
			ExcelOperate.writecache("通过", sheetname, index);
		} else {
			log.logError(errlog);
			// ExcelOperate.cellexcelerr(sheetname, index);
			ExcelOperate.writecache("未通过", sheetname, index);
		}
	}

	/***
	 * 断言并写入报告：by对象的gettext为tname,为通过 写入通过或不通过、不通过会截图、并将结果写入excel对应sheet页
	 * 
	 * @param by
	 *            by对象
	 * @param oklog
	 *            正确日志信息
	 * @param errlog
	 *            错误日志信息
	 * @param index
	 *            写入对应的行数
	 * @throws InterruptedException
	 * @throws Exception
	 */
	public static void assertReport(By by, String tname, String oklog,
			String errlog, String sheetname, int index)
			throws InterruptedException, Exception {

		WebElement userName = MainStart.driver.findElement(by);
		String tagName = userName.getText();
		System.out.println(tagName);
		if (tname.equals(tagName)) {//

			log.logInfo(oklog);
			// ExcelOperate.cellexcelok(sheetname, index);
			ExcelOperate.writecache("通过", sheetname, index);
		} else {
			log.logError(errlog);
			// ExcelOperate.cellexcelerr(sheetname, index);
			ExcelOperate.writecache("未通过", sheetname, index);
		}

	}

	/**
	 * 断言并写入报告：判断:isOne等于istwo，为通过 写入通过或不通过、不通过会截图、并将结果写入excel对应sheet页
	 * 
	 * @param isOne
	 *            第一个int
	 * @param istwo
	 *            第二个int
	 * @param oklog
	 *            正确日志信息
	 * @param errlog
	 *            错误日志信息
	 * @param sheetname
	 * @param index
	 *            写入对应的行数
	 * @throws InterruptedException
	 * @throws Exception
	 */
	public static void assertReport(int isOne, int istwo, String oklog,
			String errlog, String sheetname, int index)
			throws InterruptedException, Exception {

		if (isOne == istwo) {
			log.logInfo(oklog);
			// ExcelOperate.cellexcelok(sheetname, index);
			ExcelOperate.writecache("通过", sheetname, index);
		} else {
			log.logError(errlog);
			// ExcelOperate.cellexcelerr(sheetname, index);
			ExcelOperate.writecache("未通过", sheetname, index);
		}
	}

	/***
	 * 动态等待元素
	 * 
	 * @param by
	 *            元素定位
	 * @param time
	 *            等待时间 ，单位 秒
	 * @return
	 */
	public static boolean waitElement(By by, int time) {
		try {

			(new WebDriverWait(MainStart.driver, time))
					.until(ExpectedConditions.presenceOfElementLocated(by));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * by对象,通过文本找by对象
	 * 
	 * @param text
	 * @return
	 */
	public static String by(String text) {
		String bytext = "//*[normalize-space(text()) and normalize-space(.)='"
				+ text + "']";
		return bytext;
	}

	/***
	 * 判断页面有特定的文字
	 * 
	 * @param what
	 * @return
	 */
	public static boolean isTextPresent(String what) {
		try {
			sleep(1);
			return MainStart.driver.findElement(By.tagName("body")).getText()
					.contains(what);// 遍历body节点下的所有节点 取其文本值 并判断是否包含 文本 what
			// return
			// driver.findElement(By.className("iwapui")).getText().contains(what);//
			// 遍历body节点下的所有节点 取其文本值 并判断是否包含 文本 what
		} catch (Exception e) {
			e.printStackTrace();
			return false;// 没有该文本 则 返回 false
		}
	}

	/***
	 * 思考时间
	 * 
	 * @param time
	 *            时间 单位 秒
	 */
	public static void sleep(long time) {
		try {

			for (int i = 0; i < time; i++) {

				Thread.sleep(1000);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		// JS_wait(10);
	}

	/***
	 * 去掉字符串中的 “，”和 “。”字符
	 * 
	 * @param test1
	 * @return
	 */
	public static String strCutInt(String test1) {

		String j = "";
		String[] test2 = test1.split("\\.");
		String test3 = test2[0];
		int th = test3.length();
		for (int i = 0; i < th; i++) {
			String cha = test3.charAt(i) + "";
			// System.out.println(cha);
			if (cha.equals(",")) {
				// System.out.println("ok");
			} else {
				j = j + test3.charAt(i);
			}

		}
		return j;
	}

	/***
	 * 带“，”和 “。”字符转化成整形相加后 再转化成string型
	 * 
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static String strAndstr(String str1, String str2) {

		if (str1 == "") {
			str1 = "0";
		}
		if (str2 == "") {
			str2 = "0";
		}
		String j = "";
		int i = 0;
		i = Integer.valueOf(strCutInt(str1)) + Integer.valueOf(strCutInt(str2));

		// System.out.println(i);
		j = String.valueOf(i);

		return j;
	}

	/***
	 * 返回一个随机数 。格式为“99.99”
	 * 
	 * @return
	 */
	public static String randomIntd() {
		java.util.Random r = new java.util.Random();
		String s = "" + r.nextInt();
		// System.out.println(s);

		int h = s.length();
		String str;
		String str1 = s.charAt(h - 4) + "";
		if ("0".equals(str1)) {
			str1 = "1";
		}
		str = str1;
		str = str + s.charAt(h - 3);
		str = str + ".";
		str = str + s.charAt(h - 2);
		str = str + s.charAt(h - 1);
		// Float intt=Float.valueOf(str);
		return str;

	}

	/**
	 * 获取随机数字
	 * 
	 * @param n
	 *            数字个数
	 * @return n个随机数字
	 */
	public static String getrandomInt(int n) {
		String s = "";
		java.util.Random r = new java.util.Random();
		for (int i = 0; i < n; i++) {
			s = s + r.nextInt(9);

		}

		return s;

	}

	/***
	 * 返回当天日期。格式“20170828”
	 * 
	 * @return
	 */
	public static String today() {
		String date = new SimpleDateFormat("yyyyMMdd").format(new Date());
		return date;

	}

	/***
	 * 返回当天日期。格式“20170828120101”
	 * 
	 * @return
	 */
	public static String time() {
		String date = new SimpleDateFormat("MMddHHmmss").format(new Date());
		return date;

	}

	/***
	 * 获取xml某节点上的text
	 * 
	 * @param xmlpath
	 *            文件路径
	 * @param frist
	 *            第一个节点
	 * @param second
	 * @param th3
	 *            第三个节点
	 * @param second
	 *            第三个节点
	 * @param index
	 *            选择第三个节点字段
	 * @return
	 * @throws DocumentException
	 */
	public static String XmlGetText(String xmlpath, String frist,
			String second, String th3, int index) throws DocumentException {

		Document document;
		String aa = "";
		try {
			SAXReader reader = new SAXReader();
			document = reader.read(new File(xmlpath));
			Element root = document.getRootElement();
			System.out.println(root.getName());

			Element memberElm1 = root.element(frist);
			try {
				log.logInfo("第一个节点,第一个字段的内容为:"
						+ memberElm1.attribute(0).getText());
			} catch (Exception e) {
			}
			Element memberElm = memberElm1.element(second);
			try {
				log.logInfo("第二个节点,第一个字段的内容为:"
						+ memberElm.attribute(0).getText());
			} catch (Exception e) {
			}
			Element memberElm2 = memberElm.element(th3);
			try {
				log.logInfo("第三个节点,第一个字段的内容为:"
						+ memberElm2.attribute(0).getText());
			} catch (Exception e) {
			}

			aa = memberElm2.attribute(index).getText();
		} catch (Exception e) {
			log.logWarn(e.getMessage());
		}
		return aa;
	}

	/***
	 * 读取http远程服务器上的txt文件
	 * 
	 * @param FileName
	 * @return
	 * @throws IOException
	 */
	public static String readurl(String FileName) throws IOException {
		String read;
		String readStr = "";
		// try{
		URL url = new URL(FileName);
		HttpURLConnection urlcon = (HttpURLConnection) url.openConnection();
		urlcon.setConnectTimeout(5000);
		urlcon.setReadTimeout(5000);
		BufferedReader br = new BufferedReader(new InputStreamReader(
				urlcon.getInputStream()));
		while ((read = br.readLine()) != null) {
			readStr = readStr + read;
		}
		br.close();
		// }catch(Exception e){
		// log.logWarn(e.getMessage());
		// readStr ="没读取到";
		// }
		//
		return readStr;
	}

	/***
	 * 判断字符串是否为数字
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str) {
		for (int i = 0; i < str.length(); i++) {
			// System.out.println(str.charAt(i));
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	/***
	 * web页面中 的 鼠标悬停
	 * 
	 * @param by
	 */
	public static void actions_Perform(By by) {
		Actions ats = new Actions(MainStart.driver);

		ats.moveToElement(MainStart.driver.findElement(by)).perform();
		sleep(1);

		// ats.moveToElement(driver.findElement(by)).moveByOffset(10,
		// 3).build().perform();
		// ats.moveToElement(driver.findElement(by)).keyDown(Keys.ENTER).perform();
		// String code = "var fireOnThis = arguments[0];" +
		// "var evObj = document.createEvent('MouseEvents');"
		// + "evObj.initEvent( 'mouseover', true, true );" +
		// "fireOnThis.dispatchEvent(evObj);";
		// ((JavascriptExecutor)
		// driver).executeScript(code,driver.findElement(by));

	}

	/***
	 * web页面中 的 元素拖动
	 * 
	 * @param by
	 */
	public static void actions_dragAndDrop(By by1, By by2) {
		Actions ats = new Actions(MainStart.driver);
		WebElement by11 = MainStart.driver.findElement(by1);
		WebElement by22 = MainStart.driver.findElement(by2);
		ats.dragAndDrop(by11, by22).perform();

		sleep(1);

		// ats.moveToElement(driver.findElement(by)).moveByOffset(10,
		// 3).build().perform();
		// ats.moveToElement(driver.findElement(by)).keyDown(Keys.ENTER).perform();
		// String code = "var fireOnThis = arguments[0];" +
		// "var evObj = document.createEvent('MouseEvents');"
		// + "evObj.initEvent( 'mouseover', true, true );" +
		// "fireOnThis.dispatchEvent(evObj);";
		// ((JavascriptExecutor)
		// driver).executeScript(code,driver.findElement(by));

	}

	/**
	 * 双击
	 * 
	 * @param by1
	 */
	public static void actions_duble_click(By by1) {
		Actions ats = new Actions(MainStart.driver);
		WebElement by11 = MainStart.driver.findElement(by1);
		ats.doubleClick(by11).perform();

		sleep(1);
	}

	/***
	 * 执行JS，点击动作
	 * 
	 * @param by
	 */
	public static void JS_click(By by) {
		WebElement we = MainStart.driver.findElement(by);

		JavascriptExecutor jse = (JavascriptExecutor) MainStart.driver;

		jse.executeScript("arguments[0].click();", we);

	}

	/***
	 * 执行JS，输入数据
	 * 
	 * @param by
	 */
	public static void JS_sendkey(By by, String string) {
		WebElement we = MainStart.driver.findElement(by);

		JavascriptExecutor jse = (JavascriptExecutor) MainStart.driver;

		jse.executeScript("arguments[0].value='" + string + "'", we);

	}

	/***
	 * 执行JS，刷新页面
	 */
	public static void JS_refresh() {

		JavascriptExecutor jse = (JavascriptExecutor) MainStart.driver;

		jse.executeScript("history.go(0)");

	}

	/***
	 * 执行JS，带by对象
	 * 
	 * @param js
	 * @param by
	 */
	public static void JS_EXE(String js, By by) {
		WebElement we = MainStart.driver.findElement(by);
		((JavascriptExecutor) MainStart.driver).executeScript(js, we);
	}

	/***
	 * 执行JS
	 * 
	 * @param js
	 *
	 */
	public static void JS_EXE(String js) {

		((JavascriptExecutor) MainStart.driver).executeScript(js);
	}

	/***
	 * 元素对象标红
	 * 
	 * @param id
	 */
	public static void JS_ElementRed(String id) {
		((JavascriptExecutor) MainStart.driver)
				.executeScript("var q=document.getElementById(\"" + id
						+ "\");q.style.border=\"1px solid red\";");
	}

	/***
	 * 页面移动到by对象
	 * 
	 * @param by
	 * @throws InterruptedException
	 */
	public static void JS_MoveWebElement(By by) throws InterruptedException {
		action.JS_wait(200);
		WebElement el = MainStart.driver.findElement(by);
		((JavascriptExecutor) MainStart.driver).executeScript(
				"arguments[0].scrollIntoView(false);", el);

	}

	/**
	 * 随机生成一个汉字
	 * 
	 * @return
	 */
	public static String getRandomGBK() {
		String str = "";
		int hightPos; //
		int lowPos;
		Random random = new Random();
		hightPos = (176 + Math.abs(random.nextInt(39)));
		lowPos = (161 + Math.abs(random.nextInt(93)));
		byte[] b = new byte[2];
		b[0] = (Integer.valueOf(hightPos)).byteValue();
		b[1] = (Integer.valueOf(lowPos)).byteValue();
		try {
			str = new String(b, "GBK");

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			System.out.println("错误");
		}
		return str;
	}

	/**
	 * 随机生成汉字，汉字个数自定义
	 * 
	 * @param n
	 *            汉字个数
	 * @return
	 */
	public static String getRandomChinese(int n) {
		String car = "";
		try {
			for (int i = 1; i < n; i++) {
				String temp = getRandomGBK();
				if (!temp.isEmpty()) { // 判断是否有空
					car = car + temp;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return car;
	}

	/***
	 * 页面移动到web页面底部
	 */
	public static void JS_MoveEnd() {
		action.sleep(4);
		((JavascriptExecutor) MainStart.driver)
				.executeScript("window.scrollTo(0, document.body.scrollHeight)");

	}

	/**
	 * 等待页面加载(全局设置，重要)
	 * 
	 * @param time
	 *            (0.1秒)等待时间，如果已经加载立刻结束
	 * @throws InterruptedException
	 */
	public static void JS_wait(int time) throws InterruptedException {

		Thread.sleep(200);// 全局等待时间 毫秒
		// for(int i=0;i<time;i++){
		// Thread.sleep(100);
		// break;
		// log.logInfo("readyState:"+((JavascriptExecutor)driver).executeScript("return jQuery.active").toString());
		// if(((JavascriptExecutor)driver).executeScript("return jQuery.active").toString().contains("0")){
		//
		// break;
		// }

		// if(((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete")){
		// break;
		// }
		// }
	}

	/**
	 * 关闭全部浏览器
	 * 
	 * @throws IOException
	 */
	public static void close_allbrowse() throws IOException {
		Runtime.getRuntime().exec("taskkill /F /IM chrome.exe");
		Runtime.getRuntime().exec("taskkill /F /IM iexplorer.exe");
		Runtime.getRuntime().exec("taskkill /F /IM firefox.exe");
		Runtime.getRuntime().exec("taskkill /F /IM safari.exe");
		Runtime.getRuntime().exec("taskkill /F /IM opera.exe");
	}

	/**
	 * findElement方法的重写：点击
	 * 
	 * @param by
	 *            by对象
	 * @throws InterruptedException
	 */
	public static void findElement_click(By by) throws InterruptedException {
		MainStart.driver.findElement(by).click();
		action.JS_wait(200);
	}

	/**
	 * findElements方法的重写：点击
	 * 
	 * @param by
	 *            by对象
	 * @param n
	 *            选择操作元素的个数
	 * @throws InterruptedException
	 */
	public static void findElements_click(By by, int n)
			throws InterruptedException {
		MainStart.driver.findElements(by).get(n).click();
		action.JS_wait(200);
	}

	/**
	 * findElement方法的重写：输入内容
	 * 
	 * @param by
	 *            by对象
	 * @param text
	 *            输入的内容
	 * @throws InterruptedException
	 */
	public static void findElement_sendkeys(By by, String text)
			throws InterruptedException {

		MainStart.driver.findElement(by).clear();
		MainStart.driver.findElement(by).sendKeys(text);
		action.JS_wait(200);

	}

	/**
	 * findElements方法的重写:by对象sendkeys
	 * 
	 * @param by
	 *            by对象
	 * @param n
	 *            选择操作元素的个数
	 * @param text
	 *            输入的内容
	 * @throws InterruptedException
	 */
	public static void findElements_sendkeys(By by, int n, String text)
			throws InterruptedException {
		MainStart.driver.findElements(by).get(n).clear();
		MainStart.driver.findElements(by).get(n).sendKeys(text);
		action.JS_wait(200);

	}

	/***
	 * 
	 * 浏览器跳转到新url地址,（覆盖原页面）
	 * 
	 * @throws Exception
	 */

	public static void navigateTO_ht(String url) throws Exception {

		MainStart.driver.navigate().to(url);
		action.sleep(1);
		MainStart.driver.manage().window().maximize();
		MainStart.driver.navigate().refresh();
	}

	/***
	 * 浏览器页面返回上一页面
	 */
	public static void navigateBack() {// 浏览器页面返回

		MainStart.driver.navigate().back();
		action.sleep(1);
		MainStart.driver.manage().window().maximize();
		MainStart.driver.navigate().refresh();
	}

	/***
	 * 相当于点击弹出框的确定按钮
	 * 
	 * @throws InterruptedException
	 */
	static public void alert_accept() throws InterruptedException {

		new WebDriverWait(MainStart.driver, 5).until(ExpectedConditions
				.alertIsPresent());
		MainStart.driver.switchTo().alert().accept();

	}

	/***
	 * 相当于点击弹出框的取消按钮
	 * 
	 * @throws InterruptedException
	 */
	static public void alert_dismiss() throws InterruptedException {

		new WebDriverWait(MainStart.driver, 5).until(ExpectedConditions
				.alertIsPresent());
		MainStart.driver.switchTo().alert().dismiss();

	}

	/***
	 * 获取弹出框文字
	 * 
	 * @throws InterruptedException
	 */
	static public String alert_get() throws InterruptedException {

		new WebDriverWait(MainStart.driver, 5).until(ExpectedConditions
				.alertIsPresent());
		String inputInfo = MainStart.driver.switchTo().alert().getText();
		return inputInfo;
	}

	/***
	 * 在弹出框输入文字
	 * 
	 * @throws InterruptedException
	 */
	static public void alert_input(String text) throws InterruptedException {

		new WebDriverWait(MainStart.driver, 5).until(ExpectedConditions
				.alertIsPresent());
		MainStart.driver.switchTo().alert().sendKeys(text);
	}

	// 随机生成ip地址
	public static String getRandomIp() {

		// ip范围
		int[][] range = { { 607649792, 608174079 }, // 36.56.0.0-36.63.255.255
				{ 1038614528, 1039007743 }, // 61.232.0.0-61.237.255.255
				{ 1783627776, 1784676351 }, // 106.80.0.0-106.95.255.255
				{ 2035023872, 2035154943 }, // 121.76.0.0-121.77.255.255
				{ 2078801920, 2079064063 }, // 123.232.0.0-123.235.255.255
				{ -1950089216, -1948778497 }, // 139.196.0.0-139.215.255.255
				{ -1425539072, -1425014785 }, // 171.8.0.0-171.15.255.255
				{ -1236271104, -1235419137 }, // 182.80.0.0-182.92.255.255
				{ -770113536, -768606209 }, // 210.25.0.0-210.47.255.255
				{ -569376768, -564133889 }, // 222.16.0.0-222.95.255.255
		};

		Random rdint = new Random();
		int index = rdint.nextInt(10);
		String ip = num2ip(range[index][0]
				+ new Random().nextInt(range[index][1] - range[index][0]));
		return ip;
	}

	/*
	 * 将十进制转换成IP地址
	 */
	private static String num2ip(int ip) {
		int[] b = new int[4];
		String x = "";
		b[0] = (ip >> 24) & 0xff;
		b[1] = (ip >> 16) & 0xff;
		b[2] = (ip >> 8) & 0xff;
		b[3] = ip & 0xff;
		x = Integer.toString(b[0]) + "." + Integer.toString(b[1]) + "."
				+ Integer.toString(b[2]) + "." + Integer.toString(b[3]);

		return x;
	}

	// 生成Guid
	public static String getGuid() {
		UUID uuid = UUID.randomUUID();
		String[] str = uuid.toString().split("-");
		String Guid = "";
		for (int i = 0; i < str.length; i++) {
			Guid = Guid + str[i];
		}
		return Guid;
	}

	/**
	 * 当前日期
	 * 
	 * @param n
	 * @return
	 */
	public static String getDate() {

		String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		return date;

	}

	/**
	 * 返回过去n月
	 * 
	 * @param n
	 * @return
	 */
	public static String getDateLastMONTH(int n) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		// 过去一月
		c.setTime(new Date());
		c.add(Calendar.MONTH, -n);
		Date m = c.getTime();
		String mon = format.format(m);
		// System.out.println("过去一个月："+mon);
		return mon;
	}

	/**
	 * 返回过去n年
	 * 
	 * @param n
	 * @return
	 */
	public static String getDateLastYEAR(int n) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		// 过去一年
		c.setTime(new Date());
		c.add(Calendar.YEAR, -n);
		Date y = c.getTime();
		String year = format.format(y);
		// System.out.println("过去一年："+year);
		return year;
	}

	/**
	 * 前往未来第n天
	 * 
	 * @param n
	 * @return
	 */
	public static String getDateNextDate(int n) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		// 未来第七天
		c.setTime(new Date());
		c.add(Calendar.DATE, +n);
		Date d = c.getTime();
		String day = format.format(d);
		// System.out.println("未来第七天："+day);
		return day;
	}
	/**
	 * 返回过去n小时
	 * 
	 * @param n
	 * @return
	 */
	public static String getDateLastHour(int n) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		// 过去七天
		c.setTime(new Date());
		c.add(Calendar.HOUR, -n);
		Date d = c.getTime();
		String day = format.format(d);
		// System.out.println("过去七天："+day);
		return day;
	}

	/**
	 * 返回过去n天
	 * 
	 * @param n
	 * @return
	 */
	public static String getDateLastDate(int n) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		// 过去七天
		c.setTime(new Date());
		c.add(Calendar.DATE, -n);
		Date d = c.getTime();
		String day = format.format(d);
		// System.out.println("过去七天："+day);
		return day;
	}

	public static void exeBat(String timeFortmat) {
		String cmd = "cmd /c start E:/智慧用电自动化测试/loadrunner脚本/" + timeFortmat
				+ ".bat";
		try {
			Runtime.getRuntime().exec(cmd);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
