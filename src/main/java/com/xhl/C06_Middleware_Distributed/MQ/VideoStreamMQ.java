package com.xhl.C06_Middleware_Distributed.MQ;

/**
 * @Author: xhl
 * @Date: 2026-05-24 16:12
 * @Description: 模拟一个在网络波动下依然能流畅播放的视频传输系统：
 */
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

public class VideoStreamMQ {
    // 核心队列：模拟网络缓冲区
    private static final Map<String, LinkedBlockingQueue<String>> networkBuffer = new ConcurrentHashMap<>();

    // 【梯度参数】舒适区阈值（比如缓冲区能轻松容纳5秒的视频数据）
    private static final int COMFORT_ZONE = 5;
    // 【自适应参数】学习率
    private static final double LEARNING_RATE = 0.5;

    public static void main(String[] args) {
        System.out.println("======================================================");
        System.out.println("   🎬  Elevator MQ (Video Streaming DAG Edition)      ");
        System.out.println("   集成了：网络梯度感知 + 码率动态自适应 + 残差兜底   ");
        System.out.println("======================================================\n");

        String videoChannel = "live_room_01";
        networkBuffer.put(videoChannel, new LinkedBlockingQueue<>(100));

        System.out.println("当前直播频道: [" + videoChannel + "] (缓冲区安全阈值: " + COMFORT_ZONE + "秒)");
        System.out.println("请输入指令模拟推流 (格式: 频道名|视频片段质量), 输入 exit 结束直播。\n");
        System.out.println("💡 提示：可输入 '4K片段', '1080P片段', '720P片段', '纯音频' 等来模拟不同大小的数据包");

        // 生产者线程（模拟主播推流端）
        new Thread(() -> {
            Scanner scanner = new Scanner(System.in);
            while (true) {
                String input = scanner.nextLine();
                if ("exit".equals(input)) break;

                String[] parts = input.split("\\|");
                if (parts.length == 2) {
                    String channel = parts[0];
                    String videoSegment = parts[1]; // 比如 "4K高清片段"
                    LinkedBlockingQueue<String> queue = networkBuffer.get(channel);

                    if (queue != null) {
                        int currentLoad = queue.size(); // 当前缓冲区的堆积程度

                        // --- 1. 梯度感知阶段 (监测网络拥堵) ---
                        double gradient = Math.max(0, currentLoad - COMFORT_ZONE);

                        if (gradient > 0) {
                            System.out.println("⚠️ [网络梯度感知] 观众端网络出现拥堵! 缓冲区坡度值: " + gradient);

                            // --- 2. 动态自适应阶段 (自动降级码率) ---
                            long backoffTime = (long) (gradient * 800 * LEARNING_RATE);
                            System.out.println("🧠 [动态自适应大脑] 正在执行梯度下降策略，通知主播降低推流码率...");

                            try { Thread.sleep(backoffTime); } catch (InterruptedException e) {}

                            // 如果拥堵极其严重（坡度 > 8），触发残差旁路机制！
                            if (gradient > 8) {
                                System.out.println("🚨 [残差旁路触发] 网络濒临崩溃！启动残差连接，抛弃高画质，只传关键音频和字幕！\n");
                                bypassProcess(videoSegment); // 走VIP应急通道
                                continue;
                            }

                            // 普通拥堵时，自适应地降低后续输入的预期（这里用打印提示模拟）
                            System.out.println("📉 [自适应建议] 建议主播将下一帧从 '4K' 降级为 '720P' 发送。\n");
                        }

                        // 正常入队流程（网络通畅或轻度拥堵）
                        try {
                            queue.put(videoSegment);
                            System.out.println("✅ [推流端] 成功发出 [" + videoSegment + "] (当前网络缓冲: " + queue.size() + "秒)\n");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();

        // 消费者线程（模拟观众播放端，故意设置极慢且波动的读取速度）
        new Thread(() -> {
            LinkedBlockingQueue<String> queue = networkBuffer.get(videoChannel);
            while (true) {
                try {
                    String segment = queue.take();
                    System.out.println("📺 [观众端] 正在流畅播放: " + segment);
                    // 模拟观众端网速极慢，导致缓冲区容易堆积
                    Thread.sleep(4000);
                } catch (InterruptedException e) { e.printStackTrace(); }
            }
        }).start();
    }

    // 残差旁路处理方法（Skip Connection for Video）
    private static void bypassProcess(String originalSegment) {
        // 在真实场景中，这里会调用前向纠错(FEC)或者直接抽取音视频流中的基础层(Base Layer)进行传输
        System.out.println("✨ [残差终点] 原始 [" + originalSegment + "] 已被拦截，已通过旁路无损送达 ['纯音频+字幕']，观众未感觉到卡顿！");
    }
}
