package com.xhl.C07_Algorithm_Skills.ODExam.A2026.easy100.April;

/**
 * @Author: xhl
 * @Date: 2026-07-14 13:48
 * @Description: 完善核心代码编程-8位LED控制器-100分
 * 输入：L0L1L2D1
 * 输出：5
 */
public class ledController {
    static void main(String[] args) {
        String instructions = "L0L1L2L3L4L5L6L7";
        ledController lc = new ledController();
        int led = lc.ledController(instructions);
        System.out.println(led);
    }

    public int ledController(String instructions) {
        int state = 0;
        for (int i = 0; i < instructions.length(); i += 2) {
            char op = instructions.charAt(i);
            int x = instructions.charAt(i+1) - '0';
            if (op == 'L') state |= (1 << x);
            else if (op == 'D') state &= ~(1 << x);
            else if (op == 'T') state ^= (1 << x);
        }
        return state;
    }
}
