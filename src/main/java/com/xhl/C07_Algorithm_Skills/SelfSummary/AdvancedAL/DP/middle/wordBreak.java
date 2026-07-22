package com.xhl.C07_Algorithm_Skills.SelfSummary.AdvancedAL.DP.middle;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author: xhl
 * @Date: 2026-07-01 22:45
 * @Description: 139. 单词拆分
 * 给你一个字符串 s 和一个字符串列表 wordDict 作为字典。如果可以利用字典中出现的一个
 * 或多个单词拼接出 s 则返回 true。
 * 注意：不要求字典中出现的单词全部都使用，并且字典中的单词可以重复使用。
 */
public class wordBreak {
    // 2. 构建字典树
    private TrieNode root = new TrieNode();

    static void main(String[] args) {
        String s = "leetcode";
        List<String> wordDict = new ArrayList<>();
        wordDict.add("leet");
        wordDict.add("code");

        wordBreak wb = new wordBreak();
        System.out.println("-----------------方法一：动态规划------------------------------");
        boolean b = wb.wordBreak(s, wordDict);
        System.out.println("是否可以利用字典中出现的一个或多个单词拼接出单词 ? " + b);
        System.out.println("-----------------方法二：结合字典树 Trie 来实现------------------------------");
        boolean b1 = wb.wordBreak1(s, wordDict);
        System.out.println("是否可以利用字典中出现的一个或多个单词拼接出单词 ? " + b1);
        System.out.println("-----------------方法三：优化动态规划------------------------------");
        boolean b2 = wb.wordBreak2(s, wordDict);
        System.out.println("是否可以利用字典中出现的一个或多个单词拼接出单词 ? " + b2);

    }

    /**
     *  方法一：动态规划
     * */
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && set.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }

    private void insert(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            int idx = c - 'a';
            if (node.children[idx] == null) {
                node.children[idx] = new TrieNode();
            }
            node = node.children[idx];
        }
        node.isEnd = true;
    }

    /**
     *  方法二：结合字典树 Trie 来实现
     * */
    public boolean wordBreak1(String s, List<String> wordDict) {
        // 将所有单词插入 Trie
        for (String word : wordDict) insert(word);

        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;

        for (int i = 1; i <= s.length(); i++) {
            // 3. 核心优化：用 Trie 替代 set.contains()
            // 从 i-1 开始往回走（或者从 j 开始往前走）
            // 这里我们从 j = i-1 开始往回看，或者从 j 开始往下走都可以
            // 推荐从 j 开始往下走，逻辑更顺：
            for (int j = 0; j < i; j++) {
                if (!dp[j]) continue; // 前半段不合法，直接跳过

                // 从 dp[j] 合法的位置开始，在 Trie 中匹配 s[j...i-1]
                TrieNode node = root;
                for (int k = j; k < i; k++) {
                    char c = s.charAt(k);
                    node = node.children[c - 'a'];
                    if (node == null) break; // 字典树中没有这个前缀，剪枝！

                    // 如果匹配到了一个完整的单词，且前半段也合法
                    if (node.isEnd) {
                        dp[i] = true;
                        break; // 找到了，不需要继续往后匹配了
                    }
                }
                if (dp[i]) break; // 既然 dp[i] 已经为 true，外层循环也可以停了
            }
        }
        return dp[s.length()];
    }

    /**
     *  方法三：优化 HashSet 解法
     * */
    // 优化：哈希降维、虚拟节点防越界、子问题切割
    public boolean wordBreak2(String s, List<String> wordDict) {
        // 1. 将 List 转为 Set，保证 O(1) 查找
        Set<String> set = new HashSet<>(wordDict);
        // 2. 核心优化：计算字典中最长单词的长度
        int maxLen = 0;
        for (String word : wordDict) {
            maxLen = Math.max(maxLen, word.length());
        }
        // 3. 标准的 DP 数组初始化
        // dp[i] 代表字符串 s 的前 i 个字符（即下标 [0, i-1]）能否被拆分。
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        // 4. 外层循环：遍历字符串的每一个位置
        for (int i = 1; i <= s.length(); i++) {
            // 5. 内层循环：寻找切割点 j
            // 优化点：j 不需要从 0 开始，最多只需要往回看 maxLen 个字符
            for (int j = Math.max(0, i - maxLen); j < i; j++) {
                if (dp[j] && set.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break; // 只要找到一种合法的切割方式，就可以直接跳出
                }
            }
        }
        return dp[s.length()];
    }

    /**
     * 方法二：也可以考虑怎么结合字典树 Trie 来实现
     * */
    // 1. 定义 Trie 节点
    class TrieNode {
        TrieNode[] children = new TrieNode[26]; // 假设只有小写字母
        boolean isEnd = false;
    }
}
