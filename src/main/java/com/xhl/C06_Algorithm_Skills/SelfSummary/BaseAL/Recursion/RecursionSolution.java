package com.xhl.C06_Algorithm_Skills.SelfSummary.BaseAL.Recursion;

/**
 * @Author: xhl
 * @Date: 2026-06-10 01:09
 * @Description: 1.递归算法模板
 *  “终止条件”、“问题分解”和“结果处理”
 */
/** 可广泛应用于数学计算（如阶乘、斐波那契数列）、树形结构的遍历（如二叉树的前序/中序/后序遍历）、
 * 分治算法（如归并排序、快速排序）以及回溯算法（如全排列、迷宫寻路）等场景。*/
public class RecursionSolution {

    // 递归函数模板
    public ReturnType recursion(Parameters params) {
        // 第一步：明确终止条件（Base Case）
        // 这是防止无限递归导致栈溢出（StackOverflowError）的关键防线
        if (baseCaseCondition(params)) {
            ReturnType baseCaseResult = null;
            return baseCaseResult;
        }
        Parameters simplifiedParams = null;
        // 第二步：问题分解与递推阶段（Recursive Case）
        // 将复杂的大问题拆解为规模更小的同类子问题，并调用自身求解
        ReturnType subResult = recursion(simplifiedParams);

        // 第三步：结果处理与回归阶段
        // 基于子问题的解，构建当前层级问题的最终解并返回
        return processResult(subResult, params);
    }

    // 辅助方法：判断是否满足终止条件
    private boolean baseCaseCondition(Parameters params) {
        // 根据具体题目编写边界判断逻辑
        return false;
    }

    // 辅助方法：合并或处理子问题的结果
    private ReturnType processResult(ReturnType subResult, Parameters params) {
        // 根据具体题目编写状态转移或结果合并逻辑
        return null;
    }
}
