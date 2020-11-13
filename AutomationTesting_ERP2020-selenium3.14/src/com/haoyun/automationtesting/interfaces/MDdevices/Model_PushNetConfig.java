package com.haoyun.automationtesting.interfaces.MDdevices;

import java.io.Serializable;
import java.nio.ByteBuffer;

import com.haoyun.automationtesting.interfaces.MDdevices.CRC32.CLibrary;
import com.haoyun.automationtesting.interfaces.YBdevices.utils.ConverterHelper;

/**
 * init3：Model_PushNetConfig
 */
public class Model_PushNetConfig implements Serializable {

	private static final long serialVersionUID = -9087561759199605131L;

	/**
	 * init3：Model_PushNetConfig
	 */
	public static byte[] toArray(String DeviceID) {
		int bufferlength = 144;// 报文长度
		// 0000 f1 b2 00 00 00 7c 00 01 22 22 22 22 22 22 00 01
		// 0010 00 8a 54 33 10 06 22 22 22 22 22 22 00 0f 00 00
		// 0020 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00
		// 0030 00 00 00 00 00 05 27 11 00 00 00 00 00 00 00 00
		// 0040 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00
		// 0050 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00
		// 0060 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00
		// 0070 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00
		// 0080 00 00 00 00 00 00 00 00 00 00 00 00 e9 91 05 8c
		ByteBuffer buffer = ByteBuffer.allocate(bufferlength);
		// header
		buffer.put((byte) 0xf1);//
		buffer.put((byte) 0xb2);// 消息类型
		buffer.put((byte) 0x00);// E1--实时事件
		buffer.put((byte) 0x00);// 事件数量
		buffer.put(new byte[] { 0x00, 0x7c });// 事件序列号
		buffer.put(new byte[] { 0x00, 0x01 });// CMD No. 消息序列号
		buffer.put(ConverterHelper.strToHex(DeviceID));// MAC 通讯模块物理地址
		buffer.put((byte) 0x00);// TIMEZONE 时区
		buffer.put((byte) 0x01);// HOST ID 通讯模块ID
		// data
		buffer.put((byte) 0x00);
		buffer.put((byte) 0x8a);
		buffer.put(new byte[] { 0x54,0x33,0x10,0x06});//
		buffer.put(ConverterHelper.strToHex(DeviceID));// MAC 通讯模块物理地址
		buffer.put((byte) 0x00);
		buffer.put((byte) 0x0f);// 
		buffer.put((byte) 0x00);
		buffer.put((byte) 0x00);
		buffer.put(new byte[] { 0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00});
		buffer.put((byte) 0x05);
		buffer.put((byte) 0x27);
		buffer.put((byte) 0x11);
		buffer.put(new byte[] { 0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00});
		buffer.put(new byte[] { 0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00});
		buffer.put(new byte[] { 0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00});
		buffer.put(new byte[] { 0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00});
		buffer.put(new byte[] { 0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00});
		buffer.put(new byte[] { 0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00});
		
		long crc=CLibrary.INSTANCE.CalculateCRC32(buffer.array(), bufferlength);
		//log.logInfo("CRC="+crc);
		//String c=Long.toHexString(crc);
		//log.logInfo("转16进制:"+c);
		byte[] crc32 =ConverterHelper.reverse32(crc);
		// 添加到buffer
		buffer.put(crc32);
		return buffer.array();
	}
}
