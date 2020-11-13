package com.haoyun.automationtesting.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.haoyun.automationtesting.framework.action;

/***
 * @功能模块:设备管理-联动配置
 * @作用:页面元素定位,包括属性数据及方法
 * @author Linjf
 *
 */
public class _H730 extends action{
	public _H730() {	
		super();	
		}
	public _H730(WebDriver driver){
		super(driver);
	}
	
	/**
	 * 点击设备管理-联动配置菜单
	 * 
	 */
	public static void tapDeviceMenu() throws Exception{
		PM.return_sy();
		PM.menu_text("设备管理");
		PM.menu_text("联动配置");
	}
	
	/**
	 * 业务步骤 新增联动配置
	 * 
	 * @param deviceType
	 *            ：设备类型 ，用电安全监测主机、智能安全配电箱、智能用电管控终端
	 * @param org
	 *            ：所属机构
	 * @param ip
	 *            ：设备IP，曼顿和优柏设备才需要填写       
	 * @param deviceName
	 *            ：设备名称        
	 * @throws Exception
	 */
	public static void h730_LDPZXZ(String deviceType,String org,String ip,String deviceName) throws Exception{
		tapDeviceMenu();
		
		PM.normalize_texts_clicks("新增联动配置");		
		PM.normalize_inputs_clicks("所属机构");
		PM.pop_click_textspan("所有机构");
		PM.normalize_texts_clicks(org);	//自动化基础数据		
		
		PM.normalize_inputs_clicks("设备类型");	
		PM.normalize_texts_clicks(deviceType);	
		action.findElement_click(By.xpath("//span[text()='"+deviceName+"']"));
		action.findElement_click(By.xpath("//span[text()='OUT1']"));
		if(deviceType.equals("用电安全监测主机")==false){	
			//action.findElement_click(By.xpath("//span[text()='自动化基础数据_用电安全监测主机_浩云']"));			
			PM.normalize_inputs_sendkeys("设备IP", ip);//getRandomIp()			
		}
		
		/*else{			
			action.findElement_click(By.xpath("//span[text()='自动化基础数据_智能用电管控终端_优柏']"));		
			PM.normalize_inputs_sendkeys("设备IP",  ip);
			action.findElement_click(By.xpath("//span[text()='OUT1']"));				
		}*/	
		PM.click_QD();	
	}
	
	/**
	 * 业务步骤 新增联动配置
	 * <p>默认添加自动化基础数据机构下的联动配置，ip会随机生成
	 * @param deviceType
	 *            ：设备类型 ，用电安全监测主机、智能安全配电箱、智能用电管控终端
	 * @param deviceName
	 *            ：设备名称             
	 * @throws Exception
	 */
	public static void h730_LDPZXZ(String deviceType,String deviceName) throws Exception{
		h730_LDPZXZ(deviceType,"自动化基础数据",getRandomIp(),deviceName);
	}
	
	/**
	 * 业务步骤  修改联动配置
	 * 
	 * @param  deviceName
	 *         :设备名称       
	 * @param  isEnabled
	 *         :是否启用
	 * @throws Exception
	 */
	//联动配置页面没有查询功能
	public static void h730_LDPZXG(String deviceName,String isEnabled) throws Exception{
		tapDeviceMenu();
		PM.click_TableRowButton(deviceName, "1");//修改按钮
		//PM.normalize_texts_clicks("修改");
		action.sleep(1);
		action.getText(By.xpath("//label[@class='el-checkbox is-disabled is-checked']"));		 
		action.sleep(1);
		PM.normalize_inputs_clicks("是否启用");
		PM.normalize_texts_clicks(isEnabled);//否
		PM.click_QD();	
	}
	
	/**
	 * 业务步骤  删除联动配置
	 *  @param deviceName
	 *        :设备名称
	 *  @throws Exception
	 */
	//联动配置页面没有查询功能
	public static void h730_LDPZSC(String deviceName) throws Exception{
		tapDeviceMenu();
		action.sleep(1);
		PM.click_TableRowButton(deviceName, "2");//删除按钮
       // PM.click_SC();
        action.sleep(1);
		if (action.isTextPresent("确认删除选中记录吗？")) {
			PM.click_QD();
		}
		action.sleep(1);
	}
	
	

	
}
