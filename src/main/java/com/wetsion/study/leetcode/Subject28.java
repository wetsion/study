package com.wetsion.study.leetcode;

/**
 * @author weixin
 * @version 1.0
 * @CLassName Subject28
 * @date 2019/4/16 4:11 PM
 */
public class Subject28 {
    public static void main(String[] args) {
        String a = "mississippi" , b = "issipi";
        System.out.println(new Solution().strStr(a, b));
    }

    static class Solution {
        public int strStr(String haystack, String needle) {
            int hl = haystack.length();
            int nl = needle.length();
            if (hl < nl) {
                return -1;
            }
            if (needle == null || "".equals(needle)) {
                return 0;
            }
            int index = -1;
            for (int i = 0; i < hl; i++) {
                if (haystack.charAt(i) != needle.charAt(0)) {
                    continue;
                }
                boolean p = true;
                for (int j = 0; j < nl; j++) {
                    if (i + j >= hl) {
                        p = false;
                    } else {
                        if (haystack.charAt(i + j) != needle.charAt(j)) {
                            p = false;
                        }
                    }
                }
                if (p) {
                    index = i;
                    break;
                }
            }
            return index;
        }
    }
}

