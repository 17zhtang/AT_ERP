package com.haoyun.automationtesting.framework;

import java.sql.Connection;
//import com.mysql.jdbc.Connection;
//import com.mysql.jdbc.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@SuppressWarnings({ "unused" })
public class mysql {

	/** 设置参数 **/
	private static Connection conn = null;
	private static Statement stmt = null;
	private static ResultSet rs = null;
	private static java.sql.ResultSetMetaData rsmd;

	
	public static String url;
	public static String user;
	public static String pwd;
    public static void getuup() throws Exception {
    	
    	url = ExcelOperate.getexcel_sheet0(7, 2);// url为连接字符串
    	user =ExcelOperate.getexcel_sheet0(4, 4);// 数据库用户名
    	pwd = ExcelOperate.getexcel_sheet0(5, 4);// 数据库密码
    	
//    	public static String url = Config.mysqlurl;// url为连接字符串
//    	public static String user = Config.mysqluser;// 数据库用户名
//    	public static String pwd = Config.mysqlpassword;// 数据库密码
    }


	/***
	 * sql的删除语句
	 * @param sql
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static void Delete(String sql) throws SQLException,
			ClassNotFoundException {
		try {

			Connection conn11 = null;
			Statement stmt11 = null;
			ResultSet rs11 = null;
			java.sql.ResultSetMetaData rsmd11;

			Class.forName("com.mysql.jdbc.Driver");// 加载mysql驱动程序类

			getuup();//赋值url, user, pwd
			conn11 = DriverManager.getConnection(url, user, pwd);
			// String sql="select * from smsusr.mt_sys";
			stmt11 = conn11.createStatement();
			stmt11.executeUpdate(sql);// 执行select语句用executeQuery()方法，执行insert、update、delete语句用executeUpdate()方法。
            log.logInfo("删除成功，sql："+sql);
			// rs11.close();//后定义，先关闭
			stmt11.close();
			conn11.close();// 先定义，后关闭
		} catch (Exception e) {
			log.logWarn(e.getMessage());
			e.printStackTrace();
		}

	}

	/***
	 * sql的查询语句
	 * 
	 * @param sql
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static String select(String sql) throws SQLException,
			ClassNotFoundException {

		Connection conn11 = null;
		Statement stmt11 = null;
		ResultSet rs11 = null;
		java.sql.ResultSetMetaData rsmd11;
		String ba = "";
		try {
			//log.logInfo("正在连接数据库..........");
			Class.forName("com.mysql.jdbc.Driver");// 加载mysql驱动程序类
			getuup();//赋值url, user, pwd
			conn11 = DriverManager.getConnection(url, user, pwd);
			//log.logInfo("数据库连接成功！！！");
		} catch (Exception e) {
			log.logWarn(e.getMessage());
			log.logWarn("数据库连接失败!");

		}
		// String sql="select * from smsusr.mt_sys";
		// String sql="select * from smsusr.mt_sys";
		stmt11 = conn11.createStatement();
		try {
			rs11 = stmt11.executeQuery(sql);//

			rsmd11 = rs11.getMetaData();

			rs11.next();
			if (!rs11.isFirst()) {
				ba = "";
			} else {
				ba = rs11.getString(1);
			}

			rs11.close();// 后定义，先关闭
		} catch (Exception e) {
			ba = "没有查询到数据或者SQL语法错误!";
			log.logWarn(e.getMessage());
		}

		stmt11.close();
		conn11.close();// 先定义，后关闭
		return ba;

	}
	
	/***
	 * sql的查询语句,powersystem2020库
	 * 
	 * @param sql
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static String select_powersystem2020(String sql) throws SQLException,
			ClassNotFoundException {

		Connection conn11 = null;
		Statement stmt11 = null;
		ResultSet rs11 = null;
		java.sql.ResultSetMetaData rsmd11;
		String ba = "";
		try {
			
			Class.forName("com.mysql.jdbc.Driver");// 加载mysql驱动程序类
			getuup();//赋值url, user, pwd
			//log.logInfo(url.substring(0, 31)+"powersystem2020");
			conn11 = DriverManager.getConnection(url.substring(0, 31)+"powersystem2020", user, pwd);//jdbc:mysql://192.168.3.37:3506/powersystem2020
			//log.logInfo("数据库连接成功！！！");
		} catch (Exception e) {
			log.logWarn(e.getMessage());
			log.logWarn("数据库连接失败!");

		}
		// String sql="select * from smsusr.mt_sys";
		// String sql="select * from smsusr.mt_sys";
		stmt11 = conn11.createStatement();
		try {
			rs11 = stmt11.executeQuery(sql);//

			rsmd11 = rs11.getMetaData();

			rs11.next();
			if (!rs11.isFirst()) {
				ba = "";
			} else {
				ba = rs11.getString(1);
			}

			rs11.close();// 后定义，先关闭
		} catch (Exception e) {
			ba = "没有查询到数据或者SQL语法错误!";
			log.logWarn(e.getMessage());
		}

		stmt11.close();
		conn11.close();// 先定义，后关闭
		return ba;

	}
	
	/***
	 * sql的删除语句 powersystem2020库
	 * @param sql
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static void Delete_powersystem2020(String sql) throws SQLException,
			ClassNotFoundException {
		try {

			Connection conn11 = null;
			Statement stmt11 = null;
			ResultSet rs11 = null;
			java.sql.ResultSetMetaData rsmd11;

			Class.forName("com.mysql.jdbc.Driver");// 加载mysql驱动程序类

			getuup();//赋值url, user, pwd
			conn11 = DriverManager.getConnection(url.substring(0, 31)+"powersystem2020", user, pwd);
			// String sql="select * from smsusr.mt_sys";
			stmt11 = conn11.createStatement();
			stmt11.executeUpdate(sql);// 执行select语句用executeQuery()方法，执行insert、update、delete语句用executeUpdate()方法。
            log.logInfo("删除成功，sql："+sql);
			// rs11.close();//后定义，先关闭
			stmt11.close();
			conn11.close();// 先定义，后关闭
		} catch (Exception e) {
			log.logWarn(e.getMessage());
			e.printStackTrace();
		}

	}

}
