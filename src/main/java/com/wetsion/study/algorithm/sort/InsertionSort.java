package com.wetsion.study.algorithm.sort;

import java.util.Arrays;

public class InsertionSort {

    public static void main(String[] args) {
        int[] array = {8,7,6,1,0,9,2};
        insertionSort(array);
        System.out.println(Arrays.toString(array));
    }

    public static void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            // 要比较的当前元素，记录下来，因为后面移动的元素会覆盖掉它
            int moved = arr[i];
            // 前面已排序好的部分，最后一位的下标
            int sortedIndex = i - 1;
            // 已排序部分的下标没有向前移动越界，且当前元素比已排序部分元素小时，则将已排序部分的元素向后移动一位
            while (sortedIndex >=0 && arr[sortedIndex] > moved) {
                 arr[sortedIndex + 1] = arr[sortedIndex];
                 sortedIndex--;
            }
            // 已排序部分下标移动到头了，或者当前元素比剩余已排序部分元素大，则将当前元素插入已排序部分
            arr[sortedIndex + 1] = moved;
        }
    }
}
