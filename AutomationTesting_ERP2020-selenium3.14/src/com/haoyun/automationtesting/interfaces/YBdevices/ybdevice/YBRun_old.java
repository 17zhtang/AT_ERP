package com.haoyun.automationtesting.interfaces.YBdevices.ybdevice;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import org.testng.annotations.Test;

import com.haoyun.automationtesting.interfaces.YBdevices.packets.PacketBuilder;
import com.haoyun.automationtesting.page.PM;

public class YBRun_old {

	public static final boolean DEBUG = true;// false;

	static String SERVER_IP = "192.168.8.184";
	static int SERVER_PORT = 40001;

	// public static void main(String[] args) {
	@Test
	public static void YBdemo() throws Exception {
		// TODO Auto-generated method stub
		// System.out.format("%02X " + ConverterHelper.encodeHEX(1));
		// 1.构建实例
		PacketBuilder ybDevice = new PacketBuilder();

		// 2.设置电量模块数量，测控模块数量，可以按默认值，默认电量5个，测控模块64.
		if (DEBUG) {
			ybDevice.setElectriModuleCount(1);
			ybDevice.setMeasureModuleCount(1);
		}

		String deviceID = PM.YBDeviceID;// "11111111";

		byte[] buffer = null;
		try {
			buffer = ybDevice.CreatePacket(deviceID);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (null == buffer) {
			System.out.println("buffer is null");
			return;
		}

		if (DEBUG) {
			for (byte value : buffer) {
				System.out.format("%02X ", value);
			}
		}
		// PM.YBSendData = new String(buffer);
		try {

			Socket socket = new Socket(SERVER_IP, SERVER_PORT);
			OutputStream os = socket.getOutputStream();

			int cnt = 1;

			while (cnt-- > 0) {
				PM.YBDeviceCurrentAlarmStatus = (byte) 0x01;// A相电流预警
				PM.YBDevicemainModule_AlarmStatus = (byte) 0x00;// A相电流预警
				PM.YBDeviceCurrentWarningStatus = (byte) 0x00;// A相过流预警
				PM.YBDeviceTemperatureStatus = (byte) 0x00;// A相过流预警
				PM.YBDeviceVoltageStatus = (byte) 0x00;// A相过压报警
				os.write(ybDevice.CreatePacket(deviceID));
				Thread.sleep(5 * 1000);
			}

			os.close();
			socket.close();

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
}
