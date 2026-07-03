package com.xhl.C01_Java_Core.EffectiveJava.C01Introduction.example;

/**
 * @Author: xhl
 * @Date: 2026-07-04 01:56
 * @Description: 正常流程和异常拦截（防御性编程）的对比测试。
 *  可以清晰地看到《Effective Java》中关于不可变性、参数校验、Optional 替代 null、
 *  枚举行为绑定等核心规则是如何在真实业务代码中协同工作的
 */
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * 演示入口类
 * 将前面四个类的用法串联起来，验证 Effective Java 规则的实际运行效果。
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("===== 1. 使用 Builder 和 静态工厂方法创建对象 =====");
        // 【规则1】使用静态工厂方法
        BankAccount account1 = BankAccount.of("ACC-001", new BigDecimal("1000.00"));

        // 【规则2】使用 Builder 模式创建
        BankAccount account2 = new BankAccount.Builder("ACC-002", new BigDecimal("500.00")).build();

        // 【规则11】自动生成的 toString
        System.out.println(account1);
        System.out.println(account2);

        System.out.println("\n===== 2. 使用 Record 创建请求对象 =====");
        // 【规则55】使用 Optional 表达可能缺失的值
        TransferRequest validRequest = new TransferRequest(
                "ACC-001", "ACC-002", new BigDecimal("200.00"), Optional.of("买咖啡")
        );
        System.out.println("转账备注: " + validRequest.remark().orElse("无备注"));

        System.out.println("\n===== 3. 正常转账流程 =====");
        TransferService service = new TransferService();
        service.addAccount(account1);
        service.addAccount(account2);
        service.transfer(validRequest); // 预期：转账成功

        System.out.println("\n===== 4. 异常拦截演示 (防御性编程) =====");
        // 测试1：【规则49】参数校验拦截（金额不合法）
        try {
            new TransferRequest("ACC-001", "ACC-002", new BigDecimal("-50"), Optional.empty());
        } catch (IllegalArgumentException e) {
            System.out.println("捕获预期异常: " + e.getMessage());
        }

        // 测试2：【规则55/70】账户不存在时的 Optional 异常处理
        TransferRequest invalidRequest = new TransferRequest(
                "ACC-999", "ACC-002", new BigDecimal("10.00"), Optional.empty()
        );
        try {
            service.transfer(invalidRequest);
        } catch (IllegalArgumentException e) {
            System.out.println("捕获预期异常: " + e.getMessage());
        }

        System.out.println("\n===== 5. 枚举与 Stream 演示 =====");
        // 【规则35】枚举绑定业务行为
        System.out.println("当前状态是否允许转账: " + AccountStatus.ACTIVE.canTransfer());

        // 【规则45】Stream 过滤
        List<BankAccount> activeAccounts = service.findActiveAccounts();
        System.out.println("余额大于0的账户数量: " + activeAccounts.size());
    }
}
