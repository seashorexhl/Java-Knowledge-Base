package com.xhl.C07_Algorithm_Skills.SelfSummary.BaseDS.Stack;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.Stack;

/**
 * @Author: xhl
 * @Date: 2026-06-03 04:24
 * @Description: 栈 的 生命周期 及常用 方法
 *    初始化   压栈   判空
 *    遍历  迭代器 等
 */
public class StackLifeStyle {
    public static void main(String[] args) {
        // 栈的初始化
        Stack<Integer> stack = new Stack<>();

        /*栈的相关操作*/
        //将元素 压入栈顶
        stack.push(11);
        stack.push(22);
        stack.push(31);
        stack.push(42);
        // 栈的 判空
        System.out.println("栈的 判空:"+ stack.isEmpty());
        // 返回堆栈中 元素的个数
        System.out.println(stack.size());
        //返回栈顶元素
        System.out.println(stack.peek());
        // 删除并返回栈顶元素
        System.out.println(stack.pop());

        /* 栈的遍历*/
        //2.标准 LIFO 顺序遍历（后进先出，但不修改栈）
        System.out.println("标准 LIFO 顺序遍历（后进先出，但不修改栈）");
        ListIterator<Integer> listIterator = stack.listIterator(stack.size());
        while (listIterator.hasPrevious()) {
            System.out.println(listIterator.previous());
        }
        // 方式一：增强 for 循环（最简洁）
        System.out.println("增强 for 循环遍历：");
        for (Integer item : stack) {
            System.out.println(item);
        }

        // 方式二：普通迭代器
        System.out.println("普通迭代器遍历：");
        for (Iterator<Integer> it = stack.iterator(); it.hasNext(); ) {
            System.out.println(it.next());
        }
        // 2.按照 C -> B -> A 的顺序弹出并打印，遍历结束后栈会被清空
        System.out.println("栈的遍历：");
        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }


    }
}
