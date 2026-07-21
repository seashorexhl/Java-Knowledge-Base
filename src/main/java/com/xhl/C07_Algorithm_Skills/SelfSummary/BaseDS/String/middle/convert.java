package com.xhl.C07_Algorithm_Skills.SelfSummary.BaseDS.String.middle;

/**
 * @Author: xhl
 * @Date: 2026-07-09 00:53
 * @Description: 6. Z 字形变换
 *
 */
public class convert {
    static void main() {
        String s = "PAYPALISHIRING";
        int numRows = 3;
        convert cv = new convert();
        String convert = cv.convert(s, numRows);


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
    public String convert1(String s, int numRows) {
        int n = s.length(), r = numRows;
        if (r == 1 || r >= n) {
            return s;
        }
        StringBuffer[] mat = new StringBuffer[r];
        for (int i = 0; i < r; ++i) {
            mat[i] = new StringBuffer();
        }
        for (int i = 0, x = 0, t = r * 2 - 2; i < n; ++i) {
            mat[x].append(s.charAt(i));
            if (i % t < r - 1) {
                ++x;
            } else {
                --x;
            }
        }
        StringBuffer ans = new StringBuffer();
        for (StringBuffer row : mat) {
            ans.append(row);
        }
        return ans.toString();
    }

    /**
     *  方法三：直接构造
     * */
    public String convert2(String s, int numRows) {

        int n = s.length(), r = numRows;
        if (r == 1 || r >= n) {
            return s;
        }
        String ans = null;
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
