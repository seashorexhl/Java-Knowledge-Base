package com.xhl.C07_Algorithm_Skills.SelfSummary.BaseDS.Collection.Set.HashSet.EnumSetEx;

/**
 * @Author: xhl
 * @Date: 2026-06-16 01:11
 * @Description: 权限 演示 示例
 */
import java.util.EnumSet;

public class PermissionDemo {
    public static void main(String[] args) {
        // 创建用户A（只有READ和WRITE权限）
        User userA = new User("普通用户",
                EnumSet.of(Permission.READ, Permission.WRITE));

        // 创建用户B（管理员权限）
        User userB = new User("管理员",
                EnumSet.of(Permission.ADMIN)); // 注意：ADMIN需特殊处理

        AuthService authService = new AuthService();

        try {
            authService.deleteData(userA); // 应抛出异常
        } catch (SecurityException e) {
            System.out.println(e.getMessage());
        }

        authService.deleteData(userB); // 应成功执行
    }
}