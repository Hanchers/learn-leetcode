package com.hancher.learn.leetcode.algorithms;

import java.util.Arrays;
import java.util.List;

/**
 * 给你一个整数 x ，如果 x 是一个回文整数，返回 true ；否则，返回 false 。
 *
 * 回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。例如，121 是回文，而 123 不是。
 * x = 121 => true
 * x = -121 => false
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/palindrome-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * <p/>
 * @author Hancher
 * @date Created in 2021年02月17日 16:44
 * @version 1.0
 * @since 1.0
 */
public class 回文数 {
    public static void main(String[] args) {
        List<Integer> nums = Arrays.asList(0, 100,121,1221,12021, Integer.MAX_VALUE, Integer.MIN_VALUE, 1, 9, -1, -121);
        for (Integer num : nums) {
            System.out.println("===============begin:" + num + ",result:"+hancherSolution(num));
            System.out.println("===============official begin:" + num + ",result:"+officialCopy(num));
        }

        //test
//        int num = 1;
//        System.out.println("===============begin:" + num + ",result:"+hancherSolution(num));
    }

    /**
     * 自己解决方案
     * 思路：
     * 1、负数都不回文
     * 2、正数取余倒转，再与源比较
     */
    public static boolean hancherSolution(int x) {
        // 负数都不回文
        if (x<0) {
            return false;
        }
        // 正数
        int temp = 0;
        int source = x;
        while (source > 0) {
            temp = temp * 10 + source % 10;
            source = source / 10;
        }
//        System.out.println("中间过程：回文数=" + temp);

        return temp == x;
    }

    public static boolean officialCopy(int x) {
        // 负数都不是
        if (x<0) {
            return false;
        }
        // 最后一位是0 也非回文
        if (x!=0 && x%10 ==0) {
            return false;
        }

        // 后半部分
        int temp = 0;
        while (x > temp) {
            temp = temp * 10 + x % 10;
            x = x / 10;
        }

        // 区分奇数位与偶数位
        return temp == x || temp / 10 == x;
    }


    /**
     * 官方解决方案
     */
    public static boolean officialSolution(int x) {

        return isPalindrome(x);
    }

    /**
     * 思路：回文数的后半部分与前半部分比较
     */
    public static boolean isPalindrome(int x) {
        // 特殊情况：
        // 如上所述，当 x < 0 时，x 不是回文数。
        // 同样地，如果数字的最后一位是 0，为了使该数字为回文，
        // 则其第一位数字也应该是 0
        // 只有 0 满足这一属性
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }

        int revertedNumber = 0;
        while (x > revertedNumber) {
            revertedNumber = revertedNumber * 10 + x % 10;
            x /= 10;
        }

        // 当数字长度为奇数时，我们可以通过 revertedNumber/10 去除处于中位的数字。
        // 例如，当输入为 12321 时，在 while 循环的末尾我们可以得到 x = 12，revertedNumber = 123，
        // 由于处于中位的数字不影响回文（它总是与自己相等），所以我们可以简单地将其去除。
        return x == revertedNumber || x == revertedNumber / 10;
    }
}


