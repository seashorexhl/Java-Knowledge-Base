package com.xhl.C01_Java_Core.EffectiveJava.C01Introduction;

/**
 * @Author: xhl
 * @Date: 2026-07-04 01:04
 * @Description: 4. 业务服务层：TransferService
 * (涵盖：接口设计、异常处理、集合、Stream、资源管理)
 */
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * 转账业务服务
 * 涵盖规则：【45/46】Stream谨慎使用、【55】返回Optional、【81】并发工具类
 */
public class TransferService {

    // 【第81条】优先使用并发工具而非 wait 和 notify（这里用 ConcurrentHashMap）
    private final Map<String, BankAccount> accountStore = new ConcurrentHashMap<>();

    // 【第51条】方法签名要精心设计
    // 【第69条】只针对异常的情况才使用异常（不要用异常做流程控制）
    public void transfer(TransferRequest request) {
        // 【第55条】返回 Optional 而不是 null
        BankAccount from = getAccount(request.fromAccountId())
                .orElseThrow(() -> new IllegalArgumentException("转出账户不存在"));

        BankAccount to = getAccount(request.toAccountId())
                .orElseThrow(() -> new IllegalArgumentException("转入账户不存在"));

        // 模拟转账逻辑...
        System.out.printf("转账成功: %s -> %s, 金额: %s%n",
                from, to, request.amount());
    }

    // 【第45条】Stream 要谨慎使用，仅在合适时使用
    // 【第46条】Stream 中优先使用无副作用的函数
    public List<BankAccount> findActiveAccounts() {
        return accountStore.values().stream()
                .filter(acc -> acc.toString().contains("balance=100")) // 简单演示
                .collect(Collectors.toList());
    }

    // 【第55条】返回空集合而不是 null
    public Optional<BankAccount> getAccount(String id) {
        return Optional.ofNullable(accountStore.get(id));
    }

    // 【第70条】对可恢复的情况使用受检异常，对编程错误使用运行时异常
    // 上面的 IllegalArgumentException 就是运行时异常，因为调用方传错 ID 是编程错误
}