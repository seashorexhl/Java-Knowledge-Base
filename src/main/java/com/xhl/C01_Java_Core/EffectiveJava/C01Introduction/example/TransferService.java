package com.xhl.C01_Java_Core.EffectiveJava.C01Introduction.example;

/**
 * @Author: xhl
 * @Date: 2026-07-04 01:54
 * @Description: 转账业务服务
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

    // 【81】优先使用并发工具而非 wait/notify
    private final Map<String, BankAccount> accountStore = new ConcurrentHashMap<>();

    public void addAccount(BankAccount account) {
        accountStore.put(account.getAccountId(), account);
    }

    // 【55】返回 Optional 而不是 null
    public Optional<BankAccount> getAccount(String id) {
        return Optional.ofNullable(accountStore.get(id));
    }

    // 【70】对编程错误使用运行时异常
    public void transfer(TransferRequest request) {
        BankAccount from = getAccount(request.fromAccountId())
                .orElseThrow(() -> new IllegalArgumentException("转出账户不存在"));

        BankAccount to = getAccount(request.toAccountId())
                .orElseThrow(() -> new IllegalArgumentException("转入账户不存在"));

        System.out.printf("转账成功: %s -> %s, 金额: %s%n",
                from.getAccountId(), to.getAccountId(), request.amount());
    }

    // 【45/46】Stream 谨慎使用，保持无副作用
    public List<BankAccount> findActiveAccounts() {
        return accountStore.values().stream()
                .filter(acc -> acc.getBalance().compareTo(java.math.BigDecimal.ZERO) > 0)
                .collect(Collectors.toList());
    }
}
