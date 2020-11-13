package com.haoyun.automationtesting.interfaces.MDdevices;

import java.io.Serializable;
import java.nio.ByteBuffer;

import com.haoyun.automationtesting.interfaces.MDdevices.CRC32.CLibrary;
import com.haoyun.automationtesting.interfaces.YBdevices.utils.ConverterHelper;
/**
 * init1：注册事件
 * @param DeviceID 设备ID，限定12位长度
 * @return
 */
public class Model_Register implements Serializable {

	private static final long serialVersionUID = -9087561759199605131L;

	/**
	 * init1：注册事件
	 * @param DeviceID 设备ID，限定12位长度
	 * @return
	 */
	public static byte[] toArray(String DeviceID) {
		int bufferlength = 26;// 报文长度
		ByteBuffer buffer = ByteBuffer.allocate(bufferlength);
		buffer.put((byte)0xf1);//PVER		协议版本
		buffer.put((byte)0xb0);//CMD		消息ID
		buffer.put((byte)0x00);//CMD VER		附加码
		buffer.put((byte)0x00);//CMD VER2		附加码
		buffer.put(new byte[] {0x00, 0x06});//Len		数据长度
		buffer.put(new byte[] {0x00, 0x01});//CMD No.		消息序列号
		buffer.put(ConverterHelper.strToHex(DeviceID));//MAC		通讯模块物理地址
		buffer.put((byte)0x00);//TIMEZONE		时区
		buffer.put((byte)0x01);//HOST ID		通讯模块ID
		buffer.put((byte)0x00);//NNO*		节点号
		buffer.put((byte)0x8a);//TYPE*		产品类型
		buffer.put(new byte[] {0x54,0x33});//Model*		设备型号
		buffer.put(new byte[] {0x10,0x06});//VER*		版本号
		
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
