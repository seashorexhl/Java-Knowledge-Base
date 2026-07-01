package com.xhl.C06_Algorithm_Skills.SelfSummary.AdvanceDS.Tries;

/**
 * @Author: xhl
 * @Date: 2026-06-12 14:15
 * @Description: 哈希映射实现
 */
/**
 * 核心应用场景
 * 字典树在以下场景中表现极其出色：
 * 搜索框自动补全：用户输入前缀时，快速提供候选词列表。
 * 拼写检查：快速验证单词是否存在于词典中。
 * IP 路由最长前缀匹配：在网络路由表中高效查找目标地址。
 * 字符串去重与排序：利用树的结构特性进行字典序排列或过滤重复项。
 * */
import java.util.HashMap;
import java.util.Map;

class TrieNodeA {
    Map<Character, TrieNodeA> children = new HashMap<>();
    boolean isEnd = false;
}

public class GenericTrie {
    private TrieNodeA root;

    public GenericTrie() {
        root = new TrieNodeA();
    }

    // 插入单词
    public void insert(String word) {
        TrieNodeA node = root;
        for (char ch : word.toCharArray()) {
            // 如果不存在该字符分支，则创建新节点
            node.children.putIfAbsent(ch, new TrieNodeA());
            node = node.children.get(ch);
        }
        node.isEnd = true;
    }

    // 精确查询单词
    public boolean search(String word) {
        TrieNodeA node = root;
        for (char ch : word.toCharArray()) {
            node = node.children.get(ch);
            if (node == null) return false;
        }
        return node.isEnd;
    }

    // 前缀查询
    public boolean startsWith(String prefix) {
        TrieNodeA node = root;
        for (char ch : prefix.toCharArray()) {
            node = node.children.get(ch);
            if (node == null) return false;
        }
        return true;
    }
}