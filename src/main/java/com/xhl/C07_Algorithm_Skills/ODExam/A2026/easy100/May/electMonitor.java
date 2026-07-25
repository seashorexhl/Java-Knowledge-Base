package com.xhl.C07_Algorithm_Skills.ODExam.A2026.easy100.May;

import java.util.*;

/**
 * @Author: xhl
 * @Date: 2026-07-10 03:07
 * @Description: 小学生班长选举-100分
 * 字 符串处理 + 哈希表  + 集合操作
 */
public class electMonitor {
    static void main(String[] args) {
        List<String> students = new ArrayList<>();
        students.add("Zhangsan");
        students.add("Lisi");
        students.add("Wangwu");
        List<String> votes = new ArrayList<>();
        votes.add("Zhangsan");
        votes.add("Lisi");
        votes.add("Zhangsan");

        String elected = electMonitor(students, votes);
        System.out.println(elected);
    }
    /**
     * 字符串处理 + 哈希表 + 集合操作
     *  */
    public static String electMonitor(List<String> students, List<String> votes) {
        //
        int n = students.size();
        if (votes.size() > 3 * n) {
            return "Invalid election";
        }

        Map<String, String[]> studentTokens = new HashMap<>();
        Map<String, Integer> voteCount = new HashMap<>();
        for (String s : students) {
            studentTokens.put(s, s.split("-"));
            voteCount.put(s, 0);
        }

        for (String vote : votes) {
            String[] voteTokens = vote.split("-");
            if (voteTokens.length == 0) continue;

            List<String> matches = new ArrayList<>();
            for (String s : students) {
                String[] tokens = studentTokens.get(s);
                if (isContinuousSubsequence(voteTokens, tokens)) {
                    matches.add(s);
                }
            }

            if (matches.size() == 1) {
                voteCount.put(matches.get(0), voteCount.get(matches.get(0)) + 1);
            }
        }

        int maxVotes = 0;
        for (int count : voteCount.values()) {
            if (count > maxVotes) maxVotes = count;
        }
        if (maxVotes == 0) return "Invalid election";

        for (String s : students) {
            if (voteCount.get(s) > n) return "Invalid election";
        }

        List<String> candidates = new ArrayList<>();
        for (String s : students) {
            if (voteCount.get(s) == maxVotes) {
                candidates.add(s);
            }
        }
        Collections.sort(candidates);
        return candidates.get(0);
    }
    //
    private static boolean isContinuousSubsequence(String[] voteTokens, String[] studentTokens) {
        int n = voteTokens.length;
        int m = studentTokens.length;
        if (n > m) return false;
        for (int i = 0; i <= m - n; i++) {
            boolean match = true;
            for (int j = 0; j < n; j++) {
                if (!voteTokens[j].equals(studentTokens[i + j])) {
                    match = false;
                    break;
                }
            }
            if (match) return true;
        }
        return false;

    }
}
