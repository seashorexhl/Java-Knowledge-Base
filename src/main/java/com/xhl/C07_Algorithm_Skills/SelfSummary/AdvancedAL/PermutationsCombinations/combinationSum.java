package com.xhl.C07_Algorithm_Skills.SelfSummary.AdvancedAL.PermutationsCombinations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: xhl
 * @Date: 2026-06-23 10:00
 * @Description: 五、组合总和（可重复选取）
 */
public class combinationSum {
    List<List<Integer>> res = new ArrayList<>();
    List<Integer> path = new ArrayList<>();

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates); // 排序便于剪枝
        dfs(0, candidates, target);
        return res;
    }
    // DFS 深度优先遍历
    void dfs(int start, int[] candidates, int target) {
        if (target == 0) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            if (candidates[i] > target) break; // 剪枝
            path.add(candidates[i]);
            dfs(i, candidates, target - candidates[i]); // 注意是 i，不是 i+1（可重复）
            path.remove(path.size() - 1);
        }
    }
}
