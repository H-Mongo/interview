package cn.h2uwei.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * leetcode第3题：【中等】
 * <pre>
 * 题目：给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
 * 示例 1:
 *
 *  输入: s = "abcabcbb"
 *  输出: 3
 *  解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 *  示例 2:
 *
 *  输入: s = "bbbbb"
 *  输出: 1
 *  解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 *  示例 3:
 *
 *  输入: s = "pwwkew"
 *  输出: 3
 *  解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串
 * </pre>
 *
 * @author h.mongo
 * @date 2023/8/1 10:37
 */
public class Q3Solution {

    public static void main(String[] args) {
        Q3Solution solution = new Q3Solution();
        System.out.println(solution.lengthOfLongestSubstring("abcabcbb")); // 3
        System.out.println(solution.lengthOfLongestSubstring("bbbbb")); // 1
        System.out.println(solution.lengthOfLongestSubstring("pwwkew")); // 3
        System.out.println(solution.lengthOfLongestSubstring("ajccjkml")); // 5
        System.out.println(solution.lengthOfLongestSubstring("abcdeckmlsd")); // 7
    }

    /**
     * 自我思考：这个最小字串问题需要标记出自己访问过那些，未访问过那些元素。后续的查找只需要对未访问的数据进行处理即可（回溯算法的思路）。
     * 将访问的区间分割成两段：已访问，未访问，并标记出当前访问的起始位置
     */
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> visit = new HashMap<>(s.length());
        int start = 0;
        int next = 0;
        int length = 0;
        while (start < s.length() && next < s.length()) {
            char c = s.charAt(next);
            Integer index = visit.get(c);
            // 1. 未访问过
            // 2. 访问过但是起始位置在此之后
            if (index == null || index < start) {
                visit.put(c, next);
                next++;
                length = Math.max(length, (next - start));
                continue;
            }
            start++;
        }
        return length;
    }

}
