package com.xhl.Career_Growth.SelfSummary.BaseDS.String.easy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: xhl
 * @Date: 2026-06-23 11:01
 * @Description: 进制转换 更简洁的写法（使用对象封装）
 */
public class EasyHex {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] numStrs = br.readLine().trim().split(",");
        int base = Integer.parseInt(br.readLine().trim());

        List<Node> list = new ArrayList<>();
        for (String s : numStrs) {
            int num = Integer.parseInt(s.trim());
            String converted = Integer.toString(num, base);
            // 把十进制值也存下来，避免排序时重复解析
            list.add(new Node(converted, num));
        }

        // 按十进制值降序排序
        list.sort((a, b) -> Integer.compare(b.value, a.value));

        // 输出
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            if (i > 0) sb.append(",");
            sb.append(list.get(i).str);
        }
        System.out.println(sb.toString());
    }

    static class Node {
        String str;   // 进制转换后的字符串
        int value;    // 对应的十进制值

        Node(String str, int value) {
            this.str = str;
            this.value = value;
        }
    }
}
