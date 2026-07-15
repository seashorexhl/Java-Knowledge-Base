package com.xhl.C07_Algorithm_Skills.SelfSummary.BaseDS.Heap.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: xhl
 * @Date: 2026-07-09 23:48
 * @Description: LCP 32. 批量处理任务 端口流量统计
 */
public class processTasks {
    static void main(String[] args) {
        int[][] tasks = {{1,3,2},{2,5,3},{5,6,2}};
        processTasks pt = new processTasks();
        System.out.println(pt.processTasks(tasks));
    }
    // 贪心+栈优化
    public int processTasks(int[][] tasks) {
        Arrays.sort(tasks, (a, b) -> a[1] - b[1]);
        int ans = 0;
        var run = new boolean[tasks[tasks.length - 1][1] + 1];
        for (var t : tasks) {
            int start = t[0], end = t[1], d = t[2];
            for (int i = start; i <= end; ++i)
                if (run[i]) --d; // 去掉运行中的时间点
            for (int i = end; d > 0; --i) // 剩余的 d 填充区间后缀
                if (!run[i]) {
                    run[i] = true;
                    --d;
                    ++ans;
                }
        }
        return ans;

    }
    // 方法二： 二分查找
    public int processTasks1(int[][] tasks) {
        Arrays.sort(tasks, (a, b) -> a[1] - b[1]);
        var st = new ArrayList<int[]>();
        st.add(new int[]{-2, -2, 0}); // 闭区间左右端点，栈底到栈顶的区间长度的和
        for (var t : tasks) {
            int start = t[0], end = t[1], d = t[2];
            var e = st.get(lowerBound(st, start) - 1);
            d -= st.get(st.size() - 1)[2] - e[2]; // 去掉运行中的时间点
            if (start <= e[1]) // start 在区间 st[i] 内
                d -= e[1] - start + 1; // 去掉运行中的时间点
            if (d <= 0) continue;
            while (end - st.get(st.size() - 1)[1] <= d) { // 剩余的 d 填充区间后缀
                e = st.remove(st.size() - 1);
                d += e[1] - e[0] + 1; // 合并区间
            }
            st.add(new int[]{end - d + 1, end, st.get(st.size() - 1)[2] + d});
        }
        return st.get(st.size() - 1)[2];
    }

    // 开区间写法
    private int lowerBound(List<int[]> st, int target) {
        int left = -1, right = st.size(); // 开区间 (left, right)
        while (left + 1 < right) { // 区间不为空
            // 循环不变量：
            // st[left] < target
            // st[right] >= target
            int mid = (left + right) >>> 1;
            if (st.get(mid)[0] < target)
                left = mid; // 范围缩小到 (mid, right)
            else
                right = mid; // 范围缩小到 (left, mid)
        }
        return right; // 或者 left+1
    }

}
