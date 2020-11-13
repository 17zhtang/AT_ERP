package com.haoyun.automationtesting.framework;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelOperate {

	public static int sheet_0 = 0;// sheet页编号,第一个sheet页的编号为0
	public static int sheet_1 = 1;// sheet页编号,第一个sheet页的编号为0
	public static int sheet_2 = 2;// sheet页编号,第一个sheet页的编号为0
	public static String sheet_bg = "自动化测试配置";// sheet页名称
	public static String sheet_zlcal = "主流程案例";// sheet页名称
	public static String sheet_qbal = "全部案例";// sheet页名称

	static String cellexcel_starttime = "";
	static String cellexcel_endtime = "";
	static long alltime;
	static String cellexcel_zxtime = "";

	public ExcelOperate() throws Exception {
		super();

	}

	public static String pwd() throws IOException {
		String filePath = "";
		File directory = new File(".");
		return filePath = directory.getCanonicalPath() + "/resource/case.xlsx";
	}

	/***
	 * 写人执行时间和执行通过
	 * 
	 * @param row
	 *            写入的行数
	 * @throws Exception
	 */
	public static void cellexcelok(String sheetname, int row) throws Exception {
		try {
			// String sheet_name = ExcelOperate.getexcel_sheet0(5, 2);
			String time = new SimpleDateFormat("yyyyMMdd-HHmmss")
					.format(new Date());
			if (!pwd().endsWith(".xlsx")) {
				System.out.println("文件不是excel类型");
			}
			FileInputStream fis = null;
			Workbook wookbook = null;
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
			Sheet sheet = wookbook.getSheet(sheetname); // 获取到工作表，因为一个excel可能有多个工作表

			FileOutputStream out = new FileOutputStream(pwd()); // 写数据

			// sheet.createRow(i-1);//先创建行，不然指针为空
			sheet.getRow(row - 1).createCell(2).setCellValue(time);
			sheet.getRow(row - 1).createCell(3).setCellValue("通过");

			out.flush();
			wookbook.write(out);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 写入excel报告：执行时间、执行通过、执行状态
	 * 
	 * @param sheetname
	 *            shee页
	 * @param row
	 *            写入的行数
	 * @param state
	 *            通过、不通过
	 * @throws Exception
	 */
	public static void cellexcel_SRS(String sheetname, int row, String state,String time)
			throws Exception {
		try {
			// String sheet_name = ExcelOperate.getexcel_sheet0(5, 2);
//			String time = new SimpleDateFormat("yyyyMMdd-HHmmss")
//					.format(new Date());
			if (!pwd().endsWith(".xlsx")) {
				System.out.println("文件不是excel类型");
			}
			FileInputStream fis = null;
			Workbook wookbook = null;
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
			Sheet sheet = wookbook.getSheet(sheetname); // 获取到工作表，因为一个excel可能有多个工作表

			FileOutputStream out = new FileOutputStream(pwd()); // 写数据

			// sheet.createRow(i-1);//先创建行，不然指针为空
			//log.logInfo("time:"+time.length());
			if(time.length()==6){
				sheet.getRow(row - 1).createCell(2).setCellValue(time.substring(0, 2)+":"+time.substring(2, 4)+":"+time.substring(4, 6));
			}
			
			sheet.getRow(row - 1).createCell(3).setCellValue(state);

			out.flush();
			wookbook.write(out);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/***
	 * 写人执行时间和执行未通过
	 * 
	 * @param row
	 *            写入的行数
	 * @throws Exception
	 */
	public static void cellexcelerr(String sheetname, int row) throws Exception {
		try {
			// String sheet_name = ExcelOperate.getexcel_sheet0(5, 2);
			String time = new SimpleDateFormat("yyyyMMdd-HHmmss")
					.format(new Date());
			if (!pwd().endsWith(".xlsx")) {
				System.out.println("文件不是excel类型");
			}
			FileInputStream fis = null;
			Workbook wookbook = null;
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
			Sheet sheet = wookbook.getSheet(sheetname); // 获取到工作表，因为一个excel可能有多个工作表

			FileOutputStream out = new FileOutputStream(pwd()); // 写数据

			// sheet.createRow(i-1).createCell(2).setCellValue("未通过!");//先创建行，不然指针为空,但会清空当前行的数据
			sheet.getRow(row - 1).createCell(2).setCellValue(time);
			sheet.getRow(row - 1).createCell(3).setCellValue("未通过");

			out.flush();
			wookbook.write(out);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/***
	 * 操作除了第一个sheet页后的所有sheet页。初始化执行时间和执行是否通过，所以案例都初始化为“未执行”
	 * 
	 * 
	 * @throws Exception
	 */
	public static void cellexcelinit_sheet() throws Exception {
		try {

			if (!pwd().endsWith(".xlsx")) {
				System.out.println("文件不是excel类型");
			}
			FileInputStream fis = null;
			Workbook wb = null;
			try {
				fis = new FileInputStream(pwd());
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				// 2007版本的excel，用.xlsx结尾
				wb = new XSSFWorkbook(fis);// 得到工作簿
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			FileOutputStream out = new FileOutputStream(pwd()); // 写数据
			for (int sheetindex = 1; sheetindex < wb.getNumberOfSheets(); sheetindex++) {
				String sheetname = wb.getSheetName(sheetindex);
				if (sheetname.trim().isEmpty()) {
					break;

				} else {
					Sheet sheet = wb.getSheetAt(sheetindex); // 获取到工作表，因为一个excel可能有多个工作表
					int sheetnum = sheet.getLastRowNum();
					Cell cell = null;
					// int rowSize = 0;
					// String[] values = new String[rowSize];
					// Arrays.fill(values, "");

					for (int k = 4; k < sheetnum + 1; k++) {// 从第四行开始，初始化将状态写成00000000-00000未执行
						cell = sheet.getRow(k).getCell(1);

						if (cell != null) {
							sheet.getRow(k).createCell(2)
									.setCellValue("00000000-000000");
							sheet.getRow(k).createCell(3).setCellValue("未执行");
						} else {
							// System.out.println("kong");
						}

					}

				}
			}

			out.flush();
			wb.write(out);
			out.close();

			// System.out.println(row.getPhysicalNumberOfCells()+"
			// "+row.getLastCellNum());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/***
	 * 操作除了第一个sheet页后的所有sheet页。初始化执行时间和执行是否通过，所以案例都初始化为“未执行” 所有用例执行状态都设置为1，执行状态
	 * 
	 * @throws Exception
	 */
	public static void cellexcelinit_all() throws Exception {
		try {

			if (!pwd().endsWith(".xlsx")) {
				System.out.println("文件不是excel类型");
			}
			FileInputStream fis = null;
			Workbook wb = null;
			try {
				fis = new FileInputStream(pwd());
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				// 2007版本的excel，用.xlsx结尾
				wb = new XSSFWorkbook(fis);// 得到工作簿
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			FileOutputStream out = new FileOutputStream(pwd()); // 写数据
			for (int sheetindex = 1; sheetindex < wb.getNumberOfSheets(); sheetindex++) {
				String sheetname = wb.getSheetName(sheetindex);
				if (sheetname.trim().isEmpty()) {
					break;

				} else {
					Sheet sheet = wb.getSheetAt(sheetindex); // 获取到工作表，因为一个excel可能有多个工作表
					int sheetnum = sheet.getLastRowNum();
					Cell cell = null;
					// int rowSize = 0;
					// String[] values = new String[rowSize];
					// Arrays.fill(values, "");

					for (int k = 4; k < sheetnum + 1; k++) {// 从第四行开始，初始化将状态写成00000000-00000未执行
						cell = sheet.getRow(k).getCell(1);

						if (cell != null) {
							sheet.getRow(k).createCell(2)
									.setCellValue("00000000-000000");
							sheet.getRow(k).createCell(3).setCellValue("未执行");
							sheet.getRow(k).createCell(4).setCellValue("1");
						} else {
							// System.out.println("kong");
						}

					}

				}
			}

			out.flush();
			wb.write(out);
			out.close();

			// System.out.println(row.getPhysicalNumberOfCells()+"
			// "+row.getLastCellNum());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/***
	 * 操作除了第一个sheet页后的所有sheet页。初始化执行时间和执行是否通过，所以案例都初始化为“未执行” 所有用例执行状态都设置为0，不执行状态
	 * 
	 * @throws Exception
	 */
	public static void cellexcelinit_noall() throws Exception {
		try {

			if (!pwd().endsWith(".xlsx")) {
				System.out.println("文件不是excel类型");
			}
			FileInputStream fis = null;
			Workbook wb = null;
			try {
				fis = new FileInputStream(pwd());
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				// 2007版本的excel，用.xlsx结尾
				wb = new XSSFWorkbook(fis);// 得到工作簿
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			FileOutputStream out = new FileOutputStream(pwd()); // 写数据
			for (int sheetindex = 1; sheetindex < wb.getNumberOfSheets(); sheetindex++) {
				String sheetname = wb.getSheetName(sheetindex);
				if (sheetname.trim().isEmpty()) {
					break;

				} else {
					Sheet sheet = wb.getSheetAt(sheetindex); // 获取到工作表，因为一个excel可能有多个工作表
					int sheetnum = sheet.getLastRowNum();
					Cell cell = null;
					// int rowSize = 0;
					// String[] values = new String[rowSize];
					// Arrays.fill(values, "");

					for (int k = 4; k < sheetnum + 1; k++) {// 从第四行开始，初始化将状态写成00000000-00000未执行
						cell = sheet.getRow(k).getCell(1);

						if (cell != null) {
							sheet.getRow(k).createCell(2)
									.setCellValue("00000000-000000");
							sheet.getRow(k).createCell(3).setCellValue("未执行");
							sheet.getRow(k).createCell(4).setCellValue("0");
							sheet.getRow(k).createCell(5).setCellValue("");// 去掉多余的数据
						} else {
							// System.out.println("kong");
						}

					}

				}
			}

			out.flush();
			wb.write(out);
			out.close();

			// System.out.println(row.getPhysicalNumberOfCells()+"
			// "+row.getLastCellNum());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/***
	 * 还有bug,慎重执行 操作第三个sheet页，初始化执行时间和执行是否通过，所以案例都初始化为“未执行”
	 * 
	 * @param i
	 *            ：行数，起始行为1
	 * @throws Exception
	 */
	public static void cellexcelinit_sheet2() throws Exception, IOException {
		try {

			if (!pwd().endsWith(".xlsx")) {
				System.out.println("文件不是excel类型");
			}
			FileInputStream fis = null;
			Workbook wb = null;
			try {
				fis = new FileInputStream(pwd());
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				// 2007版本的excel，用.xlsx结尾
				wb = new XSSFWorkbook(fis);// 得到工作簿
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			Sheet sheet = wb.getSheetAt(sheet_2); // 获取到工作表，因为一个excel可能有多个工作表
			// HSSFRow row=sheet.getRow(0);
			// //获取第一行（excel中的行默认从0开始，所以这就是为什么，一个excel必须有字段列头），即，字段列头，便于赋值
			// System.out.println(sheet.getLastRowNum()+"
			// "+row.getLastCellNum()); //分别得到最后一行的行号，和一条记录的最后一个单元格
			int sheetnum = sheet.getLastRowNum();
			FileOutputStream out = new FileOutputStream(pwd()); // 写数据
			// row=sheet.createRow((short)(sheet.getLastRowNum()+1));
			// //在现有行号后追加数据
			// row.createCell(0).setCellValue("用例名"); //设置第一个（从0开始）单元格的数据
			// row.createCell(1).setCellValue("是否通过"); //设置第二个（从0开始）单元格的数据
			Cell cell = null;
			int rowSize = 0;
			String[] values = new String[rowSize];
			Arrays.fill(values, "");
			String value = "";
			for (int k = 1; k < sheetnum + 1; k++) {
				// sheet.createRow(k);
				for (int m = 1; m < 10; m++) {

					cell = sheet.getRow(k).getCell(m);

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
									value = new SimpleDateFormat("yyyy-MM-dd")
											.format(date);
								} else {
									value = "";
								}
							} else {
								value = new DecimalFormat("0").format(cell
										.getNumericCellValue());
							}
							break;
						case Cell.CELL_TYPE_FORMULA:
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
							value = (cell.getBooleanCellValue() == true ? "Y"
									: "N");
							break;
						default:
							value = "";
						}

					}
					if (value == "") {
						Row row = sheet.getRow(k);
						sheet.removeRow(row);
						sheet.shiftRows(k + 1, sheet.getLastRowNum(), -1);
					}
				}
				sheet.getRow(k).createCell(2).setCellValue("00000000-000000");
				sheet.getRow(k).createCell(3).setCellValue("未执行");
			}

			out.flush();
			wb.write(out);
			out.close();
			// System.out.println(row.getPhysicalNumberOfCells()+"
			// "+row.getLastCellNum());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/***
	 * 自定义写入某sheet页某行某列数据。
	 * 
	 * @param sheet_int
	 *            选择写入的sheet页,0为第一个sheet页
	 * @param i
	 *            ：行数
	 * @param j
	 *            ：列数
	 * @param str
	 *            ：写入的字符串
	 * @throws Exception
	 */
	public static void cellexcel(String sheetname, int i, int j, String str)
			throws Exception {
		try {

			if (!pwd().endsWith(".xlsx")) {
				System.out.println("文件不是excel类型");
			}
			FileInputStream fis = null;
			Workbook wb = null;
			try {
				fis = new FileInputStream(pwd());
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				// 2007版本的excel，用.xlsx结尾
				wb = new XSSFWorkbook(fis);// 得到工作簿
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			Sheet sheet = wb.getSheet(sheetname); // 获取到工作表，因为一个excel可能有多个工作表
			// HSSFRow row=sheet.getRow(0);
			// //获取第一行（excel中的行默认从0开始，所以这就是为什么，一个excel必须有字段列头），即，字段列头，便于赋值
			// System.out.println(sheet.getLastRowNum()+"
			// "+row.getLastCellNum()); //分别得到最后一行的行号，和一条记录的最后一个单元格

			FileOutputStream out = new FileOutputStream(pwd()); // 写数据
			// row=sheet.createRow((short)(sheet.getLastRowNum()+1));
			// //在现有行号后追加数据
			// row.createCell(0).setCellValue("用例名"); //设置第一个（从0开始）单元格的数据
			// row.createCell(1).setCellValue("是否通过"); //设置第二个（从0开始）单元格的数据

			sheet.getRow(i - 1).createCell(j - 1).setCellValue(str);

			// System.out.println(sheet.getRow(4).getCell(4));

			out.flush();
			wb.write(out);
			out.close();
			// System.out.println(row.getPhysicalNumberOfCells()+"
			// "+row.getLastCellNum());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/***
	 * 针对选择的sheet页，读取某行某列的数据
	 * 
	 * @param i
	 *            ：行数
	 * @param j
	 *            ：列数
	 * @return 读到的字符串
	 * @throws Exception
	 */
	public static String getexcel(int i, int j, String sheetname)
			throws Exception {
		// String sheetname = ExcelOperate.getexcel_sheet0(5, 2);

		// List<String[]> result = new ArrayList<String[]>();
		int rowSize = 0;
		if (!pwd().endsWith(".xlsx")) {
			System.out.println("文件不是excel类型");
		}
		FileInputStream fis = null;
		Workbook wb = null;
		try {
			fis = new FileInputStream(pwd());
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			// 2007版本的excel，用.xlsx结尾
			wb = new XSSFWorkbook(fis);// 得到工作簿
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Cell cell = null;

		// HSSFSheet st = wb.getSheetAt(1);
		Sheet st = wb.getSheet(sheetname);

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

	public static String pwd_res(String filename) throws IOException {
		String filePath = "";
		File directory = new File(".");
		return filePath = directory.getCanonicalPath() + "/" + filename;
	}

	/**
	 * 读取项目目录下excel文件的某行某列的数据
	 * 
	 * @param i
	 *            行数
	 * @param j
	 *            列数
	 * @param sheetname
	 *            sheet页
	 * @param filename
	 *            文件名
	 * @return
	 * @throws Exception
	 */
	public static String getExcelXlsx(int i, int j, String sheetname,
			String filename) throws Exception {
		// String sheetname = ExcelOperate.getexcel_sheet0(5, 2);

		// List<String[]> result = new ArrayList<String[]>();
		int rowSize = 0;
		if (!filename.endsWith(".xlsx")) {
			System.out.println("文件不是excel类型");
		}
		FileInputStream fis = null;
		Workbook wb = null;
		try {
			fis = new FileInputStream(pwd_res(filename));
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			// 2007版本的excel，用.xlsx结尾
			wb = new XSSFWorkbook(fis);// 得到工作簿
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Cell cell = null;

		// HSSFSheet st = wb.getSheetAt(1);
		Sheet st = wb.getSheet(sheetname);

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
	 * 操作第一个sheet页，读取某行某列的数据
	 * 
	 * @param i
	 *            ：行数
	 * @param j
	 *            ：列数
	 * @return 读到的字符串
	 * @throws Exception
	 */

	public static String getexcel_sheet0(int i, int j) throws Exception {

		// List<String[]> result = new ArrayList<String[]>();
		int rowSize = 0;
		if (!pwd().endsWith(".xlsx")) {
			System.out.println("文件不是excel类型");
		}
		FileInputStream fis = null;
		Workbook wb = null;
		try {
			fis = new FileInputStream(pwd());
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			// 2007版本的excel，用.xlsx结尾
			wb = new XSSFWorkbook(fis);// 得到工作簿
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Cell cell = null;

		// HSSFSheet st = wb.getSheetAt(sheet_0);//获取第一个sheet页
		Sheet st = wb.getSheet(sheet_bg);// 获取第一个sheet页

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
	 * 获取sheetname，
	 * 
	 * @param i
	 *            ，
	 * @return
	 * @throws Exception
	 */
	public static String getsheetname(int i) throws Exception {

		if (!pwd().endsWith(".xlsx")) {
			System.out.println("文件不是excel类型");
		}
		FileInputStream fis = null;
		Workbook wb = null;
		try {
			fis = new FileInputStream(pwd());
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			// 2007版本的excel，用.xlsx结尾
			wb = new XSSFWorkbook(fis);// 得到工作簿
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String getsheetnme = wb.getSheetName(i);
		return getsheetnme;

	}

	/**
	 * 读取Excel的内容，第一维数组存储的是一行中格列的值，二维数组存储的是多少个行
	 * 
	 * @param file
	 *            读取数据的源Excel
	 * @param ignoreRows
	 *            读取数据忽略的行数，比喻行头不需要读入 忽略的行数为1
	 * @return 读出的Excel中数据的内容
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	@SuppressWarnings("deprecation")
	public static String[][] getData(File file, int ignoreRows)
			throws FileNotFoundException, IOException {
		List<String[]> result = new ArrayList<String[]>();
		int rowSize = 0;
		BufferedInputStream in = new BufferedInputStream(new FileInputStream(
				file));
		// 打开HSSFWorkbook
		POIFSFileSystem fs = new POIFSFileSystem(in);
		HSSFWorkbook wb = new HSSFWorkbook(fs);
		HSSFCell cell = null;

		HSSFSheet st = wb.getSheetAt(1);
		// 第一行为标题，不取
		for (int rowIndex = ignoreRows; rowIndex <= st.getLastRowNum(); rowIndex++) {
			HSSFRow row = st.getRow(rowIndex);
			if (row == null) {
				continue;
			}
			int tempRowSize = row.getLastCellNum() + 1;
			if (tempRowSize > rowSize) {
				rowSize = tempRowSize;
			}
			String[] values = new String[rowSize];
			Arrays.fill(values, "");
			boolean hasValue = false;
			for (short columnIndex = 0; columnIndex <= row.getLastCellNum(); columnIndex++) {
				String value = "";
				cell = row.getCell(columnIndex);
				if (cell != null) {
					// 注意：一定要设成这个，否则可能会出现乱码
					// cell.setEncoding(HSSFCell.ENCODING_UTF_16);

					switch (cell.getCellType()) {
					case Cell.CELL_TYPE_STRING:
						value = cell.getStringCellValue();
						break;
					case Cell.CELL_TYPE_NUMERIC:
						if (DateUtil.isCellDateFormatted(cell)) {
							Date date = cell.getDateCellValue();
							if (date != null) {
								value = new SimpleDateFormat("yyyy-MM-dd")
										.format(date);
							} else {
								value = "";
							}
						} else {
							value = new DecimalFormat("0").format(cell
									.getNumericCellValue());
						}
						break;
					case Cell.CELL_TYPE_FORMULA:
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
				if (columnIndex == 0 && value.trim().equals("")) {
					break;
				}
				values[columnIndex] = rightTrim(value);
				hasValue = true;
			}
			if (hasValue) {
				result.add(values);
			}
		}

		in.close();
		String[][] returnArray = new String[result.size()][rowSize];
		for (int i = 0; i < returnArray.length; i++) {
			returnArray[i] = result.get(i);
		}
		return returnArray;
	}

	/**
	 * 去掉字符串右边的空格
	 * 
	 * @param str
	 *            要处理的字符串
	 * @return 处理后的字符串
	 */
	public static String rightTrim(String str) {
		if (str == null) {
			return "";
		}
		int length = str.length();
		for (int i = length - 1; i >= 0; i--) {
			if (str.charAt(i) != 0x20) {
				break;
			}
			length--;
		}
		return str.substring(0, length);
	}

	/***
	 * 写入excel第一个sheet页的内容,自动生成代码报告
	 * 
	 * @throws Exception
	 */
	public static void cellexcel_autocode_report() throws Exception {
		try {

			String errorij = com.haoyun.automationtesting.framework.AutoCode.errij
					.toString();// 错误对象
			if (!pwd().endsWith(".xlsx")) {
				System.out.println("文件不是excel类型");
			}
			FileInputStream fis = null;
			Workbook wookbook = null;
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
			Sheet sheet = wookbook.getSheetAt(0);// 获取到工作表，因为一个excel可能有多个工作表

			FileOutputStream out = new FileOutputStream(pwd()); // 写数据
			// row=sheet.createRow((short)(sheet.getLastRowNum()+1));

			sheet.getRow(18)
					.createCell(1)
					.setCellValue(
							com.haoyun.automationtesting.framework.AutoCode.starttime);// 统计执行时间
			sheet.getRow(19)
					.createCell(1)
					.setCellValue(
							com.haoyun.automationtesting.framework.AutoCode.casecount); // 执行用例数
			sheet.getRow(20).createCell(1).setCellValue(errorij); // 错误对象

			out.flush();
			wookbook.write(out);
			out.close();
			// System.out.println(row.getPhysicalNumberOfCells()+"
			// "+row.getLastCellNum());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/***
	 * 写入excel第一个sheet页的内容，执行结果报告
	 * 
	 * @throws Exception
	 */
	public static void cellexcel_main() throws Exception {
		try {
			String llqi = ExcelOperate.getexcel_sheet0(4, 2);
			// mainStart mainStart=new mainStart();

			cellexcel_starttime();
			cellexcel_endtime();
			// String
			// starttime=com.nantian.automationtesting.test.aadomain.mainStart.starttime;
			// String
			// endtime=com.nantian.automationtesting.test.aadomain.mainStart.endtime;
			// int alltime=(Integer.parseInt(endtime)-
			// Integer.parseInt(starttime)) / 1000;
			cellexcel_alltime();
			cellexcel_caseall();
			cellexcel_caseerr();
			// cellexcel_casescale();

			if (!pwd().endsWith(".xlsx")) {
				System.out.println("文件不是excel类型");
			}
			FileInputStream fis = null;
			Workbook wb = null;
			try {
				fis = new FileInputStream(pwd());
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				// 2007版本的excel，用.xlsx结尾
				wb = new XSSFWorkbook(fis);// 得到工作簿
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			Sheet sheet = wb.getSheetAt(0); // 获取到工作表，因为一个excel可能有多个工作表
			// HSSFRow row=sheet.getRow(0);
			// //获取第一行（excel中的行默认从0开始，所以这就是为什么，一个excel必须有字段列头），即，字段列头，便于赋值
			// System.out.println(sheet.getLastRowNum()+"
			// "+row.getLastCellNum()); //分别得到最后一行的行号，和一条记录的最后一个单元格

			FileOutputStream out = new FileOutputStream(pwd()); // 写数据
			// row=sheet.createRow((short)(sheet.getLastRowNum()+1));

			sheet.getRow(9).createCell(1).setCellValue("本次执行使用的浏览器为：" + llqi);
			sheet.getRow(10).createCell(1).setCellValue(cellexcel_starttime); // 开始时间
			sheet.getRow(11).createCell(1).setCellValue(cellexcel_endtime); // 结束时间
			if (alltime == 0) {
				sheet.getRow(12).createCell(1)
						.setCellValue("系统没有正常登陆或退出，无法统计执行所用时间"); // // 时间相减
			} else {
				sheet.getRow(12).createCell(1)
						.setCellValue(alltime + "秒（" + alltime / 60 + "分钟）"); // //
																				// 时间相减
			}
			sheet.getRow(13).createCell(1)
					.setCellValue(index_sj + "/" + index_jh); // 执行用例数
			sheet.getRow(14).createCell(1).setCellValue(indexerr); // 执行错误用例数
			// sheet.getRow(14).createCell(2).setCellValue(errCaseIdName.toString());
			// // 执行错误用例类名和用例名称
			sheet.getRow(14).getCell(2).setCellValue(errCaseId.toString()); // 执行错误用例类名和用例名称
			sheet.getRow(15).createCell(1)
					.setCellValue(cellexcel_casescale() + "%"); // 执行用例数%

			out.flush();
			wb.write(out);
			out.close();
			// System.out.println(row.getPhysicalNumberOfCells()+"
			// "+row.getLastCellNum());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 开始时间
	public static String cellexcel_starttime() throws Exception {
		cellexcel_starttime = com.haoyun.automationtesting.test.aadomain.MainStart.starttime;
		return cellexcel_starttime;
	}

	// 结束时间
	public static String cellexcel_endtime() throws Exception {
		cellexcel_endtime = com.haoyun.automationtesting.test.aadomain.MainStart.endtime;
		return cellexcel_endtime;
	}

	// 时间相减
	public static String cellexcel_alltime() throws Exception {

		Date time1 = null;
		Date time2 = null;

		DateFormat df = new SimpleDateFormat("yyyyMMdd-HHmmss");
		if (cellexcel_endtime == "00000000-000000" || cellexcel_endtime == "") {
			alltime = -1;
			return cellexcel_zxtime = "脚本中断无法统计执行时间!";

		} else {
			time1 = df.parse(cellexcel_endtime);
		}

		if (cellexcel_starttime == "00000000-000000"
				|| cellexcel_starttime == "") {
			alltime = -1;
			return cellexcel_zxtime = "没有登陆成功所以无法统计执行时间!";

		} else {
			time2 = df.parse(cellexcel_starttime);

		}

		System.out.println(time1);
		System.out.println(time2);
		long time = time1.getTime() - time2.getTime();
		System.out.println(time);
		alltime = time / 1000;// 时间单位：秒，再除以60后是分钟
		if (alltime < 0) {
			alltime = 0;
			cellexcel_zxtime = "脚本中断无法统计执行时间!";
		} else {

			cellexcel_zxtime = String.valueOf(alltime);
		}
		// System.out.println(time);
		// System.out.println(time1.getTime());
		// System.out.println(time2.getTime());
		// System.out.println(alltime);

		return cellexcel_zxtime;
	}

	static int index_sj = 0;// 统计实际执行用例数
	static int index_sj1 = 0;// 统计实际执行用例数
	static int index_sj2 = 0;// 统计实际执行用例数
	static int index_jh = 0;// 统计计划执行用例数

	// 统计执行用例数

	public static String cellexcel_caseall() throws IOException {
		try {
			// String sheetname = ExcelOperate.getexcel_sheet0(5, 2);
			if (!pwd().endsWith(".xlsx")) {
				System.out.println("文件不是excel类型");
			}
			FileInputStream fis = null;
			Workbook wb = null;
			try {
				fis = new FileInputStream(pwd());
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				// 2007版本的excel，用.xlsx结尾
				wb = new XSSFWorkbook(fis);// 得到工作簿
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			for (int sheetindex = 1; sheetindex < wb.getNumberOfSheets(); sheetindex++) {// 获取sheet页个数
				String sheetname = wb.getSheetName(sheetindex);
				if (!sheetname.trim().isEmpty()) {

					Sheet st = wb.getSheet(sheetname);
					int row = st.getLastRowNum();
					index_sj = 0;

					for (int i = 0; i < row + 1; i++) {

						String caseindex1 = getexcel(i + 1, 4, sheetname);
						String caseindex2 = getexcel(i + 1, 5, sheetname);
						// if (!caseindex.equals("未执行")) {
						if (("通过").equals(caseindex1) && "1".equals(caseindex2)) {
							index_sj1 = index_sj1 + 1;
						}
					}
					for (int i = 0; i < row + 1; i++) {

						String caseindex3 = getexcel(i + 1, 4, sheetname);
						String caseindex4 = getexcel(i + 1, 5, sheetname);
						// if (!caseindex.equals("未执行")) {
						if (("未通过").equals(caseindex3)
								&& "1".equals(caseindex4)) {
							index_sj2 = index_sj2 + 1;
						}
					}

					index_sj = index_sj1 + index_sj2;

					for (int i = 0; i < row + 1; i++) {

						String caseindex5 = getexcel(i + 1, 5, sheetname);
						// if (!caseindex.equals("未执行")) {
						if ("1".equals(caseindex5)) {
							index_jh = index_jh + 1;
						}
					}
				}
			}

		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}

		return index_sj + "/" + index_jh;

	}

	static int indexerr = 0;// 统计执行错误用例
	static StringBuffer errCaseId = new StringBuffer("");// 统计执行错误的类名

	// 统计失败用例数

	public static int cellexcel_caseerr() throws IOException {
		try {
			// String sheetname = ExcelOperate.getexcel_sheet0(5, 2);
			if (!pwd().endsWith(".xlsx")) {
				System.out.println("文件不是excel类型");
			}
			FileInputStream fis = null;
			Workbook wb = null;
			try {
				fis = new FileInputStream(pwd());
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				// 2007版本的excel，用.xlsx结尾
				wb = new XSSFWorkbook(fis);// 得到工作簿
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			indexerr = 0;
			for (int sheetindex = 1; sheetindex < wb.getNumberOfSheets(); sheetindex++) {// 获取sheet页个数
				String sheetname = wb.getSheetName(sheetindex);
				if (!sheetname.trim().isEmpty()) {
					Sheet st = wb.getSheet(sheetname);
					int row = st.getLastRowNum();

					for (int i = 0; i < row + 1; i++) {

						String caseindex = getexcel(i + 1, 4, sheetname);
						String caseindex2 = getexcel(i + 1, 5, sheetname);
						// System.out.println(caseindex);
						if (("未通过").equals(caseindex) && "1".equals(caseindex2)) {
							indexerr = indexerr + 1;
							errCaseId.append(getexcel(i + 1, 1, sheetname))
									.append("\r\n");
							// new StringBuffer(getexcel(i + 1, 1,
							// sheetname)).append("用例名称：").append(getexcel(i +
							// 1, 2, sheetname)).append("\r\n").toString();
							// 写入执行未通过用例的id和用例名称
						}
					}
				}
			}

		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}

		return indexerr;

	}

	static float casescale = 0;// 统计执行用例百分比

	public static String cellexcel_casescale() {
		casescale = 0;
		String str = "";
		// log.logInfo("index_jh"+index_jh);
		// log.logInfo("index_sj"+index_sj);
		try {
			if ("1".equals(String.valueOf(index_jh))) {

			} else {
				if (String.valueOf(index_jh).equals(String.valueOf(index_sj))) {
					casescale = ((((float) index_jh - (float) indexerr) / index_jh)) * 100;
				} else {

					str = "计划的用例没有执行完，无法统计成功率";

				}
			}
			// System.out.println(casescale);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		// System.out.println(String.valueOf(casescale));
		return String.valueOf(casescale) + str;

	}

	/***
	 * 根据类名获取用例所在行数
	 * 
	 * @param classname
	 *            类名也就是用例名
	 * @return
	 * @throws Exception
	 */
	public static int getclassname_rows(String classname, String sheetname)
			throws Exception {
		// String sheetname = ExcelOperate.getexcel_sheet0(5, 2);

		int rows = 0;
		if (!pwd().endsWith(".xlsx")) {
			System.out.println("文件不是excel类型");
		}
		FileInputStream fis = null;
		Workbook wb = null;
		try {
			fis = new FileInputStream(pwd());
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			// 2007版本的excel，用.xlsx结尾
			wb = new XSSFWorkbook(fis);// 得到工作簿
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Sheet st = wb.getSheet(sheetname);
		int row = st.getLastRowNum();

		for (int i = 2; i <= row + 1; i++) {
			if (ExcelOperate.getexcel(i, 1, sheetname).equals(classname)) {
				rows = i;
				return rows;
			}
		}

		if (rows == 0) {
			log.logWarn("没有找到测试用例编号：" + classname);
		}
		return rows;

	}

	/**
	 * 删除sheet页,从第3个sheet开始
	 * 
	 * @return
	 * @throws IOException
	 */
	public static void delete_sheel1toN() throws IOException {
		try {

			if (!pwd().endsWith(".xlsx")) {
				System.out.println("文件不是excel类型");
			}
			FileInputStream fis = null;
			Workbook wookbook = null;
			Sheet sheet = null;
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

			FileOutputStream out = new FileOutputStream(pwd()); // 写数据
			int dd = wookbook.getNumberOfSheets();
			// log.logInfo("共多少sheet页:"+dd);

			for (int sheetindex = 0; sheetindex < dd - 1; sheetindex++) {// 获取sheet页个数
				//
				// String name=wookbook.getSheetName(sheetindex);
				// log.logInfo("name:"+name);

				wookbook.removeSheetAt(1);

			}

			out.flush();
			wookbook.write(out);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// /////////以下为写入报告//////////////
	public static StringBuffer RoleName = new StringBuffer("");
	public static ReadWriteLock lock = new ReentrantReadWriteLock();// 创建读写锁的实例

	/**
	 * 写入结果到每一个测试用例行
	 * 
	 * @throws IOException
	 */
	public static void writeExcelReprot() throws IOException {
		// log.logInfo("RoleName:" + RoleName.toString());
		if (!RoleName.toString().isEmpty() && RoleName.toString().endsWith(";")) {

			String SJ[] = RoleName.toString().split(";");
			int length = SJ.length;
			// log.logInfo("length:" + length);
			for (int i = 0; i < length; i++) {
				String bg[] = SJ[i].split(",");
				log.logInfo(bg[1] + ":" + bg[2] + ":" + bg[0]);
				try {
					cellexcel_SRS(bg[1], Integer.valueOf(bg[2]), bg[0],bg[3]);// 写入excel
				} catch (NumberFormatException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				} catch (Exception e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
			}
			// log.logInfo("写完数据！");
		} else {
			log.logWarn("没有执行到用例或者未加assertReport报告！");
		}

	}

	public static String writecache(String okerr, String sheetname, int index) {

		try {

			lock.writeLock().lock();
			try {
				// 可能已经由其他线程写入数据
				RoleName.append(okerr + ",");
				RoleName.append(sheetname + ",");
				RoleName.append(index + ",");
				RoleName.append(new SimpleDateFormat("HHmmss").format(new Date()) + ";");
			} finally {
				// Downgrade by acquiring read lock before releasing write
				// lock
				lock.readLock().lock();
				// Unlock write, still hold read
				lock.writeLock().unlock();
			}

		} finally {
			lock.readLock().unlock();// 最后一定不要忘记释放锁
		}
		// System.out.println("get data key=" + key + ">val=" + val);
		return RoleName.toString();
	}

}