package com.xhl.C01_Java_Core.EffectiveJava.C09GenericsProgramming;

/**
 * @Author: xhl
 * @Date: 2026-07-01 09:14
 * @Description:  购物车价格计算服务（CartPriceCalculator）
 */

import java.math.BigDecimal;
import java.util.*;

// 【条目64】通过接口引用对象
// 声明变量时，左边用 List/Map/Set 等接口，右边用具体实现类。
// 这样未来想换实现（如 ArrayList -> LinkedList）只需改一行代码。
public class CartPriceCalculator {

    private final Map<String, BigDecimal> priceMap = new HashMap<>();

    public CartPriceCalculator() {
        priceMap.put("Apple", new BigDecimal("3.50"));
        priceMap.put("Banana", new BigDecimal("1.20"));
    }

    // 测试入口
    public static void main(String[] args) {
        CartPriceCalculator calculator = new CartPriceCalculator();

        // 【条目64】通过接口引用
        List<String> cart = new ArrayList<>(Arrays.asList("Apple", "Banana", "Apple"));

        System.out.println("Total: $" + calculator.calculateTotal(cart)); // $8.20
        System.out.println("Items: " + calculator.formatCartItems(cart)); // Apple, Banana, Apple
    }

    /**
     * 【条目57】局部变量的作用域最小化
     * 变量只在需要它的代码块内声明，用完即弃。
     * 避免在方法开头声明一堆变量，然后在很下面才使用。
     */
    public BigDecimal calculateTotal(List<String> cartItems) {
        // 【条目60】如果需要精确答案，避免使用 float 和 double
        // 金融/价格计算必须用 BigDecimal，否则 0.1 + 0.2 != 0.3
        BigDecimal total = BigDecimal.ZERO;

        // 【条目58】for-each 循环优先于传统 for 循环
        // 更简洁，且避免了数组越界或迭代器错误
        for (String item : cartItems) {
            BigDecimal price = priceMap.getOrDefault(item, BigDecimal.ZERO);
            total = total.add(price);
        }

        return total;
    }

    /**
     * 【条目61】基本数据类型优先于包装类型
     * 优先使用 int/long/double，而不是 Integer/Long/Double。
     * 包装类型有 null 风险，且自动装箱/拆箱有性能开销。
     */
    public int getItemCount(List<String> cartItems) {
        // 返回 int，而不是 Integer
        return cartItems.size();
    }

    /**
     * 【条目62】避免用字符串代替其他类型
     * 错误示范：public void setPrice(String price)
     * 正确做法：用 BigDecimal 或专门的 Price 类，让编译器帮你检查类型。
     */
    public void setPrice(String itemName, BigDecimal price) {
        Objects.requireNonNull(itemName, "Item name cannot be null");
        Objects.requireNonNull(price, "Price cannot be null");
        priceMap.put(itemName, price);
    }

    /**
     * 【条目63】当心字符串连接引起的性能问题
     * 错误示范：在循环中使用 str += item; （每次都会创建新的 String 对象）
     * 正确做法：使用 StringBuilder，或者直接交给 Stream/Joiner 处理。
     */
    public String formatCartItems(List<String> cartItems) {
        // 使用 String.join()，底层就是 StringBuilder
        return String.join(", ", cartItems);
    }
}

// 【条目59】了解并使用库
// 不要自己写 Arrays.sort()、Collections.shuffle()、Math.sqrt()。
// 标准库经过千锤百炼，性能更好，Bug 更少。

// 【条目65】接口优先于反射
// 不要为了“灵活性”到处用反射。如果可以通过接口（如 Strategy 模式）解决的问题，
// 就不要用反射。反射会破坏编译期类型检查，且性能差。

// 【条目66】明智地使用原生方法（JNI）
// 除非有成熟的 C/C++ 库必须调用，或者需要极致的底层性能，否则不要用 JNI。
// JNI 会破坏跨平台性，且容易导致 JVM 崩溃。

// 【条目67】明智地进行优化
// 不要过早优化！先写出清晰的代码，用 Profiler 找到真正的瓶颈，再针对性优化。
// 90% 的性能问题来自算法复杂度和数据库查询，而不是字符串拼接。

// 【条目68】遵守广泛接受的命名约定
// 类名：UpperCamelCase (如 CartPriceCalculator)
// 方法/变量：lowerCamelCase (如 calculateTotal)
// 常量：UPPER_SNAKE_CASE (如 MAX_RETRY_COUNT)
// 包名：全小写 (如 com.example.shopping)
