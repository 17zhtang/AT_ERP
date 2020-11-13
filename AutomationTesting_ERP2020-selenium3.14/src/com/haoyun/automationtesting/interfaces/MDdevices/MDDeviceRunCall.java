package com.haoyun.automationtesting.interfaces.MDdevices;

import java.io.InterruptedIOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;
//UDP 套接字传输的是数据报文

import org.testng.annotations.Test;

import com.haoyun.automationtesting.framework.Config;
import com.haoyun.automationtesting.framework.log;

/**
 * UDP 客户端发送数据报文的步骤： 1.创建UPD 套接字 DataGramSocket
 * ，使用UDP协议通信是不需要和服务器端创建的连接的，UPD协议只是在IP协议上 多加了一层端口寻址，设置超时
 * 2.创建发送的数据报文实例DataGramPacket,若是单播的则要指明目的端的ip地址和port，IP地址通过创建InetAddress 的实例
 * 3.创建接收数据报文实例DataGramPacket ,指定接收数据的缓冲区 4.调用socket的send方法将数据报文发送出去
 * 5.循环接收数据报文，当数据报文丢失的时候，发起重试。否则设置响应标志位true,将数据打印
 */
public class MDDeviceRunCall {
	/**
	 * 当客户端发送给server端信息，收到回馈信息的时候，通过read读取数据，当没有数据返回（数据丢失） read
	 * 方法会发生阻塞，若没有设置超时重发，则程序会一直阻塞
	 */
	private static final int TIME_OUT = Config.MD_TIME_OUT; // 设置超时重发时间
	private static final int MAX_RENTRY = 1; // 设置最大重试次数

	public static final String YLAlarmStatus1 = "浪涌报警";
	public static final String YLAlarmStatus2 = "报警";
	public static final String YLAlarmStatus100 = "漏电保护自检未完成";

	@Test
	public static void runtest() throws Exception {
		String serviceid = "222222222222";
		//run_init(serviceid);//init	
		//MDDeviceRunCall.run_StatusReport(PM.MDDeviceID);//保持设备在线
		run_Alarm(serviceid, "漏电保护自检未完成");
		
	}
/**
 * 曼顿设备发送报警事件
 * @param serviceid
 * @param AlarmName
 * @return
 * @throws Exception
 */
	public static Boolean run_Alarm(String serviceid, String AlarmName)
			throws Exception {
		Boolean mdstatus=true;//判断成功失败状态
		run_init(serviceid);//init
		run_StatusReport(serviceid);// 先发送状态报告
		//action.sleep(1);
		byte[] ALM = new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00,
				(byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00 };// 初始化
		
		switch (AlarmName.trim()) {
		case YLAlarmStatus1:
			ALM = new byte[] { (byte) 0x5d, (byte) 0xff, (byte) 0x2c,
					(byte) 0xf2, (byte) 0xd7, (byte) 0xa9, (byte) 0xfc,
					(byte) 0x38 };
			mdstatus=MDSend(Model_push_event.toArray(serviceid, 1, ALM));// 报警
			log.logInfo("发送一条报警：" + YLAlarmStatus1);
			break;
		case YLAlarmStatus2:
			ALM = new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00,
					(byte) 0x00, (byte) 0x00, (byte) 0x01, (byte) 0x01,
					(byte) 0x01 };
			mdstatus=MDSend(Model_push_event.toArray(serviceid, 1, ALM));// 报警
			log.logInfo("发送一条报警：" + YLAlarmStatus2);
			break;
		case YLAlarmStatus100:
			ALM = new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00,
					(byte) 0x00, (byte) 0x00, (byte) 0x01, (byte) 0x01,
					(byte) 0x01 };
			mdstatus=MDSend(Model_push_event.toArray(serviceid, 1, ALM));// 
			log.logInfo("发送一条故障信息：" + YLAlarmStatus100);
			break;
		default:
			
			break;
		}
		return mdstatus;
	}

	public static void run_StatusReport(String serviceid) throws Exception {
		MDSend(Model_StatusReport.toArray(serviceid));// 状态报告
	}

	public static void run_init(String serviceid) throws Exception {
		MDSend(Model_Register.toArray(serviceid));// init1：Model_Register
		//action.sleep(1);
		MDSend(Model_Reply_Command.toArray(serviceid));// init2：Reply_Command
		//action.sleep(1);
		MDSend(Model_PushNetConfig.toArray(serviceid));// init3：Model_PushNetConfig
		//action.sleep(1);
		MDSend(Model_PushSwConfig.toArray(serviceid, 1));// init4：Model_PushSwConfig
		//action.sleep(1);
	}

	public static boolean MDSend(byte[] sendMsg) throws Exception {

		try {

			DatagramSocket socket = new DatagramSocket(); // 创建一个数据报文
			socket.setSoTimeout(TIME_OUT); // 设置read阻塞超时时间

			DatagramPacket sendPacket = new DatagramPacket(sendMsg,
					sendMsg.length, new InetSocketAddress(Config.IP,
							Config.MDPort));
			DatagramPacket receivePacket = new DatagramPacket(
					new byte[sendMsg.length], sendMsg.length); // 接收的数据报文
			// DatagramPacket receivePacket=new DatagramPacket(new byte[20],20);
			// //接收的数据报文
			int tryTimes = 0; // 数据报文可能丢失，设置重试计数器
			Boolean receiveResponse = false;
			socket.send(sendPacket); // 将数据报文发送出去
			do {
				try {
					socket.receive(receivePacket); // 尝试去循环接收数据报文
					// if (!receivePacket.getAddress().equals(inetAddress)) {
					// //检查回馈过来的数据报文
					// throw new IOException("未知的Server端数据报文");
					// }
					receiveResponse = true;
				} catch (InterruptedIOException e) { // 数据报文中断异常
					tryTimes++;
				//	System.out.println("超时还有" + (MAX_RENTRY - tryTimes)
				//			+ "次重试机会");
				}
			} while (!receiveResponse && (tryTimes < MAX_RENTRY));
			if (receiveResponse) {// 获取返回数据，先注释
				// byte buf[] = receivePacket.getData();
				// String s = new String(buf, 0, receivePacket.getLength());
				// System.out.println("从服务器端获取的数据："+new
				// String(receivePacket.getData()));
				// System.out.println("从服务器端获取的数据：" + s);
				/*
				 * // DataInputStream istream = new DataInputStream(new //
				 * ByteArrayInputStream(receivePacket.getData(), //
				 * receivePacket.getOffset(), receivePacket.getLength())); //
				 * //获取信息 // // String msg = istream.readUTF();//这行报错 // String
				 * msg = new //
				 * String(istream.readLine().getBytes("ISO-8859-1"),"utf-8"); //
				 * System.out.println("从服务器端获取的数据："+msg);
				 */
				socket.close(); // 关闭套接字
				return true;
			} else {
				log.logWarn(YLAlarmStatus1 + "没有获取到返回数据");
				socket.close(); // 关闭套接字
				return false;
			}
			
		} catch (SocketException e) {
			e.printStackTrace();
			
		}
		return true;
	}
}
