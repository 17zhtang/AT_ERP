package com.haoyun.automationtesting.framework;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.net.util.Base64;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

//import sun.misc.BASE64Encoder;
import freemarker.template.Configuration;
import freemarker.template.Template;

public class CreateWordTest {
	public static String report_time;

	//@Test
	public static void ReportWord() {
		// public static void main(String args[]){
		CreateWordTest t = new CreateWordTest();
		t.write();
	}

	public void write() {
		report_time = new SimpleDateFormat("yyyyMMdd-HHmmss")
				.format(new Date());
		try {
			// 创建配置实例
			Configuration configuration = new Configuration();
			// 设置编码
			configuration.setDefaultEncoding("UTF-8");
			// ftl模板文件统一放至/包下面
			// configuration.setClassForTemplateLoading(CreateWordTest.class,"/");
			configuration.setDirectoryForTemplateLoading(new File(
					"src/com/haoyun/automationtesting/framework"));
			// 获取模板
			Template template = configuration.getTemplate("docreport.ftl",
					"UTF-8");
			String path0 = "resource";
			File f = new File(path0);
			// 创建文件夹
			if (!f.exists()) {
				f.mkdirs();
			}
			// 输出文件
			File outFile = new File("resource/自动化测试报告" + report_time + ".doc");
			// 将模板和数据模型合并生成文件
			Writer out = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(outFile), "UTF-8"));
			template.process(getRootWord("1"), out);
			out.flush();
			System.out.println("写入成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取数据
	 * 
	 * @param flag
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> getRootWord(String flag) throws Exception {

		String est1 = ExcelOperate.getexcel_sheet0(10, 2);// 执行结果统计
		String est2 = ExcelOperate.getexcel_sheet0(11, 2);// 开始时间：
		String est3 = ExcelOperate.getexcel_sheet0(12, 2);// 结束时间：
		String est4 = ExcelOperate.getexcel_sheet0(13, 2);//
		String est5 = ExcelOperate.getexcel_sheet0(14, 2);//
		String est6 = ExcelOperate.getexcel_sheet0(14, 2);//
		String est7 = ExcelOperate.getexcel_sheet0(15, 2);// 失败用例数：
		String est8 = ExcelOperate.getexcel_sheet0(24, 2);//
		String est9 = ExcelOperate.getexcel_sheet0(25, 2);//
		String est10 = ExcelOperate.getexcel_sheet0(26, 2);//
		String est11 = ExcelOperate.getexcel_sheet0(27, 2);// 测试负责人
		String est12 = ExcelOperate.getexcel_sheet0(11, 2);// 测试日期
		String est13 = ExcelOperate.getexcel_sheet0(28, 2);// 测试人员
		String est14 = ExcelOperate.getexcel_sheet0(16, 2);// 成功率：
		String est20 = ExcelOperate.getexcel_sheet0(5, 2);// 浏览器地址：
		String est21 = ExcelOperate.getexcel_sheet0(6, 2);// 应用系统url
		String est22 = ExcelOperate.getexcel_sheet0(7, 2);// 数据库
		String est23 = ExcelOperate.getexcel_sheet0(8, 2);// 自动化框架版本：
		String esterrcase = ExcelOperate.getexcel_sheet0(15, 3);// 错误的用例类名
		// String est15 = ExcelOperate.getexcel_sheet0(4, 2);

		Map<String, Object> root = new HashMap<String, Object>();
		List<Map<String, Object>> proList = getProList();
		List<Map<String, Object>> proList_excel = getProList_excel();
		root.clear();
		root.put("esterrcase", esterrcase.replaceAll("\\s+", "、"));//split("\\s+")
		root.put("est1", est1);
		root.put("est2", est2);
		root.put("est3", est3);
		root.put("est4", est4);
		root.put("est5", est5.split("/")[1]);
		root.put("est6", est6.split("/")[0]);
		root.put("est7", est7);
		root.put("est8", est8);
		root.put("est9", est9);
		root.put("est10", est10);
		root.put("est11", est11);
		root.put("est12", est12.split("-")[0]);
		root.put("est13", est13);
		root.put("est14", est14);
		root.put("date1", est12.substring(0, 6));
		root.put("est20", est20);
		root.put("est21", est21);
		root.put("est22", est22);
		root.put("est23", est23);
		root.put("est24", "本机ip为："+getIp());
		root.put("est25", fileRead());//获取log文件夹下sit。log文件中的内容并写入到word报告中。

		root.put("proList", proList);//获取截图中的图片导入到word报告中。 与模板${proList}对应，由于是列表，模板需要循环生成行，故用标签<#list
		
		root.put("proList_excel", proList_excel);// 与模板${proList_excel}对应，由于是列表，模板需要循环生成行，故用标签<#list
		// proList as
		// infolist>，在用infolist.各自的表头，如：${infolist.fund_num}。

		// String sumShizhi = sumShizhi(proList);
		// root.put("sum",sumShizhi);
		// 防止图片出现小红叉（生成时为空，复核加盖印章）
		// if("0".equals(flag)){
		// root.put("aa",0);
		// root.put("image","");
		// }
		// //dataMap.put("image","");
		// if ("1".equals(flag)) {
		// root.put("aa",1);
		// root.put("image",
		// intelligenceWord("src/com/haoyun/automationtesting/framework/imageone.jpg"));//插入图片
		// }
		return root;
	}

	public static String pwd() throws IOException{
		String filePath="";
		File directory = new File(".");
		return filePath = directory.getCanonicalPath() + "/resource/case.xlsx";
	}
	
	/**
	 * 插入excel中所有用例
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> getProList_excel() throws Exception {
		List<Map<String, Object>> proList_excel = new ArrayList<Map<String, Object>>();
		List<String> est19 = new ArrayList<String>();
		List<String> est15 = new ArrayList<String>();
		List<String> est16 = new ArrayList<String>();
		List<String> est17 = new ArrayList<String>();
		List<String> est18 = new ArrayList<String>();

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

				// List<String> est19 = new ArrayList<String>();
				for (int i = 5; i <= row + 1; i++)  {// 获取excel中第一列 类名
					//System.out.println(ExcelOperate.getexcel(i, 1, sheetname));
					if(!ExcelOperate.getexcel(i, 1, sheetname).isEmpty()){
						est19.add( ExcelOperate.getexcel(i, 1, sheetname));
						est15.add( ExcelOperate.getexcel(i, 2, sheetname));
						est16.add( ExcelOperate.getexcel(i, 3, sheetname));
						est17.add( ExcelOperate.getexcel(i, 4, sheetname));
						est18.add( ExcelOperate.getexcel(i, 5, sheetname));

					}
					

				}
				//System.out.println("199999:::::"+est19);
				//System.out.println("15555::::"+est15);


			
			}

		}
		for (int i = 0; i < est19.size(); i++) {
			Map<String, Object> map = new HashMap<String, Object>();
		
				map.put("est19", est19.get(i) == null ? "" : est19.get(i));
				map.put("est15", est15.get(i) == null ? "" : est15.get(i));
				map.put("est16", est15.get(i) == null ? "" : est16.get(i));
				map.put("est17", est15.get(i) == null ? "" : est17.get(i));
				map.put("est18", est15.get(i) == null ? "" : est18.get(i));
			
			

			proList_excel.add(map);
		}
		return proList_excel;
	}

	/**
	 * 求和
	 * 
	 * @param proList
	 * @return
	 */
	public String sumShizhi(List<Map<String, Object>> proList) {
		double sumShizhi = 0;
		for (int i = 0; i < proList.size(); i++) {
			double shizhi_df = Double
					.parseDouble(isNotNullString((String) proList.get(i).get(
							"shizhi")));
			sumShizhi += shizhi_df;
		}
		return String.valueOf(sumShizhi);
	}

	/**
	 * 插入“snapshot”路径下所有图片
	 * 
	 * @return
	 * @throws Exception
	 */
	private List<Map<String, Object>> getProList() throws Exception {

		List<Map<String, Object>> proList = new ArrayList<Map<String, Object>>();
		if (true) {
			List<String> fund_num = new ArrayList<String>();
			List<String> errtu = new ArrayList<String>();
			List<String> pro_code = new ArrayList<String>();
           int fnamelength=getFileNamelength();
			for (int i = 0; i < fnamelength; i++) {// 获取“snapshot”路径下所有图片的名称
				String fname=getFileName(i);
				//System.out.println("fname:::::"+fname);
				if(fname.endsWith("png")){
					fund_num.add(intelligenceWord(fname));
					
					errtu.add(fname);
					pro_code.add(String.valueOf(i+3));// 获取“snapshot”路径下图片的个数,word中已经有了两张图片，所以要从3开始。
				}


			}

			
//			for (int i = 3; i < fnamelength+3; i++) {// 获取“snapshot”路径下图片的个数,word中已经有了两张图片，所以要从3开始。
//				pro_code.add(String.valueOf(i));
//
//			}
			
                
			int fundnumsile=fund_num.size();
				for (int i = 0; i < fundnumsile; i++) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("fund_num",
							fund_num.get(i) == null ? "" : fund_num.get(i));
					map.put("pro_code",
							pro_code.get(i) == null ? "" : pro_code.get(i));
					map.put("errtu",
							errtu.get(i) == null ? "" : errtu.get(i));


					proList.add(map);
				}
				
			

		}
		return proList;
	}

