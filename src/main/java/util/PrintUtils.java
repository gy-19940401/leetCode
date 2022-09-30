package util;

/**
 * @Description: 输出打印工具
 * @author: GanYang
 * @Date: 2022/9/30 9:31
 */
public class PrintUtils {

    /**
     * 打印二维数组
     *
     * @param matrix 二维数组
     */
    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println("");
        }
    }
}
