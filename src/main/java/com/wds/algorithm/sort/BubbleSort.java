package com.wds.algorithm.sort;

import java.util.Arrays;

/**
 * 冒泡排序，是一种稳定的排序算法<br>
 * 平均速度O(n*n)，最坏情况下O(n*n)
 *
 */
public class BubbleSort {
	
	public static void main(String[] args) {
		int sourceData[] = {39,48,65,97,12,15,5,30,60,25};
		
		bubbleSort(sourceData);
		
		System.out.println(Arrays.toString(sourceData));
	}

	private static void bubbleSort(int[] sourceData) {
		int temp = 0;
		for(int i = 0; i < sourceData.length; i++){
			for(int j = 0; j < sourceData.length - 1 - i; j++){
				System.out.println(Arrays.toString(sourceData));
				if(sourceData[j] < sourceData[j+1]){
					temp = sourceData[j];
					sourceData[j] = sourceData[j + 1];
					sourceData[j + 1] = temp;
				}
			}
		}
	}

}
