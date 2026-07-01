package com.xhl.C06_Algorithm_Skills.SelfSummary.BaseDS.Heap;

/**
 * @Author: xhl
 * @Date: 2026-06-15 18:56
 * @Description: 模板方法模式（Template Method）的实现与应用
 */
// 抽象模板类
abstract class HeapOperationTemplate {
    // 模板方法：定义堆操作的固定流程（final防止覆盖）
    public final void execute() {
        allocateMemory();   // 通用步骤：分配内存
        customizeProcess(); // 抽象方法：子类定制逻辑
        releaseMemory();    // 通用步骤：释放内存
    }

    // 具体方法：父类实现
    private void allocateMemory() {
        System.out.println("分配堆内存...");
    }

    // 抽象方法：子类必须实现
    protected abstract void customizeProcess();

    private void releaseMemory() {
        System.out.println("释放堆内存...");
    }
}

// 具体子类：实现定制逻辑
class ImageProcessing extends HeapOperationTemplate {
    @Override
    protected void customizeProcess() {
        System.out.println("处理图像数据（占用大量堆内存）...");
    }
}