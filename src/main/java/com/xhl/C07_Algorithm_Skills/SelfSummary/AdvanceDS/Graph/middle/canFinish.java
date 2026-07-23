package com.xhl.C07_Algorithm_Skills.SelfSummary.AdvanceDS.Graph.middle;

/**
 * @Author: xhl
 * @Date: 2026-07-02 13:27
 * @Description: 207. 课程表
 *
 */
public class canFinish {
    static void main() {
        int numCourses  = 2;
        int[][] prerequisites = {{1,0}};
        canFinish cf = new  canFinish();
        boolean canFinish = cf.canFinish(numCourses, prerequisites);
        System.out.println("是否可能完成所有课程的学习?: " + canFinish);
    }
    /**
     *  方法一：深度优先搜索
     * */
    public boolean canFinish(int numCourses, int[][] prerequisites) {


        return false;
    }
    /**
     *  方法二: 广度优先搜索
     * */


}
