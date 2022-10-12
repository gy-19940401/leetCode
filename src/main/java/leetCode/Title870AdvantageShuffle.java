package leetCode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.IntStream;

/**
 * @Description: 优势洗牌
 * @author: GanYang
 * @Date: 2022/10/8 20:44
 * <p>
 * 给定两个大小相等的数组nums1和nums2，nums1相对于 nums2 的优势可以用满足nums1[i] > nums2[i]的索引 i的数目来描述。
 * 返回 nums1的任意排列，使其相对于 nums2的优势最大化。
 * https://leetcode.cn/problems/advantage-shuffle/
 */
public class Title870AdvantageShuffle {

    public static void main(String[] args) {
        int[] nums1 = new int[]{2, 7, 11, 15};
        int[] nums2 = new int[]{13, 25, 32, 11};
        System.out.println(Arrays.toString(advantageCount(nums1, nums2)));
    }

    public static int[] advantageCount(int[] nums1, int[] nums2) {
        int size = nums1.length;
        int[] result = new int[size];
        // 将 数组 nums1 排序
        // 将 nums2 排序后放到 result 中
        Integer[] index = IntStream.range(0, size).boxed().toArray(Integer[]::new);
        Arrays.sort(nums1);
        //存放的是 排序后的下标
        Arrays.sort(index, Comparator.comparingInt(index2 -> nums2[index2]));
        // head 存放是的 nums2 中 最小元素的的下标 ;  tail 存放的是 nums2 中最大元素的下标
        int head = 0, tail = size - 1;
        for (int target : nums1) {
            if (target > nums2[index[head]]) {
                result[index[head]] = target;
                head++;
            } else {
                result[index[tail]] = target;
                tail--;
            }
        }
        return result;
    }
}
