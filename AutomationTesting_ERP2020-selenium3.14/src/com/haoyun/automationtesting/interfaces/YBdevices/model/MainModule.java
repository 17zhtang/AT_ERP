package com.haoyun.automationtesting.interfaces.YBdevices.model;

import java.io.Serializable;
import java.nio.ByteBuffer;

import com.haoyun.automationtesting.interfaces.YBdevices.utils.ConverterHelper;
import com.haoyun.automationtesting.page.PM;

/**
 * 主机模块，9个字节长度
 * 
 * @author Vicent
 *
 */
public class MainModule implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2244384321739377274L;

	/**
	 * 数据体长度，2个字节
	 */
	private int dataLen;

	public int getDataLen() {
		return this.dataLen;
	}

	public void setDataLen(int value) {
		this.dataLen = value;
	}

	/**
	 * 无源输入(开关量)
	 */
	private byte inputIO;

	public byte getInpuIO() {
		return this.inputIO;
	}

	public void setInputIO(byte value) {
		this.inputIO = value;
	}

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
	 * 报警状态位
	 */
	private byte alarmStatus;

	public byte getAlarmStatus() {
		return this.alarmStatus;
	}

	public void setAlarmStatus(byte value) {
		this.alarmStatus = value;
	}

	/**
	 * 温度值
	 */
	private int temperature;

	public int getTemperature() {
		return this.temperature;
	}

	public void setTemperature(int value) {
		this.temperature = value;
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

	/**
	 * 开关量输入
	 */
	private byte inputStatus;

	public byte getInputStatus() {
		return this.inputStatus;
	}

	public void setInputStatus(byte value) {
		this.inputStatus = value;
	}

	public MainModule() {
		this.dataLen = 0;
		// this.inputIO = 0;
		this.relayStatus = 0;
		// this.alarmStatus = "0";
		this.temperature = 825;// 825
		this.cmd = 0;
		this.inputStatus = 0;
	}

	public byte[] toArray() {
		ByteBuffer buffer = ByteBuffer.allocate(9);
		buffer.put(ConverterHelper.reverse16((short) this.getDataLen()));
		buffer.put(PM.YBDeviceinputIO);// 无源输入(开关量)-包括水侵信号，门禁信号
		buffer.put(this.getRelayStatus());

		// buffer.put(this.getAlarmStatus());
		buffer.put(PM.YBDevicemainModule_AlarmStatus);// 温度上限报警
		buffer.put(ConverterHelper.reverse16((short) this.getTemperature()));
		buffer.put(this.getCmd());
		buffer.put(getInputStatus());

		return buffer.array();
	}

}
