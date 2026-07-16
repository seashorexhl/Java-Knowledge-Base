package com.xhl.C07_Algorithm_Skills.SelfSummary.AdvancedAL.RegExp;

/**
 * @Author: xhl
 * @Date: 2026-07-16 16:12
 * @Description: regex replace  正则表达式替换 字符串消除
 */
public class regexReplace {

    /**
     * 约定一个对输入的字符串中的下划线 _ 做统一替换。
     * 具体要求如下：
     * 输入字符串，将其中包含的每一个下划线 _，使用特殊字符串 (^|$|[,+]) 替换，
     * 并输出替换后的结果。
     * 在一对方括号 [...] 之内的下划线不做替换（注意：方括号会嵌套，如 [xx[yyyy]xxx]）。
     * 被转义的下划线，即 \_ 不做替换。
     * */
    static void main(String[] args) {
        String inStr = "hello_world_test";
        String replaceStr = "(^|$|[,+])"; // 需要替换成的目标字符串
        StringBuilder ans = new StringBuilder(); // 使用 StringBuilder 提高拼接效率
        int bracketCount = 0; // 括号计数器，用于判断是否在方括号内

        for (int i = 0; i < inStr.length(); i++) {
            char ch = inStr.charAt(i);

            // 1. 维护括号状态
            if (ch == '[') {
                bracketCount++;
            } else if (ch == ']') {
                bracketCount--;
            }

            // 2. 判断是否满足替换条件
            // 条件：当前字符是 '_'
            // 且：不在任何方括号内 (bracketCount == 0)
            // 且：不是被转义的 (i == 0 防止越界，且前一个字符不是 '\')
            if (ch == '_' && bracketCount == 0 && (i == 0 || inStr.charAt(i - 1) != '\\')) {
                ans.append(replaceStr);
            } else {
                ans.append(ch);
            }
        }

        System.out.println(ans.toString());
    }


}
