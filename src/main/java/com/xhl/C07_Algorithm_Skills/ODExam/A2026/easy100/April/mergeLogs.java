package com.xhl.C07_Algorithm_Skills.ODExam.A2026.easy100.April;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: xhl
 * @Date: 2026-07-14 11:56
 * @Description: API请求日志去重分析 100分
 *  双指针  + 单次遍历分组统计
 *  将连续相同的日志路径进行合并，并计算该路径下的平均响应时间。
 */
public class mergeLogs {
    static void main(String[] args) {
        // 测试用例
        String[] paths = {"A", "A", "B", "B", "B", "C"};
        int[] responseTimes = {100, 200, 150, 150, 150, 300};
        mergeLogs ml = new mergeLogs();
        int[][] result = ml.mergeLogs(paths, responseTimes);

        // 打印验证
        for (int[] row : result) {
            System.out.println("Start: " + row[0] + ", Count: " + row[1] + ", Avg: " + row[2]);
        }

    }
    /**
     *  滑动窗口 + 字符串处理
     * */
    public int[][] mergeLogs(String[] paths, int[] responseTimes) {
        List<int[]> result = new ArrayList<>();
        if (paths.length == 0) return new int[0][3];

        int start = 0;
        String currentPath = paths[0];
        int sum = responseTimes[0], count = 1;

        for (int i = 1; i < paths.length; i++) {
            if (paths[i].equals(currentPath)) {
                sum += responseTimes[i];
                count++;
            } else {
                result.add(new int[]{start, count, sum / count});
                start = i;
                currentPath = paths[i];
                sum = responseTimes[i];
                count = 1;
            }
        }
        result.add(new int[]{start, count, sum / count});
        return result.toArray(new int[0][]);
    }
}
