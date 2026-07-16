package com.xhl.C07_Algorithm_Skills.SelfSummary.BaseDS.Array.hard;

/**
 * @Author: xhl
 * @Date: 2026-07-03 18:12
 * @Description: 135. 分发糖果
 *  最少需要的糖果数
 */
public class candy {
    static void main() {
        int[] ratings = {1,0,2};
        candy cd = new candy();
        int candy = cd.candy(ratings);
        System.out.println("计算并返回需要准备的 最少糖果数目: " + candy);
    }
    /**
     * 方法一：两次遍历
     * */
    // 两次遍历：一次从左到右保证右边评分高的孩子比左边糖果多，一次从右到左保证左边
    // 评分高的孩子比右边糖果多，最后取两者的最大值。
    public int candy(int[] ratings) {
        int n = ratings.length;
        // 1. 第一次遍历（从左到右）：
        // 创建一个数组，记录只考虑“左侧邻居”时，每个孩子至少需要的糖果数

        int[] left = new int[n];
        for (int i = 0; i < n; i++) {
            // 如果当前孩子的评分比左侧邻居高，那么他的糖果数 = 左侧邻居糖果数 + 1

            if (i > 0 && ratings[i] > ratings[i - 1]) {
                left[i] = left[i - 1] + 1;
            } else {
                // 否则（评分不高或相等），当前孩子至少分配 1 颗糖果
                left[i] = 1;
            }
        }
        int right = 0, ret = 0;
        // 2. 第二次遍历（从右到左）：
        // 动态计算只考虑“右侧邻居”时，每个孩子至少需要的糖果数，并直接累加最终结果
        for (int i = n - 1; i >= 0; i--) {
            if (i < n - 1 && ratings[i] > ratings[i + 1]) {
                // 如果当前孩子的评分比右侧邻居高，那么他的糖果数 = 右侧邻居糖果数 + 1
                right++;
            } else {
                // 否则，当前孩子至少分配 1 颗糖果
                right = 1;
            }
            //3. 取两次遍历结果的最大值：
            //为了同时满足“比左侧高”和“比右侧高”的规则，当前孩子的最终糖果数
            //必须是 left[i] 和 right 中的较大者，然后累加到总结果中
            ret += Math.max(left[i], right);
        }
        return ret;
    }
    /**
     * 方法二：常数空间遍历
     * */
    // 单次遍历法 (One-Pass / 贪心与数学规律)
    public int candy1(int[] ratings) {
        int n = ratings.length;
        int ret = 1;
        int inc = 1, dec = 0, pre = 1;
        for (int i = 1; i < n; i++) {
            if (ratings[i] >= ratings[i - 1]) {
                dec = 0;
                pre = ratings[i] == ratings[i - 1] ? 1 : pre + 1;
                ret += pre;
                inc = pre;
            } else {
                dec++;
                if (dec == inc) {
                    dec++;
                }
                ret += dec;
                pre = 1;
            }
        }
        return ret;
    }

}
