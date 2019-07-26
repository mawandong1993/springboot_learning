package com.note.cloud_note.test1;

import java.io.UnsupportedEncodingException;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;

public class demo01 {

	
	
	@Test
	public void testBean2(){
		for(int i=0;i<=63;i++){
			System.out.println(i+":\t"+Integer.toBinaryString(i));
		}
	}

	@Test
	public void testLeftPad(){
		//左填充；将str左面补充到指定的长度
		long time1=System.currentTimeMillis();
		for(int i=0;i<=64;i++){
			System.out.println(i+"\t"+StringUtils.leftPad(Integer.toBinaryString(i), 32,"0"));
		}
		long time2=System.currentTimeMillis();
		System.out.println(time2-time1);
	}
	
	@Test
	public void testHex(){
		int i=0x7532a2fa;
		System.out.println(Integer.toBinaryString(i));
	}
	
	@Test
	public void test01(){
		int i=0xac;
		System.out.println(i);
		System.out.println(Integer.toBinaryString(i));
	}
	
	@Test
	public void test02(){
		int i=-3;
		System.out.println(i);
		System.out.println(Integer.toBinaryString(i));
		int j=3;
		System.out.println(j);
		System.out.println(Integer.toBinaryString(j));
	}
	
	@Test
	public void test03(){
		int i=-1;
		System.out.println(i);
		System.out.println(Integer.toBinaryString(i));
		long j=-1L;
		System.out.println(j);
		System.out.println(Long.toBinaryString(j));
	}
	@Test
	public void test04(){
		for(int i=0;i<=50;i++){
			long j=-i;
			System.out.println(Integer.toBinaryString(i)+"\t"+Long.toBinaryString(j));
		}
	}
	@Test
	public void test05(){
			long i=Long.MAX_VALUE;
			System.out.println(i);
			System.out.println(Long.toBinaryString(i));
			i=Long.MIN_VALUE;
			System.out.println(i);
			System.out.println(Long.toBinaryString(i));
			
	}
	@Test
	public void test07(){
		long i=Integer.MAX_VALUE;
		System.out.println(Long.toBinaryString(i));
	}
	@Test
	public void test08(){
		int i=0xffffffff;
		System.out.println(i);
		System.out.println(Integer.toBinaryString(i));
	}
	@Test
	public void test09(){
		int i=Integer.MAX_VALUE;
		int k=Integer.MIN_VALUE;
		System.out.println(k-i);
	}
	@Test
	public void test10(){
		int i=8;
		System.out.println(~8);
	}
	
	@Test
	public void test11(){
		int i='中';
		System.out.println(i);
		System.out.println(Integer.toBinaryString(i));
	}
	
	@Test
	public void test12(){
		int i='中';
		int j=0x3f;
		int k=i&j;
		System.out.println(Integer.toBinaryString(i));
		System.out.println(Integer.toBinaryString(j));
		System.out.println(Integer.toBinaryString(k));
	}
	@Test
	public void test13(){
		int i=0x2d;
		int j=0x80;
		int k=i|j;
		System.out.println(Integer.toBinaryString(i));
		System.out.println(Integer.toBinaryString(j));
		System.out.println(Integer.toBinaryString(k));
	}
	@Test
	public void test14(){
		int i='中';
		int j=0x3f;
		int k=(i&j)|0x80;
		System.out.println(Integer.toBinaryString(i));
		System.out.println(Integer.toBinaryString(j));
		System.out.println(Integer.toBinaryString(k));
	}
	@Test
	public void test15(){
		int i=0x227aaabb;
		int j=i>>>1;
		int k=i>>>2;
		int m=i>>>6;
		System.out.println(Integer.toBinaryString(i));
		System.out.println(Integer.toBinaryString(j));
		System.out.println(Integer.toBinaryString(k));
		System.out.println(Integer.toBinaryString(m));
	}
	@Test
	public void test16(){
		int i=50;
		int m=i<<1;
		System.out.println(m);
		m=i<<2;
		System.out.println(m);
		m=i>>1;
		System.out.println(m);
	}
	
	
	@Test
	public void unicode_to_utf_8() throws UnsupportedEncodingException{
		int c='中';
		int m=0x3f;
		int b3=(c&m)|0x80;
		int b2=((c>>>6)&m)|0x80;
		int b1=(c>>>12)|0xe0;
		System.out.println(StringUtils.leftPad(Integer.toBinaryString(c), 32,"0"));
		System.out.println(Integer.toBinaryString(c));
		System.out.println(Integer.toBinaryString(m));
		System.out.println(Integer.toBinaryString(b3));
		System.out.println(Integer.toBinaryString(b2));
		System.out.println(Integer.toBinaryString(b1));
		//jdk提供了utf-8到char的解码
		byte[] bytes={(byte)b1,(byte)b2,(byte)b3};
		String s=new String(bytes,"utf-8");
		System.out.println(s);
		
		int ch=(b3&0x3f) | ((b2&0x3f)<<6) | ((b1&0xf)<<12);
		System.out.println((char)(ch));
		
	}
	
}
