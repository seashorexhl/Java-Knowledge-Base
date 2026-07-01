package com.xhl.C01_Java_Core.EffectiveJava.C05Generics;

/**
 * @Author: xhl
 * @Date: 2026-07-01 09:04
 * @Description: 类型安全的消息分发器
 */
import java.util.*;

// 【条目26】不要使用原始类型（Raw Types）
// 错误示范：public class MessageDispatcher { List list = new ArrayList(); }
// 正确做法：永远带上泛型参数，哪怕是通配符<?>
public class MessageDispatcher {

    // 【条目28】列表优先于数组
    // 数组是协变的（String[] 是 Object[] 的子类），且在运行时才检查类型，极易抛出 ArrayStoreException。
    // 泛型列表是不变的（List<String> 不是 List<Object> 的子类），且在编译期就保证类型安全。
    // 错误示范：Object[] array = new String[10]; array[0] = 123; // 运行时崩溃！
    private final List<Object> messageList = new ArrayList<>();

    // ================= 测试入口 =================
    public static void main(String[] args) {
        MessageDispatcher dispatcher = new MessageDispatcher();

        // 测试 31: PECS 原则
        List<String> strList = Arrays.asList("Msg1", "Msg2");
        dispatcher.addAllFromProducer(strList); // String 是 Object 的子类，完美兼容

        // 测试 32: 泛型可变参数
        dispatcher.safeAddMessages("Msg3", "Msg4");
        dispatcher.printAll(); // [Msg1, Msg2, Msg3, Msg4]
    }

    /**
     * 【条目31】使用有限制的通配符增加 API 的灵活性 (PECS 原则)
     * Producer Extends: 如果参数是“生产者”（往外读数据），用 <? extends T>
     * Consumer Super:   如果参数是“消费者”（往里写数据），用 <? super T>
     */
    public <T> void addAllFromProducer(List<? extends T> producerList) {
        for (T item : producerList) {
            messageList.add(item); // 安全地读取并添加
        }
    }

    /**
     * 【条目32】谨慎混用泛型和可变参数
     * 泛型可变参数（T... args）在底层会被擦除为 Object[]，编译器会报 Heap pollution 警告。
     * 解决：如果确定安全，加上 @SafeVarargs 注解消除警告。
     */
    @SafeVarargs
    public final <T> void safeAddMessages(T... messages) {
        Collections.addAll(messageList, messages);
    }

    /**
     * 【条目27】消除 Unchecked 警告
     * 遇到强转或原始类型操作时，尽量将警告限制在最小的方法内，并使用 @SuppressWarnings("unchecked")。
     */
    @SuppressWarnings("unchecked")
    public <T> T getMessage(int index, Class<T> clazz) {
        Object obj = messageList.get(index);
        if (clazz.isInstance(obj)) {
            return (T) obj; // 安全的强转，因为上面做了 isInstance 检查
        }
        throw new IllegalArgumentException("Type mismatch!");
    }

    public void printAll() {
        System.out.println("Messages: " + messageList);
    }
}

/**
 * 【条目29】首选泛型类型
 * 自己写集合或容器时，优先使用泛型，而不是 Object。
 */
class GenericBox<T> {
    private T value;
    public GenericBox(T value) { this.value = value; }
    public T getValue() { return value; }
}

/**
 * 【条目30】首选泛型方法
 * 静态工具方法尤其适合写成泛型方法，利用类型推导实现类型安全。
 */
class Util {
    // 编译器会自动推导出 T 是 String
    public static <T> List<T> singletonList(T item) {
        List<T> list = new ArrayList<>();
        list.add(item);
        return list;
    }
}

/**
 * 【条目33】考虑类型安全的异构容器
 * 传统的 Map<K, V> 只能有一种 Key 和一种 Value。
 * 如果我们希望 Key 是 Class，Value 是该 Class 对应的实例，可以把泛型参数放在 Key 上！
 */
class Favorites {
    // 核心：Map 的 Key 带有泛型信息
    private Map<Class<?>, Object> favorites = new HashMap<>();

    public <T> void putFavorite(Class<T> type, T instance) {
        favorites.put(Objects.requireNonNull(type), instance);
    }

    public <T> T getFavorite(Class<T> type) {
        // 返回前进行安全的向下转型
        return type.cast(favorites.get(type));
    }
}

// 异构容器测试
class HeterogeneousTest {
    public static void main(String[] args) {
        Favorites f = new Favorites();
        f.putFavorite(String.class, "Hello Java");
        f.putFavorite(Integer.class, 10086);

        // 取出时不需要强转，且类型绝对安全！
        String str = f.getFavorite(String.class);
        Integer num = f.getFavorite(Integer.class);
        System.out.println(str + " | " + num);
    }
}
