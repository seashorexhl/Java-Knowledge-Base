package com.xhl.C07_Algorithm_Skills.SelfSummary.AdvanceDS.Tree;


import java.util.*;

/**
 * @Author: xhl
 * @Date: 2026-06-11 03:06
 * @Description: 树的 相关解决方案
 */
public class TreeSolution {
    public static void main(String[] args) {
        // 构造树
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        TreeSolution ts = new TreeSolution();
        show(root);
        System.out.println("----------递归实现--------------");
        System.out.println("二叉树的前序遍历："+ts.preTraverse(root));
        System.out.println("中序遍历："+ts.traverse(root));
        System.out.println("后序遍历："+ts.postTraverse(root));

        /*层序遍历*/
        List<List<Integer>> result = ts.levelOrder(root);
        // 优雅的 格式化输出，告别多余的逗号
        StringBuilder sb = new StringBuilder("层序遍历二叉树：[");
        for (int i = 0; i < result.size(); i++) {
            sb.append(result.get(i));
            if (i < result.size() - 1) sb.append(", ");
        }
        sb.append("]");
        System.out.println(sb.toString());

        System.out.println("----------非递归实现------------");
        System.out.println("前序遍历："+ts.preorderTraversal(root));
        System.out.println("中序遍历："+ts.inorderTraversal(root));
        System.out.println("后序遍历："+ts.postorderTraversal(root));

    }

    /**
     * 获取树的深度
     */
    public static int getTreeDepth(TreeNode root) {
        return root == null ? 0 : (1 + Math.max(getTreeDepth(root.left), getTreeDepth(root.right)));
    }

    /**
     * 核心方法：将节点和连接线写入二维数组
     * @param currNode 当前节点
     * @param rowIndex 当前在二维数组中的行索引
     * @param columnIndex 当前在二维数组中的列索引
     * @param res 存储图形的二维数组
     * @param treeDepth 树的总深度
     */
    private static void writeArray(TreeNode currNode, int rowIndex, int columnIndex, String[][] res, int treeDepth) {
        if (currNode == null) return;

        // 1. 将当前节点的值写入对应位置
        res[rowIndex][columnIndex] = String.valueOf(currNode.val);

        // 计算当前位于第几层（根节点为第1层）
        int currLevel = ((rowIndex + 1) / 2);
        // 如果已经是最后一层，无需再向下绘制分支，直接返回
        if (currLevel == treeDepth) return;

        // 2. 计算当前节点到下一层子节点的横向间距
        // 随着层级加深，间距呈指数级缩小
        int gap = treeDepth - currLevel - 1;

        // 3. 处理左子树
        if (currNode.left != null) {
            res[rowIndex + 1][columnIndex - gap] = "/"; // 写入左斜线
            writeArray(currNode.left, rowIndex + 2, columnIndex - gap * 2, res, treeDepth); // 递归写入左子节点
        }

        // 4. 处理右子树
        if (currNode.right != null) {
            res[rowIndex + 1][columnIndex + gap] = "\\"; // 写入右斜线
            writeArray(currNode.right, rowIndex + 2, columnIndex + gap * 2, res, treeDepth); // 递归写入右子节点
        }
    }

    /**
     * 对外暴露的打印入口
     */
    public static void show(TreeNode root) {
        if (root == null) {
            System.out.println("EMPTY!");
            return;
        }

        int treeDepth = getTreeDepth(root);
        // 根据树的深度动态计算画布的高度和宽度
        int arrayHeight = treeDepth * 2 - 1;
        int arrayWidth = (2 << (treeDepth - 2)) * 3 + 1;

        // 初始化二维数组，默认填充空格
        String[][] res = new String[arrayHeight][arrayWidth];
        for (int i = 0; i < arrayHeight; i++) {
            for (int j = 0; j < arrayWidth; j++) {
                res[i][j] = " ";
            }
        }

        // 从根节点开始递归绘制
        writeArray(root, 0, arrayWidth / 2, res, treeDepth);

        // 逐行拼接并打印结果
        for (String[] line : res) {
            StringBuilder sb = new StringBuilder();
            for (String s : line) {
                sb.append(s);
            }
            System.out.println(sb.toString());
        }
    }

