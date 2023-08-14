package cn.h2uwei.leetcode;

import java.util.*;

/**
 * 15. 三数之和 【中等】
 * <pre>
 *  给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j != k ，同时还满足 nums[i] + nums[j] + nums[k] == 0 。请
 *
 * 你返回所有和为 0 且不重复的三元组。
 *
 * 注意：答案中不可以包含重复的三元组。
 *
 *
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [-1,0,1,2,-1,-4]
 * 输出：[[-1,-1,2],[-1,0,1]]
 * 解释：
 * nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0 。
 * nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0 。
 * nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0 。
 * 不同的三元组是 [-1,0,1] 和 [-1,-1,2] 。
 * 注意，输出的顺序和三元组的顺序并不重要。
 * 示例 2：
 *
 * 输入：nums = [0,1,1]
 * 输出：[]
 * 解释：唯一可能的三元组和不为 0 。
 * 示例 3：
 *
 * 输入：nums = [0,0,0]
 * 输出：[[0,0,0]]
 * 解释：唯一可能的三元组和为 0 。
 *
 *
 * 提示：
 *
 * 3 <= nums.length <= 3000
 * -105 <= nums[i] <= 105
 * </pre>
 *
 * @author h.mongo
 * @date 2023/8/10 23:54
 */
public class Q15Solution {
    public static void main(String[] args) {
        Q15Solution solution = new Q15Solution();
        System.out.println(solution.threeSum(new int[]{-1, 0, 1, 2, -1, -4})); // [[-1,-1,2],[-1,0,1]]
    }

    /**
     * 通过阅读官方题解，得到的解决的方案就是排序后使用双指针，以下代码是官方题解答案！
     * 针对数组相关的题目，很多都需要进行排序后处理。只要保证数组中数据的顺序性，就可以采用双指针进行解题了！
     */
    public List<List<Integer>> threeSum(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        // 枚举 a
        for (int first = 0; first < n; ++first) {
            // 需要和上一次枚举的数不相同
            if (first > 0 && nums[first] == nums[first - 1]) {
                continue;
            }
            // c 对应的指针初始指向数组的最右端
            int third = n - 1;
            int target = -nums[first];
            // 枚举 b
            for (int second = first + 1; second < n; ++second) {
                // 需要和上一次枚举的数不相同
                if (second > first + 1 && nums[second] == nums[second - 1]) {
                    continue;
                }
                // 需要保证 b 的指针在 c 的指针的左侧
                while (second < third && nums[second] + nums[third] > target) {
                    --third;
                }
                // 如果指针重合，随着 b 后续的增加
                // 就不会有满足 a+b+c=0 并且 b<c 的 c 了，可以退出循环
                if (second == third) {
                    break;
                }
                if (nums[second] + nums[third] == target) {
                    List<Integer> list = new ArrayList<Integer>();
                    list.add(nums[first]);
                    list.add(nums[second]);
                    list.add(nums[third]);
                    ans.add(list);
                }
            }
        }
        return ans;
    }

    /**
     * 通过查看题解，将三数之和降级为两数之和进行求解，但是没能解决重复元组的问题！
     */
    public List<List<Integer>> threeSumOld(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums.length == 3) {
            if ((nums[0] + nums[1] + nums[2]) == 0) {
                result.add(createList(nums[0], nums[1], nums[2]));
            }
            return result;
        }
        for (int i = 0; i < nums.length; i++) {
            List<List<Integer>> twoSums = twoSum(nums, i, -nums[i]);
            if (!twoSums.isEmpty() && !result.containsAll(twoSums)) {
                result.addAll(twoSums);
            }
        }
        return result;
    }

    public List<List<Integer>> twoSum(int[] nums, int skipIndex, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (i == skipIndex) {
                continue;
            }
            int y = target - nums[i];
            Integer yIdx = map.get(y);
            // 没找到
            if (yIdx == null) {
                map.put(nums[i], i);
            } else if (i != yIdx) {
                list.add(createList(nums[skipIndex], nums[i], y));
            }
        }
        return list;
    }

    private static List<Integer> createList(int x, int y, int z) {
        List<Integer> list = new ArrayList<>();
        list.add(x);
        list.add(y);
        list.add(z);
        return list;
    }

}
