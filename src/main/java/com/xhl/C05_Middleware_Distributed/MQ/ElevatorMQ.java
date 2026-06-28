package com.xhl.C05_Middleware_Distributed.MQ;

/**
 * @Author: xhl
 * @Date: 2026-05-24 16:00
 * @Description:  电梯 MQ
 */
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

public class ElevatorMQ {

    // Broker核心：Map的Key是“楼层号”(Topic)，Value是等待进电梯的“乘客队列”
    private static final Map<String, LinkedBlockingQueue<String>> broker = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        System.out.println("===============================================");
        System.out.println("       🛗  Elevator MQ v0.1 Alpha 已启动!  ƪ(˘⌣˘)ʃ     ");
        System.out.println("===============================================");

        // 初始化两条业务线路（楼层）
        String floorOrder = "order_floor";
        String floorLog = "log_floor";

        broker.put(floorOrder, new LinkedBlockingQueue<>(100));
        broker.put(floorLog, new LinkedBlockingQueue<>(100));

        System.out.println("当前运营楼层(Topic): [" + floorOrder + "], [" + floorLog + "]");
        System.out.println("请输入指令调度电梯 (格式: 楼层名|乘客信息)，输入 exit 停运。\n");

        // 1. 生产者线程：大厅接待员，负责把乘客送进对应的电梯队列
        new Thread(() -> {
            Scanner scanner = new Scanner(System.in);
            while (true) {
                String input = scanner.nextLine();
                if ("exit".equals(input)) {
                    System.out.println("🛑 Elevator MQ 正在停止运营...");
                    break;
                }

                String[] parts = input.split("\\|");
                if (parts.length == 2) {
                    String floor = parts[0];
                    String passenger = parts[1];
                    LinkedBlockingQueue<String> queue = broker.get(floor);

                    if (queue != null) {
                        try {
                            queue.put(passenger);
                            System.out.println("⬆️ [前台] 乘客 [" + passenger + "] 已进入去往 [" + floor + "] 的电梯队列");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else {
                        System.out.println("❌ 错误：本大楼没有 [" + floor + "] 这个楼层！");
                    }
                }
            }
        }).start();

        // 2. 消费者线程：各楼层的电梯工，负责把乘客接走并处理业务
        startElevatorWorker(floorOrder, "订单处理中心");
        startElevatorWorker(floorLog, "日志分析中心");
    }

    // 电梯工（消费者）监听方法
    private static void startElevatorWorker(String floor, String workerName) {
        new Thread(() -> {
            LinkedBlockingQueue<String> queue = broker.get(floor);
            while (true) {
                try {
                    String passenger = queue.take(); // 电梯门打开，接走一位乘客
                    System.out.println("⬇️ [" + workerName + "] 在 [" + floor + "] 接到乘客: " + passenger);
                    // 这里可以模拟处理业务的耗时
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}