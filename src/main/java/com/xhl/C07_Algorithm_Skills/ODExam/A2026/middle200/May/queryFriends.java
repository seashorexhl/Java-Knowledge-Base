package com.xhl.C07_Algorithm_Skills.ODExam.A2026.middle200.May;

import java.util.*;

/**
 * @Author: xhl
 * @Date: 2026-07-10 02:54
 * @Description: 社交网络相同爱好好友查询-200分
 * 1.数据预处理：
 * 2.BFS 广度优先搜索
 * 3.兴趣匹配
 * 4.结果排序与输出
 */
public class queryFriends {

    static void main(String[] args) {
        List<List<String>> nodes = new ArrayList<>();
        List<List<String>> relations =  new ArrayList<>();
        String myId = "0";
        int maxHop =  2;
        queryFriends qf = new queryFriends();
        qf.queryFriends(nodes,relations,myId,maxHop);
    }
    /**
     *
     * */
    public List<List<String>> queryFriends(List<List<String>> nodes, List<List<String>> relations, String myId, int maxHop) {
        // 预处理用户兴趣
        Map<String, Set<String>> interests = new HashMap<>();
        for (List<String> node : nodes) {
            String userId = node.get(0);
            Set<String> set = new HashSet<>(node.subList(1, node.size()));
            interests.put(userId, set);
        }

        // 构建有向图（忽略无效关系）
        Map<String, List<String>> graph = new HashMap<>();
        Set<String> validUsers = new HashSet<>(interests.keySet());
        for (List<String> rel : relations) {
            String follower = rel.get(0);
            String followee = rel.get(1);
            if (validUsers.contains(follower) && validUsers.contains(followee)) {
                graph.computeIfAbsent(follower, k -> new ArrayList<>()).add(followee);
            }
        }

        // BFS计算最短距离
        Map<String, Integer> dist = new HashMap<>();
        Queue<String> queue = new LinkedList<>();
        dist.put(myId, 0);
        queue.offer(myId);
        int maxK = Math.min(maxHop, 100); // 跳数上限处理

        while (!queue.isEmpty()) {
            String current = queue.poll();
            int currentDist = dist.get(current);
            if (currentDist >= maxK) continue;
            List<String> neighbors = graph.get(current);
            if (neighbors != null) {
                for (String neighbor : neighbors) {
                    if (!dist.containsKey(neighbor)) {
                        dist.put(neighbor, currentDist + 1);
                        queue.offer(neighbor);
                    }
                }
            }
        }

        // 收集并过滤结果
        List<List<String>> result = new ArrayList<>();
        Set<String> startInterests = interests.get(myId);
        for (Map.Entry<String, Integer> entry : dist.entrySet()) {
            String user = entry.getKey();
            if (user.equals(myId)) continue; // 排除起始用户
            Set<String> common = new HashSet<>(interests.get(user));
            common.retainAll(startInterests);
            if (!common.isEmpty()) {
                List<String> sortedCommon = new ArrayList<>(common);
                Collections.sort(sortedCommon); // ASCII码序排序
                List<String> item = new ArrayList<>();
                item.add(user);
                item.addAll(sortedCommon);
                result.add(item);
            }
        }

        // 用户排序：先距离升序，再ID整数值升序
        result.sort((a, b) -> {
            int distA = dist.get(a.get(0));
            int distB = dist.get(b.get(0));
            if (distA != distB) return distA - distB;
            return Integer.parseInt(a.get(0)) - Integer.parseInt(b.get(0));
        });
        return result;
    }
}