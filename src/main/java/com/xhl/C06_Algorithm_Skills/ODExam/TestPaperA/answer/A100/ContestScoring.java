package com.xhl.C06_Algorithm_Skills.ODExam.TestPaperA.answer.A100;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringJoiner;

/**
 * @Author: xhl
 * @Date: 2026-06-06 12:04
 * @Description: 04 ContestScoring 比赛评分
 */
public class ContestScoring {
    // 输入获取
    public static void main(String[] args) {
        // 将输入分隔符设置为“,”或者换行
        Scanner sc = new Scanner(System.in).useDelimiter("[,\n]");

        int m = sc.nextInt();
        int n = sc.nextInt();

        int[][] scores = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                scores[i][j] = sc.nextInt();
            }
        }

        System.out.println(getResult(m, n, scores));
    }

    // 算法入口
    public static String getResult(int m, int n, int[][] scores) {
        if (m < 3 || m > 10 || n < 3 || n > 100) return "-1";

        HashMap<Integer, Integer[]> players = new HashMap<>();

        for (int j = 0; j < n; j++) { // 列
            Integer[] player = new Integer[m];
            for (int i = 0; i < m; i++) { // 行
                if (scores[i][j] > 10 || scores[i][j] < 1) return "-1"; // 队员得分1~10合法，否则不合法
                player[i] = scores[i][j];
            }
            Arrays.sort(player, (a, b) -> b - a); // 将每个队员的得分降序
            players.put(j, player);
        }
        // 字符串拼接器
        StringJoiner sj = new StringJoiner(",");

        players.entrySet().stream()
                .sorted(
                        (a, b) -> {
                            Integer[] playerA = a.getValue();
                            Integer[] playerB = b.getValue();

                            int sumA = sum(playerA);
                            int sumB = sum(playerB);

                            if (sumA != sumB) { // 按总分降序
                                return sumB - sumA;
                            }

                            for (int i = 0; i < m; i++) {
                                if (playerA[i] == playerB[i]) continue;
                                return playerB[i] - playerA[i]; // 按最高分降序
                            }

                            return 0;
                        })
                .limit(3)
                .forEach(p -> sj.add(p.getKey() + 1 + ""));

        return sj.toString();
    }

    public static int sum(Integer[] arr) {
        return Arrays.stream(arr).reduce(Integer::sum).orElse(0);
    }
}
