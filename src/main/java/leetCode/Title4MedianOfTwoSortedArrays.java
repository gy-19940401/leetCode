package leetCode;

/**
 * @Description: 寻找两个正序数组的中位数
 * @author: GanYang
 * @Date: 2022/9/29 14:01
 * explain : 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
 * 算法的时间复杂度应该为 O(log (m+n)) 。
 * <p>
 * https://leetcode.cn/problems/median-of-two-sorted-arrays/
 */
public class Title4MedianOfTwoSortedArrays {

    public static void main(String[] args) {
        int[] nums1 = new int[]{2, 7, 11, 15};
        int[] nums2 = new int[]{2, 7, 11, 15};
        System.out.println(findMedianSortedArrays(nums1, nums2));
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int total = nums1.length + nums2.length;
        int mid = total / 2;
        int[] tmp = new int[total];
        int index1 = 0, index2 = 0;
        int index = 0;
        while (nums1.length > index1 && nums2.length > index2) {
            if (nums1[index1] > nums2[index2]) {
                tmp[index++] = nums2[index2++];
            } else {
                tmp[index++] = nums1[index1++];
            }
        }
        if (index1 >= nums1.length) {
            while (index2 < nums2.length) {
                tmp[index++] = nums2[index2++];
            }
        } else if (index2 >= nums2.length) {
            while (index1 < nums1.length) {
                tmp[index++] = nums1[index1++];
            }
        }
        if (total % 2 == 0) {
            //总长度是双数
            return (tmp[mid - 1] + tmp[mid]) / 2.0;
        } else {
            //总长度是单数
            return tmp[mid];
        }
    }
}
