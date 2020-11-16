package com.haoyun.automationtesting.test.aadomain;

import org.testng.annotations.Test;

import com.haoyun.automationtesting.framework.action;
/**
 * 执行单个用例，主要用于代码调试时使用。
 * 
 * @author Administrator
 *
 */
public class RunSelectCase_thread2 {

	@Test
	// (invocationCount = 1, threadPoolSize = 1)
	public static void run() throws Exception {
		System.out.printf("RunSelectCase,Thrad Id : %s%n", Thread
				.currentThread().getId());
		// ExcelOperate.delete_sheel1toN();// 删除sheet页，除了第一页
		// AutoCase_SinglePackage.inputexcel("h020用户管理", "H020_0010", "", "1");
		// AutoCase_SinglePackage.inputexcel("h730联动配置", "H730_0010", "", "1");

		// MainStart.init();
		// MainStart.main();
		// MainStart.after();
		action.sleep(1);



	}
}
