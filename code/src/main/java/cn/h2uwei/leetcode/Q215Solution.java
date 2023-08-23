package cn.h2uwei.leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 215. 数组中的第K个最大元素 【中等】
 * <pre>
 * 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
 *
 * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 *
 * 你必须设计并实现时间复杂度为 O(n) 的算法解决此问题。
 *
 *
 *
 * 示例 1:
 *
 * 输入: [3,2,1,5,6,4], k = 2
 * 输出: 5
 * 示例 2:
 *
 * 输入: [3,2,3,1,2,4,5,5,6], k = 4
 * 输出: 4
 *
 *
 * 提示：
 *
 * 1 <= k <= nums.length <= 105
 * -104 <= nums[i] <= 104
 * </pre>
 *
 * @author zuweih
 * @date 2023/8/23 9:57
 */
public class Q215Solution {

    public static void main(String[] args) {
        Q215Solution solution = new Q215Solution();
        System.out.println(solution.findKthLargest(new int[]{3, 2, 1, 5, 6, 4}, 2));
    }


    public int findKthLargest(int[] nums, int k) {
        return -1;
    }

    /**
     * 优先级队列的效果还不如数组排序呢
     */
    public int findKthLargestUsePriorityQueue(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.reverseOrder());
        int i = 0;
        while (i < nums.length) {
            queue.add(nums[i++]);
        }
        if (k == 1) {
            return queue.peek();
        }
        i = 0;
        int r = -1;
        while (i != k && (i++) < nums.length) {
            r = queue.remove();
        }
        return r;
    }

    /**
     * 这个解法太简单了，复杂度很高
     */
    public int findKthLargestUseArraysSort(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k];
    }

}
