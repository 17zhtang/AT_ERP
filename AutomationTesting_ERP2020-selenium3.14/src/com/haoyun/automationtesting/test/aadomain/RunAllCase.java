package com.haoyun.automationtesting.test.aadomain;

import org.testng.annotations.Test;

import com.haoyun.automationtesting.framework.AutoCase;
import com.haoyun.automationtesting.framework.ExcelOperate;

/**
 * 执行代码中全部用例，会自动同步excel中数据
 * 
 * @author Administrator
 *
 */
public class RunAllCase {

	@Test
	// (invocationCount = 1, threadPoolSize = 2)
	public static void run() throws Exception {
		ExcelOperate.delete_sheel1toN();// 删除sheet页，除了第一页
		AutoCase.MainAutoCase();// 自动生成用例
		ExcelOperate.cellexcelinit_all();// 用例执行状态全部置为 1
		MainStart.init();
		MainStart.main();
		MainStart.after();

	}
}
