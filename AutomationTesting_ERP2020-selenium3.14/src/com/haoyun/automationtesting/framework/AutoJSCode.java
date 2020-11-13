package com.haoyun.automationtesting.framework;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import javax.swing.JOptionPane;

import org.testng.annotations.Test;

/***
 * 
 * 功能说明：将自动化录制工具生成的代码经过筛选转换到本框架中。 取外部文件的路径是 在config.xml:AutoUIrecorderToCode_pwd
 * 文件名命名规则： 1、前面是模块名称（包名）后面是用例名（类名），中间以“666”隔开
 * 2、模块名称（包名）和用例名（类名）第一位必须为字母。模块名称第一位最好为大写字母。具体命名规则参考操作手册中的说明。
 * 3、举例“HY001login666HY0010001”
 * 
 * @author lisheng
 *
 */

public class AutoJSCode {

	 @Test
	public static void run() throws Exception {
		try {
			String in = JOptionPane
					.showInputDialog("请输入要转换的文件名（前面是模块名称（包名）后面是用例名（类名），中间以“666”隔开）:");
			if (in.trim().isEmpty()) {
				JOptionPane.showMessageDialog(null, "输入的内容为空", "输入文件名格式不正确",
						JOptionPane.ERROR_MESSAGE);
			} else {

				if (in.trim().contains("666") && !in.trim().startsWith("666")
						&& !in.trim().endsWith("666")) {
					char c1 = in.trim().split("666")[0].charAt(0);
					char c2 = in.trim().split("666")[1].charAt(0);
					// System.out.println(c1);
					// System.out.println(c2);

					if (((c1 >= 'a' && c1 <= 'z') || (c1 >= 'A' && c1 <= 'Z'))
							&& ((c2 >= 'a' && c2 <= 'z') || (c2 >= 'A' && c2 <= 'Z'))) {

						charOutStream(in);// 执行
						System.out
								.println("用例生成完成，如果是在eclipse中操作，请在操作后刷新代码后才能显示");
						JOptionPane.showMessageDialog(null,
								"如果是在eclipse中操作，请在操作后刷新代码后才能显示", "用例生成完成",
								JOptionPane.PLAIN_MESSAGE);

					} else {
						JOptionPane.showMessageDialog(null,
								"第一位和666后的第一位必须为字母", "输入文件名格式不正确",
								JOptionPane.ERROR_MESSAGE);
					}

				} else {
					JOptionPane.showMessageDialog(null, "要包含666", "输入文件名格式不正确",
							JOptionPane.ERROR_MESSAGE);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//@Test
	public void runtest() throws Exception {
		filerule("sdf");
	}

	public static String filerule(String extfile) throws Exception {

		StringBuffer sb_all = new StringBuffer("");// 可用的操作包括对象和操作，全面放入数组

		String fileName;

		fileName = file_pwd() + "\\" + extfile + ".js";

		InputStreamReader isr = new InputStreamReader(new FileInputStream(
				new File(fileName)), "UTF-8");
		BufferedReader br = new BufferedReader(isr);
		String filewenben;

		StringBuffer sb = new StringBuffer();
		while ((filewenben = br.readLine()) != null) {
			sb.append(filewenben + "\n");
		}
		br.close();
		// System.out.println(sb.toString());

		String jianqie = sb.toString();// 获取整个java文件的内容
		try {

			int casecount = 0;// 计数
			int length = 0;

			String temp2[] = jianqie.split(";");

			length = temp2.length;
			String duixiang = "";
			String sendkeyname = "";
			for (int i = 0; i < length; i++) {// 对单个动作进行判断写入操作

				String fileNameNow = temp2[i];

				if (fileNameNow.trim().contains("click(0)")
						|| fileNameNow.trim().contains("sendKeys")) {

					if (fileNameNow.trim().contains("wait")) {
						try {
							duixiang = fileNameNow.trim().split("wait\\('")[1]
									.split("',")[0];
							
							duixiang=duixiang.replaceAll("\"", "'");
							if(duixiang.startsWith("//")){//如果存在//就去掉//
								duixiang=duixiang.split("//")[1];
							}else if(duixiang.contains("[")){//如果存在【就在后面加@
								String[] duiixiang1=duixiang.split("\\[");
								duixiang=duiixiang1[0]+"[@"+duiixiang1[1];
								//System.out.println("duixiang:::"+duixiang);
								
							}
						} catch (Exception e) {
							// e.printStackTrace();
						}

						// System.out.println("duixiang:::"+ duixiang );
						//System.out.println("temp2[i]:::" + temp2[i]);
						if (temp2[i].contains("sendKeys")) {
							try {
								sendkeyname = temp2[i]
										.split("driver.sendKeys\\('")[1]
										.split("'")[0];
							} catch (Exception e) {
								// e.printStackTrace();
							}
							sb_all.append("            driver.findElement(By.xpath(\"//"
									+ duixiang + "\")).sendKeys(\"" + sendkeyname
									+ "\");\r\n");

						} else {
							sb_all.append("            driver.findElement(By.xpath(\"//"
									+ duixiang + "\")).click();\r\n");

						}

						casecount = casecount + 1;
					}

				}
			}
			System.out.println("casecount:::" + casecount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(sb_all.toString());

		return sb_all.toString();
	}

	public static void charOutStream(String extfile) throws Exception {
		// 1：利用File类找到要操作的对象

		String package_name = "";
		String classname = "";

		try {
			package_name = extfile.split("666")[0];// 用666来划分包名和类名
			classname = extfile.split("666")[1];
		} catch (Exception e) {
			e.printStackTrace();
		}

		File directory = new File(".");
		String fileName = directory.getCanonicalPath()
				+ "\\src\\com\\haoyun\\automationtesting\\test\\"
				+ package_name + "\\" + classname + ".java";
		File file = new File(fileName);

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
		out.write(AutoCodeTest(extfile, sBuffer));

		out.flush();
		out.close();

	}

	public static String out_config() {// 导入首行用例
		return Config.autocode_1;
	}

	public static String out_config_import() {// 导入import
		return Config.autocode_2;
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
	public static String AutoCodeTest(String extfile, StringBuffer sb)
			throws Exception {
		String casename = "";
		String packagename = "";
		String classname = "";

		try {
			packagename = extfile.split("666")[0];// 用666来划分包名和类名
			classname = extfile.split("666")[1];
		} catch (Exception e) {
			e.printStackTrace();
		}

		casename = classname;// 暂时把类名赋给用例名，生成后可以在代码中修改
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
		sb.append("import com.haoyun.automationtesting.framework.DB2conn;\r\n");
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
		sb.append("        try{//CaseStepStart  \r\n");
		sb.append("            " + out_config() + "\r\n");
		try {

			sb.append("            " + filerule(extfile) + "\r\n");

		} catch (Exception e) {
			e.printStackTrace();
			log.logWarn("error:" + e);
		}
		
		sb.append("            //action.isObjectExist_cell(\"检查点内容\", \"用例验证成功\", \"用例验证失败\", sheetname, index);\r\n");
		sb.append("            //CaseStepEnd  \r\n");
		sb.append("        }  catch(Exception e){\r\n");
		sb.append("              log.logError(\"" + classname
				+ "--用例验证失败\");\r\n");
		sb.append("              ExcelOperate.cellexcelerr(sheetname,index);\r\n");

		sb.append("              e.printStackTrace();\r\n");
		sb.append("        }    \r\n");
		sb.append("    }\r\n");

		sb.append("}\r\n");

		return sb.toString();
	}

	public static String file_pwd() throws IOException {
		String filePath = "";

		return filePath = Config.AutoUIrecorderToCode_pwd;//"C:/nodejs/sample";
	}

}
