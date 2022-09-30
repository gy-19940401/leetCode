package leetCode;

import java.util.Random;

/**
 * @Description: 两数相加
 * @author: GanYang
 * @Date: 2022/9/29 12:38
 * <p>
 * explain 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * <p>
 * url: https://leetcode.cn/problems/two-sum/
 */
public class Title2AddTwoNumbers {

    static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        @Override
        public String toString() {
            ListNode p = this;
            StringBuilder result = new StringBuilder();
            while (p != null) {
                result.append(p.val);
                p = p.next;
            }
            return result.reverse().toString();
        }
    }

    public static void main(String[] args) {

        ListNode l1 = new ListNode();
        ListNode l2 = new ListNode();
        Random random = new Random();
        int index = random.nextInt(10) + 1;
        for (int i = 0; i < index; i++) {
            ListNode listNode = new ListNode(random.nextInt(10));
            listNode.next = l1.next;
            l1.next = listNode;
        }
        index = random.nextInt(10) + 1;
        for (int i = 0; i < index; i++) {
            ListNode listNode = new ListNode(random.nextInt(10));
            listNode.next = l2.next;
            l2.next = listNode;
        }
        addTwoNumbers(l1.next, l2.next);
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l1;
        }
        if (l2 == null) {
            return l2;
        }
        ListNode result = new ListNode();
        ListNode p = result;
        int c = 0;
        int add = 0;
        while (l1 != null || l2 != null || c > 0) {
            int val1 = l1 == null ? 0 : l1.val;
            int val2 = l2 == null ? 0 : l2.val;

            add = val1 + val2 + c;

            int val = add % 10;
            p.next = new ListNode(val);
            p.next.val = val;
            p = p.next;

            c = add / 10;

            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        return result.next;
    }
}
