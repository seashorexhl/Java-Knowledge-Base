package com.xhl.C01_Java_Core.EffectiveJava.C01Introduction.example;

/**
 * @Author: xhl
 * @Date: 2026-07-04 01:53
 * @Description: 账户状态枚举
 */

/**
 * 账户状态枚举
 * 涵盖规则：【34】用enum代替int常量、【35】用实例域代替序数
 */
public enum AccountStatus {

    ACTIVE("正常", true),
    FROZEN("冻结", false),
    CLOSED("销户", false);

    private final String description;
    private final boolean canTransfer;

    AccountStatus(String description, boolean canTransfer) {
        this.description = description;
        this.canTransfer = canTransfer;
    }

    public String getDescription() {
        return description;
    }

    public boolean canTransfer() {
        return canTransfer;
    }
}
