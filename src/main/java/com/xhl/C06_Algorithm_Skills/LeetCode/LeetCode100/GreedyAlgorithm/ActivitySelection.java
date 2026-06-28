package com.xhl.Algorithm_Skills.LeetCode.LeetCode100.GreedyAlgorithm;

/**
 * @Author: xhl
 * @Date: 2026-06-16 04:13
 * @Description: 贪心策略 模板
 *  经典案例：活动选择问题
 */

import java.util.*;

/**
 * 活动选择问题：在给定活动集合中，选择最大兼容活动子集
 * 每个活动有开始时间和结束时间，活动不能重叠
 */
public class ActivitySelection {

    public static List<Activity> selectActivities(Activity[] activities) {
        // 1. 按结束时间升序排序（关键贪心策略）
        Arrays.sort(activities, Comparator.comparingInt(a -> a.end));

        List<Activity> selected = new ArrayList<>();

        // 2. 选择第一个活动（结束时间最早的）
        selected.add(activities[0]);
        int lastSelectedEnd = activities[0].end;

        // 3. 贪心选择：选择下一个开始时间不早于上一个结束时间的活动
        for (int i = 1; i < activities.length; i++) {
            if (activities[i].start >= lastSelectedEnd) {
                selected.add(activities[i]);
                lastSelectedEnd = activities[i].end;
            }
        }

        return selected;
    }

    public static void main(String[] args) {
        Activity[] activities = {
                new Activity(1, 4),
                new Activity(3, 5),
                new Activity(0, 6),
                new Activity(5, 7),
                new Activity(3, 9),
                new Activity(5, 9),
                new Activity(6, 10),
                new Activity(8, 11),
                new Activity(8, 12),
                new Activity(2, 14),
                new Activity(12, 16)
        };

        List<Activity> selected = selectActivities(activities);

        System.out.println("选择的活动: " + selected);
        System.out.println("最大兼容活动数量: " + selected.size());
    }

    static class Activity {
        int start;
        int end;

        public Activity(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public String toString() {
            return "[" + start + ", " + end + "]";
        }
    }
}