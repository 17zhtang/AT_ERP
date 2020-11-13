package com.haoyun.automationtesting.interfaces.YBdevices.utils;

public class CheckSumHelper {

	/**
	 * 加和校验
	 * 
	 * @param data
	 * @param len
	 * @return
	 */
	public static byte CheckSum(byte[] data, int len) {
		int num = 0;
		for (int i = 0; i < len - 1; i++) {
			num += (data[i] & 0xFF);
		}
		return (byte) (num % 256);
	}

}
