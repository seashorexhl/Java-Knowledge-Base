package com.xhl.Career_Growth.SelfSummary.AdvanceDS.BFS;

/**
 * @Author: xhl
 * @Date: 2026-06-10 23:16
 * @Description: 图中节点的 定义
 */
public class Node {
    int  element;
    public Node(int element) {
        this.element = element;
    }
    public int getElement() {
        return element;
    }
    public void setElement(int element) {
        this.element = element;
    }
    /*相邻的节点*/
    public Node[] adj() {
        return Node.this.adj();
    }
}
