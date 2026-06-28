package com.xhl.Career_Growth.SelfSummary.BaseDS.BackTrackLifeStyle;

import java.util.LinkedList;
import java.util.List;

/**
 * @Author: xhl
 * @Date: 2026-06-07 16:50
 * @Description: 求一个数组的全排列 使用 回朔的方法
 */
public class permuteTree {

    //使用 List 记录所有全排列
    List<List<Integer>> res = new LinkedList<>();
    LinkedList<Integer> track = new LinkedList<>();

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5};
        permuteTree pt = new permuteTree();
        List<List<Integer>> permute = pt.permute(nums);
        System.out.println("正常打印：");
        System.out.println(permute);
        // 换行打印
        System.out.println(" 换行打印！");
        for (List<Integer> innerList : permute) {
            System.out.println(innerList + " ");
        }
    }

    /*主函数 输入一组 不重复的数字，求它们的全排列*/
    List<List<Integer>> permute(int[] nums) {
        backtracks(nums);
        return res;
    }

    // 回朔 算法框架  遍历 打印 全排列
    public void backtracks(int[] nums) {

        if(track.size() == nums.length){
            //穷举完 一个全排列
            res.add(new LinkedList<>(track));
            return;
        }

        for(int i = 0; i < nums.length; i++){
            //前序遍历位置 做选择
            if(track.contains(nums[i])){
                continue;
            }
            // 前序遍历 位置 做选择
            track.add(nums[i]);
            backtracks(nums);
            // 后续遍历位置 取消选择
            track.removeLast();
        }
    }
}
