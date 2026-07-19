package com.xhl.C07_Algorithm_Skills.SelfSummary.BaseAL.BinarySearch.middle;

/**
 * @Author: xhl
 * @Date: 2026-07-02 15:55
 * @Description:    74. 搜索二维矩阵
 *  二分查找 + 数学映射
 */
public class searchMatrix {
    static void main() {
        int[][] matrix = {{1,3,5,7},{10,11,16,20},{23,30,34,60}};
        int target = 3;

        searchMatrix sm = new searchMatrix();
        boolean b = sm.searchMatrix(matrix, target);
        System.out.println("方法一：target 是否在矩阵中？" + b + "\n");
        boolean searched = sm.searchMatrix1(matrix, target);
        System.out.println("方法二：target 是否在矩阵中？"+searched);
    }
    /**
     *  方法一：两次二分查找
     * */
    public boolean searchMatrix(int[][] matrix, int target) {
        int rowIndex = binarySearchFirstColumn(matrix, target);
        if (rowIndex < 0) {
            return false;
        }
        return binarySearchRow(matrix[rowIndex], target);
    }
    //
    public int binarySearchFirstColumn(int[][] matrix, int target) {
        int low = -1, high = matrix.length - 1;
        while (low < high) {
            int mid = (high - low + 1) / 2 + low;
            if (matrix[mid][0] <= target) {
                low = mid;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }
    //
    public boolean binarySearchRow(int[] row, int target) {
        int low = 0, high = row.length - 1;
        while (low <= high) {
            int mid = (high - low) / 2 + low;
            if (row[mid] == target) {
                return true;
            } else if (row[mid] > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return false;
    }

    /**
     *  方法二：一次二分查找
     *  二分升序数组的下标，将其映射到原矩阵的行和列上。
     * */
    public boolean searchMatrix1(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;
        int m = matrix.length, n = matrix[0].length;
        int low = 0, high = m * n - 1;
        while (low <= high) {
            int mid = (high - low) / 2 + low;
            // 数学映射
            int x = matrix[mid / n][mid % n];
            if (x < target) {
                low = mid + 1;
            } else if (x > target) {
                high = mid - 1;
            } else {
                return true;
            }
        }
        return false;
    }
}
