package com.haoyun.automationtesting.interfaces.MDdevices;

import java.io.Serializable;
import java.nio.ByteBuffer;

import com.haoyun.automationtesting.interfaces.MDdevices.CRC32.CLibrary;
import com.haoyun.automationtesting.interfaces.YBdevices.utils.ConverterHelper;

public class Model_push_event implements Serializable {
	private static final long serialVersionUID = -9087561759199605131L;

	public static byte[] toArray(String DeviceID, int CMD_VER2,byte[] ALM) {
		int bufferlength = 16 + 28*CMD_VER2 +4;// 报文长度

		ByteBuffer buffer = ByteBuffer.allocate(bufferlength);
		// header
		buffer.put((byte) 0xf1);//
		buffer.put((byte) 0xa4);// 消息类型
		buffer.put((byte) 0xe1);// E1--实时事件
		buffer.put((byte) CMD_VER2);// 事件数量
		buffer.put(new byte[] { 0x00, 0x1c });// 事件序列号
		buffer.put(new byte[] { 0x00, 0x01 });// CMD No. 消息序列号
		buffer.put(ConverterHelper.strToHex(DeviceID));// MAC 通讯模块物理地址
		buffer.put((byte) 0x00);// TIMEZONE 时区
		buffer.put((byte) 0x01);// HOST ID 通讯模块ID

		// data
		//字段,节点报警改变,三相节点 A相报警改变,三相节点B相报警改变,三相节点 C相报警改变	,时间改变
		//CODE  0x45  0x45  0x45  0x45  0x45
		//EVT_ID 0x34  0x35  0x36  0x37  0x39
		//EVT_LV 0 or 2  0 or 2  0 or 2  0 or 2  0
		//EVT_RE 0  0  0  0  NMM
		//EVT_EX  NNO  NNO  NNO  NNO  TIME
		//EVT_SP  NNO  NNO  NNO  NNO 
		//TIME  TIME  TIME  TIME  TIME 
		//APX  4 ALM_N 4 ALM_N 4  ALM_N 4  TIME N
		//     4 ALM_O 4 ALM_O 4  ALM_O 4  TIME O
		for (int i = 0; i < CMD_VER2; i++) {

			buffer.put(new byte[] { 0x00, 0x00 });// NNO* 节点号
			buffer.put((byte) 0x45);// CODE类型
			buffer.put((byte) 0x34);// EVT_ID 事件ID;三相节点 A相报警改变
			buffer.put((byte) 0x01);// EVT_LV 告警等级 0 表示记录，1 表示提醒，2 表示告警
			buffer.put((byte) 0x02);
			buffer.put((byte) 0x01);// EVT_EX 事件执行者ID
			buffer.put(new byte[] { 0x00, 0x00, 0x00, 0x00 });// EVT_SP 事件发起者ID

			// log.logInfo(""+System.currentTimeMillis());
			long utc = (System.currentTimeMillis() + (8 * 60 * 60 * 1000)) / 1000;// 系统当前时间
			// int buffer1 = Integer.parseUnsignedInt(String.valueOf(utc));
			byte[] buffer1 = ConverterHelper.reverse32((int) utc);
			for (byte b : buffer1) {// TIME 时间戳
				// System.out.format("%X", b);
				buffer.put(b);
			}

			// buffer.put(ConverterHelper.strToHex("8888888888888"));//APX(13B)
			// 附属消息

			// APX
			// 1. X0R = ALM_O ^ ALM_N
			// 2. ALARM = XOR & ALM_N;
		
			buffer.put(ALM[0]);//5d
			buffer.put(ALM[1]);//ff
			buffer.put(ALM[2]);//2c
			buffer.put(ALM[3]);//f2
			buffer.put(ALM[4]);//d7
			buffer.put(ALM[5]);//a9
			buffer.put(ALM[6]);//fc
			buffer.put(ALM[7]);//38
			//00001111漏电保护自检完成
			//00000111漏电保护自检未完成
			buffer.put((byte) 0x00);//e0
			buffer.put((byte) 0x00);//8c
			buffer.put((byte) 0x00);//97
			buffer.put((byte) 0x00);//02
			buffer.put((byte) 0x00);//0x98
		}

		long crc = CLibrary.INSTANCE.CalculateCRC32(buffer.array(),
				bufferlength);
		byte[] crc32 = ConverterHelper.reverse32(crc);
		// 添加到buffer
		buffer.put(crc32);

		return buffer.array();
	}
}
