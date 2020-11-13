package com.haoyun.automationtesting.interfaces.MDdevices;

import java.io.Serializable;
import java.nio.ByteBuffer;

import com.haoyun.automationtesting.interfaces.MDdevices.CRC32.CLibrary;
import com.haoyun.automationtesting.interfaces.YBdevices.utils.ConverterHelper;

public class Model_PushSwConfig implements Serializable {

	/**
	 * init4：PushSwConfig
	 */
	private static final long serialVersionUID = -9087561759199605131L;

	public static byte[] toArray(String DeviceID, int i) {
		int bufferlength = 16 + 80*i + 4;// 报文长度100
		// 0000 f1 b4 01 01 00 50 00 01 22 22 22 22 22 22 00 01
		// 0010 01 80 20 07 00 aa 00 00 00 00 00 00 00 01 01 04
		// 0020 00 64 ,00 00 03 e8 00 00 1b 80 ,00 00 00 00 03 84
		// 0030 00 00 12 c0, 00 00 00 00 00 00 00 00 00 00 00 00
		// 0040 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00
		// 0050 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00
		// 0060 61 4b e8 02
		ByteBuffer buffer = ByteBuffer.allocate(bufferlength);
		// header
		buffer.put((byte) 0xf1);//
		buffer.put((byte) 0xb4);// 消息类型
		buffer.put((byte) 0x01);//
		buffer.put((byte) 0x01);//
		buffer.put(new byte[] { 0x00, 0x50 });// 事件序列号
		buffer.put(new byte[] { 0x00, 0x01 });// CMD No. 消息序列号
		buffer.put(ConverterHelper.strToHex(DeviceID));// MAC 通讯模块物理地址
		buffer.put((byte) 0x00);// TIMEZONE 时区
		buffer.put((byte) 0x01);// HOST ID 通讯模块ID
		// data
		for (int j = 0; j < i; j++) {

			
			buffer.put((byte) i);
			buffer.put((byte) 0x80);
			buffer.put(new byte[] { 0x20, 0x07, 0x00 });//
			buffer.put((byte) 0xaa);
			buffer.put(new byte[] { 0x00, 0x00, 0x00, 0x00 });// SER* 序列号
			buffer.put(new byte[] { 0x00, 0x00 });//
			buffer.put(new byte[] { 0x00, 0x01 });// CTRL 控制位
			buffer.put(new byte[] { 0x01, 0x04 });// VOLH 电压上限
			buffer.put(new byte[] { 0x00, 0x64 });// VOLL 电压下限
			buffer.put(new byte[] { 0x00, 0x00 });//
			buffer.put((byte) 0x03);
			buffer.put((byte) 0xe8);// LKIH 漏电流上限
			buffer.put(new byte[] { 0x00, 0x00 });//
			buffer.put((byte) 0x1b);
			buffer.put((byte) 0x80);// PWRH 功率上限
			buffer.put(new byte[] { 0x00, 0x00 });//
			buffer.put(new byte[] { 0x00, 0x00 });//
			buffer.put((byte) 0x03);
			buffer.put((byte) 0x84);// //TMPH 温度上限
			buffer.put(new byte[] { 0x00, 0x00 });//
			buffer.put((byte) 0x12);
			buffer.put((byte) 0xc0);// CURH 电流上限

			buffer.put(new byte[] { 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
					0x00, 0x00, 0x00, 0x00, 0x00 });
			buffer.put(new byte[] { 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
					0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00 });
			buffer.put(new byte[] { 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
					0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00 });
		}
		long crc = CLibrary.INSTANCE.CalculateCRC32(buffer.array(),
				bufferlength);
		//log.logInfo("CRC=" + crc);
		byte[] crc32 = ConverterHelper.reverse32(crc);
		// 添加到buffer
		buffer.put(crc32);
		return buffer.array();
	}
}
