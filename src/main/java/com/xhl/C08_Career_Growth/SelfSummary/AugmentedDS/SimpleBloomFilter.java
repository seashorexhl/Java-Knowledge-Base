package com.xhl.Career_Growth.SelfSummary.AugmentedDS;

/**
 * @Author: xhl
 * @Date: 2026-06-11 06:55
 * @Description: 构建 简单的布隆过滤器（Bloom Filter）  脑筋急转弯
 */
import java.util.BitSet;
/**
 *  纯Java 语言构建
 * */
public class SimpleBloomFilter {
    private final BitSet bitSet;
    private final int size;
    private final int[] hashSeeds;

    public SimpleBloomFilter(int expectedElements, double falsePositiveRate) {
        // 根据公式计算最优位数组大小 m = -n*ln(p)/(ln2)^2
        this.size = (int) (-expectedElements * Math.log(falsePositiveRate) / Math.pow(Math.log(2), 2));
        // 计算最优哈希函数数量 k = m/n * ln2
        int hashCount = Math.max(1, (int) (this.size / expectedElements * Math.log(2)));

        this.bitSet = new BitSet(this.size);
        this.hashSeeds = new int[hashCount];
        for (int i = 0; i < hashCount; i++) {
            hashSeeds[i] = i * 31 + 7; // 简单的哈希种子生成
        }
    }

    public void add(String element) {
        for (int seed : hashSeeds) {
            int hash = Math.abs((element.hashCode() ^ seed) % size);
            bitSet.set(hash, true);
        }
    }

    public boolean mightContain(String element) {
        for (int seed : hashSeeds) {
            int hash = Math.abs((element.hashCode() ^ seed) % size);
            if (!bitSet.get(hash)) return false; // 只要有一位为0，则绝对不存在
        }
        return true; // 所有位都为1，可能存在（有误判概率）
    }
}
