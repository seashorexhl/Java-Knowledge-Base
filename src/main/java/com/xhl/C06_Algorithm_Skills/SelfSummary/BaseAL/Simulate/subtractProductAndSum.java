package com.xhl.C06_Algorithm_Skills.SelfSummary.BaseAL.Simulate;

/**
 * @Author: xhl
 * @Date: 2026-06-20 10:13
 * @Description: 整数的各位积和之差
 * 给你一个整数 n，请你帮忙计算并返回该整数「各位数字之积」与「各位数字之和」的差。
 */
public class subtractProductAndSum {
    static void main() {
        int n = 234;
        subtractProductAndSum spas = new subtractProductAndSum();
        System.out.println(spas.subtractProductSum(n));
    }
    //    方法一：模拟
    public int subtractProductSum(int n) {
        int m = 1, s = 0;
        while (n != 0) {
            int x = n % 10;
            n /= 10;
            m *= x;
            s += x;
        }
        return m - s;
    }
    //
    public int subtractProductAndSum(int n) {
        int res =0;
        int ji = 1;
        int sum = 0;
        if(n==0){
            return 0;
        }
        while(n>0){
            sum +=n%10;
            ji *= n%10;
            n = n/10;
        }
        res =  ji - sum;
        return res;
    }
}
