package leetCode;

/**
 * @description: 数组元素积的符号
 * @author: GanYang
 * @date: 2022/10/27 12:34
 * <p>
 * 已知函数 signFunc(x) 将会根据 x 的正负返回特定值：
 * <p>
 * 如果 x 是正数，返回 1 。
 * 如果 x 是负数，返回 -1 。
 * 如果 x 是等于 0 ，返回 0 。
 * 给你一个整数数组 nums 。令 product 为数组 nums 中所有元素值的乘积。
 * <p>
 * 返回 signFunc(product) 。
 * <p>
 * 链接：https://leetcode.cn/problems/sign-of-the-product-of-an-array
 */
public class Title1822SignOfTheProductOfAnArray {
    public static void main(String[] args) {
        int[] nums = new int[]{-1, 1, -1, 1, -1};
        System.out.println(signFunc(nums));
    }

    public static int signFunc(int[] nums) {
        int result = 0;
        for (int num : nums) {
            if (num < 0) {
                result++;
            } else if (num == 0) {
                return 0;
            }
        }
        return result % 2 == 0 ? 1 : -1;
    }
}