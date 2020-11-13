package com.haoyun.automationtesting.framework;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

public class AutoCode {
	/***
	 * 功能：excel中用例转换成代码，excel路径为工程目录下“/resource/case.xlsx” 生成规则：
	 * 1、从第二个sheet开始生成代码：一个sheet页对应生成一个以com.haoyun.automationtesting.test开头的包名
	 * 2、sheet页中从第5行开始，每一个数据为一条用例，对应生成一个。java文件 3、程序入口为run() 4、执行前需关闭excel
	 * 5、当excel中sheet页名称中包含
	 * “nocode”时该sheet页中的用例不执行转换代码操作。注意：要执行该sheet页中的用例时，必须要去掉nocode字段，否则不会执行。
	 * 
	 * @author lisheng
	 */

	public static String starttime;// 执行开始时间
	public static StringBuffer errij = new StringBuffer();
	public static int casecount = 0;

	public static String pwd = pwd();

	public static String pwd() {
		// String filePath="";
		File directory = new File(".");
		try {
			return directory.getCanonicalPath() + "/resource/case.xlsx";
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return "";
	}

	public static void charOutStream(String package_name, String classname,
			int SheetAt, int rows, String casename) throws Exception {
		// 1：利用File类找到要操作的对象

		File directory = new File(".");
		String fileName = directory.getCanonicalPath()
				+ "\\src\\com\\haoyun\\automationtesting\\test\\"
				+ package_name + "\\" + classname + ".java";
		File file = new File(fileName);

		if (package_name.trim().endsWith("nocode")) {
			log.logInfo("设置为不生成代码的sheet包名为：" + package_name);
		} else {

			if (classname.trim() != null) {// 如果类名不为空则创建。java文件

				if (!file.getParentFile().exists()) {
					file.getParentFile().mkdirs();
				} else {
					if (file.exists()) {
						file.delete();
					}

					// file.getParentFile().mkdirs();
				}

			}

			// 2：准备输出流
			StringBuffer sBuffer = new StringBuffer("//Reserved interface");
			// OutputStreamWriter out = new OutputStreamWriter(new
			// FileOutputStream(
			// file), "UTF-8");// 完美解决中文乱码问题

			OutputStream putStream = new FileOutputStream(file);
			OutputStreamWriter out = new OutputStreamWriter(putStream, "UTF-8");// 完美解决中文乱码问题
			// Writer out = new FileWriter(file);
			out.write(AutoCodeTest(classname, package_name, SheetAt, rows,
					casename, sBuffer));

			out.flush();
			out.close();
		}
	}

	@Test
	public static void Run() throws Exception {
		try {
			log.logInfo("开始自动生成用例。。。");
			starttime = new SimpleDateFormat("yyyyMMdd-HHmmss")
					.format(new Date());
			int sheet_count;
			// StringBuffer sBuffer = new StringBuffer("//预留接口");
			sheet_count = issheet_at();// 取excel的sheet页数。
			// System.out.println("一共有多少个sheet页：" + sheet_count);
			for (int SheetAt = 1; SheetAt < sheet_count; SheetAt++) {// 一个sheet页为一个功能模块的案例，
				// log.logInfo(ExcelAutoCode.getexcel_at(i, 6, 3));
				int rowscount = getrows(SheetAt);
				String package_name = getsheetname(SheetAt);// 当前sheet页的名字
				// System.out.println("当前sheet页中一共有多少行数据：" + rowscount);
				// System.out.println("当前sheet页的名字为：" + package_name);

				for (int rows = 5; rows < rowscount + 2; rows++) {// 一个sheet页中没行数据生成一条测试用例。rows为从第几行开始

					String classname = getexcel_at(SheetAt, rows, 1);
					String casename = getexcel_at(SheetAt, rows, 2);
					// AutoCodeTest("test", i, j, sBuffer);
					// /System.out.println("当前的calssname为：" + classname);

					if (classname.trim().isEmpty()) {
						// System.out.println(AutoCodeTest(classname, SheetAt,
						// rows,sBuffer));
						// System.out.println("当前的calssname为：空");
					} else {
						charOutStream(package_name, classname, SheetAt, rows,
								casename);
					}

				}
			}
			log.logInfo("自动生成用例完成。");
		} catch (Exception e) {
			e.printStackTrace();
			log.logWarn("error:" + e);

		}
	}

	public static String out_config() {// 导入首行用例
		return Config.autocode_1;
	}

	public static String out_config_import() {// 导入import
		return Config.autocode_2;
	}

	/***
	 * 针对个单元格中的内容，转换成可执行代码格式
	 * 
	 * @param sheetye
	 *            ：sheet页数，起始页为0
	 * @param i
	 *            ：行数
	 * @param j
	 *            ：列数
	 * @return 可执行的代码格式，如：driver.findElement(By.name("name")).click
	 * @throws Exception
	 */
	public static String step(int SheetAt, int i, int j) {

		String st = "";

		try {
			String gs = getexcel_at(SheetAt, i, j);
			if (gs == "") {

			} else {

				String[] gets = gs.split("-");// 将单元格中数据拆成三份，a为对应元素对象，b对应动作，c对应输入的数据
				System.out.println("当前处理的数据为：" + gs);
				String a = "";
				String b = "";
				String c = "";
				try {
					a = gets[0];
				} catch (Exception e) {

				}
				try {
					b = gets[1];
				} catch (Exception e) {
				}
				try {
					c = gets[2];
					if (!gets[3].isEmpty()) {
						c = c + "-" + gets[3];
						if (!gets[4].isEmpty()) {
							c = c + "-" + gets[4];
							if (!gets[5].isEmpty()) {
								c = c + "-" + gets[5];
							}
						}
					}
				} catch (Exception e) {
				}
				// System.out.println("拆分后a：" + a + "b:" + b + "c:" + c);
				if ((!Character.isDigit(a.charAt(0))) && !a.equals("x")
						&& !a.equals("p") && !a.equals("s") && !a.equals("r")
						&& !a.equals("rsql") && !a.equals("robot")) { // 判断a是否为数字rsql
					errij.append(SheetAt + "页:" + i + "行" + j + "列\r\n");

				} else {

					int cellss = getcells(SheetAt, 2);

					// log.logInfo("s:"+cellss);

					for (int index = 2; index < cellss; index++) {// 从第二行的元素对象行中循环取值。
						// 列总匹配，如果匹配成功会选择对应单元格中的数据写入模板。

						if (a.equals(String.valueOf(index))) {
							String cell_name = getexcel_at(SheetAt, 2,
									index + 1);// 获取对应单元格中的数据,数据在excel表中国的第二行。
							// System.out.println("cell_name:" + cell_name);

							switch (b) {
							case "1":// 点击。如“2-1”表示第二个元素点击
								st = "driver.findElement(By." + cell_name
										+ ").click();";
								break;
							case "2":// 输入。如：“2-2-admin”
										// 表示第二个元素输入内容为admin
								st = "driver.findElement(By."
										+ cell_name
										+ ").clear(); \r\n            driver.findElement(By."
										+ cell_name + ").sendKeys(\"" + c
										+ "\");";
								break;
							case "3":// 动态等待思考时间。如
										// “2-3”表示第二个元素等待2秒；“2-3-5”表示第二个元素等待5秒
								if (c.isEmpty()) {
									st = "action.isdisplay(By." + cell_name
											+ ",2);";
								} else {
									if (action.isNumeric(c)) {
										st = "action.isdisplay(By." + cell_name
												+ "," + c + ");";
									} else {
										st = "action.isdisplay(By." + cell_name
												+ ",2);";
									}
								}

								break;
							case "4":// 固定思考时间。 如
										// “2-4”表示思考时间2秒；“2-4-5”表示思考时间5秒
								if (c.isEmpty()) {
									st = "action.sleep(2);";
								} else {
									if (action.isNumeric(c)) {
										st = "action.sleep(" + c + ");";
									} else {
										st = "action.sleep(2);";
									}
								}

								break;
							case "5":// 鼠标悬停.如“2-5”表示鼠标悬停在第二个元素上。
								st = "action.actions_moveto(By." + cell_name
										+ ");";
								break;
							case "6":// 进入到子iframe窗口,如“2-6-main”表示进入到id为main的iframe窗口中。不依赖第二个元素
								st = "driver.switchTo().frame(\"" + c + "\");";
								break;
							case "7":// 返回到主窗口，如“2-7”，不依赖第二个元素
								st = "driver.switchTo().defaultContent();";
								break;
							case "8":// 相同属性元素对象的点击操作，如“2-8-0”，点击页面相同元素中的第一个元素，0代表第一个
								st = "driver.findElements(By." + cell_name
										+ ").get(" + c + ").click();";
								break;
							case "9":// 点击弹出框的确定按钮
								st = "Alert.alert_accept();";
								break;
							case "10":// 点击弹出框的取消按钮
								st = "Alert.alert_dismiss();";
								break;
							case "11":// 弹出框中输入数据
								st = "Alert.alert_input(\"" + c + "\");";
								break;
							case "12":// web页面移动到by对象
								// .如2-12，表示页面移动到第二个元素
								st = "action.JS_MoveWebElement(By." + cell_name
										+ ");";
								break;
							case "13":// 页面移动到web页面底部..如2-13，不依赖第一项
								st = "action.JS_MoveEnd();";
								break;
							case "100":
								st = "action.isObjectExist_cell(By."
										+ cell_name
										+ ", \"用例验证成功\", \"用例验证失败\",sheetname, index);";

								break;
							case "":
								st = "";
								errij.append(SheetAt + "页:" + i + "行" + j
										+ "列\r\n");
								break;
							default:
								if (!Character.isDigit(b.charAt(0))) {
									st = "";
									errij.append(SheetAt + "页:" + i + "行" + j
											+ "列\r\n");
								}else{
									st = "未知错误";
									errij.append(SheetAt + "页:" + i + "行" + j
											+ "列\r\n");
								}
							}

//							if (b.equals("1")) {// 点击。如“2-1”表示第二个元素点击。
//
//								st = "driver.findElement(By." + cell_name
//										+ ").click();";
//							} else if (b.equals("2")) {// 输入。如：“2-2-admin”
//														// 表示第二个元素输入内容为admin
//								st = "driver.findElement(By."
//										+ cell_name
//										+ ").clear(); \r\n            driver.findElement(By."
//										+ cell_name + ").sendKeys(\"" + c
//										+ "\");";
//							} else if (b.equals("3")) {// 动态等待思考时间。如
//														// “2-3”表示第二个元素等待2秒；“2-3-5”表示第二个元素等待5秒
//								if (c.isEmpty()) {
//									st = "action.isdisplay(By." + cell_name
//											+ ",2);";
//								} else {
//									if (action.isNumeric(c)) {
//										st = "action.isdisplay(By." + cell_name
//												+ "," + c + ");";
//									} else {
//										st = "action.isdisplay(By." + cell_name
//												+ ",2);";
//									}
//								}
//
//							} else if (b.equals("4")) {// 固定思考时间。 如
//														// “2-4”表示思考时间2秒；“2-4-5”表示思考时间5秒
//								if (c.isEmpty()) {
//									st = "action.sleep(2);";
//								} else {
//									if (action.isNumeric(c)) {
//										st = "action.sleep(" + c + ");";
//									} else {
//										st = "action.sleep(2);";
//									}
//								}
//
//							} else if (b.equals("5")) {// 鼠标悬停.如“2-5”表示鼠标悬停在第二个元素上。
//								st = "action.actions_moveto(By." + cell_name
//										+ ");";
//							} else if (b.equals("6")) { // 进入到子iframe窗口,如“2-6-main”表示进入到id为main的iframe窗口中。不依赖第二个元素
//								st = "driver.switchTo().frame(\"" + c + "\");";
//
//							} else if (b.equals("7")) {// 返回到主窗口，如“2-7”，不依赖第二个元素
//								st = "driver.switchTo().defaultContent();";
//
//							} else if (b.equals("8")) {// 相同属性元素对象的点击操作，如“2-8-0”，点击页面相同元素中的第一个元素，0代表第一个
//								st = "driver.findElements(By." + cell_name
//										+ ").get(" + c + ").click();";
//							} else if (b.equals("9")) {// 点击弹出框的确定按钮
//								st = "Alert.alert_accept();";
//							} else if (b.equals("10")) {// 点击弹出框的取消按钮
//								st = "Alert.alert_dismiss();";
//							} else if (b.equals("11")) {// 弹出框中输入数据
//								st = "Alert.alert_input(\"" + c + "\");";
//							} else if (b.equals("12")) {// web页面移动到by对象
//														// .如2-12，表示页面移动到第二个元素
//								st = "action.JS_MoveWebElement(By." + cell_name
//										+ ");";
//							} else if (b.equals("13")) {// 页面移动到web页面底部..如2-13，不依赖第一项
//								st = "action.JS_MoveEnd();";
//
//							} else if (b.equals("100")) {
//								st = "action.isObjectExist_cell(By."
//										+ cell_name
//										+ ", \"用例验证成功\", \"用例验证失败\",sheetname, index);";
//
//							} else if (b.equals("")) {
//								st = "";
//								errij.append(SheetAt + "页:" + i + "行" + j
//										+ "列\r\n");
//							} else if (!Character.isDigit(b.charAt(0))) {
//								st = "";
//								errij.append(SheetAt + "页:" + i + "行" + j
//										+ "列\r\n");
//							}

						} else if (a.equals("r")) {// 三种情况判断,1、以元素对象存在。2.以页面中存在文本文字。3.以元素对象的gettext值。
							if (c.equals("")) {
								if (action.isNumeric(b)) {

									String cn = getexcel_at(SheetAt, 2,
											Integer.parseInt(b) + 1);// 获取对应单元格中的数据
									st = "action.isObjectExist_cell(By."
											+ cn
											+ ", \"用例验证成功\", \"用例验证失败\",sheetname, index);";
								} else {
									st = "action.isObjectExist_cell(\""
											+ b
											+ "\", \"用例验证成功\", \"用例验证失败\",sheetname, index);";
								}
							} else {
								st = "action.isObjectExist_cell(By."
										+ getexcel_at(SheetAt, 2,
												Integer.parseInt(b) + 1)
										+ ",\""
										+ c
										+ "\", \"用例验证成功\", \"用例验证失败\",sheetname, index);";
							}

						} else if (a.equals("rsql")) {

							st = "action.isObjectExist_cell(\""
									+ b
									+ "\",\""
									+ c
									+ "\", \"用例验证成功\", \"用例验证失败\",sheetname, index);";
						} else if (a.equals("x")) {

							st = gs.substring(2, gs.length());

						} else if (a.equals("p")) {
							st = b + " " + b + " =new " + b
									+ "();\r\n            " + b
									+ ".testcase();";

						} else if (a.equals("s")) {
							st = "action.sleep(5);\r\n            String handle = driver.getWindowHandle();\r\n            for (String handles : driver.getWindowHandles()) {\r\n                if (handles.equals(handle)) {\r\n                   driver.close();\r\n                }else{\r\n                   driver.switchTo().window(handles);\r\n                   }\r\n              }\r\n                ";

						} else if (a.equals("robot")) {
							if (b.equals("1") && !c.isEmpty()) {// 单一按键
								st = "Robots.keyPress(KeyEvent." + c + ");";
							} else if (b.equals("2") && !c.isEmpty()) {// 输入字符串
								st = "Robots.keyPressString(\"" + c + "\");";
							} else if (b.equals("3") && !c.isEmpty()) {// 输入组合键control+按键
								st = "Robots.keyPressWithCtrl(KeyEvent." + c
										+ ");";
							} else if (b.equals("4") && !c.isEmpty()) {// 输入组合键Shift+按键
								st = "Robots.keyPressWithShift(KeyEvent." + c
										+ ");";
							} else if (b.equals("5") && !c.isEmpty()) {// 输入组合键Alt+按键
								st = "Robots.keyPressWithAlt(KeyEvent." + c
										+ ");";
							} else {
								st = "";
							}

						}//
//						{switch (a) {
//						case "r":
//							if (c.equals("")) {
//								if (action.isNumeric(b)) {
//
//									String cn = getexcel_at(SheetAt, 2,
//											Integer.parseInt(b) + 1);// 获取对应单元格中的数据
//									st = "action.isObjectExist_cell(By."
//											+ cn
//											+ ", \"用例验证成功\", \"用例验证失败\",sheetname, index);";
//								} else {
//									st = "action.isObjectExist_cell(\""
//											+ b
//											+ "\", \"用例验证成功\", \"用例验证失败\",sheetname, index);";
//								}
//							} else {
//								st = "action.isObjectExist_cell(By."
//										+ b
//										+ ",\""
//										+ c
//										+ "\", \"用例验证成功\", \"用例验证失败\",sheetname, index);";
//							}
//							break;
//						case "rsql":
//							st = "action.isObjectExist_cell(\""
//									+ b
//									+ "\",\""
//									+ c
//									+ "\", \"用例验证成功\", \"用例验证失败\",sheetname, index);";
//							break;
//						case "x":
//							st = gs.substring(2, gs.length());
//							break;
//						case "p":
//							st = b + " " + b + " =new " + b
//							+ "();\r\n            " + b
//							+ ".testcase();";
//
//							break;
//						case "s":
//							st = "action.sleep(5);\r\n            String handle = driver.getWindowHandle();\r\n            for (String handles : driver.getWindowHandles()) {\r\n                if (handles.equals(handle)) {\r\n                   driver.close();\r\n                }else{\r\n                   driver.switchTo().window(handles);\r\n                   }\r\n              }\r\n                ";
//							break;
//						case "robot":
//							if (b.equals("1") && !c.isEmpty()) {// 单一按键
//								st = "Robots.keyPress(KeyEvent." + c + ");";
//							} else if (b.equals("2") && !c.isEmpty()) {// 输入字符串
//								st = "Robots.keyPressString(\"" + c + "\");";
//							} else if (b.equals("3") && !c.isEmpty()) {// 输入组合键control+按键
//								st = "Robots.keyPressWithCtrl(KeyEvent." + c
//										+ ");";
//							} else if (b.equals("4") && !c.isEmpty()) {// 输入组合键Shift+按键
//								st = "Robots.keyPressWithShift(KeyEvent." + c
//										+ ");";
//							} else if (b.equals("5") && !c.isEmpty()) {// 输入组合键Alt+按键
//								st = "Robots.keyPressWithAlt(KeyEvent." + c
//										+ ");";
//							} else {
//								st = "";
//							}
//							break;
//						default:
//							st = "";
//					}
//					}
					}
				}
			}

		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			log.logWarn("step error:" + e);
			log.logWarn("第" + SheetAt + "个sheet页:中的第" + i + "行" + j + "列");
			errij.append(SheetAt + "页:" + i + "行" + j + "列\r\n");
		}

		return st;
	}

	/***
	 * c-ak 字母 数组
	 * 
	 * @param n
	 *            取其中一个字母，起始未0
	 * @return
	 */
	public static String Letter(int n) {
		String[] gs = { "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m",
				"n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y",
				"z", "aa", "ab", "ac", "ad", "ae", "af", "ag", "ah", "ai",
				"aj", "ak" };
		return gs[n];
	}

	/***
	 * 测试用例的生成模板。
	 * 
	 * @param classname
	 * @param SheetAt
	 *            sheet页
	 * @param i
	 *            行数
	 * @param sb
	 *            注释内容
	 * @return
	 * @throws Exception
	 */
	public static String AutoCodeTest(String classname, String packagename,
			int SheetAt, int i, String casename, StringBuffer sb)
			throws Exception {

		sb.append("\r\n package com.haoyun.automationtesting.test."
				+ packagename + "; \r\n");

		sb.append("import org.openqa.selenium.Dimension;\r\n");
		sb.append("import org.openqa.selenium.WebDriver;\r\n");
		sb.append("import org.openqa.selenium.WebElement;\r\n");
		sb.append("import org.openqa.selenium.firefox.FirefoxDriver;\r\n");
		sb.append("import org.testng.annotations.Test;\r\n");
		sb.append("import java.sql.SQLException; \r\n");
		sb.append("import java.awt.event.KeyEvent; \r\n");

		sb.append("import java.util.concurrent.TimeUnit;\r\n");
		sb.append("import io.appium.java_client.android.AndroidDriver;\r\n");
		sb.append("\r\nimport io.appium.java_client.android.AndroidElement; \r\n");
		sb.append("import org.openqa.selenium.By;\r\n");
		sb.append("import com.haoyun.automationtesting.framework.Config;\r\n");
		sb.append("import com.haoyun.automationtesting.framework.mysql;\r\n");
		sb.append("import com.haoyun.automationtesting.framework.Robots;\r\n");
		sb.append("import com.haoyun.automationtesting.framework.Alert;\r\n");

		sb.append("import com.haoyun.automationtesting.framework.action;\r\n");
		sb.append("import com.haoyun.automationtesting.framework.BaseObj;\r\n");
		sb.append("import com.haoyun.automationtesting.framework.ExcelOperate;\r\n");
		sb.append("import com.haoyun.automationtesting.framework.TestCase;\r\n");
		sb.append("import com.haoyun.automationtesting.framework.log;\r\n");
		sb.append("import org.openqa.selenium.interactions.Actions;\r\n");

		sb.append("  \r\n");
		sb.append("import com.haoyun.automationtesting.framework.log;\r\n");
		sb.append("import com.haoyun.automationtesting.test.aadomain.mainStart;\r\n");
		sb.append(out_config_import() + "\r\n");

		sb.append("  \r\n\r\n");
		// String
		// n=" /*** \r\n   * @功能模块:我的 \r\n  * @作用:页面元素定位,包括属性数据及方法  \r\n  * @author admin \r\n  */\r\n";

		sb.append(" /*** \r\n   * @用例名称:" + casename
				+ "\r\n  * @author lisheng \r\n  */\r\n");

		sb.append("\r\npublic class  " + classname
				+ " extends action implements TestCase  {\r\n");

		sb.append("    public  " + classname + "() {\r\n");
		sb.append("    }\r\n");
		sb.append("\r\n\r\n");
		sb.append("\r\n    public " + classname + "(WebDriver driver) {\r\n");
		sb.append("    	super(driver);\r\n");
		sb.append("    }\r\n");
		sb.append("\r\n\r\n");
		sb.append("    @Override\r\n");

		sb.append("    public void testcase() throws Exception {\r\n");
		sb.append("    String sheetname=" + classname
				+ ".class.getPackage().getName().split(\"\\\\.\")[4]; \r\n");

		sb.append("        int index = ExcelOperate.getclassname_rows(this.getClass().getSimpleName(),sheetname);// 获取该用例在excel中的行数\r\n");
		sb.append("        try{//CaseStepStart \r\n");
		sb.append("            " + out_config() + "\r\n");
		try {
			int getcells = getcells(SheetAt, i);
			// System.out.println("当前行中一共有多少列数据：" + getcells);

			// int rowscount = getrows(SheetAt);
			// System.out.println("总行数：" + rowscount);

			for (int j = 6; j < getcells + 1; j++) { // 第7列开始为数据项
				sb.append("            " + step(SheetAt, i, j) + "\r\n");
				// System.out.println("当前拼接的代码为：" + step(SheetAt, i, j));
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.logWarn("AutoCodeTest error:" + e);
		}
		sb.append("            //CaseStepEnd  \r\n");
		// sb.append("            System.out.println(\"ok\"); \r\n");
		sb.append("        }  catch(Exception e){\r\n");
		sb.append("              log.logError(\"" + classname
				+ "--用例验证失败\");\r\n");
		sb.append("              ExcelOperate.cellexcelerr(sheetname,index);\r\n");

		sb.append("              e.printStackTrace();\r\n");
		sb.append("        }    \r\n");
		sb.append("    }\r\n");

		sb.append("}\r\n");

		casecount = casecount + 1; // 统计生成用例个数

		return sb.toString();
	}

	/***
	 * 针对选择的sheet页，读取某行某列的数据
	 * 
	 * @param sheetname
	 *            ：sheet页编号（0，1，2.。。）,0为起始页
	 * @param i
	 *            ：行数
	 * @param j
	 *            ：列数
	 * @return 读到的字符串
	 * @throws Exception
	 */
	@SuppressWarnings("static-access")
	public static String getexcel_at(int SheetAt, int i, int j)
			throws Exception {

		// File directory = new File(".");
		// String filePath = directory.getCanonicalPath() +
		// "/resource/case.xlsx";
		// if (!pwd.endsWith(".xlsx")) {
		// System.out.println("文件不是excel类型");
		// }
		FileInputStream fis = null;
		Workbook wookbook = null;

		try {
			// 获取一个绝对地址的流
			fis = new FileInputStream(pwd);
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			// 2007版本的excel，用.xlsx结尾

			wookbook = new XSSFWorkbook(fis);// 得到工作簿
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		int rowSize = 0;

		Cell cell = null;

		// 得到一个工作表
		Sheet st = wookbook.getSheetAt(SheetAt);
		Row row = st.getRow(i - 1);
		String[] values = new String[rowSize];
		Arrays.fill(values, "");
		String value = "";
		cell = row.getCell(j - 1);
		// value = cell.getStringCellValue().trim();

		if (cell != null) {
			// 注意：一定要设成这个，否则可能会出现乱码
			// cell.setEncoding(HSSFCell.ENCODING_UTF_16);

			switch (cell.getCellType()) {

			case Cell.CELL_TYPE_STRING:

				value = cell.getStringCellValue().trim();
				break;
			case Cell.CELL_TYPE_NUMERIC:
				if (DateUtil.isCellDateFormatted(cell)) {
					Date date = cell.getDateCellValue();
					if (date != null) {
						value = new SimpleDateFormat("yyyy-MM-dd").format(date);
					} else {
						value = "";
					}
				} else {
					value = new DecimalFormat("0").format(cell
							.getNumericCellValue());
				}
				break;
			case Cell.CELL_TYPE_FORMULA:
				// 处理函数单元格问题，先将单元格内容转为String
				cell.setCellType(Cell.CELL_TYPE_STRING);
				value = cell.getStringCellValue().trim();
				// 导入时如果为公式生成的数据则无值
				if (!cell.getStringCellValue().equals("")) {
					value = cell.getStringCellValue();
				} else {
					value = cell.getNumericCellValue() + "";
				}
				break;
			case Cell.CELL_TYPE_BLANK:
				break;
			case Cell.CELL_TYPE_ERROR:
				value = "";
				break;
			case Cell.CELL_TYPE_BOOLEAN:
				value = (cell.getBooleanCellValue() == true ? "Y" : "N");
				break;
			default:
				value = "";
			}
		}

		return value;

	}

	/***
	 * 获取case文件夹下“case.xlsx”文件的sheet页数
	 * 
	 * @return
	 * @throws Exception
	 */
	public static int issheet_at() throws Exception {
		// if (!pwd.endsWith(".xlsx")) {
		// System.out.println("文件不是excel类型");
		// }
		FileInputStream fis = null;
		Workbook wookbook = null;

		try {
			// 获取一个绝对地址的流
			fis = new FileInputStream(pwd);
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			// 2007版本的excel，用.xlsx结尾

			wookbook = new XSSFWorkbook(fis);// 得到工作簿
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		int at = wookbook.getNumberOfSheets();
		// System.out.println(at);

		return at;

	}

	/***
	 * 获取一行用例的 列数
	 * 
	 * @param SheetAt
	 * @param i
	 *            ：选择的行数
	 * @return
	 * @throws Exception
	 */
	public static int getcells(int SheetAt, int i) throws Exception {

		// if (!pwd.endsWith(".xlsx")) {
		// System.out.println("文件不是excel类型");
		// }
		FileInputStream fis = null;
		Workbook wookbook = null;

		try {
			// 获取一个绝对地址的流
			fis = new FileInputStream(pwd);
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			// 2007版本的excel，用.xlsx结尾

			wookbook = new XSSFWorkbook(fis);// 得到工作簿
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Sheet st = wookbook.getSheetAt(SheetAt);
		Row row = st.getRow(i - 1);

		int rowcount = row.getLastCellNum();

		return rowcount;

	}

	/***
	 * 获取某sheet页中数据的行数
	 * 
	 * @param SheetAt
	 * @return
	 * @throws Exception
	 */
	public static int getrows(int SheetAt) throws Exception {
		// if (!pwd.endsWith(".xlsx")) {
		// System.out.println("文件不是excel类型");
		// }
		FileInputStream fis = null;
		Workbook wookbook = null;

		try {
			// 获取一个绝对地址的流
			fis = new FileInputStream(pwd);
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			// 2007版本的excel，用.xlsx结尾

			wookbook = new XSSFWorkbook(fis);// 得到工作簿
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Sheet st = wookbook.getSheetAt(SheetAt);

		int rowcount = st.getLastRowNum();

		return rowcount;

	}

	/***
	 * 获取某sheet页的名字
	 * 
	 * @param SheetAt
	 * @return
	 * @throws Exception
	 */
	public static String getsheetname(int SheetAt) throws Exception {
		// if (!pwd.endsWith(".xlsx")) {
		// System.out.println("文件不是excel类型");
		// }
		FileInputStream fis = null;
		Workbook wookbook = null;

		try {
			// 获取一个绝对地址的流
			fis = new FileInputStream(pwd);
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			// 2007版本的excel，用.xlsx结尾

			wookbook = new XSSFWorkbook(fis);// 得到工作簿
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Sheet st = wookbook.getSheetAt(SheetAt);

		String sn = st.getSheetName();

		return sn;

	}

}
