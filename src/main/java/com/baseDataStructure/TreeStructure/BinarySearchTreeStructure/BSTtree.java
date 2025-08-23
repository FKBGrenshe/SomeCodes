package com.baseDataStructure.TreeStructure.BinarySearchTreeStructure;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: Bingyu Chen
 * @CreateTime: 2025-08-12
 * @Description: 二叉搜索树
 */
public class BSTtree {

    BSTNode root; // 根节点

    /**
     * 查找关键字对应的值
     * @param key - 关键字
     * @return 关键字对应的值
     */
    public Object get(int key){
        return doGet(root,key);
    }

    private Object doGet(BSTNode curNode, int key){

        if (curNode == null){
            return null;
        }

        if (curNode.key < key){
            return doGet(curNode.right,key);
        } else if (curNode.key > key) {
            return doGet(curNode.left,key);
        }else {
            return curNode.value;
        }
    }

    /**
     * 查找最小关键字对应的值
     */
    public Object min(){
        BSTNode minNode = root;
        if (minNode == null){
            return null;
        }
        while (minNode.left != null){
            minNode = minNode.left;
        }
        return minNode.left;
    }
    /**
     * 查找最大关键字对应的值
     */
    public Object max(){
        BSTNode maxNode = root;
        if (maxNode == null){
            return null;
        }
        while (maxNode.right != null){
            maxNode = maxNode.right;
        }
        return maxNode.right;
    }

    /**
     * 存储关键字和值
     * @param key 关键字
     * @param value 值
     */
    public void put(int key, Object value){
        /*tree为空，插入root节点*/
        if (root == null){
            root = new BSTNode(key,value);
        }

        // 1. 本来就有key -- 替换该node
        // 2. 没有该key -- 插入

        BSTNode curNode = root;
        BSTNode prev = null;
        while (curNode != null){
            prev = curNode;
            if (curNode.key > key){
                curNode = curNode.left;
            } else if (curNode.key == key) {
                // 更新
                curNode.value = value;
                return;
            }else {
                curNode = curNode.right;
            }
        }
        BSTNode newNode = new BSTNode(key, value);
        if (prev.key > key){
            prev.left = newNode;
        }else {
            prev.right = newNode;
        }
    }

    /**
     * 找到关键字的前驱
     * @param key
     * @return
     */
    public Object successor(int key){
        /*找到当前节点*/

        BSTNode curNode = root;
        BSTNode ancestorFromRight = null;
        while (curNode!= null){
            if (curNode.key < key){
                curNode = curNode.right;
            } else if (curNode.key > key) {
                ancestorFromRight = curNode;
                curNode = curNode.left;
            }else {
                // 找到当前节点
                break;
            }
        }
        if (curNode == null){
            /*没有当前节点*/
            return null;
        }
        /*
        * 1. 当前节点有右子树，该节点的右子树的最小值 = seccessor
        * 2. 当前节点无右子树，该节点的前驱节点在它右边，该节点的前驱 = successor
        */

        if (curNode.right != null){
            // 情况1
            BSTNode rightMaxNode = curNode.right;
            while (rightMaxNode.left != null){
                rightMaxNode = rightMaxNode.left;
            }
            return rightMaxNode;
        }
        return ancestorFromRight != null ? ancestorFromRight.value : null;
    }

    /**
     * 找到关键字的前任（就是最接近当前key的并且小于它的key）
     * @param key
     * @return
     */
    public Object predecessor(int key){
        /*找到当前节点*/
        BSTNode p = root;
        BSTNode ancestorFromLeft = null;
        while (p != null){
            if (key < p.key){
                p = p.left;
            }else if (key > p.key){
                // 情况2：父节点在当前节点的左边
                ancestorFromLeft = p;
                p = p.right;
            }else {
                break;
            }
        }
        if (p == null){
            // 没有找到当前key对应的节点
            return null;
        }
        /*
        * 1：节点有左子树，当前节点的左子树最大值 = predecessor
        * 2： 节点无左子树，若父节点在该节点左侧，则父节点是predecessor
        */
        if (p.left != null){
            // 情况1
            BSTNode maxLeftNode = p.left;
            while (maxLeftNode.right != null){
                maxLeftNode = maxLeftNode.right;
            }
            return maxLeftNode.value;
        }
        // 情况2
        return ancestorFromLeft != null ?
                ancestorFromLeft.value : null;
    }

