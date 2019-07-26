package com.note.cloud_note.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
//@Aspect
public class homework {
	
	@Before("bean(notebookService)")
	public void test1(){
		System.out.println("访问了，notebookService");
	}
	
	@Before("within(cn.tedu.note.service.UserService)")
	public void test2(){
		System.out.println("访问了UserService");
	}
	
	@Before("within(cn.tedu.note.service.NoteService)")
	public void test3(){
		System.out.println("业务类 被访问了");
	}
	
	@Around("bean(*Service)")
	public Object test4(ProceedingJoinPoint jp) throws Throwable{
		long t1=System.currentTimeMillis();
		Object val=jp.proceed();
		long t2=System.currentTimeMillis();
		long t=t2-t1;
		Signature sig= jp.getSignature();
		System.out.println(sig+"时间："+t+"ms");
		return val;
		
	}
	
}
