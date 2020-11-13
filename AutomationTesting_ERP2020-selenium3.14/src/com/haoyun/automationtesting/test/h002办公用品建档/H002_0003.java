package com.haoyun.automationtesting.test.h002办公用品建档;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.haoyun.automationtesting.framework.ExcelOperate;
import com.haoyun.automationtesting.framework.TestCase;
import com.haoyun.automationtesting.framework.action;
import com.haoyun.automationtesting.framework.log;
import com.haoyun.automationtesting.page._H002;

/***
 * @用例名称:查找办公用品
 * @author 
 */
public class H002_0003 extends _H002 implements TestCase {
	public H002_0003() {
	}

	public H002_0003(WebDriver driver) {
		super(driver);
	}
	
	
	@Override
	public void testcase() throws Exception {
		// TODO 自动生成的方法存根
		sleep(3);
		String sheetname = H002_0003.class.getPackage().getName().split("\\.")[4];
		int index = ExcelOperate.getclassname_rows(this.getClass()
				.getSimpleName(), sheetname);// 获取该用例在excel中的行数
		//测试数据
		String name = "双飞燕键盘2";  
		try {//CaseStepStart
			log.logInfo("开始查找办公用品用例");
			H002_clickMenu();
			sleep(2);
			H002_searchByName(name);
			action.assertReport(By.xpath("//span[@title='"+name+"']"), name, "查找办公用品用例验证通过", "查找办公用品用例验证失败", sheetname, index);
			H002_returnMenu();
			// CaseStepEnd
		} catch (Exception e) {
			log.logError("查找办公用品用例验证失败");
			ExcelOperate.cellexcelerr(sheetname, index);
			e.printStackTrace();
		}
	}
}

