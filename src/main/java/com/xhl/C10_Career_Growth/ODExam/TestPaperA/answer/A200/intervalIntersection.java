package com.xhl.C10_Career_Growth.ODExam.TestPaperA.answer.A200;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @Author: xhl
 * @Date: 2026-07-04 20:33
 * @Description: 02  interval intersection  区间交集
 */
public class intervalIntersection {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int[][] ranges = new int[n][2];
        for (int i = 0; i < n; i++) {
            ranges[i][0] = sc.nextInt();
            ranges[i][1] = sc.nextInt();
        }

        getResult(n, ranges);
    }
    //
    public static void getResult(int n, int[][] ranges) {
        // 区间按照开始位置升序
        Arrays.sort(ranges, (a, b) -> a[0] - b[0]);

        // combine用于保存交集
        ArrayList<int[]> combine = new ArrayList<>();

        // 求任意两个区间之间的交集
        for (int i = 0; i < n; i++) {
            int s1 = ranges[i][0], e1 = ranges[i][1];
            for (int j = i + 1; j < n; j++) {
                int s2 = ranges[j][0], e2 = ranges[j][1];
                if (s2 <= e1) {
                    combine.add(new int[] {s2, Math.min(e1, e2)});
                } else {
                    // 由于ranges已经升序，因此如果ranges[i]和ranges[j]没有交集的话，则也不可能和ranges[j+1]区间有交集
                    break;
                }
            }
        }

        if (combine.size() == 0) {
            System.out.println("None");
            return;
        }

        // 合并公共区间
        combine.sort((a, b) -> a[0] != b[0] ? a[0] - b[0] : b[1] - a[1]);

        int[] pre = combine.get(0);
        for (int i = 1; i < combine.size(); i++) {
            int[] cur = combine.get(i);

            if (pre[1] >= cur[0]) {
                pre[1] = Math.max(cur[1], pre[1]);
            } else {
                System.out.println(pre[0] + " " + pre[1]);
                pre = cur;
            }
        }

        System.out.println(pre[0] + " " + pre[1]);
    }


}
