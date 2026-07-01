package com.xhl.C06_Algorithm_Skills.SelfSummary.BaseDS.Collection.Set.HashSet;

import java.util.TreeSet;

/**
 * @Author: xhl
 * @Date: 2026-06-09 00:33
 * @Description: 底层基于红黑树（TreeMap）实现
 */
public class TreeSetSolution {
    static void main() {
        // 存储 Integer（默认升序）
        TreeSet<Integer> numbers = new TreeSet<>();
        numbers.add(5);
        numbers.add(2);
        numbers.add(3);
        System.out.println(numbers); // 输出：<websource>source_group_web_5</websource>

        // 获取极值
        int min = numbers.first(); // 2
        int max = numbers.last();  // 5

    }



}

