package com.haoyun.automationtesting.test.aadomain;

import org.testng.annotations.Test;

import com.haoyun.automationtesting.framework.AutoCase;
import com.haoyun.automationtesting.framework.ExcelOperate;


public class CodeToCase {


	@Test
	public static void zhu() throws Exception {

//		AutoExtImportCode.charOutStream("HY01666hy01001");	
//		action.sleep(1);
		ExcelOperate.delete_sheel1toN();//删除sheet页，除了第一页
		AutoCase.MainAutoCase();//自动生成用例
		
	}
}
