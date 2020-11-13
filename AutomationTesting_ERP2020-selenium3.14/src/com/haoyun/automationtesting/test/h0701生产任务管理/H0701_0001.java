package com.haoyun.automationtesting.test.h0701生产任务管理;
import com.haoyun.automationtesting.framework.action;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.haoyun.automationtesting.framework.ExcelOperate;
import com.haoyun.automationtesting.framework.TestCase;
import com.haoyun.automationtesting.framework.log;
import com.haoyun.automationtesting.page.PM;
import com.haoyun.automationtesting.page._H0701;


/***
 * @用例名称:设置预计交付时间
 * @author 
 */
public class H0701_0001 extends _H0701 implements TestCase {
	public H0701_0001() {
	}

	public H0701_0001(WebDriver driver) {
		super(driver);
	}
	
	
	@Override
	public void testcase() throws Exception {
		// TODO 自动生成的方法存根
		sleep(3);
		String sheetname = H0701_0001.class.getPackage().getName().split("\\.")[4];
		int index = ExcelOperate.getclassname_rows(this.getClass()
				.getSimpleName(), sheetname);// 获取该用例在excel中的行数

		try {//CaseStepStart
			log.logInfo("开始设置预计交付时间用例");
			H0701_clickMenu();
			H0701_YJJFSJ(PM.SCSQDH);
			action.sleep(1);

			//action.assertReport(By.xpath("//span[@title='"+newName+"']"), newName, "修改办公用品用例验证通过", "修改办公用品用例验证失败", sheetname, index);
			action.findElement_click(By.xpath("//span[@title='"+ PM.SCSQDH +"']/parent::*/parent::*/parent::*//button[contains(.,'项目详情')]"));
			action.assertReport(By.xpath("//input[@controlvalue='"+PM.YJJFSJ +"']"),"设置预计交付时间用例执行成功","设置预计交付时间用例执行失败",sheetname,index);
			PM.returnMenu();
			// CaseStepEnd
		} catch (Exception e) {
			log.logError("设置预计交付时间用例验证失败");
			ExcelOperate.cellexcelerr(sheetname, index);
			e.printStackTrace();
		}
	}
}
