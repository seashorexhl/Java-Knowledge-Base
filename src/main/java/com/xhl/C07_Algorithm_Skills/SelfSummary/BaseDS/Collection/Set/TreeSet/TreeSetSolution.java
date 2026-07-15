package com.xhl.C07_Algorithm_Skills.SelfSummary.BaseDS.Collection.Set.TreeSet;

import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

/**
 * @Author: xhl
 * @Date: 2026-06-26 20:14
 * @Description: 树集
 */
public class TreeSetSolution {
    static void main() {
        Set<String> s = new TreeSet<String>(Collections.reverseOrder());
        s.add("lxh");
        s.add("Engineer A");
        s.add("Engineer B");
        printCollections(s);
    }

    private static void printCollections(Set<String> s) {
        System.out.println(Set.copyOf(s));
    }

}
