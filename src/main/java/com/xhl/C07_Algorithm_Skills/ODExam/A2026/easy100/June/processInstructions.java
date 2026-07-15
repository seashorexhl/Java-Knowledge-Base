package com.xhl.C07_Algorithm_Skills.ODExam.A2026.easy100.June;

import java.util.*;

/**
 * @Author: xhl
 * @Date: 2026-07-10 05:10
 * @Description: 0607 完善核心代码编程-网络数据包收发处理-100分
 * 输入：
 * 输出：
 */
/**
 * 思路：
 *   1.使用合适的 数据结构 初始化缓冲区队列、发送区队列和缓冲区集合。
 *   2.解析每条指令：
 *   3.注意移动缓冲区数据时需清空缓冲区并维护顺序。
 *   4.输出数组与指令一一对应。
*/
public class processInstructions {
    static void main(String[] args) {
        List<String> instructions  = new ArrayList<>();
        instructions.add("RECEIVE 1");
        instructions.add("RECEIVE 2");
        instructions.add("QUERY");
        instructions.add("SEND");
        instructions.add("QUERY");
        instructions.add("SEND");

        List<Integer> list = processInstructions(instructions);
        System.out.println(list);
    }
    // 缓冲队列+状态转移  (双队列 + 哈希表 + 状态转移)
    public static List<Integer> processInstructions(List<String> instructions) {
        // 缓冲区队列：暂存接收到的消息，保持接收顺序
        Queue<Integer> bufQueue = new LinkedList<>();
        // 发送队列：准备发送的消息队列，消息按 FIFO 顺序发送
        Queue<Integer> sendQueue = new LinkedList<>();
        // 缓冲区去重集合：用于 O(1) 时间复杂度判断消息是否已在缓冲区中，防止重复接收
        Set<Integer> bufSet  = new HashSet<>();
        // 结果列表：记录每条指令执行后的输出值
        List<Integer> output = new ArrayList<>();

        // 遍历并逐条处理指令
        for (String inst: instructions) {
            // 1. 处理 RECEIVE 指令：接收新消息
            if (inst.startsWith("RECEIVE ")) {
                // 提取消息内容并转换为整数
                int x = Integer.parseInt(inst.substring(8).trim());
                // 检查消息是否已经存在于缓冲区中（去重校验）
                if (bufSet.contains(x)) {
                    // 如果已存在，输出 -1 表示接收失败（重复消息）
                    output.add(-1);
                } else {
                    // 消息合法，将其加入缓冲区队列和去重集合
                    bufQueue.add(x);
                    // 输出接收到的消息值，表示接收成功
                    bufSet.add(x);
                    output.add(x);
                }
                // 2. 处理 SEND 指令：从队列中取出并发送一条消息
            } else if (inst.equals("SEND")) {
                // 发送队列不为空，直接弹出队首消息并输出
                if (!sendQueue.isEmpty()) {
                    output.add(sendQueue.poll());
                    // 发送队列为空，但缓冲区有消息：
                } else if (!bufQueue.isEmpty()) {
                    // 将缓冲区的所有消息批量转移到发送队列（保持原有顺序）
                    while (!bufQueue.isEmpty()) {
                        sendQueue.add(bufQueue.poll());
                    }
                    // 转移完成后，清空缓冲区去重集合（因为消息已进入发送流程，允许再次接收相同消息）
                    bufSet.clear();
                    // 弹出并输出发送队列的队首消息
                    output.add(sendQueue.poll());
                }else {
                    // 发送队列和缓冲区都为空，无消息可发送，输出 0
                    output.add(0);
                }
                // 3. 处理 QUERY 指令：查询下一条待发送的消息（不取出）
            } else if (inst.equals("QUERY")) {
                if (!sendQueue.isEmpty()) {
                    // 发送队列不为空，查看队首消息并输出（不改变队列状态）
                    output.add(sendQueue.peek());
                    // 发送队列为空，但缓冲区有消息：
                }else if(!bufQueue.isEmpty()){
                    while (!bufQueue.isEmpty()) {
                        // 同样执行批量转移逻辑，将缓冲区消息移入发送队列
                        sendQueue.add(bufQueue.poll());
                    }
                    // 清空缓冲区去重集合
                    bufSet.clear();
                    // 查看并输出发送队列的队首消息
                    output.add(sendQueue.peek());
                }else {
                    // 没有任何消息可查询，输出 0
                    output.add(0);
                }
            }
        }
        // 返回所有指令的执行结果
        return output;
    }
}
