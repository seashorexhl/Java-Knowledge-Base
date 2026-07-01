package com.xhl.C01_Java_Core.EffectiveJava.C02CreatingDestroyingObjects;

/**
 * @Author: xhl
 * @Date: 2026-07-01 15:05
 * @Description: User 类
 *  把可命名、可缓存、可返回子类型这三个核心优势都体现出来
 */
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

// 1. 【可返回子类型】：接口/抽象类可以通过静态方法返回具体的实现类，调用方无需关心具体实现
interface User {
    // 静态工厂方法：根据角色返回不同的子类型
    static User create(String name, String role) {
        if ("admin".equalsIgnoreCase(role)) {
            return new AdminUser(name);
        }
        return new NormalUser(name);
    }

    String getName();
}

class NormalUser implements User {
    private final String name;
    NormalUser(String name) { this.name = name; }
    @Override public String getName() { return name; }
}

class AdminUser implements User {
    private final String name;
    AdminUser(String name) { this.name = name; }
    @Override public String getName() { return "[Admin] " + name; }
}

public class StaticFactoryDemo {
    // 2. 【可缓存实例】：静态工厂方法可以控制对象的创建，避免重复创建（类似单例或对象池）
    private static final Map<String, User> USER_CACHE = new ConcurrentHashMap<>();

    // 3. 【可命名】：构造器只能叫类名，而静态方法可以有明确的名字，表达创建意图
    public static User getCachedUser(String name, String role) {
        return USER_CACHE.computeIfAbsent(name, k -> User.create(k, role));
    }

    public static void main(String[] args) {
        // 语义清晰：一看就知道是获取一个缓存的用户
        User user1 = getCachedUser("Alice", "admin");
        User user2 = getCachedUser("Alice", "admin");

        System.out.println(user1.getName()); // [Admin] Alice
        System.out.println(user1 == user2);  // true (证明缓存生效，没有重复创建)

        // 自动返回对应的子类型，无需 new AdminUser()
        User user3 = getCachedUser("Bob", "user");
        System.out.println(user3.getName()); // Bob
    }
}