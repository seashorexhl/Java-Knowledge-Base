package com.xhl.C06_Middleware_Distributed.MQ;

/**
 * @Author: xhl
 * @Date: 2026-05-24 16:06
 * @Description: 01 电梯MQ N1s
 */
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

public class ElevatorMQN1 {
    // 核心队列
    private static final Map<String, LinkedBlockingQueue<String>> broker = new ConcurrentHashMap<>();
    // 设定一个“舒适区”阈值，超过这个值就开始触发梯度下降策略
    private static final int COMFORT_ZONE = 10;

    public static void main(String[] args) {
        System.out.println("===============================================");
        System.out.println("   🛗  Elevator MQ (Gradient Descent Edition)  ");
        System.out.println("===============================================");

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
                        // 【梯度下降核心逻辑】：实时计算当前队列的“坡度”（即剩余容量比例）
                        int currentLoad = queue.size();

                        if (currentLoad > COMFORT_ZONE) {
                            // 如果超出了舒适区，触发“反向梯度”反馈，要求生产者减速（模拟学习率的调整）
                            System.out.println("⚠️ [系统警告] 检测到 [" + floor + "] 拥堵指数上升！正在执行梯度下降策略...");
                            System.out.println("⏳ [前台提示] 乘客 [" + passenger + "] 请稍等，系统正在平滑降速...\n");
                            try { Thread.sleep(1000); } catch (InterruptedException e) {} // 模拟优雅的退避
                        }

                        try {
                            queue.put(passenger);
                            System.out.println("✅ [前台] 乘客 [" + passenger + "] 已进入去往 [" + floor + "] 的电梯队列 (当前排队人数: " + queue.size() + ")");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();

        // 消费者线程（故意放慢速度，制造拥堵，让梯度下降策略生效）
        new Thread(() -> {
            LinkedBlockingQueue<String> queue = broker.get(floorOrder);
            while (true) {
                try {
                    String passenger = queue.take();
                    System.out.println("📩 [订单中心] 接到乘客: " + passenger);
                    Thread.sleep(2000); // 模拟业务处理耗时
                } catch (InterruptedException e) { e.printStackTrace(); }
            }
        }).start();
    }
}
