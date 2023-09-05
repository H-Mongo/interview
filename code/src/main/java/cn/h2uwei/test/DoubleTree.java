package cn.h2uwei.test;

import java.util.Deque;
import java.util.LinkedList;

/**
 *
 * 操作给定的二叉树，将其变换为源二叉树的镜像。
 * 输入描述:
 *
 * 二叉树的镜像定义：源二叉树
 *             8
 *            /  \
 *           6   10
 *          / \  / \
 *         5  7 9 11
 *         镜像二叉树
 *             8
 *            /  \
 *           10   6
 *          / \  / \
 *         11 9 7  5
 *
 * @author zuweih
 * @date 2023/8/31 20:19
 */
public class DoubleTree {


    public void main(TreeNode node) {
        if (node == null) {
            return;
        }
        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(node);
        while (!stack.isEmpty()) {
            TreeNode curr = stack.pop();
            if (curr.left != null) {
                stack.push(curr.left);
            }
            if (curr.right != null) {
                stack.push(curr.right);
            }
            TreeNode temp = curr.right;
            curr.left = temp;
            curr.right = curr.left;
        }

    }

    static class TreeNode {
        private Integer value;
        private TreeNode right;
        private TreeNode left;
    }
}
