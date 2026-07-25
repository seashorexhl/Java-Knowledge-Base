package com.xhl.C07_Algorithm_Skills.LeetCode.LeetCode100.DP;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * @Author: xhl
 * @Date: 2026-06-14 03:36
 * @Description: 最大正方形
 */
/*采用动态规划 和 降维转化 + 单调栈 的方式 计算 最大正方形和 最大矩形 */
public class maximalSquareSolution {
    public static void main(String[] args) {
        char[][] matrix = {
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}
        };
        maximalSquareSolution mss = new maximalSquareSolution();
        System.out.println("最大的正方形：");
        System.out.println(mss.maximalSquare(matrix));

        System.out.println("最大的长方形：");
        System.out.println(mss.maximalRectangle(matrix));

    }
    /**
     *  方法一、暴力法
     * */
    public int maximalSquare(char[][] matrix) {
        int maxSide = 0;
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return maxSide;
        }
        int rows = matrix.length, columns = matrix[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (matrix[i][j] == '1') {
                    // 遇到一个 1 作为正方形的左上角
                    maxSide = Math.max(maxSide, 1);
                    // 计算可能的最大正方形边长
                    int currentMaxSide = Math.min(rows - i, columns - j);
                    for (int k = 1; k < currentMaxSide; k++) {
                        // 判断新增的一行一列是否均为 1
                        boolean flag = true;
                        if (matrix[i + k][j + k] == '0') {
                            break;
                        }
                        for (int m = 0; m < k; m++) {
                            if (matrix[i + k][j + m] == '0' || matrix[i + m][j + k] == '0') {
                                flag = false;
                                break;
                            }
                        }
                        if (flag) {
                            maxSide = Math.max(maxSide, k + 1);
                        } else {
                            break;
                        }
                    }
                }
            }
        }
        int maxSquare = maxSide * maxSide;
        return maxSquare;

    }
    /**
     *  动态规划法
     * */
    public int maximalSquare1(char[][] matrix) {
        int maxSide = 0;
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return maxSide;
        }

        int rows = matrix.length, columns = matrix[0].length;
        int[][] dp = new int[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (matrix[i][j] == '1') {
                    if (i == 0 || j == 0) {
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                    }
                    maxSide = Math.max(maxSide, dp[i][j]);
                }
            }
        }
        int maxSquare = maxSide * maxSide;
        return maxSquare;
    }
    /**扩展：最大矩形？  降维转化 + 单调栈 */
    /*核心思路解析
        构建高度数组（降维）：
        遍历矩阵的每一行，维护一个高度数组 heights。对于每一列 j，如果当前元素 matrix[i][j] == '1'，
        则高度加 1；如果是 '0'，则高度重置为 0。这样，每一行都会生成一个直方图（柱状图）。
        利用单调栈求解：
        对每一行生成的 heights 数组，调用“柱状图中最大矩形”的单调栈解法，计算当前行的最大矩形面积，
        并不断更新全局最大面积。*/
    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        int rows = matrix.length, cols = matrix[0].length;
        int[] heights = new int[cols];
        int maxArea = 0;

        // 逐行遍历矩阵
        for (int i = 0; i < rows; i++) {
            // 1. 更新当前行的高度数组
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == '1') {
                    heights[j] += 1;
                } else {
                    heights[j] = 0; // 遇到 '0' 必须切断，高度重置为 0
                }
            }

            // 2. 对当前行的高度数组使用单调栈求最大矩形面积
            maxArea = Math.max(maxArea, largestRectangleArea(heights));
        }
        return maxArea;
    }

    /**
     *  柱状图中最大的矩形（LeetCode 84 单调栈+哨兵解法）
     * */
    private int largestRectangleArea(int[] heights) {
        int maxArea = 0;
        Deque<Integer> stack = new ArrayDeque<>();

        // 在数组末尾添加 0 作为哨兵，确保所有柱子都能出栈计算
        int[] newHeights = Arrays.copyOf(heights, heights.length + 1);
        newHeights[heights.length] = 0;

        for (int i = 0; i < newHeights.length; i++) {
            // 当当前高度小于栈顶高度时，说明找到了栈顶元素的右边界
            while (!stack.isEmpty() && newHeights[i] < newHeights[stack.peek()]) {
                int h = newHeights[stack.pop()];
                int leftIdx = stack.isEmpty() ? -1 : stack.peek();
                int w = i - leftIdx - 1;
                maxArea = Math.max(maxArea, h * w);
            }
            stack.push(i);
        }
        return maxArea;
    }

}
