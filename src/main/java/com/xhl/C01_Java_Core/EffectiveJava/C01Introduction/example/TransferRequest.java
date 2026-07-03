package com.xhl.C01_Java_Core.EffectiveJava.C01Introduction.example;

/**
 * @Author: xhl
 * @Date: 2026-07-04 01:52
 * @Description: 转账请求对象
 */
import java.math.BigDecimal;
import java.util.Optional;

/**
 * 转账请求对象 (Java 14+ Record)
 * 涵盖规则：【16】访问方法替代公有域、【49】构造器校验、【55】Optional替代null
 */
public record TransferRequest(
        String fromAccountId,
        String toAccountId,
        BigDecimal amount,
        Optional<String> remark
) {
    // 紧凑构造器，用于参数校验
    public TransferRequest {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("转账金额必须大于 0");
        }
    }
}
