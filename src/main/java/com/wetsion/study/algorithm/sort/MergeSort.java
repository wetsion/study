package com.wetsion.study.algorithm.sort;

import java.util.Arrays;

public class MergeSort {

    public static void main(String[] args) {
        int[] array = {8,7,6,1,0,9,2};
        mergeSort(array, 0, array.length-1);
        System.out.println(Arrays.toString(array));
    }

    public static void mergeSort(int[] arr, int low, int high) {
        // 数组只有一个元素的时候不满足条件，会跳出递归
        if (low < high) {
            // 计算中间位置
            int mid = low + (high - low) / 2;
            // 切分数组，分成左右两个子数组，直到数组只有一个元素
            mergeSort(arr, low, mid);
            mergeSort(arr, mid+1, high);
            // 合并左右子数组
            merge(arr, low, mid, high);
        }
    }

    public static void merge(int[] arr, int low, int mid, int high) {
        // 计算两个子数组的长度
        int leftBlockLength = mid - low + 1;
        int rightBlockLength = high - mid;
        // 创建两个临时数组
        int[] leftBlock = new int[leftBlockLength];
        int[] rightBlock = new int[rightBlockLength];
        // 复制数据到临时数组
        for (int i = 0; i < leftBlockLength; i++) {
            leftBlock[i] = arr[low + i];
        }
        for (int i = 0;i < rightBlockLength; i++) {
            rightBlock[i] = arr[mid + i +1];
        }
        // 两个子数组的起点初始化
        int leftIndex = 0, rightIndex = 0;
        // 合并到原数组目前位置的起点
        int mergeIndex = low;
        // 同时遍历两个子数组，将两个子数组合并成一个有序的数组
        while (leftIndex < leftBlockLength && rightIndex < rightBlockLength) {
            if (leftBlock[leftIndex] < rightBlock[rightIndex]) {
                // 左数据元素小，则将左数据元素复制到原数组中
                arr[mergeIndex] = leftBlock[leftIndex];
                leftIndex++;
            } else {
                // 右数据元素小，则将右数据元素复制到原数组中
                arr[mergeIndex] = rightBlock[rightIndex];
                rightIndex++;
            }
            // 目标数组位置移动+1
            mergeIndex++;
        }
        // 处理左数组可能的剩余元素，剩余的元素复制到原数组中
        while (leftIndex < leftBlockLength) {
            arr[mergeIndex] = leftBlock[leftIndex];
            leftIndex++;
            mergeIndex++;
        }
        // 处理右数组可能的剩余元素，剩余的元素复制到原数组中
        while (rightIndex < rightBlockLength) {
            arr[mergeIndex] = rightBlock[rightIndex];
            rightIndex++;
            mergeIndex++;
        }
    }
}
