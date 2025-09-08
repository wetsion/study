package com.wetsion.study.algorithm.sort;

import java.util.Arrays;

public class QuickSort {
    public static void main(String[] args) {
        int[] array = {8,7,6,1,0,9,2};
        quickSort(array, 0, array.length-1);
        System.out.println(Arrays.toString(array));
    }

    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int mid = partition(arr, low, high);
            quickSort(arr, low, mid-1);
            quickSort(arr, mid+1, high);
        }
    }

    private static int partition(int[] arr, int low, int high) {
        // 最后一个元素作为中心元素
        int mid = arr[high];
        // 第二指针指向第一个元素
        int secondPoint = low;
        for (int i = low; i < high; i++) {
            if (arr[i] < mid) {
                int tmp = arr[i];
                arr[i] = arr[secondPoint];
                arr[secondPoint]= tmp;
                secondPoint++;
            }
        }
        // 将第二指针和high位置的元素即中心元素交换
        int tmp = arr[secondPoint];
        arr[secondPoint] = arr[high];
        arr[high] = tmp;
        // 返回第二指针的位置
        return secondPoint;
    }
}
