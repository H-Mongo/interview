package cn.h2uwei.leetcode;

import cn.h2uwei.util.LeetcodeUtil;

/**
 * 11. 盛最多水的容器【中等】
 * <pre>
 * 给定一个长度为 n 的整数数组 height 。有 n 条垂线，第 i 条线的两个端点是 (i, 0) 和 (i, height[i]) 。
 * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 * 返回容器可以储存的最大水量。
 * 说明：你不能倾斜容器。
 *
 * 示例 1：<a href="https://leetcode.cn/problems/container-with-most-water/">原题图例</a>
 *  输入：[1,8,6,2,5,4,8,3,7]
 *  输出：49
 *  解释：图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。
 *
 * 示例 2：
 *  输入：height = [1,1]
 *  输出：1
 *
 * 提示：
 *  n == height.length
 *  2 <= n <= 105
 *  0 <= height[i] <= 104
 * </pre>
 *
 * @author zuweih
 * @date 2023/8/4 13:13
 */
public class Q11Solution {

    public static void main(String[] args) {
        // leetcode 执行超时
//        force();
        System.out.println("--------");
        doublePoint();
    }

    public static void doublePoint() {
        Q11Solution solution = new Q11Solution();
//        LeetcodeUtil.console("[1, 8, 6, 2, 5, 4, 8, 3, 7]", solution.maxArea(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}));
//        LeetcodeUtil.console("[1, 1]", solution.maxArea(new int[]{1, 1}));
        LeetcodeUtil.console("[1,8,100,2,100,4,8,3,7]",
                solution.maxArea(new int[]{1, 8, 100, 2, 100, 4, 8, 3, 7}));
    }

    private static void force() {
        Q11Solution solution = new Q11Solution();
        System.out.println(solution.maxAreaForce(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}));
        System.out.println(solution.maxAreaForce(new int[]{1, 1}));
        System.out.println(solution.maxAreaForce(new int[]{2, 3, 4, 5, 18, 17, 6}));
    }

    /**
     * 采用暴力破解方式来解决，两层遍历，这种方式直接超时！！！！
     */
    public int maxAreaForce(int[] height) {
        if (height.length == 2) {
            return Math.min(height[0], height[1]);
        }
        int result = 0;
        for (int i = 0; i < height.length - 1; i++) {
            for (int j = (i + 1); j < height.length; j++) {
                int t = ((j - i) * Math.min(height[i], height[j]));
                result = Math.max(result, t);
            }
        }
        return result;
    }

    /**
     * 很遗憾没有解出来!!!!
     * 思考过程：通过查看题目的提示说是采用双指针，感觉恍然大大悟，这种题就是滑动窗口性质的。关键词：双指针，滑动窗口
     * <h1>核心（没用意义的核心）：通过左右指针叫交叉计算所覆盖的所有区域，左右各取最大面积，并获取最终面积</h1>
     * <p>
     * 看了leetcode讨论区：才明白了两个指针移动过程中，底在减小，此时要求面积最大，只能让高最大化，一下是官方给出的题解
     */
    public int maxArea(int[] height) {
        int i = 0, j = height.length - 1, res = 0;
        while (i < j) {
            res = height[i] < height[j] ?
                    Math.max(res, (j - i) * height[i++]) :
                    Math.max(res, (j - i) * height[j--]);
        }
        return res;
    }
}
