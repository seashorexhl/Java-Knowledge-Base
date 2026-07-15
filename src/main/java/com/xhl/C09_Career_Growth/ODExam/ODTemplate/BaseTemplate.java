package com.xhl.C09_Career_Growth.ODExam.ODTemplate;

import java.util.*;

/**
 * @Author: xhl
 * @Date: 2026-07-04 03:19
 * @Description: 基础模板
 */
public class BaseTemplate {
    static void main() {
        /**
         *  1.标准 IO 处理逻辑
         * */
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
        /**
         * 2.处理 输入场景代码片段
         * */
        // 场景 A：读取单行多个整数（空格分隔）
        int n = sc.nextInt();
        // 场景 B：读取数组（已知长度 n）
        int m = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }
        // 场景 C：读取字符串（含空格或整行）
        int k = sc.nextInt();
        sc.nextLine(); // 【关键】消耗掉 nextInt 留下的换行符

        String line = sc.nextLine(); // 读取整行
        String[] parts = line.split(" "); // 按空格拆分

        // 场景 D：读取多组数据（直到结束）
        while (sc.hasNextLine()) {
            String linen = sc.nextLine();
            if (line.isEmpty()) break; // 防止空行死循环
            // 处理 line...
        }
        /**
         *  常见输入场景代码片段
         * */
        // 数组
        int[] nums1 = new int[10];
        // 优先队列/堆
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        // 哈希表
        HashMap<String, Integer> map = new HashMap<>();
        // 字符串拼接
        StringBuilder sb = new StringBuilder();
        // 排序
        Arrays.sort(nums);
        // 列表
        ArrayList<Integer> list = new ArrayList<>();

        /**
         *  避坑指南
         *    类名必须是 Main
         *    不要写包名 直接写 import
         *    输出不要有多余空格
         *    涉及大数累加、距离计算 直接 用long
         *    多注意边界检查 和 判空 :
         *    使用 API 解析器：InputStream Scanner等。
         * */

    }

}
