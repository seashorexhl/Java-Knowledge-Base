package com.xhl.Career_Growth.SelfSummary.BaseDS.Collection.List.ArrayList;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Author: xhl
 * @Date: 2026-06-03 01:15
 * @Description: 动态数组 的生命周期
 */
public class ArrayListLifeStyle {
    public static void main(String[] args) {
        // 初始化存储一个 String 类型的动态数组
        List<String> list = new ArrayList<>();
        list.add("a");

        // 初始化 一个 存储 int 类型的动态数组
        ArrayList<Integer> nums = new ArrayList<>();
        nums.add(1);
        nums.add(2);
        nums.add(3);
        nums.add(4);
        nums.add(5);

        /* 常用方法 和 API  */

        // 返回数组的元素个数
        System.out.println(list.size());

        // 增删改查 + 判空 在数组尾部添加 元素E
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("e");
        list.add("f");
        list.add("g");
        list.add("h");
        list.remove("a");
        list.remove(2);
        // 判断数组 是否为空
        System.out.println(list.isEmpty());
        list.set(4,"m");
        System.out.println(list.get(4));
        // 返回索引index 的元素
        System.out.println(list.get(4));

        /*遍历*/
        // 1.增强 for 循环 遍历
        System.out.println("增强 For 循环");
        for(Integer a:nums){
            System.out.print(a+" ");
        }
        System.out.println();
        // 2.迭代器遍历
        Iterator<Integer> iterator = nums.iterator();
        System.out.println("迭代器遍历：");
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }

        // 3. Java 8 forEach（推荐，函数式风格）
        System.out.println("\nforEach:");
        list.forEach(item -> System.out.print(item + " "));
        // 良好的 String.join 方法
        System.out.println("String.join() 方法");
        System.out.println(String.join(",",list));

    }
}
