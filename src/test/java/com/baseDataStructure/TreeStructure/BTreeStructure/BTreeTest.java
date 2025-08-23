package com.baseDataStructure.TreeStructure.BTreeStructure;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @Author: Bingyu Chen
 * @CreateTime: 2025-08-21
 * @Description:
 */
public class BTreeTest {


    @Test
    void split1(){
        BTree tree = new BTree();
        BTree.BTreeNode root = tree.root;
        root.leaf = false;
        root.keys[0] = 5;
        root.keyNumber = 1;

        root.children[0] = new BTree.BTreeNode(new int[]{1, 2, 3});
        root.children[0].keyNumber = 3;

        root.children[1] = new BTree.BTreeNode(new int[]{6});
        root.children[1].keyNumber = 1;

        tree.split(root.children[0], root, 0);

        Assertions.assertEquals("[2, 5]", root.toString());
        Assertions.assertEquals("[1]", root.children[0].toString());
        Assertions.assertEquals("[3]", root.children[1].toString());
        Assertions.assertEquals("[6]", root.children[2].toString());

    }

    @Test
    void putTest(){
        BTree tree = new BTree();
        tree.put(1);
        tree.put(2);
        tree.put(3);
        tree.put(4);
        tree.put(5);
        tree.put(6);
        tree.put(7);
        tree.put(8);
        tree.put(9);
        tree.put(10);
        tree.put(11);

        Assertions.assertEquals("[4, 8]", tree.root.toString());
        Assertions.assertEquals("[2]", tree.root.children[0].toString());
        Assertions.assertEquals("[6]", tree.root.children[1].toString());
        Assertions.assertEquals("[10]", tree.root.children[2].toString());
        Assertions.assertEquals("[1]", tree.root.children[0].children[0].toString());
        Assertions.assertEquals("[3]", tree.root.children[0].children[1].toString());
        Assertions.assertEquals("[5]", tree.root.children[1].children[0].toString());
        Assertions.assertEquals("[7]", tree.root.children[1].children[1].toString());
        Assertions.assertEquals("[9]", tree.root.children[2].children[0].toString());
        Assertions.assertEquals("[11]", tree.root.children[2].children[1].toString());

    }

}
