package com.xhl.C06_Algorithm_Skills.SelfSummary.AdvancedAL.UnionFind;

/**
 * @Author: xhl
 * @Date: 2026-06-15 22:41
 * @Description: 并查集解决方案
 */
/**
 * 扩展建议
 * 按大小合并（Union by Size）：若需关注集合大小，可将rank数组替换为size数组，合并时将小集合合并到大集合中
 * 泛型支持：如需处理非整数对象，可添加HashMap实现对象到索引的映射
 * 撤销操作：如需支持回退操作，可实现"带权并查集"或使用回溯技术
 * */
public class UnionFindSolution {
    public static void main(String[] args) {
        // 创建包含5个元素的并查集
        UnionFind uf = new UnionFind(5);

        // 进行一些合并操作
        uf.union(0, 1);
        uf.union(1, 2);
        uf.union(3, 4);

        System.out.println("0 和 2 是否连通: " + uf.connected(0, 2));  // true
        System.out.println("0 和 3 是否连通: " + uf.connected(0, 3));  // false
        System.out.println("当前连通分量数量: " + uf.getCount());      // 2

        uf.union(2, 4);
        System.out.println("0 和 4 是否连通: " + uf.connected(0, 4));  // true
        System.out.println("当前连通分量数量: " + uf.getCount());      // 1
    }

}
