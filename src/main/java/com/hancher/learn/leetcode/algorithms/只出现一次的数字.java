package com.hancher.learn.leetcode.algorithms;
/**
 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 * 说明：
 * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 * tag: 位运算 , 哈希表 , 布隆过滤
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions/xm0u83/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *
 * <p/>
 * @author Hancher
 * @date Created in 2021年02月21日 22:44
 * @version 1.0
 * @since 1.0
 */
public class 只出现一次的数字 {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 4, 5, 4, 3, 2, 1};
        int i = hancherSolution(nums);
        System.out.println(i);
    }



    /**
     * 通过位运算方式求解
     * 两个相同的数据，异或后=0. 最终剩余唯一值
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     * @param nums
     * @return
     */
    public static int hancherSolution(int[] nums) {
        int i = 0;
        for (int num : nums) {
            i ^= num;
        }

        return i;
    }

    /**
     *  作者：LeetCode-Solution
     *     链接：https://leetcode-cn.com/problems/single-number/solution/zhi-chu-xian-yi-ci-de-shu-zi-by-leetcode-solution/
     *     来源：力扣（LeetCode）
     *     著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param nums
     * @return
     */
    public static int singleNumber(int[] nums) {
        int single = 0;
        for (int num : nums) {
            single ^= num;
        }
        return single;
    }

}
