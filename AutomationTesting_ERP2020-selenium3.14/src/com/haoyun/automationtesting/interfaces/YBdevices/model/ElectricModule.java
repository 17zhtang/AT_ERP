package com.haoyun.automationtesting.interfaces.YBdevices.model;

import java.io.Serializable;
import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.haoyun.automationtesting.interfaces.YBdevices.utils.ConverterHelper;
import com.haoyun.automationtesting.page.PM;

/**
 * 电量模块, 131字节
 * 
 * @author Vicent
 *
 */
public class ElectricModule implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2268758313628062803L;

	/**
	 * 继电器状态
	 */
	private byte voltageStatus;

	public byte getVoltageStatus() {
		return this.voltageStatus;
	}

	public void setVoltageStatus(byte value) {
		this.voltageStatus = value;
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
	 * 电流超上限状态
	 */
	private byte currentWarningStatus;

	public byte getCurrentWarningStatus() {
		return this.currentWarningStatus;
	}

	public void setCurrentWarningStatus(byte value) {
		this.currentWarningStatus = value;
	}

	/**
	 * 温度超限状态
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
	 * 缺相状态
	 */
	private byte phaseLossStatus;

	public byte getPhaseLostStatus() {
		return this.phaseLossStatus;
	}

	public void setPhaseLossStatus(byte value) {
		this.phaseLossStatus = value;
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

	public int[] getChlTemperture() {
		return this.chlTemperture;
	}

	public void setChlTemperture(int[] value) {
		this.chlTemperture = value;
	}

	/**
	 * A相电量，4个字节
	 */
	private int energyA;

	public int getEnergyA() {
		return this.energyA;
	}

	public void setEnergyA(int value) {
		this.energyA = value;
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
	 * B相电量，4个字节
	 */
	private int energyB;

	public int getEnergyB() {
		return this.energyB;
	}

	public void setEnergyB(int value) {
		this.energyB = value;
	}

	/**
	 * C相电量，4个字节
	 */
	private int energyC;

	public int getEnergyC() {
		return this.energyC;
	}

	public void setEnergyC(int value) {
		this.energyC = value;
	}

	/**
	 * 合相电量，4个字节
	 */
	private int energyTotal;

	public int getEnergyTotal() {
		return this.energyTotal;
	}

	public void setEnergyTotal(int value) {
		this.energyTotal = value;
	}

	/**
	 * A相有功功率，4个字节
	 */
	private int powerA;

	public int getPowerA() {
		return this.powerA;
	}

	public void setPowerA(int value) {
		this.powerA = value;
	}

	/**
	 * B相有功功率，4个字节
	 */
	private int powerB;

	public int getPowerB() {
		return this.powerB;
	}

	public void setPowerB(int value) {
		this.powerB = value;
	}

	/**
	 * C相有功功率，4个字节
	 */
	private int powerC;

	public int getPowerC() {
		return this.powerC;
	}

	public void setPowerC(int value) {
		this.powerC = value;
	}

	/**
	 * 合相有功功率，4个字节
	 */
	private int powerTotal;

	private int getPowerTotal() {
		return this.powerTotal;
	}

	public void setPowerTotal(int value) {
		this.powerTotal = value;
	}

	/**
	 * A相无功功率，4个字节
	 */
	private int reactivePowerA;

	public int getReactivePowerA() {
		return this.reactivePowerA;
	}

	public void setReactivePowerA(int value) {
		this.reactivePowerA = value;
	}

	/**
	 * B相无功功率，4个字节
	 */
	private int reactivePowerB;

	public int getReactivePowerB() {
		return this.reactivePowerB;
	}

	public void setReactivePowerB(int value) {
		this.reactivePowerB = value;
	}

	/**
	 * C相无功功率，4个字节
	 */
	private int reactivePowerC;

	public int getReactivePowerC() {
		return this.reactivePowerC;
	}

	public void setReactivePowerC(int value) {
		this.reactivePowerC = value;
	}

	/**
	 * 合相无功功率，4个字节
	 */
	private int reactivePowerTotal;

	public int getReactivePowerTotal() {
		return this.reactivePowerTotal;
	}

	public void setReactivePowerTotal(int value) {
		this.reactivePowerTotal = value;
	}

	/**
	 * A相视在功率，4个字节
	 */
	private int parentPowerA;

	public int getParentPowerA() {
		return this.parentPowerA;
	}

	public void setParentPowerA(int value) {
		this.parentPowerA = value;
	}

	/**
	 * B相视在功率，4个字节
	 */
	private int parentPowerB;

	public int getParentPowerB() {
		return this.parentPowerB;
	}

	public void setParentPowerB(int value) {
		this.parentPowerB = value;
	}

	/**
	 * C相视在功率，4个字节
	 */
	private int parentPowerC;

	public int getParentPowerC() {
		return this.parentPowerC;
	}

	public void setParentPowerC(int value) {
		this.parentPowerC = value;
	}

	/**
	 * 合相视在功率，4个字节
	 */
	private int parentPowerTotal;

	public int getParentPowerTotal() {
		return this.parentPowerTotal;
	}

	public void setParentPowerTotal(int value) {
		this.parentPowerTotal = value;
	}

	/**
	 * A相功率因数，4个字节
	 */
	private int powerFactorA;

	public int getPowerFactorA() {
		return this.powerFactorA;
	}

	public void setPowerFactorA(int value) {
		this.powerFactorA = value;
	}

	/**
	 * B相功率因数，4个字节
	 */
	private int powerFactorB;

	public int getPowerFactorB() {
		return this.powerFactorB;
	}

	public void setPowerFactorB(int value) {
		this.powerFactorB = value;
	}

	/**
	 * C相功率因数，4个字节
	 */
	private int powerFactorC;

	public int getPowerFactorC() {
		return this.powerFactorC;
	}

	public void setPowerFactorC(int value) {
		this.powerFactorC = value;
	}

	/**
	 * 合相功率因数，4个字节
	 */
	private int powerFactorTotal;

	public int getPowerFactorTotal() {
		return this.powerFactorTotal;
	}

	public void setPowerFactorTotal(int value) {
		this.powerFactorTotal = value;
	}

	/**
	 * A相谐波电压，4个字节
	 */
	private int harmonicVoltageA;

	public int getHarmonicVoltageA() {
		return this.harmonicVoltageA;
	}

	public void setHarmonicVoltageA(int value) {
		this.harmonicVoltageA = value;
	}

	/**
	 * B相谐波电压，4个字节
	 */
	private int harmonicVoltageB;

	public int getHarmonicVoltageB() {
		return this.harmonicVoltageB;
	}

	public void setHarmonicVoltageB(int value) {
		this.harmonicVoltageB = value;
	}

	/**
	 * C相谐波电压，4个字节
	 */
	private int harmonicVoltageC;

	public int getHarmonicVoltageC() {
		return this.harmonicVoltageC;
	}

	public void setHarmonicVoltageC(int value) {
		this.harmonicVoltageC = value;
	}

	/**
	 * A相谐波电流，4个字节
	 */
	private int harmonicCurrentA;

	public int getHarmonicCurrentA() {
		return this.harmonicCurrentA;
	}

	public void setHarmonicCurrentA(int value) {
		this.harmonicCurrentA = value;
	}

	/**
	 * B相谐波电流，4个字节
	 */
	private int harmonicCurrentB;

	public int getHarmonicCurrentB() {
		return this.harmonicCurrentB;
	}

	public void setHarmonicCurrentB(int value) {
		this.harmonicCurrentB = value;
	}

	/**
	 * C相谐波电流，4个字节
	 */
	private int harmonicCurrentC;

	public int getHarmonicCurrentC() {
		return this.harmonicCurrentC;
	}

	public void setHarmonicCurrentC(int value) {
		this.harmonicCurrentC = value;
	}

	public ElectricModule() {
		this.voltageStatus = 0;
		this.currentAlarmStatus = 0;
		this.currentWarningStatus = 0;
		this.temperatureStatus = 0;
		this.moduleStatus = 0;
		this.phaseLossStatus = 0;
		this.chlLeakage = 0;
		this.chlCurrent = new int[3];
		this.chlVoltage = new int[3];
		this.chlTemperture = new int[3];
		this.energyA = 0;
		this.cmd = 0;
		this.energyB = 0;
		this.energyC = 0;
		this.energyTotal = 0;
		this.powerA = 0;
		this.powerB = 0;
		this.powerC = 0;
		this.powerTotal = 0;
		this.reactivePowerA = 0;
		this.reactivePowerB = 0;
		this.reactivePowerC = 0;
		this.reactivePowerTotal = 0;
		this.parentPowerA = 0;
		this.parentPowerB = 0;
		this.parentPowerC = 0;
		this.parentPowerTotal = 0;
		this.parentPowerA = 0;
		this.parentPowerB = 0;
		this.parentPowerC = 0;
		this.parentPowerTotal = 0;
		this.powerFactorA = 0;
		this.powerFactorB = 0;
		this.powerFactorC = 0;
		this.powerFactorTotal = 0;
		this.harmonicVoltageA = 0;
		this.harmonicVoltageB = 0;
		this.harmonicVoltageC = 0;
		this.harmonicCurrentA = 0;
		this.harmonicCurrentB = 0;
		this.harmonicCurrentC = 0;
	}

	public byte[] toArray() {

		ByteBuffer buffer = ByteBuffer.allocate(131);
		buffer.put(PM.YBDeviceVoltageStatus);// A相过压报警//PM.YBDeviceVoltageStatus
		buffer.put(currentAlarmStatus);// (byte) 0x00currentAlarmStatus
		buffer.put(currentWarningStatus);// currentWarningStatus
		buffer.put(temperatureStatus);// temperatureStatus
		buffer.put(PM.moduleStatus);// moduleStatus//RS485通信异常报警
		buffer.put(PM.YBDevicephaseLossStatus);// A相输入缺相//PM.YBDevicephaseLossStatus
		buffer.put(ConverterHelper.reverse16((short) chlLeakage));// chlLeakage

		buffer.put(ConverterHelper.reverse16((short) chlCurrent[0]));
		buffer.put(ConverterHelper.reverse16((short) chlCurrent[1]));
		buffer.put(ConverterHelper.reverse16((short) chlCurrent[2]));

		buffer.put(ConverterHelper.reverse16((short) chlVoltage[0]));
		buffer.put(ConverterHelper.reverse16((short) chlVoltage[1]));
		buffer.put(ConverterHelper.reverse16((short) chlVoltage[2]));

		buffer.put(ConverterHelper.reverse16((short) chlTemperture[0]));
		buffer.put(ConverterHelper.reverse16((short) chlTemperture[1]));
		buffer.put(ConverterHelper.reverse16((short) chlTemperture[2]));
		int dl = Integer
				.valueOf(new SimpleDateFormat("dHH").format(new Date()));// 递增整数，天，小时递增

		buffer.put(ConverterHelper.reverse32(dl));// energyA//A相电量，4个字节
		buffer.put(cmd);
		buffer.put(ConverterHelper.reverse32(dl));// energyB
		buffer.put(ConverterHelper.reverse32(dl));// energyC
		buffer.put(ConverterHelper.reverse32(dl * 3));// energyTotal

		buffer.put(ConverterHelper.reverse32(powerA));
		buffer.put(ConverterHelper.reverse32(powerB));
		buffer.put(ConverterHelper.reverse32(powerC));
		buffer.put(ConverterHelper.reverse32(powerTotal));

		buffer.put(ConverterHelper.reverse32(reactivePowerA));
		buffer.put(ConverterHelper.reverse32(reactivePowerB));
		buffer.put(ConverterHelper.reverse32(reactivePowerC));
		buffer.put(ConverterHelper.reverse32(reactivePowerTotal));

		buffer.put(ConverterHelper.reverse32(parentPowerA));
		buffer.put(ConverterHelper.reverse32(parentPowerB));
		buffer.put(ConverterHelper.reverse32(parentPowerC));
		buffer.put(ConverterHelper.reverse32(parentPowerTotal));

		buffer.put(ConverterHelper.reverse32(powerFactorA));
		buffer.put(ConverterHelper.reverse32(powerFactorB));
		buffer.put(ConverterHelper.reverse32(powerFactorC));
		buffer.put(ConverterHelper.reverse32(powerFactorTotal));

		buffer.put(ConverterHelper.reverse32(harmonicVoltageA));
		buffer.put(ConverterHelper.reverse32(harmonicVoltageB));
		buffer.put(ConverterHelper.reverse32(harmonicVoltageC));

		buffer.put(ConverterHelper.reverse32(harmonicCurrentA));
		buffer.put(ConverterHelper.reverse32(harmonicCurrentB));
		buffer.put(ConverterHelper.reverse32(harmonicCurrentC));

		return buffer.array();
	}

}