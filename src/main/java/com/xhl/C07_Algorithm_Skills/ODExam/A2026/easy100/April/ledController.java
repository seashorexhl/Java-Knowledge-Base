package com.xhl.C07_Algorithm_Skills.ODExam.A2026.easy100.April;

/**
 * @Author: xhl
 * @Date: 2026-07-14 13:48
 * @Description: 完善核心代码编程-8位LED控制器-100分 ⭐⭐⭐
 *  位运算 + 字符串解析与指令集模拟 + 状态压缩
 *  输入：L0L1L2D1
 *  输出：5
 */
public class ledController {
    static void main(String[] args) {
        String instructions = "L0L1L2L3L4L5L6L7";
        ledController lc = new ledController();
        int led = lc.ledController(instructions);
        System.out.println(led);
    }

    /**
     * 位运算与 掩码操作 + 状态压缩思想
     *
     */
    public int ledController(String instructions) {
        int state = 0;
        for (int i = 0; i < instructions.length(); i += 2) {
            char op = instructions.charAt(i);
            int x = instructions.charAt(i + 1) - '0';
            if (op == 'L') state |= (1 << x);
            else if (op == 'D') state &= ~(1 << x);
            else if (op == 'T') state ^= (1 << x);
        }
        return state;
    }

    /**
     * 核心逻辑 思想 位运算
     * 1. 初始状态与返回值
     * 2. 指令解析的容错处理
     * 3. 位运算的精准映射
     *
     */
    public int ledController1(String instructions) {
        int state = 0; // 初始状态全灭，二进制 00000000

        // 1. 健壮性检查：防止空指针异常
        if (instructions == null || instructions.isEmpty()) {
            return state;
        }

        // 2. 步长为2解析指令，同时增加边界检查防止越界
        for (int i = 0; i + 1 < instructions.length(); i += 2) {
            char op = instructions.charAt(i);
            char xChar = instructions.charAt(i + 1);

            // 3. 提取并校验 LED 编号 (必须是 '0' 到 '7' 之间的字符)
            if (xChar < '0' || xChar > '7') {
                continue; // 遇到非法编号直接跳过该指令
            }
            int x = xChar - '0';

            // 4. 执行对应的位运算
            if (op == 'L') {
                state |= (1 << x);       // 点亮：将第 x 位设为 1
            } else if (op == 'D') {
                state &= ~(1 << x);      // 熄灭：将第 x 位设为 0
            } else if (op == 'T') {
                state ^= (1 << x);       // 切换：将第 x 位取反 (0变1，1变0)
            }
            // 其他未知操作符默认忽略
        }

        // 5. 确保返回值严格限制在 8位 范围内 (0-255)
        return state & 0xFF;
    }

    /**
     * 位运算 + 状态压缩 +性能 优化
     */
    public int ledController2(String instructions) {
        int state = 0;// 1。初始状态
        // 2。循环解析
        for (int i = 0; i + 1 < instructions.length(); i += 2) {
            char op = instructions.charAt(i);
            int x = instructions.charAt(i + 1) - '0';
            // 3. 核心操作（只记这三个字母）
            if (op == 'L') state |= (1 << x);      // L = Light (点亮)
            else if (op == 'D') state &= ~(1 << x); // D = Dark (熄灭)
            else if (op == 'T') state ^= (1 << x);  // T = Toggle (切换)
        }
        return state;
    }
    /**
     *  数组映射
     * */
    public int ledController3(String instructions) {
        // 1. 创建一个长度为 8 的数组，模拟 8 个开关
        // 数组默认初始值就是 0，完美对应题目“初始状态全灭”
        int[] leds = new int[8];

        // 2. 遍历指令字符串（步长为 2，每次取两个字符）
        // 加上 i + 1 < instructions.length() 防止字符串长度为奇数时越界
        for (int i = 0; i + 1 < instructions.length(); i += 2) {
            char op = instructions.charAt(i);      // 第 1 个字符：操作类型 (L/D/T)
            int x = instructions.charAt(i + 1) - '0'; // 第 2 个字符：灯的编号 (0-7)

            // 3. 核心操作：像操作真实开关一样去修改数组
            if (op == 'L') {
                leds[x] = 1;             // L (Light)：打开开关，设为 1
            } else if (op == 'D') {
                leds[x] = 0;             // D (Dark)：关闭开关，设为 0
            } else if (op == 'T') {
                leds[x] = 1 - leds[x];   // T (Toggle)：切换开关（0变1，1变0）
            }
        }

        // 4. 将数组状态转换为最终的整数值
        int result = 0;
        for (int i = 0; i < 8; i++) {
            if (leds[i] == 1) {
                result += (1 << i);      // 如果第 i 个灯亮着，加上 2 的 i 次方
            }
        }

        return result;
    }
}
