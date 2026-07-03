package com.xhl.C01_Java_Core.EffectiveJava.C01Introduction.example;

/**
 * @Author: xhl
 * @Date: 2026-07-04 01:55
 * @Description: 银行工具类
 */
import java.util.Collections;
import java.util.List;

/**
 * 银行工具类
 * 涵盖规则：【4】私有构造器强化不可实例化、【22】接口仅定义类型（反模式演示）
 */
public final class BankUtils {

    public static final String CURRENCY_CNY = "CNY";

    // 【4】私有构造器，防止实例化
    private BankUtils() {
        throw new AssertionError("不允许实例化工具类");
    }

    // 【47】返回空集合而不是 null
    public static <T> List<T> emptyIfNull(List<T> list) {
        return list == null ? Collections.emptyList() : list;
    }
}