package com.xhl.C06_Algorithm_Skills.SelfSummary.BaseAL.Greedy;

import java.util.Arrays;

/**
 * @Author: xhl
 * @Date: 2026-06-10 01:04
 * @Description: 贪心算法 模板
 *  局部最优选择”和“状态更新
 */
public class GreedySolution {

    public int solve(int[] data) {
        // 第一步：按照贪心策略对数据进行预处理（通常是排序）
        Arrays.sort(data);  // 或者使用 Collections.sort() / 自定义排序规则

        // 第二步：初始化结果变量与当前状态
        int result = 0;
        int currentState = 0;

        // 第三步：遍历数据，依次做出贪心选择
        for (int i = 0; i < data.length; i++) {
            // 判断当前元素是否满足选择的条件
            if (canSelect(data[i], currentState)) {
                // 做出贪心选择并累加结果
                result += data[i];
                // 更新当前状态（为下一步决策做准备）
                currentState = updateState(currentState, data[i]);
            }
        }

        // 第四步：返回最终结果
        return result;
    }

    // 辅助方法：判断当前元素是否可以被选中
    private boolean canSelect(int value, int state) {
        // 根据具体题目编写判断逻辑
        return true;
    }

    // 辅助方法：更新当前状态
    private int updateState(int state, int value) {
        // 根据具体题目编写状态转移逻辑
        return state + value;
    }
}
