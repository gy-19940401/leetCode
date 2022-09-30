package face;

/**
 * @Description: 峰与谷
 * @author: GanYang
 * @Date: 2022/9/30 12:49
 * <p>
 * 在一个整数数组中，“峰”是大于或等于相邻整数的元素，相应地，“谷”是小于或等于相邻整数的元素。
 * 例如，在数组{5, 8, 4, 2, 3, 4, 6}中，{8, 6}是峰， {5, 2}是谷。
 * 现在给定一个整数数组，将该数组按峰与谷的交替顺序排序。
 * <p>
 * https://leetcode.cn/problems/peaks-and-valleys-lcci/
 */
public class Face221011PeaksAndValleysLcci {
    public static void main(String[] args) {
        int[] nums = new int[]{3, 5, 2, 1, 6, 4};
        //                    [3, 5, 1, 6, 2, 4]
        wiggleSort(nums);
    }

    public static void wiggleSort(int[] nums) {


    }
}
