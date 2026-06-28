package com.xhl.Career_Growth.SelfSummary.AdvanceDS.Tries;

/**
 * @Author: xhl
 * @Date: 2026-06-12 14:14
 * @Description:  数组实现 字典树
 */
/**
 *  核心应用场景
 * 字典树在以下场景中表现极其出色：
 * 搜索框自动补全：用户输入前缀时，快速提供候选词列表。
 * 拼写检查：快速验证单词是否存在于词典中。
 * IP 路由最长前缀匹配：在网络路由表中高效查找目标地址。
 * 字符串去重与排序：利用树的结构特性进行字典序排列或过滤重复项。
 * */
class TrieNode {
    TrieNode[] children;
    boolean isEnd; // 标记当前节点是否是一个单词的结尾

    public TrieNode() {
        children = new TrieNode[26]; // 对应 a-z
        isEnd = false;
    }
}

public class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // 插入单词
    public void insert(String word) {
        TrieNode cur = root;
        for (char c : word.toCharArray()) {
            int index = c - 'a'; // 计算字符对应的数组索引 0-25
            if (cur.children[index] == null) {
                cur.children[index] = new TrieNode();
            }
            cur = cur.children[index];
        }
        cur.isEnd = true; // 遍历结束，标记为单词结尾
    }

    // 精确查询单词
    public boolean search(String word) {
        TrieNode cur = root;
        for (char c : word.toCharArray()) {
            int index = c - 'a';
            if (cur.children[index] == null) {
                return false;
            }
            cur = cur.children[index];
        }
        return cur.isEnd; // 必须走到单词结尾才算找到
    }

    // 前缀查询
    public boolean startsWith(String prefix) {
        TrieNode cur = root;
        for (char c : prefix.toCharArray()) {
            int index = c - 'a';
            if (cur.children[index] == null) {
                return false;
            }
            cur = cur.children[index];
        }
        return true; // 只要能遍历完前缀就返回 true
    }
}
