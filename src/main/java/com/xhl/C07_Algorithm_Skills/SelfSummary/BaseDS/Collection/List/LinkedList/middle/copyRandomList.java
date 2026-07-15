package com.xhl.C07_Algorithm_Skills.SelfSummary.BaseDS.Collection.List.LinkedList.middle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: xhl
 * @Date: 2026-06-24 15:54
 * @Description: 138. 随机链表的复制 copyRandomList
 * 复制链表中的指针都不应指向原链表中的节点 。
 * 先克隆节点，再通过哈希表建立新旧节点的映射关系
 */
public class copyRandomList {
    /**
     * 方法一：回溯 + 哈希表
     **/
    //1.用哈希表记录每一个节点对应新节点的创建情况
    Map<Node, Node> cacheNode = new HashMap<>();

    public static void main(String[] args) {
        // 测试输入: [[7,null],[13,0],[11,4],[10,2],[1,0]]
        // 由于 Java 基本类型 int 不能存 null，这里用 -1 代替 null 作为 random 的索引
        int[][] input = {
                {7, -1},  // 7, random -> null
                {13, 0},  // 13, random -> index 0 (7)
                {11, 4},  // 11, random -> index 4 (1)
                {10, 2},  // 10, random -> index 2 (11)
                {1, 0}    // 1, random -> index 0 (7)
        };

        // 1. 构建原始链表
        Node head = buildList(input);

        // 2. 执行深拷贝 (可切换测试方法一或方法二)
        copyRandomList solution = new copyRandomList();
        Node copiedHead = solution.copyRandomList(head);
        // Node copiedHead = solution.copyRandomList1(head);

        // 3. 将新链表序列化并打印
        List<List<Object>> output = serialize(copiedHead);
        System.out.println("方法一递归：输出结果: " + output);

        // 4. 验证深拷贝是否成功 (内存地址不能相同，但值必须相同)
        System.out.println("是否为同一对象引用: " + (head == copiedHead)); // 应为 false
        System.out.println("Random 是否为同一对象引用: " + (head.random == copiedHead.random)); // 应为 false

        System.out.println("-------------------方法二：迭代 + 节点拆分------------------------------");
        Node headNew = solution.copyRandomList1(head);
        List<List<Object>> output1 = serialize(copiedHead);
        System.out.println("方法二：输出结果: " + output1);

        // 4. 验证深拷贝是否成功 (内存地址不能相同，但值必须相同)
        System.out.println("是否为同一对象引用: " + (head == copiedHead)); // 应为 false
        System.out.println("Random 是否为同一对象引用: " + (head.random == copiedHead.random)); // 应为 false

        /**
         * 方法一：构建
         * */
        // 构建链表:
        /*Node head = new Node(7);
        head.next = new Node(13);
        head.next.next = new Node(11);
        head.next.next.next = new Node(10);
        head.next.next.next.next = new Node(1);

        // 设置 random 指针
        head.random = null;
        head.next.random = head;
        head.next.next.random = head.next.next.next.next;
        head.next.next.next.random = head.next.next;
        head.next.next.next.next.random = head;


        copyRandomList crl = new copyRandomList();
        Node copied = crl.copyRandomList(head); // 建议用方法一测试，或 crl.copyRandomList1(head)

        // 打印验证
        while (copied != null) {
            System.out.print("Val: " + copied.val);
            System.out.println(", Random: " + (copied.random != null ? copied.random.val : "null"));
            copied = copied.next;
        }*/
    }

    /**
     *  辅助测试方法：
     * */
    // 将 LeetCode 格式的二维数组转换为链表
    public static Node buildList(int[][] arr) {
        if (arr == null || arr.length == 0) return null;
        Node[] nodes = new Node[arr.length];

        // 1. 创建所有节点
        for (int i = 0; i < arr.length; i++) {
            nodes[i] = new Node(arr[i][0]);
        }

        // 2. 连接 next 和 random
        for (int i = 0; i < arr.length; i++) {
            if (i < arr.length - 1) nodes[i].next = nodes[i + 1];
            if (arr[i][1] != -1 && arr[i][1] < arr.length) {
                // 注意：LeetCode 用 null 表示，在 Java 的 int 数组中通常用 -1 代替 null
                // 如果输入包含真正的 null，请使用 Integer[][] 类型
                nodes[i].random = nodes[arr[i][1]];
            }
        }
        return nodes[0];
    }

    // 将链表转换回 LeetCode 格式的二维数组 (方便验证)
    public static List<List<Object>> serialize(Node head) {
        List<List<Object>> result = new ArrayList<>();
        Map<Node, Integer> nodeToIndex = new HashMap<>();

        // 1. 建立节点到索引的映射
        int index = 0;
        for (Node curr = head; curr != null; curr = curr.next) {
            nodeToIndex.put(curr, index++);
        }

        // 2. 构建结果
        for (Node curr = head; curr != null; curr = curr.next) {
            List<Object> pair = new ArrayList<>();
            pair.add(curr.val);
            if (curr.random == null) {
                pair.add(null);
            } else {
                pair.add(nodeToIndex.get(curr.random));
            }
            result.add(pair);
        }
        return result;
    }

    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        if (!cacheNode.containsKey(head)) {
            Node NewHead = new Node(head.val);
            cacheNode.put(head, NewHead);
            NewHead.next = copyRandomList(head.next);
            NewHead.random = copyRandomList(head.random);

        }
        return cacheNode.get(head);
    }

   /**
    * 方法二：迭代 + 节点拆分
    * */
    public Node copyRandomList1(Node head) {
        if (head==null){
            return null;
        }
        // 1.克隆节点并交织拼接
        for (Node node = head;node!= null;node=node.next.next){
            Node NewHead = new Node(node.val);
            NewHead.next = node.next;
            node.next = NewHead;
        }
        // 2.借力打力，设置 random 指针
        for (Node node = head;node!=null;node=node.next.next ){
            Node NewHead = node.next;
            NewHead.random = (node.random!=null)?node.random.next:null;
        }
        // 3.拆分链表，恢复原状
        Node NewHead = head.next;
        for (Node node = head;node!=null;node = node.next){
            Node NewNode = node.next;
            node.next = node.next.next;
            NewHead.next =(NewHead.next != null ) ? NewHead.next.next : null;
        }
        return  NewHead;
    }
}
