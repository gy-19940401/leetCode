package leetCode;

import java.util.Arrays;

/**
 * @Description: 两数之和
 * @author: GanYang
 * @Date: 2022/9/29 12:33
 * <p>
 * explain : 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
 * url: https://leetcode.cn/problems/two-sum/
 */
public class Title1TwoSum {
    public static void main(String[] args) {
        int[] nums = new int[]{2, 7, 11, 15};
        int target = 9;
        System.out.println(Arrays.toString(twoSum(nums, target)));
    }

    public static int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        for (int i = 0; i < nums.length; i++) {
            result[0] = i;
            int subTarget = target - nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] == subTarget) {
                    result[1] = j;
                    return result;
                }
            }
        }
        return result;
    }
}
