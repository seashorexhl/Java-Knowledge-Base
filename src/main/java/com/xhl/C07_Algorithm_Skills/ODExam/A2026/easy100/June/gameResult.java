package com.xhl.C07_Algorithm_Skills.ODExam.A2026.easy100.June;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: xhl
 * @Date: 2026-07-10 03:10
 * @Description: 链表数字游戏-100分
 */
public class gameResult {
    static void main(String[] args) {
        ListNode head = new ListNode(12);
        head.next = new ListNode(23);
        head.next.next = new ListNode(7);
        head.next.next.next = new ListNode(13);
        head.next.next.next.next = new ListNode(8);
        System.out.println(gameResult(head));
    }

    public static String gameResult(ListNode head) {
        int count = 0;
        ListNode curr = head;
        List<Integer> list = new ArrayList<>();

        while (curr != null) {
            count++;
            if(count>=10000){
                return "-1";
            }
            int n =curr.val;
            if(n<0 || n>1000000000){
                return "-1";
            }
            list.add(n);
            curr = curr.next;
        }

        List<Integer> reHead =  new ArrayList<>();
        List<Integer> keep =  new ArrayList<>();
        List<Integer> reLast =  new ArrayList<>();


        for (int num:list) {
            if (num % 3 == 0) {
                continue;
            }
            String numStr = String.valueOf(num);
            if (numStr.contains("3")) {
                reLast.add(num);
            } else if (numStr.contains("2")) {
                reHead.add(num);
            } else {
                keep.add(num);
            }
        }

            List<Integer> temp = new ArrayList<>();
            temp.addAll(reHead);
            temp.addAll(keep);
            temp.addAll(reLast);

            if(temp.isEmpty()){
                return " ";
            }
            StringBuffer sb = new StringBuffer();
            for(int i=0;i<temp.size();i++){
                if(i>0){
                    sb.append(" ");
                }
                sb.append(temp.get(i));
            }
            return sb.toString();
    }

    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int x) { val = x; }
        ListNode(int x, ListNode next) {
            val = x;
            this.next = next;
        }
    }
}
