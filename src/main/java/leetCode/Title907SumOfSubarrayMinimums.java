package leetCode;

import java.util.Stack;

/**
 * @description: 子数组的最小值之和
 * @author: GanYang
 * @date: 2022/10/28 10:27
 * 给定一个整数数组 arr，找到 min(b)的总和，其中 b 的范围为 arr 的每个（连续）子数组。
 * <p>
 * 由于答案可能很大，因此 返回答案模 10^9 + 7 。
 * <p>
 * 链接：https://leetcode.cn/problems/sum-of-subarray-minimums
 * <p>
 * 输入：arr = [3,1,2,4]
 * 输出：17
 * 解释：
 * 子数组为 [3]，[1]，[2]，[4]，[3,1]，[1,2]，[2,4]，[3,1,2]，[1,2,4]，[3,1,2,4]。
 * 最小值为 3，1，2，4，1，1，2，1，1，1，和为 17。
 */
public class Title907SumOfSubarrayMinimums {
    public static void main(String[] args) {
        int[] arr = new int[]{71, 55, 82, 55};

        System.out.println(sumSubarrayMins(arr));
    }

    /**
     * 考虑所有满足以数组 arr 中的某个元素 arr[i] 为 最右且最小 的元素的子序列个数 C[i]，
     * 那么题目要求 ：求连续子数组的最小值之和即为
     * i=0
     * ∑ arr[i]×C[i] ,其中数组 arr 的长度为 n。
     * n−1
     * 我们必须假设当前元素为最右边且最小的元素，这样才可以构造互不相交的子序列，否则会出现多次计算，因为一个数组的最小值可能不唯一。
     * <p>
     * 经过以上思考，我们只需要找到每个元素 arr[i] 以该元素为 最右且最小 的子序列的数目 left[i]，以及以该元素为 最左且最小 的子序列的数目 right[i]，
     * 则以 arr[i] 为最小元素的子序列的数目合计为 left[i] × right[i]。
     * 当然为了防止重复计算，我们可以设 arr[i] 左边的元素都必须满足 大于等于 arr[i]，arr[i] 右边的元素必须满足严格 大于 arr[i]。
     * 当然这就变成求最小的下标 j≤i，且连续子序列中的元素 arr[j],arr[j+1],⋯,arr[i] 都满足 大于等于 arr[i]，
     * 以及最大的下标 k > i 满足连续子序列 arr[i+1],arr[i+1],⋯,arr[k] 都满足严格大于 arr[i]。
     * 上述即转化为经典的单调栈问题，即求数组中当前元素 xx 左边第一个小于 xx 的元素以及右边第一个小于等于 xx 的元素。
     * 关于「单调栈」的算法细节，可以参考「496. 下一个更大元素 I 题解」。
     * <p>
     * 对于数组中每个元素 arr[i]，具体做法如下：
     * <p>
     * 求左边第一个小于 arr[i] 的元素：从左向右遍历数组，并维护一个单调递增的栈，遍历当前元素 arr[i]，
     * 如果遇到当前栈顶的元素大于等于 arr[i] 则将其弹出，直到栈顶的元素小于arr[i]，栈顶的元素即为左边第一个小于 arr[i] 的元素 arr[j]，此时 left[i]=i−j。
     * <p>
     * 求右边第一个大于等于 arr[i] 的元素：从右向左遍历数组，维护一个单调递增的栈，遍历当前元素 arr[i]，
     * 如果遇到当前栈顶的元素大于 arr[i] 则将其弹出，直到栈顶的元素小于等于 arr[i]，栈顶的元素即为右边第一个小于等于 arr[i] 的元素 ,此时 right[i]=k−i。
     * <p>
     * 连续子数组 arr[j],arr[j+1],⋯,arr[k] 的最小元素即为 arr[i]，以 arr[i] 为最小元素的连续子序列的数量为 (i−j)×(k−i)。
     * <p>
     * 根据以上结论可以知道，所有子数组的最小值之和即为
     * i=0
     * ∑arr[i]×left[i]×right[i]。
     * n−1
     * 维护单调栈的过程线性的，因为只进行了线性次的入栈和出栈。
     *
     * @param arr
     * @return
     */

    private static int sumSubarrayMins(int[] arr) {
        //记录一个栈
        // 遍历整个数组
        // 记录 左侧 比 当前位置元素 严格大的 数量
        // 记录 右侧 比 当前位置元素 严格大的 数量
        int n = arr.length;
        int[] left = new int[n];
        int[] right = new int[n];
        //记录位置
        Stack<Integer> stack = new Stack<Integer>();
        // 查找左边的记录
        for (int i = 0; i < n; i++) {
            //栈非空，并且 当前元素小于 栈顶元素，栈顶元素出栈
            while (!stack.isEmpty() && arr[i] <= arr[stack.peek()]) {
                stack.pop();
            }
            // 栈空 或者 到达 小于当前元素的下标位置
            left[i] = i - (stack.isEmpty() ? -1 : stack.peek());
            stack.push(i);
        }
        //查找右侧的记录
        //清空栈
        stack.clear();
        for (int i = n - 1; i >= 0; i--) {
            //栈非空，并且 当前元素小于 栈顶元素，栈顶元素出栈
            while (!stack.isEmpty() && arr[i] < arr[stack.peek()]) {
                stack.pop();
            }
            //如果 栈空 就表明 全部元素都比当前元素大
            right[i] = (stack.isEmpty() ? n : stack.peek()) - i;
            stack.push(i);
        }
        int result = 0;
        int mod = (int) (Math.pow(10, 9) + 7);
        for (int i = 0; i < n; i++) {
            result = (int) ((result + (long) arr[i] * right[i] * left[i]) % mod);
        }
        return result;
    }

    /**
     * @param arr 数组
     * @return 最新加入的值是 小于 当前 最小 那就是 加 当前 已经存在子数列 个数 * 当前加入的值
     * <p>
     */
    private int sumSubarrayMins(int[][] arr) {
        int result = 0;
//
//        //记录 上次累积的值
//        int lastAdd = 0;
//        // 记录 上次的 子数组个数
//        int subArray = 0;
//        //记录当前最小值
//        int min = Integer.MAX_VALUE;
//
//        for (int i = 0; i < arr.length; i++) {
//            if (min > arr[i]) {
//                // 最新加入的值是 小于 当前 最小
//                // 那就是 加 当前 已经存在子数列 个数 * 当前加入的值
//                result = result + arr[i] + subArray * arr[i];
//                // 迭代最小值
//                lastAdd = arr[i] + subArray * arr[i];
//                min = arr[i];
//                subArray++;
//
//            } else {
//                //最新加入的值是 大于 当前 最小 那就是 累计上一次的值
//                result = result + arr[i] + lastAdd;
//                lastAdd = arr[i] + lastAdd;
//            }
//        }
        return result;
    }
}
