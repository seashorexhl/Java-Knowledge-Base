package com.xhl.Java_Core.java17.SwitchPatternMatch;

/**
 * @Author: xhl
 * @Date: 2026-04-05 22:59
 * @Description: Java 17 模式匹配是一种新的语法特性，允许在 switch 语句中使用更复杂的条件。
 * 这使得 switch 语句更加灵活和强大。
 */
public class PatternMatching {
    public static void main(String[] args) {
        Object value = "abc";
        switch (value) {
            case String s:
                System.out.println("Value is a string: " + s);
                break;
            case Integer i:
                System.out.println("Value is an integer: " + i);
                break;
            default:
                System.out.println("Value is of unknown type.");
        }
    }
}
