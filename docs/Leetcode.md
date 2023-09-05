##### 面试所需要的 leetcode 技能
> 解题有很多套路和方式，算法很重要。可以用来解答一系列相同的问题，常见的解题算法有：双指针、动态规划、贪心算法、回溯算法等等。
> 同时还要使用到各种数据结构来协助我们进行解题，比如队列、hash表、链表、栈、堆等。

###### 无重复字符的最长子串
> 实例：
> 结果：
> 解释：

+ 滑动窗口
> 利用滑动窗口来移动，选取最长的字串长度，这是自己理解并写出来的题解。自己写的这个还是比较绕，其实就是一个窗口收缩的问题，
> 当出现重复的元素的时候，就需要考虑左边界的向右侧进行收缩的过程。当没有出现冲突的时候，右侧边界继续向右侧移动。

```java
public class Solution {

    public List<int[]> doSolution(int[] nums, int n) {
        Map<Character, Integer> visit = new HashMap<>(s.length());
        int left = 0;
        int right = 0;
        int length = 0;
        while (left < s.length() && right < s.length()) {
            char c = s.charAt(right);
            Integer index = visit.get(c);
            // 1. 未访问过
            // 2. 访问过但是起始位置在此之后
            // 当元素不存在，或者已存在的元素下标在左指针的左侧，那么这个时候是不需要关注的，只需要继续移动右指针就可以了
            if (index == null || index < left) {
                visit.put(c, right);
                right++;
                length = Math.max(length, (right - left));
                continue;
            }
            // 当出现了重复的元素，并且重复的元素在左指针的右侧，那么这个时候需要不停的移动左指针，直到左指针移动到了重复元素的右侧。
            left++;
        }
        return length;
    }
}
```

###### 最长回文字子串
> 实例：str = "babad"
> 结果：aba
> 解释：当前 bab，aba 两个都符合，返回最后匹配的结果即可。

+ 中心点扩展法
> 以当前元素为中心点向左、向右两个方向进行扩展，比较 “对称” 位置的元素是否相同！ 

```java
public class Solution {
    
    public String doSolution(String s) {
        if (s == null || s.length() < 1) {
            return s;
        }
        int start = 0;
        int end = 0;
        for (int i = 0; i < s.length(); i++) {
            // 以自己为中心开始扩展
            int len1 = expandFromCenterPoint(s, i, i);
            // 以自己相邻节点为中心进行扩展，针对相邻元素相同的case
            int len2 = expandFromCenterPoint(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                // (len - 1) / 2 ：表示向前推进找到起始位置，减 1 的目的是排除自己的位置
                start = i - (len - 1) / 2;
                // (len - 1) / 2 表示向后推进找到结束的位置
                end = i - (len) / 2;
                // 一个会减 1，一个不会。目的就是排除自身
            }
        }
        // substring 是一个前闭后开的截取方式
        return s.substring(start, end + 1);
    }

    private int expandFromCenterPoint(String s, int left, int right) {
        // 向前向后进行推送
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        // 返回字串的长度
        return right - left - 1;
    }
} 
```

###### 找到数组中两个数和为目标值的两个数对应的索引列表
> 实例：nums = [1,3,3,4], n = 4
> 结果：[[0，1],[0，2]]

+ hash 表的方式实现
> 将当前元素和索引加一个索引表（这里就是一个 map）中，当进行下一个元素遍历的时候，可以去索引表中找到它的差值。
> 当数组中存在多个相同的差值时，将它们的索引通过链表进行缓存。

```java
import java.util.HashMap;
import java.util.LinkedList;

public class Solution {
    
    public List<int[]> doSolution(int[] nums, int n) {
        List<int[]> ans = new LinkedList<>();
        if (nums == null || nums.length < 1) {
            return ans;
        }
        Map<Integer, List<Integer>> indexMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int t = n - nums[i];
            if (!indexMap.containsKey(t)) {
                indexMap.compute(nums[i], (k, v) -> {
                    if (v == null) {
                        v = new LinkedList<>();
                    }
                    v.add(i);
                    return v;
                });
                continue;
            }
            List<Integer> indexList = indexMap.get(nums[i]);
            for (int index :  indexList) {
                ans.add(new int[] {i, index});
            }
        }
        return ans;
    }
} 
```

###### 全排列
> 实例：[1,2]
> 结果：[[1,2],[2,1]]

+ 回溯算法
> 通过对每一种情况都进行尝试枚举的方式来获取到最终的结果。
> 官方题解中存在一个大佬的解答，代码相对于官方的代码多了很多，但是理解起来会更清晰。
> https://leetcode.cn/problems/permutations/solutions/9914/hui-su-suan-fa-python-dai-ma-java-dai-ma-by-liweiw/?envType=study-plan-v2&envId=top-interview-150

