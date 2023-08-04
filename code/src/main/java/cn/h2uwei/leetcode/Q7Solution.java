package cn.h2uwei.leetcode;

/**
 * 7. 整数反转【中等】
 * <pre>
 * 给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。
 *
 * 如果反转后整数超过 32 位的有符号整数的范围 [−2^31,  2^(31 − 1)] ，就返回 0。
 *
 * 假设环境不允许存储 64 位整数（有符号或无符号）。
 *
 *
 * 示例 1：
 *
 * 输入：x = 123
 * 输出：321
 * 示例 2：
 *
 * 输入：x = -123
 * 输出：-321
 * 示例 3：
 *
 * 输入：x = 120
 * 输出：21
 * 示例 4：
 *
 * 输入：x = 0
 * 输出：0
 *
 *
 * 提示：
 *
 * -231 <= x <= 231 - 1
 * </pre>
 *
 * @author h.mongo
 * @date 2023/8/3 17:32
 */
public class Q7Solution {

    public static void main(String[] args) {
//        useLong2Solution();
    }


    private static void useLong2Solution() {
        Q7Solution solution = new Q7Solution();
        System.out.println(solution.reverse(123)); // 321
        System.out.println(solution.reverse(-123)); // -321
        System.out.println(solution.reverse(120)); // 21
        System.out.println(solution.reverse(0)); // 0
        System.out.println(solution.reverse(Integer.MAX_VALUE)); // 0
        System.out.println(solution.reverse(Integer.MIN_VALUE)); // 0
        // 2145447412
        System.out.println(solution.reverse(2145447412));
    }

    /**
     * 题解：通过与10取模获取最个位，将获得的个位数在与10相乘，原数与10进行除法运算得到下一次进行计算的原始数，最后反转后的数据进行数据区间检查即可
     * <h1>题虽然解了，但是不符合要求，因为不支持64位存储</h1>
     * 是否可以考虑通过两个32位的数据进行存储，高 8 位和低 8 位分开存储即可！请注意：高 8 位中存在 1 位符号位！
     */
    public int reverse(int x) {
        if (x == 0) {
            return 0;
        }
        // 无需判断当前数是不是负数，除法运算会自动携带符号位
        long t = 0L;
        // 取模运算
        while (x != 0) {
            // 获取个位数
            int i = x % 10;
            x /= 10;
            t = (t * 10 + i);
        }
        if (t < Integer.MIN_VALUE || t > Integer.MAX_VALUE) {
            return 0;
        }
        return (int) t;
    }

    /**
     * 官方题解：通过对成立的条件进行不等式推导，得到不成立的判断条件
     */
    public int reverseOfLeetcode(int x) {
        if (x == 0) {
            return 0;
        }
        // 无需判断当前数是不是负数，除法运算会自动携带符号位
        int t = 0;
        // 取模运算
        while (x != 0) {
            // leetcode 题解，通过不等式推到得到
            if (t < Integer.MIN_VALUE / 10 || t > Integer.MAX_VALUE / 10) {
                return 0;
            }
            // 获取个位数
            int i = x % 10;
            x /= 10;
            t = (t * 10 + i);
        }
        return t;
    }

}
