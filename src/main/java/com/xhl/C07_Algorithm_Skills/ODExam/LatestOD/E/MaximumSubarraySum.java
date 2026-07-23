package com.xhl.C07_Algorithm_Skills.ODExam.LatestOD.E;

import java.util.Scanner;

/**
 * @Author: xhl
 * @Date: 2026-07-23 23:54
 * @Description: Maximum Subarray Sum  数组连续子数组的最大和
 */
public class MaximumSubarraySum {

    static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for(int i = 0;i<n;i++){
            arr[i] = sc.nextInt();
        }
        int pre = 0, maxAns = arr[0];
        for(int x : arr){
            pre = Math.max(pre + x, x);
            maxAns = Math.max(maxAns, pre);
        }
        System.out.println(maxAns);
    }
}
