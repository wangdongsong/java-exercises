package com.wds.sort;

/**
 * 希尔排序
 * <br>
 * 希尔排序(Shell Sort)是插入排序的一种。<br>
 * 也称缩小增量排序，是直接插入排序算法的一种更高效的改进版本。希尔排序是非稳定排序算法
 * @author wds
 *
 */
public class ShellSout {
	
	public static void shellSort(int[] sourceData){
		
		double d1 = sourceData.length;
		int temp = 0;
		while(true){
			d1 = Math.ceil(d1 / 2);
			int d = (int)d1;
			for(int x = 0; x < d; x++){
				for(int i = x + d; i < sourceData.length; i += d){
					int j = i - d;
					temp = sourceData[i];
					for(; j >= 0 && temp < sourceData[j]; j -= d){
						sourceData[j + d] = sourceData[j];
					}
					sourceData[j + d] = temp;
				}
			}
			
			if(d == 1){
				break;
			}
		}
		
	}
	
	public static void main(String[] args) {
		int sourceData[] = {39,48,65,97,12,15,5,30,60,25};
		shellSort(sourceData);
		for (int i : sourceData) {
			System.out.print(i + ",");
		}
		System.out.println();
		shellSort(sourceData);
		for (int i : sourceData) {
			System.out.print(i + ",");
		}
	}
	
}
 