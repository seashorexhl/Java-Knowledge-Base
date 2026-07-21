package com.xhl.C07_Algorithm_Skills.SelfSummary.BaseDS.String.easy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * @Author: xhl
 * @Date: 2026-06-23 11:00
 * @Description: 进制转换后自定义排序
 *
 */
public class HexSolution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 读取第一行：整数数组，逗号分隔
        String[] numStrs = br.readLine().trim().split(",");
        int[] nums = new int[numStrs.length];
        for (int i = 0; i < numStrs.length; i++) {
            nums[i] = Integer.parseInt(numStrs[i].trim());
        }

        // 读取第二行：进制
        int base = Integer.parseInt(br.readLine().trim());

        // 步骤1：进制转换，存储为字符串数组
        String[] converted = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            converted[i] = Integer.toString(nums[i], base);
        }

        // 步骤2：自定义排序——按十进制数值降序
        // 将字符串和对应的十进制值绑定，方便排序
        Integer[] indices = new Integer[nums.length];
        for (int i = 0; i < nums.length; i++) {
            indices[i] = i;
        }

        Arrays.sort(indices, (i, j) -> {
            // 将进制字符串解析回十进制数进行比较
            int valI = Integer.parseInt(converted[i], base);
            int valJ = Integer.parseInt(converted[j], base);
            // 降序：valJ - valI
            return Integer.compare(valJ, valI);
        });

        // 步骤3：按排序后的顺序拼接结果
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < indices.length; i++) {
            if (i > 0) sb.append(",");
            sb.append(converted[indices[i]]);
        }
        System.out.println(sb.toString());
    }
}
