package com.hancher.learn.leetcode.algorithms.model;

public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int x) { val = x; }

    public ListNode next(int x) {
        this.next = new ListNode(x);
        return next;
    }
}
