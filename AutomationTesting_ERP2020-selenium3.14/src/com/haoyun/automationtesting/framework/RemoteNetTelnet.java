package com.haoyun.automationtesting.framework;

/**   
 * commons-net-2.0.jar是工程依赖包    
 */

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;

import org.apache.commons.net.telnet.EchoOptionHandler;
import org.apache.commons.net.telnet.TelnetClient; //下载 commons-net.zip，解压得到commons-net.jar，再把它加到build path里
import org.apache.commons.net.telnet.TerminalTypeOptionHandler;
import org.apache.commons.net.telnet.WindowSizeOptionHandler;

/**
 * 使用 telnet 远程执行linux的shell script
 * 
 * @descript NetTelenet.java
 * @author
 * @date Jun 10, 2017
 */
public class RemoteNetTelnet {
	private TelnetClient telnet = new TelnetClient();
	private InputStream in;
	private PrintStream out;
	private char prompt = '$';// 普通用户结束

	public RemoteNetTelnet(String ip, int port, String user, String password) {
		try {
			telnet.connect(ip, port);
			in = telnet.getInputStream();
			out = new PrintStream(telnet.getOutputStream());

			// 根据root用户设置结束符
			this.prompt = user.equals("root") ? '#' : '$';
			login(user, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 登录
	 * 
	 * @param user
	 * @param password
	 */
	public void login(String user, String password) {
		readUntil("login:");
		write(user);
		readUntil("Password:");
		write(password);
		readUntil(prompt + " ");
	}

	/**
	 * 读取分析结果
	 * 
	 * @param pattern
	 * @return
	 */
	public String readUntil(String pattern) {
		try {
			char lastChar = pattern.charAt(pattern.length() - 1);
			StringBuffer sb = new StringBuffer();
			char ch = (char) in.read();
			while (true) {
				sb.append(ch);
				if (ch == lastChar) {
					if (sb.toString().endsWith(pattern)) {
						return sb.toString();
					}
				}
				ch = (char) in.read();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 写操作
	 * 
	 * @param value
	 */
	public void write(String value) {
		try {
			out.println(value);
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 向目标发送命令字符串
	 * 
	 * @param command
	 * @return
	 */
	public String sendCommand(String command) {
		try {
			write(command);
			return readUntil(prompt + " ");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 关闭连接
	 */
	public void disconnect() {
		try {
			telnet.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/***
	 * 使用 telnet 远程执行linux命令，获取日志信息，并对比结果。
	 * 
	 * @param keyword
	 *            对比结果的关键字
	 * @return
	 */
	public static Boolean isExist(String keyword) {
		boolean isExist = false;
		try {
			System.out.println("启动Telnet...");
			String ip = "10.8.6.29";
			int port = 23;
			String user = "mbusr";
			String password = "123456";
			RemoteNetTelnet telnet = new RemoteNetTelnet(ip, port, user,
					password);
			System.out.println("10.8.6.29  远程连接成功");
			// Thread.sleep(1000);
			telnet.sendCommand("export LANG=en");
			// Thread.sleep(1000);
			// String r1 = telnet.sendCommand( "ls" );
			// System.out.println( r1 );
			// Thread.sleep(1000);
			// String r2 = telnet.sendCommand(
			// "cd /home/mbusr/mbankAppSrv01/logs/newapplog" );
			// System.out.println( r2 );
			// Thread.sleep(1000);
			String r3 = telnet
					.sendCommand("tail -n 200 /home/mbusr/mbankAppSrv01/logs/newapplog/esb_api.log");
			System.out.println(r3);
			log.logInfo(r3);
			// TextFileSearch.SearchString(r3, "INFO","Default");
			int i = r3.indexOf(keyword);
			System.out.println(i);
			if (i > 0) {
				isExist = true;
			} else {
				isExist = false;
			}
			telnet.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isExist;
	}

	/***
	 * telnet 与服务器交互。可在控制台操作linux，代替终端工具。
	 * 
	 * @throws Exception
	 */
	public static void controljiaohu() throws Exception {
		TelnetClient tc = new TelnetClient();
		tc.addOptionHandler(new TerminalTypeOptionHandler("VT100", false,
				false, true, false));
		tc.addOptionHandler(new EchoOptionHandler(true, true, true, true));
		tc.addOptionHandler(new WindowSizeOptionHandler(80, 25, true, false,
				true, false));
		String ip = "10.8.6.29";
		int port = 23;
		tc.connect(ip, port);
		final InputStream in = tc.getInputStream();
		OutputStream out = tc.getOutputStream();
		Thread t1 = new Thread() {
			@Override
			public void run() {
				try {
					byte[] buff = new byte[10240];
					int len = 0;
					while ((len = in.read(buff, 0, buff.length)) != -1) {
						System.out.print(new String(buff, 0, len));
					}
					System.out.println("EXIT");
					System.exit(0);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		t1.start();

		PrintStream ps = new PrintStream(out);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = null;
		while ((line = br.readLine()) != null) {
			ps.println(line);
			ps.flush();
		}
		br.close();
	}

}
