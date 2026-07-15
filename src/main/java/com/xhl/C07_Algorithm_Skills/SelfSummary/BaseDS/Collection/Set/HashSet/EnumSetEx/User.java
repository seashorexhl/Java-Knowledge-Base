package com.xhl.C07_Algorithm_Skills.SelfSummary.BaseDS.Collection.Set.HashSet.EnumSetEx;

import java.util.EnumSet;

/**
 * @Author: xhl
 * @Date: 2026-06-16 01:05
 * @Description: User.java（用户实体类）
 */

public class User {
    private String name;
    private EnumSet<Permission> permissions;

    public User(String name, EnumSet<Permission> permissions) {
        this.name = name;
        this.permissions = permissions;
    }

    public EnumSet<Permission> getPermissions() {
        return permissions;
    }

    @Override
    public String toString() {
        return name;
    }
}
