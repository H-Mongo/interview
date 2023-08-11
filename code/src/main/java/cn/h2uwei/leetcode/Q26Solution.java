package cn.h2uwei.leetcode;

/**
 * 26. 删除有序数组中的重复项 【简单】
 * <pre>
 * 给你一个 升序排列 的数组 nums ，请你 原地 删除重复出现的元素，使每个元素 只出现一次 ，返回删除后数组的新长度。元素的 相对顺序 应该保持 一致 。然后返回 nums 中唯一元素的个数。
 *
 * 考虑 nums 的唯一元素的数量为 k ，你需要做以下事情确保你的题解可以被通过：
 *
 * 更改数组 nums ，使 nums 的前 k 个元素包含唯一元素，并按照它们最初在 nums 中出现的顺序排列。nums 的其余元素与 nums 的大小不重要。
 * 返回 k 。
 * 判题标准:
 *
 * 系统会用下面的代码来测试你的题解:
 *
 * int[] nums = [...]; // 输入数组
 * int[] expectedNums = [...]; // 长度正确的期望答案
 *
 * int k = removeDuplicates(nums); // 调用
 *
 * assert k == expectedNums.length;
 * for (int i = 0; i < k; i++) {
 *     assert nums[i] == expectedNums[i];
 * }
 * 如果所有断言都通过，那么您的题解将被 通过。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,1,2]
 * 输出：2, nums = [1,2,_]
 * 解释：函数应该返回新的长度 2 ，并且原数组 nums 的前两个元素被修改为 1, 2 。不需要考虑数组中超出新长度后面的元素。
 * 示例 2：
 *
 * 输入：nums = [0,0,1,1,1,2,2,3,3,4]
 * 输出：5, nums = [0,1,2,3,4]
 * 解释：函数应该返回新的长度 5 ， 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4 。不需要考虑数组中超出新长度后面的元素。
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 3 * 104
 * -104 <= nums[i] <= 104
 * nums 已按 升序 排列
 * </pre>
 *
 * @author zuweih
 * @date 2023/8/9 18:26
 */
public class Q26Solution {

    public static void main(String[] args) {
        Q26Solution solution = new Q26Solution();
        int[] arr = {2, 2, 2, 2, 2, 2};
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
     * 通过建立一个新数组来缓存去重后的数据，最后将新数组中的数据写回原数组中
     */
    public int removeDuplicates(int[] nums) {
        int[] temp = new int[nums.length];
        temp[0] = nums[0];
        int p = 0;
        for (int i = 1; i < nums.length; i++) {
            if (temp[p] != nums[i]) {
                temp[++p] = nums[i];
            }
        }
        System.arraycopy(temp, 0, nums, 0, p + 1);
        return p + 1;
    }

    /**
     * <pre>
     * 思路：
     *      1）执行第一次全局遍历，寻找到第一次出现重复的位置 dupIndex 后退出循环；
     *      2）判断 dupIndex 是否存在，不存在直接返回当前数组的长度；
     *      3）从 dupIndex + 1 的位置开始寻找唯一的元素后给 nums[dupIndex]，此时 dupIndex++。
     * 元素重复/唯一判断：
     *      1）找首次重复的位置时，因为起始位置 i = 0，通过 nums[i] == nums[i+1] 判断元素相等，退出条件是 i < nums.length - 1;
     *      2）不重复的元素向前移动的过程中，因为起始位置 i = dupIndex + 1，我们 nums[i - 1] != nums[i] 找到不重复的元素，退出条件是 i < nums.length;
     * </pre>
     * 看了力扣题解后，发现其实我的想法就是快慢指针，只不过太繁琐了
     */
    public int removeDuplicatesUseDuplicateIndex(int[] nums) {
        int dupStart = -1;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == nums[i + 1]) {
                dupStart = i + 1;
                break;
            }
        }
        if (dupStart == -1) {
            return nums.length;
        }
        for (int i = dupStart + 1; i < nums.length; i++) {
            if (nums[i - 1] != nums[i]) {
                nums[dupStart] = nums[i];
                dupStart++;
            }
        }
        return dupStart;
    }

    /**
     * 力扣官方题解，通过快慢指针的方式，当快指针与慢指针对应的数据不相等的时候，用快指针的数据覆盖慢指针，慢指针向前移动，整个过程中，快指针始终向前移动
     */
    public int removeDuplicatesLeetcode(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        int fast = 1;
        int slow = 1;
        while (fast < n) {
            if (nums[fast] != nums[fast - 1]) {
                nums[slow] = nums[fast];
                ++slow;
            }
            ++fast;
        }
        return slow;
    }


}
