package com.xhl.C07_Algorithm_Skills.SelfSummary.AdvancedAL.PermutationsCombinations;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: xhl
 * @Date: 2026-06-23 09:56
 * @Description: backtrack 排列组合 模板
 * 核心在于掌握DFS回溯模板和去重剪枝
 */
public class PCSolution {
    static void main() {

    }

    // 组合模板（最通用）
    void backtrack(int start, int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();

        List<Integer> path = new ArrayList<>();
        if (path.isEmpty()/* 满足条件 */) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = start; i < nums.length; i++) {
            // 去重剪枝（有重复元素时）
            if (i > start && nums[i] == nums[i-1]) continue;
            // 可行性剪枝
            if (nums[i] > target) break;

            path.add(nums[i]);
            backtrack(i/* 可重复用i，不可重复用i+1 */, nums, target - nums[i]);
            path.remove(path.size() - 1);
        }
    }
}
