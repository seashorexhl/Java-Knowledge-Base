package com.xhl.C10_Career_Growth.ODExam.TestPaperA;

import java.util.Scanner;

/**
 * @Author: xhl
 * @Date: 2026-06-10 03:34
 * @Description: 61 三叉搜索树  计算最接近的数
 */
public class TSTree {

    public static void main(String[] args) {
        Tree tree = new Tree(); // 创建树对象
        Scanner scanner = new Scanner(System.in); // 创建扫描器读取输入
        int N = scanner.nextInt();  // 读取第一个整数作为后续要输入的节点数量
        TreeNode root = null; // 初始化根节点为null
        for (int i = 0; i < N; i++) {
            int num = scanner.nextInt();  // 循环读取N个整数作为节点值
            root = tree.insert(root, num);  // 将每个整数插入树中
        }
        scanner.close(); // 关闭扫描器
        int height = tree.getHeight(root);  // 获取树的高度
        System.out.println(height);  // 输出树的高度
    }

    /**
     *  静态内部类：树
     * */
    static class Tree {
        // 插入方法：向树中插入值
        public TreeNode insert(TreeNode root, int val) {
            if (root == null) {
                return new TreeNode(val); // 如果根节点为空，创建新节点作为根节点
            }
            if (val < root.val - 500) {
                root.left = insert(root.left, val); // 如果值小于根节点值减500，插入到左子树
            } else if (val > root.val + 500) {
                root.right = insert(root.right, val); // 如果值大于根节点值加500，插入到右子树
            } else {
                root.mid = insert(root.mid, val); // 如果值在根节点值加减500范围内，插入到中间子树
            }
            return root; // 返回根节点
        }

        // 获取树的高度
        public int getHeight(TreeNode root) {
            if (root == null) {
                return 0; // 如果根节点为空，高度为0
            }
            int leftHeight = getHeight(root.left); // 计算左子树的高度
            int midHeight = getHeight(root.mid); // 计算中间子树的高度
            int rightHeight = getHeight(root.right); // 计算右子树的高度
            return Math.max(Math.max(leftHeight, midHeight), rightHeight) + 1; // 返回三者中最大的高度加1
        }
    }

    // 静态内部类：树节点
    static class TreeNode {
        int val; // 节点值
        TreeNode left, mid, right; // 左子节点、中间子节点、右子节点
        TreeNode(int x) { val = x; } // 构造方法，初始化节点值
    }
    /*public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N =  sc.nextInt();// 5
        int[] arr = new int[N];//
        while (N-- > 0){
            arr[N] = sc.nextInt();
        }
        TSTree tst = new TSTree();
        tst.buildTSTree(arr);
        int high = tst.getHigh(arr);
        System.out.println("三叉搜索树的高度为："+high);
    }
    // TSTreeNode节点的构建
    public class TSTreeNode {
        public int val;
        public TSTreeNode left;
        public TSTreeNode right;
        public TSTreeNode middle;
        int high;
        TSTreeNode(){

        }
        TSTreeNode(int val){
            this.val = val;
            if (val <val -500){
                this.left = new TSTreeNode(val);
            }else if (val > val + 500){
                this.right = new TSTreeNode(val);
            }else  {
                this.middle = new TSTreeNode(val);
            }
        }


    }
    // 构造 三叉搜索树
    public  void  buildTSTree(int[] arr){

        int N = arr.length;
        for (int i = 1; i < N; i++) {
            new TSTreeNode(arr[i]);
        }
    }
    // 获得 三叉搜索树的高度
    public int getHigh(int[] arr) {
        int res = 0;

        return res;
    }*/
}
