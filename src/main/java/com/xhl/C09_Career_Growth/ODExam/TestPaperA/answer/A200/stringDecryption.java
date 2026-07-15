package com.xhl.C09_Career_Growth.ODExam.TestPaperA.answer.A200;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

/**
 * @Author: xhl
 * @Date: 2026-07-04 20:39
 * @Description: 05 string decryption 字符串解密  字符串拼接
 */
public class stringDecryption {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String str1 = sc.next();
        String str2 = sc.next();

        System.out.println(getResult(str1, str2));
    }

    public static String getResult(String str1, String str2) {
        String reg = "[0-9a-f]+";

        String[] valids = str1.split(reg);

        int count = getDistinctCount(str2);

        String[] ans = Arrays.stream(valids).filter(valid -> !"".equals(valid) && getDistinctCount(valid) <= count).toArray(String[]::new);

        if(ans.length == 0) return "Not Found";

        Arrays.sort(ans, (a,b)->{
            int c1 = getDistinctCount(a);
            int c2 = getDistinctCount(b);
            return c1 != c2 ? c2 - c1 : b.compareTo(a);
        });

        return ans[0];
    }

    public static int getDistinctCount(String str) {
        HashSet<Character> set = new HashSet<>();
        for (char c : str.toCharArray()) {
            set.add(c);
        }
        return set.size();
    }
}
