package com.xhl.Middleware_Distributed.MQ;

/**
 * @Author: xhl
 * @Date: 2026-05-24 15:58
 * @Description: 00   消息中间件最底层的逻辑：
 *  主题订阅与路由：通过 ConcurrentHashMap 实现了多 Topic 的管理，生产者发往不同 Topic 的消息会被精准投递给对应的消费者。
 * 削峰填谷与阻塞机制：利用 LinkedBlockingQueue 自带的 put() 和 take() 方法，天然实现了生s产者和消费者的解耦。如果消费者处理慢了，消息会在队列里堆积（削峰）；如果队列空了，消费者会自动等待（避免空轮询浪费 CPU）。
 * 内存级高并发：由于全程在内存中操作且使用了线程安全的数据结构，这个原型的单机吞吐量可以轻松达到每秒数千条，延迟极低。
 */
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

public class SimpleMQ {

    // 1. Broker核心：使用 ConcurrentHashMap 存储多个 Topic，每个 Topic 对应一个阻塞队列
    private static final Map<String, LinkedBlockingQueue<String>> broker = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        // 预定义两个测试主题
        String topicA = "order_topic";
        String topicB = "log_topic";

        // 初始化队列
        broker.put(topicA, new LinkedBlockingQueue<>(100));
        broker.put(topicB, new LinkedBlockingQueue<>(100));

        System.out.println("🚀 简易消息中间件已启动！");
        System.out.println("当前支持的主题: " + topicA + ", " + topicB);
        System.out.println("请输入要发送的消息 (格式: 主题名|消息内容)，例如: order_topic|用户下单成功");

        // 2. 模拟生产者 (Producer)：通过控制台输入发送消息
        new Thread(() -> {
            Scanner scanner = new Scanner(System.in);
            while (true) {
                String input = scanner.nextLine();
                if ("exit".equals(input)) break;

                String[] parts = input.split("\\|");
                if (parts.length == 2) {
                    String topic = parts[0];
                    String message = parts[1];
                    LinkedBlockingQueue<String> queue = broker.get(topic);

                    if (queue != null) {
                        try {
                            queue.put(message); // 队列满时会自动阻塞等待
                            System.out.println("✅ [生产者] 向 [" + topic + "] 发送消息成功: " + message);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else {
                        System.out.println("❌ 主题不存在: " + topic);
                    }
                }
            }
        }).start();

        // 3. 模拟消费者 (Consumer)：异步监听并拉取消息
        startConsumer(topicA, "订单处理系统");
        startConsumer(topicB, "日志分析系统");
    }

    // 消费者监听方法
    private static void startConsumer(String topic, String consumerName) {
        new Thread(() -> {
            LinkedBlockingQueue<String> queue = broker.get(topic);
            while (true) {
                try {
                    String message = queue.take(); // 队列为空时会自动阻塞等待
                    System.out.println("📩 [" + consumerName + "] 收到来自 [" + topic + "] 的新消息: " + message);
                    // 这里可以添加消息持久化到文件或数据库的逻辑
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}