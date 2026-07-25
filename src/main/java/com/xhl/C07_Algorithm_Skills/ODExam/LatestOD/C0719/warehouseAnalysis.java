package com.xhl.C07_Algorithm_Skills.ODExam.LatestOD.C0719;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: xhl
 * @Date: 2026-07-21 11:45
 * @Description: 202607-19 物流仓储成本利润查询
 * 前缀和 + 字符串处理
 */
public class warehouseAnalysis {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder input = new StringBuilder();
        while (scanner.hasNextLine()) input.append(scanner.nextLine());
        scanner.close();

        String data = input.toString();
        int firstEnd = data.indexOf(']');
        int queryEnd = data.lastIndexOf("]]");
        int[] warehouses = extractInts(data.substring(0, firstEnd + 1));
        int[] queryNums = extractInts(data.substring(firstEnd + 1, queryEnd + 2));
        int numOfWarehouse = Integer.parseInt(data.substring(queryEnd + 2).trim());

        int[][] queries = new int[queryNums.length / 3][3];
        for (int i = 0, j = 0; i < queries.length; i++) {
            queries[i][0] = queryNums[j++];
            queries[i][1] = queryNums[j++];
            queries[i][2] = queryNums[j++];
        }

        List<List<Integer>> ans = solve(warehouses, queries, numOfWarehouse);
        StringBuilder output = new StringBuilder("[");
        for (int i = 0; i < ans.size(); i++) {
            if (i > 0) output.append(",");
            output.append("[").append(ans.get(i).get(0)).append(",").append(ans.get(i).get(1)).append(",").append(ans.get(i).get(2)).append("]");
        }
        output.append("]");
        System.out.println(output.toString());
    }
    /**
     * 前缀和 + 字符串处理
     * */
    static List<List<Integer>> solve(int[] warehouses, int[][] queries, int numOfWarehouse) {
        // 每个仓库每天有 3 个指标，因此可反推出每个仓库共有多少天数据
        int days = warehouses.length / (numOfWarehouse * 3);
        int[][] prefixIn = new int[numOfWarehouse][days + 1];
        int[][] prefixOut = new int[numOfWarehouse][days + 1];
        int[][] prefixLoss = new int[numOfWarehouse][days + 1];

        // 为每个仓库分别建立入库、出库、损耗三类前缀和
        for (int warehouse = 0; warehouse < numOfWarehouse; warehouse++) {
            int base = warehouse * days * 3;
            for (int day = 0; day < days; day++) {
                int index = base + day * 3;
                prefixIn[warehouse][day + 1] = prefixIn[warehouse][day] + warehouses[index];
                prefixOut[warehouse][day + 1] = prefixOut[warehouse][day] + warehouses[index + 1];
                prefixLoss[warehouse][day + 1] = prefixLoss[warehouse][day] + warehouses[index + 2];
            }
        }

        // 每个查询用前缀和 O(1) 得到区间累计值
        List<List<Integer>> report = new ArrayList<>();
        for (int[] query : queries) {
            int warehouse = query[0], startDay = query[1], endDay = query[2];
            int totalIn = prefixIn[warehouse][endDay + 1] - prefixIn[warehouse][startDay];
            int totalOut = prefixOut[warehouse][endDay + 1] - prefixOut[warehouse][startDay];
            int totalLoss = prefixLoss[warehouse][endDay + 1] - prefixLoss[warehouse][startDay];
            int netProfit = totalOut - totalIn;
            int denominator = totalIn + totalOut;
            int risk = denominator > 0 && totalLoss * 100 > 5 * denominator ? 1 : 0;

            List<Integer> row = new ArrayList<>();
            row.add(netProfit);
            row.add(totalLoss);
            row.add(risk);
            report.add(row);
        }
        return report;
    }

    static int[] extractInts(String text) {
        Matcher matcher = Pattern.compile("-?\\d+").matcher(text);
        List<Integer> values = new ArrayList<>();
        while (matcher.find()) values.add(Integer.parseInt(matcher.group()));
        int[] nums = new int[values.size()];
        for (int i = 0; i < values.size(); i++) nums[i] = values.get(i);
        return nums;
    }



}
