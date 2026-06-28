package com.xhl.Career_Growth.SelfSummary.AdvancedAL.LFUCache;

import java.util.HashMap;
import java.util.LinkedHashSet;

/**
 * @Author: xhl
 * @Date: 2026-06-10 23:53
 * @Description: LFU Cache LFU缓存
 */
public class LFUCache {
    // KV 表
    HashMap<Integer,Integer> keyToVal;
    // KF 表
    HashMap<Integer,Integer> keyToFreq;
    //FK 表
    HashMap<Integer, LinkedHashSet<Integer>> freqToKeys;
    // 记录最小的频次
    int minFreq;
    // 记录 LFU缓存的 最大容量
    int cap;

    // 构造容量为 capacity 的缓存
    public LFUCache(int capacity) {
        keyToVal = new HashMap<>();
        keyToFreq = new HashMap<>();
        freqToKeys = new HashMap<>();
        this.cap = capacity;
        this.minFreq = 0;
    }
    // 在 缓存中 查询 key
    public int get(int key) {
        if(!keyToVal.containsKey(key)){
            return -1;
        }
        // 增加 key 对应的 freq
        increaseFreq(key);
        return keyToVal.get(key);
    }



    // 将 Key 和 Value 存入缓存
    public void put(int key, int value) {
        if(this.cap<=0) return ;

        /*若 key 已存在 ，修改对应的 val 即可*/
        if(keyToVal.containsKey(key)){
            keyToVal.put(key, value);
            // key 对应的 freq 加1
            increaseFreq(key);
            return;
        }
        /*key 不存在，需要插入*/
        /*容量 已满的话 需要淘汰一个 freq 最小的 key*/
        if(this.cap <= keyToFreq.get(key)){
            removeMinFreqKey();
        }
        /*插入 key 和 val,对应的 freq 为 1*/
        // 插入 KV表
        keyToVal.put(key, value);
        // 插入 KF 表
        keyToFreq.put(key, value);
        //插入 FK 表
        freqToKeys.putIfAbsent(key, new LinkedHashSet<>());
        freqToKeys.get(1).add(key);

        // 插入 新 key 后 最小的 freq 肯定是 1
        this.minFreq = 1;

    }

    /*移除*/
    private void removeMinFreqKey() {


    }
    /**/
    private void increaseFreq(int key) {
        int freq = keyToFreq.get(key);
        /*更新 KF 表*/

        /*更新 FK 表*/

        //
    }
}
