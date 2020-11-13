package com.haoyun.automationtesting.framework;

import java.io.Closeable;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;

/**
 * 对文本文件的关键词进行搜索
 * 
 * @author
 * 
 */
public class TextFileSearch {
	/***
	 * 对文本文件的关键词进行搜索
	 * 
	 * @param file
	 *            文件路径及文件名
	 * @param keyword
	 *            搜索的关键字
	 */
	public static void SearchKeyword(File file, String keyword) {
		// 参数校验
		verifyParam(file, keyword);

		// 行读取
		LineNumberReader lineReader = null;
		try {
			lineReader = new LineNumberReader(new FileReader(file));
			String readLine = null;
			while ((readLine = lineReader.readLine()) != null) {
				// 判断每一行中,出现关键词的次数
				int index = 0;
				int next = 0;
				int times = 0;// 出现的次数
				// 判断次数
				while ((index = readLine.indexOf(keyword, next)) != -1) {
					next = index + keyword.length();
					times++;
				}
				if (times > 0) {
					log.logInfo("第" + lineReader.getLineNumber() + "行" + "出现 "
							+ keyword + " 次数: " + times);
				} else {
					log.logInfo("没找到关键字");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 关闭流
			close(lineReader);
		}
	}

	public static Boolean SearchKeyword_2(File file, String keyword,
			String keyword2) {
		boolean SearchKeyword_2 = false;
		// 参数校验
		verifyParam(file, keyword);

		// 行读取
		LineNumberReader lineReader = null;
		try {
			lineReader = new LineNumberReader(new FileReader(file));
			String readLine = null;

			while ((readLine = lineReader.readLine()) != null) {
				// 判断每一行中,出现关键词的次数
				int index = 0;
				int next = 0;
				int times = 0;// 出现的次数
				int i = 0;
				// 判断次数
				while ((index = readLine.indexOf(keyword, next)) != -1) {
					next = index + keyword.length();

					i = readLine.indexOf(keyword2, next);
					if (i > 0) {
						SearchKeyword_2 = true;

						times = times + 1;

					}

				}

			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 关闭流
			close(lineReader);
		}
		return SearchKeyword_2;
	}

	/**
	 * 参数校验
	 * 
	 * Date: 2014年11月5日
	 */
	private static void verifyParam(File file, String keyword) {
		// 对参数进行校验证
		if (file == null) {
			throw new NullPointerException("the file is null");
		}
		if (keyword == null || keyword.trim().equals("")) {
			throw new NullPointerException("the keyword is null or \"\" ");
		}

		if (!file.exists()) {
			throw new RuntimeException("the file is not exists");
		}
		// 非目录
		if (file.isDirectory()) {
			throw new RuntimeException("the file is a directory,not a file");
		}

		// 可读取
		if (!file.canRead()) {
			throw new RuntimeException("the file can't read");
		}
	}

	/**
	 * 关闭流 <br>
	 * Date: 2014年11月5日
	 */
	private static void close(Closeable able) {
		if (able != null) {
			try {
				able.close();
			} catch (IOException e) {
				e.printStackTrace();
				able = null;
			}
		}
	}

}