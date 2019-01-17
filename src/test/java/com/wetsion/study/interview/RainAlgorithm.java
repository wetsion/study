package com.wetsion.study.interview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 头条雨水算法问题
 *
 * @CLassName: RainAlgorithm
 * @author weixin
 * @DATE: 2019/1/17 11:16 AM
 * @Version: 1.0
 */
public class RainAlgorithm {
    public static void main(String[] args) {
//        int[] arr = {0,1,0,2,1,0,1,4,2,1,2,1};
        int[] arr = {3,1,0,2,1,0,1,3,2,1,2,1};
//        int[] arr = {3,1,0,2,1};
        System.out.println(trapRainWater(arr));
    }
//    private static int compute(int[] arr) {
//        int level = 0;
//        int total = 0;
//        for (int i=0;i<arr.length;i++) {
//            level = Math.max(level, arr[i]);
//        }
//    }

    public static int trapRainWater(int[] heights) {
        int max = 0;
        for (int i=0;i<heights.length;i++) {
            max = Math.max(max, heights[i]);
        }
        int rains = 0;
        // 思路是先看最底下一层能接多少雨水，然后再依次推断到最高层
        for (int floor = 0; floor < max; floor++) {
            // 当层数为floor层时
            // 从第1位到第一个非0位之间的0都置为1（便于统计这一层的雨水）
            for (int i=0;i<=heights.length-1;i++) {//统一使用0-start
                if (heights[i] == 0) {
                    heights[i] = 1;
                } else {
                    break;
                }
            }
            // 从最后1位到倒数第一个非0位之间的0都置为1（便于统计这一层的雨水）
            for (int i=heights.length-1;i>=0;i--) {//统一使用0-start
                if (heights[i] == 0) {
                    heights[i] = 1;
                } else {
                    break;
                }
            }
            for (int i=0;i<heights.length;i++) {
                if (heights[i] == 0) {
                    //经过前面的准备工作，我们可以很轻松的分辨出来哪些是雨水
                    rains++;
                } else {
                    //这里要做个操作，就是把数组里的所有元素都减1，为了上面一层的计算做准备
                    heights[i]--;
                }
            }

        }
        return rains;
    }

}
