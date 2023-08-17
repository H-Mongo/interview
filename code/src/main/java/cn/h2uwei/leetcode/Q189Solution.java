package cn.h2uwei.leetcode;

import java.util.Arrays;

/**
 * 189. 轮转数组 【中等】
 * <pre>
 *  定一个整数数组 nums，将数组中的元素向右轮转 k 个位置，其中 k 是非负数。
 *
 *
 *
 *  示例 1:
 *
 *  输入: nums = [1,2,3,4,5,6,7], k = 3
 *  输出: [5,6,7,1,2,3,4]
 *  解释:
 *  向右轮转 1 步: [7,1,2,3,4,5,6]
 *  向右轮转 2 步: [6,7,1,2,3,4,5]
 *  向右轮转 3 步: [5,6,7,1,2,3,4]
 *  示例 2:
 *
 *  输入：nums = [-1,-100,3,99], k = 2
 *  输出：[3,99,-1,-100]
 *  解释:
 *  向右轮转 1 步: [99,-1,-100,3]
 *  向右轮转 2 步: [3,99,-1,-100]
 *
 *
 *  提示：
 *
 *  1 <= nums.length <= 105
 *  -231 <= nums[i] <= 231 - 1
 *  0 <= k <= 105
 *
 *
 *  进阶：
 *
 *  尽可能想出更多的解决方案，至少有 三种 不同的方法可以解决这个问题。
 *  你可以使用空间复杂度为 O(1) 的 原地 算法解决这个问题吗？
 * </pre>
 *
 * @author zuweih
 * @date 2023/8/16 17:43
 */
public class Q189Solution {

    public static void main(String[] args) {
        Q189Solution solution = new Q189Solution();
        int[] arr = new int[]{1, 2};
        solution.rotate(arr, 5);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 官方题解代码，通过开启临时空间，自己有想过环形数组就是没实现出来。</br>
     * 思路：从当前位置 i 右移 n 位，类似于 hash 冲突的解决方式，再 hash 法。那么当前元素移动后位置就是：(i + n) % len
     */
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        int[] newArr = new int[n];
        for (int i = 0; i < n; ++i) {
            newArr[(i + k) % n] = nums[i];
        }
        System.arraycopy(newArr, 0, nums, 0, n);
    }

}
