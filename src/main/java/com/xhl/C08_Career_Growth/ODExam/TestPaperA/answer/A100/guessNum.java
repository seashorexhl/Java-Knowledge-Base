package com.xhl.C08_Career_Growth.ODExam.TestPaperA.answer.A100;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @Author: xhl
 * @Date: 2026-07-03 19:11
 * @Description: 09 猜数字
 */
public class guessNum {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(); // 输入猜测的次数

        // 存储所有猜测的数字和提示结果
        List<String[]> guessInfos = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String guessNum = scanner.next(); // 输入猜测的数字
            String guessResult = scanner.next(); // 输入猜测的结果
            guessInfos.add(new String[]{guessNum, guessResult}); // 将猜测的数字和结果存入列表中
        }

        int validCount = 0; // 记录符合条件的答案数量
        String validAnswer = ""; // 存储符合条件的答案

        // 遍历所有可能的四位数
        for (int num = 0; num <= 9999; num++) {
            String answer = String.format("%04d", num); // 将数字格式化为四位数字符串
            boolean isValid = true; // 标记当前答案是否有效

            // 遍历每个猜测的数字和结果
            for (String[] guessInfo : guessInfos) {
                String guess = guessInfo[0]; // 获取猜测的数字
                String expectResult = guessInfo[1]; // 获取猜测的结果

                int countA = 0; // 记录数字和位置都正确的个数
                int countB = 0; // 记录数字正确但位置不正确的个数

                int[] answerArr = new int[10]; // 存储答案中每个数字出现的次数
                int[] guessArr = new int[10]; // 存储猜测中每个数字出现的次数

                // 遍历每个位置
                for (int i = 0; i < guess.length(); i++) {
                    int c1Int = guess.charAt(i) - '0'; // 获取猜测中该位置上的数字
                    int c2Int = answer.charAt(i) - '0'; // 获取答案中该位置上的数字

                    if (c1Int == c2Int) {
                        countA++; // 如果数字和位置都正确，countA+1
                    } else {
                        guessArr[c1Int]++; // 在 guessArr 中记录该数字出现的次数
                        answerArr[c2Int]++; // 在 answerArr 中记录该数字出现的次数
                    }
                }

                for (int i = 0; i < 10; i++) {
                    countB += Math.min(answerArr[i], guessArr[i]); // 计算数字正确但位置不正确的个数
                }

                String realResult = countA + "A" + countB + "B"; // 根据猜测和答案计算真实结果

                if (!realResult.equals(expectResult)) {
                    isValid = false; // 如果真实结果和猜测结果不一致，标记当前答案为无效
                    break;
                }
            }

            if (isValid) {
                validCount++; // 如果当前答案有效，更新符合条件的答案数量
                validAnswer = answer; // 更新符合条件的答案

                if (validCount > 1) {
                    break; // 如果符合条件的答案数量大于1，跳出循环
                }
            }
        }

        if (validCount != 1) {
            System.out.println("NA"); // 如果符合条件的答案不唯一，输出 NA
        } else {
            System.out.println(validAnswer); // 如果符合条件的答案唯一，输出答案
        }
    }
}
