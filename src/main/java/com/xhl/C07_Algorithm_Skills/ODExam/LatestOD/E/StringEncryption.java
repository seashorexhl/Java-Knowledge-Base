package com.xhl.C07_Algorithm_Skills.ODExam.LatestOD.E;

import java.util.Scanner;

/**
 * @Author: xhl
 * @Date: 2026-07-23 23:44
 * @Description: 字符串加密
 * 有一种简单的加密算法，规则如下：
 * 对于字母，如果是小写字母，则将其转换为对应的大写字母；如果是大写字母，则将其转换为对应的小写字母。
 * 对于数字，如果是奇数，则加 1；如果是偶数，则保持不变。
 * 其他字符（如空格、标点符号等）保持原样。
 */
public class StringEncryption {
    static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        int n = s.length();
        StringBuffer sb = new StringBuffer(n);
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c >= 'a' && c <= 'z') {
                sb.append((char) (c - 'a' + 'A'));
            }else if (c >= 'A' && c <= 'Z') {
                sb.append((char) (c - 'A' + 'a'));
            }else if (c >= '0' && c <= '9') {
                if(c%2==1){
                    sb.append((char) (c - '0' + '1'));
                }else {
                    sb.append(c);
                }

            }else{
                sb.append(c);
            }
        }
        System.out.println(sb.toString());

    }


}
