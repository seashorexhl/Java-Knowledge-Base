package com.xhl.C01_Java_Core.EffectiveJava.C10Exceptions;

/**
 * @Author: xhl
 * @Date: 2026-07-01 09:15
 * @Description: 银行转账服务（BankTransferService）
 */
import java.io.IOException;
import java.util.Objects;

public class BankTransferService {

    // 【修正1】将占位方法提取到类级别，它们现在是独立的类成员方法
    private boolean accountExists(String acc) {
        return true;
    }

    // 【修正2】统一变量名为 accountId，避免命名混淆
    private double getBalance(String accountId) {
        return 0;
    }

    private void deductBalance(String acc, double amt) {}
    private void addBalance(String acc, double amt) {}
    private void restoreBalance(String acc, double amt) {}
    private void riskyOperation() throws IOException {}

    // 核心转账方法
    public void transfer(String fromAccount, String toAccount, double amount) {
        // 1. 常规检查
        if (!accountExists(fromAccount)) {
            throw new IllegalArgumentException("Source account does not exist: " + fromAccount);
        }
        if (getBalance(fromAccount) < amount) {
            throw new IllegalStateException("Insufficient funds for account: " + fromAccount);
        }

        // 2. 真正的异常处理
        try {
            executeNetworkTransfer(fromAccount, toAccount, amount);
        } catch (IOException e) {
            throw new TransferFailedException("Network error during transfer", e);
        }
    }

    public double getBalancePublic(String accountId) throws AccountNotFoundException {
        Objects.requireNonNull(accountId, "Account ID cannot be null");
        if (!accountExists(accountId)) {
            throw new AccountNotFoundException("Account not found: " + accountId);
        }
        return 1000.0;
    }

    private void executeNetworkTransfer(String from, String to, double amount) throws IOException {
        throw new IOException("Connection timeout to bank API");
    }

    public void atomicTransfer(String fromAccount, String toAccount, double amount) {
        double originalBalance = getBalance(fromAccount);
        try {
            deductBalance(fromAccount, amount);
            addBalance(toAccount, amount);
        } catch (Exception e) {
            restoreBalance(fromAccount, originalBalance);
            throw e;
        }
    }

    public void safeMethod() {
        try {
            riskyOperation();
        } catch (IOException e) {
            System.err.println("Risky operation failed: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

class TransferFailedException extends RuntimeException {
    TransferFailedException(String msg, Throwable cause) { super(msg, cause); }
}

class AccountNotFoundException extends Exception {
    AccountNotFoundException(String msg) { super(msg); }
}