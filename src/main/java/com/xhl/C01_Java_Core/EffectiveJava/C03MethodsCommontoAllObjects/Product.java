package com.xhl.C01_Java_Core.EffectiveJava.C03MethodsCommontoAllObjects;

/**
 * @Author: xhl
 * @Date: 2026-07-01 08:50
 * @Description: （商品）类
 */
import java.util.Objects;

/**
 * 【条目14】考虑实现 Comparable 接口
 * 实现 Comparable 接口后，对象就可以被 Arrays.sort() 或 TreeSet 自动排序。
 * 注意：compareTo 的逻辑必须与 equals 保持一致！
 */
public class Product implements Comparable<Product> {
    private final String id;
    private final String name;
    private final double price;

    public Product(String id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    // 【条目13】谨慎重写 clone 方法
    // 最佳实践：不要重写 clone，不要实现 Cloneable 接口！
    // 如果需要复制对象，请提供“拷贝构造器”或“静态工厂方法”：
    public Product(Product other) {
        this.id = other.id;
        this.name = other.name;
        this.price = other.price;
    }

    // 测试入口
    public static void main(String[] args) {
        Product p1 = new Product("001", "Java书", 99.0);
        Product p2 = new Product("001", "Java书", 99.0);

        // 测试 10 & 11：equals 和 hashCode 的配合
        System.out.println("p1 == p2 (equals): " + p1.equals(p2)); // true
        System.out.println("HashCodes match: " + (p1.hashCode() == p2.hashCode())); // true

        // 测试 12：toString 的可读性
        System.out.println("Product Info: " + p1);

        // 测试 13：安全的对象复制（代替 clone）
        Product p3 = new Product(p1);
        System.out.println("Copied Product: " + p3);

        // 测试 14：排序
        Product p4 = new Product("002", "Python书", 79.0);
        System.out.println("Compare (99 vs 79): " + p1.compareTo(p4)); // 正数，表示 p1 大于 p4
    }

    /**
     * 【条目10】重写 equals 遵守通用约定
     * 必须满足：自反性、对称性、传递性、一致性、非空性。
     * 最佳实践：使用 Java 7 引入的 Objects.equals() 避免空指针异常。
     */
    @Override
    public boolean equals(Object o) {
        // 1. 自反性：x.equals(x) 必须为 true
        if (this == o) return true;
        // 2. 非空性：x.equals(null) 必须为 false
        if (o == null || getClass() != o.getClass()) return false;

        // 3. 强制类型转换并比较核心字段
        Product product = (Product) o;
        return Double.compare(product.price, price) == 0 &&
                Objects.equals(id, product.id) &&
                Objects.equals(name, product.name);
    }

    /**
     * 【条目11】重写 equals 时必须总是重写 hashCode
     * 约定：相等的对象必须具有相同的哈希码。
     * 如果不重写，放入 HashMap/HashSet 时会引发灾难（无法正确查找或去重）。
     */
    @Override
    public int hashCode() {
        // 使用 Objects.hash() 或 IDE 生成的标准公式，避免自己手写位运算出错
        return Objects.hash(id, name, price);
    }

    /**
     * 【条目12】总是重写 toString
     * 让对象在打印、日志输出时具有可读性。
     */
    @Override
    public String toString() {
        return "Product{id='" + id + "', name='" + name + "', price=" + price + "}";
    }

    /**
     * 【条目14】实现 compareTo
     * 这里我们按价格升序排序。
     * 警告：千万不要用 (int)(this.price - o.price)，浮点数转 int 会丢失精度！
     */
    @Override
    public int compareTo(Product o) {
        return Double.compare(this.price, o.price);
    }
}