package com.xhl.C06_Algorithm_Skills.SelfSummary.BaseDS.RBTree;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @Author: xhl
 * @Date: 2026-06-05 00:56
 * @Description: 哈夫曼树的 几个主要应用场景
 * 1. 数据压缩与文件存储
 * 2. 网络通信与协议优化
 * 3. 任务调度与资源分配
 * 4. 聚类分析与磁盘存储
 */
/* 2.哈夫曼树的构建与编码生成 */
    /*
    完整生命周期：频率统计 -> 构建树 -> 生成编码映射 -> 编解码。
    */
public class HuffmanTree {
    // 1. 构建哈夫曼树
    public static HuffmanNode buildTree(Map<Character, Integer> freqMap) {
        PriorityQueue<HuffmanNode> pq = new PriorityQueue<>();

        // 将每个字符创建为叶子节点加入优先队列
        for (Map.Entry<Character, Integer> entry : freqMap.entrySet()) {
            pq.offer(new HuffmanNode(entry.getKey(), entry.getValue()));
        }

        // 反复合并，直到队列中只剩一个根节点
        while (pq.size() > 1) {
            HuffmanNode left = pq.poll();
            HuffmanNode right = pq.poll();

            // 创建新的父节点，频率为两者之和
            HuffmanNode parent = new HuffmanNode(left.frequency + right.frequency, left, right);
            pq.offer(parent);
        }
        return pq.poll(); // 返回根节点
    }

    // 2. 递归生成哈夫曼编码表
    public static Map<Character, String> generateCodes(HuffmanNode root) {
        Map<Character, String> codeMap = new HashMap<>();
        if (root != null) {
            buildCodes(root, "", codeMap);
        }
        return codeMap;
    }
    /*3.常用方法：编码与解码*/
    // 3. 编码文本
    public static String encode(String text, Map<Character, String> codeMap) {
        StringBuilder encoded = new StringBuilder();
        for (char c : text.toCharArray()) {
            String code = codeMap.get(c);
            if (code == null) throw new IllegalArgumentException("Character not found: " + c);
            encoded.append(code);
        }
        return encoded.toString();
    }

    // 4. 解码二进制字符串
    public static String decode(String encodedText, HuffmanNode root) {
        StringBuilder decoded = new StringBuilder();
        HuffmanNode current = root;

        for (char bit : encodedText.toCharArray()) {
            current = (bit == '0') ? current.left : current.right;

            // 到达叶子节点，记录字符并重置到根节点
            if (current.isLeaf()) {
                decoded.append(current.character);
                current = root;
            }
        }
        return decoded.toString();
    }
    private static void buildCodes(HuffmanNode node, String code, Map<Character, String> codeMap) {
        if (node.isLeaf()) {
            // 处理只有一个节点的极端情况
            codeMap.put(node.character, code.isEmpty() ? "0" : code);
        } else {
            buildCodes(node.left, code + "0", codeMap);
            buildCodes(node.right, code + "1", codeMap);
        }
    }
    /*
        4. 测试用例
     */
    public static void main(String[] args) {
        String text = "hello world";

        // 1. 统计字符频率
        Map<Character, Integer> freqMap = new HashMap<>();
        for (char c : text.toCharArray()) {
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        }

        // 2. 构建树并生成编码表
        HuffmanNode root = HuffmanTree.buildTree(freqMap);
        Map<Character, String> codes = HuffmanTree.generateCodes(root);

        // 打印编码表
        System.out.println("Huffman Codes:");
        codes.forEach((k, v) -> System.out.println("'" + k + "' -> " + v));

        // 3. 编码与解码测试
        String encoded = HuffmanTree.encode(text, codes);
        System.out.println("\nEncoded: " + encoded);

        String decoded = HuffmanTree.decode(encoded, root);
        System.out.println("Decoded: " + decoded);
    }


}
