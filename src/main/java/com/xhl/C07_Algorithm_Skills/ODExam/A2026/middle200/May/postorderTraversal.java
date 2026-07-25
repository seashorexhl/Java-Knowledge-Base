package com.xhl.C07_Algorithm_Skills.ODExam.A2026.middle200.May;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author: xhl
 * @Date: 2026-07-10 02:59
 * @Description:    输出二叉树后序遍历结果-200分
 */
public class postorderTraversal {

    static void main(String[] args) {
        String preorderStr = "ADE";
        String inorderStr = "DAE";
        char beDeletedNode = 'E';
        postorderTraversal pt = new postorderTraversal();
        String mained = pt.main(preorderStr, inorderStr, beDeletedNode);
        System.out.println(mained);

    }
    /**
     *
     * */
    public static String main(String preorderStr, String inorderStr, char beDeletedNode) {
        if (preorderStr == null || inorderStr == null ||
                preorderStr.length() < 2 || preorderStr.length() > 26 ||
                inorderStr.length() < 2 || inorderStr.length() > 26 ||
                preorderStr.length() != inorderStr.length() ||
                !Character.isUpperCase(beDeletedNode)) {
            return "";
        }

        Set<Character> set = new HashSet<>();
        for (char c : preorderStr.toCharArray()) {
            if (!Character.isUpperCase(c)) return "";
            set.add(c);
        }
        for (char c : inorderStr.toCharArray()) {
            if (!Character.isUpperCase(c)) return "";
            if (!set.contains(c)) return "";
        }
        if (set.size() != preorderStr.length()) return "";

        TreeNode root = buildTree(preorderStr, inorderStr);
        if (root == null) return "";

        if (root.val != beDeletedNode) {
            deleteNode(root, beDeletedNode);
        }

        return postorderTraversal(root);
    }
    /**
     *  遍历
     * */
    private static TreeNode buildTree(String preorder, String inorder) {
        if (preorder.isEmpty() || inorder.isEmpty()) return null;
        char rootVal = preorder.charAt(0);
        TreeNode root = new TreeNode(rootVal);
        int rootIndex = inorder.indexOf(rootVal);
        if (rootIndex == -1) return null;
        root.left = buildTree(preorder.substring(1, 1 + rootIndex), inorder.substring(0, rootIndex));
        root.right = buildTree(preorder.substring(1 + rootIndex), inorder.substring(rootIndex + 1));
        return root;
    }

    /**
     *  删除节点
     * */
    private static void deleteNode(TreeNode root, char target) {
        TreeNode[] result = findNode(root, target, null);
        if (result[0] == null || result[1] == null) return; // 根节点或未找到

        TreeNode targetNode = result[0];
        TreeNode parent = result[1];

        if (targetNode.left == null && targetNode.right == null) {
            if (parent.left == targetNode) parent.left = null;
            else parent.right = null;
            return;
        }

        if (parent.left == targetNode) {
            parent.left = targetNode.left;
        } else {
            parent.right = targetNode.right;
        }
    }

    /**
     *  寻找节点
     * */
    private static TreeNode[] findNode(TreeNode node, char target, TreeNode parent) {
        if (node == null) return new TreeNode[]{null, null};
        if (node.val == target) return new TreeNode[]{node, parent};

        TreeNode[] left = findNode(node.left, target, node);
        if (left[0] != null) return left;

        return findNode(node.right, target, node);
    }

    /**
     *  后序遍历
     * */
    private static String postorderTraversal(TreeNode root) {
        if (root == null) return "";
        return postorderTraversal(root.left) + postorderTraversal(root.right) + root.val;
    }

}
