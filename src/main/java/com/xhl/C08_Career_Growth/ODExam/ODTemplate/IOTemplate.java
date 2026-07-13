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
        /**
         *  针对于 一行
         * */

        // 1. 读取包含所有数据的一整行，并按逗号分割
        String[] parts = sc.nextLine().trim().split(",");

        // 2. 提取 n (第一个元素)
        int n = Integer.parseInt(parts[0]);

        int[] duration = new int[n];
        int[] deadline = new int[n];
        int[] profit = new int[n];

        // 3. 从 parts 中提取数组数据
        // 注意：输入格式可能是 "[1,2,3,4]"，也可能是 "1,2,3,4"
        // 我们需要去掉中括号，再按逗号分割
        duration = parseArray(parts[1], n);
        deadline = parseArray(parts[2], n);
        profit = parseArray(parts[3], n);

        sc.close();
    }
    /**
     * 解析带中括号的字符串数组，例如 "[1,2,3,4]"
     */
    private static int[] parseArray(String str, int n) {
        // 去掉可能存在的中括号和空格
        str = str.replace("[", "").replace("]", "").trim();
        String[] nums = str.split(",");

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(nums[i].trim());
        }
        return arr;
    }
}
