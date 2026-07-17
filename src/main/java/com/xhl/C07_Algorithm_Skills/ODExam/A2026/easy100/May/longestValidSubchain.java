package com.xhl.C07_Algorithm_Skills.ODExam.A2026.easy100.May;

/**
 * @Author: xhl
 * @Date: 2026-07-10 03:08
 * @Description: Skill执行链完整性检测-100分
 *  在给定的一个技能类型数组中，找到最长的、符合特定规则的连续子数组的长度。
 *  规则总结:
 * 子链必须以基础类型（0）开头
 * 扩展类型（1）依赖前一个技能必须是基础类型（0）
 * 高级类型（2）依赖前两个技能必须是基础类型（0）
 * 相邻基础类型间最多只能有一个非基础类型（1或2）
 */
public class longestValidSubchain {
    static void main(String[] args) {
        int[] type = {0,1,0,1};
        longestValidSubchain sol = new longestValidSubchain();
        int longested = sol.longestValidSubchain(type);
        System.out.println(longested);
    }
    /**
     * 动态规划
     * */
    public int longestValidSubchain(int[] type) {
        //1. 初始化和边界处理
        int n = type.length;
        if (n == 0) return 0;
        //定义 dp,以 type[i] 这个技能结尾的、最长的合法子链的长度。
        int[] dp = new int[n];
        dp[0] = type[0] == 0 ? 1 : 0;
        int maxLen = dp[0];
        //2. 动态规划主循环
        for (int i = 1; i < n; i++) {
            //3. 状态转移详解
            /*情况一：当前技能是基础类型 (type[i] == 0)*/
            if (type[i] == 0) {
                dp[i] = 1;   // 最坏情况：自己单独成为一个合法子链，长度为1
                // 情况1: 前一个是基础类型(0)。可以直接连接。例如: [..., 0] -> [..., 0, 0]
                if (type[i-1] == 0) dp[i] = Math.max(dp[i], dp[i-1] + 1);
                // 情况2: 前一个是扩展类型(1)，再前一个是基础类型(0)。可以连接。例如: [..., 0, 1] -> [..., 0, 1, 0]
                if (i >= 1 && type[i-1] == 1 && i-2 >= 0 && type[i-2] == 0)
                    dp[i] = Math.max(dp[i], dp[i-2] + 2);
                // 情况3: 前一个是高级类型(2)，再前两个是基础类型(0)。可以连接。例如: [..., 0, 0, 2] -> [..., 0, 0, 2, 0]
                if (i >= 1 && type[i-1] == 2 && i-3 >= 0 && type[i-2] == 0 && type[i-3] == 0)
                    dp[i] = Math.max(dp[i], dp[i-3] + 3);
            /*情况二：当前技能是扩展类型 (type[i] == 1)*/
            } else if (type[i] == 1) {
                if (type[i-1] == 0) dp[i] = dp[i-1] + 1;
            //情况三：当前技能是高级类型 (type[i] == 2)
            } else if (type[i] == 2) {
                if (i >= 2 && type[i-1] == 0 && type[i-2] == 0){
                    dp[i] = dp[i-2] + 2;
                }
            }
            maxLen = Math.max(maxLen, dp[i]);
        }
        return maxLen;
    }

}
