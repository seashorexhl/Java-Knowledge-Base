package com.xhl.Career_Growth.SelfSummary.AdvancedAL.StateMachine;

import java.util.Scanner;

/**
 * @Author: xhl
 * @Date: 2026-06-23 14:57
 * @Description: 状态机
 *  可以理解为 状态标志位
 *          或者 状态切换
 *          1.字符串匹配
 *          2.规则校验类
 *          3.模式切换类
 */
public class StateMachine {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 1. 读取输入（根据题目调整）
        String line = scanner.nextLine().trim();

        // 2. 初始化状态机
        int state = 0; // 0 通常是初始状态
        int result = 0; // 记录结果（如匹配次数、最终状态等）

        // 3. 遍历输入，驱动状态机
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);

            // 4. 核心：根据当前状态和输入字符，决定下一个状态
            switch (state) {
                case 0: // 初始状态 / 等待状态
                    if (c == 'q') state = 1;
                    // 其他字符保持 state = 0
                    break;

                case 1: // 已匹配 'q'，等待 'u'
                    if (c == 'u') state = 2;
                    else if (c == 'q') state = 1; // 容错：连续q，保持或重置
                    else state = 0; // 失败，重置
                    break;

                case 2: // 已匹配 'qu'，等待 'a'
                    if (c == 'a') state = 3;
                    else if (c == 'q') state = 1; // 容错：qu后跟q，直接跳到q状态
                    else state = 0;
                    break;

                // ... 继续扩展 case ...

                case 4: // 已匹配 'quac'，等待 'k'
                    if (c == 'k') {
                        result++; // 完成一次完整匹配
                        state = 0; // 重置，准备下一次
                    } else if (c == 'q') {
                        state = 1; // 容错
                    } else {
                        state = 0;
                    }
                    break;

                default:
                    state = 0; // 兜底重置
                    break;
            }
        }

        // 5. 输出结果
        System.out.println(result);
    }
}
