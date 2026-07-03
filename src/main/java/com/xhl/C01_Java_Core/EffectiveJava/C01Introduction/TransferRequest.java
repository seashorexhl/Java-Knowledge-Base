package com.xhl.C01_Java_Core.EffectiveJava.C01Introduction;

/**
 * @Author: xhl
 * @Date: 2026-07-04 01:02
 * @Description:  2.转账请求对象：TransferRequest
 * (涵盖：记录类、Optional、Lombok/Java 14+ 特性)
 */
import java.math.BigDecimal;
import java.util.Optional;

/**
 * 转账请求对象 (Java 14+ Record)
 * 涵盖规则：【16】访问方法替代公有域、【49】构造器校验、【55】Optional替代null
 */
// 【第16条】在公有类中使用访问方法而非公有域
// 【第55条】谨慎返回 null，优先返回 Optional 或空集合
// 【第68条】遵守广泛接受的命名约定
public record TransferRequest(
        String fromAccountId,
        String toAccountId,
        BigDecimal amount,
        Optional<String> remark // 【第55条】用 Optional 表达“可能缺失”的值
) {
    // 【第49条】在构造器中校验参数   紧凑构造器，用于参数校验
    public TransferRequest {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("转账金额必须大于 0");
        }
    }
}