    /**
     * 优化 res.addAll()方法
     * 将 List 作为参数传递下去，或者使用一个类级别的成员变量
     * */
    public List<Integer> preOrderTraverse(TreeNode root){
        List<Integer> res = new ArrayList<>();
        dfs(root, res);
        return res;
    }

    private void dfs(TreeNode node, List<Integer> res){
        if(node == null) return;
        res.add(node.val);
        dfs(node.left, res);
        dfs(node.right, res);
    }

    /**
     * 二叉树的前序遍历
     * */
    public  List<Integer> preTraverse(TreeNode root){
        List<Integer> res = new ArrayList<>();
        if(root == null) return res;
        res.add(root.val);
        res.addAll(preTraverse(root.left));
        res.addAll(preTraverse(root.right));
        return res;
    }

    // 中序遍历 二叉树
    public List<Integer> traverse(TreeNode root){
        List<Integer> res = new ArrayList<>();
        if(root == null) return res;

        res.addAll(traverse(root.left));
        res.add(root.val);
        res.addAll(traverse(root.right));

        return res;
    }

    // 后序遍历 二叉树
    public List<Integer> postTraverse(TreeNode root){
        List<Integer> res = new ArrayList<>();
        if(root == null) return res;
        res.addAll(postTraverse(root.left));
        res.addAll(postTraverse(root.right));
        res.add(root.val);

        return res;
    }

    /**
     * 标准层序遍历方法（按层返回结果）
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            // 核心技巧：记录当前层的节点数量
            int levelSize = queue.size();
            List<Integer> currentLevel = new ArrayList<>();

            // 只处理当前层的节点
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                currentLevel.add(node.val);

                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            // 将当前层的结果加入总结果集
            res.add(currentLevel);
        }
        return res;
    }

    /**
     * 非递归实现前序遍历 通过 栈 来实现
     *  栈的选择可以优化：Stack vs Deque
     *  Deque<TreeNode> stack = new ArrayDeque<>();
     *  stack.push(root); // push 对应 addFirst
     *  stack.pop();      // pop 对应 removeFirst
     * */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root); // 根节点入栈

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            res.add(node.val); // 访问当前节点

            // 先压右，再压左，保证出栈时先处理左子树
            if (node.right != null) stack.push(node.right);
            if (node.left != null) stack.push(node.left);
        }
        return res;
    }

    /*非递归实现中序遍历 */
    public  List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;

        while (cur != null || !stack.isEmpty()) {
            // 1. 沿着左子树一直往下走，把遇到的节点全部入栈
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }

            // 2. 左边走到头了，弹出栈顶元素（相当于回溯到父节点）
            cur = stack.pop();
            res.add(cur.val); // 访问节点

            // 3. 转向右子树，进入下一轮循环
            cur = cur.right;
        }
        return res;
    }

    /**
     * 非递归实现 后序遍历
     * */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        TreeNode prev = null; // 记录前一个被访问的节点

        while (cur != null || !stack.isEmpty()) {
            // 1. 一路向左到底，全部压栈
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }

            // 2. 看一眼栈顶节点（注意：先不弹出！）
            cur = stack.peek();

            // 3. 判断：如果栈顶节点的右子树为空，或者右子树刚刚已经被访问过
            if (cur.right == null || cur.right == prev) {
                res.add(cur.val); // 可以安全地访问当前节点
                stack.pop();      // 真正弹出
                prev = cur;       // 更新前一个被访问的节点
                cur = null;       // 强制置空，防止重新进入左侧循环
            } else {
                // 4. 否则，说明右子树还没访问，转向右子树
                cur = cur.right;
            }
        }
        return res;
    }

}
