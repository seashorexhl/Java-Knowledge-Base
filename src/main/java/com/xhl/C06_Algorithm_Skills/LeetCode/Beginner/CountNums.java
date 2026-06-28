package com.xhl.Algorithm_Skills.LeetCode.Beginner;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * @Author: xhl
 * @Date: 2026-06-05 03:00
 * @Description: 约瑟夫环（Josephus）问题
 */
public class CountNums {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int  M =sc.nextInt();
        // 如果M的值不在1到100之间，输出错误信息
        if(M<1 || M>=100){
            System.out.println("ERROR");
        }else {
            // 创建一个存储1到100的列表
            LinkedList<Integer> numbers  = new LinkedList<>();
            // 将1到100依次加入numbers列表
            for(int i =0;i<100;i++){
                numbers.add(i);
            }
            // 调用find_last_person函数，处理numbers列表
            List<Integer> result = find_last_person(numbers, M);
            // 对结果进行排序
            Collections.sort(result);
            // 遍历result列表，按格式输出结果
            for (int i = 0; i < result.size(); i++) {
                System.out.println(result.get(i));
                // 在元素之间添加逗号，最后一个元素后不加逗号
                if (i != result.size() - 1) {
                    System.out.print(",");
                }
            }
            // 输出换行符
            System.out.println();
        }

    }
    public static List<Integer> find_last_person(List<Integer> numbers, int M) {
        // 遍历numbers数组
        for (int i = 0; i < numbers.size(); i++) {
            // 当索引i等于M时，执行以下操作

        }


        // 将第M个元素之后的元素放入新的临时列表temp

        // 将原数组从0到M-1的元素加到临时列表的末尾

        // 递归调用find_last_person函数，以新的temp列表继续进行处理

        // 当数组遍历完成，返回最终的numbers列表
        return numbers;

    }

}
