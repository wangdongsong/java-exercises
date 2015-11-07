package com.wds.algorithm.sort;

/**
 * 平均速度O(n*n)，最坏情况下的速度为O(n*n)
 */
public class SimpleSelectSort {

	public static void main(String[] args) {
		int sourceData[] = {39,48,65,97,12,15,5,30,60,25};
		
		simpleSelectSort(sourceData);
		for (int i : sourceData) {
			System.out.print(i + ",");
		}
	}
	
	public static void simpleSelectSort(int[] sourceData){
		int position = 0;
		int j = 0;
		int temp = 0;
		for(int i = 0; i < sourceData.length; i++){
			j = i + 1;
			position = i;
			
			temp = sourceData[i];
			
			for(; j < sourceData.length; j++){
				if(sourceData[j] < temp){
					temp = sourceData[j];
					position = j;
				}
			}
			
			sourceData[position] = sourceData[i];
			sourceData[i] = temp;
		}
	}

}
