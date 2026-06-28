package com.xhl.Career_Growth.SelfSummary.BaseAL.Sort;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: xhl
 * @Date: 2026-06-08 23:51
 * @Description: 排序 与 打印
 */
public class Sort {
    public static void main(String[] args) {
        /*1. 数组 (Array) 排序 打印*/
        // 基本类型数组（默认升序）
        int[] arr = {5, 2, 8, 1};
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));

        // 对象数组（按字符串长度降序排序）
        String[] strs = {"banana", "apple", "pear"};
        Arrays.sort(strs, (a, b) -> b.length() - a.length());
        System.out.println(Arrays.toString(strs));

        /*
             List 集合排序 打印
        */
        List<Integer> list = new ArrayList<>(Arrays.asList(5, 2, 8, 1));
        // 方式1：List.sort()（推荐）
                list.sort((a, b) -> b - a);  // 降序排序
                list.forEach(System.out::println);

                list.sort((a,b) -> a - b); //升序排序
                System.out.println("升序排序："+list);

        // 方式2：Stream流排序（不修改原集合，生成新列表）
        List<Integer> sorted = list.stream()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());

                System.out.println(sorted);
        // Collection 排序
            Collections.sort(sorted);
            Collectors.toList().accumulator().toString();

        /*Map 与 Set 排序*/
        /*使用 迭代器 或者 转化成 List 排序*/
        Iterator<Integer> iterator = list.iterator();
        System.out.println("迭代器遍历：");
        while (iterator.hasNext()){
            System.out.print(iterator.next()+" ");
        }

    }
}
