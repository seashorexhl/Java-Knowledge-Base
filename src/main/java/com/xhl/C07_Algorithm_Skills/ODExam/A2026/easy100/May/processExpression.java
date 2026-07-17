package com.xhl.C07_Algorithm_Skills.ODExam.A2026.easy100.May;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: xhl
 * @Date: 2026-07-10 03:05
 * @Description: 简单表达式运算-100分
 *  进制转换、位运算、数组越界、异常处理
 */
public class processExpression {
    // 主测试方法
    static void main(String[] args) {
        String inputStr = "30+0xEc+0012+9";
        String s = processExpression(inputStr);
        System.out.println(s);
    }
    /**
     * 核心处理方法：解析包含多进制数字的加减表达式，计算结果并进行位运算
     * @param inputStr 输入的表达式字符串
     * @return 计算后的十六进制字符串（0x00格式），若格式非法则返回 "NA"
     */
    public static String processExpression(String inputStr) {
        // 1. 基础防御性校验：处理空值、空字符串以及超长字符串
        if (inputStr == null || inputStr.isEmpty() || inputStr.length() > 10000) {
            return "NA";
        }
        // 2. 词法分析（Lexer）：将字符串拆解为数字和运算符的交替列表
        // 数字存为实际数值，运算符存为 1(+) 或 -1(-)

        List<Integer> tokens = new ArrayList<>();
        int i = 0;
        int n = inputStr.length();

        while (i < n) {
            char c = inputStr.charAt(i);
            // 2.1 处理运算符：遇到 + 或 -，将其转换为 1 或 -1 存入列表
            if (c == '+' || c == '-') {
                tokens.add(c == '+' ? 1 : -1);
                i++;
            // 2.2 处理数字：以数字开头，需要进一步判断进制类型
            } else if (Character.isDigit(c)) {
                int start = i;
                // 判断是否为特殊进制（以 '0' 开头且后面还有字符）

                if (i + 1 < n && c == '0') {
                    char nextChar = inputStr.charAt(i+1);
                    // 2.2.1 解析十六进制 (0x 或 0X)

                    if (nextChar == 'x' || nextChar == 'X') {
                        i += 2;// 跳过 '0x' 前缀
                        start = i;// 更新实际数字的起始位置
                        // 循环读取合法的十六进制字符 (0-9, a-f, A-F)
                        while (i < n) {
                            char hexChar = inputStr.charAt(i);
                            if (Character.digit(hexChar, 16) != -1) {
                                i++;
                            } else {
                                break; // 遇到非法字符则停止读取
                            }
                        }
                        // 容错：如果 '0x' 后面没有跟任何合法数字，返回 NA

                        if (i == start) {
                            return "NA";
                        }
                        String numStr = inputStr.substring(start, i);
                        try {
                            int value = Integer.parseInt(numStr, 16);// 按16进制解析
                            // 校验数值范围：题目要求单个数字在 0~999 之间

                            if (value < 0 || value > 999) {
                                return "NA"; // 解析失败（如数字过大超出int范围）
                            }
                            tokens.add(value);
                        } catch (NumberFormatException e) {
                            return "NA";
                        }
                        // 2.2.2 解析八进制 (0o 或 0O)

                    } else if (nextChar == 'o' || nextChar == 'O') {
                        i += 2;// 跳过 '0o' 前缀
                        start = i;
                        // 循环读取合法的八进制字符 (0-7)

                        while (i < n) {
                            char octChar = inputStr.charAt(i);
                            if (octChar >= '0' && octChar <= '7') {
                                i++;
                            } else {
                                break;
                            }
                        }
                        if (i == start) {
                            return "NA";
                        }
                        String numStr = inputStr.substring(start, i);
                        try {
                            int value = Integer.parseInt(numStr, 8);// 按8进制解析
                            if (value < 0 || value > 999) {
                                return "NA";
                            }
                            tokens.add(value);
                        } catch (NumberFormatException e) {
                            return "NA";
                        }
                    } else {
                        // 2.2.3 解析十进制 (以 0 开头但后面不是 x/o)
                        while (i < n && Character.isDigit(inputStr.charAt(i))) {
                            i++;
                        }
                        if (i == start) {
                            return "NA";
                        }
                        String numStr = inputStr.substring(start, i);
                        try {
                            int value = Integer.parseInt(numStr);
                            if (value < 0 || value > 999) {
                                return "NA";
                            }
                            tokens.add(value);
                        } catch (NumberFormatException e) {
                            return "NA";
                        }
                    }
                // 2.2.4 解析普通十进制数字 (非 0 开头)
                } else {
                    while (i < n && Character.isDigit(inputStr.charAt(i))) {
                        i++;
                    }
                    if (i == start) {
                        return "NA";
                    }
                    String numStr = inputStr.substring(start, i);
                    try {
                        int value = Integer.parseInt(numStr);
                        if (value < 0 || value > 999) {
                            return "NA";
                        }
                        tokens.add(value);
                    } catch (NumberFormatException e) {
                        return "NA";
                    }
                }
            // 3. 非法字符处理：既不是运算符也不是数字，直接报错
            } else {
                return "NA"; // 非法字符
            }
        }
        // 【语法校验】表达式结构必须是：数字、运算符、数字...
        // 因此 tokens 的长度必须是奇数，且不能为空
        if (tokens.isEmpty() || tokens.size() % 2 != 1) {
            return "NA";
        }
        // 【结构校验】严格校验奇偶位置上的元素类型是否正确
        for (int idx = 0; idx < tokens.size(); idx++) {
            if (idx % 2 == 0) {
                // 偶数位置必须是正数（数字）

                if (tokens.get(idx) < 0) {
                    return "NA";
                }
            } else {
                // 奇数位置必须是合法的运算符 (1 或 -1)

                if (tokens.get(idx) != 1 && tokens.get(idx) != -1) {
                    return "NA";
                }
            }
        }
        // 【表达式求值】由于只有加减法，优先级相同，直接从左到右线性计算
        int result = tokens.get(0);
        for (int idx = 1; idx < tokens.size(); idx += 2) {
            int op = tokens.get(idx);// 获取运算符
            int nextNum = tokens.get(idx + 1);// 获取下一个数字
            if (op == 1) {
                result += nextNum;
            } else {
                result -= nextNum;
            }
        }
        // 【业务约束】将最终计算结果钳制（Clamp）在 -255 ~ 255 之间

        if (result > 255) result = 255;
        if (result < -255) result = -255;
        // 【位运算】对结果进行按位取反，并使用 0xFF 掩码截断为 8位（1字节）

        int notValue = (~result) & 0xFF;
        // 格式化输出为两位大写的十六进制数（如 0x0A）

        return String.format("0x%02X", notValue);
    }
}
