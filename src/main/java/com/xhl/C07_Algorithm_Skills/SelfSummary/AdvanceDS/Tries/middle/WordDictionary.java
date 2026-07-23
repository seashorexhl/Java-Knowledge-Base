package com.xhl.C07_Algorithm_Skills.SelfSummary.AdvanceDS.Tries.middle;

/**
 * @Author: xhl
 * @Date: 2026-07-02 02:44
 * @Description:    211. 添加与搜索单词 - 数据结构设计
 *  方法一：字典树
 */
public class WordDictionary {
    /**
     * 方法一：字典树
     * */
    private Trie root;

    /**
     *  构造方法
     * */
    public WordDictionary() {
        root = new Trie();
    }
    /**
     *  添加 单词
     * */
    public void addWord(String word) {
        root.insert(word);
    }

    /**
     *  搜索
     * */
    public boolean search(String word) {
        return dfs(word, 0, root);
    }

    /**
     *  深度优先
     * */
    private boolean dfs(String word, int index, Trie node) {
        if (index == word.length()) {
            return node.isEnd();
        }
        char ch = word.charAt(index);
        if (Character.isLetter(ch)) {
            int childIndex = ch - 'a';
            Trie child = node.getChildren()[childIndex];
            if (child != null && dfs(word, index + 1, child)) {
                return true;
            }
        } else {
            for (int i = 0; i < 26; i++) {
                Trie child = node.getChildren()[i];
                if (child != null && dfs(word, index + 1, child)) {
                    return true;
                }
            }
        }
        return false;
    }
}
/**
 * 字典树
 * */
class Trie {
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

    public Trie[] getChildren() {
        return children;
    }

    public boolean isEnd() {
        return isEnd;
    }
}

