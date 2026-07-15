package com.xhl.C09_Career_Growth.ODExam.A177.Answer;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @Author: xhl
 * @Date: 2026-06-25 18:18
 * @Description: 54 单向链表中间节点
 */
public class MiddleNode {
    static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 输入链表头节点地址和节点数

        String[] firstLine = sc.nextLine().split(" ");
        String headAddress = firstLine[0];
        int n = Integer.parseInt(firstLine[1]);

        // 创建 HashMap 存储每个节点的值和下一个节点的地址
        Map<String,String[]> nodeMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            String[] nodeData = sc.nextLine().split(" ");
            String address = nodeData[0];
            String value = nodeData[1];
            String nextAdress = nodeData[2];
            nodeMap.put(address,new String[]{value,nextAdress});
        }
        // 初始化慢指针和快指针，均指向头节点
        String slow =headAddress;
        String fast = headAddress;
        // 快指针每次走两步，慢指针每次走一步，直到快指针到达链表末尾
        while (fast != null && nodeMap.containsKey(fast)){
            fast = nodeMap.get(fast)[1]; // 快指针走一步
            if(fast == null || !nodeMap.containsKey(fast)){
                break; // 如果快指针到达链表末尾，结束
            }
            fast = nodeMap.get(fast)[1];// 快指针再走一步
            slow = nodeMap.get(slow)[1];// 慢指针走一步
        }

        // 输出慢指针指向的节点的值
        System.out.println(nodeMap.get(slow)[0]);
    }

}
