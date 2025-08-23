package com.baseDataStructure.TreeStructure.BinarySearchTreeStructure;

/**
 * @Author: Bingyu Chen
 * @CreateTime: 2025-08-13
 * @Description: 基于泛型key的带比较接口的二叉搜索树
 */
public class BSTtreeWithComparable<T extends Comparable<T>, R> {

    BSTcomparableTreeNode<T,R> root;

    public BSTtreeWithComparable(BSTcomparableTreeNode<T,R> root) {
        this.root = root;
    }

    public BSTtreeWithComparable() {
    }

    static class BSTcomparableTreeNode<T,R>{
        T key;
        R value;

        BSTcomparableTreeNode<T,R> left;
        BSTcomparableTreeNode<T,R> right;

        public BSTcomparableTreeNode(T key, R value) {
            this.key = key;
            this.value = value;
        }

        public BSTcomparableTreeNode() {
        }

        public BSTcomparableTreeNode(T key, R value, BSTcomparableTreeNode<T,R> left, BSTcomparableTreeNode<T,R> right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    public R get(T key){
        return doGet(root,key);
    }

    public R doGet(BSTcomparableTreeNode<T,R> curNode, T key){

        while (curNode != null){
            int result = key.compareTo(curNode.key);

            if (result > 0){
                // key > curNode.key
                curNode = curNode.right;
            } else if (result == 0) {
                return curNode.value;
            }else {
                curNode = curNode.left;
            }
        }
        return null;
    }

}
