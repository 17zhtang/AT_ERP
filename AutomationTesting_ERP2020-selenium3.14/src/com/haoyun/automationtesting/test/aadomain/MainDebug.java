package com.haoyun.automationtesting.test.aadomain;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.haoyun.automationtesting.framework.log;

public class MainDebug {

	// /**
	// * DLL动态库调用方法
	// */
	// public interface CLibrary extends Library {
	// //
	// DLL文件默认路径为项目根目录，若DLL文件存放在项目外，请使用绝对路径。（此处：(Platform.isWindows()?"msvcrt":"c")指本地动态库msvcrt.dll）
	// CLibrary INSTANCE = (CLibrary) Native.loadLibrary("CRC32",
	// CLibrary.class);
	//
	// // 声明将要调用的DLL中的方法,可以是多个方法(此处示例调用本地动态库msvcrt.dll中的printf()方法)
	//
	//
	// public long CalculateCRC32(byte[] data, int len);
	//
	// }
	//
	// public static void main(String[] args) {
	// ByteBuffer buffer = ByteBuffer.allocate(48);
	// //header
	// buffer.put((byte)0xf1);//
	// buffer.put((byte)0xa4);//消息类型
	// buffer.put((byte)0xe1);//E1--实时事件
	// buffer.put((byte)0x01);//事件数量
	// CLibrary.INSTANCE.CalculateCRC32(buffer.array(), 4);
	//
	//
	// //log.logInfo("jieguo::"+CRC32.CalculateCRC32(buffer.array(), 4));;
	//
	//
	// }
	//

	// /**
	// * DLL动态库调用方法2
	// *
	// * @Description: 读取调用Decl方式导出的DLL动态库方法
	// */
	// public interface CLibrary extends Library {
	// // DLL文件默认路径为项目根目录，若DLL文件存放在项目外，请使用绝对路径
	// CLibrary INSTANCE = (CLibrary) Native.loadLibrary("CRC32",
	// CLibrary.class);
	//
	// // 声明将要调用的DLL中的方法（可以是多个方法）
	// public long CalculateCRC32(byte[] data, int len);
	// //public long CalculateCRC32(unsigned char* data, int len);
	// }
	//
	// public static void main(String[] args) {
	// //CRC32.INSTANCE.printf("Hello, World!\n");
	// ByteBuffer buffer = ByteBuffer.allocate(48);
	// //header
	// buffer.put((byte)0xf1);//
	// buffer.put((byte)0xa4);//消息类型
	// buffer.put((byte)0xe1);//E1--实时事件
	// buffer.put((byte)0x01);//事件数量
	// CLibrary.INSTANCE.CalculateCRC32(buffer.array(), 4);
	// // for (byte c : CLibrary.INSTANCE.CalculateCRC32(buffer.array(), 4))
	// {//TIME 时间戳
	// // System.out.format("CRC为：%X", c);
	// // buffer.put(c);
	// // }
	// log.logInfo(""+CLibrary.INSTANCE.CalculateCRC32(buffer.array(), 4));
	// }

	// public static AndroidDriver<AndroidElement> driver;
	public static WebDriver driver;
	public static String errCaseIdName;// 统计执行错误的类名及用例名称

	@Test
	// public static void main(String[] args) throws InterruptedException,
	// Exception {
	public static void zhu() throws Exception {

		try {
			SimpleDateFormat sdfFormat = new SimpleDateFormat("yyyyMMddHHmmss");
			DateFormat dateStart;
			Date dateEnd;
			
			dateStart = DateFormat.getDateInstance();
			
			Calendar cal = Calendar.getInstance();
		    
		    cal.add(Calendar.HOUR, +8);
		    
		    
		    
		    

		  
		   System.out.println(sdfFormat.format(dateStart));
		   

		} catch (Exception e) {
			e.printStackTrace();
			log.logError("错误信息：" + e);
		}
		
		
		

	}

}
