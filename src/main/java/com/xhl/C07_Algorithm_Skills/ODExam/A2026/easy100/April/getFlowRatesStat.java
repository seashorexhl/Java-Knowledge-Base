package com.xhl.C07_Algorithm_Skills.ODExam.A2026.easy100.April;

import java.util.Arrays;
import java.util.Stack;

/**
 * @Author: xhl
 * @Date: 2026-07-10 00:54
 * @Description: 端口流量统计  100分
 */
public class getFlowRatesStat {
    static void main() {
        int[] portRates = {7, 8, 9, 10};
        getFlowRatesStat gf = new getFlowRatesStat();
        int[] ratesStat = gf.getRatesStat(portRates);
        System.out.println(Arrays.toString(ratesStat));
    }

    /**
     * 单调栈
     * 关键点说明
     * 单调栈性质：确保栈中索引对应的元素值严格递增（栈顶最小），快速定位下一个更大元素。
     * 倒序遍历：保证每个元素处理时，栈中存储其右侧所有候选解。
     * 边界处理：栈为空时直接返回0。
     * 复杂度优化：线性时间完成计算，适用于大数据规模。
     */
    public int[] getRatesStat(int[] portRates) {
        Stack<Integer> st = new Stack<>();
        int n = portRates.length;
        int[] res = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            while (!st.isEmpty() && portRates[st.peek()] <= portRates[i]) {
                st.pop();
            }
            if (!st.isEmpty()) {
                res[i] = st.peek() - i;
            }
            st.push(i);
        }
        return res;
    }


}