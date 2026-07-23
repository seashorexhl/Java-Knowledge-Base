package com.xhl.C07_Algorithm_Skills.SelfSummary.AdvanceDS.Tries.middle.DesionTire;

/**
 * @Author: xhl
 * @Date: 2026-07-02 02:43
 * @Description: 208 实现前缀树
 *   前缀树 是一种树形数据结构，用于高效地存储和检索字符串数据集中的键。
 *   这一数据结构有相当多的应用情景，例如自动补全和拼写检查。
 */
public class Trie {

    /**
     *  方法一：字典树
     * */
    private Trie[] children;
    private boolean isEnd;

    public Trie() {
        children = new Trie[26];
        isEnd = false;
    }

    public void insert(String word) {
        Trie node = this;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            int index = ch - 'a';
            if (node.children[index] == null) {
                node.children[index] = new Trie();
            }
            node = node.children[index];
        }
        node.isEnd = true;
    }

    public boolean search(String word) {
        Trie node = searchPrefix(word);
        return node != null && node.isEnd;
    }

    public boolean startsWith(String prefix) {
        return searchPrefix(prefix) != null;
    }

    private Trie searchPrefix(String prefix) {
        Trie node = this;
        for (int i = 0; i < prefix.length(); i++) {
            char ch = prefix.charAt(i);
            int index = ch - 'a';
            if (node.children[index] == null) {
                return null;
            }
            node = node.children[index];
        }
        return node;
    }

}
