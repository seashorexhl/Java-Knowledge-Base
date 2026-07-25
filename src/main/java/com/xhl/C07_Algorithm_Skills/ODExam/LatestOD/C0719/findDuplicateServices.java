package com.xhl.C07_Algorithm_Skills.ODExam.LatestOD.C0719;

import java.util.*;

/**
 * @Author: xhl
 * @Date: 2026-07-21 11:44
 * @Description: 2026-7-19 酒店服务 记录分析
 *  programming
 */
public class findDuplicateServices {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String record = scanner.hasNextLine() ? scanner.nextLine().trim() : "";
        scanner.close();

        List<Character> ans = solve(record);
        StringBuilder output = new StringBuilder("[");
        for (int i = 0; i < ans.size(); i++) {
            if (i > 0) {
                output.append(",");
            }
            output.append("\"").append(ans.get(i)).append("\"");
        }
        output.append("]");
        System.out.println(output.toString());
    }
    static List<Character> solve(String record) {
        // 统计每种服务类型在整条记录中的出现次数
        int[] frequency = new int[26];
        for (int i = 0; i < record.length(); i++) {
            frequency[record.charAt(i) - 'a']++;
        }

        // 从左到右扫描，保证加入答案的顺序就是首次出现顺序
        boolean[] added = new boolean[26];
        List<Character> repeatedTypes = new ArrayList<>();
        for (int i = 0; i < record.length(); i++) {
            int index = record.charAt(i) - 'a';
            if (frequency[index] > 1 && !added[index]) {
                repeatedTypes.add(record.charAt(i));
                added[index] = true;
            }
        }

        // repeatedTypes 中只保存重复出现且第一次遇到的服务类型
        return repeatedTypes;
    }
    /**
     *  方法二:单次遍历 + 集合
     * */
    public static List<Character> solve1(String record) {
        Set<Character> seen = new HashSet<>();
        Set<Character> repeated = new LinkedHashSet<>();
        for (char c : record.toCharArray()) {
            // 如果已经见过，且还没加入重复集合，则加入
            if (!seen.add(c)) {
                repeated.add(c);
            }
        }
        return new ArrayList<>(repeated);
    }
    /**
     *  方法三: 数组 + 遍历
     * */
    public static List<Character> solve2(String record) {
        int[] count = new int[26];
        for (char c : record.toCharArray()) count[c - 'a']++;

        List<Character> res = new ArrayList<>();
        boolean[] added = new boolean[26];
        for (char c : record.toCharArray()) {
            if (count[c - 'a'] > 1 && !added[c - 'a']) {
                res.add(c);
                added[c - 'a'] = true;
            }
        }
        return res;
    }
}
