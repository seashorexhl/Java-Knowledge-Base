package com.xhl.C07_Algorithm_Skills.SelfSummary.BaseAL.interval.middle;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @Author: xhl
 * @Date: 2026-07-02 06:28
 * @Description: 452. 用最少数量的箭引爆气球
 */
public class findMinArrowShots {
    static void main() {
        int[][] points = {{10,16},{2,8},{1,6},{7,12}};
        findMinArrowShots poi = new findMinArrowShots();
        int shots = poi.findMinArrowShots(points);
        System.out.println(shots);
    }

    /**
     *  方法一：排序 + 贪心
     * */
    public int findMinArrowShots(int[][] points) {
        if (points.length == 0) {
            return 0;
        }
        Arrays.sort(points, new Comparator<int[]>() {
            public int compare(int[] point1, int[] point2) {
                if (point1[1] > point2[1]) {
                    return 1;
                } else if (point1[1] < point2[1]) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });
        int pos = points[0][1];
        int ans = 1;
        for (int[] balloon: points) {
            if (balloon[0] > pos) {
                pos = balloon[1];
                ++ans;
            }
        }
        return ans;
    }

}
