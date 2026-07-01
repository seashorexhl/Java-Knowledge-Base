package com.xhl.C06_Algorithm_Skills.SelfSummary.BaseAL.SlideWindow;
import java.util.HashMap;
import java.util.Map;
/**
 * @Author: xhl
 * @Date: 2026-06-10 02:39
 * @Description:  滑动窗口 通用模板 框架
 */
public class SlidingWindowTemplate {
    static void main() {

    }
    /*不定长滑动窗口模板*/
    public class VariableSlidingWindowTemplate {
        public int slidingWindow(String s) {
            // 1. 初始化状态变量（如哈希表记录频次、数值累加和等）
            Map<Character, Integer> window = new HashMap<>();
            int left = 0, right = 0;
            int result = 0; // 结果变量（求最长初始化为0，求最短可初始化为Integer.MAX_VALUE）

            // 2. 主循环：右指针主动扩张窗口
            while (right < s.length()) {
                char c = s.charAt(right);
                right++;

                // TODO: 进行窗口内数据的更新（例如：window.put(c, window.getOrDefault(c, 0) + 1)）

                // 3. 子循环：判断是否需要收缩左边界

                // 触发条件通常是：窗口不再满足题目限制（求最长时）或 已经满足条件尝试优化（求最短时）
                while (left==right/* 窗口需要收缩的条件 */) {
                    char d = s.charAt(left);
                    left++;

                    // TODO: 同步更新窗口内数据（例如：window.put(d, window.get(d) - 1)）
                }

                // 4. 更新最终结果
                // 求最大值通常在收缩后更新（确保当前窗口合法）；求最小值可在合法时立即更新
                result = Math.max(result, right - left);
            }
            return result;
        }
    }
    /*定长滑动窗口*/
    public class FixedSlidingWindowTemplate {
        public static int fixedLengthSlidingWindow(int[] nums, int k) {
            if (nums == null || nums.length < k) return 0;

            int windowSum = 0;
            int maxSum = 0;

            // 1. 构建初始窗口（前 k 个元素）
            for (int i = 0; i < k; i++) {
                windowSum += nums[i];
            }
            maxSum = windowSum;

            // 2. 滑动窗口遍历剩余元素
            for (int right = k; right < nums.length; right++) {
                int left = right - k; // 计算当前窗口的左边界

                // 移出左侧离开窗口的元素，加入右侧进入窗口的元素
                windowSum = windowSum - nums[left] + nums[right];

                // 3. 更新最优结果
                maxSum = Math.max(maxSum, windowSum);
            }
            return maxSum;
        }
    }
}
