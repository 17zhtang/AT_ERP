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

//		AutoCase_SinglePackage.inputexcel("h005基础数据", "H005_0010", "", "1");
        AutoCase_SinglePackage.inputexcel("h0406方案管理", "H0406_0000", "方案管理-方案清单-新增", "1");
		AutoCase_SinglePackage.inputexcel("h0406方案管理", "H0406_0005", "方案管理-方案清单-修改", "1");
		AutoCase_SinglePackage.inputexcel("h0406方案管理", "H0406_0010", "方案管理-方案清单-搜索", "1");
		AutoCase_SinglePackage.inputexcel("h0406方案管理", "H0406_0015", "方案管理-方案清单-删除", "1");




		MainStart.init();
		MainStart.main();
		MainStart.after();



	}

}
