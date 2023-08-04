package cn.h2uwei.leetcode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 5. 最长回文子串【中等】
 * <pre>
 * 题目：
 *   给你一个字符串 s，找到 s 中最长的回文子串。
 *   如果字符串的反序与原始字符串相同，则该字符串称为回文字符串。
 * 示例：
 *   示例 1：
 *
 *    输入：s = "babad"
 *    输出："bab"
 *    解释："aba" 同样是符合题意的答案。
 *    示例 2：
 *
 *    输入：s = "cbbd"
 *    输出："bb"
 *
 *
 * 提示：
 *
 *   1 <= s.length <= 1000
 *   s 仅由数字和英文字母组成
 * </pre>
 *
 * @author h.mongo
 * @date 2023/8/2 10:48
 */
public class Q5Solution {

    public static void main(String[] args) {
        stdSolution();
    }

    private static void stdSolution() {
        mineSolution();
        System.out.println("-----------------");
        Q5Solution solution = new Q5Solution();
        System.out.println(solution.longestPalindromeWithExpandAroundCenter("babad")); // bab
        System.out.println(solution.longestPalindromeWithExpandAroundCenter("cbbd")); // bb
        System.out.println(solution.longestPalindromeWithExpandAroundCenter("a")); // a
        System.out.println(solution.longestPalindromeWithExpandAroundCenter("aaaa")); // aaaa
        System.out.println(solution.longestPalindromeWithExpandAroundCenter("kaaaavaak")); //
        System.out.println(solution.longestPalindromeWithExpandAroundCenter("aacabdkacaa")); // aca
    }

    private static void mineSolution() {
        Q5Solution solution = new Q5Solution();
        System.out.println(solution.longestPalindrome("babad")); // bab
        System.out.println(solution.longestPalindrome("cbbd")); // bb
        System.out.println(solution.longestPalindrome("a")); // a
        System.out.println(solution.longestPalindrome("aaaa")); // aaaa
        System.out.println(solution.longestPalindrome("kaaaavaak")); //
        System.out.println(solution.longestPalindrome("aacabdkacaa")); // aca
    }

    /**
     * 分析：标记同一个元素出现了多次, 在相同出现多次的数据区间进行会问见检查，这个算法的时间复杂度还是很高的！
     */
    public String longestPalindrome(String s) {
        Map<Character, List<Integer>> map = new HashMap<>(s.length());
        int i = 0;
        int f = 0;
        int t = 0;
        for (; i < s.length(); i++) {
            char c = s.charAt(i);
            List<Integer> list = map.get(c);
            if (list == null) {
                list = new LinkedList<>();
                list.add(i);
                map.put(c, list);
            } else {
                for (int idx : list) {
                    int j = i;
                    int k = idx;
                    boolean b = true;
                    while (k < j) {
                        if (s.charAt(k) != s.charAt(j)) {
                            b = false;
                            break;
                        }
                        k++;
                        j--;
                    }
                    int nl = (i - idx);
                    int ol = (t - f);
                    if (b && nl > ol) {
                        t = i;
                        f = idx;
                    }
                }
                list.add(i);
            }
        }
        return s.substring(f, t + 1);
    }


    /**
     * <pre>
     * 1）官方题解中心扩展的方案（时间复杂度 O(N²)）, 空间复杂度 O(1))：
     *      从p(i-1,j-1)扩展到p(i,j)如果不相等，那么后的扩展都不成立！核心就是回文串只有基数的长度和偶数的，每次从当前位置扩展，判断奇数。加1位置判断偶数
     * 2）例如：ababd
     * 3）分析：从 index 开始向后进行遍历的过程中，每次都会以 (index,index)， (index, index + 1) 为中心点开始扩展判断符合的回文串的大小
     * </pre>
     */
    public String longestPalindromeWithExpandAroundCenter(String s) {
        if (s == null || s.length() < 1) {
            return "";
        }
        int start = 0;
        int end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            // 取最长的回文串
            int len = Math.max(len1, len2);
            if (len > end - start) {
                // 找到起始位置
                start = i - (len - 1) / 2;
                // 找到终止位置
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    /**
     * 从中心点开始向外扩展
     *
     * @param s     需判断的字符串
     * @param left  中心点左端
     * @param right 中心点右端
     * @return 返回回文串的长度
     */
    public int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            --left;
            ++right;
        }
        // 因为中心元素在该区间内出现了 2 次，所以需要减 1
        return right - left - 1;
    }


}
