package com.wds.sort;

import java.util.Arrays;

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
				if(sourceData[j] > sourceData[j+1]){
					temp = sourceData[j];
					sourceData[j] = sourceData[j + 1];
					sourceData[j + 1] = temp;
				}
			}
		}
	}

}
