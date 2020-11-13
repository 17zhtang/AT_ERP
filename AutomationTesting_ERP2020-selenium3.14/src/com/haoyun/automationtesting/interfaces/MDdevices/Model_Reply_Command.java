package com.haoyun.automationtesting.interfaces.MDdevices;

import java.io.Serializable;
import java.nio.ByteBuffer;

import com.haoyun.automationtesting.interfaces.MDdevices.CRC32.CLibrary;
import com.haoyun.automationtesting.interfaces.YBdevices.utils.ConverterHelper;
/**
 *  init2：reply_command 
 */
public class Model_Reply_Command implements Serializable {


	private static final long serialVersionUID = -9087561759199605131L;

	/**
	 *  init2：reply_command 
	 */
	public static byte[] toArray(String DeviceID) {
		ByteBuffer buffer = ByteBuffer.allocate(20);
		buffer.put((byte)0xf1);//
		buffer.put((byte)0xd3);//
		buffer.put((byte)0xd1);//
		buffer.put((byte)0x00);//
		buffer.put((byte)0x00);//
		buffer.put((byte)0x00);//
		buffer.put((byte)0x00);//
		buffer.put((byte)0x01);//
		buffer.put(ConverterHelper.strToHex(DeviceID));//MAC		通讯模块物理地址
		buffer.put((byte)0x00);//
		buffer.put((byte)0x01);//	
		long crc=CLibrary.INSTANCE.CalculateCRC32(buffer.array(), 20);
		//log.logInfo("CRC="+crc);
		//String c=Long.toHexString(crc);
		//log.logInfo("转16进制:"+c);
		byte[] crc32 =ConverterHelper.reverse32(crc);
		// 添加到buffer
		buffer.put(crc32);
		return buffer.array();
	}

}
