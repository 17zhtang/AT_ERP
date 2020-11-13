package com.haoyun.automationtesting.interfaces.MDdevices;

import java.io.Serializable;
import java.nio.ByteBuffer;

import com.haoyun.automationtesting.interfaces.MDdevices.CRC32.CLibrary;
import com.haoyun.automationtesting.interfaces.YBdevices.utils.ConverterHelper;

public class Model_StatusReport implements Serializable {

	/**
	 *  REGITSTER 消息
	 */
	private static final long serialVersionUID = -9087561759199605131L;

	
	public static byte[] toArray(String DeviceID) {
//		0000   f1 a0 01 01 00 1a 00 01 22 22 22 22 22 22 00 01
//		0010   5e cb 61 e4 01 80 00 dc 00 00 00 00 00 00 00 00
//		0020   00 00 00 00 00 00 00 00 00 00 47 bd f1 40
		int bufferlength = 46;// 报文长度
		ByteBuffer buffer = ByteBuffer.allocate(bufferlength);
		buffer.put((byte)0xf1);//PVER		协议版本
		buffer.put((byte)0xa0);//CMD		消息ID
		buffer.put((byte)0x01);//
		buffer.put((byte)0x01);//
		buffer.put((byte)0x00);//
		buffer.put((byte)0x1a);//
		buffer.put((byte)0x00);//
		buffer.put((byte)0x01);//
		buffer.put(ConverterHelper.strToHex(DeviceID));//MAC		通讯模块物理地址
		buffer.put((byte)0x00);//
		buffer.put((byte)0x01);//
		
		long utc = (System.currentTimeMillis()+(8*60*60*1000)) / 1000;			
		//int buffer1 = Integer.parseUnsignedInt(String.valueOf(utc));
		byte[] buffer1 = ConverterHelper.reverse32((int) utc);
		for (byte b : buffer1) {//TIME		时间戳
			//System.out.format("%X", b);
			buffer.put(b);
		}
		
		buffer.put((byte)0x01);//
		buffer.put((byte)0x80);//
		buffer.put((byte)0x00);//
		buffer.put((byte)0xdc);//
		buffer.put(new byte[] {0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00});//

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
