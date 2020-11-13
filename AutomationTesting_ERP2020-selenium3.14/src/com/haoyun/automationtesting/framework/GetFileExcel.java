package com.haoyun.automationtesting.framework;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/***
 * 获取某文件目录下的所有excel文件名
 * 
 * @author ls
 *
 */
public class GetFileExcel {
	// 遍历所有文件夹下面的xlsx文件，返回一个文件名字。
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static String OnlyExcel() throws InterruptedException, Exception {

		File file = new File("resource/");
		String result = "";
		String s[] = file.list();
		List<String> list = new ArrayList();

		for (String string : s) {
			log.logInfo("process文件夹下的文件是：" + string);
			result = string.substring(string.lastIndexOf("."), string.length());
			if (result.equals(".xlsx")) {
				list.add(string);
			}
		}
		// System.out.println(list.size());
		if (list.size() > 1 || list.size() == 0) {
			log.logError("请在process文件夹中放一个要执行的excel测试用例文件~！");
			return null;
		} else {
			return list.get(0);
		}

	}

	public static void main(String[] args) throws InterruptedException,
			Exception {
		System.out.println(OnlyExcel());
	}
}
