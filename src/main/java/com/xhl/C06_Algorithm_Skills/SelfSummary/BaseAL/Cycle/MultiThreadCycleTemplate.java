package com.xhl.C06_Algorithm_Skills.SelfSummary.BaseAL.Cycle;

/**
 * @Author: xhl
 * @Date: 2026-06-10 02:36
 * @Description:  进阶：多线程交替循环模板
 */
public class MultiThreadCycleTemplate {
    static class MyPrinter {
        int flag = 1; // 状态标志位

        public void print(int printFlag, int nextFlag) {
            // 外层控制循环次数，无限循环可改为 while(true)
            for (int j = 0; j < 10; j++) {
                synchronized (this) {
                    // 如果当前不是自己的回合，则等待
                    while (flag != printFlag) {
                        try { this.wait(); } catch (InterruptedException e) { e.printStackTrace(); }
                    }

                    // 执行当前线程的业务逻辑（循环体）
                    System.out.println(Thread.currentThread().getName() + " 正在执行...");

                    // 唤醒其他等待的线程，并将控制权交接给下一个线程
                    this.notifyAll();
                    flag = nextFlag;
                }
            }
        }
    }
}