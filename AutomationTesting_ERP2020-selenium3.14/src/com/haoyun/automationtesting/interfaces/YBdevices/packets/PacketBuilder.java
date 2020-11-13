package com.haoyun.automationtesting.interfaces.YBdevices.packets;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import com.haoyun.automationtesting.interfaces.YBdevices.model.ElectricModule;
import com.haoyun.automationtesting.interfaces.YBdevices.model.MainModule;
import com.haoyun.automationtesting.interfaces.YBdevices.model.MeasureModule;
import com.haoyun.automationtesting.interfaces.YBdevices.model.ProtocolHeader;
import com.haoyun.automationtesting.interfaces.YBdevices.model.ProtocolTail;
import com.haoyun.automationtesting.interfaces.YBdevices.utils.CheckSumHelper;
import com.haoyun.automationtesting.interfaces.YBdevices.utils.EncryptionHelper;

/**
 * 
 * @author Vicent
 *
 */
public class PacketBuilder {

	/**
	 * 电量模块数量
	 */
	private int electriModuleCount;

	public int getElectriModuleCount() {
		return this.electriModuleCount;
	}

	public void setElectriModuleCount(int value) {
		this.electriModuleCount = value < 0 ? 1 : value;
	}

	/**
	 * 测控模块数量
	 */
	private int measureModuleCount;

	public int getMeasureModuleCount() {
		return this.measureModuleCount;
	}

	public void setMeasureModuleCount(int value) {
		this.measureModuleCount = value;
	}

	/**
	 * 协议头
	 */
	private ProtocolHeader header;

	/**
	 * 协议尾
	 */
	private ProtocolTail tail;

	/**
	 * 主机模块
	 */
	private MainModule mainModule;

	/**
	 * 主机模块
	 */
	private List<MeasureModule> measureModuleList;

	/**
	 * 电量模块,不设置，默认最大压测数量，电量模块5个，测控模块64个
	 */
	private List<ElectricModule> electriModuleList;

	public PacketBuilder() {
		this.electriModuleCount = 5;
		this.measureModuleCount = 64;
		init();
	}

	/**
	 * 
	 * @param elctricCnt
	 *            电量模块数量
	 * @param measureCnt
	 *            测控模块数量
	 */
	public PacketBuilder(int electricCnt, int measureCnt) {
		this.electriModuleCount = electricCnt < 0 ? 1 : electricCnt > 5 ? 5
				: electricCnt;
		this.measureModuleCount = measureCnt < 0 ? 1 : measureCnt > 64 ? 64
				: measureCnt;
		init();
	}

