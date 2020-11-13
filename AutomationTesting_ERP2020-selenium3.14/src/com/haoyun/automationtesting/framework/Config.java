package com.haoyun.automationtesting.framework;

/***
 * 定义全局变量
 * 
 * @author lisheng
 *
 */

public class Config {
	/***
	 * 启动路径、全局思考时间、数据库连接配置
	 */

	public static ParseXml parseXml;
	/** 全局等待时间 **/
	public static int appiumconfig_waitTime;
	/** 接入服务ip **/
	public static String IP;
	/** 优柏端口 **/
	public static int YBPort;
	/** 曼顿端口 **/
	public static int MDPort;
	/** 曼顿超时时间 **/
	public static int MD_TIME_OUT;

	public static String autocode_1;// 写入代码生成器。首行用例
	public static String autocode_2;// 写入代码生成器。import
	public static String AutoUIrecorderToCode_pwd;
	public static String AutoSeleniumIDEToCode_pwd;
	public static String mysqlurl;
	public static String mysqluser;
	public static String mysqlpassword;

	static {

		parseXml = new ParseXml("config/config.xml");
		appiumconfig_waitTime = Integer.valueOf(parseXml
				.getElementText("/config/waitTime"));
		IP = parseXml.getElementText("/config/JRServer/YBIP");
		YBPort = Integer.valueOf(parseXml.getElementText("/config/JRServer/YBPort"));
		MDPort = Integer.valueOf(parseXml.getElementText("/config/JRServer/MDPort"));
		MD_TIME_OUT=Integer.valueOf(parseXml.getElementText("/config/JRServer/MD_TIME_OUT"));
		

		autocode_1 = parseXml.getElementText("/config/autocode_1");
		autocode_2 = parseXml.getElementText("/config/autocode_2");
		AutoUIrecorderToCode_pwd = parseXml
				.getElementText("/config/AutoUIrecorderToCode_pwd");
		AutoSeleniumIDEToCode_pwd = parseXml
				.getElementText("/config/AutoSeleniumIDEToCode_pwd");

		mysqlurl = parseXml.getElementText("/config/mysql/mysqlurl");
		mysqluser = parseXml.getElementText("/config/mysql/mysqluser");
		mysqlpassword = parseXml.getElementText("/config/mysql/mysqlpassword");

	}

	public static String getParam(String param) {
		String value = parseXml.getElementText(param);
		return value;
	}

	public static void main(String[] args) {

		// System.out.println(appiumconfig_waitTime);

	}

}