```java
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Solution {

    public List<List<Integer>> doSolution(int[] nums) {
        List<List<Integer>> ans = new LinkedList<>();
        List<Integer> output = new ArrayList<>();
        // 构造一个用于全排列的基准列表，后续的排列都通过对当前基准的元素移动交换完成
        for (int n : nums) {
            output.add(n);
        }
        int len = nums.length;
        backtrack(len, output, ans, 0);
        return ans;
    }

    public void backtrack(int n, List<Integer> output, List<List<Integer>> ans, int first) {
        // 指定起始位置对应一次有效排列完成，加入结果集中
        if (n == first) {
            ans.add(new ArrayList<>(output));
        }
        for (int i = first; i < n; i++) {
            // 动态维护数组，交换元素
            Collections.swap(output, i, first);
            // 继续递归完成下一个数交换处理
            backtrack(n, output, ans, first + 1);
            // 动态维护数组，撤销前 1 步的交换结果
            Collections.swap(output, i, first + 1);
        }
    }

}
```

###### 最长递增子序列
> 实例：nums = [10,9,2,5,3,7,101,18]
> 结果：4
> 解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。

+ 动态规划
> 起始可以得到一个结论，当前符合的递增子序列肯定包含前一个递增子序列，就是 i-1 属于 i 的子集，代码理解起来还真的不是那么简单，挺费劲。

```java
public class Solution {

    public int doSolution(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        // 因为动态规划，所以需要使用一个 dp 数组，数组的长度为元素中的个数
        int[] dp = new int[nums.length];
        // 递增序列的最小情况就是自身，所以就是 dp[0]=1
        dp[0] = 1;
        int ans = 1;
        for (int i = 1; i < nums.length; i++) {
            // 记录当前位置所能够构成的最长递增子序列的长度，初始值为 1 表示情况就只有自身的情况下。
            dp[i] = 1;
            // 当前 for 循环的目的是：从数组起始位置 0 开始，到当前位置结束这段区间内的最长递增子序列
            for (int j = 0; j < i; j++) {
                // 当 i 位置的元素比它前面的元素大的情况下，能够与 i 构成的递增子序列长度就加 1 了
                if (nums[i] > nums[j]) {
                    // 因为找的是全局最长递增子序列，所以需要和当前元素所组成的递增子序列进行比较
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }
}
```

###### 70. 爬楼梯
> 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
> 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
> 实例：n = 2
> 结果：2
> 解释：有两种方法可以爬到楼顶。1）1 阶 + 1阶；2）2 阶

+ 动态规划中的滚动数组
> 第 N 步的可选结果来自第 N-1 步。

```java
public class Solution {

    public int doSolution(int n) {
        // p 可以看作第 i - 2 次的结果;
        int p = 0;
        // q 可以看作第 i - 1 次的结果;
        int q = 0;
        // 可选择的方式初始值为 1，可以理解为当 n = 0 的情况下。
        // 第 i 次的结果，也就是整个过程执行完成后的最终保存的结果
        int r = 1;
        for (int i = 1; i <= n; i++) {
            q = p;
            p = r;
            r = p + q;
        }
        return r;
    }
}
```
###### 887. 鸡蛋掉落【困难】
> 实例：
> 结果：
> 解释：

+ 动态规划 + 二分查找
> 释义

```java
public class Solution {

    Map<Integer, Integer> memo = new HashMap<Integer, Integer>();
    
    public int doSolution(int k, int n) {
        return dp(k, n);
    }
    
    public int dp(int k, int n) {
        if (!memo.containsKey(n * 100 + k)) {
            int ans;
            if (n == 0) {
                ans = 0;
            } else if (k == 1) {
                ans = n;
            } else {
                int lo = 1, hi = n;
                while (lo + 1 < hi) {
                    int x = (lo + hi) / 2;
                    int t1 = dp(k - 1, x - 1);
                    int t2 = dp(k, n - x);

                    if (t1 < t2) {
                        lo = x;
                    } else if (t1 > t2) {
                        hi = x;
                    } else {
                        lo = hi = x;
                    }
                }

                ans = 1 + Math.min(Math.max(dp(k - 1, lo - 1), dp(k, n - lo)), Math.max(dp(k - 1, hi - 1), dp(k, n - hi)));
            }

            memo.put(n * 100 + k, ans);
        }

        return memo.get(n * 100 + k);
    }
}
```

###### 380. O(1) 时间插入、删除和获取随机元素
> 实例：
> 结果：
> 解释：

+ 哈希表 + 变长数组
> 利用哈希表的特性来快速判断元素是否存在，利用变长数组来存储元素，移除元素时使用数组尾部的元素进行替换，然后移除尾部元素即可

```java
class RandomizedSet {
    List<Integer> nums;
    Map<Integer, Integer> indices;
    Random random;

    public RandomizedSet() {
        nums = new ArrayList<Integer>();
        indices = new HashMap<Integer, Integer>();
        random = new Random();
    }

    public boolean insert(int val) {
        if (indices.containsKey(val)) {
            return false;
        }
        int index = nums.size();
        nums.add(val);
        indices.put(val, index);
        return true;
    }

    public boolean remove(int val) {
        if (!indices.containsKey(val)) {
            return false;
        }
        int index = indices.get(val);
        int last = nums.get(nums.size() - 1);
        nums.set(index, last);
        indices.put(last, index);
        nums.remove(nums.size() - 1);
        indices.remove(val);
        return true;
    }

    public int getRandom() {
        int randomIndex = random.nextInt(nums.size());
        return nums.get(randomIndex);
    }
}
```


###### 题目
> 实例：
> 结果：
> 解释：

+ 解法
> 释义

```java
public class Solution {

    public List<int[]> doSolution(int[] nums, int n) {
        //
    }
}
```
