package com.xhl.C01_Java_Core.EffectiveJava.C01Introduction.example;

/**
 * @Author: xhl
 * @Date: 2026-07-04 01:52
 * @Description: 银行账户实体类
 */

import java.math.BigDecimal;
import java.util.Objects;

/**
 * 银行账户实体类
 * 涵盖规则：【1】静态工厂方法、【2】Builder模式、【10/11】equals/hashCode/toString、
 * 【14】Comparable接口、【17】最小化可变性、【49】参数校验
 */
public final class BankAccount implements Comparable<BankAccount> {

    private final String accountId;
    private final BigDecimal balance;

    // 【17】私有构造器，强化不可变性
    private BankAccount(Builder builder) {
        // 【49】防御性参数校验
        Objects.requireNonNull(builder.accountId, "accountId 不能为 null");
        if (builder.balance.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("余额不能为负数");
        }
        this.accountId = builder.accountId;
        this.balance = builder.balance;
    }

    // 【1】静态工厂方法代替公有构造器
    public static BankAccount of(String accountId, BigDecimal balance) {
        return new Builder(accountId, balance).build();
    }

    public String getAccountId() {
        return accountId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    // 【10】覆盖 equals 必须覆盖 hashCode
    // 【11】覆盖 toString
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

    // 【14】实现 Comparable 接口
    @Override
    public int compareTo(BankAccount other) {
        return this.accountId.compareTo(other.accountId);
    }

    // 【2】Builder 模式
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
