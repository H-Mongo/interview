package cn.h2uwei.leetcode;

import java.util.Arrays;

/**
 * 88. 合并两个有序数组 【简单】
 *
 * <pre>
 * 给你两个按 非递减顺序 排列的整数数组 nums1 和 nums2，另有两个整数 m 和 n ，分别表示 nums1 和 nums2 中的元素数目。
 *
 * 请你 合并 nums2 到 nums1 中，使合并后的数组同样按 非递减顺序 排列。
 *
 * 注意：最终，合并后数组不应由函数返回，而是存储在数组 nums1 中。为了应对这种情况，nums1 的初始长度为 m + n，其中前 m 个元素表示应合并的元素，后 n 个元素为 0 ，应忽略。nums2 的长度为 n 。
 *
 * 示例 1：
 *  输入：nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
 *  输出：[1,2,2,3,5,6]
 *  解释：需要合并 [1,2,3] 和 [2,5,6] 。
 *  合并结果是 [1,2,2,3,5,6] ，其中斜体加粗标注的为 nums1 中的元素。
 *
 * 示例 2：
 *  输入：nums1 = [1], m = 1, nums2 = [], n = 0
 *  输出：[1]
 *  解释：需要合并 [1] 和 [] 。
 *  合并结果是 [1] 。
 *
 * 示例 3：
 *  输入：nums1 = [0], m = 0, nums2 = [1], n = 1
 *  输出：[1]
 *  解释：需要合并的数组是 [] 和 [1] 。
 *  合并结果是 [1] 。
 *  注意，因为 m = 0 ，所以 nums1 中没有元素。nums1 中仅存的 0 仅仅是为了确保合并结果可以顺利存放到 nums1 中。
 *
 * 提示：
 *
 *   nums1.length == m + n
 *   nums2.length == n
 *   0 <= m, n <= 200
 *   1 <= m + n <= 200
 *   -109 <= nums1[i], nums2[j] <= 109
 *
 *
 * 进阶：你可以设计实现一个时间复杂度为 O(m + n) 的算法解决此问题吗？
 *
 * </pre>
 *
 * @author h.mongo
 * @date 2023/8/8 18:10
 */
public class Q88Solution {

    public static void main(String[] args) {
        Q88Solution solution = new Q88Solution();
//        int[] nums1 = new int[]{1, 2, 3, 0, 0, 0};
//        solution.merge(nums1, 3, new int[]{2, 5, 6}, 3);
//        System.out.println(Arrays.toString(nums1));

//        int[] nums1 = new int[]{1};
//        solution.merge(nums1, 1, new int[]{}, 0);
//        System.out.println(Arrays.toString(nums1));

//        int[] nums1 = new int[]{0};
//        solution.merge(nums1, 0, new int[]{1}, 1);
//        System.out.println(Arrays.toString(nums1));

        int[] nums1 = new int[]{-2, -1, 0, 8, 0, 0};
        solution.merge(nums1, 4, new int[]{1, 5}, 2);
        System.out.println(Arrays.toString(nums1));

    }

    /**
     * 此时此刻的自己就是个废柴，有想到使用双指针就是没有想到如何使用。
     * 双想到了双层while循环为什么不动手呀！！！笨的要死！！！</br>
     * 官方的题解合并后排序我真的惊呆了，为啥自己一定要陷入固定思维呀？？？？</br>
     * 题解内容是通过理解了逆向双指针加空闲空间利用写出来的答案，但是下次肯定不能很快写出来
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = (m + n) - 1;
        m--;
        n--;
        int num;
        while (m >= 0 || n >= 0) {
            if (m == -1) { // m结束了
               num =  nums2[n--];
            } else if (n == -1) { // n 结束了
                num = nums1[m--];
            } else if (nums1[m] > nums2[n]) { // 移动m
                num = nums1[m--];
            } else { // 移动n
                num = nums2[n--];
            }
            nums1[i--] = num;
        }
    }

    /**
     * 合并后排序：
     */
    public void mergeThenSort(int[] nums1, int m, int[] nums2, int n) {
        for (; m < (m + n); m++) {
            nums1[m] = nums2[m - n];
        }
        Arrays.sort(nums1);
    }

}
