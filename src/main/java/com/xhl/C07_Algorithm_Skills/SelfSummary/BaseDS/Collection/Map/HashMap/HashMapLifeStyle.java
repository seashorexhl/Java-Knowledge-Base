package com.xhl.C07_Algorithm_Skills.SelfSummary.BaseDS.Collection.Map.HashMap;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @Author: xhl
 * @Date: 2026-06-03 03:16
 * @Description: hashMap 哈希Map 基础 及生命周期
 *  用于 数据去重、词频统计、分组聚合
 *  1.默认首选 HashMap：除非题目明确要求处理多线程环境或保持特定顺序，否则一律使用 HashMap，
 *  因为它的时间复杂度最优。警惕空指针异常 (NPE)：在使用 map.get(key) 获取值并直接进行
 *  数值运算（如 count++）前，务必先判断返回值是否为 null。推荐使用 map.getOrDefault
 *  (key, defaultValue) 来优雅地处理默认值。
 *  2.高频 API 掌握：熟练掌握 put, get, containsKey, keySet(), entrySet() 等方法。
 * 特别是遍历 Map 时，推荐使用 for(Map.Entry<K,V> entry : map.entrySet()) 的方式，
 * 这比通过 keySet() 再二次 get 效率更高。
 */
public class HashMapLifeStyle {
    public static void main(String[] args) {
        // 整数映射到 字符串的 hash 表
        HashMap<Integer,String> map = new HashMap<>();

        // 字符串映射到 数组的哈希表
        HashMap<String,int[]> map2 = new HashMap<>();

        /*增删改查*/
        map.put(3,"a");
        map.put(2,"b");
        map.put(4,"c");
        map.put(5,"d");
        String s = map.get(2);
        System.out.println(s);

        /*判断哈希表中是否存在键 Key*/
        boolean containsKey = map.containsKey(2);
        System.out.println(containsKey);
        // 如果key 存在 删除并返回对应的值
        map.remove(2);
        // 获得Key的值，如果 key 不存在，则返回 defaultValue
        map.getOrDefault(2,null);
        System.out.println(map.get(2));
        // 获得Hash表中的 所有 Key
        Set<Integer> keySet =  map.keySet();
        System.out.println(keySet);
        // 如果 key 不存在 ，则将键值对 Key Value 放入 hash 表 ，如果存在 则什么都不做
        map.putIfAbsent(2,null);
        System.out.println(map.get(2));

        // HashMap 的遍历 使用 entrySet
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            System.out.println("键: " + entry.getKey() + ", 值: " + entry.getValue());
        }

    }
}
