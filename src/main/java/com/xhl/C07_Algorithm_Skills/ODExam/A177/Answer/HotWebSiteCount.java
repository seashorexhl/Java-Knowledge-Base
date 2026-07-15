package com.xhl.C09_Career_Growth.ODExam.A177.Answer;

import java.util.*;

/**
 * @Author: xhl
 * @Date: 2026-04-11 23:01
 * @Description: 05 热点网站统计
 */
public class HotWebSiteCount {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 创建一个字符串列表，用于存储输入的每一行数据
        List<String> lines = new ArrayList<>();
        // 创建一个哈希表，用于存储每个 URL 出现的次数
        Map<String, Integer> cache = new HashMap<>();
        // 不断读取输入，直到没有下一行
        while (sc.hasNext()) {
            // 读取一行数据
            String line = sc.nextLine();
            // 将该行数据添加到字符串列表中
            lines.add(line);
            // 如果该行数据只包含数字，说明已经读取完了一个测试用例
            if (line.matches("^\\d+$")) {
                // 调用 sortURL 方法进行处理，并输出结果
                System.out.println(sortURL(lines, cache));
                // 清空字符串列表，为下一个测试用例做准备
                lines.clear();
            }
        }

    }

    // 排序URL链接
    private static String sortURL(List<String> lines, Map<String, Integer> cache) {
        // 从字符串列表中取出最后一个元素，即需要输出的 URL 数量
        int n = Integer.parseInt(lines.remove(lines.size() - 1));
        // 遍历字符串列表中的每一个 URL
        for (String url : lines) {
            // 将该 URL 在哈希表中的计数加一
            cache.put(url, cache.getOrDefault(url, 0) + 1);
        }
        // 将哈希表中的每一项转换成一个键值对，并存入一个列表中
        List<Map.Entry<String, Integer>> list = new ArrayList<>(cache.entrySet());
        // 对列表进行排序，按照计数从大到小排序，如果计数相同则按照字典序从小到大排序
        list.sort((a, b) -> {
            int res = b.getValue() - a.getValue();
            return res == 0 ? a.getKey().compareTo(b.getKey()) : res;
        });
        StringBuilder sb = new StringBuilder();
        // 取出前 n 个 URL，并将它们拼接成一个字符串
        for (int i = 0; i < n && i < list.size(); i++) {
            sb.append(list.get(i).getKey()).append(",");
        }
        // 如果字符串不为空，则删除最后一个逗号
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        // 返回拼接好的字符串
        return sb.toString();
    }

}
