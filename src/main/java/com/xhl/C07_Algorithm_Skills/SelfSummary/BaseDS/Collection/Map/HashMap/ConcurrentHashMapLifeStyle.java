package com.xhl.C07_Algorithm_Skills.SelfSummary.BaseDS.Collection.Map.HashMap;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: xhl
 * @Date: 2026-06-05 01:29
 * @Description: ConcurrentHashMap 并发哈希表
 */
public class ConcurrentHashMapLifeStyle {

    public static void main(String[] args) {
        /*一、 基础使用与初始化*/
        // 无参初始化
        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
        map.putIfAbsent("absent", 0);
        map.putIfAbsent("ss", 1);
        map.putIfAbsent("sa", 2);
        ConcurrentHashMap<String, Integer> concurrentMap = new ConcurrentHashMap<>();
        // 指定初始容量
        ConcurrentHashMap<String, Integer> mapWithCapacity = new ConcurrentHashMap<>(16);

        /*二、核心原子操作方法*/
        //1. 实现并发计数器
        ConcurrentHashMap<String, Integer> cnt = new ConcurrentHashMap<>();
        // 多线程下安全地进行累加：已存在则相加，否则设为 1
        cnt.merge("counter_key", 1, Integer::sum);

        //2.实现安全的本地缓存
        ConcurrentHashMap<String, String> cache = new ConcurrentHashMap<>();
        // 仅当 "user:1001" 不存在时，执行 lambda 表达式获取数据并放入缓存
        String userInfo = cache.computeIfAbsent("user:1001", k -> {
            // 模拟耗时操作，如从数据库查询用户信息
            return queryFromDatabase(k);
        });
        //3. 批量遍历与聚合
        // 弱一致性遍历
        map.forEach((k, v) -> System.out.println(k + "=" + v));
        // 归约操作：例如统计所有值的总和
        Integer sum = map.reduceValues(1, (value, sumSoFar) -> sumSoFar == null ? value : sumSoFar + value);

        /*常见的遍历方式及其适用场景*/
        /**
         *  1. 使用 forEach + Lambda 表达式（推荐）
         适用于只需要打印或简单处理键值对的场景
         */
        concurrentMap.forEach((key, value) -> {
            System.out.println("Key: " + key + ", Value: " + value);
        });
        /**
         *  2.使用 entrySet() + for-each 循环（性能最佳）
         * */
        for (Map.Entry<String, Integer> entry : concurrentMap.entrySet()) {
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }
        /**
         *  3.使用 Iterator 迭代器（适合遍历中删除）
         * */
        Iterator<Map.Entry<String, Integer>> iterator = concurrentMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Integer> entry = iterator.next();
            if ("delete_key".equals(entry.getKey())) {
                iterator.remove(); // 安全删除当前元素
            }
        }
        /**
         *  4.仅遍历键或仅遍历值
         * */
        // 仅遍历键
        for (String key : concurrentMap.keySet()) {
            System.out.println("Key: " + key + ", Value: " + concurrentMap.get(key));
        }

        // 仅遍历值
        for (Integer value : concurrentMap.values()) {
            System.out.println("Value: " + value);
        }
        /**
         * 5.JDK 8 并行聚合方法（适合海量数据）
         * */
        // 并行搜索第一个符合条件的元素
        String result = concurrentMap.search(1, (k, v) ->
                v > 100 ? k : null
        );

        // 并行归约求和
        Integer sum1 = concurrentMap.reduceValues(1,
                (v1, v2) -> v1 == null ? v2 : (v2 == null ? v1 : v1 + v2)
        );    }

    private static String queryFromDatabase(String k) {
        return null;
    }
}
