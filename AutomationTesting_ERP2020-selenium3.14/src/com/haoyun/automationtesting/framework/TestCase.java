/*
 * Copyright (C) 2017 China Construction Bank
 *
 * 本程序中所包含的信息属于机密信息，如无中国建设银行的书面许可，任何人都无权复制或利用。
 */
package com.haoyun.automationtesting.framework;

/**
 * 
 *
 * @author
 *
 */
public interface TestCase {

	// public void test(GenericDao dao);//包含数据库参数
	public void testcase() throws Exception;

	// AndroidDriver<AndroidElement> getDriver();

	// public AndroidDriver<AndroidElement> getDriver() ;
}
