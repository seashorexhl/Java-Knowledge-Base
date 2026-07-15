package com.xhl.C07_Algorithm_Skills.SelfSummary.BaseAL.SlideWindow;

/**
 * @Author: xhl
 * @Date: 2026-06-20 14:08
 * @Description: 滑动窗口框架
 */
public class SlideWindowFrameWork {
    static void main() {
        String s = "Lxh is a good boy!";

    }
    /**
     *  滑动窗口 算法框架
     */
    public int problemSubstring(String s) {
        //外层循环扩展右边界，内层循环扩展左边界
        for (int l = 0, r = 0 ; r < s.length() ; r++) {
            //当前考虑的元素
            while (l <= r /*&& check()*/) {//区间[left,right]不符合题意
                //扩展左边界
            }
            //区间[left,right]符合题意，统计相关信息
        }
        return 0;
    }

}
