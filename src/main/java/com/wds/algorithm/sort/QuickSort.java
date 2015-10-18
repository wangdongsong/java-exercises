package com.wds.algorithm.sort;

import java.util.Arrays;

public class QuickSort {

	public static void main(String[] args) {
		int sourceData[] = {39,48,65,97,12,15,5,30,60,25};
		
		quickSort(sourceData, 0, sourceData.length -1);
		
		System.out.println(Arrays.toString(sourceData));
	}
	
	public static int getMiddle(int[] sourceData, int low, int high){
		int temp = sourceData[low];
		while(low < high){
			while(low < high && sourceData[high] >= temp){
				high--;
			}
			
			sourceData[low] = sourceData[high];
			while(low < high && sourceData[low] <= temp){
				low++;
			}
			
			sourceData[high] = sourceData[low];
		}
		sourceData[low] = temp;
		
		return low;
	}

	private static void quickSort(int[] sourceData, int low, int high) {
		if(low < high){
			int middle = getMiddle(sourceData, low, high);
			quickSort(sourceData, low, middle - 1);
			quickSort(sourceData, middle + 1, high);
		}
	}
	
}
