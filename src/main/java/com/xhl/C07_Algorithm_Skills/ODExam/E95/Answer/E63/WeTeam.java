package com.xhl.C09_Career_Growth.ODExam.E95.Answer.E63;

import java.util.Scanner;

/**
 * @Author: xhl
 * @Date: 2026-06-30 19:53
 * @Description: 03  We Are A Team   补种未成活胡杨
 * 输入描述
 * 第一行包含两个整数 n，m(1<=n,m<100000),分别表示有 n 个人和 m 条消息
 * 随后的 m 行，每行一条消息，消息格式为：a b c(1<=a,b<=n,0<=c<=1)
 * 输出描述
 * c ==1,根据 a 和 b 是否在一个团队中输出一行字符串，在一个团队中输出‘we are a team‘,不在一个团队中输出’we are not a team’
 * c 为其他值，或当前行 a 或 b 的标号小于 1 或者大于 n 时，输出字符串‘da pian zi‘
 * 如果第一行 n 和 m 的值超出约定的范围时，输出字符串”Null“。
 */
public class WeTeam {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numPeople  = sc.nextInt();
        int numMessages  = sc.nextInt();

        // 读取消息并存储到二维数组中
        int[][] messages = new int[numMessages][3];
        for (int i = 0; i < numMessages; i++) {
            messages[i][0] = sc.nextInt();
            messages[i][1] = sc.nextInt();
            messages[i][2] = sc.nextInt();
        }
        // 检查输入范围，如果超出范围则输出 "Null"
        if (numPeople < 1 || numPeople >= 100000 || numMessages < 1 || numMessages >= 100000) {
            System.out.println("Null");
            return;
        }

        // 初始化数组，用于存储每个人的团队信息
        int[] parent = new int[numPeople + 1];
        for (int i = 0; i < numPeople + 1; i++) parent[i] = i;

        // 遍历消息，根据指令处理团队关系
        for (int[] message : messages) {
            int personA = message[0], personB = message[1], command = message[2];

            // 检查输入范围，如果超出范围则输出 "da pian zi"
            if (personA < 1 || personA > numPeople || personB < 1 || personB > numPeople) {
                System.out.println("da pian zi");
                continue;
            }

            // 如果指令为 0，则合并 personA 和 personB 所在的团队
            if (command == 0) {
                int rootA = find(personA, parent);
                int rootB = find(personB, parent);

                if (rootA != rootB) {
                    parent[rootB] = rootA;
                }
            }
            // 如果指令为 1，则判断 personA 和 personB 是否在同一个团队
            else if (command == 1) {
                System.out.println(find(personA, parent) == find(personB, parent) ? "We are a team" : "We are not a team");
            }
            // 如果指令为其他值，则输出 "da pian zi"
            else {
                System.out.println("da pian zi");
            }
        }
        sc.close();
    }

    // 查找节点，用于判断两个人是否在同一个团队
    public static int find(int x, int[] parent) {
        if (parent[x] != x) {
            return parent[x] = find(parent[x], parent);
        }
        return x;
    }
}
