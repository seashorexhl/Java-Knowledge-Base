package com.xhl.C07_Algorithm_Skills.SelfSummary.AdvancedAL.DP.multiyDP.middle;

/**
 * @Author: xhl
 * @Date: 2026-07-02 02:05
 * @Description:    72. 编辑距离
 *  将 word1 转换成 word2 所使用的最少操作数
 *  插入 删除 替换
 */
public class minDistance {
    static void main() {
        String word1 = "intention";
        String word2 = "execution";
        minDistance md = new minDistance();
        int distance = md.minDistance(word1, word2);
        System.out.println("所使用的最少操作数:" + distance);
    }
    /**
     * 方法一：递归
     */

    // 大量的重复计算 会超时
    public int minDistance(String word1, String word2) {
        // 定义递归出口
        if(word1.length() == 0 || word2.length() == 0){
            return Math.max(word1.length(),word2.length());
        }

        if(word1.charAt(word1.length() - 1) == word2.charAt(word2.length() - 1)){
            return  minDistance(word1.substring(0,word1.length()-1),word2.substring(0,word2.length()-1));

        }
        // 三个操作
        return 1 + Math.min(minDistance(word1,word2.substring(0,word2.length()-1)),
                Math.min(minDistance(word1.substring(0,word1.length()-1),word2),
                minDistance(word1.substring(0,word1.length()-1),word2.substring(0,word2.length()-1))
                ));

    }
    /**
     *  方法二：动态规划
     */
    // 从状态转移 抽象出 状态转移方程
    public int minDistance1(String word1, String word2) {
            int n = word1.length();
            int m = word2.length();

            // 有一个字符串为空串
            if (n * m == 0) {
                return n + m;
            }

            // DP 数组
            int[][] D = new int[n + 1][m + 1];

            // 边界状态初始化
            for (int i = 0; i < n + 1; i++) {
                D[i][0] = i;
            }
            for (int j = 0; j < m + 1; j++) {
                D[0][j] = j;
            }

            // 计算所有 DP 值
            for (int i = 1; i < n + 1; i++) {
                for (int j = 1; j < m + 1; j++) {
                    int left = D[i - 1][j] + 1;
                    int down = D[i][j - 1] + 1;
                    int left_down = D[i - 1][j - 1];
                    if (word1.charAt(i - 1) != word2.charAt(j - 1)) {
                        left_down += 1;
                    }
                    D[i][j] = Math.min(left, Math.min(down, left_down));
                }
            }
            return D[n][m];
    }
}
