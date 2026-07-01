package com.xhl.C01_Java_Core.JVM;

/**
 * @Author: xhl
 * @Date: 2026-06-24 20:46
 * @Description: 栈帧（Stack Frame）与 StackOverflowError 示例：
 * 每次方法调用都会在虚拟机栈中创建一个栈帧（包含局部变量表、操作数栈等）。无限递归会导致栈深度超限：
 */
public class StackDemo {
    public void recursive() {
        recursive(); // 无限递归，抛出 StackOverflowError
    }
}