package com.haoyun.automationtesting.interfaces.YBdevices.ybdevice;

import com.haoyun.automationtesting.framework.log;
import com.haoyun.automationtesting.page.PM;

/*
 //新增优柏设备的报警项：
 1、输入缺相缺
 2、A相输入缺相缺
 3、B相输入缺相
 4、C相输入缺相
 5、RS485通信异常报警
 6、欠压报警
 7、A相欠压缺相缺
 8、B相欠压缺相缺
 9、C相欠压缺相缺
 10、过压报警
 11、A相过压报警
 12、B相过压报警
 13、C相过压报报警
 14、温度报警
 15、A温度报警
 16、B温度报警
 17、C温度报警
 18、温度预警
 19、A温度预警	20、B温度预警
 21、C温度预警
 22、过流报警
 23、A过流报警
 24、B过流报警
 25、C过流报警
 26、电流预警
 27、A电流预警
 28、B电流预警
 29、C电流预警
 30、打火报警
 31、A打火报警
 32、B打火报警
 33、C打火报警
 34、设备门磁报警
 35、设备水浸报警

 36、漏电报警
 37、漏电预警

 38、实弹断电报警、
 39、网络掉线报警
 */
public class YBPackage {
	public static final String YBDeviceCurrentAlarmStatus1 = "A相电流预警";
	public static final String YBDeviceCurrentAlarmStatus2 = "B相电流预警";
	public static final String YBDeviceCurrentAlarmStatus3 = "C相电流预警";
	public static final String YBDeviceCurrentWarningStatus1 = "A相过流报警";
	public static final String YBDeviceCurrentWarningStatus2 = "B相过流报警";
	public static final String YBDeviceCurrentWarningStatus3 = "C相过流报警";
	public static final String YBDeviceCurrentWarningStatus4 = "A相打火报警";
	public static final String YBDeviceCurrentWarningStatus5 = "B相打火报警";
	public static final String YBDeviceCurrentWarningStatus6 = "C相打火报警";

	public static final String YBDeviceTemperatureStatus1 = "A相温度上限预警";
	public static final String YBDeviceTemperatureStatus2 = "B相温度上限预警";
	public static final String YBDeviceTemperatureStatus3 = "C相温度上限预警";
	public static final String YBDeviceTemperatureStatus4 = "A相温度上限报警";
	public static final String YBDeviceTemperatureStatus5 = "B相温度上限报警";
	public static final String YBDeviceTemperatureStatus6 = "C相温度上限报警";
	public static final String YBDevicemainModule_AlarmStatus = "温度上限报警";

	public static final String YBDeviceVoltageStatus0 = "A相过压报警";
	public static final String YBDeviceVoltageStatus1 = "B相过压报警";
	public static final String YBDeviceVoltageStatus2 = "C相过压报警";
	public static final String YBDeviceVoltageStatus4 = "A相欠压报警";
	public static final String YBDeviceVoltageStatus5 = "B相欠压报警";
	public static final String YBDeviceVoltageStatus6 = "C相欠压报警";

	public static final String YBDevicephaseLossStatusA = "A相输入缺相";
	public static final String YBDevicephaseLossStatusB = "B相输入缺相";
	public static final String YBDevicephaseLossStatusC = "C相输入缺相";

	public static final String moduleStatus8 = "RS458 通讯异常报警";
	public static final String YBDevicegetInputStatus1 = "设备水浸报警";
	public static final String YBDevicegetInputStatus2 = "设备门磁报警";

	public static final String YBDeviceleakageStatus = "剩余电流报警";

