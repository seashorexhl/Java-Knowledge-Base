package com.xhl.C10_Career_Growth.ODExam.A177.Answer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * @Author: xhl
 * @Date: 2026-04-11 22:51
 * @Description:  02 报数游戏 补种未成熟胡杨
 *  约瑟夫环问题 + 数组/列表的模拟操作 + 递归思想
 */
public class CountNumsGame {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int M = scanner.nextInt();

        // 如果M的值不在1到100之间，输出错误信息
        if (M <= 1 || M >= 100) {
            System.out.println("ERROR!");
        } else {
            // 创建一个存储1到100的列表
            List<Integer> numbers = new ArrayList<>();
            for (int i = 0; i < 100; i++) {
                numbers.add(i + 1);  // 将1到100依次加入numbers列表
            }

            // 调用find_last_person函数，处理numbers列表
            List<Integer> result = find_last_person(numbers, M);
            // 对结果进行排序
            Collections.sort(result);

            // 遍历result列表，按格式输出结果
            for (int i = 0; i < result.size(); i++) {
                System.out.print(result.get(i));
                // 在元素之间添加逗号，最后一个元素后不加逗号
                if (i != result.size() - 1) {
                    System.out.print(",");
                }
            }
            // 输出换行符
            System.out.println();
        }
    }
    /**
     *
     * */
    public static List<Integer> find_last_person(List<Integer> numbers, int M) {
        // 遍历numbers数组
        for (int i = 1; i <= numbers.size(); i++) {
            // 当索引i等于M时，执行以下操作
            if (i == M) {
                // 将第M个元素之后的元素放入新的临时列表temp
                List<Integer> temp = new ArrayList<>(numbers.subList(M, numbers.size()));
                // 将原数组从0到M-1的元素加到临时列表的末尾
                temp.addAll(numbers.subList(0, M - 1));
                // 递归调用find_last_person函数，以新的temp列表继续进行处理
                return find_last_person(temp, M);
            }
        }
        // 当数组遍历完成，返回最终的numbers列表
        return numbers;
    }



}
