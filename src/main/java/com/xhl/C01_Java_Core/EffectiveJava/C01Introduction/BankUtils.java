package com.xhl.C01_Java_Core.EffectiveJava.C01Introduction;

/**
 * @Author: xhl
 * @Date: 2026-07-04 01:04
 * @Description: 5. 工具类与常量：BankUtils
 * (涵盖：工具类设计、常量接口反模式)
 */
// 【第4条】通过私有构造器强化不可实例化能力
// 【第22条】用接口仅定义类型（反模式：不要用常量接口）
public final class BankUtils {

    // 【第34条】用 enum 代替 int 常量（即使是常量，也尽量用枚举或类+私有构造）
    public static final String CURRENCY_CNY = "CNY";

    private BankUtils() {
        throw new AssertionError("不允许实例化工具类");
    }

    // 【第47条】返回集合或数组时优先返回空集合/数组，而不是 null
    public static <T> java.util.List<T> emptyIfNull(java.util.List<T> list) {
        return list == null ? java.util.Collections.emptyList() : list;
    }
}
