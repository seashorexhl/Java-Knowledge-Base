package com.xhl.C07_Algorithm_Skills.ODExam.A2026.middle200.Apirl;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author: xhl
 * @Date: 2026-07-14 13:33
 * @Description: 题目三 直捣黄龙 200分 ⭐⭐⭐
 *  BFS
 */
public class findShortestPath {

    static void main(String[] args) {
        int n = 3;
        // 哨兵放在左上角和右下角，不影响中间的最短路径
        Point[] soldiers = {new Point(0, 0), new Point(2, 2)};
        findShortestPath fsp = new findShortestPath();
        int[] shortestPath = fsp.findShortestPath(n, soldiers);
        System.out.println(Arrays.toString(shortestPath));
        // 预期输出: [2, 2] (2条最短路径，长度为2)
    }
    /**
     *  广度优先搜索
     * */
    public int[] findShortestPath(int n, Point[] soldiers) {
        int[] result = new int[2]; // [count, length]

        boolean[][] safe = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                safe[i][j] = true;
            }
        }

        for (Point s : soldiers) {
            for (int dx = -1; dx <= 1; dx++) {
                for (int dy = -1; dy <= 1; dy++) {
                    int nx = s.x + dx;
                    int ny = s.y + dy;
                    if (nx >= 0 && nx < n && ny >= 0 && ny < n) {
                        safe[nx][ny] = false;
                    }
                }
            }
        }

        int startX = 0, startY = n / 2;
        int endX = n - 1, endY = n / 2;
        if (!safe[startX][startY] || !safe[endX][endY]) {
            return result;
        }

        int[][] dist = new int[n][n];
        int[][] count = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dist[i][j] = Integer.MAX_VALUE;
                count[i][j] = 0;
            }
        }

        Queue<Point> queue = new LinkedList<>();
        dist[startX][startY] = 0;
        count[startX][startY] = 1;
        queue.add(new Point(startX, startY));

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        while (!queue.isEmpty()) {
            Point cur = queue.poll();
            int x = cur.x, y = cur.y;
            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d], ny = y + dy[d];
                if (nx >= 0 && nx < n && ny >= 0 && ny < n && safe[nx][ny]) {
                    if (dist[nx][ny] == Integer.MAX_VALUE) {
                        dist[nx][ny] = dist[x][y] + 1;
                        count[nx][ny] = count[x][y];
                        queue.add(new Point(nx, ny));
                    } else if (dist[nx][ny] == dist[x][y] + 1) {
                        count[nx][ny] += count[x][y];
                    }
                }
            }
        }

        if (dist[endX][endY] != Integer.MAX_VALUE) {
            result[0] = count[endX][endY];
            result[1] = dist[endX][endY];
        }
        return result;
    }

    static class Point {
        int x, y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}
