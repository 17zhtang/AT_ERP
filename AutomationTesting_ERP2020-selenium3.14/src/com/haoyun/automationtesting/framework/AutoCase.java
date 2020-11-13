package com.haoyun.automationtesting.framework;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFDataFormat;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

/***最新更新：只在excel中添加类名，用例名称，执行时间，是否通过，是否执行几个页签。
 * 代码转换成excel中用例.excel路径为工程目录下“/resource/case.xlsx”
 * 功能说明：将包名包含“com.haoyun.automationtesting.test”的包下面的类自动转换成excel中测试用例
 * 1、当类中包含//noautocase时该类不转换 2、程序入口MainAutoCase(),、执行前需关闭excel
 * 3、同一个包下面，类名在excel中已经存在的将不执行 4、转换元素对象：同一个类中有相同元素对象时合并成一个对象。
 * 5、转换用例规则：一个java类对应excel中一行测试用例
 * ；暂时先实现click（），sendkey（）。sleep（），robot（）等几个方法的转换。
 * 
 * @author lisheng
 *
 */

public class AutoCase {

	@Test(priority = 0)
	public static void MainAutoCase() {

		Set<Class<?>> classes = getClasses("com.haoyun.automationtesting.test");
		for (Class<?> clazz : classes) {
			// if (clazz.getPackage() != null &&
			// "appium.frame.testcase".equals(clazz.getPackage().getName())) {
			// System.out.println(clazz.getName());

			if (TestCase.class.isAssignableFrom(clazz) && !clazz.isInterface()) {

				try {

					// System.out.println(clazz.getName());//获取到了类名

					String classnameall = clazz.getName();
					String temp[] = classnameall.split("\\.");
					String classname = temp[temp.length - 1];// 类名
					String packagename = temp[temp.length - 2];// 包名
					String casename = "";// 用例名称
					// System.out.println(packagename);
					// System.out.println(classname);

					// C:\AutomationTest\AutomationTestingFrameWork_v1.0-selenium3.9-20190517\src\com\haoyun\automationtesting\test

					int index_dx = 0;// 记录写入元素对象个数
					int index_bz = 0;// 记录用例操作个数
					StringBuffer sb_all = new StringBuffer("");// 可用的操作包括对象和操作，全面放入数组
					StringBuffer sb_dx = new StringBuffer("");// 元素对象放入的数组
					StringBuffer sb_bz = new StringBuffer("");// 元素操作放入的数组

					String fileName;

					fileName = package_pwd() + "\\" + packagename + "\\"
							+ classname + ".java";

					InputStreamReader isr = new InputStreamReader(
							new FileInputStream(new File(fileName)), "UTF-8");
					BufferedReader br = new BufferedReader(isr);
					String filewenben;

					StringBuffer sb = new StringBuffer();
					while ((filewenben = br.readLine()) != null) {
						sb.append(filewenben + "\n");
					}
					br.close();
					// System.out.println(sb);

					String jianqie = sb.toString();// 获取整个java文件的内容
					// System.out.println("sb::"+sb);
					if (jianqie.contains("用例名称:")) {
						String[] jq = jianqie.split("用例名称:");
						casename = jq[1].split("\n|\r")[0];
						// System.out.println(casename);
					}

					if (jianqie.contains("//CaseStepStart")) {
						int jq1 = jianqie.indexOf("//CaseStepStart");

						jianqie = jianqie.substring(jq1, jianqie.length());
						if (jianqie.contains("//CaseStepEnd")) {
							int jq2 = jianqie.indexOf("//CaseStepEnd");
							jianqie = jianqie.substring(0, jq2);
							// System.out.println("================" + jianqie);
						}
					}

					String temp2[] = jianqie.split("\n|\r");

					if (jianqie.contains("//noautocase")) {
						System.out.println("packagename:" + packagename
								+ ".classname:" + classname
								+ "---设置了不执行，需要执行请在代码中去掉//noautocase");
					} else {

						int length = temp2.length;

						for (int i = 0; i < length; i++) {// 对单个动作进行判断写入操作

							String fileNameNow = temp2[i];

							if (fileNameNow.contains("driver")
									&& fileNameNow.contains("By.")) {// 以driver开头的代码为操作元素对象，需要写入对元素对象和步骤两部分。
								// System.out.println(fileNameNow);

								if (fileNameNow.contains("cssSelector")) {
									// System.out.println("cssSelector对象暂时不写入对象库");

								} else {
									// System.out.println(packagename+classname);
									String[] inputexcel = fileNameNow
											.split("\\.");
									String duixiang = "";
									if (fileNameNow.contains("findElements")) {
										duixiang = inputexcel[inputexcel.length - 3];// 获取元素对象
									} else {
										duixiang = inputexcel[inputexcel.length - 2];// 获取元素对象
									}

									// System.out.println(duixiang);
									String duixiang1 = duixiang.substring(0,
											duixiang.length() - 1);// 最终的元素对象，需要写入到excel中
									// System.out.println(duixiang1);

									// String shuju =
									// inputexcel[inputexcel.length - 1];//
									// 获取动作和数据
									// System.out.println(shuju);

									if (!sb_dx.toString().contains(
											duixiang1.trim())) {// 去掉重复的元素对象
										sb_dx.append(duixiang1.trim() + "____");// 4个下划线来划分
										index_dx = index_dx + 1;// 记录+1
									}

								}

							}
							if (!fileNameNow.trim().isEmpty()
									&& !fileNameNow.trim().startsWith("//")) {
								// System.out.println(fileNameNow.trim());
								sb_all.append(fileNameNow.trim() + "____");// 一条可用的用例全部放入数组，每个以____划分
								index_bz = index_bz + 1;// 用例操作个数记录+1
							}

						}

					}
					// System.out.println(packagename + classname + "元素对象:"
					// + sb_dx.toString());
					// System.out.println(packagename + classname +
					// "操作步骤:"+sb_bz.toString());
					// System.out.println(packagename + classname + "全部操作步骤:"
					// + sb_all.toString());

					if (index_bz == 0) {

					} else {
//						System.out.println("packagename:" + packagename
//								+ ".classname:" + classname + "元素对象个数:"
//								+ index_dx + ".用例操作个数" + index_bz);
					}

					if (index_bz != 0) {

//						inputexcel(packagename, classname, casename,
//								sb_dx.toString(), sb_all.toString(), "0");// 写入excel数据，最后的0为执行状态，0执行1不执行
						inputexcel(packagename, classname, casename,
								"", "", "0");// 只写入类名用例名等，不写入用例数据。最后的0为执行状态，0执行1不执行
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			// }
		}
		System.out.println("完成写入excel数据！");
	}

	/**
	 * 从包package中获取所有的Class
	 * 
	 * @param pack
	 * @return
	 */
	public static Set<Class<?>> getClasses(String pack) {

		// 第一个class类的集合
		Set<Class<?>> classes = new LinkedHashSet<Class<?>>();
		// 是否循环迭代
		boolean recursive = true;
		// 获取包的名字 并进行替换
		String packageName = pack;
		String packageDirName = packageName.replace('.', '/');
		// 定义一个枚举的集合 并进行循环来处理这个目录下的things
		Enumeration<URL> dirs;
		try {
			dirs = Thread.currentThread().getContextClassLoader()
					.getResources(packageDirName);
			// 循环迭代下去
			while (dirs.hasMoreElements()) {
				// 获取下一个元素
				URL url = dirs.nextElement();
				// 得到协议的名称
				String protocol = url.getProtocol();
				// 如果是以文件的形式保存在服务器上
				if ("file".equals(protocol)) {
					// System.err.println("file类型的扫描");
					// 获取包的物理路径
					String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
					// 以文件的方式扫描整个包下的文件 并添加到集合中
					findAndAddClassesInPackageByFile(packageName, filePath,
							recursive, classes);
				} else if ("jar".equals(protocol)) {
					// 如果是jar包文件
					// 定义一个JarFile
					// System.err.println("jar类型的扫描");
					JarFile jar;
					try {
						// 获取jar
						jar = ((JarURLConnection) url.openConnection())
								.getJarFile();
						// 从此jar包 得到一个枚举类
						Enumeration<JarEntry> entries = jar.entries();
						// 同样的进行循环迭代
						while (entries.hasMoreElements()) {
							// 获取jar里的一个实体 可以是目录 和一些jar包里的其他文件 如META-INF等文件
							JarEntry entry = entries.nextElement();
							String name = entry.getName();
							// 如果是以/开头的
							if (name.charAt(0) == '/') {
								// 获取后面的字符串
								name = name.substring(1);
							}
							// 如果前半部分和定义的包名相同
							if (name.startsWith(packageDirName)) {
								int idx = name.lastIndexOf('/');
								// 如果以"/"结尾 是一个包
								if (idx != -1) {
									// 获取包名 把"/"替换成"."
									packageName = name.substring(0, idx)
											.replace('/', '.');
								}
								// 如果可以迭代下去 并且是一个包
								if ((idx != -1) || recursive) {
									// 如果是一个.class文件 而且不是目录
									if (name.endsWith(".class")
											&& !entry.isDirectory()) {
										// 去掉后面的".class" 获取真正的类名
										String className = name.substring(
												packageName.length() + 1,
												name.length() - 6);
										try {
											// 添加到classes
											classes.add(Class
													.forName(packageName + '.'
															+ className));
										} catch (ClassNotFoundException e) {
											// log
											// .error("添加用户自定义视图类错误
											// 找不到此类的.class文件");
											e.printStackTrace();
										}
									}
								}
							}
						}
					} catch (IOException e) {
						// log.error("在扫描用户定义视图时从jar包获取文件出错");
						e.printStackTrace();
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return classes;
	}

	/**
	 * 以文件的形式来获取包下的所有Class
	 * 
	 * @param packageName
	 * @param packagePath
	 * @param recursive
	 * @param classes
	 */
	public static void findAndAddClassesInPackageByFile(String packageName,
			String packagePath, final boolean recursive, Set<Class<?>> classes) {
		// 获取此包的目录 建立一个File
		File dir = new File(packagePath);
		// 如果不存在或者 也不是目录就直接返回
		if (!dir.exists() || !dir.isDirectory()) {
			// log.warn("用户定义包名 " + packageName + " 下没有任何文件");
			return;
		}
		// 如果存在 就获取包下的所有文件 包括目录
		File[] dirfiles = dir.listFiles(new FileFilter() {
			// 自定义过滤规则 如果可以循环(包含子目录) 或则是以.class结尾的文件(编译好的java类文件)
			@Override
			public boolean accept(File file) {
				return (recursive && file.isDirectory())
						|| (file.getName().endsWith(".class"));
			}
		});
		// 循环所有文件
		for (File file : dirfiles) {
			// 如果是目录 则继续扫描
			if (file.isDirectory()) {
				findAndAddClassesInPackageByFile(
						packageName + "." + file.getName(),
						file.getAbsolutePath(), recursive, classes);
			} else {
				// 如果是java类文件 去掉后面的.class 只留下类名
				String className = file.getName().substring(0,
						file.getName().length() - 6);
				try {
					// 添加到集合中去
					// classes.add(Class.forName(packageName + '.' +
					// className));
					// 经过回复同学的提醒，这里用forName有一些不好，会触发static方法，没有使用classLoader的load干净
					classes.add(Thread.currentThread().getContextClassLoader()
							.loadClass(packageName + '.' + className));
				} catch (ClassNotFoundException e) {
					// log.error("添加用户自定义视图类错误 找不到此类的.class文件");
					e.printStackTrace();
				}
			}
		}
	}

	public static String pwd() throws IOException {
		String filePath = "";
		File directory = new File(".");
		return filePath = directory.getCanonicalPath() + "/resource/case.xlsx";
	}

	public static String package_pwd() throws IOException {
		String filePath = "";
		File directory = new File(".");
		return filePath = directory.getCanonicalPath()
				+ "/src/com/haoyun/automationtesting/test";
	}

	public static String fileRead(String pwd) throws Exception {

		File file = new File(pwd);// 定义一个file对象，用来初始化FileReader

		FileReader reader = new FileReader(file);// 定义一个fileReader对象，用来初始化BufferedReader

		BufferedReader bReader = new BufferedReader(reader);// new一个BufferedReader对象，将文件内容读取到缓存

		StringBuilder sb = new StringBuilder();// 定义一个字符串缓存，将字符串存放缓存中

		String s = "";

		while ((s = bReader.readLine()) != null) {// 逐行读取文件内容，不读取换行符和末尾的空格

			sb.append(s + "\n");// 将读取的字符串添加换行符后累加存放在缓存中

			// System.out.println(s);

		}

		bReader.close();

		String str = sb.toString();

		return str;
	}

	/***
	 * 写入excel,包括各种规则判断
	 * 
	 * @param packagename
	 *            包名
	 * @param classname
	 *            类名
	 * @param casename
	 *            用例名
	 * @param duixiang1
	 *            写入excel的元素对象，所有的对象
	 * @param shuju
	 *            写入excel的整个动作，所有的动作
	 * @param exeswitch
	 *            执行开关 0和1
	 * @throws IOException
	 */
	public static void inputexcel(String packagename, String classname,
			String casename, String duixiang1, String shuju, String exeswitch)
			throws IOException {

		try {

			if (!pwd().endsWith(".xlsx")) {
				System.out.println("文件不是excel类型");
			}
			FileInputStream fis = null;
			XSSFWorkbook wookbook = null;
			try {
				fis = new FileInputStream(pwd());
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

			Sheet sheet = wookbook.getSheet(packagename); // 获取到工作表，因为一个excel可能有多个工作表

			if (sheet == null) {
//				System.out.println("sheet：" + packagename
//						+ "不存在，创建该sheet，请稍后……");
				sheet = wookbook.createSheet(packagename);

				// sheet.setCellType(HSSFCell.CELL_TYPE_STRING);
				// sheet.autoSizeColumn(1);//自适应列宽度,未生效

				for (int i = 0; i < 4; i++) {
					sheet.createRow(i);

					// sheet.getRow(i).setRowStyle(XSSFCell.CELL_TYPE_STRING);
				}

				// sheet.getRow(1).createCell(0).setCellStyle(cellStyle2);
				// sheet.getRow(1).createCell(0).setCellType(XSSFCell.CELL_TYPE_STRING);

				for (int i = 0; i < 100; i++) {
					sheet.getRow(0).createCell(i)
							.setCellValue(String.valueOf(i));
				}
				// setcell(sheet.getRow(1).createCell(0),wookbook);
				sheet.getRow(0).setHeightInPoints(1);// 设置行高,隐藏
				sheet.getRow(1).setHeightInPoints(1);// 设置行高,隐藏
				sheet.getRow(2).setHeightInPoints(1);// 设置行高,隐藏
				sheet.getRow(3).setHeightInPoints(40);// 设置行高
				
				//设置列宽
				sheet.setColumnWidth(0, 4000);
				sheet.setColumnWidth(1, 30000);
				sheet.setColumnWidth(2, 5000);

				XSSFCellStyle cellStyle = wookbook.createCellStyle();
				XSSFFont font = wookbook.createFont();

				font.setFontName("仿宋_GB2312");

				font.setFontHeightInPoints((short) 10);// 设置字体大小
				font.setBoldweight(Font.BOLDWEIGHT_BOLD);// 粗体显示

				cellStyle.setFont(font);// 选择需要用到的字体格式

				XSSFCellStyle cellStyleyanse = wookbook.createCellStyle();
				cellStyleyanse.setFillForegroundColor((short) 41);// 设置背景色 13是黄色

				cellStyleyanse.setFillPattern(CellStyle.SOLID_FOREGROUND);

				sheet.getRow(1).createCell(0).setCellStyle(cellStyle);
				// sheet.getRow(2).createCell(0).setCellStyle(cellStyle);
				sheet.getRow(3).createCell(0).setCellStyle(cellStyle);
				sheet.getRow(3).createCell(1).setCellStyle(cellStyle);
				sheet.getRow(3).createCell(2).setCellStyle(cellStyle);
				sheet.getRow(3).createCell(3).setCellStyle(cellStyle);
				sheet.getRow(3).createCell(4).setCellStyle(cellStyle);
				sheet.getRow(3).createCell(5).setCellStyle(cellStyle);

				sheet.getRow(1).getCell(0).setCellValue("元素对象库");
				sheet.getRow(2).createCell(0).setCellValue("华丽分割线");
				sheet.getRow(3).getCell(0).setCellValue("用例类名");
				sheet.getRow(3).getCell(1).setCellValue("用例名称");
				sheet.getRow(3).getCell(2).setCellValue("执行时间");
				sheet.getRow(3).getCell(3).setCellValue("是否通过");
				sheet.getRow(3).getCell(4).setCellValue("是否执行");
				sheet.getRow(3)
						.getCell(5)
						.setCellValue("备用");
								//"步骤&数据(编写规则：格式为“*-*-*”，以“-”隔开，第一项“*”取表中“元素对象”对应的行号；第二项“*”输入1至100的数字；第三项为可选。具体规则见sheet“结果统计”中的“用例编写规则”");

				sheet.getRow(3).getCell(0).setCellStyle(cellStyleyanse);
				sheet.getRow(3).getCell(1).setCellStyle(cellStyleyanse);
				sheet.getRow(3).getCell(2).setCellStyle(cellStyleyanse);
				sheet.getRow(3).getCell(3).setCellStyle(cellStyleyanse);
				sheet.getRow(3).getCell(4).setCellStyle(cellStyleyanse);
				sheet.getRow(3).getCell(5).setCellStyle(cellStyleyanse);
				sheet.getRow(3).setRowStyle(cellStyleyanse);

				// 合并单元格
				//CellRangeAddress region1 = new CellRangeAddress(2, 2,
				//		(short) 0, (short) 50);

				// 参数1：起始行 参数2：终止行 参数3：起始列 参数4：终止列
				// 但应注意两个构造方法的参数不是一样的，具体使用哪个取决于POI的不同版本。

				//sheet.addMergedRegion(region1);

				//sheet.createFreezePane(0, 4, 0, 4);// 固定前面四行

				//System.out.println("sheet：" + packagename + "创建完成");
			}

			XSSFCellStyle cellStyle2 = wookbook.createCellStyle();
			XSSFDataFormat format = wookbook.createDataFormat();

			cellStyle2.setDataFormat(format.getFormat("@")); // 设置单元格为文本格式

			sheet.getRow(1).getCell(0).setCellStyle(cellStyle2);// 设置单元格为文本格式

			int rowlenth = sheet.getLastRowNum();// 获取excel数据行数，增量添加用例
			// System.out.println("rowlenth:" + rowlenth);

			int bl = 0;// 判断类名是否在excel中存在，如果存在就不写入
			for (int rows = 0; rows < rowlenth + 1; rows++) {
				String vr = "";
				try {
					vr = sheet.getRow(rows + 3).getCell(0).getStringCellValue();
					// System.out.println("vr:" + vr);
					// System.out.println("classname.trim():" +
					// classname.trim());
				} catch (Exception e) {
					// e.printStackTrace();
				}
				if ((vr.trim()).equals(classname.trim())) {
					bl = bl + 1;
//					System.out.println("类名为：" + classname
//							+ "已经存在，请在excel中删除该行后再执行。。。");
					// log.logWarn("类名已经存在，请在excel中删除该行后再执行。。。");

				}
			}

			// System.out.println("bl"+bl);
			if (bl == 0) {

				String duixiang11[] = duixiang1.split("____");
				int duixiangindex = duixiang11.length;
				// System.out.println(duixiangindex);

				int celllenth = 0;// 元素数据行中存在多少列数据，之后添加是增量添加
				label: for (int j = 0; j < 200; j++) {
					String values = "";
					try {
						values = sheet.getRow(1).getCell(j + 5)
								.getStringCellValue();

					} catch (Exception e) {
						// e.printStackTrace();
					}
					if (values.trim().isEmpty()) {

						break label;
					} else {
						celllenth = j + 1;
					}
				}
				// System.out.println("第几个" + celllenth);
				for (int i = 0; i < duixiangindex; i++) {// 写入元素对象

					// System.out.println(duixiang11[duixiangindex-(duixiangindex-i)]);
					sheet.getRow(1).createCell(celllenth + i + 5)
							.setCellValue(duixiang11[i]);
					// sheet.getRow(1).createCell(celllenth + i +
					// 5).setCellStyle(cellStyle2);//设置单元格为文本格式

				}

				// 写入第一行用例
				sheet.createRow(rowlenth + 1);
				sheet.getRow(rowlenth + 1).setHeightInPoints(40);// 设置行高

				sheet.getRow(rowlenth + 1).createCell(0)
						.setCellValue(classname);// 写入class类名
				sheet.getRow(rowlenth + 1).createCell(1).setCellValue(casename);// 写入用例名
				sheet.getRow(rowlenth + 1).createCell(4)
						.setCellValue(exeswitch);// 写入是否执行0，1

				String shuju1[] = shuju.split("____");
				int shuju1_index = shuju1.length;

				for (int i = 0; i < shuju1_index; i++) {// 写入所有动作
					Cell sht = sheet.getRow(rowlenth + 1).createCell(i + 5);
					sht.setCellType(Cell.CELL_TYPE_STRING);// 单元格设置成字符串格式
					if (shuju1[i].contains("driver")
							&& shuju1[i].contains("By.")) {// 所有包含driver的动作处理
						if (shuju1[i].contains("cssSelector")) {// 包含css的先直接用x-处理
							sht.setCellValue("x-" + shuju1[i]);
						} else if (shuju1[i].contains("clear")) {
							sht.setCellValue("x-" + shuju1[i]);
						} else {

							for (int j = 0; j < duixiangindex; j++) {
								if (shuju1[i].contains(duixiang11[j])) {
									if (shuju1[i].contains("click")) {
										if (shuju1[i].contains("findElements")) {
											sht.setCellValue(celllenth + j + 5
													+ "-8-0");
										} else {
											sht.setCellValue(celllenth + j + 5
													+ "-1");
										}

									}
									if (shuju1[i].contains("sendKeys")) {// sendkeys是字符串的情况
										
										if (shuju1[i].contains("sendKeys(\"")) {
											String sendkeyname = shuju1[i]
													.split("\"")[shuju1[i]
													.split("\"").length - 2];
											// System.out.println("sendkeyname"+sendkeyname);
											sht.setCellValue((celllenth + j + 5)
													+ "-2-" + sendkeyname);
										} else{// sendkeys是变量的情况，没有“”了
											sht.setCellValue("x-" + shuju1[i]);
										}
									}
								}
							}
						}
					} else if (shuju1[i].contains("action.sleep")) {
						String sleepindex = shuju1[i].trim().substring(13, 14);
						sht.setCellValue("2-4-" + sleepindex);

						// }else if(shuju1[i].contains("isObjectExist_cell")){
						// try{
						// String[] isObjectExist=shuju1[i].trim().split(",");
						// int lent=isObjectExist[0].length();
						// String rs=isObjectExist[0].substring(29, lent);
						// sht.setCellValue("r-"+rs);//还未实现
						// }catch (Exception e) {
						// e.printStackTrace();
						// }
					} else {
						sht.setCellValue("x-" + shuju1[i]);
					}
					sht.setCellStyle(cellStyle2);// 设置单元格为文本格式

				}
			}

			// 设置网格，不成功
			// XSSFCellStyle cellStylekuang = wookbook.createCellStyle();
			// cellStylekuang.setBorderBottom(CellStyle.BORDER_THIN); // 下边框
			// cellStylekuang.setBorderLeft(CellStyle.BORDER_THIN);// 左边框
			// cellStylekuang.setBorderTop(CellStyle.BORDER_THIN);// 上边框
			// cellStylekuang.setBorderRight(CellStyle.BORDER_THIN);// 右边框
			// cellStylekuang.setVerticalAlignment(CellStyle.ALIGN_LEFT);
			// cellStylekuang.setWrapText(true);
			// for(int i=0;i<rowlenth;i++){
			// sheet.getRow(i).setRowStyle(cellStylekuang);
			// }

			FileOutputStream out = new FileOutputStream(pwd()); // 写数据

			// //BufferedOutputStream out1 = null;
			// out1 = new BufferedOutputStream(out);

			// exportExcel(out1);
			out.flush();
			wookbook.write(out);
			out.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void setcell(Cell cell, XSSFWorkbook workbook)
			throws IOException {
		// XSSFWorkbook workbook = new XSSFWorkbook();

		// 创建一个单元格样式
		XSSFCellStyle style = workbook.createCellStyle();
		cell.setCellStyle(style);

		/***************************** 使用默认颜色 **************************************************/
		// 填充色
		// style.setFillForegroundColor(IndexedColors.BLUE.getIndex());
		// style.setFillForegroundColor(HSSFColorPredefined.BLUE.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		// 字体颜色
		Font font = workbook.createFont();
		font.setColor(IndexedColors.WHITE.getIndex());
		style.setFont(font);
		/**************************************************************************************/

		/***************************** 自定义颜色 **************************************************/
		XSSFColor color = new XSSFColor(new java.awt.Color(255, 0, 0));
		style.setFillForegroundColor(color);
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		XSSFColor color1 = new XSSFColor(new java.awt.Color(255, 0, 255));
		// 字体颜色
		XSSFFont font1 = workbook.createFont();
		font1.setColor(color1);
		style.setFont(font1);
		/**************************************************************************************/
	}

}
