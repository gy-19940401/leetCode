package leetCode;

/**
 * @Description: 最多能完成排序的块
 * @author: GanYang
 * @Date: 2022/10/13 9:47
 * 给定一个长度为 n 的整数数组 arr ，它表示在 [0, n - 1] 范围内的整数的排列。
 * 我们将 arr 分割成若干 块 (即分区)，并对每个块单独排序。将它们连接起来后，使得连接的结果和按升序排序后的原数组相同。
 * 返回数组能分成的最多块数量。{
 * ---- n == arr.length
 * ---- 1 <= n <= 10
 * ---- 0 <= arr[i] < n
 * ---- arr 中每个元素都 不同
 * }
 * <p>
 * https://leetcode.cn/problems/max-chunks-to-make-sorted/
 */
public class Title769MaxChunksToMakeSorted {
    public static void main(String[] args) {
        int[] arr = new int[]{1, 0, 2, 3};
        System.out.println(maxChunksToSorted(arr));
    }

    /**
     * @param arr 数组
     * @return int
     * @show
     * @author Ganyang
     * @data 9:45
     * 贪心算法 最好的情况是 当前数组本身有序 能够将 数组的每一个 元素分成的 独立的块
     * 实际的情况是 数组本身不是有序的{
     * ---- 比较 上一次记录的最大值 与
     * }
     */
    public static int maxChunksToSorted(int[] arr) {
        //记录当前位置之前的最大值
        int lastMax = 0;
        int result = 0;
        for (int i = 0; i < arr.length; i++) {
            lastMax = Math.max(lastMax, arr[i]);
            if (i == lastMax) {
                result++;
            }
        }
        return result;
    }
}
