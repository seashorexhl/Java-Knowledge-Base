package com.xhl.Career_Growth.SelfSummary.AdvancedAL.PermutationsCombinations;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: xhl
 * @Date: 2026-06-23 10:00
 * @Description:  四、子集（所有子集）
 * 适用场景：返回数组所有可能的子集（包括空集）。每个元素选/不选两种状态。
 */
public class subsets {
    List<List<Integer>> res = new ArrayList<>();
    List<Integer> path = new ArrayList<>();

    public List<List<Integer>> subsets(int[] nums) {
        dfs(0, nums);
        return res;
    }

    void dfs(int start, int[] nums) {
        res.add(new ArrayList<>(path)); // 每个节点都加入结果
        for (int i = start; i < nums.length; i++) {
            path.add(nums[i]);
            dfs(i + 1, nums);
            path.remove(path.size() - 1);
        }
    }
}
