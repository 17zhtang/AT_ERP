package com.haoyun.automationtesting.test.h002办公用品建档;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.haoyun.automationtesting.framework.ExcelOperate;
import com.haoyun.automationtesting.framework.TestCase;
import com.haoyun.automationtesting.framework.action;
import com.haoyun.automationtesting.framework.log;
import com.haoyun.automationtesting.page._H002;


/***
 * @用例名称:修改办公用品
 * @author 
 */
public class H002_0002 extends _H002 implements TestCase {
	public H002_0002() {
	}

	public H002_0002(WebDriver driver) {
		super(driver);
	}
	
	
	@Override
	public void testcase() throws Exception {
		// TODO 自动生成的方法存根
		sleep(3);
		String sheetname = H002_0002.class.getPackage().getName().split("\\.")[4];
		int index = ExcelOperate.getclassname_rows(this.getClass()
				.getSimpleName(), sheetname);// 获取该用例在excel中的行数
		//测试数据
		String name = "双飞燕键盘";  
		String newName = "双飞燕键盘2";
		String newSpecification ="DEF";
		String newUnit = "个";
		try {//CaseStepStart
			log.logInfo("开始修改办公用品用例");
			H002_clickMenu();
			H002_modify(name,newName,newSpecification,newUnit);
			sleep(2);
			H002_searchByName(newName);
			action.assertReport(By.xpath("//span[@title='"+newName+"']"), newName, "修改办公用品用例验证通过", "修改办公用品用例验证失败", sheetname, index);	
			H002_returnMenu();
			// CaseStepEnd
		} catch (Exception e) {
			log.logError("修改办公用品用例验证失败");
			ExcelOperate.cellexcelerr(sheetname, index);
			e.printStackTrace();
		}
	}
}

