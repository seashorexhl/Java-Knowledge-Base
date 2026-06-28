package com.xhl.Career_Growth.SelfSummary.BaseDS.String;

/**
 * @Author: xhl
 * @Date: 2026-06-19 08:09
 * @Description: 转换成小写字母
 *  扩展 （大小写转换）
 */
public class toLowerCase {
    static void main() {
        String s = "Hello World";
        toLowerCase tlc = new toLowerCase();
        System.out.printf("大写转小写：");
        System.out.println(tlc.toLowerCase1(s));
        System.out.printf("小写转大写：");
        System.out.println(tlc.toUpCase(s));
    }

    //方法一：使用语言 API
    public String toLowerCase(String s) {
        return s.toLowerCase();
    }

    //    方法二：自行实现该 API 转换成 小写
    public String toLowerCase1(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); ++i) {
            char ch = s.charAt(i);
            if (ch >= 65 && ch <= 90) {
                // 将 ch 转为小写
                ch |= 32;
//                // 按位与 -33（转大写）
//                ch &= -33;
//                // 更直观的掩码形式：
//                ch &= ~32;
            }
            sb.append(ch);
        }
        return sb.toString();
    }
    // 转换成 大写
    public  String toUpCase(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); ++i) {
            char ch = s.charAt(i);
            if (ch >= 'a' && ch <= 'z') {
                ch &= -33;
            }
            sb.append(ch);
        }
        return sb.toString();
    }
}