	/**
	 * 获取印章并解析word可以识别的编码
	 * 
	 * @return
	 * @throws FileNotFoundException
	 * @throws UnsupportedEncodingException 
	 */
	public String intelligenceWord(String image) throws FileNotFoundException, UnsupportedEncodingException {
		//BASE64Encoder encoder = new BASE64Encoder();
		
		//byte[] encodeBase64 = Base64.encodeBase64(message.getBytes("UTF-8"));
		// String path = this.getClass().getResource("/").getPath();
		// InputStream input =
		// this.getClass().getResourceAsStream("/image.jpg");
		// System.out.println(path);
		File file = new File("snapshot/" + image);
		InputStream input = new FileInputStream(file);
		byte[] fileBytes = new byte[(int) file.length()];

		try {
			input.read(fileBytes);// 读进fileBytes数组里面
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (input != null)
					input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		//return encoder.encodeBuffer(fileBytes).trim();
		
		return new String(Base64.encodeBase64(fileBytes)).trim();
	}
		

	private String isNotNullString(String agus) {
		String result = "0";
		if (null != agus && !"".equals(agus)) {
			result = agus;
		}
		return result;

	}

	public static void main(String[] args) {
		System.out.println(getFileName(4));
		System.out.println(getFileNamelength());

	}

	/****
	 * 获取文件夹下文件名
	 */
	public static String getFileName(int n) {
		String path = "snapshot"; // 路径
		File f = new File(path);
		if (!f.exists()) {
			System.out.println(path + " not exists");
			return null;
		}
		File fa[] = f.listFiles();
		// for (int i = 0; i < fa.length; i++) {
		// File fs = fa[i];
		// if (fs.isDirectory()) {
		// System.out.println(fs.getName() + " [目录]");
		// } else {
		// System.out.println(fs.getName());
		//
		// }
		return fa[n].getName();
	}

	public static int getFileNamelength() {
		String path = "snapshot"; // 路径
		File f = new File(path);
		if (!f.exists()) {
			System.out.println(path + " not exists");
			return 0;
		}
		File fa[] = f.listFiles();

		return fa.length;

	}
	
	/***
	 * 本机ip
	 * @return
	 * @throws Exception
	 */
	public static String getIp() throws Exception {
        InetAddress addr = InetAddress.getLocalHost();  
        String ip=addr.getHostAddress().toString(); //获取本机ip  
		return ip;
   }
	/***
	 * 本机机器名
	 * @return
	 * @throws Exception
	 */
	public static String gethostname() throws Exception {
        InetAddress addr = InetAddress.getLocalHost();  
        String hostName=addr.getHostName().toString(); //获取本机计算机名称        
		return hostName;
   }
	
	/***
	 * 读取log文件内容
	 * @return
	 * @throws Exception
	 */
	public static String fileRead() throws Exception {
        File file = new File("log\\Sit.log");//定义一个file对象，用来初始化FileReader
        FileReader reader = new FileReader(file);//定义一个fileReader对象，用来初始化BufferedReader
        BufferedReader bReader = new BufferedReader(reader);//new一个BufferedReader对象，将文件内容读取到缓存
        StringBuilder sb = new StringBuilder();//定义一个字符串缓存，将字符串存放缓存中
        String s = "";
        while ((s =bReader.readLine()) != null) {//逐行读取文件内容，不读取换行符和末尾的空格
            sb.append(s + "\n");//将读取的字符串添加换行符后累加存放在缓存中
           // System.out.println(s);
        }
        bReader.close();
        String str = sb.toString();
        return str;
    }//<br>遇到问题：<br>while循环中，直接使用（bReader.readLine()!=null)，循环体内用s=bReader.readLine()赋值，<br>输出发现跳行获取的，每调用一次bReader.readLine()就会读取一行，所以造成跳行显示。

	
}