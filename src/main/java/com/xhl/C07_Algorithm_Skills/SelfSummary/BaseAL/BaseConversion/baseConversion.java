package com.xhl.C07_Algorithm_Skills.SelfSummary.BaseAL.BaseConversion;

import java.math.BigInteger;

/**
 * @Author: xhl
 * @Date: 2026-06-10 02:55
 * @Description: 进制转换 通用模板
 */
public class baseConversion {
    public static void main(String[] args) {

    }
    /*1. 十进制转其他进制*/
    public class DecimalToOtherBase {
        public static void main(String[] args) {
            int decimalValue = 255;

            // 转为二进制 (Binary)
            String binary = Integer.toBinaryString(decimalValue);

            // 转为八进制 (Octal)
            String octal = Integer.toOctalString(decimalValue);

            // 转为十六进制 (Hexadecimal)
            String hex = Integer.toHexString(decimalValue);

            // 转为任意 R 进制 (Radix 范围: 2-36)
            // 例如转为五进制
            String baseFive = Integer.toString(decimalValue, 5);

            System.out.println("十进制: " + decimalValue);
            System.out.println("二进制: " + binary);       // 输出: 11111111
            System.out.println("十六进制: " + hex);         // 输出: ff
            System.out.println("五进制: " + baseFive);      // 输出: 2020
        }
    }
    /*2.其他进制转 十进制*/
    public class OtherBaseToDecimal {
        public static void main(String[] args) {
            // 将二进制字符串转为十进制
            int fromBinary = Integer.parseInt("11111111", 2);

            // 将十六进制字符串转为十进制（不区分大小写）
            int fromHex = Integer.parseInt("FF", 16);

            // 将任意 M 进制字符串转为十进制
            int fromBaseFive = Integer.parseInt("2020", 5);

            System.out.println("二进制转十进制: " + fromBinary);   // 输出: 255
            System.out.println("十六进制转十进制: " + fromHex);     // 输出: 255
            System.out.println("五进制转十进制: " + fromBaseFive);  // 输出: 255
        }
    }
    /*3. 任意 M 进制转 N 进制（两步法/大数支持）*/


    public class BaseMToBaseN {
        /**
         * 将 M 进制数转换为 N 进制数
         * @param number 原始数字字符串
         * @param fromBase 原始进制 (M)
         * @param toBase 目标进制 (N)
         */
        public static String convert(String number, int fromBase, int toBase) {
            // 第一步：将 M 进制字符串解析为 BigInteger (相当于转为十进制内部表示)
            BigInteger decimal = new BigInteger(number, fromBase);

            // 第二步：将 BigInteger 转换为 N 进制字符串
            return decimal.toString(toBase).toUpperCase();
        }

        public static void main(String[] args) {
            String result = convert("1A3", 16, 8);
            System.out.println("十六进制 1A3 转八进制: " + result); // 输出: 643
        }
    }
    /*4.底层算法实现（除基取余法）*/
    public class ManualConversion {
        // 字符映射表，用于处理大于9的位（如十六进制的 A-F）
        private static final char[] DIGITS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

        public static String toBase(int num, int radix) {
            if (num == 0) return "0";

            boolean isNegative = num < 0;
            if (isNegative) num = -num; // 先处理绝对值

            StringBuilder sb = new StringBuilder();

            while (num > 0) {
                int remainder = num % radix;      // 取余得到当前最低位
                sb.append(DIGITS[remainder]);     // 映射到对应字符
                num /= radix;                     // 整除缩小规模
            }

            if (isNegative) sb.append('-');
            return sb.reverse().toString();       // 必须反转，因为余数是逆序得到的
        }

        public static void main(String[] args) {
            System.out.println(toBase(255, 16)); // 输出: FF
            System.out.println(toBase(13, 2));   // 输出: 1101
        }
    }
}
