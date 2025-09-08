package com.wetsion.study.algorithm.sort;

import java.util.Arrays;

import static com.wetsion.study.algorithm.Swep.swap;

public class BubbleSort {

    public static void main(String[] args) {
        int[] array = {8,7,6,1,0,9,2};
        bubbleSort(array);
        System.out.println(Arrays.toString(array));
    }

    public static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j+1]) {
                    swap(arr, j, j+1);
                }
            }
        }
    }
}
