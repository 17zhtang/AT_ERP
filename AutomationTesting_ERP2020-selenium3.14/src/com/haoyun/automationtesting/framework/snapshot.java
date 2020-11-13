package com.haoyun.automationtesting.framework;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.google.common.io.Files;



public class snapshot extends action {

	// public void log(String logText) {
	// System.out.println("[" + new SimpleDateFormat("yyyy-MM-dd
	// HH:mm:ss").format(new Date(System.currentTimeMillis())) + "] " +
	// logText);
	// }

	public snapshot() {
		super();
		// TODO 自动生成的构造函数存根
	}

	public snapshot(WebDriver driver) {
		super(driver);
		// TODO 自动生成的构造函数存根
	}

	/***
	 * 截图
	 * 
	 * @throws Exception
	 * @throws InterruptedException
	 */
	public static void screenShot() throws Exception, InterruptedException {
		try {
			String dir = "snapshot";
			String time = new SimpleDateFormat("yyyyMMdd-HHmmss")
					.format(new Date());

			String screenShotPath = dir + File.separator + time + ".png";
			log.logInfo("截图路径是："+screenShotPath);
			

				// System.out.println("driver::::"+ driver);

				// System.out.println(screenShotPath);

				// File screenShotFile = ((TakesScreenshot)
				// driver).getScreenshotAs(OutputType.FILE);
				// File screenShotFile = ((RemoteWebDriver)
				// driver).getScreenshotAs(OutputType.FILE);
				File screenShotFile = ((RemoteWebDriver) driver)
						.getScreenshotAs(OutputType.FILE);
//				org.apache.commons.io.FileUtils.copyFile(screenShotFile,
//						new File(screenShotPath));
				Files.copy(screenShotFile,
						new File(screenShotPath));
				
			
		}catch(Exception e){
			 e.printStackTrace();
			log.logWarn("浏览器关闭或无响应或焦点不在driver对象！截图未成功");
			try{
				action.alert_accept();//点击弹出框确定按钮
				action.alert_dismiss();//点击弹出框取消按钮
				
			}catch(Exception el){
				
			}

//			mainStart mainStart =new mainStart();
//			mainStart.after();
//			System.exit(0);//这里中断执行会出现问题，当driver对象还在的时候，只是焦点定位在非driver对象的窗口上就会导致中断。
			 
			
		}
	}

}