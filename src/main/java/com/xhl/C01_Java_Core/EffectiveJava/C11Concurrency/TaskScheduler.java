package com.xhl.C01_Java_Core.EffectiveJava.C11Concurrency;

/**
 * @Author: xhl
 * @Date: 2026-07-01 09:23
 * @Description: 高并发任务调度器（TaskScheduler）
 */
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class TaskScheduler {

    // ================= 条目78：同步访问共享的可变数据 =================
    // 错误示范：private int count = 0; count++; (多线程下会丢失更新)
    // 正确做法1：使用原子类（无锁，性能高）
    private final AtomicInteger safeCount = new AtomicInteger(0);

    // 正确做法2：使用显式锁或 synchronized 保护可变状态
    private final ReentrantLock lock = new ReentrantLock();
    // ================= 条目80：Executor、Task 和 Stream 优先于线程 =================
    // 错误示范：new Thread(() -> doWork()).start(); (频繁创建销毁线程极其浪费资源)
    // 正确做法：使用线程池（ExecutorService）
    private final ExecutorService executor = Executors.newFixedThreadPool(10);
    // ================= 条目81：并发工具优先于 wait 和 notify =================
    // 错误示范：自己写 synchronized + wait() + notifyAll() (极易死锁或虚假唤醒)
    // 正确做法：使用 java.util.concurrent 下的高级工具
    private final BlockingQueue<Task> taskQueue = new LinkedBlockingQueue<>();
    private int unsafeState = 0;

    public void safeIncrement() {
        safeCount.incrementAndGet();
    }

    public void updateStateSafely(int newValue) {
        lock.lock();
        try {
            unsafeState = newValue;
        } finally {
            lock.unlock(); // 必须在 finally 中释放锁
        }
    }

    // ================= 条目79：避免过度同步 =================
    // 错误示范：把整个大方法都加上 synchronized，导致所有线程排队等待。
    // 正确做法：只同步真正需要保护的那几行代码（临界区）。
    public void processTask(Task task) {
        // 1. 耗时操作（如网络请求、复杂计算）放在锁外面
        String result = heavyComputation(task);

        // 2. 只有更新共享状态时才加锁
        lock.lock();
        try {
            unsafeState++;
        } finally {
            lock.unlock();
        }
    }

    public void scheduleTask(Task task) {
        // 提交任务，返回 Future，可以获取异步结果
        Future<String> future = executor.submit(() -> heavyComputation(task));
    }

    public void addTask(Task task) throws InterruptedException {
        taskQueue.put(task); // 队列满时自动阻塞，无需手写 wait()
    }

    // ================= 条目82：线程安全性的文档化 =================
    // 必须在 Javadoc 中明确说明该类的线程安全性。

    public ExpensiveResource getExpensiveResource() {
        // 第一次调用时才加载，且线程安全
        return ExpensiveResourceHolder.INSTANCE;
    }

    // ================= 条目84：不要依赖于线程调度器 =================
    // 错误示范：Thread.yield(); 或者依赖线程优先级来保证执行顺序。
    // 正确做法：程序的执行顺序不应该依赖于操作系统的线程调度策略。
    // 如果需要保证顺序，请使用同步机制（如 CountDownLatch, Semaphore）。
    public void badPractice() {
        // Thread.currentThread().setPriority(Thread.MAX_PRIORITY); // 绝对不要这么做！
    }

    // 占位方法
    private String heavyComputation(Task task) { return ""; }

    // ================= 条目83：明智谨慎地使用延迟初始化 =================
    // 错误示范：双重检查锁定（DCL）写错，或者对非耗时对象也做延迟初始化。
    // 正确做法：如果是单例或耗时对象，使用静态内部类模式（Holder）或 volatile + DCL。
    private static class ExpensiveResourceHolder {
        static final ExpensiveResource INSTANCE = new ExpensiveResource();
    }

    static class ExpensiveResource {}

    /**
     * 任务调度器。
     * <p>
     * 线程安全性：此类是【线程安全的】。所有对内部状态 {@code unsafeState} 的访问
     * 都通过 {@link ReentrantLock} 进行了同步保护。
     * </p>
     */
    public class Task {}
}
