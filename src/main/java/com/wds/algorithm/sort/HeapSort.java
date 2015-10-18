package com.wds.algorithm.sort;

import java.util.Arrays;

public class HeapSort {
	
	public static void main(String[] args) {
		int sourceData[] = {39,48,65,97,12,15,5,30,60,25};
		
		heapSort(sourceData);
		
		System.out.println(Arrays.toString(sourceData));
	}
	
	public static void heapSort(int[] sourceData){
		int arrLength = sourceData.length;
		
		//loop build heap
		for(int i = 0; i < arrLength - 1; i++){
			//build heap
			buildMaxheap(sourceData, arrLength - 1- i);
			
			swap(sourceData, 0, arrLength - 1- i);
			
		}
	}

	private static void swap(int[] sourceData, int i, int j) {
		int temp = sourceData[i];
		sourceData[i] = sourceData[j];
		sourceData[j] = temp;
	}

	private static void buildMaxheap(int[] sourceData, int lastIndex) {
		int k;
		//从lastIndex处节点（最后一个节点）的父节点开始
		for(int i = (lastIndex - 1) /2; i >= 0; i--){
			//k保存正在判断的节点
			k = i;
			//如果当前k节点的子节点存在
			while(k * 2 + 1 <= lastIndex){
				//k节点的左子节点的索引
				int biggerIndex = 2 * k + 1;
				
				//如果biggerIndex小于lastIndex，即biggerIndex+1代表的k节点的右子节点存在
				if(biggerIndex < lastIndex){
					//如果右子节点的值较大
					if(sourceData[biggerIndex] < sourceData[biggerIndex + 1]){
						//biggerIndex总是记录较大子节点的索引
						biggerIndex++;
					}
				}
				
				//如果k节点的值小于其较大的子节点的值
				if(sourceData[k] < sourceData[biggerIndex]){
					//交换他们
					swap(sourceData, k, biggerIndex);
					//将biggerIndex给k，开始while循环的下一次循环
					//重新保证k节点的值大于其左右子节点的值
					k = biggerIndex;
				}else{
					break;
				}
			}
		}
	}
	
}
