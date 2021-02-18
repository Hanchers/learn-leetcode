package com.hancher.learn.leetcode.algorithms;


/**
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 *
 * 如果不存在公共前缀，返回空字符串 ""。
 * https://leetcode-cn.com/problems/longest-common-prefix/
 * <p/>
 * @author Hancher
 * @date Created in 2021年02月18日 18:22
 * @version 1.0
 * @since 1.0
 */
public class 最长公共前缀 {
    /**
     * 本质上，遍历完一个字符的长度，就能找到公共前缀了
     */
    public static void main(String[] args) {
        String[] strs = new String[]{"abc","abcd","abc"};
        System.out.println(hancherSolution(strs));
    }

    /**
     * 效果
     * 执行用时：15 ms, 在所有 Java 提交中击败了5.49%的用户
     * 内存消耗：38.5 MB, 在所有 Java 提交中击败了6.89%的用户
     * @param strs
     * @return
     */
    public static String hancherSolution(String[] strs) {
        String prefix = "";
        if (strs == null || strs.length == 0) {
            return prefix;
        }
        boolean finish = false;
        while (!finish) {
            String temp = "";
            for (String str : strs) {
                if (prefix.equals(str)) {
                    // 当前缀与字符串完全相同时，匹配最短字符串
                    finish = true;
                    break;
                }
                if (temp.isBlank()) {
                    temp = prefix + str.charAt(prefix.length());
                }
                if (!str.startsWith(temp)) {
                    // 不重复了
                    finish = true;
                    break;
                }
            }
            // 全部相同，前缀+1
            if (!finish) {
                prefix = temp;
            }
        }

        return prefix;
    }


    /**
     * 横向比较法
     * 两两比较，取最大值
     * @param strs
     * @return
     */
    public static String longestCommonPrefix1(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        String prefix = strs[0];
        int count = strs.length;
        for (int i = 1; i < count; i++) {
            // 前缀和每一个字符比较
            prefix = longestCommonPrefix(prefix, strs[i]);
            if (prefix.length() == 0) {
                break;
            }
        }
        return prefix;
    }

    public static String longestCommonPrefix(String str1, String str2) {
        int length = Math.min(str1.length(), str2.length());
        int index = 0;
        // 找公共字符串
        while (index < length && str1.charAt(index) == str2.charAt(index)) {
            index++;
        }
        return str1.substring(0, index);
    }

    /**
     * 纵向比较
     * 比较每个字符串的前字符
     * 和自己的实现思路可以做对比
     * @param strs
     * @return
     */
    public static String longestCommonPrefix2(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        int length = strs[0].length();// 取第一个字符串的长度
        int count = strs.length; // 字符串数组长度
        for (int i = 0; i < length; i++) {
            char c = strs[0].charAt(i);
            for (int j = 1; j < count; j++) {
                // 找到最短的字符串 或者 对应位置字符不匹配时
                if (i == strs[j].length() || strs[j].charAt(i) != c) {
                    return strs[0].substring(0, i);
                }
            }
        }
        return strs[0];
    }

}
