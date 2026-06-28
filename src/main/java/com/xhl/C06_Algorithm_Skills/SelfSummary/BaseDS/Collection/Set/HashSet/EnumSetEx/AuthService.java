package com.xhl.C06_Algorithm_Skills.SelfSummary.BaseDS.Collection.Set.HashSet.EnumSetEx;

import java.util.EnumSet;

/**
 * @Author: xhl
 * @Date: 2026-06-16 01:10
 * @Description:    权限校验服务
 */

public class AuthService {
    public boolean hasPermission(EnumSet<Permission> userPermissions, Permission targetPermission) {
        // 关键修正：ADMIN 权限自动拥有所有权限
        if (userPermissions.contains(Permission.ADMIN)) {
            return true;
        }
        return userPermissions.contains(targetPermission);
    }

    public void deleteData(User user) {
        if (hasPermission(user.getPermissions(), Permission.DELETE)) {
            System.out.println(user + " 有权删除数据，执行操作！");
        } else {
            throw new SecurityException(user + " 无权删除数据！");
        }
    }
}
