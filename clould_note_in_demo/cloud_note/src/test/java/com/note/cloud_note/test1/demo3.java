package com.note.cloud_note.test1;

import org.junit.Test;

public class demo3 {
	public static void main(String[] args) {
		int n=2;
		int[] ary={2};
		test(n,ary);
		System.out.println(n);
		System.out.println(ary[0]);
	}
	
	public static void test(Integer i,int[] ary){
		i=i++;
		ary[0]++;
	}
	
	@Test
	public void test2(){
		String s="A";
		add(s);
		System.out.println(s);
	}
	
	public void add(String s){
		s+="A";
	}

}
