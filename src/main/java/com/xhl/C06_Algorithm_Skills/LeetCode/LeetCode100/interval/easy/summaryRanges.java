package com.xhl.C06_Algorithm_Skills.LeetCode.LeetCode100.interval.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: xhl
 * @Date: 2026-06-23 18:43
 * @Description: 228. 汇总区间
 * 给定一个  无重复元素 的 有序 整数数组 nums 。
 * 区间 [a,b] 是从 a 到 b（包含）的所有整数的集合。
 * 返回 恰好覆盖数组中所有数字 的 最小有序 区间范围列表 。也就是说，nums 的每个元素都恰好被
 * 某个区间范围所覆盖，并且不存在属于某个区间但不属于 nums 的数字 x 。
 * 列表中的每个区间范围 [a,b] 应该按如下格式输出：
 * "a->b" ，如果 a != b
 * "a" ，如果 a == b
 */
public class summaryRanges {

    static void main() {
        int[] nums = {0,1,2,4,5,7};
        summaryRanges sr = new summaryRanges();
        List<String> strings = sr.summaryRanges(nums);
        System.out.printf(Arrays.toString(strings.toArray()));
    }
    /*方法一：一次遍历*/
    public List<String> summaryRanges(int[] nums) {
        int n = nums.length;
        List<String> list = new ArrayList<String>();
        int i = 0;

        while(i<n){
            int low = i;
            i++;
            while(i<n && nums[i] == nums[i-1]+1){
                i++;
            }
            int high = i-1;
            StringBuffer temp = new StringBuffer(Integer.toString(nums[low]));
            if(low<high){
                temp.append("->");
                temp.append(Integer.toString(nums[high]));
            }
            list.add(temp.toString());
        }
        return list;
    }
}
