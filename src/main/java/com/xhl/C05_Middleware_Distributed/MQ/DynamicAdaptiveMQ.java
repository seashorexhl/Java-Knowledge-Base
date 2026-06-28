package com.xhl.Middleware_Distributed.MQ;

/**
 * @Author: xhl
 * @Date: 2026-05-24 16:08
 * @Description: 动态自适应消息 队列   类和接口
 *
 */
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

public class DynamicAdaptiveMQ {
    // 核心队列
    private static final Map<String, LinkedBlockingQueue<String>> broker = new ConcurrentHashMap<>();
    // 【梯度参数】设定舒适区阈值，超过就开始产生“负梯度”压力信号
    private static final int COMFORT_ZONE = 5;
    // 【自适应参数】学习率，每次调整的步长
    private static final double LEARNING_RATE = 0.5;

    public static void main(String[] args) {
        System.out.println("======================================================");
        System.out.println("   🛗  Elevator MQ (Dynamic Adaptive DAG Edition)     ");
        System.out.println("   集成了：梯度感知 + 残差旁路 + 动态自适应引擎       ");
        System.out.println("======================================================\n");

        String floorOrder = "order_floor";
        broker.put(floorOrder, new LinkedBlockingQueue<>(100));

        System.out.println("当前运营楼层: [" + floorOrder + "] (拥堵阈值: " + COMFORT_ZONE + ")");
        System.out.println("请输入指令 (格式: 楼层名|乘客信息)，输入 exit 停运。\n");

        // 生产者线程
        new Thread(() -> {
            Scanner scanner = new Scanner(System.in);
            while (true) {
                String input = scanner.nextLine();
                if ("exit".equals(input)) break;

                String[] parts = input.split("\\|");
                if (parts.length == 2) {
                    String floor = parts[0];
                    String passenger = parts[1];
                    LinkedBlockingQueue<String> queue = broker.get(floor);

                    if (queue != null) {
                        int currentLoad = queue.size();

                        // --- 1. 梯度感知阶段 ---
                        // 计算当前队列的“坡度”，即超出舒适区的程度
                        double gradient = Math.max(0, currentLoad - COMFORT_ZONE);

                        if (gradient > 0) {
                            System.out.println("⚠️ [梯度感知] 检测到 [" + floor + "] 出现拥堵梯度! 坡度值: " + gradient);

                            // --- 2. 动态自适应阶段 ---
                            // 根据梯度和学习率，动态计算需要暂停的时间（模拟退避）
                            long backoffTime = (long) (gradient * 1000 * LEARNING_RATE);
                            System.out.println("🧠 [自适应大脑] 正在执行梯度下降策略，要求生产者降速 " + backoffTime + "ms...");

                            try { Thread.sleep(backoffTime); } catch (InterruptedException e) {}

                            // --- 3. 残差网络阶段 ---
                            // 如果坡度实在太陡（比如超过10），触发残差旁路机制，走VIP通道！
                            if (gradient > 10) {
                                System.out.println("🚨 [残差旁路] 拥堵极其严重！启动残差连接，乘客 [" + passenger + "] 绕过业务处理，直达终点！\n");
                                bypassProcess(passenger); // 触发残差逻辑
                                continue; // 跳过正常的入队流程
                            }
                        }

                        // 正常入队流程
                        try {
                            queue.put(passenger);
                            System.out.println("✅ [前台] 乘客 [" + passenger + "] 已进入去往 [" + floor + "] 的电梯队列 (当前排队人数: " + queue.size() + ")\n");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();

        // 消费者线程（故意设置极慢的处理速度，用来逼出系统的自适应和残差机制）
        new Thread(() -> {
            LinkedBlockingQueue<String> queue = broker.get(floorOrder);
            while (true) {
                try {
                    String passenger = queue.take();
                    System.out.println("📩 [订单中心] 接到乘客: " + passenger);
                    Thread.sleep(3000); // 模拟极其缓慢的业务处理
                } catch (InterruptedException e) { e.printStackTrace(); }
            }
        }).start();
    }

    // 残差旁路处理方法（Skip Connection）
    private static void bypassProcess(String passenger) {
        // 这里可以是将消息直接写入冷存储、转发给备用集群，或者仅仅做一个轻量级的日志记录
        // 它的核心意义在于：不经过那个已经拥堵不堪的主队列，保证消息绝对不丢。
        System.out.println("✨ [残差终点] 乘客 [" + passenger + "] 已通过旁路安全送达，未占用主队列资源。");
    }
}
