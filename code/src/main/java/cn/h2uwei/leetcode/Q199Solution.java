package cn.h2uwei.leetcode;

import java.util.*;

/**
 * 199. 二叉树的右视图 【中等】
 * <pre>
 * 给定一个二叉树的 根节点 root，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
 *
 * 示例 1:
 *  输入: [1,2,3,null,5,null,4]
 *  输出: [1,3,4]
 *
 * 示例 2:
 *  输入: [1,null,3]
 *  输出: [1,3]
 *
 * 示例 3:
 *  输入: []
 *  输出: []
 *
 * 提示:
 *
 *  二叉树的节点个数的范围是 [0,100]
 *  -100 <= Node.val <= 100
 *
 * </pre>
 *
 * @author zuweih
 * @date 2023/8/7 17:16
 */
public class Q199Solution {

    public static void main(String[] args) {
        Q199Solution solution = new Q199Solution();
        // [1,2,3,null,5,null,4]  -> [1,3,4]
//        System.out.println(solution.rightSideView(new TreeNode(1, new TreeNode(2, null, new TreeNode(5)), new TreeNode(3, null, new TreeNode(4)))));
//        // [1,null,3] -> [1,3]
//        System.out.println(solution.rightSideView(new TreeNode(1, null, new TreeNode(3))));
        // [1,2,3,4] -> 1,3,4
//        System.out.println(solution.rightSideView(new TreeNode(1, new TreeNode(2, new TreeNode(4), null), new TreeNode(3))));
        // [1,2,3,4,null,5]
        System.out.println(solution.rightSideView(new TreeNode(1, new TreeNode(2, new TreeNode(4), null), new TreeNode(3, new TreeNode(5), null))));
    }

    /**
     * 关于这个题，在19年的秋招面试中遇见过，微盟一面的算法题，当时真的没思路也没做出来，面试官换了另外一道！</br>
     * 分析：右视图的二叉树其实就是从右侧进行投影即可，优先输出每个右侧的端点，当没有右侧端点的case中，
     * 需要输出其左侧端点，通过左右两个子树输出进行合并处理得到答案的子集。这个不能完全覆盖所有场景！！<br/>
     * 通过查看官方的解析，这个题的内涵就是通过DFS或者BFS进行解答！记录每一个深度下所输出的节点
     */
    public List<Integer> rightSideView(TreeNode root) {
        // 保存每个深度所对应的最右侧的节点
        Map<Integer, Integer> rightView = new HashMap<>();
        // 用于记录当前操作的节点，按照深度逐级压栈出栈操作
        Deque<TreeNode> nodeStack = new LinkedList<>();
        // 用于记录当前操作的深度，与当前处理的节点进行关联
        Deque<Integer> depthStack = new LinkedList<>();
        nodeStack.push(root);
        depthStack.push(0);
        while (!nodeStack.isEmpty()) {
            TreeNode node = nodeStack.pop();
            Integer depth = depthStack.pop();
            if (node == null) {
                continue;
            }
            rightView.computeIfAbsent(depth, k -> node.val);
            // 按照栈的特点先进后出，所以先加入左节点，然后右节点，能够保证每次都先遍历右子树
            nodeStack.push(node.left);
            nodeStack.push(node.right);
            depthStack.push(depth + 1);
            depthStack.push(depth + 1);
        }
        // 这种复制效率很慢！！！
        return new ArrayList<>(rightView.values());
    }

    /**
     * 一个二叉树的左视图，和右视图的处理基本类似，改变左右节点的入栈顺序也就控制了下一次优先遍历的是左子树还是右子树
     */
    public List<Integer> leftSideView(TreeNode root) {
        // 保存每个深度所对应的最右侧的节点
        Map<Integer, Integer> leftView = new HashMap<>();
        // 用于记录当前操作的节点，按照深度逐级压栈出栈操作
        Deque<TreeNode> nodeStack = new LinkedList<>();
        int maxDepth = -1;
        // 用于记录当前操作的深度，与当前处理的节点进行关联
        Deque<Integer> depthStack = new LinkedList<>();
        nodeStack.push(root);
        depthStack.push(0);
        while (!nodeStack.isEmpty()) {
            TreeNode node = nodeStack.pop();
            Integer depth = depthStack.pop();
            if (node == null) {
                continue;
            }
            maxDepth = Math.max(maxDepth, depth);
            leftView.computeIfAbsent(depth, k -> node.val);
            // 按照栈的特点先进后出，所以先加入左节点，然后右节点，能够保证每次都先遍历右子树
            nodeStack.push(node.right);
            nodeStack.push(node.left);
            depthStack.push(depth + 1);
            depthStack.push(depth + 1);
        }
        return new ArrayList<>(leftView.values());
    }

    /**
     * 来自leetcode官方的深度优先遍历解法
     */
    public List<Integer> rightSideViewSolutionFromLeetcode(TreeNode root) {
        Map<Integer, Integer> rightmostValueAtDepth = new HashMap<Integer, Integer>();
        int max_depth = -1;

        Deque<TreeNode> nodeStack = new LinkedList<TreeNode>();
        Deque<Integer> depthStack = new LinkedList<Integer>();
        nodeStack.push(root);
        depthStack.push(0);

        while (!nodeStack.isEmpty()) {
            TreeNode node = nodeStack.pop();
            int depth = depthStack.pop();

            if (node != null) {
                // 维护二叉树的最大深度
                max_depth = Math.max(max_depth, depth);

                // 如果不存在对应深度的节点我们才插入
                if (!rightmostValueAtDepth.containsKey(depth)) {
                    rightmostValueAtDepth.put(depth, node.val);
                }

                nodeStack.push(node.left);
                nodeStack.push(node.right);
                depthStack.push(depth + 1);
                depthStack.push(depth + 1);
            }
        }

        List<Integer> rightView = new ArrayList<Integer>();
        for (int depth = 0; depth <= max_depth; depth++) {
            rightView.add(rightmostValueAtDepth.get(depth));
        }

        return rightView;
    }

    /**
     * Definition for a binary tree node.
     */
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            return String.valueOf(val);
        }
    }


}
