package com.xhl.Career_Growth.SelfSummary.BaseAL.Cycle;

/**
 * @Author: xhl
 * @Date: 2026-06-19 05:06
 * @Description: 统计好三元组
 * 给你一个整数数组 arr ，以及 a、b 、c 三个整数。请你统计其中好三元组的数量。
 * 如果三元组 (arr[i], arr[j], arr[k]) 满足下列全部条件，则认为它是一个 好三元组 。
 */
public class countGoodTriplets {
    static void main() {
        int[] arr = {3,0,1,1,9,7};
        int a = 7,b = 2,c = 3;
        countGoodTriplets cgt =  new countGoodTriplets();
        int i = cgt.countGoodTriplets(arr, a, b, c);
        System.out.printf("好的 三元组的数量 :" + i );
    }
    /*方法一：枚举*/
    public int countGoodTriplets(int[] arr, int a, int b, int c) {
        int n = arr.length;
        int count = 0;
        for(int i = 0;i<n;i++){
            for(int j =i+1;j<n;j++){
                for(int k=j+1;k<n;k++){
                    if(Math.abs(arr[i]-arr[j])<=a
                            && Math.abs(arr[j] - arr[k]) <= b
                            && Math.abs(arr[i] - arr[k])<= c){
                        count++;
                    }
                }
            }
        }
        return count;
    }
    /*方法二：枚举优化*/
    public int countGoodTriplets1(int[] arr, int a, int b, int c) {
        int ans = 0, n = arr.length;
        int[] sum = new int[1001];
        for (int j = 0; j < n; ++j) {
            for (int k = j + 1 ; k < n; ++k) {
                if (Math.abs(arr[j] - arr[k]) <= b) {
                    int lj = arr[j] - a, rj = arr[j] + a;
                    int lk = arr[k] - c, rk = arr[k] + c;
                    int l = Math.max(0, Math.max(lj, lk)), r = Math.min(1000, Math.min(rj, rk));
                    if (l <= r) {
                        if (l == 0) {
                            ans += sum[r];
                        }
                        else {
                            ans += sum[r] - sum[l - 1];
                        }
                    }
                }
            }
            for (int k = arr[j]; k <= 1000; ++k) {
                ++sum[k];
            }
        }
        return ans;
    }

}
