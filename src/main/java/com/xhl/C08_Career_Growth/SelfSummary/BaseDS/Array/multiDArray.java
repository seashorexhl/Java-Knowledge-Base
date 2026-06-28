package com.xhl.Career_Growth.SelfSummary.BaseDS.Array;

import java.util.Arrays;

/**
 * @Author: xhl
 * @Date: 2026-06-12 15:40
 * @Description: 多维数组 面试专题
 */
public class multiDArray {
    public static void main(String[] args) {
        int[][] arr = {{1,2,3},{4,5,6},{7,8,9}};

        /**
         *  多维数组的遍历
         * */
        for (int[] ar:arr){
            System.out.println(Arrays.toString(ar));
        }
    }

}
