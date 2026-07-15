package com.xhl.C09_Career_Growth.ODExam.A177.Answer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @Author: xhl
 * @Date: 2026-04-11 23:14
 * @Description: 04 内存资源分配
 */
public class MemoryAllocation {
    public static void main(String[] args) {
        Scanner sc =new Scanner(System.in);
        // 读取内存池资源列表
        String memoryInfo = sc.next();
        // 读取申请列表
        String applyList = sc.next();

        // 内存信息
        // 创建一个ArrayList对象，用于存储内存池中可用的内存大小
        List<Integer> memoryList = new ArrayList<>();
        // 将内存池资源列表按逗号分隔，转换为ArrayList对象
        List<String> memoryInfoList = new ArrayList<>(Arrays.asList(memoryInfo.split(",")));
        // 遍历内存池资源列表
        for (String info : memoryInfoList) { // 遍历内存池资源列表
            int colonIndex = info.indexOf(":"); // 找到冒号的位置
            int size = Integer.parseInt(info.substring(0, colonIndex)); // 截取内存大小
            int count = Integer.parseInt(info.substring(colonIndex + 1)); // 截取内存块数量
            for (int i = 0; i < count; i++) { // 将内存块数量的内存大小添加到内存列表中
                memoryList.add(size);
            }
        }

        // 申请信息
        // 创建一个ArrayList对象，用于存储申请的内存大小
        List<Integer> applyMemoryList  = new ArrayList<>();
        // 将申请列表按逗号分隔，转换为ArrayList对象
        List<String> applyListList = new ArrayList<>(Arrays.asList(applyList.split(",")));
        // 遍历申请列表
        for (String apply : applyListList) {
            applyMemoryList.add(Integer.parseInt(apply));// 将申请的内存大小添加到申请内存列表中
        }


        // 分配内存
        List<Boolean> resultList = new ArrayList<>(); // 创建一个ArrayList对象，用于存储每个申请是否成功
        for (int applyMemory : applyMemoryList) { // 遍历申请内存列表
            boolean flag = false; // 定义一个标志位，用于标记是否成功分配内存
            for (int i = 0; i < memoryList.size(); i++) { // 遍历内存列表
                if (memoryList.get(i) >= applyMemory) { // 如果当前内存块的大小大于等于申请的内存大小
                    flag = true; // 标记成功分配内存
                    memoryList.remove(i); // 将当前内存块从内存列表中移除
                    break; // 跳出循环
                }
            }
            resultList.add(flag); // 将是否成功分配内存的结果添加到结果列表中
        }

        // 输出结果
        for (int i = 0; i < resultList.size(); i++) { // 遍历结果列表
            System.out.print(resultList.get(i)); // 输出当前申请是否成功分配内存
            if (i != resultList.size() - 1) { // 如果不是最后一个结果
                System.out.print(","); // 输出逗号分隔符
            }
        }
    }

}
