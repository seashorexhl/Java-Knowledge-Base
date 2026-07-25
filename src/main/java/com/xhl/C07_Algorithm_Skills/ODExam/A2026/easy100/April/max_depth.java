package com.xhl.C07_Algorithm_Skills.ODExam.A2026.easy100.April;



import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author: xhl
 * @Date: 2026-07-10 03:11
 * @Description:    企业内部门的最大层级-100分
 *  层序遍历（BFS）
 */
public class max_depth {
    static void main(String[] args) {
        String[] arr = {"1","#","2","#","3","#","4","#","5"};
        max_depth md = new max_depth();
        System.out.println("企业内部门的最大层级: "+ md.max_depth(arr));
    }
    /**
     *  深度优先遍历
     * */
    public int max_depth(String[] arr) {
        if (arr.length == 0 || "#".equals(arr[0])) return 0;
        int n = arr.length;
        int nextIndex = 1;
        Queue<Integer> depths = new LinkedList<>();
        depths.add(1);
        int maxDepth = 1;
        while (!depths.isEmpty() && nextIndex < n) {
            int depth = depths.poll();
            for (int i = 0; i < 2; i++) {
                if (nextIndex >= n) break;
                if (!"#".equals(arr[nextIndex])) {
                    int childDepth = depth + 1;
                    maxDepth = Math.max(maxDepth, childDepth);
                    depths.add(childDepth);
                }
                nextIndex++;
            }
        }
        return maxDepth;
    }
}