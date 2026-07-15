package com.xhl.C07_Algorithm_Skills.SelfSummary.BaseDS.Collection.Set.HashSet;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * @Author: xhl
 * @Date: 2026-06-03 04:11
 * @Description: 哈希集合 生命周期 及常用 API
 */
public class HashSetLifeStyle {
    public static void main(String[] args) {
        //新建 一个 存储 String 的 hash 集合
        Set<String> set = new HashSet<>();
        /*常用方法*/
        // 增删改查
        set.add("a");
        set.add("b");
        set.add("c");
        set.add("d");
        System.out.println(set.size());
        System.out.println("判断元素是否存在于 哈希集合中："+set.contains("d"));
        System.out.println(set.remove("c"));
        set.add("c");
        // 遍历
        Iterator<String> iterator = set.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
