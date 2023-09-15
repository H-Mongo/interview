package cn.h2uwei.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;
import java.util.concurrent.*;

/**
 * @author zuweih
 * @date 2023/8/26 11:09
 */
public class Main {

    public static void main(String[] args) throws Exception {
//        isValid("{}{}");
        System.out.println(majorityElement(new int[]{3, 3, 4}));
    }

    /**
     * 1.用代码的方式模拟下面的过程
     * 10个不同的烧水壶，用最短的时间找到能够使得500ML水烧开的水壶，前3名。
     */
    public static void test10Shuihu() throws Exception {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 10,
                60, TimeUnit.MINUTES, new ArrayBlockingQueue<>(10));
        List<Callable<Long>> callables = new ArrayList<>(10);
        List<Long> ansList = new CopyOnWriteArrayList();
        for (int i = 0; i < 10; i++) {
            callables.add(() -> {
                long start = System.currentTimeMillis();
                new Shuihu(500).shaoshui();
                Thread.sleep(new Random().nextInt(1000));
                ansList.add(System.currentTimeMillis() - start);
                return 1L;
            });
        }
        List<Future<Long>> futures = executor.invokeAll(callables);
        long[] ansArr = new long[10];
//       for (int i = 0; i < futures.size(); i++) {
//           ansArr[i] = futures.get(i).get();
//       }
//       Arrays.sort(ansArr);
        System.out.println(Arrays.toString(ansArr));
    }

    static class Shuihu {
        public Shuihu(int size) {
            this.size = size;
        }

        private final int size;

        public void shaoshui() {
            // 烧水的动作
        }
    }

    public int removeElement(int[] nums, int val) {
        int l = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[l++] = nums[i];
            }
        }
        return l;
    }

    public static boolean isValid(String s) {
        char[] array = s.toCharArray();
        Deque<Character> stack = new LinkedList<>();
        Map<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put(']', '[');
        map.put('}', '{');
        int i = 0;
        while (i < array.length) {
            char c = array[i++];
            if (stack.isEmpty()) {
                stack.push(c);
                continue;
            }
            if (map.get(c).equals(stack.peek())) {
                stack.pop();
            } else {
                stack.push(c);
            }
        }
        return stack.isEmpty();
    }

    public List<List<Integer>> permute(int[] nums) {

        List<List<Integer>> res = new ArrayList<>();
        List<Integer> output = new ArrayList<>(nums.length);
        for (int num : nums) {
            output.add(num);
        }
        backtrack(nums.length, output, res, 0);
        return res;
    }

    private void backtrack(int n, List<Integer> output, List<List<Integer>> res, int currIndex) {
        if (n == currIndex) {
            res.add(new ArrayList<>(output));
        }
        for (int i = currIndex; i < n; i++) {
            Collections.swap(output, currIndex, i);
            backtrack(n, output, res, currIndex + 1);
            Collections.swap(output, currIndex, i);
        }
    }

    // [3,3,4]
    public static int majorityElement(int[] nums) {
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int n : nums) {
            countMap.compute(n, (k, v) -> v == null ? 1 : v + 1);
        }
        int c = -1;
        int ans = -1;
        for (Map.Entry<Integer, Integer> e : countMap.entrySet()) {
            if (e.getValue() > c) {
                ans = e.getKey();
            }
        }
        return ans;
    }

    private String findMaxLengthSubStr(String s) {
        int start = 0;
        int end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandFromCenterPoint(s, i, i);
            int len2 = expandFromCenterPoint(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > (end - start)) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private int expandFromCenterPoint(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;
    }

    // 最长递增子序列
    public int lengthOfLIS(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int maxans = 1;
        for (int i = 1; i < nums.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxans = Math.max(maxans, dp[i]);
        }
        return maxans;
    }

    Map<Integer, Integer> memo = new HashMap<Integer, Integer>();

    public int superEggDrop(int k, int n) {
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


    int quickselect(int[] nums, int l, int r, int k) {
        if (l == r) return nums[k];
        int x = nums[l];
        int i = l - 1;
        int j = r + 1;
        while (i < j) {
            do i++; while (nums[i] < x);
            do j--; while (nums[j] > x);
            if (i < j) {
                int tmp = nums[i];
                nums[i] = nums[j];
                nums[j] = tmp;
            }
        }
        if (k <= j) return quickselect(nums, l, j, k);
        else return quickselect(nums, j + 1, r, k);
    }

    public int findKthLargest(int[] _nums, int k) {
        int n = _nums.length;
        return quickselect(_nums, 0, n - 1, n - k);
    }

    private void execQuickSort() {
        int[] nums = {2, 3, 4, 5, 3};
        quickSort(nums, 0, nums.length - 1);
    }

    /**
     * 默写快排算法
     */

    public void quickSort(int[] nums, int left, int right) {
        // 需要选取一个基准点，然后调整基准点的位置，左侧都小于基准点，右侧都大于等于基准点
        // 这里选取第一个元素为基准点
        int base = nums[0];
        while (left < right) {
//            if (nums[]) {
//                //
//            }
        }
    }


    /**
     * 数组奇数偶数分组
     */
    public void test(int[] nums) {
        int l = 0;
        int r = nums.length - 1;
        // 左侧是偶数，右侧是奇数
        while (l < r) {
            boolean left = nums[l] % 2 == 0;
            boolean right = nums[r] % 2 == 0;
            if (!left && right) { // 左奇右偶
                swap(nums, l, r);
                l++;
                r++;
            } else if (left && right) { // 左偶右偶
                l++;
            } else if (!left) { // 左奇右奇
                r++;
            } else { // 左偶右奇
                l++;
                r++;
            }
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int t = nums[j];
        nums[j] = nums[i];
        nums[i] = t;
    }


    public void findTopN(File file, Date start, Date end) {
        // weiboId,likeId,time
        TreeMap<Weibo, Weibo> treeMap = new TreeMap<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = null;
            while ((line = reader.readLine()) != null) {
                String[] split = line.split(",");
                if (treeMap.containsKey(split[0])) {
                    final Weibo weibo = treeMap.get(split[0]);
                    weibo.likeCount++;
                    Date date = new Date(split[2]);
                    if (weibo.time.before(date)) {
                        weibo.time = date;
                    }
                } else {
                    Weibo weibo = new Weibo();
                    weibo.weiboId =  Integer.parseInt(split[0]);
                    weibo.likeCount = Integer.parseInt(split[1]);
                    weibo.time = new Date(split[2]);
                    treeMap.put(weibo, weibo);
                }
            }
        } catch (Exception e) {
            //
        }
    }

    static class Weibo implements Comparator<Weibo> {
        private int weiboId;
        private int likeCount;
        private Date time;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Weibo weibo = (Weibo) o;
            return weiboId == weibo.weiboId;
        }

        @Override
        public int hashCode() {
            return Objects.hash(weiboId);
        }

        @Override
        public int compare(Weibo o1, Weibo o2) {
            if (o1.likeCount == o2.likeCount) {
                return 0;
            }
            return o1.likeCount > o2.likeCount ? 1 : -1;
        }
    }


}
