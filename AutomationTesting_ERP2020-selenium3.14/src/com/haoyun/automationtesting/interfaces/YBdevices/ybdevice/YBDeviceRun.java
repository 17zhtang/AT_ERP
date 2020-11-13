package com.haoyun.automationtesting.interfaces.YBdevices.ybdevice;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import org.testng.annotations.Test;

import com.haoyun.automationtesting.framework.Config;
import com.haoyun.automationtesting.interfaces.YBdevices.packets.PacketBuilder;
import com.haoyun.automationtesting.page.PM;

/**
 * 暂时不用。多线程，长连接在线时候用到
 * 
 * @author Administrator
 *
 */
public class YBDeviceRun {
	public static Socket socket = null;
	public static OutputStream os = null;

	@Test
	// (invocationCount = 1, threadPoolSize = 2)
	// 3个线程同时运行，invocationCount共执行1次
	public static void run() throws Exception {
		System.out.printf("YBDeviceRun,Thrad Id : %s%n", Thread.currentThread()
				.getId());
		YBDeviceSend(10);// 5秒一次，执行100次

	}

	/**
	 * 优柏设备：正常在线状态
	 * 
	 * @param cnt
	 *            执行次数
	 * @throws Exception
	 */
	public static void YBDeviceSend(int cnt) throws Exception {

		// 1.构建实例
		PacketBuilder ybDevice = new PacketBuilder();

		// 2.设置电量模块数量，测控模块数量，可以按默认值，默认电量5个，测控模块64.
		{
			ybDevice.setElectriModuleCount(1);
			ybDevice.setMeasureModuleCount(1);
		}

		for (byte value : ybDevice.CreatePacket(PM.YBDeviceID)) {
			System.out.format("%02X ", value);
		}

		try {

			socket = new Socket(Config.IP, Config.YBPort);
			os = socket.getOutputStream();
			// int cnt = 100;// 执行100*15秒

			while (cnt-- > 0) {
				YBPackage.YBDeviceAlarmInit("allno");
				os.write(ybDevice.CreatePacket(PM.YBDeviceID));// 设备正常状态
				Thread.sleep(5 * 1000);// 等待15秒
				// System.out.printf(Thread.currentThread().getId() +
				// "时间 : %s%n",
				// cnt);
			}

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
