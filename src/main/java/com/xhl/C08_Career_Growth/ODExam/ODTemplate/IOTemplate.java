package com.xhl.C08_Career_Growth.ODExam.ODTemplate;

import java.util.Scanner;

/**
 * @Author: xhl
 * @Date: 2026-06-24 02:25
 * @Description: OD 输入输出流 最新 宝典
 */
public class IOTemplate {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            // 示例：读取一行字符串，按空格分割
            String[] line = sc.nextLine().trim().split("\\s+");
            // int n = Integer.parseInt(line);

            // 示例：读取一行以逗号分隔的数组
            // String[] arrStr = sc.nextLine().trim().split(",");
            // int[] arr = Arrays.stream(arrStr).mapToInt(Integer::parseInt).toArray();

            // 核心逻辑处理...
            System.out.println("result");
        }
        sc.close();
    }
}
