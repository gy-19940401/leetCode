package leetCode;

import java.util.Arrays;

/**
 * @description: 和至少为 K 的最短子数组
 * @author: GanYang
 * @date: 2022/10/27 12:44
 * 给你一个整数数组 nums 和一个整数 k ，找出 nums 中和至少为 k 的 最短非空子数组 ，并返回该子数组的长度。如果不存在这样的 子数组 ，返回 -1 。
 * 子数组 是数组中 连续 的一部分。
 * <p>
 * 链接：https://leetcode.cn/problems/shortest-subarray-with-sum-at-least-k
 */
public class Title862ShortestSubarrayWithSumAtLeastK {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int k = 55;
        System.out.println(shortestSubarray(nums, k));
    }

    private static int shortestSubarray(int[] nums, int k) {
        Arrays.sort(nums);
        if (nums[0] == k) {
            return 1;
        } else if (nums[0] > k) {
            return -1;
        } else {

        }

        return -1;
    }
}
