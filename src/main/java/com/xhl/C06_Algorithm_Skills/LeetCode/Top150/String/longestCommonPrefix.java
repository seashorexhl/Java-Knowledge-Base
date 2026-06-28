package com.xhl.C06_Algorithm_Skills.LeetCode.Top150.String;

/**
 * @Author: xhl
 * @Date: 2026-06-21 11:24
 * @Description: leetCode 14 最长公共前缀
 *  用 多种方法 解
 */
public class longestCommonPrefix {
    static void main() {
        String[] strs = {"flower","flow","flight"};
        longestCommonPrefix lp =  new longestCommonPrefix();
        System.out.println("横向扫描法：");
        System.out.println(lp.longestCommonPrefix(strs));
        System.out.println("纵向扫描法：");
        System.out.println(lp.longestCommonPrefix1(strs));
        System.out.println("分治法：");
        System.out.println(lp.longestCommonPrefix2(strs));
        System.out.println("二分查找法：");
        System.out.println(lp.longestCommonPrefix3(strs));
        System.out.println("纵向扫描法：");
//        System.out.println(lp.longestCommonPrefixText(strs));
    }

    // 方法一：横向扫描
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        String prefix = strs[0];
        int count = strs.length;
        for (int i = 1; i < count; i++) {
            prefix = longestCommonPrefix(prefix, strs[i]);
            if (prefix.length() == 0) {
                break;
            }
        }
        return prefix;
    }

    public String longestCommonPrefix(String str1, String str2) {
        int length = Math.min(str1.length(), str2.length());
        int index = 0;
        while (index < length && str1.charAt(index) == str2.charAt(index)) {
            index++;
        }
        return str1.substring(0, index);
    }
    // 方法二：纵向扫描  ***
    public String longestCommonPrefix1(String[] strs) {
        // 1. 处理极端边界情况：如果数组为空或长度为0，直接返回空字符串
        if (strs == null || strs.length == 0) {
            return "";
        }
        // 2. 获取第一个字符串的长度，作为外层循环的边界（最多只比较到最短字符串的长度）
        int length = strs[0].length();
        // 获取数组中字符串的总个数
        int count = strs.length;
        // 3. 外层循环：按列（逐字符）遍历第一个字符串
        for (int i = 0; i < length; i++) {
            // 取出第一个字符串在第 i 个位置的字符，作为基准字符
            char c = strs[0].charAt(i);
            // 4. 内层循环：从第二个字符串开始，依次检查它们在相同位置 i 的字符
            for (int j = 1; j < count; j++) {
                // 核心判断：
                // 条件1：当前字符串的长度 <= i，说明该字符串已经遍历完了（越界）
                // 条件2：当前字符串在第 i 个位置的字符与基准字符 c 不一致
                // 只要满足其中一个条件，说明最长公共前缀到此结束
                if (i == strs[j].length() || strs[j].charAt(i) != c) {
                    // 截取第一个字符串从 0 到 i (不包含 i) 的部分，即为最长公共前缀
                    return strs[0].substring(0, i);
                }
            }
        }
        // 5. 如果外层循环正常结束，说明第一个字符串本身就是所有字符串的公共前缀
        // （例如输入为 ["flower", "flow", "flight"] 时不会走到这，但如果是 ["a", "a"] 就会走到这）
        return strs[0];
    }

    // 方法三：分治
    public String longestCommonPrefix2(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        } else {
            return longestCommonPrefix(strs, 0, strs.length - 1);
        }
    }
    // 左边一半找公共前缀，右边一半找公共前缀。最后把左边和右边找出来的结果，再互相比对一下。
    public String longestCommonPrefix(String[] strs, int start, int end) {
        if (start == end) {
            return strs[start];
        } else {
            int mid = (end - start) / 2 + start;
            String lcpLeft = longestCommonPrefix(strs, start, mid);
            String lcpRight = longestCommonPrefix(strs, mid + 1, end);
            return commonPrefix(lcpLeft, lcpRight);
        }
    }

    public String commonPrefix(String lcpLeft, String lcpRight) {
        int minLength = Math.min(lcpLeft.length(), lcpRight.length());
        for (int i = 0; i < minLength; i++) {
            if (lcpLeft.charAt(i) != lcpRight.charAt(i)) {
                return lcpLeft.substring(0, i);
            }
        }
        return lcpLeft.substring(0, minLength);
    }

    // 方法四：二分查找 猜长度，不断缩小范围
    public String longestCommonPrefix3(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        int minLength = Integer.MAX_VALUE;
        for (String str : strs) {
            minLength = Math.min(minLength, str.length());
        }
        int low = 0, high = minLength;
        while (low < high) {
            int mid = (high - low + 1) / 2 + low;
            if (isCommonPrefix(strs, mid)) {
                low = mid;
            } else {
                high = mid - 1;
            }
        }
        return strs[0].substring(0, low);
    }

    public boolean isCommonPrefix(String[] strs, int length) {
        String str0 = strs[0].substring(0, length);
        int count = strs.length;
        for (int i = 1; i < count; i++) {
            String str = strs[i];
            for (int j = 0; j < length; j++) {
                if (str0.charAt(j) != str.charAt(j)) {
                    return false;
                }
            }
        }
        return true;
    }

}
