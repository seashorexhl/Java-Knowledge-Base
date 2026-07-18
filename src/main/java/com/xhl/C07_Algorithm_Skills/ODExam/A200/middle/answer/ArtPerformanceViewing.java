package com.xhl.C07_Algorithm_Skills.ODExam.A200.middle.answer;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 * @Author: xhl
 * @Date: 2026-06-26 10:33
 * @Description: 03 Art Performance Viewing 观看文艺汇演问题
 *  贪心算法 +区间调度问题
 */
public class ArtPerformanceViewing {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        // 输入演出场数
        int n = in.nextInt();

        // 创建一个列表来存储演出时间表
        List<List<Integer>> schedule = new ArrayList<>();

        // 循环读取每个演出的开始时间和持续时间，并将其添加到演出时间表中
        for (int i = 0; i < n; i++) {
            int startTime = in.nextInt();
            int endTime = startTime + in.nextInt();
            schedule.add(List.of(startTime, endTime));
        }

        // 将演出时间表按照结束时间进行排序
        schedule.sort(Comparator.comparingInt(a -> a.get(1)));

        // 获取第一个演出的结束时间和初始化观看的演出场数
        int firstEndTime = schedule.get(0).get(1);
        int numShows = 1;

        // 遍历演出时间表中的每个演出时间段
        for (List<Integer> interval : schedule) {
            int startTime = interval.get(0);
            int endTime = interval.get(1);

            // 如果当前演出的开始时间与前一个演出的结束时间间隔大于等于15分钟，则可以观看该演出
            if (startTime - firstEndTime >= 15) {
                numShows++;
                firstEndTime = endTime;
            }
        }

        // 输出最多能观看的演出场数
        System.out.println(numShows);
    }
}
