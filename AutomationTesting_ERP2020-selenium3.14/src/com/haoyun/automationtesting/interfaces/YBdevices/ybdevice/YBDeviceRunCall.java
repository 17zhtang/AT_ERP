package com.haoyun.automationtesting.interfaces.YBdevices.ybdevice;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import org.testng.annotations.Test;

import com.haoyun.automationtesting.framework.Config;
import com.haoyun.automationtesting.interfaces.YBdevices.packets.PacketBuilder;
import com.haoyun.automationtesting.page.PM;

public class YBDeviceRunCall {
	public static Socket socket = null;
	public static OutputStream os = null;

	// (invocationCount = 1, threadPoolSize = 2)
	// 3个线程同时运行，invocationCount共执行1次
	// public static void run() throws Exception {
	// // System.out.printf("yb,Thrad Id : %s%n",
	// // Thread.currentThread().getId());
	// run(0, 1, 0, 0, 0);
	//
	// }
	
	@Test
	public static void runtest() throws Exception {
		
		run("温度上限报警");
		
	}

	/**
	 * 发送报警事件
	 * 
	 * @param a
	 *            A相电流预警
	 * @param b
	 *            温度上限报警
	 * @param c
	 *            A相过流报警
	 * @param d
	 *            A相温度上限预警
	 * @param e
	 *            A相过压报警
	 * @param f
	 *            A相输入缺相
	 * @throws Exception
	 */
	//@Test
	public static void run(String alarmname) throws Exception {

		// 1.构建实例
		PacketBuilder ybDevice = new PacketBuilder();

		// 2.设置电量模块数量，测控模块数量，可以按默认值，默认电量5个，测控模块64.
		{
			ybDevice.setElectriModuleCount(1);
			ybDevice.setMeasureModuleCount(1);
		}

		// for (byte value : ybDevice.CreatePacket(PM.YBDeviceID)) {
		// System.out.format("%02X ", value);
		// }

		try {

			socket = new Socket(Config.IP, Config.YBPort);
			os = socket.getOutputStream();

			YBPackage.YBDeviceAlarmInit("allno");
			os.write(ybDevice.CreatePacket(PM.YBDeviceID));// 初始化为正常在线状态。如果设备在报警状态再次发送同样的报警会不显示
			Thread.sleep(1000);
			YBPackage.YBDeviceAlarmInit(alarmname);
			os.write(ybDevice.CreatePacket(PM.YBDeviceID));
			Thread.sleep(5 * 1000);
			YBDeviceClose();

		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	public static void YBDeviceClose() {
		try {
			os.close();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		try {
			socket.close();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}

}
