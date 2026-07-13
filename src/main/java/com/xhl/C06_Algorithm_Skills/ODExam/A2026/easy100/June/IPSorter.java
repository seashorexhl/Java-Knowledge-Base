package com.xhl.C06_Algorithm_Skills.ODExam.A2026.easy100.June;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: xhl
 * @Date: 2026-07-10 05:11
 * @Description:    0607核心代码编程-内网IP有效性校验-100分
 */
public class IPSorter {

    static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 1. 读取输入：读取一整行字符串，例如 ["10.2.3.4","192.168.1.1"]
        String line = scanner.nextLine().trim();

        // 2. 解析输入：去除首尾的 '[' 和 ']'，然后按 "," 分割
        // 注意：这里假设输入格式严格遵循题目给出的 JSON 数组字符串格式
        if (line.startsWith("[") && line.endsWith("]")) {
            line = line.substring(1, line.length() - 1);
        }

        String[] ipArray = line.split(",");
        List<String> validIps = new ArrayList<>();

        // 3. 遍历并校验每个 IP
        for (String ipStr : ipArray) {
            // 去除可能存在的前后空格和引号
            String ip = ipStr.trim().replace("\"", "");

            if (isValid(ip)) {
                validIps.add(ip);
            }
        }

        // 4. 排序：按"网段层级"（第二段、第三段、第四段）升序排序
        validIps.sort(Comparator.comparingInt((String ip) -> Integer.parseInt(ip.split("\\.")[1]))
                .thenComparingInt(ip -> Integer.parseInt(ip.split("\\.")[2]))
                .thenComparingInt(ip -> Integer.parseInt(ip.split("\\.")[3])));

        // 5. 格式化输出：按照题目要求的列表格式输出
        String result = validIps.stream()
                .map(ip -> "\"" + ip + "\"")
                .collect(Collectors.joining(",", "[", "]"));

        System.out.println(result);
        scanner.close();
    }

    public static boolean isValid(String ip) {
        String[] segs = ip.split("\\.");
        if (segs.length != 4) return false;
        for (String seg : segs) {
            if (!seg.matches("\\d+")) return false;
            if (seg.length() > 1 && seg.charAt(0) == '0') return false;
            int num = Integer.parseInt(seg);
            if (num < 0 || num > 255) return false;
        }
        return "10".equals(segs[0]);
    }

    public static List<String> sort(List<String> ips) {
        List<IPRecord> records = new ArrayList<>();
        for (String ip : ips) {
            if (!isValid(ip)) continue;
            String[] segs = ip.split("\\.");
            records.add(new IPRecord(
                    Integer.parseInt(segs[1]),
                    Integer.parseInt(segs[2]),
                    Integer.parseInt(segs[3]),
                    ip));
        }
        Collections.sort(records);
        return records.stream()
                .map(r -> r.ip)
                .collect(java.util.stream.Collectors.toList());
    }

    static class IPRecord implements Comparable<IPRecord> {
        int p2, p3, p4;
        String ip;

        public IPRecord(int p2, int p3, int p4, String ip) {
            this.p2 = p2;
            this.p3 = p3;
            this.p4 = p4;
            this.ip = ip;
        }

        @Override
        public int compareTo(IPRecord o) {
            if (p2 != o.p2) return Integer.compare(p2, o.p2);
            if (p3 != o.p3) return Integer.compare(p3, o.p3);
            return Integer.compare(p4, o.p4);
        }
    }

}
