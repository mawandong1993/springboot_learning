package com.note.cloud_note.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
//@Component
//@Aspect
public class PointcutAspect {
	
	//@Before("bean(*Service)")
	public void test(){
		System.out.println("切入点测试");
	}
	//@Before("bean(noteService)")
	public void test1(){
		System.out.println("笔记切入点测试");
	}
	//@Before("within(cn.tedu.note.service.NoteService)")
	public void test2(){
		System.out.println("业务类 被访问了");
	}
	//@Before("within(cn.tedu.note.controller.NoteController)")
	public void test3(){
		System.out.println("控制类 被访问了");
	}
	
	//@Before("execution(* cn.tedu.note.*.*Service.list*(..))")
	public void test4(){
		System.out.println("列表 方法 被访问了");
	}
	

}
