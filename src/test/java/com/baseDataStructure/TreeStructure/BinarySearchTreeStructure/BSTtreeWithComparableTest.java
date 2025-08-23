package com.baseDataStructure.TreeStructure.BinarySearchTreeStructure;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * @Author: Bingyu Chen
 * @CreateTime: 2025-08-13
 * @Description: 带泛型key可比较的二叉搜索树测试
 */
public class BSTtreeWithComparableTest {

    @Test
    void testFind() {
        BSTtreeWithComparable<String,String> tree = createTree();
        assertEquals("张无忌", tree.get("a"));
        assertEquals("周芷若", tree.get("b"));
        assertEquals("宋青书", tree.get("c"));
        assertEquals("小昭", tree.get("d"));
        assertEquals("说不得", tree.get("e"));
        assertEquals("赵敏", tree.get("f"));
        assertEquals("殷离", tree.get("g"));
        assertNull(tree.get("h"));
    }

    public BSTtreeWithComparable<String,String> createTree(){
        /*
                     4
                   /   \
                  2     6
                 / \   / \
                1   3 5   7
         */
        BSTtreeWithComparable.BSTcomparableTreeNode<String,String> n1 = new BSTtreeWithComparable.BSTcomparableTreeNode<>("a", "张无忌");
        BSTtreeWithComparable.BSTcomparableTreeNode<String,String> n3 = new BSTtreeWithComparable.BSTcomparableTreeNode<>("c", "宋青书");
        BSTtreeWithComparable.BSTcomparableTreeNode<String,String> n2 = new BSTtreeWithComparable.BSTcomparableTreeNode<>("b", "周芷若", n1, n3);

        BSTtreeWithComparable.BSTcomparableTreeNode<String,String> n5 = new BSTtreeWithComparable.BSTcomparableTreeNode<>("e","说不得");
        BSTtreeWithComparable.BSTcomparableTreeNode<String,String> n7 = new BSTtreeWithComparable.BSTcomparableTreeNode<>("g","殷离");
        BSTtreeWithComparable.BSTcomparableTreeNode<String,String> n6 = new BSTtreeWithComparable.BSTcomparableTreeNode<>("f", "赵敏", n5, n7);
        BSTtreeWithComparable.BSTcomparableTreeNode<String,String> root = new BSTtreeWithComparable.BSTcomparableTreeNode<>("d", "小昭", n2, n6);

        BSTtreeWithComparable<String,String> tree = new BSTtreeWithComparable<>();
        tree.root = root;
        return tree;
    }
}
