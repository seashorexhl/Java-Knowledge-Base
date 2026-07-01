package com.xhl.C06_Algorithm_Skills.LeetCode.Top150.Array;

/**
 * @Author: xhl
 * @Date: 2026-06-16 03:07
 * @Description:
 */
/**
 *
 代码
 测试用例
 测试用例
 测试结果
 58. 最后一个单词的长度
 已解答
 简单
 相关标签
 premium lock icon
 相关企业
 给你一个字符串 s，由 若干 单词 组成，单词前后用一些空格字符隔开。返回字符串中 最后一个 单词的长度。

 单词 是指仅由字母组成、不包含任何空格字符的最大子字符串。
 * */
public class lengthOfLastWord {
    static void main() {
        String str = "Hello World";
        lengthOfLastWord loop = new lengthOfLastWord();
        System.out.println(loop.lengthOfLastWord(str));
    }

    public int lengthOfLastWord(String s) {
        int count = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == ' ') {
                continue;
            }else if (s.charAt(i) != ' ') {
                count++;
                if (s.charAt(i - 1) == ' ') {
                    break;
                }
            }
        }

        return count;
    }

    public int lengthOfLastWord1(String s) {
        int index = s.length() - 1;
        while (s.charAt(index) == ' ') {
            index--;
        }
        int wordLength = 0;
        while (index >= 0 && s.charAt(index) != ' ') {
            wordLength++;
            index--;
        }
        return wordLength;
    }
}
