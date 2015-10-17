package com.wds.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RadixSort {
	
	public static void main(String[] args) {
		
		int sourceData[] = { 39, 48, 65, 97, 12, 15, 5, 30, 60, 25 };

		radixSort(sourceData);

		System.out.println(Arrays.toString(sourceData));
		
	}

	private static void radixSort(int[] sourceData) {
		
		int max = sourceData[0];
		
		for(int i = 1; i < sourceData.length; i++){
			if(sourceData[i] > max){
				max = sourceData[i];
			}
		}
		
		int time = 0;
		while(max > 0){
			max /= 10;
			time++;
		}
		
		List<ArrayList<Integer>> queue = new ArrayList<>();
		for(int i = 0; i < 10; i++){
			ArrayList<Integer> q = new ArrayList<>();
			queue.add(q);
		}
		
		for(int i = 0; i < time; i++){
			for(int j = 0; j < sourceData.length; j++){
				int x = sourceData[j] % (int)Math.pow(10, i+1) / (int)Math.pow(10, i);
				
				ArrayList<Integer> q = queue.get(x);
				
				q.add(sourceData[j]);
				
				queue.set(x, q);
			}
			
			int count = 0;
			for(int k = 0; k < 10; k++){
				while(queue.get(k).size() > 10){
					ArrayList<Integer> q = queue.get(k);
					sourceData[count] = q.get(0);
					q.remove(0);
					count++;
				}
			}
		}
		
	}

}
