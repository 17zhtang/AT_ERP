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
 * @用例名称:设备管理-设备列表--修改设备（前置用例H710_0010）
 * @author Linjf
 */

public class H710_0070 extends _H710 implements TestCase {
	public H710_0070(){
		super();
	}
	public H710_0070(WebDriver driver){
		super(driver);
	}
	
	@Override
	public void testcase() throws Exception{
		String sheetname = H710_0070.class.getPackage().getName().split("\\.")[4];
		int index=ExcelOperate.getclassname_rows(this.getClass().getSimpleName(), sheetname);
		try{//CaseStepStart
			String deviceName="管控终端"+PM.sjs6;
			h710_SBXG(deviceName,"自动化测试_修改设备_填写备注");
			
			String sql="select comments from es_deviceinfo where deviceName=" +  "\'"  +deviceName +"\'";
			String sql1=mysql.select(sql);
			if (sql1.equals("自动化测试_修改设备_填写备注")==false) {				
				log.logWarn("没找到要修改的设备，可能新增设备没成功或者新增设备的用例未执行！");
			}
			action.assertReport(sql, "自动化测试_修改设备_填写备注", "用例验证成功", "用例验证失败", sheetname, index);	
			
			//CaseStepEnd			
		}
		catch(Exception e){
			log.logError("用例验证失败");
			ExcelOperate.cellexcelerr(sheetname, index);
			e.printStackTrace();
		}

	}

}
