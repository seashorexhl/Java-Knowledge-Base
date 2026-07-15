package com.xhl.C07_Algorithm_Skills.SelfSummary.BaseDS.RBTree;

/**
 * @Author: xhl
 * @Date: 2026-06-08 06:22
 * @Description: 红黑树 节点的构建
 */
public class RedBlackTree {

    private RBNode root;

    // 2. 左旋操作
    private void leftRotate(RBNode x) {
        RBNode y = x.right;
        x.right = y.left;
        if (y.left != null) {
            y.left.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == null) {
            root = y;
        } else if (x == x.parent.left) {
            x.parent.left = y;
        } else {
            x.parent.right = y;
        }
        y.left = x;
        x.parent = y;
    }

    // 3. 右旋操作
    private void rightRotate(RBNode x) {
        RBNode y = x.left;
        x.left = y.right;
        if (y.right != null) {
            y.right.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == null) {
            root = y;
        } else if (x == x.parent.right) {
            x.parent.right = y;
        } else {
            x.parent.left = y;
        }
        y.right = x;
        x.parent = y;
    }

    // 4. 对外暴露的插入方法
    public void insert(int key) {
        RBNode node = new RBNode(key);
        if (root == null) {
            root = node;
            root.color = false; // 根节点必须为黑色
            return;
        }

        // 标准的 BST 插入过程寻找父节点
        RBNode current = root, parent = null;
        while (current != null) {
            parent = current;
            if (key < current.data) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        // 挂载到父节点上
        node.parent = parent;
        if (key < parent.data) {
            parent.left = node;
        } else {
            parent.right = node;
        }

        // 执行插入后的平衡修复
        fixAfterInsertion(node);
    }

    // 5. 核心修复逻辑
    private void fixAfterInsertion(RBNode x) {
        // 当父节点存在且为红色时，说明出现了连续红节点，需要修复
        while (x.parent != null && x.parent.color == true) {
            RBNode grandParent = x.parent.parent;

            // 父节点是祖父节点的左孩子
            if (x.parent == grandParent.left) {
                RBNode uncle = grandParent.right;

                // 情况1：叔叔节点是红色 -> 变色并向上回溯
                if (uncle != null && uncle.color == true) {
                    x.parent.color = false;
                    uncle.color = false;
                    grandParent.color = true;
                    x = grandParent;
                } else {
                    // 情况2：叔叔节点是黑色，且当前节点是右孩子 -> 先对父节点左旋，转为情况3
                    if (x == x.parent.right) {
                        x = x.parent;
                        leftRotate(x);
                    }
                    // 情况3：叔叔节点是黑色，且当前节点是左孩子 -> 父变黑，祖父变红，对祖父右旋
                    x.parent.color = false;
                    x.parent.parent.color = true;
                    rightRotate(x.parent.parent);
                }
            }
            // 父节点是祖父节点的右孩子（与上面完全对称的逻辑）
            else {
                RBNode uncle = grandParent.left;
                if (uncle != null && uncle.color == true) {
                    x.parent.color = false;
                    uncle.color = false;
                    grandParent.color = true;
                    x = grandParent;
                } else {
                    if (x == x.parent.left) {
                        x = x.parent;
                        rightRotate(x);
                    }
                    x.parent.color = false;
                    x.parent.parent.color = true;
                    leftRotate(x.parent.parent);
                }
            }
        }
        // 无论经历多少次变换，最终都要保证根节点是黑色的
        root.color = false;
    }

    // 1. 内部节点定义
    private static class RBNode {
        int data;
        RBNode left, right, parent;
        boolean color; // true 表示红色，false 表示黑色

        public RBNode(int data) {
            this.data = data;
            this.color = true; // 新插入的节点默认初始化为红色
        }
    }
}
