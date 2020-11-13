package com.haoyun.automationtesting.framework;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class readExcel {

	// public static String reflectName;
	private String fileName;
	private String reflectName;

	public String getFileName() {
		return fileName;
	}

	public void setFileName() throws InterruptedException, Exception {
		this.fileName = GetFileExcel.OnlyExcel();
	}

	public String getReflectName() {
		return reflectName;
	}

	public void setReflectName() {
		this.reflectName = fileName.substring(0, fileName.lastIndexOf("."));
	}

	@SuppressWarnings({ "unused", "resource", "unchecked", "rawtypes" })
	public List<Object> readExcel_return() throws Exception {

		this.fileName = GetFileExcel.OnlyExcel();
		this.reflectName = fileName.substring(0, fileName.lastIndexOf("."));

		String targetFile = "return_test/" + fileName;

		FileInputStream fis = new FileInputStream(new File(targetFile));
		Workbook wb = WorkbookFactory.create(new File(targetFile));

		Sheet sheet = wb.getSheet("Sheet1");

		List<String> list = new ArrayList<String>();
		// 获取标题行数据存放在list里面
		for (int i = 2; i < sheet.getPhysicalNumberOfRows(); i++) {
			Row r = sheet.getRow(i);
			for (int j = 1; j < r.getPhysicalNumberOfCells(); j++) {
				Cell cell = r.getCell(j);
				String brandName = this.getCellValue(cell);
				if (i == 2 && getCellValue(cell) != "") {
					list.add(getCellValue(cell));
				}
			}
		}
		// 打印标题行内容
		for (String string : list) {
			log.logInfo("存放标题的list的内容是：" + string);
		}
		// 打印标题行数量
		log.logInfo("存放标题的list的数量是：" + list.size());

		List<Object> listTestData = new ArrayList<Object>();
		Method met = null;

		// 反射测试数据对象内容到Object
		for (int i = 3; i < sheet.getPhysicalNumberOfRows(); i++) {
			Row r = sheet.getRow(i);
			Class c = Class.forName("cn.com.hxb.www.bean." + reflectName);
			Object obj = c.newInstance();

			for (int j = 1; j < r.getPhysicalNumberOfCells() - 1; j++) {
				Cell cell = r.getCell(j);
				String brandName = this.getCellValue(cell);
				// 处理字符串
				if (brandName.lastIndexOf("：") >= 0) {
					int stat = brandName.lastIndexOf("：");
					brandName = brandName.substring(0, stat);
				}
				if (brandName.lastIndexOf(" ") >= 0) {
					int stat = brandName.lastIndexOf(" ");
					brandName = brandName.substring(0, stat);
				}
				// System.out.println("获得的参数是="+brandName);
				// System.out.println("获得的方法名="+getMethodName(list.get(j-1)));
				met = c.getDeclaredMethod(getMethodName(list.get(j - 1)),
						String.class);
				met.invoke(obj, brandName);
			}
			listTestData.add(obj);
		}

		log.logInfo("readExcel结束未见异常");
		return listTestData;

	}

	// 获取注入得方法名
	private String getMethodName(String str) {
		String s = "set" + str.substring(0, 1).toUpperCase()
				+ str.substring(1, str.length());
		return s;
	}

	private String getCellValue(Cell cell) {
		int cellType = cell.getCellType();
		String value = "";
		if (cellType == Cell.CELL_TYPE_STRING) {
			value = cell.getStringCellValue();
		} else if (cellType == Cell.CELL_TYPE_NUMERIC) {
			value = String.valueOf(cell.getNumericCellValue());
		} else if (cellType == Cell.CELL_TYPE_BOOLEAN) {
			value = String.valueOf(cell.getBooleanCellValue());
		} else if (cellType == Cell.CELL_TYPE_BLANK) {
			value = "";
		} else if (cellType == Cell.CELL_TYPE_FORMULA) {
			value = String.valueOf(cell.getCellFormula());
		} else {
			value = "";
		}
		return value;
	}

}
