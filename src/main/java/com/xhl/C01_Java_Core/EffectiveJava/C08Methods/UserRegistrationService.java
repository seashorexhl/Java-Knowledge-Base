package com.xhl.C01_Java_Core.EffectiveJava.C08Methods;

/**
 * @Author: xhl
 * @Date: 2026-07-01 09:12
 * @Description: 用户注册服务（UserRegistrationService）
 */
import java.util.*;

/**
 * 【条目56】为所有公开的API元素编写文档注释
 * 必须包含：前提条件(@param)、返回值(@return)、异常(@throws)。
 * 让调用方在不看源码的情况下就能正确使用 API。
 */
public class UserRegistrationService {

    private final List<User> registeredUsers = new ArrayList<>();
    /**
     * 【条目50】必要时进行防御性拷贝
     * 如果入参或返回值是可变对象（如 Date, List），必须拷贝，防止外部修改破坏内部状态。
     */
    private final Date serverStartTime;

    public UserRegistrationService(Date serverStartTime) {
        // 构造器中：防御性拷贝入参
        this.serverStartTime = new Date(serverStartTime.getTime());
    }

    /**
     * 【条目49】检查参数的有效性
     * 在方法开头显式检查参数，尽早暴露问题（Fail-fast）。
     * 使用 Objects.requireNonNull 检查 null。
     */
    public void registerUser(User user) {
        // 如果 user 为 null，立即抛出 NullPointerException，并附带自定义消息
        Objects.requireNonNull(user, "User cannot be null");
        if (user.getName() == null || user.getName().isEmpty()) {
            throw new IllegalArgumentException("User name must not be empty");
        }
        registeredUsers.add(user);
    }

    public Date getServerStartTime() {
        // Getter 中：防御性拷贝返回值，防止引用逃逸
        return new Date(serverStartTime.getTime());
    }

    /**
     * 【条目51】仔细设计方法签名
     * 1. 方法名见名知意。
     * 2. 参数不超过4个，过多时封装成参数对象。
     * 3. 参数类型优先使用接口（如 List 而非 ArrayList）。
     */
    public void batchRegisterUsers(List<User> users) {
        // 使用接口 List，而不是具体的 ArrayList
        for (User user : users) {
            registerUser(user);
        }
    }

    /**
     * 【条目52】明智地使用重载
     * 避免导出参数数量相同的多个重载方法，容易引起混淆。
     * 如果逻辑相同，考虑使用可变参数或重命名方法。
     */
    public void logMessage(String message) {
        System.out.println("[INFO] " + message);
    }

    // 避免这种重载：public void logMessage(String msg, String level)
    // 更好的做法是给方法起个新名字，或者使用条目53的可变参数

    /**
     * 【条目53】明智地使用可变参数
     * 每次调用都会创建数组，高频调用时注意性能。
     * 如果 95% 的调用只有 1-2 个参数，可以提供重载方法，最后再用可变参数兜底。
     */
    public void logMessages(String... messages) {
        for (String msg : messages) {
            System.out.println("[INFO] " + msg);
        }
    }

    /**
     * 【条目54】返回空集合或数组，而非 null
     * 返回 null 会迫使调用方必须写 null 检查，极易引发 NPE。
     */
    public List<User> getRegisteredUsers() {
        // 永远不要返回 null，返回不可变的空集合或真实集合的副本
        return registeredUsers.isEmpty()
                ? Collections.emptyList()
                : new ArrayList<>(registeredUsers);
    }

    /**
     * 【条目55】明智地返回 Optional
     * 当结果可能不存在，且调用方必须处理这种情况时，使用 Optional。
     * 注意：永远不要返回 null 的 Optional，也不要将 Optional 用作字段或 Map 的 Key。
     */
    public Optional<User> findUserByName(String name) {
        return registeredUsers.stream()
                .filter(u -> u.getName().equals(name))
                .findFirst();
    }

    // 内部测试类
    static class User {
        private final String name;
        User(String name) { this.name = name; }
        public String getName() { return name; }
    }
}
