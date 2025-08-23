package com.baseDataStructure.TreeStructure.BinarySearchTreeStructure;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import  org. junit. jupiter. api.Assertions.*;


/**
 * @Author: Bingyu Chen
 * @CreateTime: 2025-08-13
 * @Description: 二叉搜索树测试
 */
public class BSTtreeTest {

    @Test
    public void findNodeTest(){
        BSTtree tree = createTree();
        Assertions.assertEquals("张无忌", tree.get(1));
        Assertions.assertEquals("说不得", tree.get(5));
        Assertions.assertEquals(null, tree.get(9));
    }

    @Test
    public void put() {
        BSTtree tree = new BSTtree();
        tree.put(4, new Object());
        tree.put(2, new Object());
        tree.put(6, new Object());
        tree.put(1, new Object());
        tree.put(3, new Object());
        tree.put(7, new Object());
        tree.put(5, new Object());
        Assertions.assertTrue(isSameTree(createTree().root, tree.root));
        tree.put(1, "教主张无忌");
        Assertions.assertEquals("教主张无忌", tree.get(1));
    }

    static boolean isSameTree(BSTtree.BSTNode tree1, BSTtree.BSTNode tree2) {
        if (tree1 == null && tree2 == null) {
            return true;
        }
        if (tree1 == null || tree2 == null) {
            return false;
        }
        if (tree1.key != tree2.key) {
            return false;
        }
        return isSameTree(tree1.left, tree2.left) && isSameTree(tree1.right, tree2.right);
    }


    public BSTtree createTree(){
        BSTtree.BSTNode n1 = new BSTtree.BSTNode(1, "张无忌");
        BSTtree.BSTNode n3 = new BSTtree.BSTNode(3, "宋青书");
        BSTtree.BSTNode n2 = new BSTtree.BSTNode(2, "周芷若", n1, n3);

        BSTtree.BSTNode n5 = new BSTtree.BSTNode(5, "说不得");
        BSTtree.BSTNode n7 = new BSTtree.BSTNode(7, "殷离");
        BSTtree.BSTNode n6 = new BSTtree.BSTNode(6, "赵敏",n5,n7);
        BSTtree.BSTNode root = new BSTtree.BSTNode(4, "小昭",n2,n6);

        BSTtree tree = new BSTtree();
        tree.root = root;
        return tree;
    }

}
