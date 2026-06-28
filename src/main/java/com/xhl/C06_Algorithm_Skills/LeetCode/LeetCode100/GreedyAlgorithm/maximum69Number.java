package com.xhl.Algorithm_Skills.LeetCode.LeetCode100.GreedyAlgorithm;

/**
 * @Author: xhl
 * @Date: 2026-06-16 04:39
 * @Description: 6 和  9 组成的最大数字
 */
public class maximum69Number {
    static void main() {
        int num = 9669;
        maximum69Number mn =  new maximum69Number();
        int i = mn.maximum69Number(num);
        System.out.println("最大的69数字！"+ i);
    }

    /**
     * 方法一：贪心 + 字符串
     * */
    /*利用 字符数组 */
    public int maximum69Number (int num) {
        char[] chars = Integer.toString(num).toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '6') {
                chars[i] = '9';
                break;
            }
        }
        return Integer.parseInt(new String(chars));

    }
    /**
     * 方法二：贪心 + 数学
     * */
    public int maximum69Number1 (int num) {
        int digitBase = (int)Math.pow(10, (int)Math.log10(num));
        while (digitBase > 0) {
            if ((num / digitBase) % 10 == 6) {
                num += 3 * digitBase;
                return num;
            }
            digitBase /= 10;
        }

        return num;
    }
}
