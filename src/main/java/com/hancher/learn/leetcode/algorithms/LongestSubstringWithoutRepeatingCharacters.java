package com.hancher.learn.leetcode.algorithms;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/
 * <p/>
 * Given a string, find the length of the longest substring without repeating characters. <p/>
 *
 * Example 1: <p/>
 *
 * Input: "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3.
 * Example 2:
 * <p/>
 * Input: "bbbbb"
 * Output: 1
 * Explanation: The answer is "b", with the length of 1.
 * Example 3:
 * <p/>
 * Input: "pwwkew"
 * Output: 3
 * Explanation: The answer is "wke", with the length of 3.
 *              Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
 *
 * <p/>
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * <p/>
 * 示例 1:
 * <p/>
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 * <p/>
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 * <p/>
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 *
 * <p/>
 * @author Hancher
 * @date Created in 2020年01月18日 19:10
 * @version 1.0
 * @since 1.0
 */
public class LongestSubstringWithoutRepeatingCharacters {
    public static void main(String[] args) {
        LongestSubstringWithoutRepeatingCharacters o = new LongestSubstringWithoutRepeatingCharacters();
        String str = "abccabdbb";
//        System.out.println(str + " : "+o.lengthOfLongestSubstring(str));
//        str = "bbbbb";
//        System.out.println(str + " : "+o.lengthOfLongestSubstring(str));
//        str = "pwwkew";
//        System.out.println(str + " : "+o.lengthOfLongestSubstring(str));
//        str = "abcdefgh";
//        System.out.println(str + " : "+o.lengthOfLongestSubstring(str));
//        str = "";
//        System.out.println(str + " : "+o.lengthOfLongestSubstring(str));
//        str = " "; //错误1
//        System.out.println(str + " : "+o.lengthOfLongestSubstring(str));
//        str = "cdd"; //错误2
//        System.out.println(str + " : "+o.lengthOfLongestSubstring(str));
//        str = "jbpnbwwd"; //错误3
//        System.out.println(str + " : "+o.lengthOfLongestSubstring(str));

        str = "abcadefj";
        System.out.println(str + " : "+o.godAnswer(str));
    }

    /**
     * my solution
     *
     * <p/>
     * @author Hancher
     * @date Created in 2020年01月18日 19:19
     * @version 1.0
     * @since 1.0
     */
    public int lengthOfLongestSubstring(String s) {
        //目标字符串
        String target = "";

        for (int i=0;i<s.length();i++) {
            StringBuilder temp = new StringBuilder();
            Set<Character> charSet = new HashSet<>();

//            boolean toEnd = false;
            boolean toEnd = true;
            for (int j=i;j<s.length();j++) {
                //从当前位置开始叠加字符，判断新增字符是否存在
                char c = s.charAt(j);
                if (!charSet.contains(c)) {
                    temp.append(c);
                    //错误3
//                    charSet.add(c);
                } else {
                    //如果存在，跳出当前循序，更新目标值
                    if (target.length() < temp.length()) {
                        target = temp.toString();
                        toEnd = false;
                        //错误3
//                        break;
                    }
                    break;
                }

                //已经遍历的字符加入set
                charSet.add(c);
//                错误1
//                if (j == s.length() -1) {
//                    toEnd = true;
//                    target = temp.toString();
//                }
            }

            //如果到最后也没重复，则找到最终结果
            if (toEnd
                    //错误2
                    &&target.length() < temp.length()) {
                target = temp.toString();//错误1，漏了
                break;
            }
        }


        return target.length();
    }


    /**
     * 官方答案:滑动窗口
     * https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/solution/wu-zhong-fu-zi-fu-de-zui-chang-zi-chuan-by-leetcod/
     */
    public int officialAnswer(String s) {
        int n = s.length();
        Set<Character> set = new HashSet<>();
        int ans = 0, i = 0, j = 0;
        while (i < n && j < n) {
            // try to extend the range [i, j]
            if (!set.contains(s.charAt(j))){
                set.add(s.charAt(j++));
                ans = Math.max(ans, j - i);
            }
            else {
                set.remove(s.charAt(i++));
            }
        }
        return ans;

    }

    /**
     * 官方答案:优化的滑动窗口
     * https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/solution/wu-zhong-fu-zi-fu-de-zui-chang-zi-chuan-by-leetcod/
     */
    public int officialAnswer2(String s) {
        int n = s.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<>(); // current index of character
        // try to extend the range [i, j]
        for (int j = 0, i = 0; j < n; j++) {
            if (map.containsKey(s.charAt(j))) {
                i = Math.max(map.get(s.charAt(j)), i);
            }
            ans = Math.max(ans, j - i + 1);
            map.put(s.charAt(j), j + 1);
        }
        return ans;

    }

    /**
     * 大神答案：活动窗口
     * https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/solution/hua-dong-chuang-kou-by-powcai/
     *
     * 第一次没看懂
     */
    public int godAnswer(String s) {
        if (s.length()==0) return 0;
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        int max = 0;
        int left = 0;
        for(int i = 0; i < s.length(); i ++){
            if(map.containsKey(s.charAt(i))){
                left = Math.max(left,map.get(s.charAt(i)) + 1);
            }
            map.put(s.charAt(i),i);
            max = Math.max(max,i-left+1);
        }
        return max;

    }
}
