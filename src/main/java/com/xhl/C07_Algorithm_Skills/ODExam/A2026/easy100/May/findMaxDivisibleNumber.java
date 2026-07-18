package com.xhl.C07_Algorithm_Skills.ODExam.A2026.easy100.May;

/**
 * @Author: xhl
 * @Date: 2026-07-10 02:52
 * @Description:    查找能被整除的最大整数-100分
 * 字符串解析 + 简单数学逻辑 + 状态机模拟
 */
public class findMaxDivisibleNumber {
    static void main(String[] args) {
        // 测试用例：包含字母和数字的混合字符串
        String inputStr = "abc123EFEDG34aadD78er";
        int inputDivisor = 2;
        // 调用核心处理方法并输出结果

        int number = findMaxDivisibleNumber(inputStr, inputDivisor);
        System.out.println(number);
    }
    // 字符串解析 + 简单数学逻辑
    /**
     * 核心逻辑：从混合字符串中提取连续数字，寻找能被指定除数整除的最大数字
     * @param inputStr 输入的混合字符串
     * @param inputDivisor 目标除数
     * @return 符合条件的最大数字，若不存在或输入非法则返回 -1
     */
    public static int findMaxDivisibleNumber(String inputStr, int inputDivisor) {
        // 1. 参数合法性校验：除数必须在 1~99 之间

        if (inputDivisor < 1 || inputDivisor > 99) {
            return -1;
        }

        int len = inputStr.length();
        // 2. 字符串长度校验：必须在 1~10000 之间

        if (len < 1 || len > 10000) {
            return -1;
        }

        // 3. 非法字符校验：遍历字符串，确保只包含字母或数字
        for (int i = 0; i < len; i++) {
            char c = inputStr.charAt(i);
            if (!(Character.isLetter(c) || Character.isDigit(c))) {
                return -1;  // 发现非法字符直接返回 -1
            }
        }

        int maxValidNum = -1; // 记录最终符合条件的最大数字，初始为 -1
        int start = -1;// 记录连续数字子串的起始索引，-1 表示当前不在数字序列中
        // 4. 核心遍历逻辑：使用双指针（start 和 i）提取连续数字
        // 注意：条件为 i <= len，这是为了巧妙处理字符串以数字结尾的边界情况
        for (int i = 0; i <= len; i++) {
            // 情况 A：当前字符是数字，且未越界

            if (i < len && Character.isDigit(inputStr.charAt(i))) {
                if (start == -1) {
                    start = i;// 记录当前连续数字序列的起点
                }
            } else {
                // 情况 B：遇到非数字字符，或者已经遍历到字符串末尾 (i == len)

                if (start != -1) {
                    // 截取当前连续数字子串，并计算其长度

                    int numLen = i - start;
                    // 【易错点/业务规则】：如果提取出的数字长度大于 3，直接判定整个输入非法
                    // （注：实际工程中通常应跳过该数字，但此处按题目规则直接返回 -1）

                    if (numLen > 3) {
                        return -1;
                    }
                    // 将字符串转为整数

                    String numStr = inputStr.substring(start, i);
                    int num = Integer.parseInt(numStr);
                    // 校验数字范围（虽然长度<=3已保证在0~999内，但作为双重保险）

                    if (num >= 0 && num <= 999) {
                        // 核心数学判断：是否能被目标除数整除

                        if (num % inputDivisor == 0) {
                            // 如果满足条件，且大于当前记录的最大值，则更新最大值

                            if (num > maxValidNum) {
                                maxValidNum = num;
                            }
                        }
                    }
                    // 重置起点，准备寻找下一个连续数字序列

                    start = -1;
                }
            }
        }
        // 5. 返回最终结果：如果找到了合法数字返回最大值，否则返回 -1

        return (maxValidNum != -1) ? maxValidNum : -1;

    }

}
