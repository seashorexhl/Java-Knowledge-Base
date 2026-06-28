package com.xhl.C06_Algorithm_Skills.SelfSummary.BaseDS.Collection.Set.HashSet.EnumSetEx;

/**
 * @Author: xhl
 * @Date: 2026-06-16 00:50
 * @Description: 1. Permission.java（权限枚举定义）
 *      EnumSet 最佳实践
 *  1. 替代传统位标志系统
 *  2. 高效枚举集合操作
 *  3. 状态机与配置管理
 */
/**
 * 1. **定义权限枚举类型：**
 * */
public enum Permission {
    READ,        // 查看权限
    WRITE,       // 编辑权限
    DELETE,      // 删除权限
    ADMIN        // 管理员权限（包含所有权限）
}
