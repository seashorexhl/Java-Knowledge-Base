package com.xhl.C07_Algorithm_Skills.SelfSummary.AdvanceDS.Graph.middle;

/**
 * @Author: xhl
 * @Date: 2026-07-02 13:28
 * @Description:    210. 课程表 II
 */
public class findOrder {
    static void main() {
        int numCourses = 2;
        int[][] prerequisites = {{1,0}};
        findOrder fo = new findOrder();
        int[] order = fo.findOrder(numCourses, prerequisites);
        System.out.println(order[0] + " " + order[1]);

    }
    /**
     *  方法一：深度优先搜索
     * */
    public int[] findOrder(int numCourses, int[][] prerequisites) {


        return null;
    }
    /**
     *  方法二：广度优先搜索
     * */

}
