package com.xhl.C06_Algorithm_Skills.SelfSummary.AdvancedAL.PermutationsCombinations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: xhl
 * @Date: 2026-06-23 09:58
 * @Description: 二、全排列（有重复元素 → 去重）
 */
public class permuteUnique {
    List<List<Integer>> res = new ArrayList<>();
    List<Integer> path = new ArrayList<>();
    boolean[] used;

    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums); // 必须先排序！
        used = new boolean[nums.length];
        dfs(nums);
        return res;
    }

    void dfs(int[] nums) {
        if (path.size() == nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            // 去重核心剪枝
            if (used[i]) continue;
            if (i > 0 && nums[i] == nums[i-1] && !used[i-1]) continue;

            used[i] = true;
            path.add(nums[i]);
            dfs(nums);
            path.remove(path.size() - 1);
            used[i] = false;
        }
    }
}
