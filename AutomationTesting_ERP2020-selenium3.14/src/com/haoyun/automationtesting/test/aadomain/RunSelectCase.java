//
package com.haoyun.automationtesting.test.aadomain;

import org.testng.annotations.Test;

import com.haoyun.automationtesting.framework.AutoCase_SinglePackage;
import com.haoyun.automationtesting.framework.ExcelOperate;

/**
 * 执行单个用例，主要用于代码调试时使用。
 * 
 * @author Administrator
 *
 */
public class RunSelectCase {

	@Test
	// (invocationCount = 1, threadPoolSize = 1)
	public static void run() throws Exception {
		System.out.printf("运行选择的用例RunSelectCase,Thrad Id : %s%n", Thread
				.currentThread().getId());
		ExcelOperate.delete_sheel1toN();// 删除sheet页，除了第一页
		
		//AutoCase_SinglePackage.inputexcel("h002办公用品建档", "H002_0001", "", "1");
		//AutoCase_SinglePackage.inputexcel("h002办公用品建档", "H002_0003", "", "1");
		//AutoCase_SinglePackage.inputexcel("h002办公用品建档", "H002_0002", "", "1");
		//AutoCase_SinglePackage.inputexcel("h002办公用品建档", "H002_0004", "", "1");
		//AutoCase_SinglePackage.inputexcel("h723简易方案", "H723_0002", "", "1");
		//AutoCase_SinglePackage.inputexcel("h170110机构基本信息管理", "H170110_0001", "", "1");
		//AutoCase_SinglePackage.inputexcel("h0701生产任务管理", "H0701_0001", "", "1");
		AutoCase_SinglePackage.inputexcel("h0502产品销售下单", "H0502_0001", "", "1");
		MainStart.init();
		MainStart.main();
		MainStart.after();

		// YBDeviceRunCall.run(1, 0, 0, 0, 0);

	}

}
