package com.haoyun.automationtesting.interfaces.YBdevices.model;

import java.io.Serializable;
import java.nio.ByteBuffer;

public class ProtocolTail implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2034306854525310511L;

	/**
	 * 预留位
	 */
	private byte reserved;

	public byte getReserved() {
		return this.reserved;
	}

	public void setReserved(byte value) {
		this.reserved = value;
	}

	/**
	 * 报文结束
	 */
	private byte[] endCode;

	public byte[] getEndCode() {
		return this.endCode;
	}

	public void setEndCode(byte[] value) {
		this.endCode = value;
	}

	/**
	 * 加和校验位
	 */
	private byte checkSum;

	public byte getCheckSum() {
		return this.checkSum;
	}

	public void setCheckSum(byte value) {
		this.checkSum = value;
	}

	public ProtocolTail() {
		this.reserved = 0;
		this.endCode = new byte[] { 0x56, 0x40 };
		this.checkSum = 0;
	}

	public byte[] toArray() {

		ByteBuffer buffer = ByteBuffer.allocate(4);
		buffer.put(reserved);
		buffer.put(endCode);
		buffer.put(checkSum);

		return buffer.array();
	}

}
