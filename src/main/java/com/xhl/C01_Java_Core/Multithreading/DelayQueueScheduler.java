package com.xhl.C01_Java_Core.Multithreading;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.DelayQueue;

/**
 * @Author: xhl
 * @Date: 2026-05-31 14:59
 * @Description: 延迟队列 定时器
 * 核心思路
 * 计算延迟时间：计算从“现在”到“本周日 23:59:59”还有多少毫秒。
 * 封装任务：创建一个实现了 Delayed 接口的任务对象，放入 DelayQueue。
 * 消费者监听：开启一个线程，不断从队列中获取任务（take()），当时间到达时，任务会自动从队列中释放并被执行。
 */

import java.time.*;
import java.util.concurrent.*;

public class DelayQueueScheduler {

    public static void main(String[] args) {
        // 创建延迟队列
        DelayQueue<DelayedTask> delayQueue = new DelayQueue<>();

        // ****************** 关键逻辑：计算本周日 23:59:59 的时间差 ******************

        // 1. 获取当前时间 (海口时区)
        ZoneId zoneId = ZoneId.of("Asia/Shanghai"); // 海口使用北京时间
        ZonedDateTime now = ZonedDateTime.now(zoneId);

        // 2. 构建本周日 (Sunday) 的 23:59:59
        // 注意：DayOfWeek.SUNDAY 是星期日
        ZonedDateTime targetTime = now
                .with(DayOfWeek.SUNDAY) // 跳转到本周日
                .with(LocalTime.of(23, 59, 59)); // 设置时间为 23:59:59

        // 3. 计算延迟时间 (毫秒)
        Duration duration = Duration.between(now, targetTime);
        long delayInMillis = duration.toMillis();

        // 防止计算出错（比如当前时间已经过了周日，不过今天是周日，通常不会）
        if (delayInMillis < 0) {
            System.out.println("当前时间已超过本周日 23:59:59");
            return;
        }

        System.out.println("当前时间: " + now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        System.out.println("任务计划执行时间: " + targetTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        System.out.println("距离任务执行还有: " + delayInMillis + " 毫秒 (" + (delayInMillis / 1000 / 60) + " 分钟)");

        // ****************** 将任务放入队列 ******************

        // 提交任务到队列
        delayQueue.put(new DelayedTask(() -> {
            System.out.println("\n[任务触发] 现在时间: " + ZonedDateTime.now(zoneId).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            System.out.println("执行周日 23:59:59 的清理/结束任务！");
            // 在这里写你的具体业务逻辑，例如：更新活动状态、发送通知等
        }, System.currentTimeMillis() + delayInMillis));

        // ****************** 消费者线程 (监听队列) ******************

        // 开启一个线程去消费队列
        Thread consumerThread = new Thread(() -> {
            try {
                while (!Thread.interrupted()) {
                    // take() 方法会阻塞，直到队列头部的任务延迟时间到达
                    DelayedTask task = delayQueue.take();
                    // 执行任务
                    task.task.run();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        consumerThread.setDaemon(false); // 非守护线程，确保任务能执行完
        consumerThread.start();
    }

    // 1. 定义任务类，必须实现 Delayed 接口
    static class DelayedTask implements Delayed {
        private final Runnable task; // 要执行的具体任务
        private final long executeTime; // 任务的执行时间戳 (毫秒)

        public DelayedTask(Runnable task, long executeTime) {
            this.task = task;
            this.executeTime = executeTime;
        }

        // getDelay 方法：返回当前任务还需要多久才能执行
        @Override
        public long getDelay(TimeUnit unit) {
            long diff = executeTime - System.currentTimeMillis();
            return unit.convert(diff, TimeUnit.MILLISECONDS);
        }

        // compareTo 方法：队列排序依据，时间越早的排在前面
        @Override
        public int compareTo(Delayed o) {
            return Long.compare(this.executeTime, ((DelayedTask) o).executeTime);
        }
    }
}