	/**
	 * 报警组包
	 * 
	 * @param AlarmName
	 *            报警名称
	 */
	public static void YBDeviceAlarmInit(String AlarmName) {
		switch (AlarmName.trim()) {
		case YBDeviceCurrentAlarmStatus1:
			PM.YBDeviceCurrentAlarmStatus = (byte) 0x01;// A相电流预警
			log.logInfo("发送一条报警：" + YBDeviceCurrentAlarmStatus1);
			break;
		case YBDeviceCurrentAlarmStatus2:
			PM.YBDeviceCurrentAlarmStatus = (byte) 0x02;// B相电流预警
			log.logInfo("发送一条报警：" + YBDeviceCurrentAlarmStatus2);
			break;
		case YBDeviceCurrentAlarmStatus3:
			PM.YBDeviceCurrentAlarmStatus = (byte) 0x04;// C相电流预警
			log.logInfo("发送一条报警：" + YBDeviceCurrentAlarmStatus3);
			break;
		case YBDevicemainModule_AlarmStatus:
			PM.YBDevicemainModule_AlarmStatus = (byte) 0x01;// 温度上限报警
			log.logInfo("发送一条报警：" + YBDevicemainModule_AlarmStatus);
			break;
		case YBDeviceCurrentWarningStatus1:
			PM.YBDeviceCurrentWarningStatus = (byte) 0x01;// A相过流预警
			log.logInfo("发送一条报警：" + YBDeviceCurrentWarningStatus1);
			break;
		case YBDeviceCurrentWarningStatus2:
			PM.YBDeviceCurrentWarningStatus = (byte) 0x02;// B相过流预警
			log.logInfo("发送一条报警：" + YBDeviceCurrentWarningStatus2);
			break;
		case YBDeviceCurrentWarningStatus3:
			PM.YBDeviceCurrentWarningStatus = (byte) 0x04;// C相过流预警
			log.logInfo("发送一条报警：" + YBDeviceCurrentWarningStatus3);
			break;
		case YBDeviceCurrentWarningStatus4:
			PM.YBDeviceCurrentWarningStatus = (byte) 0x018;// A相打火报警
			log.logInfo("发送一条报警：" + YBDeviceCurrentWarningStatus4);
			break;
		case YBDeviceCurrentWarningStatus5:
			PM.YBDeviceCurrentWarningStatus = (byte) 0x020;// B相打火报警
			log.logInfo("发送一条报警：" + YBDeviceCurrentWarningStatus5);
			break;
		case YBDeviceCurrentWarningStatus6:
			PM.YBDeviceCurrentWarningStatus = (byte) 0x040;// C相打火报警
			log.logInfo("发送一条报警：" + YBDeviceCurrentWarningStatus6);
			break;
		case YBDeviceTemperatureStatus1:
			PM.YBDeviceTemperatureStatus = (byte) 0x01;// A相温度上限预警
			log.logInfo("发送一条报警：" + YBDeviceTemperatureStatus1);
			break;
		case YBDeviceTemperatureStatus2:
			PM.YBDeviceTemperatureStatus = (byte) 0x02;// B相温度上限预警
			log.logInfo("发送一条报警：" + YBDeviceTemperatureStatus2);
			break;
		case YBDeviceTemperatureStatus3:
			PM.YBDeviceTemperatureStatus = (byte) 0x04;// C相温度上限预警
			log.logInfo("发送一条报警：" + YBDeviceTemperatureStatus3);
			break;
		case YBDeviceTemperatureStatus4:
			PM.YBDeviceTemperatureStatus = (byte) 0x018;// A相温度上限报警
			log.logInfo("发送一条报警：" + YBDeviceTemperatureStatus4);
			break;
		case YBDeviceTemperatureStatus5:
			PM.YBDeviceTemperatureStatus = (byte) 0x20;// B相温度上限报警
			log.logInfo("发送一条报警：" + YBDeviceTemperatureStatus5);
			break;
		case YBDeviceTemperatureStatus6:
			PM.YBDeviceTemperatureStatus = (byte) 0x40;// C相温度上限报警
			log.logInfo("发送一条报警：" + YBDeviceTemperatureStatus6);
			break;
		case YBDeviceVoltageStatus0:
			PM.YBDeviceVoltageStatus = (byte) 0x01;// A相过压报警
			log.logInfo("发送一条报警：" + YBDeviceVoltageStatus0);
			break;
		case YBDeviceVoltageStatus1:
			PM.YBDeviceVoltageStatus = (byte) 0x02;// B相过压报警
			log.logInfo("发送一条报警：" + YBDeviceVoltageStatus1);
			break;
		case YBDeviceVoltageStatus2:
			PM.YBDeviceVoltageStatus = (byte) 0x04;// C相过压报警
			log.logInfo("发送一条报警：" + YBDeviceVoltageStatus2);
			break;
		case YBDeviceVoltageStatus4:
			PM.YBDeviceVoltageStatus = (byte) 0x18;// A相欠压报警
			log.logInfo("发送一条报警：" + YBDeviceVoltageStatus4);
			break;
		case YBDeviceVoltageStatus5:
			PM.YBDeviceVoltageStatus = (byte) 0x20;// B相欠压报警
			log.logInfo("发送一条报警：" + YBDeviceVoltageStatus5);
			break;
		case YBDeviceVoltageStatus6:
			PM.YBDeviceVoltageStatus = (byte) 0x40;// C相欠压报警
			log.logInfo("发送一条报警：" + YBDeviceVoltageStatus6);
			break;

		case YBDevicephaseLossStatusA:
			PM.YBDevicephaseLossStatus = (byte) 0x01;// A相输入缺相
			log.logInfo("发送一条报警：" + YBDevicephaseLossStatusA);
			break;
		case YBDevicephaseLossStatusB:
			PM.YBDevicephaseLossStatus = (byte) 0x02;// B相输入缺相
			log.logInfo("发送一条报警：" + YBDevicephaseLossStatusB);
			break;
		case YBDevicephaseLossStatusC:
			PM.YBDevicephaseLossStatus = (byte) 0x04;// C相输入缺相
			log.logInfo("发送一条报警：" + YBDevicephaseLossStatusC);
			break;
		case moduleStatus8:
			PM.moduleStatus = (byte) 0x08;// RS458 通讯异常报警0x08
			log.logInfo("发送一条报警：" + moduleStatus8);
			break;
		case YBDevicegetInputStatus1:
			PM.YBDeviceinputIO = (byte) 0x20;// 设备水浸报警
			log.logInfo("发送一条报警：" + YBDevicegetInputStatus1);
			break;
		case YBDevicegetInputStatus2:
			PM.YBDeviceinputIO = (byte) 0x40;// 设备门磁报警
			log.logInfo("发送一条报警：" + YBDevicegetInputStatus2);
			break;
		case YBDeviceleakageStatus:
			PM.YBDeviceleakageStatus = (byte) 0x01;// 剩余电流报警
			log.logInfo("发送一条报警：" + YBDeviceleakageStatus);
			break;
		case "allno":// 全部不报警
			PM.YBDeviceCurrentAlarmStatus = (byte) 0x00;// 电流预警
			PM.YBDevicemainModule_AlarmStatus = (byte) 0x00;// 温度上线报警
			PM.YBDeviceCurrentWarningStatus = (byte) 0x00;// 过流预警
			PM.YBDeviceTemperatureStatus = (byte) 0x00;// 温度上限预警
			PM.YBDeviceVoltageStatus = (byte) 0x00;// 过压报警
			PM.YBDevicephaseLossStatus = (byte) 0x00;// 输入缺相
			PM.moduleStatus = (byte) 0x00;// RS458 通讯异常报警
			PM.YBDeviceinputIO = (byte) 0x00;// 开关量输入-包括设备水浸报警，门磁报警
			PM.YBDeviceleakageStatus = (byte) 0x00;// 剩余电流报警
			break;
		case "all":// 全部报警
			PM.YBDeviceCurrentAlarmStatus = (byte) 0x01;// A相电流预警
			PM.YBDevicemainModule_AlarmStatus = (byte) 0x01;// 温度上线报警
			PM.YBDeviceCurrentWarningStatus = (byte) 0x01;// A相过流预警
			PM.YBDeviceTemperatureStatus = (byte) 0x01;// A相温度上限预警
			PM.YBDeviceVoltageStatus = (byte) 0x01;// A相过压报警
			PM.YBDevicephaseLossStatus = (byte) 0x01;// A相输入缺相
			PM.YBDeviceinputIO = (byte) 0x20;// 门磁报警
			break;
		default:
			break;
		}

	}
}
