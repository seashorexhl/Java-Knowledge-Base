package com.xhl.C07_Algorithm_Skills.SelfSummary.AdvancedAL.PermutationsCombinations;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: xhl
 * @Date: 2026-06-23 09:59
 * @Description: 三、组合（从n个数中选k个）
 *
 */
public class combine {
    List<List<Integer>> res = new ArrayList<>();
    List<Integer> path = new ArrayList<>();

    public List<List<Integer>> combine(int n, int k) {
        dfs(1, n, k); // 从1开始选
        return res;
    }

    void dfs(int start, int n, int k) {
        if (path.size() == k) {
            res.add(new ArrayList<>(path));
            return;
        }
        // 剪枝优化：剩余可选数不够时提前停止
        for (int i = start; i <= n - (k - path.size()) + 1; i++) {
            path.add(i);
            dfs(i + 1, n, k);
            path.remove(path.size() - 1);
        }
    }
}
