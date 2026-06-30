package com.xhl.C08_Career_Growth.ODExam.E95.Answer.E63;

import java.util.Scanner;

/**
 * @Author: xhl
 * @Date: 2026-06-30 18:57
 * @Description: 02 TLV解码
 * 输入描述
 *  输入的第一行为一个字符串，表示待解码信元的Tag；
 *  输入的第二行为一个字符串，表示待解码的16进制码流，字节之间用空格分隔。
 * 输出描述
 *  输出一个字符串，表示待解码信元以16进制表示的Value。
 */
public class decodeTLV {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        // 输入待解码信元的Tag
        String tag =  sc.nextLine();
        // 输入16进制码流
        String line = sc.nextLine();
        // 将16进制码流按空格分割成字符串数组
        String[] hexArray =  line.split(" ");
        int index = 0;
        while (index < hexArray.length) {

            // 获取当前信元的长度
            int length = Integer.parseInt(hexArray[index + 2] + hexArray[index + 1], 16);

            // 如果当前信元的Tag与待解码信元的Tag相同
            if (hexArray[index].equals(tag)) {
                StringBuilder sb = new StringBuilder();

                // 将当前信元的Value拼接到StringBuilder中
                for (int i = index + 3; i < index + 3 + length; i++) {
                    sb.append(hexArray[i]).append(" ");
                }

                // 输出待解码信元的Value，转换为大写并去除首尾空格
                System.out.println(sb.toString().toUpperCase().trim());
                break;
            } else {
                // 跳过当前信元
                index += (2 + length + 1);
            }


        }

    }

}
