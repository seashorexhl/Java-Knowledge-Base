package com.xhl.C06_Algorithm_Skills.SelfSummary.BaseAL.Cycle;

/**
 * @Author: xhl
 * @Date: 2026-06-10 02:32
 * @Description: 循环 通用模板
 *  1.For循环模板
 *  2.while 循环模板
 *  3.do-while 循环模板
 *  4.多线程交替循环模板
 */
public class CycleSolution {
    public static void main(String[] args) {
        int sum = 0;
        // 语法格式：for (初始化值; 循环条件 ; 迭代语句) { 循环体 }
        for (int i = 1; i <= 10; i++) {
            sum += i;
            System.out.println("当前累加结果：" + sum);
        }
        System.out.println("最终总和：" + sum);
    }

}
