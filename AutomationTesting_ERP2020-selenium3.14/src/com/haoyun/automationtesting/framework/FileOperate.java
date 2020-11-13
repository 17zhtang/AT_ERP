package com.haoyun.automationtesting.framework;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/***
 * 删除单个文件
 * 
 * @author admin
 * @param filePathAndName
 *            文件的路径和文件名
 */
public class FileOperate {
	public static void delFile(String filePathAndName) {
		try {

			String filePath = filePathAndName;
			filePath = filePath.toString();
			java.io.File myDelFile = new java.io.File(filePath);
			myDelFile.delete();
		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	/***
	 * 删除目录下所有文件
	 * 
	 * @param path
	 */
	public static void delAllFile(String path) {

		File file = new File(path);
		if (!file.exists()) {
			return;
		}

		if (!file.isDirectory()) {
			return;
		}

		String[] tempList = file.list();
		File temp = null;
		for (int i = 0; i < tempList.length; i++) {
			// System.out.println(tempList.length);
			if (path.endsWith(File.separator)) {
				temp = new File(path + tempList[i]);
			} else {
				temp = new File(path + File.separator + tempList[i]);
			}

			if (temp.isFile()) {
				temp.delete();
			}

			if (temp.isDirectory()) {
				delAllFile(path + "/" + tempList[i]);
				// delFolder(path + "/" + tempList[i]);
			}
		}

	}

	/***
	 * 复制整个文件夹
	 * 
	 * @param oldPath
	 * @param newPath
	 */
	public static void copyFolder(String oldPath, String newPath) {
		try {
			(new File(newPath)).mkdirs();
			File a = new File(oldPath);
			String[] file = a.list();
			File temp = null;
			for (int i = 0; i < file.length; i++) {
				if (oldPath.endsWith(File.separator)) {
					temp = new File(oldPath + file[i]);
				} else {
					temp = new File(oldPath + File.separator + file[i]);
				}

				if (temp.isFile()) {
					FileInputStream input = new FileInputStream(temp);
					FileOutputStream output = new FileOutputStream(newPath
							+ "/" + (temp.getName()).toString());
					byte[] b = new byte[1024 * 5];
					int len;
					while ((len = input.read(b)) != -1) {
						output.write(b, 0, len);
					}

					output.flush();
					output.close();
					input.close();
				}
				if (temp.isDirectory()) {// 如果是子文件夹
					copyFolder(oldPath + "/" + file[i], newPath + "/" + file[i]);
				}
			}

		} catch (Exception e) {
			log.logWarn("复制整个文件夹内容出错！");
			e.printStackTrace();

		}
	}

	/***
	 * 剪切文件夹
	 * 
	 * @param oldPath
	 * @param newPath
	 */
	public static void cutFolder(String oldPath, String newPath) {
		try {
			copyFolder(oldPath, newPath);
			delAllFile(oldPath);
		} catch (Exception e) {
			log.logWarn("剪切文件夹出错！");
			e.printStackTrace();
		}

	}
}
