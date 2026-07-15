package com.xhl.C07_Algorithm_Skills.SelfSummary.BaseDS.String;

import java.util.Arrays;

/**
 * @Author: xhl
 * @Date: 2026-06-02 22:21
 * @Description: 字符串 的常用方法等等
 */
public class StringLifeStyle {
    public static void main(String[] args) {
        String str = "a,b,c,d,e,f,g,h,i,j,k";
        String nums = "12 3456 789  ";
        String nums1 = "23456";
        /*1.基础信息获取与类型转换*/
        // 获取字符串的长度
        System.out.println(str.length());
        // 获取指定索引位置的单个字符
        System.out.println(str.charAt(3));
        // 将整个字符串转换为字符数组
        System.out.println(str.toCharArray());
        // 静态方法，将 int、double 或其他基本数据类型转换为字符串
        System.out.println(str.valueOf('a'+str));
        String str1 = "hello";
        // 先拼接成 "ahello"，再用 String.valueOf 包装一次
        System.out.println(String.valueOf('a' + str1));
        // 将数字字符串转换为对应的整数或长整数
        System.out.println(Integer.parseInt(nums1));

        /*2.字符串截取与分割*/
        System.out.println(str.substring(1,str.length()-1));
        System.out.println(Arrays.toString(str.split(",")));

        /*3.字符串查找与判断*/
        // 判断当前字符串是否包含某个子串
        System.out.println(str.contains("b,c,d"));
        // 返回指定子串第一次出现的索引；如果没找到则返回 -1
        System.out.println(str.indexOf("c,d"));
        // 返回指定子串最后一次出现的索引。
        System.out.println(str.lastIndexOf("c,d"));
        // 判断字符串是否以指定的前缀开头或后缀结尾。
        System.out.println(str.startsWith("a,b,c,d"));
        System.out.println(str.endsWith(",b,c,d"));
        /*4.字符串清洗与修改*/
        System.out.println("字符串清洗与修改");
        // 去除字符串首尾的空白字符（包括空格、\n、\r 等）。在处理手动输入的测试用例时非常有用。
        System.out.println(nums.trim());

        /*5.字符串比较*/
        // 比较两个字符串的内容是否完全相同。
        System.out.println(str.equals("a,b,c,d,e,f,g,h,i,j,k"));
        // 忽略大小写比较两个字符串的内容是否相同。
        System.out.println(str.equalsIgnoreCase("A,B,C,D,E,F,G,H,I,J,K"));

        /*6.进阶加分项*/
        // StringBuilder（字符串拼接神器） StringBuffer 
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            sb.append(i); // 高效拼接
        }
        String result = sb.toString(); // 最后再转回 String
        //toCharArray() 配合双指针

        // 使用ReplaceAll 配合 正则表达式来使用

    }




}
