package com.haoyun.automationtesting.interfaces.YBdevices.model;

import java.io.Serializable;
import java.nio.ByteBuffer;

import com.haoyun.automationtesting.interfaces.YBdevices.utils.ConverterHelper;
import com.haoyun.automationtesting.page.PM;

/**
 * 测控模块，31字节
 * 
 * @author Vicent
 *
 */
public class MeasureModule implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2861864227948728393L;

	/**
	 * 继电器状态
	 */
	private byte relayStatus;

	public byte getRelayStatus() {
		return this.relayStatus;
	}

	public void setRelayStatus(byte value) {
		this.relayStatus = value;
	}

	/**
	 * 电流过载状态
	 */
	private byte currentAlarmStatus;

	public byte getCurrentAlarmStatus() {
		return this.currentAlarmStatus;
	}

	public void setCurrentAlarmStatus(byte value) {
		this.currentAlarmStatus = value;
	}

	/**
	 * 电流超限状态
	 */
	private byte currentWarningStatus;

	public byte getCurrentWarningStatus() {
		return this.currentWarningStatus;
	}

	public void setCurrentWarningStatus(byte value) {
		this.currentWarningStatus = value;
	}

	/**
	 * 温度超时状态
	 */
	private byte temperatureStatus;

	public byte getTemperatureStatus() {
		return this.temperatureStatus;
	}

	public void setTemperatureStatus(byte value) {
		this.temperatureStatus = value;
	}

	/**
	 * 状态
	 */
	private byte moduleStatus;

	public byte getModuleStatus() {
		return this.moduleStatus;
	}

	public void setModuleStatus(byte value) {
		this.moduleStatus = value;
	}

	/**
	 * 剩余电流状态
	 */
	private byte leakageStatus;

	public byte getLeakageStatus() {
		return this.leakageStatus;
	}

	public void setLeakageStatus(byte value) {
		this.leakageStatus = value;
	}

	/**
	 * 预留
	 */
	public byte reserved1;

	public byte getReserved1() {
		return this.reserved1;
	}

	public void setReserved1(byte value) {
		this.reserved1 = value;
	}

	/**
	 * 3个通道电流,6个字节,2位小数(100倍数)
	 */
	private int[] chlCurrent;

	public int[] getChlCurrent() {
		return this.chlCurrent;
	}

	public void setChlCurrent(int[] value) {
		this.chlCurrent = value;
	}

	/**
	 * 3个通道电压,6个字节,2位小数(100倍数)
	 */
	private int[] chlVoltage;

	public int[] getChlVoltage() {
		return this.chlVoltage;
	}

	public void setChlVoltage(int[] value) {
		this.chlVoltage = value;
	}

	/**
	 * 3个通道温度,6个字节,从-50℃开始
	 */
	private int[] chlTemperture;

	public int[] getChlTemperature() {
		return this.chlTemperture;
	}

	public void setChlTemperature(int[] value) {
		this.chlTemperture = value;
	}

	/**
	 * 剩余电流值,2个字节，
	 */
	private int chlLeakage;

	public int getChlLeakage() {
		return this.chlLeakage;
	}

	public void setChlLeakage(int value) {
		this.chlLeakage = value;
	}

	/**
	 * 备用，3个字节
	 */
	private byte[] reserved2;

	public byte[] getReserved2() {
		return this.reserved2;
	}

	public void setReserved2(byte[] value) {
		this.reserved2 = value;
	}

	/**
	 * 模块功能码
	 */
	private byte cmd;

	public byte getCmd() {
		return this.cmd;
	}

	public void setCmd(byte value) {
		this.cmd = value;
	}

	public MeasureModule() {
		this.relayStatus = 0;
		// this.currentAlarmStatus = 0;
		this.currentWarningStatus = 0;
		this.temperatureStatus = 0;
		this.moduleStatus = 0;
		this.leakageStatus = 0;
		this.reserved1 = 0;
		this.chlCurrent = new int[3];
		this.chlVoltage = new int[3];
		this.chlTemperture = new int[3];
		this.chlLeakage = 0;
		this.reserved2 = new byte[3];
		this.cmd = 0;
	}

	public byte[] toArray() {

		ByteBuffer buffer = ByteBuffer.allocate(31);
		buffer.put(this.relayStatus);
		buffer.put(PM.YBDeviceCurrentAlarmStatus);// 电流预警
		buffer.put(PM.YBDeviceCurrentWarningStatus);// 过流预警
		buffer.put(PM.YBDeviceTemperatureStatus);// 温度上限预警
		buffer.put(this.moduleStatus);// (byte) 0x02
		buffer.put(PM.YBDeviceleakageStatus);// this.leakageStatus
		buffer.put(this.reserved1);
		buffer.put(ConverterHelper.reverse16((short) 11));// this.chlCurrent[0]
		buffer.put(ConverterHelper.reverse16((short) this.chlCurrent[1]));
		buffer.put(ConverterHelper.reverse16((short) this.chlCurrent[2]));

		buffer.put(ConverterHelper.reverse16((short) 22));// this.chlVoltage[0]
		buffer.put(ConverterHelper.reverse16((short) this.chlVoltage[1]));
		buffer.put(ConverterHelper.reverse16((short) this.chlVoltage[2]));

		buffer.put(ConverterHelper.reverse16((short) 33));// this.chlTemperture[0]
		buffer.put(ConverterHelper.reverse16((short) this.chlTemperture[1]));
		buffer.put(ConverterHelper.reverse16((short) this.chlTemperture[2]));

		buffer.put(ConverterHelper.reverse16((short) 1000));// this.chlLeakage
		buffer.put(this.reserved2);
		buffer.put(this.cmd);

		return buffer.array();
	}

}
