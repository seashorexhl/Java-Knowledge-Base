package com.xhl.C01_Java_Core.EffectiveJava.C01Introduction;

/**
 * @Author: xhl
 * @Date: 2026-07-04 01:02
 * @Description: 1. 核心领域对象：BankAccount
 * (涵盖：创建与初始化、不可变性、Builder模式、equals/hashCode、Comparable)
 */
import java.math.BigDecimal;
import java.util.Objects;

/**
 * 银行账户实体类
 * 涵盖规则：【1】静态工厂方法、【2】Builder模式、【10/11】equals/hashCode/toString、
 * 【14】Comparable接口、【17】最小化可变性、【49】参数校验
 */
// 【第2条】遇到多个构造器参数时，考虑用 Builder 模式
// 【第17条】使可变性最小化（这里设计为不可变对象）
// 【第50条】在需要时制作保护性拷贝（构造器里拷贝了 BigDecimal）
public final class BankAccount implements Comparable<BankAccount> {
    private final String accountId;
    private final BigDecimal balance;
    // 【17】私有构造器，强化不可变性
    private BankAccount(Builder builder) {
        // 【第49条】参数校验（防御性编程）
        Objects.requireNonNull(builder.accountId, "accountId 不能为 null");
        if (builder.balance.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("余额不能为负数");
        }
        this.accountId = builder.accountId;
        this.balance = builder.balance; // BigDecimal 本身不可变，无需拷贝
    }

    // 【第1条】考虑用静态工厂方法代替构造器
    public static BankAccount of(String accountId, BigDecimal balance) {
        return new Builder(accountId, balance).build();
    }

    // 【第10条】覆盖 Object 的 equals 时总要覆盖 hashCode
    // 【第11条】总要覆盖 toString
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BankAccount)) return false;
        BankAccount that = (BankAccount) o;
        return accountId.equals(that.accountId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountId);
    }

    @Override
    public String toString() {
        return "BankAccount{id='" + accountId + "', balance=" + balance + "}";
    }

    // 【第14条】考虑实现 Comparable 接口
    @Override
    public int compareTo(BankAccount other) {
        return this.accountId.compareTo(other.accountId);
    }

    // 内部 Builder
    public static final class Builder {
        private final String accountId;
        private final BigDecimal balance;

        public Builder(String accountId, BigDecimal balance) {
            this.accountId = accountId;
            this.balance = balance;
        }

        public BankAccount build() {
            return new BankAccount(this);
        }
    }
}
