package com.xhl.C07_Algorithm_Skills.ODExam.LatestOD.E;

/**
 * @Author: xhl
 * @Date: 2026-07-23 23:52
 * @Description: 字符串加密 （严谨）
 */
import java.util.Scanner;

public class StringEncryptionUp {
    // 1. 补全 public 修饰符
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        int n = s.length();
        // 2. 推荐使用 StringBuilder
        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c >= 'a' && c <= 'z') {
                // 大小写转换逻辑非常棒！
                sb.append((char) (c - 'a' + 'A'));
            } else if (c >= 'A' && c <= 'Z') {
                sb.append((char) (c - 'A' + 'a'));
            } else if (c >= '0' && c <= '9') {
                // 3. 修正奇偶数判断逻辑
                int digit = c - '0';
                if (digit % 2 == 1) {
                    sb.append(digit + 1); // 直接追加数字即可，StringBuilder会自动转成字符串
                } else {
                    sb.append(c);
                }
            } else {
                sb.append(c);
            }
        }
        System.out.println(sb.toString());
    }
}