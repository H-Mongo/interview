package cn.h2uwei.leetcode;

/**
 * 80. 删除有序数组中的重复项 II 【中等】
 *
 * <pre>
 * 给你一个有序数组 nums ，请你 原地 删除重复出现的元素，使得出现次数超过两次的元素只出现两次 ，返回删除后数组的新长度。
 *
 * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
 *
 *
 *
 * 说明：
 *
 * 为什么返回数值是整数，但输出的答案是数组呢？
 *
 * 请注意，输入数组是以「引用」方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。
 *
 * 你可以想象内部操作如下:
 *
 * // nums 是以“引用”方式传递的。也就是说，不对实参做任何拷贝
 * int len = removeDuplicates(nums);
 *
 * // 在函数里修改输入数组对于调用者是可见的。
 * // 根据你的函数返回的长度, 它会打印出数组中 该长度范围内 的所有元素。
 * for (int i = 0; i < len; i++) {
 *     print(nums[i]);
 * }
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,1,1,2,2,3]
 * 输出：5, nums = [1,1,2,2,3]
 * 解释：函数应返回新长度 length = 5, 并且原数组的前五个元素被修改为 1, 1, 2, 2, 3 。 不需要考虑数组中超出新长度后面的元素。
 * 示例 2：
 *
 * 输入：nums = [0,0,1,1,1,1,2,3,3]
 * 输出：7, nums = [0,0,1,1,2,3,3]
 * 解释：函数应返回新长度 length = 7, 并且原数组的前五个元素被修改为 0, 0, 1, 1, 2, 3, 3 。 不需要考虑数组中超出新长度后面的元素。
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 3 * 104
 * -104 <= nums[i] <= 104
 * nums 已按升序排列
 * </pre>
 *
 * @author zuweih
 * @date 2023/8/9 18:22
 */
public class Q80Solution {

    public static void main(String[] args) {
        Q80Solution solution = new Q80Solution();
        // 1,1,2,2,3
        // 1,1,2,1,3
        int[] arr = {1,1,1,1,2,2,3};
        int len = solution.removeDuplicates(arr);
        printResult(arr, len);
    }

    public static void printResult(int[] arr, int len) {
        for (int i = 0; i < len; i++) {
            System.out.print(arr[i] + ((i == len - 1) ? "" : ", "));
        }
        System.out.println();
    }

    /**
     * <pre>
     *  思路：依然通过快慢指针的方式进行交换，在遇见相同元素的时候记录当前的元素重估出现的个数，
     *  当个数小于被允许的重复数量时慢指针移动，否则满指针保持不变！当然这个方案失败了！！！
     *
     *  官方题解：
     *      通过阅读题解真的是恍然大悟，自己的思维能力真的太差了，太固定了！！！！，
     *      与 26 题的解法基本一致，采用 ”快慢指针“可解，慢指针对应的值就是去重后的数组长度。
     *      当条件 nums[slow - 2] == nums[fast] 满足的时候 fast 位置对应的元素已经出现了 2 次了，跳过当前元素。
     *      所以移动慢指针的条件就是 nums[slow -2] != nums[fast]。
     * </pre>
     *
     *
     *
     */
    public int removeDuplicates(int[] nums) {
        int n = nums.length;
        if (n <= 2) {
            return n;
        }
        int slow = 2;
        int fast = 2;
        while (fast < n) {
            if (nums[slow - 2] != nums[fast]) {
                nums[slow] = nums[fast];
                ++slow;
            }
            ++fast;
        }
        return slow;
    }

}