	/**
	 * 初始化实例
	 */
	private void init() {
		this.header = new ProtocolHeader();
		this.mainModule = new MainModule();
		this.measureModuleList = new ArrayList<MeasureModule>();
		this.electriModuleList = new ArrayList<ElectricModule>();
		this.tail = new ProtocolTail();

		// 1.初始化协议头
		header.setStartCode(new byte[] { 0x55, 0x41 });
		header.setCmd((byte) 0x99);
		header.setBandID("01010000");
		header.setBranchID("00000000");
		header.setMAC("01B2C3D4E5F6"); // 接入服务不做MAC逻辑判断，可以固定一个

		// 2.初始化主机模块
		mainModule.setInputIO((byte) 0x00);
		mainModule.setRelayStatus((byte) 0x00);
		// mainModule.setAlarmStatus((byte) 0x00);// 温度上限报警
		mainModule.setTemperature(825); // 带1位小数，*10倍数, 32.5℃
		mainModule.setCmd((byte) 0x00);
		mainModule.setInputStatus((byte) 0x00);

		// 3.初始化测控模块
		for (int i = 0; i < measureModuleCount; i++) {
			MeasureModule model = new MeasureModule();
			model.setRelayStatus((byte) 0x00);
			// model.setCurrentAlarmStatus((byte) 0x01);// A相电流预警
			// model.setCurrentWarningStatus((byte) 0x01);//A相过流报警
			// model.setTemperatureStatus((byte) 0x01);
			model.setModuleStatus((byte) 0x01); // 三相
			model.setLeakageStatus((byte) 0x00);
			model.setReserved1((byte) 0x00);
			model.setChlCurrent(new int[] { 20, 30, 40 }); // 分别0.2,0.3,0.4A,2位小数，*100倍数
			model.setChlVoltage(new int[] { 0, 0, 0 });
			model.setChlTemperature(new int[] { 850, 850, 850 }); // -50为0，设置35℃，1位小数，*10倍数。
			model.setChlLeakage(10); // 10mA
			model.setReserved2(new byte[] { (byte) 0x00, (byte) 0x00,
					(byte) 0x00 });
			model.setCmd((byte) 0x00);

			measureModuleList.add(model);
		}

		// 4.初始化电量模块
		for (int i = 0; i < electriModuleCount; i++) {
			ElectricModule model = new ElectricModule();
			model.setVoltageStatus((byte) 0x00);
			model.setCurrentAlarmStatus((byte) 0x00);
			model.setCurrentWarningStatus((byte) 0x00);
			model.setTemperatureStatus((byte) 0x00);
			model.setModuleStatus((byte) 0x01);
			model.setPhaseLossStatus((byte) 0x00);
			model.setChlLeakage(20); // 20mA
			model.setChlCurrent(new int[] { 20, 30, 40 }); // 分别0.2,0.3,0.4A,2位小数，*100倍数
			model.setChlVoltage(new int[] { 2300, 2300, 2300 }); // 230V，1位小数，*10倍
			model.setChlTemperture(new int[] { 850, 850, 850 }); // -50为0，设置35℃，1位小数，*10倍数。
			model.setEnergyA(1000); // 电量累加值 ,1位小数,*10倍数
			model.setCmd((byte) 0x64); // 固定，不可修改
			model.setEnergyB(1000); // 电量累加值 ,1位小数,*10倍数
			model.setEnergyC(1000); // 电量累加值 ,1位小数,*10倍数
			model.setEnergyTotal(3000);
			model.setPowerA(100); // 有功功率，单位W，不带小数
			model.setPowerB(100);
			model.setPowerC(100);
			model.setPowerTotal(300);
			model.setReactivePowerA(0);
			model.setReactivePowerB(0);
			model.setReactivePowerC(0);
			model.setReactivePowerTotal(0);
			model.setParentPowerA(0);
			model.setParentPowerB(0);
			model.setParentPowerC(0);
			model.setParentPowerTotal(0);
			model.setPowerFactorA(100); // 功率因数, 2位小数，*100倍数
			model.setPowerFactorB(100);
			model.setPowerFactorC(100);
			model.setPowerFactorTotal(100);
			model.setHarmonicVoltageA(0);
			model.setHarmonicVoltageB(0);
			model.setHarmonicVoltageC(0);
			model.setHarmonicCurrentA(0);
			model.setHarmonicCurrentB(0);
			model.setHarmonicCurrentC(0);

			electriModuleList.add(model);
		}

		// 5.初始化协议尾
		tail.setReserved((byte) 0x00);
		tail.setEndCode(new byte[] { 0x56, 0x41 });
	}

	/**
	 * 构建数据包
	 * 
	 * @param deviceID
	 *            设备ID，长度8位，大写英文和数字组成
	 * @param mainModule_AlarmStatus
	 *            温度上限报警
	 * @return
	 * @throws Exception
	 */
	public byte[] CreatePacket(String deviceID) throws Exception {

		if (deviceID == "")
			throw new Exception("deviceID is empty");

		// 数据包长度
		int bodyLen = 9 + measureModuleCount * 31 + electriModuleCount * 131;
		int packetLen = 23 + bodyLen + 4;

		// 缓存
		ByteBuffer buffer = ByteBuffer.allocate(packetLen);

		// 1. 协议头部
		header.setDeviceID(deviceID);
		header.setPackageLen(packetLen);
		buffer.put(header.toArray());

		// 2.主机模块
		mainModule.setDataLen(bodyLen);
		// mainModule.setAlarmStatus(mainModule_AlarmStatus);
		buffer.put(mainModule.toArray());

		// 3.测控模块列表
		for (int i = 0; i < measureModuleCount; i++) {
			// MeasureModule

			buffer.put(measureModuleList.get(i).toArray());
		}

		// 4.电量模块列表
		for (int i = 0; i < electriModuleCount; i++) {
			buffer.put(electriModuleList.get(i).toArray());
		}

		// 5.协议尾部
		buffer.put(tail.toArray());

		// 6.加密
		byte[] sendBuffer = new byte[packetLen];
		byte[] encryBuffer = EncryptionHelper.EncryptionPacket(buffer.array(),
				buffer.capacity() - 1);
		System.arraycopy(encryBuffer, 0, sendBuffer, 0, encryBuffer.length);

		// 7.加和校验
		byte chkSum = CheckSumHelper.CheckSum(sendBuffer, sendBuffer.length);
		sendBuffer[packetLen - 1] = chkSum;

		return sendBuffer;
	}
}
