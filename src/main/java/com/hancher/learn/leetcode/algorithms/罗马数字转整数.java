package com.hancher.learn.leetcode.algorithms;

import java.util.HashMap;
import java.util.Map;

/**
 * 罗马数字包含以下七种字符:I，V，X，L，C，D和M。
 * <p>
 * 字符          数值
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * 例如， 罗马数字 2 写做II，即为两个并列的 1。12 写做XII，即为X+II。 27 写做XXVII, 即为XX+V+II。
 * <p>
 * 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做IIII，而是IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为IX。这个特殊的规则只适用于以下六种情况：
 * <p>
 * I可以放在V(5) 和X(10) 的左边，来表示 4 和 9。
 * X可以放在L(50) 和C(100) 的左边，来表示 40 和90。
 * C可以放在D(500) 和M(1000) 的左边，来表示400 和900。
 * 给定一个罗马数字，将其转换成整数。输入确保在 1到 3999 的范围内。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/roman-to-integer
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * <p/>
 *
 * @author Hancher
 * @version 1.0
 * @date Created in 2021年02月17日 18:07
 * @since 1.0
 */
public class 罗马数字转整数 {
    public static void main(String[] args) {
        expect().forEach((k,v)->{
            System.out.println("***begin:" + k);
            int result = hancherSolution(k);
            System.out.println("source:" + k + ",expect: " + v + ",result: "+result + ",ok:"+ (v == result) );
        });
    }

    /**
     * 解题思路：
     * 1、基本规则与特殊规则 就是全部有限的规则了，通过全量枚举实现
     * 2、将字符串拆解为单个字符，相加。只不过针对特殊规则，每次取规则的时候，优先与下一位组合匹配，不成功则单独匹配
     *
     * 效果：
     * 执行用时：21 ms, 在所有 Java 提交中击败了5.31%的用户
     * 内存消耗：39.6 MB, 在所有 Java 提交中击败了5.10%的用户
     * @param
     * @author Hancher
     * @date  2021年02月18日  10:23
     * @since 1.0
     * @return
     */
    public static int hancherSolution(String s) {
        Map<String, Integer> romanRule = romanRule();
        // 元数据映射
        if (romanRule.get(s) != null) {
            return romanRule.get(s);
        }
        // 普通数据时,按空格拆分,依次相加
        String[] romanNums = s.split("");
        int result = 0;
        for (int i = 0; i < romanNums.length; i++) {
            String romanNum = romanNums[i];
            boolean specialStart = "I".equals(romanNum) || "X".equals(romanNum) || "C".equals(romanNum);
            if (i<romanNums.length-1 && specialStart) {
                // 特殊标记，优先和下一个字符匹配
                String temp = romanNum + romanNums[i + 1];
                if (romanRule.get(temp) != null) {
                    // 特殊规则匹配成功
                    romanNum = temp;
                    i++;
                }
            }
            result += romanRule.get(romanNum);
        }
        return result;
    }

    /**
     * 效果：
     * 执行用时：18 ms, 在所有 Java 提交中击败了5.31%的用户
     * 内存消耗：39.4 MB, 在所有 Java 提交中击败了5.10%的用户
     */
    public static int hancherSolution2(String s) {
        // 普通数据时,按空格拆分,依次相加
        String[] romanNums = s.split("");
        int result = 0;
        for (int i = 0; i < romanNums.length; i++) {
            String romanNum = romanNums[i];
            boolean specialStart = "I".equals(romanNum) || "X".equals(romanNum) || "C".equals(romanNum);
            if (i<romanNums.length-1 && specialStart) {
                // 特殊标记，优先和下一个字符匹配
                String temp = romanNum + romanNums[i + 1];
                if (witch(temp) >0) {
                    // 特殊规则匹配成功
                    romanNum = temp;
                    i++;
                }
            }
            result += witch(romanNum);
        }
        return result;
    }

    private static Map<String, Integer> expect() {
        Map<String, Integer> map = new HashMap<>();
        map.put("I", 1);
        map.put("III", 3);
        map.put("IV", 4);
        map.put("IX", 9);
        map.put("LVIII", 58);
        map.put("MCMXCIV", 1994); //  M = 1000, CM = 900, XC = 90, IV = 4.
        map.put("XLIX", 49); //  XL=40,IX=9
        map.put("CMXCIX", 999); //  CM=900, XC=90, IX=9

        return map;
    }

    /**
     * 元数据映射
     *
     * @return 基本元数据+特殊元数据
     */
    private static Map<String, Integer> romanRule() {
        Map<String, Integer> rule = new HashMap<>();
        rule.put("I", 1);
        rule.put("V", 5);
        rule.put("X", 10);
        rule.put("L", 50);
        rule.put("C", 100);
        rule.put("D", 500);
        rule.put("M", 1000);
        // 特殊规则
        rule.put("IV", 4);
        rule.put("IX", 9);
        rule.put("XL", 40);
        rule.put("XC", 90);
        rule.put("CD", 400);
        rule.put("CM", 900);

        return rule;
    }

    private static Integer witch(String unit) {
        switch(unit) {
            case "I": return 1;
            case "V": return 5;
            case "X": return 10;
            case "L": return 50;
            case "C": return 100;
            case "D": return 500;
            case "M": return 1000;
            case "IV": return 4;
            case "IX": return 9;
            case "XL": return 40;
            case "XC": return 90;
            case "CD": return 400;
            case "CM": return 900;
            default:
                return 0;
        }
    }

    private static Integer witch2(char unit) {
        switch(unit) {
            case 'I': return 1;
            case 'V': return 5;
            case 'X': return 10;
            case 'L': return 50;
            case 'C': return 100;
            case 'D': return 500;
            case 'M': return 1000;
            case 'a': return 4;
            case 'b': return 9;
            case 'c': return 40;
            case 'd': return 90;
            case 'e': return 400;
            case 'f': return 900;
            default:
                return 0;
        }
    }



    /**
     * 比较法
     * 把小值放在大值左边就做减法， 否则做加法
     * @param s
     * @return
     */
    public static int romanToInt(String s) {
        int sum = 0;
        int preNum = witch2(s.charAt(0));
        for(int i = 1;i < s.length(); i ++) {
            int num = witch2(s.charAt(i));
            if(preNum < num) {
                sum -= preNum;
            } else {
                sum += preNum;
            }
            preNum = num;
        }
        sum += preNum;
        return sum;
    }

    /**
     * 替换法：
     * 1、将特殊规则优先全部替换
     * 2、累加
     * 效果：
     * 执行用时：7 ms, 在所有 Java 提交中击败了41.22%的用户
     * 内存消耗：38.8 MB, 在所有 Java 提交中击败了39.88%的用户
     * @param s
     * @return
     */
    public static int romanToInt2(String s) {
        s = s.replace("IV","a");
        s = s.replace("IX","b");
        s = s.replace("XL","c");
        s = s.replace("XC","d");
        s = s.replace("CD","e");
        s = s.replace("CM","f");

        int result = 0;
        for (int i=0; i<s.length(); i++) {
            result += witch2(s.charAt(i));
        }
        return result;
    }


}
