package com.haoyun.automationtesting.framework;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

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
 * 
 *
 * 
 * @author lisheng
 *
 */

public class AutoCase_SinglePackage {


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
	 * @param exeswitch
	 *            执行开关 0和1
	 * @throws IOException
	 */
	@Test(priority = 0)
	public static void inputexcel(String packagename, String classname,
			String casename, String exeswitch)
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
				} catch (Exception e) {
					// e.printStackTrace();
				}
				if ((vr.trim()).equals(classname.trim())) {
					bl = bl + 1;
				}
			}

			// System.out.println("bl"+bl);
			if (bl == 0) {

				// 写入第一行用例
				sheet.createRow(rowlenth + 1);
				sheet.getRow(rowlenth + 1).setHeightInPoints(40);// 设置行高

				sheet.getRow(rowlenth + 1).createCell(0)
						.setCellValue(classname);// 写入class类名
				sheet.getRow(rowlenth + 1).createCell(1).setCellValue(casename);// 写入用例名
				sheet.getRow(rowlenth + 1).createCell(4)
						.setCellValue(exeswitch);// 写入是否执行0，1

			}

			FileOutputStream out = new FileOutputStream(pwd()); // 写数据

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
