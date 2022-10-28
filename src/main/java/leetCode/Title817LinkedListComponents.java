package leetCode;

import java.util.HashSet;

/**
 * @Description: 链表组件
 * @author: GanYang
 * @Date: 2022/10/14 12:39
 */
public class Title817LinkedListComponents {
    public static void main(String[] args) {
        ListNode head = new ListNode();
        ListNode node0 = new ListNode(0);
        head.next = node0;

        ListNode node1 = new ListNode(1);
        node0.next = node1;

        ListNode node2 = new ListNode(2);
        node1.next = node2;

        node2.next = new ListNode(3);


        int[] nums = new int[]{0, 1, 3};

        System.out.println(numComponents(head, nums));
    }

    /**
     * @param head 0,1,2,3
     * @param nums [0,1,3]
     * @return
     */
    public static int numComponents(ListNode head, int[] nums) {
        // 将 nums 排序
        ListNode p = head;
        boolean flag = false;
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }
        int result = 0;
        while (p != null) {
            //如果 当前值在 数组中，就表示是可能的组件
            //前提是 中间断过
            if (set.contains(p.val)) {
                if (!flag) {
                    result++;
                }
                flag = true;
            } else {
                flag = false;
            }
            p = p.next;
        }
        return result;
    }


    static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}

