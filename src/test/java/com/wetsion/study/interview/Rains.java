package com.wetsion.study.interview;

import java.util.Arrays;

/**
 * @CLassName: Rains
 * @Author: weixin
 * @Description: TODO
 * @DATE: 2019/1/17 4:34 PM
 * @Version: 1.0
 */
public class Rains {

    private static int result;
    private static Integer max = Integer.MIN_VALUE;
    private static Integer[] tempArray;
    private static Integer[] pastArray = new Integer[2];

    public static void main(String[] args) {
//        int[] array = { 0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1 };
//        int[] array = {3,1,0,2,1,0,1,3,2,1,2,1};
        int[] array = {3,1,0,2,1};
        do {
            getMaxValue(array);
            System.out.println("max = " + max);
            pastArray = tempArray;
            System.out.println("pastArray = " + Arrays.toString(pastArray));
            getCountOfMaxValue(array);
            System.out.println("tempArray = " + Arrays.toString(tempArray));
            getResult(array);
            System.out.println("result = " + result);
        } while (max > 0);
    }

    private static void getResult(int[] array) {
        int tempResult = (tempArray[1] - tempArray[0] - 1) * max;
        for (int i = tempArray[0] + 1; i < tempArray[1]; i++) {
            if (array[i] > max) {
                tempResult -= max;
            } else {
                if (array[i] < max && i > pastArray[0] && i < pastArray[1]) {
                    tempResult -= max;
                } else {
                    tempResult -= array[i];
                }
            }
        }
        if (tempResult > 0) {
            result += tempResult;
        }
    }

    private static void getMaxValue(int[] array) {
        int tempMax = 0;
        if (max != Integer.MIN_VALUE) {
            for (int i = 0; i < array.length; i++) {
                if (array[i] > tempMax && array[i] < max) {
                    tempMax = array[i];
                }
            }
            max = tempMax;
        } else {
            for (int i = 0; i < array.length; i++) {
                if (array[i] > max) {
                    max = array[i];
                }
            }
        }

    }

    public static void getCountOfMaxValue(int[] array) {
        tempArray = new Integer[] { -1, -1 };
        for (int i = 0; i < array.length; i++) {
            if (array[i] == max) {
                if (tempArray[0] == -1) {
                    tempArray[0] = i;
                } else {
                    tempArray[1] = i;
                }
            }
        }
    }
}
