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
 * @用例名称:设备管理-设备列表-新增设备-智能安全配电箱
 * @author Linjf
 */
public class H710_0030 extends _H710 implements TestCase{
	public H710_0030(){
		super();
	}
	public H710_0030(WebDriver driver){
		super(driver);
	}
	
	@Override
	public void testcase() throws Exception{
		String sheetname = H710_0030.class.getPackage().getName().split("\\.")[4];
		int index=ExcelOperate.getclassname_rows(this.getClass().getSimpleName(), sheetname);
		
		try{//CaseStepStart
			String deviceType="智能安全配电箱";
			String deviceName="配电箱"+ PM.sjs6;
			String deviceID= PM.sjs6+ PM.sjs6;//12位
			//h710_SBXZ(deviceType,deviceName,"5918",getGuid(),getRandomIp(),"自动化基础数据","7楼","7号","茶水间","设备规格00001","曼顿","","1");
			h710_SBXZ(deviceType, deviceName,deviceID, getRandomIp());

			String sql=new StringBuffer(
					"select deviceID from es_deviceinfo where deviceID=")
			.append("\'").append(deviceID).append("\'").toString();
			
			action.assertReport(sql, deviceID, "用例验证成功", "用例验证失败", sheetname, index);

			mysql.Delete("delete from es_deviceinfo where deviceName='"+deviceName+"'");
			//CaseStepEnd
		}
		catch(Exception e){
			log.logError("用例验证失败");
			ExcelOperate.cellexcelerr(sheetname, index);
			e.printStackTrace();
		}
	}

}
