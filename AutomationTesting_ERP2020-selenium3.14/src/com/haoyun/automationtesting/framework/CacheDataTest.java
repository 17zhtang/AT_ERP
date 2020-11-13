//package com.haoyun.automationtesting.framework;
//
//import java.util.concurrent.locks.ReadWriteLock;
//import java.util.concurrent.locks.ReentrantReadWriteLock;
//
//import org.testng.annotations.Test;
//
//public class CacheDataTest {
//	static StringBuffer RoleName = new StringBuffer("");
//	static ReadWriteLock lock = new ReentrantReadWriteLock();// 创建读写锁的实例
//
//	static String writecache(String okerr, String sheetname, int index) {
//
//		try {
//
//			lock.writeLock().lock();
//			try {
//				// 可能已经由其他线程写入数据
//				RoleName.append(okerr + ",");
//				RoleName.append(sheetname + ",");
//				RoleName.append(index + ";");
//			} finally {
//				// Downgrade by acquiring read lock before releasing write
//				// lock
//				lock.readLock().lock();
//				// Unlock write, still hold read
//				lock.writeLock().unlock();
//			}
//
//		} finally {
//			lock.readLock().unlock();// 最后一定不要忘记释放锁
//		}
//		System.out.println("报告中内容：" + RoleName.toString());
//		return RoleName.toString();
//	}
//
//	// public static void main(String[] args) {
//	// @Test
//	public static void run() {
//
//		System.out.printf("Thrad Id : %s%n", Thread.currentThread().getId());
//		System.out.println(writecache("成功", "1", 1));
//		System.out.println(writecache("成功", "1", 2));
//		System.out.println(writecache("成功", "1", 3));
//
//	}
//
//	@Test
//	public static void writeExcel() {
//		run();
//		log.logInfo("RoleName:" + RoleName.toString());
//		String SJ[] = RoleName.toString().split(";");
//		int length = SJ.length;
//		log.logInfo("length:" + length);
//		for (int i = 0; i < length; i++) {
//			String bg[] = SJ[i].split(",");
//			log.logInfo("1:" + bg[0] + "2:" + bg[1] + "3:" + bg[2]);
//		}
//
//	}
//}
