package com.wetsion.study.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author weixin
 * @version 1.0
 * @CLassName Subject5
 * @date 2019/4/16 2:45 PM
 */
public class Subject5 {

    public static void main(String[] args) {
        String str = "ccc";
        System.out.println(new Solution().longestPalindrome(str));
    }
}
class Solution {
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 1) {
            return "";
        }
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private int expandAroundCenter(String s, int left, int right) {
        int L = left, R = right;
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }
        return R - L - 1;
    }
//    public String longestPalindrome(String s) {
//        int length = s.length();
//        if (length == 1) {
//            return s;
//        }
//        String result = "";
//        int max = 0;
//        Map<Integer, Character> leftMap = null;
//        Set<Character> leftSet = null;
//        for (int i = 0; i < length; i++) {
//            int pointor = i;
//            leftMap = new HashMap<>();
//            leftSet = new HashSet<>();
//
//            leftMap.put(0, s.charAt(pointor));
//            leftSet.add(s.charAt(pointor));
//            pointor -= 1;
//            boolean isSide = false;
//            while (pointor >= 0) {
//                leftMap.put(i - pointor, s.charAt(pointor));
//                leftSet.add(s.charAt(pointor));
//                int rightPointor = i + (i - pointor);
//                if (rightPointor < length) {
//                    // 如果右侧的在左侧存在
//                    if(leftSet.contains(s.charAt(rightPointor)) && leftMap.get(i-pointor).equals(s.charAt(rightPointor))) {
//                        isSide = true;
//                        String full = s.substring(pointor, rightPointor + 1);
//                        if (full.length() > max) {
//                            result = full;
//                            max = full.length();
//                        }
//                    }
//                }
//                pointor --;
//            }
//            if (!isSide && i < length - 1) {
//                if (s.charAt(i) == s.charAt(i + 1)) {
//                    String full = s.substring(i, i + 2);
//                    if (full.length() > max) {
//                        result = full;
//                        max = full.length();
//                    }
//                } else {
//                    String full = s.substring(i, i + 1);
//                    if (full.length() > max) {
//                        result = full;
//                        max = full.length();
//                    }
//                }
//            }
//        }
//        return result;
//    }
}
