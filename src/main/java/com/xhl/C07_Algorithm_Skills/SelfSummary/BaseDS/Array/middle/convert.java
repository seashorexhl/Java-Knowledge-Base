package com.xhl.C07_Algorithm_Skills.SelfSummary.BaseDS.Array.middle;

/**
 * @Author: xhl
 * @Date: 2026-07-11 21:12
 * @Description: 6. Z 字形变换
 *  方法一：利用二维矩阵模拟
 *  方法二：压缩矩阵空间
 *  方法三：直接构造
 */
public class convert {
    static void main(String[] args) {
        String s = "PAHNAPLSIIGYIR";
        int numRows = 3;
        convert cv = new convert();
        // 2. 调用方法并使用 System.out.println 打印结果
        String result = cv.convert(s, numRows);
        System.out.println("方法一（二维矩阵模拟）结果: " + result);


        String result2 = cv.convert2(s, numRows);
        System.out.println("方法二（压缩矩阵空间）结果: " + result2);

        String result3 = cv.convert3(s, numRows);
        System.out.println("方法三（直接构造）结果: " + result3);
    }

    /**
     *  方法一：利用二维矩阵模拟
     * */
    public String convert(String s, int numRows) {
        int n = s.length(), r = numRows;
        if (r == 1 || r >= n) {
            return s;
        }
        int t = r * 2 - 2;
        int c = (n + t - 1) / t * (r - 1);
        char[][] mat = new char[r][c];
        for (int i = 0, x = 0, y = 0; i < n; ++i) {
            mat[x][y] = s.charAt(i);
            if (i % t < r - 1) {
                ++x; // 向下移动
            } else {
                --x;
                ++y; // 向右上移动
            }
        }
        StringBuffer ans = new StringBuffer();
        for (char[] row : mat) {
            for (char ch : row) {
                if (ch != 0) {
                    ans.append(ch);
                }
            }
        }
        return ans.toString();
    }
    /**
     *  方法二：压缩矩阵空间
     * */
    public String convert2(String s, int numRows) {
        int n = s.length(), r = numRows;
        if (r == 1 || r >= n) {
            return s;
        }
        int t = r * 2 - 2;
        int c = (n + t - 1) / t * (r - 1);
        char[][] mat = new char[r][c];
        for (int i = 0, x = 0, y = 0; i < n; ++i) {
            mat[x][y] = s.charAt(i);
            if (i % t < r - 1) {
                ++x; // 向下移动
            } else {
                --x;
                ++y; // 向右上移动
            }
        }
        StringBuffer ans = new StringBuffer();
        for (char[] row : mat) {
            for (char ch : row) {
                if (ch != 0) {
                    ans.append(ch);
                }
            }
        }
        return ans.toString();
    }
    /**
     *  方法三：直接构造
     * */
    public    String convert3(String s, int numRows) {
        int n = s.length(), r = numRows;
        if (r == 1 || r >= n) {
            return s;
        }
        String ans = new String();
        int t = r * 2 - 2;
        for (int i = 0; i < r; ++i) { // 枚举矩阵的行
            for (int j = 0; j + i < n; j += t) { // 枚举每个周期的起始下标
                ans += s.charAt(j + i); // 当前周期的第一个字符
                if (0 < i && i < r - 1 && j + t - i < n) {
                    ans += s.charAt(j + t - i); // 当前周期的第二个字符
                }
            }
        }
        return ans;
    }
}
