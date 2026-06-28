package com.xhl.C06_Algorithm_Skills.SelfSummary.BaseDS.Collection.Collections;

import java.util.*;

/**
 * @Author: xhl
 * @Date: 2026-06-23 10:12
 * @Description: Collections接口 时空漫游者的能量跳跃 量子网络穿梭
 */
public class CollectionsTemplate {



    public static void main(String[] args) {
        /**
         *  一、排序与反转（最高频）
         */
        List<Integer> list = new ArrayList<>(Arrays.asList(3, 1, 4, 1, 5));

        // 1. 升序排序（默认）
        Collections.sort(list);    // [1, 1, 3, 4, 5]

        // 2. 降序排序
        Collections.sort(list, Collections.reverseOrder()); // [5, 4, 3, 1, 1]

        // 3. 自定义排序（按字符串长度）
        List<String> strs = Arrays.asList("aaa", "b", "cc");
        Collections.sort(strs, (a, b) -> a.length() - b.length()); // [b, cc, aaa]

        // 4. 反转列表顺序
        Collections.reverse(list);  // 将list顺序完全颠倒

        // 5. 随机打乱（洗牌算法）
        Collections.shuffle(list);  // 随机排列

        // 6. 交换两个位置的元素
        Collections.swap(list, 0, 2); // 交换索引0和2

        /**
         * 二、查找与极值（秒杀题）
         * */
        List<Integer> list1 = Arrays.asList(3, 1, 4, 1, 5);

        // 1. 二分查找（必须已排序！）
        Collections.sort(list);  // [1, 1, 3, 4, 5]
        int index = Collections.binarySearch(list, 4);  // 返回 3
        // 找不到返回负数：-(插入点) - 1

        // 2. 最大值/最小值
        int max = Collections.max(list);    // 5
        int min = Collections.min(list);    // 1

        // 3. 查找首次出现的索引（List自带，非Collections）
        int firstIndex = list.indexOf(1);   // 0

        // 4. 查找最后一次出现的索引
        int lastIndex = list.lastIndexOf(1); // 1

        // 5. 检查是否包含某元素（List自带）
        boolean exists = list.contains(4);  // true

        /**
         * 三、频率统计与填充（冷门但好用）
         * */
        List<Integer> list2 = Arrays.asList(1, 2, 2, 3, 2, 4);

        // 1. 统计某元素出现次数
        int count = Collections.frequency(list, 2);  // 3

        // 2. 用指定元素填充整个列表
        Collections.fill(list, 0);  // [0, 0, 0, 0, 0, 0]

        // 3. 将所有元素替换为另一个值
        Collections.replaceAll(list, 0, 999);  // [999, 999, 999, 999, 999, 999]

        // 4. 复制列表（目标列表长度必须 >= 源列表）
        List<Integer> dest = new ArrayList<>(Arrays.asList(0,0,0,0,0,0));
        Collections.copy(dest, list);  // dest = [999, 999, 999, 999, 999, 999]

        /**
         *  四、不可变集合（防篡改）
         * */
        // 1. 创建空集合（不可修改）
        List<String> empty = Collections.emptyList();
        Set<String> emptySet = Collections.emptySet();
        Map<String, String> emptyMap = Collections.emptyMap();

        // 2. 创建单元素集合（不可修改）
        List<String> single = Collections.singletonList("only");
        Set<String> singleSet = Collections.singleton("only");
        Map<String, Integer> singleMap = Collections.singletonMap("key", 1);

        // 3. 包装为不可修改视图（只读）
        List<Integer> original = new ArrayList<>(Arrays.asList(1,2,3));
        List<Integer> readOnly = Collections.unmodifiableList(original);
        // readOnly.add(4); // ❌ 抛出UnsupportedOperationException
        // 但 original.add(4) 可以，readOnly也会同步变化！

        // 4. 线程安全的包装
        List<Integer> syncList = Collections.synchronizedList(new ArrayList<>());
        Map<String, String> syncMap = Collections.synchronizedMap(new HashMap<>());
        /**
         * 五、快速初始化（组合技）
         **/
        // 1. 快速创建List（固定长度，不可增删）
        List<Integer> list3 = Arrays.asList(1, 2, 3, 4);
        // list1.add(5); // ❌ 抛出UnsupportedOperationException

        // 2. 可变List（可增删）
        List<Integer> list4 = new ArrayList<>(Arrays.asList(1, 2, 3, 4));

        // 3. 快速创建Set/Map（Java 9+）
        Set<Integer> set = Set.of(1, 2, 3);      // 不可变
        Map<String, Integer> map = Map.of("a", 1, "b", 2); // 不可变

        // 4. 旧版本兼容方式
        Set<Integer> setOld = new HashSet<>(Arrays.asList(1, 2, 3));
        Map<String, Integer> mapOld = new HashMap<String, Integer>() {{
            put("a", 1);
            put("b", 2);
        }};
        /**
         *  七、机试高频组合拳（直接背）
         * */
        // 1. 数组转List并排序（最常用）
        int[] arr = {3, 1, 4, 1, 5};
        List<Integer> list5 = new ArrayList<>();
        for (int x : arr) list.add(x);
        Collections.sort(list);

        // 2. List转数组（方便打印）
        Integer[] array = list.toArray(new Integer[0]);

        // 3. 取前K大元素（排序后取）
        int k = 4;
        Collections.sort(list, Collections.reverseOrder());
        List<Integer> topK = list.subList(0, k); // subList是视图，小心！

        // 4. 去重（转为Set再转回）
        Set<Integer> set1 = new HashSet<>(list);
        List<Integer> distinct = new ArrayList<>(set);

        // 5. 求两个集合的交集（retainAll会修改原集合）
        List<Integer> listA = new ArrayList<>(Arrays.asList(1,2,3,4));
        List<Integer> listB = Arrays.asList(3,4,5,6);
        listA.retainAll(listB); // listA变成 [3,4]（交集）

        // 6. 求差集
        listA.removeAll(listB); // listA变成 [1,2]（差集）

        /**
         *  八、常用操作
         * */
        // 1.判空
        System.out.println(list.isEmpty());
        // 2.
//        list.size();
//        list.contains();
//        list.add();
//        list.remove();
//        list.clear();
//        Iterator<Integer> iterator();
//        /*获得集合的基本类型 和数据视图*/
//        Object[] toArray();


    }


}
