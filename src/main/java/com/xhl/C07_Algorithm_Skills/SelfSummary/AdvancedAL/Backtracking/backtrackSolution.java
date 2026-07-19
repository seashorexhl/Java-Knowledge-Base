package com.xhl.C07_Algorithm_Skills.SelfSummary.AdvancedAL.Backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: xhl
 * @Date: 2026-06-24 18:12
 * @Description: 回朔算法模板 子集与全排列
 *     1.确定递归函数的参数
 *     2.确定终止条件
 *     3.确定单层搜索逻辑
 */
public class backtrackSolution {

    // 1. 结果集：存放所有符合条件的最终答案
    List<List<Integer>> result = new ArrayList<>();
    // 2. 路径集：记录当前正在走的路径（当前做出的选择）
    List<Integer> path = new ArrayList<>();

    static void main() {

    }

    public List<List<Integer>> backtrackExample(int[] nums) {
        // 3. 从起点开始回溯
        backtrack(nums, 0);
        return result;
    }

    /**
     * @param nums      选择列表（所有可选项）
     * @param startIndex 当前从哪个位置开始选（用于避免重复选择，如组合问题）
     */
    private void backtrack(int[] nums, int startIndex) {
        // 4. 终止条件：满足什么条件时，把当前路径加入结果集并返回
        if (true/*满足结束条件*/) {
            result.add(new ArrayList<>(path)); // ⚠️ 注意：必须 add 一份新的拷贝！
            return;
        }

        // 5. 遍历选择列表：横向遍历当前层的所有选择
        for (int i = startIndex; i < nums.length; i++) {

            // 6. 剪枝（可选）：如果当前选择明显不符合要求，直接跳过
            if (false/*不满足条件*/) {
                continue;
            }

            // 7. 做选择：将当前节点加入路径
            path.add(nums[i]);

            // 8. 递归：进入下一层，继续探索（纵向深入）
            backtrack(nums, i + 1);

            // 9. 撤销选择（回溯的核心）：退回上一步，把刚才加的元素移除
            path.remove(path.size() - 1);
        }
    }
}
