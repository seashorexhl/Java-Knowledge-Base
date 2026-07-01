package com.xhl.C01_Java_Core.EffectiveJava.C07LambdaExpressionsandStreams;

/**
 * @Author: xhl
 * @Date: 2026-07-01 09:11
 * @Description: 电商订单数据处理（Order Processing）
 */

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

// 订单实体
class Order {
    private final String product;
    private final double price;
    private final boolean isPaid;

    Order(String product, double price, boolean isPaid) {
        this.product = product;
        this.price = price;
        this.isPaid = isPaid;
    }

    public String getProduct() { return product; }
    public double getPrice() { return price; }
    public boolean isPaid() { return isPaid; }

    @Override
    public String toString() {
        return product + ": $" + price;
    }
}

public class StreamDemo {

    // 测试入口
    public static void main(String[] args) {
        StreamDemo demo = new StreamDemo();
        List<Order> orders = Arrays.asList(
                new Order("Laptop", 1000, true),
                new Order("Mouse", 50, true),
                new Order("Keyboard", 100, false)
        );

        // 测试 43 & 46
        System.out.println("Products: " + demo.getProductNames(orders));
        System.out.println("Total Paid: $" + demo.calculateTotal(orders));

        // 测试 44
        demo.processOrders(orders,
                Order::isPaid,       // Predicate
                o -> System.out.println("Processing: " + o) // Consumer
        );
    }

    // ================= 条目42：Lambda 优先于匿名类 =================
    // 错误示范：Collections.sort(list, new Comparator<Order>() { ... });
    // 正确做法：使用 Lambda，代码极其简洁。
    public void sortOrders(List<Order> orders) {
        orders.sort((o1, o2) -> Double.compare(o1.getPrice(), o2.getPrice()));
    }

    // ================= 条目43：方法引用优先于 Lambda =================
    // 错误示范：orders.stream().map(o -> o.getProduct()).collect(Collectors.toList());
    // 正确做法：当 Lambda 仅仅是调用一个已有方法时，用方法引用 `::`，可读性更强。
    public List<String> getProductNames(List<Order> orders) {
        return orders.stream()
                .map(Order::getProduct) // 比 o -> o.getProduct() 更优雅
                .collect(Collectors.toList());
    }

    // ================= 条目44：坚持使用标准的函数接口 =================
    // 错误示范：自己定义一个 @FunctionalInterface interface StringConverter { String convert(String s); }
    // 正确做法：直接使用 java.util.function 包下的标准接口（如 Function, Predicate, Consumer）。
    public void processOrders(List<Order> orders, Predicate<Order> filter, Consumer<Order> action) {
        // Predicate: 接收参数返回 boolean
        // Consumer: 接收参数无返回值
        orders.stream().filter(filter).forEach(action);
    }

    // ================= 条目45：谨慎使用 Stream =================
    // Stream 适合多步管道处理。如果只是简单的 for 循环就能解决，不要强行上 Stream。
    // 例如：遍历列表并打印，用 for-each 循环可能比 stream().forEach() 更直观。

    // ================= 条目46：优先考虑流中无副作用的函数 =================
    // 错误示范：在 forEach 里修改外部变量（这会导致并发问题且违背 Stream 设计初衷）。
    //   double[] total = {0};
    //   orders.stream().forEach(o -> total[0] += o.getPrice()); // 极度危险！
    // 正确做法：使用 Stream 内置的聚合操作（如 reduce, sum, collect）。
    public double calculateTotal(List<Order> orders) {
        return orders.stream()
                .filter(Order::isPaid) // 无副作用的过滤
                .mapToDouble(Order::getPrice) // 无副作用的映射
                .sum(); // 安全的聚合
    }

    // ================= 条目48：谨慎使用 Stream 并行 =================
    // 错误示范：orders.parallelStream().map(...).forEach(...);
    // 并行流底层使用 ForkJoinPool，只有在数据量极大且操作无状态、CPU 密集时才有效。
    // 对于小数据量，并行流的线程切换开销反而比串行流慢得多！
    // 正确做法：默认使用串行流，只有在明确遇到性能瓶颈且经过基准测试后，才改用 parallelStream()。

    // ================= 条目47：优先使用 Collection 而不是 Stream 作为返回类型 =================
    // 错误示范：public Stream<Order> getOrders() { return orders.stream(); }
    // 正确做法：返回 List/Set 等集合。Stream 是一次性的（消耗后就不能再用），
    // 而集合可以被多次遍历、获取大小、随机访问。
    public List<Order> getPaidOrders(List<Order> orders) {
        return orders.stream()
                .filter(Order::isPaid)
                .collect(Collectors.toList()); // 返回 List，而不是 Stream
    }
}
