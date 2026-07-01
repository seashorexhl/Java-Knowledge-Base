package com.xhl.C01_Java_Core.EffectiveJava.C12Serialization;

/**
 * @Author: xhl
 * @Date: 2026-07-01 09:27
 * @Description: 用户会话管理（UserSession）
 */
import java.io.*;
import java.util.Date;

// ================= 条目85：优先考虑替代方案 =================
// 错误示范：为了让类支持网络传输，强行 implements Serializable。
// 正确做法：优先使用 JSON (Jackson/Gson)、Protobuf、XML 等跨语言、更安全的序列化方案。
// 只有在必须与强依赖 Java 序列化的旧系统集成时，才考虑 Java 原生序列化。

// ================= 条目86：非常谨慎地实现 Serializable =================
// 错误示范：类中包含敏感信息（如密码），或者包含不可序列化的资源（如数据库连接），却强行实现 Serializable。
// 正确做法：如果不希望某些字段被序列化，必须显式声明为 transient。
public class UserSession implements Serializable {
    private static final long serialVersionUID = 1L; // 【条目86补充】必须显式声明 UID，防止类结构变化导致反序列化失败

    private final String username;
    private final String token;
    private transient String password; // 敏感信息，绝不参与序列化
    private transient InputStream inputStream; // 无法序列化的资源

    public UserSession(String username, String token, String password) {
        this.username = username;
        this.token = token;
        this.password = password;
    }

    // ================= 条目87：考虑使用自定义的序列化形式 =================
    // 错误示范：默认序列化会将整个对象图（包括内部缓存、临时状态）都暴露出去。
    // 正确做法：只序列化“逻辑状态”，忽略派生状态或缓存。
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject(); // 写入非 transient 字段
        out.writeUTF(username);   // 自定义写入核心逻辑状态
    }

    // ================= 条目88：保护性地编写 readObject 方法 =================
    // 错误示范：直接信任输入流中的数据。黑客可以篡改序列化字节流，注入恶意对象或非法参数。
    // 正确做法：在 readObject 中进行严格的参数校验和防御性拷贝。
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();

        // 1. 防御性校验：确保反序列化出来的数据是合法的
        if (username == null || username.isEmpty()) {
            throw new InvalidObjectException("Username cannot be empty");
        }

        // 2. 防御性拷贝：如果包含可变对象（如 Date），必须拷贝，防止外部篡改
        // （此处 username 是 String 不可变，仅作演示说明）
    }

    // ================= 条目89：对于实例控制，枚举类型优先于 readResolve =================
    // 错误示范：使用 readResolve() 来保证单例，代码冗长且容易被绕过。
    // 正确做法：如果类需要实例控制（如单例、状态机），直接使用枚举。
    // （枚举天然免疫序列化破坏，无需 readResolve）
}

// ================= 条目90：考虑使用序列化代理模式 =================
// 这是终极防御手段！彻底隔离外部对真实类的反序列化攻击。
// 真实类本身不再需要 writeObject / readObject / readResolve。
class SafeUserSession implements Serializable {
    private static final long serialVersionUID = 1L;
    private final String username;
    private final Date loginTime;

    SafeUserSession(String username, Date loginTime) {
        this.username = username;
        // 防御性拷贝
        this.loginTime = new Date(loginTime.getTime());
    }

    // 序列化时，写出一个代理对象，而不是真实对象
    private Object writeReplace() {
        return new SerializationProxy(this);
    }

    // 真实类直接拒绝反序列化，黑客如果伪造字节流攻击真实类，会直接抛异常
    private void readObject(ObjectInputStream stream) throws InvalidObjectException {
        throw new InvalidObjectException("Proxy required");
    }

    // 序列化代理类（真正的序列化/反序列化在这里发生）
    private static class SerializationProxy implements Serializable {
        private static final long serialVersionUID = 1L;
        private final String username;
        private final long loginTimeMillis;

        SerializationProxy(SafeUserSession session) {
            this.username = session.username;
            this.loginTimeMillis = session.loginTime.getTime();
        }

        // 反序列化时，通过代理重建真实对象，可以在这里做严格的校验
        private Object readResolve() {
            return new SafeUserSession(username, new Date(loginTimeMillis));
        }
    }
}
