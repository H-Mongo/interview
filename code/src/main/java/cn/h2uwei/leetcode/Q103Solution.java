package cn.h2uwei.leetcode;

import java.util.*;

/**
 * 103. 二叉树的锯齿形层序遍历
 *
 * 给你二叉树的根节点 root ，返回其节点值的 锯齿形层序遍历 。
 * （即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
 *
 * @author zuweih
 * @date 2023/8/25 21:03
 */
public class Q103Solution {

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ans = new LinkedList<>();
        if (root == null) {
            return ans;
        }
        Queue<TreeNode> nodesQueue = new LinkedList<>();
        nodesQueue.offer(root);
        boolean isLeft = true;
        while (!nodesQueue.isEmpty()) {
            Deque<Integer> values = new LinkedList<>();
            for (int i = 0; i < nodesQueue.size(); i++) {
                TreeNode curr = nodesQueue.poll();
                if (isLeft) {
                    values.addLast(curr.val);
                } else {
                    values.addFirst(curr.val);
                }
                if (curr.left != null) {
                    nodesQueue.offer(curr.left);
                }
                if (curr.right != null) {
                    nodesQueue.offer(curr.right);
                }
            }
            isLeft = !isLeft;
            ans.add(new LinkedList<>(values));
        }
        return ans;
    }

   static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}

/**
 * Definition for a binary tree node.
 */


