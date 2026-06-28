package com.xhl.Career_Growth.SelfSummary.AdvancedAL.Backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: xhl
 * @Date: 2026-06-24 18:26
 * @Description:    123 的全排列
 *
 */
public class GetSubsets {
    List<List<Integer>> result = new ArrayList<>();
    List<Integer> path = new ArrayList<>();

    static void main() {
        int[] nums = {1,2,3};
        GetSubsets gs = new GetSubsets();
        for (List<Integer> subset : gs.subsets(nums)) {
            System.out.print(Arrays.toString(subset.toArray()));
        }

    }

    public List<List<Integer>> subsets(int[] nums) {
        backtrack(nums, 0);
        return result;
    }

    private void backtrack(int[] nums, int startIndex) {
        // 终止条件：其实子集问题不需要特定的终止条件
        // 因为进入函数的每一条路径（包括空集）都是一个合法的子集
        result.add(new ArrayList<>(path));

        for (int i = startIndex; i < nums.length; i++) {
            path.add(nums[i]);          // 做选择
            backtrack(nums, i + 1);     // 递归
            path.remove(path.size() - 1); // 撤销选择
        }
    }
}
