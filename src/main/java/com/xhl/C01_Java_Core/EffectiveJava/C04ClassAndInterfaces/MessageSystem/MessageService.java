package com.xhl.C01_Java_Core.EffectiveJava.C04ClassAndInterfaces.MessageSystem;

/**
 * @Author: xhl
 * @Date: 2026-07-01 08:55
 * @Description: 消息通知系统
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// 【条目25】将源文件限制为单个顶层类
// 注意：一个 .java 文件里只能有一个 public 顶层类。
// 其他辅助类（如 MessageBuilder）必须是非 public 的，或者放在独立的文件中。

/**
 * 【条目20】与抽象类相比，优先选择接口
 * 【条目21】为传诸后世而设计接口（保持接口精简，不要随意添加默认方法）
 * 【条目22】接口仅用于定义类型（不要在接口里写常量或实现逻辑）
 */
interface MessageSender {
    // 仅定义行为契约，不包含任何状态
    void send(String content);
}

/**
 * 【条目18】组合优先于继承
 * 【条目19】要么为继承而设计，要么禁止继承
 * 这里我们不继承 ArrayList，而是通过组合持有一个 List。
 * 并且用 final 修饰，明确告诉别人：不要继承我！
 */
public final class MessageService {
    // 【条目15】最小化类和成员的可访问性
    // 内部状态绝不暴露给外部，使用 private
    private final List<MessageSender> senders;
    private final String defaultPrefix;

    // 【条目17】使可变性最小化（部分体现）
    // 构造器中接收 List，防御性拷贝，防止外部修改影响内部状态
    public MessageService(List<MessageSender> senders, String defaultPrefix) {
        this.senders = Collections.unmodifiableList(new ArrayList<>(senders));
        this.defaultPrefix = defaultPrefix;
    }

    // 【条目16】在公有类中，使用访问器方法，而不是用公有的字段
    // 绝对不要写 public String defaultPrefix;
    // 必须通过 getter 获取，这样未来可以在内部改变实现逻辑而不破坏调用方
    public String getDefaultPrefix() {
        return defaultPrefix;
    }

    public void broadcast(String content) {
        for (MessageSender sender : senders) {
            sender.send(defaultPrefix + ": " + content);
        }
    }
}

/**
 * 【条目23】优先使用层次结构而不是标记类
 * 反面教材：不要写一个 Message 类，里面加个 boolean isEmail; boolean isSms;
 * 正确做法：使用继承体系，EmailMessage 和 SmsMessage 是独立的类。
 */
abstract class BaseMessage {
    protected final String content;
    protected BaseMessage(String content) { this.content = content; }
}

class EmailMessage extends BaseMessage {
    EmailMessage(String content) { super(content); }
}

class SmsMessage extends BaseMessage {
    SmsMessage(String content) { super(content); }
}

/**
 * 【条目24】与非静态成员类相比，优先选择静态成员类
 * 如果内部类不需要访问外部类的实例变量，一定要加 static！
 * 非静态内部类会隐式持有外部类的引用，极易导致内存泄漏。
 */
class OuterClass {
    private String data = "Outer Data";

    // 错误示范：非静态内部类（会持有 OuterClass 的引用）
    // class Inner { void print() { System.out.println(data); } }

    // 正确示范：静态内部类（独立存在，不持有外部类引用）
    static class StaticInner {
        void print() {
            System.out.println("I am a static inner class.");
        }
    }
}

// 【条目25】辅助类放在同一个文件中，但必须是非 public 的
class MessageBuilder {
    public static String build(String msg) {
        return "[Built] " + msg;
    }
}
