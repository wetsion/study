package com.wetsion.study.algorithm.sort;

import java.util.Arrays;

import static com.wetsion.study.algorithm.Swep.swap;

public class HeapSort {

    public static void main(String[] args) {
        int[] array = {8,7,6,1,0,9,2};
        System.out.println(Arrays.toString(array));
        heapSort(array);
        System.out.println(Arrays.toString(array));
    }

    public static void heapSort(int[] arr) {
        int n = arr.length;
        // 从最后一个非叶子节点开始，倒序遍历，n/2-1就是最后一个非叶子节点
        for (int i = n/2 -1; i>=0; i--) {
            heapify(arr, n, i);
            System.out.println(Arrays.toString(arr));
        }
        System.out.println("第二遍");
        // 倒序遍历，每个元素都和堆顶元素对比，如果比堆顶元素大，则交换，并继续和子节点对比
        for (int i = n-1; i>0; i--) {
            int tmp = arr[0];
            arr[0] = arr[i];
            arr[i] = tmp;
            System.out.println("交换堆顶");
            System.out.println(Arrays.toString(arr));
            heapify(arr, i, 0);
            System.out.println(Arrays.toString(arr));
        }
    }

    /**
     * 堆化
     * @param arr 数组
     * @param n 数组长度
     * @param i 节点索引
     */
    private static void heapify(int[] arr, int n, int i) {
        System.out.println("i:"+i+", n:"+n);
        // 当前节点是最大节点
        int largest = i;
        // 左子节点
        int left = 2*i + 1;
        // 右子节点
        int right = 2*i + 2;
        if (left < n && arr[left] > arr[i]) {
            // 如果左子节点大于父节点
            largest = left;
        }
        if (right < n && arr[right] > arr[largest]) {
            largest = right;
        }
        if (largest != i) {
            // 如果最大节点不是当前节点，就交换
            swap(arr, i, largest);
            // 递归调整交换影响到的子树
            heapify(arr, n, largest);
        }
    }
}
