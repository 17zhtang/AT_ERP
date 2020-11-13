package com.haoyun.automationtesting.interfaces.YBdevices.model;

import java.io.Serializable;
import java.nio.ByteBuffer;

import com.haoyun.automationtesting.interfaces.YBdevices.utils.ConverterHelper;

public class ProtocolHeader implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9087561759199605131L;

	/**
	 * 报文开始
	 */
	private byte[] startCode;

	public byte[] getStartCode() {
		return this.startCode;
	}

	public void setStartCode(byte[] value) {
		this.startCode = value;
	}

	/**
	 * 命令码
	 */
	public byte cmd;

	public byte getCmd() {
		return this.cmd;
	}

	public void setCmd(byte value) {
	   this.cmd = value;
	}

	/**
	 * 报文长度低字节
	 */
	private int packageLen;

	public int getPackageLen() {
		return this.packageLen;
	}

	public void setPackageLen(int value) {
		this.packageLen = value;
	}

	/**
	 * 设备ID，限定8位长度
	 */
	private String deviceID;

	public String getDeviceID() {
		return this.deviceID;
	}

	public void setDeviceID(String value) {
		this.deviceID = value;
	}

	/**
	 * 银行网点ID,限定8位长度
	 */
	private String bandID;

	public String getBandID() {
		return this.bandID;
	}

	public void setBandID(String value) {
		this.bandID = value;
	}

	/**
	 * 支行ID,限定8位长度
	 */
	private String branchID;

	public String getBranchID() {
		return this.branchID;
	}

	public void setBranchID(String value) {
		this.branchID = value;
	}

	/**
	 * 物理MAC
	 */
	private String mac;

	public String getMAC() {
		return this.mac;
	}

	public void setMAC(String value) {
		this.mac = value;
	}

	public byte[] toArray() {

		ByteBuffer buffer = ByteBuffer.allocate(23);
		buffer.put(this.startCode);
		buffer.put(this.cmd);
		buffer.put((byte) (this.packageLen & 0xFF)); // 低字节
		buffer.put(ConverterHelper.strToHex(this.deviceID));
		buffer.put(ConverterHelper.strToHex(this.bandID));
		buffer.put(ConverterHelper.strToHex(this.branchID));
		buffer.put(ConverterHelper.strToHex(this.mac));
		buffer.put((byte) ((this.packageLen >> 8) & 0xFF));

		return buffer.array();
	}

}
