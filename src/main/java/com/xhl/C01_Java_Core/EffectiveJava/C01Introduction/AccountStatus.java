package com.xhl.C01_Java_Core.EffectiveJava.C01Introduction;

/**
 * @Author: xhl
 * @Date: 2026-07-04 01:03
 * @Description: 3. 账户状态枚举：AccountStatus
 * (涵盖：枚举最佳实践、策略模式)
 */
/**
 * 账户状态枚举
 * 涵盖规则：【34】用enum代替int常量、【35】用实例域代替序数
 */
// 【第34条】用 enum 代替 int 常量
// 【第35条】用实例域代替序数
// 【第38条】用接口模拟可扩展的枚举（如果需要的话，这里用基础版）
public enum AccountStatus {
    ACTIVE("正常", true),
    FROZEN("冻结", false),
    CLOSED("销户", false);

    private final String description;
    private final boolean canTransfer; // 【第35条】将行为与数据绑定

    AccountStatus(String description, boolean canTransfer) {
        this.description = description;
        this.canTransfer = canTransfer;
    }

    public boolean canTransfer() {
        return canTransfer;
    }
}
