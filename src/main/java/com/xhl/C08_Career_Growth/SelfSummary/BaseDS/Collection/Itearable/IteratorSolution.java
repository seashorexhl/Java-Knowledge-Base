package com.xhl.Career_Growth.SelfSummary.BaseDS.Collection.Itearable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Author: xhl
 * @Date: 2026-06-17 06:18
 * @Description: 使用 Iterator 接口
 * 通常用于 ArrayList HashSet等的 遍历
 */
public class IteratorSolution {

    static void main() {
        List<String> list = new ArrayList<String>();

        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            String s = it.next();
            if ("待删除元素".equals(s)) {
                it.remove(); // 安全删除
            }
        }
        System.out.println("遍历：");
        System.out.println(list.toString());
    }
}
