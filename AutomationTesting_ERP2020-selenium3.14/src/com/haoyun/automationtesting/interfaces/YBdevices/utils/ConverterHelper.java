package com.haoyun.automationtesting.interfaces.YBdevices.utils;

import java.math.BigInteger;
import java.nio.ByteBuffer;

public class ConverterHelper {
	/**
	 * 长整型 字节序转换
	 * 
	 * @param value
	 * @return
	 */
	public static byte[] reverse32int(int value) {
		byte[] result = new byte[4];
		result[0] = (byte) ((value >> 24) & 0xFF);
		result[1] = (byte) ((value >> 16) & 0xFF);
		result[2] = (byte) ((value >> 8) & 0xFF);
		result[3] = (byte) (value & 0xFF);
		return result;
	}

	/**
	 * 整型 字节序转换
	 * 
	 * @param value
	 * @return
	 */
	public static byte[] reverse32(long value) {
		byte[] result = new byte[4];
		result[0] = (byte) ((value >> 24) & 0xFF);
		result[1] = (byte) ((value >> 16) & 0xFF);
		result[2] = (byte) ((value >> 8) & 0xFF);
		result[3] = (byte) (value & 0xFF);
		return result;
	}
	

	/**
	 * 短整型字节序转换
	 * 
	 * @param value
	 * @return
	 */
	public static byte[] reverse16(short value) {
		byte[] result = new byte[2];
		result[0] = (byte) ((value >> 8) & 0xFF);
		result[1] = (byte) (value & 0xFF);
		return result;
	}

	/**
	 * 字符串转16进制
	 * 
	 * @param value
	 * @return
	 */
	public static byte[] strToHex(String value) {

		char h1, h2;
		byte s1, s2;

		char[] charArr = value.toUpperCase().toCharArray();
		ByteBuffer buffer = ByteBuffer.allocate(charArr.length / 2);

		for (int i = 0; i < charArr.length / 2; i++) {
			// 打断点
			h1 = charArr[2 * i];
			h2 = charArr[2 * i + 1];

			s1 = (byte) ((byte) h1 - 0x30);
			if (s1 > 9)
				s1 -= 7;

			s2 = (byte) ((byte) h2 - 0x30);
			if (s2 > 9)
				s2 -= 7;

			// 打断点
			buffer.put((byte) (s1 << 4 | s2));
		}

		return buffer.array();
	}

	// 將10進制轉換為16進制
	public static String encodeHEX(Integer numb) {

		String hex = Integer.toHexString(numb);
		return hex;

	}

	// 將16進制字符串轉換為10進制數字
	public static int decodeHEX(String hexs) {
		BigInteger bigint = new BigInteger(hexs, 16);
		int numb = bigint.intValue();
		return numb;
	}

}
