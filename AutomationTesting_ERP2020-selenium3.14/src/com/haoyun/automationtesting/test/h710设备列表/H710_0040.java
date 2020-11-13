package com.haoyun.automationtesting.test.h710设备列表;

import org.openqa.selenium.WebDriver;

import com.haoyun.automationtesting.framework.ExcelOperate;
import com.haoyun.automationtesting.framework.TestCase;
import com.haoyun.automationtesting.framework.action;
import com.haoyun.automationtesting.framework.log;
import com.haoyun.automationtesting.framework.mysql;
import com.haoyun.automationtesting.page.PM;
import com.haoyun.automationtesting.page._H710;

/***
 * @用例名称:设备管理-设备列表--按设备IP查询（前置用例H710_0010）
 * @author Linjf
 */

public class H710_0040 extends _H710 implements TestCase {
	public H710_0040(){
		super();
	}
	public H710_0040(WebDriver driver){
		super(driver);
	}
	
	@Override
	public void testcase() throws Exception{
		String sheetname = H710_0040.class.getPackage().getName().split("\\.")[4];
		int index=ExcelOperate.getclassname_rows(this.getClass().getSimpleName(), sheetname);
		try{//CaseStepStart
			
			String deviceName="管控终端"+PM.sjs6;
			String sql=new StringBuffer("select deviceIP from es_deviceinfo WHERE deviceName='").append(deviceName).append("'").toString();
			String IP=mysql.select(sql);
			h710_SBCX("",IP);		
			
			action.assertReport(deviceName, "用例验证成功", "用例验证失败", sheetname, index);

            
          //CaseStepEnd		
		}
		catch(Exception e){
			log.logError("用例验证失败");
			ExcelOperate.cellexcelerr(sheetname, index);
			e.printStackTrace();
		}

	}

}
