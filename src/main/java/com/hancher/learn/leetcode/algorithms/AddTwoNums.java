package com.hancher.learn.leetcode.algorithms;

import com.hancher.learn.leetcode.algorithms.model.ListNode;

/**
 * You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
 * <p>
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 * <p>
 * Example:
 * <p>
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 0 -> 8
 * Explanation: 342 + 465 = 807.
 * <p/>
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 * <p/>
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 * <p/>
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * <p/>
 * 答案：https://leetcode-cn.com/problems/add-two-numbers/solution/liang-shu-xiang-jia-by-leetcode/
 * <p/>
 *
 * @author Hancher
 * @version 1.0
 * @date Created in 2020年01月15日 23:56
 * @since 1.0
 */
public class AddTwoNums {
    public static void main(String[] args) {
        ListNode l1 = new ListNode(9);
        ListNode l2 = new ListNode(1);
        l2.next(9).next(9).next(9)
        .next(9).next(9).next(9)
        .next(9).next(9).next(9);

        ListNode result = new AddTwoNums().addTwoNumbers(l1, l2);
        ListNode result2 = new AddTwoNums().officialAnswer(l1, l2);
        System.out.println(1);
    }

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode(int x) { val = x; }
     * }
     *
     * my solution
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        return addListNodeByHeight(l1,l2,0,0);
    }

    /**
     * 递归链表，每一位相加。
     * 因为是从低位开始的，所以很合适，大于10进位加1就行了
     */
    private ListNode addListNodeByHeight(ListNode l1,ListNode l2,int height,int isTen) {
        if (l1 == null && l2 == null && isTen == 0) {
            return null;
        }
        int v1 = l1==null ? 0: l1.val;
        int v2 = l2==null ? 0: l2.val;

        int val = v1+v2+isTen;
        // 判断是否要进1
        int thisIsTen = 0;
        if (val > 9) {
            thisIsTen = 1;
            val = val % 10;
        }

        //初始化节点
        ListNode result = new ListNode(val);
        //下一层
        ListNode next1 = l1==null ? null : l1.next;
        ListNode next2 = l2==null ? null : l2.next;
        result.next = addListNodeByHeight(next1,next2,++height,thisIsTen);
        return result;
    }


    /**
     * 官方答案：https://leetcode-cn.com/problems/add-two-numbers/solution/liang-shu-xiang-jia-by-leetcode/
     * <p/>
     * 使用循环，更好一些，不会有栈溢出的风险
     * <p/>
     * @author Hancher
     * @date Created in 2020年01月18日 18:57
     * @version 1.0
     * @since 1.0
     */
    public ListNode officialAnswer(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode p = l1, q = l2, curr = dummyHead;
        int carry = 0;
        while (p != null || q != null) {
            int x = (p != null) ? p.val : 0;
            int y = (q != null) ? q.val : 0;
            int sum = carry + x + y;
            carry = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
            if (p != null) p = p.next;
            if (q != null) q = q.next;
        }
        //将所有链表遍历完后，判断是否还有进位
        if (carry > 0) {
            curr.next = new ListNode(carry);
        }
        return dummyHead.next;
    }

}
