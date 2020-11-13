package com.haoyun.automationtesting.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.haoyun.automationtesting.framework.action;
import com.haoyun.automationtesting.framework.log;

/***
 * @功能模块:设备管理-设备列表
 * @作用:页面元素定位,包括属性数据及方法
 * @author Linjf
 *
 */
public class _H710 extends action {
	public _H710() {
		super();
	}

	public _H710(WebDriver driver) {
		super(driver);
	}

	/**
	 * 点击设备管理-设备列表菜单
	 * 
	 */
	public static void tapDeviceMenu() throws Exception {
		PM.return_sy();
		PM.menu_text("设备管理");
		PM.menu_text("设备列表");
	}

	/**
	 * 业务步骤 新增设备
	 * 
	 * @param deviceType
	 *            ：设备类型 ，用电安全监测主机、智能安全配电箱、智能用电管控终端
	 * @param deviceName
	 *            :设备名称
	 * @param devicePort
	 *            :设备端口
	 * @param deviceID
	 *            :设备ID，基础数据为16个字节，其他随机获取guid
	 * @param ip
	 *            :设备ip
	 * @param org
	 *            :所属机构
	 * @param floor
	 *            :楼层
	 * @param floorNum
	 *            :楼层 号           
	 * @param detailLoc
	 *            :详细位置       
	 * @param devNormal
	 *            :设备规格           
	 * @param deviceBrand
	 *            :设备品牌             
	 * @param loopNum
	 *            :回路数量
	 * @param deviceSort
	 *            :设备排序   
	 * @throws Exception
	 */
	public static void h710_SBXZ(String deviceType, String deviceName,
			String devicePort, String deviceID, String ip,String org,
			String floor,String floorNum,String detailLoc,String devNormal,
			String deviceBrand,String loopNum,String deviceSort) throws Exception {
		tapDeviceMenu();

		PM.normalize_texts_clicks("新增设备");
		PM.normalize_inputs_clicks("所属机构");
		PM.pop_click_textspan("所有机构");
		PM.normalize_texts_clicks(org);//自动化基础数据

		PM.normalize_inputs_sendkeys("设备名称", deviceName);
		PM.normalize_inputs_sendkeys("设备端口", devicePort);// 5918
		// PM.normalize_inputs_sendkeys("设备位置", "7楼");
		action.findElement_sendkeys(By.xpath("//*[@placeholder='楼层']"), floor);//7楼
		action.findElement_sendkeys(By.xpath("//*[@placeholder='楼层号']"), floorNum);//7号
		action.findElement_sendkeys(By.xpath("//*[@placeholder='详细位置']"), detailLoc);//茶水间

		PM.normalize_inputs_sendkeys("设备规格", devNormal);//设备规格-0001

		PM.normalize_inputs_clicks("设备类型");
		PM.normalize_texts_clicks(deviceType);

		if (deviceType == "用电安全监测主机") {
			PM.normalize_inputs_sendkeys("设备品牌", deviceBrand);//浩云
			PM.normalize_inputs_clicks("回路数量");
			PM.normalize_texts_clicks(loopNum);//16

		} else if (deviceType == "智能安全配电箱") {
			PM.normalize_inputs_sendkeys("设备品牌", deviceBrand);//曼顿
		} else {
			PM.normalize_inputs_sendkeys("设备品牌", deviceBrand);//优柏
		}

		PM.normalize_inputs_sendkeys("设备排序", deviceSort);//1
		PM.normalize_inputs_sendkeys("设备IP", ip);// getRandomIp()
		PM.normalize_inputs_sendkeys("设备ID", deviceID);
		PM.click_TJ();
	}
	/**
	 * 业务步骤 新增设备
	 * <p>默认添加1个挂在自动化基础数据下的设备
	 * @param deviceType
	 *            ：设备类型 ，用电安全监测主机、智能安全配电箱、智能用电管控终端
	 * @param deviceName
	 *            :设备名称
	 * @param deviceID
	 *            :设备ID，基础数据为16个字节，其他随机获取guid
	 * @param ip
	 *            :设备ip
	 * @throws Exception
	 */
	public static void h710_SBXZ(String deviceType, String deviceName,
			 String deviceID, String ip) throws Exception {	
		if(deviceType.equals("智能安全配电箱")){
			h710_SBXZ(deviceType,deviceName,"5918",deviceID,ip,"自动化基础数据",
					"7楼","7号","茶水间","设备规格00001","优柏","","1");
		}else if(deviceType.equals("智能用电管控终端")){
			h710_SBXZ(deviceType,deviceName,"5918",deviceID,ip,"自动化基础数据",
					"7楼","7号","茶水间","设备规格00001","曼顿","","1");				
		}else{
			h710_SBXZ(deviceType,deviceName,"5918",deviceID,ip,"自动化基础数据",
					"7楼","7号","茶水间","设备规格00001","浩云","16","1");
		}		
	}
	
	/**
	 * 业务步骤 查询设备
	 * 
	 * @param 查询项
	 *            ：deviceName :设备名称
	 * @param 查询项
	 *            ：IP :设备IP
	 * @throws Exception
	 */
	public static void h710_SBCX(String deviceName, String IP) throws Exception {
		tapDeviceMenu();
		// Device_XZ("智能安全配电箱",deviceName);
		if (!deviceName.trim().isEmpty()) {
			PM.normalize_inputs_sendkeys("设备名称：", deviceName);
		}
		if (!IP.trim().isEmpty()) {
			PM.normalize_inputs_sendkeys("设备IP：", IP);
		}
		action.sleep(1);
		PM.click_CX();
		log.logInfo("查询项 deviceName：" + deviceName);
		log.logInfo("查询项 IP" + IP);
	}

	/**
	 * 业务步骤 重置设备
	 * 
	 * @param deviceName
	 *            :设备名称
	 * @throws Exception
	 */
	public static void h710_SBCZ(String deviceName) throws Exception {
		tapDeviceMenu();
		h710_SBCX(deviceName, "");
		PM.click_CZ();
	}

	/**
	 * 业务步骤 修改设备
	 * 
	 * @param deviceName
	 *            :设备名称，需要修改的设备名称
	 * @param remarks
	 *            :备注，需要修改的字段
	 * @throws Exception
	 */
	public static void h710_SBXG(String deviceName,String remarks) throws Exception {
		h710_SBCX(deviceName, "");
		action.sleep(1);
		PM.normalize_texts_clicks("修改");
		action.sleep(1);
		action.findElement_sendkeys(By.className("el-textarea__inner"),
				remarks);//自动化测试_修改设备_填写备注
		PM.click_TJ();
	}

	/**
	 * 业务步骤 删除设备
	 * 
	 * @param deviceName
	 *            :设备名称
	 * @throws Exception
	 */

	public static void h710_SBSC(String deviceName) throws Exception {

		h710_SBCX(deviceName, "");
		action.sleep(1);
		PM.click_SC();
		action.sleep(1);
		if (action.isTextPresent("确认删除选中记录吗？")) {
			PM.click_QD();

		}
		action.sleep(1);

	}

}
