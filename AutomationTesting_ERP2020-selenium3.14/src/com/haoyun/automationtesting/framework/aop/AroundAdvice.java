package com.haoyun.automationtesting.framework.aop;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import com.haoyun.automationtesting.framework.log;

//@Aspect  
public class AroundAdvice implements MethodInterceptor {

	// @Pointcut("execution(* AutomationSpringAOP.*.*(..))")
	public void run() {
		System.out
				.println("**************The System is Searching Information For You****************");
	}

	// public void around(ProceedingJoinPoint pjp) throws Throwable {
	//
	// System.out.println("开始执行："+ pjp.getSignature().getName());
	// try {
	// pjp.proceed();
	// } catch (Throwable ex) {
	// System.out.println("error in around");
	// throw ex;
	// }
	// System.out.println("执行完成");
	// }
	long diff = 0;

	// @Override
	// @Around("pointCutMethod()")
	// @Around("run()")
	@Override
	public Object invoke(MethodInvocation arg0) throws Throwable {
		// TODO Auto-generated method stub

		// ProceedingJoinPoint pjp = null ;
		/*
		 * Object os[] = arg0.getArguments(); if (null != os) { for (int i = 0;
		 * i < os.length; i++) { System.out.println("===========" +
		 * os.toString()); } }else{ System.out.println("os is null===========");
		 * }
		 */
		String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS")
				.format(new Date());
		log.logInfo("开始执行：" + arg0.getThis().getClass());
		Object rval = arg0.proceed();

		String time1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS")
				.format(new Date());
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
		Date d1 = df.parse(time);
		Date d2 = df.parse(time1);
		diff = d2.getTime() - d1.getTime();
		// long m=diff / 1000;
		// System.out.println(d2);
		// System.out.println("秒:"+diff);
		log.logInfo("执行完成" + arg0.getClass().getName() + "所用时间（" + diff + ")毫秒");

		return rval;
	}

	public long gettime() {
		return diff;
	}

}