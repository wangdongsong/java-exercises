package com.wds.datastructure.base.first;

public class PrintN {
	
	public static void main(String[] args) {
		
		forPrint(1000000);
		System.out.println("------------");
		recursion(1000000);
		
	}
	
	public static void forPrint(int n){
		for (int i = 0; i <= n; i++) {
			System.out.println(i);
		}
	}
	
	public static void recursion(int n){
		if(n > 0){
			recursion(n -1);
		}
		System.out.println(n);
	}

}
