package com.haoyun.automationtesting.interfaces.YBdevices.utils;

import java.util.Arrays;

public class EncryptionHelper {

	/**
	 * 加密 vs. 解密
	 * 
	 * @param data
	 * @param len
	 * @return
	 */
	public static byte[] EncryptionPacket(byte[] data, int len) {

		byte[] bt = Arrays.copyOf(data, len);
		int num2 = len - 2;
		for (int i = 2; i <= num2; i++) {
			if ((i % 2) == 0) {
				bt[i] = (byte) (0xff - bt[i]);
			}
		}
		return bt;

	}

}
