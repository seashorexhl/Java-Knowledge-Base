package com.xhl.C07_Algorithm_Skills.ODExam.LatestOD.E;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @Author: xhl
 * @Date: 2026-06-29 14:22
 * @Description:  找终点 最少的步数 Find the destination
 *  解题思路
 * 给定一个由正整数组成的数组，要求从数组的第一个元素开始，通过逐步前进最终到达数组的最后
 * 一个元素。你需要找出最少的步骤数，并且必须满足以下条件：
 * 第一步：必须从数组的第一个元素开始，步长的选择范围是1到数组长度的一半之间
 * （即 1 ≤ 步长 < len 2 1 \leq \text{步长} < \frac{\text{len}}{2}1≤步长<2）。
 * 第二步及之后：从当前停留的元素开始，必须按照该元素的值走相应的步数。例如，
 * 当前停留在值为9的元素，则你只能前进9步。
 * 只能向数组尾部前进，不能后退。
 * 如果无法到达数组的最后一个元素，则输出 -1。
 */
public class findDestination {
    static void main() {
        Scanner sc = new Scanner(System.in);
        // 读取用户输入的整数字符串，以空格分隔
        String input = sc.nextLine();
        // 将输入的字符串分割成字符串数组
        String[] numberStrings = input.split(" ");
        int[] numbers = new int[numberStrings.length]; // 创建一个与字符串数组长度相同的整型数组
        for (int i = 0; i < numberStrings.length; i++) {
            numbers[i] = Integer.parseInt(numberStrings[i]); // 将字符串数组的每个元素转换为整数，并存入整型数组
        }
        int length = numbers.length; // 获取数组的长度
        List<Integer> result = new ArrayList<>(); // 用于存储所有可能的步数结果
        for (int i = 1; i < length / 2; i++) { // 遍历所有从第一个元素开始的有效步长
            int step = 1; // 初始化步数为1，因为第一步已经走出
            int index = i; // 将索引设为当前步长
            while (index < length - 1) { // 只要没有走到数组的最后一个元素
                index += numbers[index]; // 按照当前索引位置的数字值前进
                step++; // 每走一步，步数加1
            }
            if (index == length - 1) { // 如果恰好到达数组的最后一个元素
                result.add(step); // 将步数结果存入结果列表
            }
        }
        if (result.size() > 0) {
            Integer[] resultArray = result.toArray(new Integer[0]); // 将结果列表转换为数组
            Arrays.sort(resultArray); // 对步数结果进行排序
            System.out.println(resultArray[0]); // 输出最小的步数
        } else {
            System.out.println(-1); // 如果没有结果，输出-1
        }
    }
}