    /**
     * 删除关键字对应的节点
     * @param key
     * @return
     */
    public Object delete(int key){
        /*
        四种情况
        * 1. 被删节点 - 有左子树、无右子树
        * 2. 被删节点 - 无左子树、有右子树
        * 3. 被删节点 - 无左子树、无右子树
        * 4. 被删节点 - 有左子树、有右子树
        */

        // 找到被删除节点
        BSTNode deletedNode = root;
        BSTNode ancestor = null;

        while (deletedNode != null){
            if (deletedNode.key > key){
                ancestor = deletedNode;
                deletedNode = deletedNode.left;
            } else if (deletedNode.key == key) {
                // 找到了
                break;
            }else {
                ancestor = deletedNode;
                deletedNode = deletedNode.right;
            }
        }
        if (deletedNode == null){
            // 没找到需要删除的节点
            return null;
        }

        // 删除操作
        if (deletedNode.left == null){
            // 无左子树 或者 无左子树和右子树
            shift(ancestor,deletedNode,deletedNode.right);
        } else if (deletedNode.right == null) {
            // 无右子树
            shift(ancestor,deletedNode,deletedNode.left);
        }else {
            // 左右子树都存在
            /*
            * 1）找到当前节点的后任节点 -- 被删除节点找后任
            * 2）用后任节点替代当前节点 -- 处理后任的后事
            * 3）继续“删除”后任节点 -- 后任取代被删除节点
            */

            // 1. 找被删除节点的右子树中最小值
            BSTNode nextNode = deletedNode.right;
            BSTNode nextNodeParent = deletedNode;
            while (nextNode.left != null){
                nextNodeParent = nextNode;
                nextNode = nextNode.left;
            }
            // 2. 当：被删除节点&后任并非直接相连的关系时，需要处理后任节点的后事
            if (nextNodeParent != deletedNode){
                // 处理后任节点的后事 -- 因为后任节点是被删除节点的右子树的最小值，因此后任节点肯定没有左子树
                shift(nextNodeParent,nextNode,nextNode.right);
                nextNode.right = deletedNode.right;
            }
            // 3. 后任取代被删除节点
            shift(ancestor,deletedNode,nextNode);
            nextNode.left = deletedNode.left;
        }
        return deletedNode.value;
    }

    /**
     * 托孤方法，将某节点的左子树or右子树托孤给该节点的父节点
     * @param parent 父节点
     * @param curNode 被删除节点
     * @param childNode 被顶上去的节点
     */
    private void shift(BSTNode parent, BSTNode curNode, BSTNode childNode){
        if (parent == null){
            // 说明此时被删除节点为root节点
            root = childNode;
            return;
        } else if (curNode == parent.left) {
            parent.left = childNode;
        }else {
            parent.right = childNode;
        }
    }

    // 找到 < key 的所有value
    public List<Object> less(int key){
        ArrayList<Object> result = new ArrayList<>();
        BSTNode p = root;
        LinkedList<BSTNode> stack = new LinkedList<>();

        while (p != null || !stack.isEmpty()){
            if (p != null){
                stack.push(p);
                p = p.left;
            }else {
                BSTNode pop = stack.pop();
                // 处理值
                if (pop.key < key){
                    result.add(pop.value);
                }else {
                    break;
                }
                p = pop.right;
            }
        }

        return result;
    }

    // 找到 > key 的所有value
    public List<Object> greater(int key){
        ArrayList<Object> result = new ArrayList<>();
        BSTNode p = root;
        LinkedList<BSTNode> stack = new LinkedList<>();

        while (p != null || !stack.isEmpty()){
            if (p != null){
                stack.push(p);
                p = p.left;
            }else {
                BSTNode pop = stack.pop();
                // 处理值
                p = pop.right;
            }
        }
        return result;
    }

    // 找到 [min,max] 的所有value
    public List<Object> between(int min, int max){
        ArrayList<Object> result = new ArrayList<>();
        BSTNode p = root;
        LinkedList<BSTNode> stack = new LinkedList<>();

        while (p != null || !stack.isEmpty()){
            if (p != null){
                stack.push(p);
                p = p.left;
            }else {
                BSTNode pop = stack.pop();
                // 处理值
                p = pop.right;
            }
        }
        return result;
    }


    /**
     * 节点类
     */
    static class BSTNode{
        int key;
        Object value;

        BSTNode left;
        BSTNode right;

        public BSTNode(int key) {
            this.key = key;
        }

        public BSTNode(int key, Object value, BSTNode left, BSTNode right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }

        public BSTNode(int key ,Object value) {
            this.value = value;
            this.key = key;
        }
    }






}
