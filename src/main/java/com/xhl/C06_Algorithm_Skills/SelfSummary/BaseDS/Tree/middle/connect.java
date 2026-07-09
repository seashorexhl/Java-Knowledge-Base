package com.xhl.C06_Algorithm_Skills.SelfSummary.BaseDS.Tree.middle;


import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * @Author: xhl
 * @Date: 2026-06-22 13:01
 * @Description: 117. 填充每个节点的下一个右侧节点指针 II
 * 层序遍历 + Next 指针
 * 层序遍历 +
 */
public class connect {
    // 方法二：使用已建立的 next 指针
    Node last = null, nextStart = null;

    static void main() {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.right = new Node(7);

        connect conn = new connect();
        Node node = conn.connect(root);
        System.out.println(conn.serialize(node));
        System.out.println(conn.serializeByNext(node));

    }

    // 方法一:层序遍历
    public Node connect(Node root) {
        if (root == null) return null;
        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            Node last = null;
            for (int i = 1; i <= size; i++) {

                Node node = queue.poll();

                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
                if (i != 1) {
                    last.next = node;
                }
                last = node;
            }
        }
        return root;
    }
    // 方法二：使用已建立的 next 指针
    public Node connect1(Node root) {
        if (root == null) {
            return null;
        }
        Node start = root;
        while (start != null) {
            last = null;
            nextStart = null;
            for (Node p = start; p != null; p = p.next) {
                if (p.left != null) {
                    handle(p.left);
                }
                if (p.right != null) {
                    handle(p.right);
                }
            }
            start = nextStart;
        }
        return root;
    }

    public void handle(Node p) {
        if (last != null) {
            last.next = p;
        }
        if (nextStart == null) {
            nextStart = p;
        }
        last = p;
    }

    // 打印 序列化树结构（基于 next 指针的层序遍历，符合题意要求）
    public String serialize(Node root) {
        if (root == null) return "[]";

        List<String> result = new ArrayList<>();
        Node levelStart = root; // 记录当前层的起始节点

        // 只要当前层还有节点，就继续循环
        while (levelStart != null) {
            // 1. 顺着 next 指针遍历当前层的所有节点
            Node curr = levelStart;
            while (curr != null) {
                result.add(String.valueOf(curr.val));
                curr = curr.next; // 关键：使用 next 指针向右移动，而不是 left/right
            }

            // 2. 当前层遍历结束，添加 '#' 作为层级分隔符
            result.add("#");

            // 3. 寻找下一层的起始节点
            // 再次遍历当前层，找到第一个有子节点（左孩子或右孩子）的节点
            Node nextLevelStart = null;
            curr = levelStart;
            while (curr != null) {
                if (curr.left != null) {
                    nextLevelStart = curr.left;
                    break; // 找到了就立刻跳出
                }
                if (curr.right != null) {
                    nextLevelStart = curr.right;
                    break; // 找到了就立刻跳出
                }
                curr = curr.next; // 继续在当前层向右找
            }

            // 4. 将当前层指针下移到下一层
            levelStart = nextLevelStart;
        }

        return "[" + String.join(",", result) + "]";
    }

    // 打印 专门用于验证 next 指针的序列化方法
    public String serializeByNext(Node root) {
        if (root == null) return "[]";

        List<String> result = new ArrayList<>();
        Node currLevelStart = root;

        while (currLevelStart != null) {
            // 1. 顺着 next 指针遍历当前层
            Node p = currLevelStart;
            while (p != null) {
                result.add(String.valueOf(p.val));
                p = p.next;
            }
            // 2. 当前层结束，添加 '#'
            result.add("#");

            // 3. 寻找下一层的起始节点
            // 遍历当前层，找到第一个有子节点的节点
            Node nextLevelStart = null;
            p = currLevelStart;
            while (p != null) {
                if (p.left != null) {
                    nextLevelStart = p.left;
                    break;
                }
                if (p.right != null) {
                    nextLevelStart = p.right;
                    break;
                }
                p = p.next;
            }
            // 更新当前层起始节点为下一层
            currLevelStart = nextLevelStart;
        }

        return "[" + String.join(",", result) + "]";
    }
}
