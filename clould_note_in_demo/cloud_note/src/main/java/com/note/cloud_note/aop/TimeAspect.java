package com.note.cloud_note.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
//@Aspect
public class TimeAspect {
	
	@Around("bean(*Service)")
	public Object test(ProceedingJoinPoint jp)throws Throwable{
		long t1=System.currentTimeMillis();
		Object val=jp.proceed();//目标业务方法
		long t2=System.currentTimeMillis();
		long t=t2-t1;
		//JoinPoint对象可以获取目标业务方法的详细信息：方法签名，调用参数
		Signature m=jp.getSignature();//签名，这里是方法签名
		
		System.out.println(m+"用时："+t+"ms");
		return val;
	}
	
	
}
