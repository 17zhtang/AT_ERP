package com.haoyun.automationtesting.framework;

import java.io.File;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class ParseXml {
	@SuppressWarnings("unused")
	private String filePath;
	private Document document;

	public ParseXml(String filePath) {
		super();
		this.filePath = filePath;
		this.load(filePath);
	}

	public void load(String filePath) {
		File file = new File(filePath);
		if (file.exists()) {
			SAXReader saxReader = new SAXReader();
			try {
				document = saxReader.read(file);
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				System.out.println("文件加载异常" + filePath);
			}
		} else {
			System.out.println("文件没找到");
		}
	}

	public Element getElementObject(String elementPath) {
		// 专门读取哪个节点
		return (Element) document.selectSingleNode(elementPath);
	}

	public String getElementText(String elementPath) {
		// 此方法就是加了一个去掉空格转换成字符串,判断节点是否存在
		Element element = this.getElementObject(elementPath);
		if (element != null) {
			return element.getTextTrim();
		} else {
			return null;
		}
	}

	public static void main(String[] args) {
		ParseXml parseXml = new ParseXml("config/config.xml");
		String element = parseXml.getElementText("/config/browser");
		System.out.println(element);
	}
}
