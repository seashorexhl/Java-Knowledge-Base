package com.xhl.C07_Algorithm_Skills.SelfSummary.BaseDS.RBTree;

/**
 * @Author: xhl
 * @Date: 2026-06-08 22:50
 * @Description: 1. 定义哈夫曼树节点类
 */

class HuffmanNode implements Comparable<HuffmanNode> {
    char character;      // 存储的字符（仅叶子节点有效）
    int frequency;       // 频率/权重
    HuffmanNode left;    // 左子节点
    HuffmanNode right;   // 右子节点

    // 叶子节点构造器
    public HuffmanNode(char character, int frequency) {
        this.character = character;
        this.frequency = frequency;
        this.left = null;
        this.right = null;
    }

    // 内部合并节点构造器
    public HuffmanNode(int frequency, HuffmanNode left, HuffmanNode right) {
        this.character = '\0'; // 内部节点无具体字符
        this.frequency = frequency;
        this.left = left;
        this.right = right;
    }

    // 判断是否为叶子节点
    public boolean isLeaf() {
        return left == null && right == null;
    }

    // 重写比较方法，使优先队列按频率升序排列
    @Override
    public int compareTo(HuffmanNode other) {
        return this.frequency - other.frequency;
    }
}