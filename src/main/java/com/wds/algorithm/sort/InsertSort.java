package com.wds.sort;


/**
 * 插入排序
 * <br>
 *     有一个已经有序的数据序列，要求在这个已经排好的数据序列中插入一个数，<br>
 *     但要求插入后此数据序列仍然有序，这个时候就要用到一种新的排序方法。时间复杂度0(n^2)<br>
 *     是稳定的排序方法。
 * <br>
 * Created by wds on 2015/8/1.
 */
public class InsertSort {

    public static void sort(int[] sourceData){
        int temp = 0;
        int j = 0;
        for(int i = 1; i < sourceData.length; i++){
            j = i - 1;
            temp = sourceData[i];
            for(; j >= 0 && temp < sourceData[j]; j--){
            	sourceData[j+1] = sourceData[j];
            }
            
            sourceData[j+1] = temp;
        }
        
    }
    
    public static void main(String[] args) {
		int sourceData[] = {39,48,65,97,12,15,5,30,60,25};
		sort(sourceData);
		for (int i : sourceData) {
			System.out.print(i + ",");
		}
		sortGt(sourceData);
		System.out.println();
		for (int i : sourceData) {
			System.out.print(i + ",");
		}
	}
    
    public static void sortGt(int[] sourceData){
    	if(sourceData == null || sourceData.length < 2){
    		return;
    	}
    	
    	int temp = 0;
    	
    	for(int i = 1; i < sourceData.length; i++){
    		for(int j = i; j > 0; j--){
    			if(sourceData[j] > sourceData[j - 1]){
    				temp = sourceData[j];
    				sourceData[j] = sourceData[j - 1];
    				sourceData[j - 1] = temp;
    			}else{
    				break;
    			}
    		}
    	}
    }
}
