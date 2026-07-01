package com.xhl.C06_Algorithm_Skills.SelfSummary.AdvancedAL.Statistics;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

import static com.xhl.C06_Algorithm_Skills.SelfSummary.AdvancedAL.Statistics.UserGenerator.getUserList;


/**
 * @Author: xhl
 * @Date: 2026-06-24 18:32
 * @Description: 统计 模板
 */
public class summarize {
    static void main() {
        List<User> userList = getUserList();

        // 1. 基础类型 (int/Integer) 统计
        IntSummaryStatistics ageStats = userList.stream()
                .collect(Collectors.summarizingInt(User::getAge));

        System.out.println("员工总数: " + ageStats.getCount());
        System.out.println("年龄总和: " + ageStats.getSum());
        System.out.println("平均年龄: " + ageStats.getAverage());
        System.out.println("最大年龄: " + ageStats.getMax());
        System.out.println("最小年龄: " + ageStats.getMin());

        // 2. 浮点型 (double/Double) 统计
        DoubleSummaryStatistics salaryStats = userList.stream()
                .collect(Collectors.summarizingDouble(u -> u.getSalary().doubleValue()));
        // 按部门分组，并统计每个部门的薪资情况
        Map<String, DoubleSummaryStatistics> deptSalaryStats = userList.stream()
                .collect(Collectors.groupingBy(
                        User::getDepartment,
                        Collectors.summarizingDouble(u -> u.getSalary().doubleValue())
                ));

        // 遍历结果
        deptSalaryStats.forEach((dept, stats) -> {
            System.out.printf("部门: %s | 人数: %d | 平均薪资: %.2f%n",
                    dept, stats.getCount(), stats.getAverage());
        });
        List<User> userList1 = getUserList();

        // 1. 求和
        BigDecimal totalSalary = userList.stream()
                .map(User::getSalary)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // 2. 最大值 / 最小值
        BigDecimal maxSalary = userList.stream()
                .map(User::getSalary)
                .max(Comparator.naturalOrder())
                .orElse(BigDecimal.ZERO);

        // 3. 平均值 (注意处理除零和精度)
        BigDecimal avgSalary = userList.stream()
                .map(User::getSalary)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .divide(BigDecimal.valueOf(userList.size()), 2, RoundingMode.HALF_UP);
    }

}
