package leetCode;

/**
 * @Description: 最短的桥
 * @author: GanYang
 * @Date: 2022/10/25 12:45
 * <p>
 * 给你一个大小为 n x n 的二元矩阵 grid ，其中 1 表示陆地，0 表示水域。
 * 岛 是由四面相连的 1 形成的一个最大组，即不会与非组内的任何其他 1 相连。grid 中 恰好存在两座岛 。
 * <p>
 * 你可以将任意数量的 0 变为 1 ，以使两座岛连接起来，变成 一座岛 。
 * 返回必须翻转的 0 的最小数目。
 * <p>
 * https://leetcode.cn/problems/shortest-bridge/
 */
public class Title934ShortestBridge {
    public static void main(String[] args) {

        int[][] grid = new int[][]{
                {1, 1, 1, 1, 1},
                {1, 0, 0, 0, 1},
                {1, 0, 1, 0, 1},
                {1, 0, 0, 0, 1},
                {1, 1, 1, 1, 1}};
        System.out.println(shortestBridge(grid));
    }

    public static int shortestBridge(int[][] grid) {
        int result = 0;


        return result;
    }
}
