package com.xhl.C06_Algorithm_Skills.SelfSummary.BaseDS.Collection.Map.HashMap;

import java.util.LinkedHashMap;

/**
 * @Author: xhl
 * @Date: 2026-06-05 01:28
 * @Description: LinkedHashMapLifeStyle 有序哈希表
 */
public class LinkedHashMapLifeStyle {
    public static void main(String[] args) {
        //双机位A卷
        //恢复数字序列
        LinkedHashMap<Integer,Integer> map = new LinkedHashMap<>();
        map.putFirst(1,2);
        map.put(8,9);
        System.out.println(map.getOrDefault(1,2));
        System.out.println(map.get(8));
    }
}
