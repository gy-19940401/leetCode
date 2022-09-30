package daily;

import util.PrintUtils;

/**
 * @Description: 零矩阵
 * @author: GanYang
 * @Date: 2022/9/30 9:27
 * <p>
 * 若M × N矩阵中某个元素为0，则将其所在的行与列清零。
 */
public class Day221030ZeroMatrixLcci {
    public static void main(String[] args) {
        int[][] matrix = new int[][]{{0, 1, 2, 0}, {3, 4, 5, 2}, {1, 3, 1, 5}};
        PrintUtils.printMatrix(matrix);
        setZeroes(matrix);

    }

    public static void setZeroes(int[][] matrix) {
        //记录列的被修改的
        // 记录的是 行数
        int[] changeIndexRow = new int[matrix.length];
        // 记录的是列数
        int[] changeIndexColumn = new int[matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 0) {
                    changeIndexRow[i] = 1;
                    changeIndexColumn[j] = 1;
                }
            }
        }

        //遍历行数中存在 0 的 行 将 整行 改为 0 
        for (int i = 0; i < changeIndexRow.length; i++) {
            if (changeIndexRow[i] == 1) {
                //将 整行 改为0 遍历列
                for (int j = 0; j < matrix[0].length; j++) {
                    matrix[i][j] = 0;
                }
            }
        }

        //遍历行数中存在 0 的 列 将 整列 改为 0 
        for (int i = 0; i < changeIndexColumn.length; i++) {
            if (changeIndexColumn[i] == 1) {
                //将 整列 改为0 遍历行
                for (int j = 0; j < matrix.length; j++) {
                    matrix[j][i] = 0;
                }
            }
        }

        PrintUtils.printMatrix(matrix);
    }
}
