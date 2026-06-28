package com.xhl.Career_Growth.SelfSummary.AdvancedAL.PermutationsCombinations;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: xhl
 * @Date: 2026-06-23 09:58
 * @Description: 全排列 一、全排列（无重复元素）
 */
public class permute {
    List<List<Integer>> res = new ArrayList<>();
    List<Integer> path = new ArrayList<>();
    boolean[] used;

    public List<List<Integer>> permute(int[] nums) {
        used = new boolean[nums.length];
        dfs(nums);
        return res;
    }

    void dfs(int[] nums) {
        if (path.size() == nums.length) {
            res.add(new ArrayList<>(path)); // 必须new副本！
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) continue;
            used[i] = true;
            path.add(nums[i]);
            dfs(nums);
            path.remove(path.size() - 1);
            used[i] = false;
        }
    }
}
