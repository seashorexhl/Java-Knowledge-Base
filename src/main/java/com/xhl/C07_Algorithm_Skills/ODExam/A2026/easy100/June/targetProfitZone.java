package com.xhl.C07_Algorithm_Skills.ODExam.A2026.easy100.June;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: xhl
 * @Date: 2026-07-10 05:07
 * @Description: 统计盈利目标区间-100分
 *  前缀和  + 哈希表
 */
public class targetProfitZone {
    static void main() {
        String[] ops = {"add","add","query","add","query"};
        int[] vals = {1,2,3,3,6};
        targetProfitZone tpz = new targetProfitZone();
        List<Integer> list = tpz.targetZone(ops, vals);
        System.out.println(list.toString());
    }
    // 前缀和  + 哈希表
    public List<Integer> targetZone(String[] ops, int[] vals) {
        long prefix = 0;
        Map<Long, Integer> map = new HashMap<>();
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < ops.length; i++) {
            if ("add".equals(ops[i])) {
                map.put(prefix, map.getOrDefault(prefix, 0) + 1);
                prefix += vals[i];
            } else if ("query".equals(ops[i])) {
                long key = prefix - vals[i];
                res.add(map.getOrDefault(key, 0));
            }
        }
        return res;

    }
}
