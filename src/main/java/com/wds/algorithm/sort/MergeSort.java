package com.wds.sort;

import java.util.Arrays;

public class MergeSort {

	public static void main(String[] args) {
		int sourceData[] = { 39, 48, 65, 97, 12, 15, 5, 30, 60, 25 };

		mergeSort(sourceData, 0, sourceData.length -1);

		System.out.println(Arrays.toString(sourceData));
	}

	private static void mergeSort(int[] sourceData, int left, int right) {
		if(left < right){
			int center = (left + right) / 2;
			mergeSort(sourceData, left, center);
			mergeSort(sourceData, center + 1, right);
			merge(sourceData, left, center, right);
		}
	}

	private static void merge(int[] sourceData, int left, int center, int right) {
		int[] tempArr = new int[sourceData.length];
		int mid = center + 1;
		int third = left;
		int temp = left;
		
		while(left <= center && mid <= right){
			if(sourceData[left] <= sourceData[mid]){
				tempArr[third++] = sourceData[left++];
			}else{
				tempArr[third++] = sourceData[mid++];
			}
		}
		
		while(mid <= right){
			tempArr[third++] = sourceData[mid++];
		}
		
		while(left <= center){
			tempArr[third++] = sourceData[left++];
		}
		
		while(temp <= right){
			sourceData[temp] = tempArr[temp++];
		}
	}

}
