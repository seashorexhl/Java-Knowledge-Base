package com.xhl.C06_Algorithm_Skills.SelfSummary.AdvancedAL.Statistics;

/**
 * @Author: xhl
 * @Date: 2026-06-24 18:40
 * @Description:
 */
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class UserGenerator {
    public static List<User> getUserList() {
        return Arrays.asList(
                new User(1L, "张三", 25, new BigDecimal("8000.00"), "技术部"),
                new User(2L, "李四", 28, new BigDecimal("12000.50"), "技术部"),
                new User(3L, "王五", 32, new BigDecimal("15000.00"), "产品部"),
                new User(4L, "赵六", 24, new BigDecimal("7500.00"), "运营部"),
                new User(5L, "孙七", 29, new BigDecimal("18000.00"), "产品部")
        );
    }
}
