package com.note.cloud_note.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import com.note.cloud_note.exception.UserNotFoundException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * 创建一个切面组件，就是一个普通的javabean
 */
//@Component
//@Aspect
public class DemoAspect {
	
	//声明test方法将在userService的全部方法之前执行
	//@Before("bean(userService)")
	public void test(){
		
		ServletWebRequest servletContainer = (ServletWebRequest) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletContainer.getRequest();
        HttpServletResponse response = servletContainer.getResponse();
		
		System.out.println("Hello World!");
	}
	
	//@After("bean(userService)")
	public void test2(){
		System.out.println("After");
	}
	
	//@AfterReturning("bean(userService)")
	public void test3(){
		System.out.println("AfterReturning");
	}
	
	//@AfterThrowing("bean(userService)")
	public void test4(){
		System.out.println("AfterThrowing");
	}
	
	/**
	 *  1. 必须有返回值值Object
	 *  2. 必须有参数  ProceedingJoinPoint
	 *  3. 必须抛出异常
	 *  4. 需要在方法中调用  jp.proceed()
	 *  5. 返回业务方法的返回值
	 * @param jp
	 * @return
	 * @throws Throwable
	 */
	//@Around("bean(userService)")
	public Object test5(ProceedingJoinPoint jp)throws Throwable{
		Object val=jp.proceed();
//		System.out.println("业务结果："+val);
//		throw new UserNotFoundException("就是不让登陆");
		return val;
	}
	
}
