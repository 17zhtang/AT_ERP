package com.haoyun.automationtesting.framework;

import java.io.File;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class log {

	/**
	 * @param args
	 */
	private static Logger logger;

	private static String filePath = System.getProperty("user.dir")
			+ "/src/log4j.properties";
	// private static String
	// filePath="C:/Users/Administrator/workspace/AutomationCCBFrameWork_Sit/build/src/log4j.properties";
	private static boolean flag = false;

	// @SuppressWarnings("static-access")
	private static synchronized void getPropertyFile() {
		logger = Logger.getLogger("TestProject");
		PropertyConfigurator.configure(new File(filePath).getAbsolutePath());
		flag = true;
	}

	private static void getFlag() {
		if (flag == false) {
			log.getPropertyFile();
		}
	}

	/***
	 * 正确日志打印
	 * 
	 * @param message
	 */
	public static void logInfo(String message) {
		log.getFlag();
		logger.info(message);
	}

	/***
	 * 错误日志打印
	 * 
	 * @param message
	 * @throws InterruptedException
	 * @throws Exception
	 */
	public static void logError(String message) throws InterruptedException,
			Exception {

		snapshot.screenShot();
		log.getFlag();
		logger.error(message);
	}

	/***
	 * 警告日志打印
	 * 
	 * @param message
	 */
	public static void logWarn(String message) {

		log.getFlag();
		logger.warn(message);
	}
}
