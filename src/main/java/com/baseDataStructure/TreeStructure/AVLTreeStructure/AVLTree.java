package com.baseDataStructure.TreeStructure.AVLTreeStructure;

/**
 * @Author: Bingyu Chen
 * @CreateTime: 2025-08-16
 * @Description: AVL树
 *   二叉搜索树在插入和删除时，节点可能失衡
 *   如果在插入和删除时通过旋转，始终让二叉搜索树保持平衡，成为自平衡的二叉搜索树
 *   ABL是自平衡二叉搜索树的实现之一
 */
public class AVLTree {

    AVLNode root;


    static class AVLNode{
        int key;
        Object value;
        AVLNode left;
        AVLNode right;
        int height = 1; // 高度

        public AVLNode(int key, Object value) {
            this.key = key;
            this.value = value;
        }

        public AVLNode(int key) {
            this.key = key;
        }

        public AVLNode(int key, Object value, AVLNode left, AVLNode right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    // 求节点高度
    private int height(AVLNode node){
        return node == null ? 0 : node.height;
    }

    // 更新节点高度(新增、删除、旋转)
    /**
     * 使用后序遍历对各个节点高度进行更新 - 左右中
     * curNode.height = Max(leftChild.height, rightChild.height)+1
     * @param node
     * @return
     */
    private int updateHeight(AVLNode node){
        if (node == null){
            return height(node);
        }
//        int leftHeight = updateHeight(node.left);
//        int rightHeight = updateHeight(node.right);
//        node.height = Math.max(leftHeight,rightHeight)+1;
        node.height = Math.max(node.left.height,node.right.height)+1;
        return node.height;
    }

    // 平衡因子 balance factor = height(左子树) - height(左子树)
    private int bf(AVLNode node){
        return height(node.left) - height(node.right);
        // 返回 -1，0，1 -- 表示该节点平衡
        // 返回其他值 -- 表示该节点不平衡，需要进行旋转
        // bf>1 : 左子树太高了 ； bf<-1：右子树太高了
    }

    /*
    旋转的四种情况
            失衡节点的左右那边高？ && 失衡节点的height高的child节点，其左右子树哪边更高？
        LL  失衡节点左子树高 && leftChild.bf() >= 0  >> 直接对失衡节点进行右旋
        LR  失衡节点左子树高 && leftChild.bf() < 0 >> 先对左子树进行左旋变成LL，按照LL进行旋转
        RR  失衡节点右子树高 && rightChild.bf() <= 0 >> 直接对失衡节点进行左旋
        RL  失衡节点右子树高 && rightChild.bf() > 0  >> 先对右子树进行右旋变成RR，按照RR进行旋转
     */

    /**
     * 右旋
     * @param node 要旋转的节点
     * @return 转上来的节点
     */
    private AVLNode rightRotate(AVLNode node){
        // 找到node的左孩子
        AVLNode leftChild = node.left;
        // 需要提前处理 左孩子的右子树
        node.left = leftChild.right;
        // 让左孩子转上去，node转下来 -- node变为leftChild的右孩子
        leftChild.right = node;
        // 更新高度
        updateHeight(leftChild);
        return leftChild;
    }

    /**
     * 左旋
     * @param node 要旋转的节点
     * @return 转上来的节点
     */
    private AVLNode leftRotate(AVLNode node){
        // 找到node的右孩子
        AVLNode rightChild = node.right;
        // 右孩子的左子树 -> node的right
        node.right = rightChild.left;
        // 将右孩子转上去，node向左降
        rightChild.left = node;
        // 更新高度
        updateHeight(rightChild);
        return rightChild;
    }

    /**
     * （左右旋）LR -- 先左旋左子树 -- 变成LL，再右旋
     * @param node
     * @return
     */
    private AVLNode leftRightRotate(AVLNode node){
        AVLNode newLeftChild = leftRotate(node.left);
        node.left = newLeftChild;
        AVLNode newNode = rightRotate(node.right);
        return newNode;
    }

    /**
     * （右左旋）RL -- 先右旋右子树 -- 变成RR，再左旋
     * @param node
     * @return
     */
    private AVLNode rightLeftRotate(AVLNode node){
        AVLNode newRightChild = rightRotate(node.right);
        node.right = newRightChild;
        AVLNode newNode = leftRotate(node.left);
        return newNode;
    }

    /**
     * 检查节点是否失衡，重新平衡代码
     * @param node
     * @return
     */
    private AVLNode balance(AVLNode node){
        if (node == null){
            return null;
        }

        int balanceFactor = bf(node);
        if (balanceFactor > 1){
            // 左子树高
            int leftChildBalanceFactor = bf(node.left);
            if (leftChildBalanceFactor > 0){
                // LL
                return rightRotate(node);
            }else {
                // LR
                return leftRightRotate(node);
            }
        }if (balanceFactor < -1){
            // 右子树高
            int rightChildBalanceFactor = bf(node.right);
            if (rightChildBalanceFactor < 0){
                // RR
                return leftRotate(node);
            }else {
                // RL
                return rightLeftRotate(node);
            }
        }else {
            // 平衡，不用动
            return node;
        }

    }
    // 新增
    public void put(int key, Object value){
        root = doPut(root,key,value);
    }

    private AVLNode doPut(AVLNode curNode, int key, Object value){
        // 找到合适的位置，将新节点插入到AVL树中，返回
        // circumstance 1: key无重复，直接插入
        if (curNode == null){
            AVLNode avlNode = new AVLNode(key, value);
            return avlNode;
        }

        // 继续查找
        if (key < curNode.key){
            AVLNode leftchildNode = doPut(curNode.left, key, value);
            curNode.left = leftchildNode;
        }else if (key == curNode.key){
        // circumstance 2: key已经存在，对节点value进行更新
            curNode.value = value;
            return curNode;
        } else {
            AVLNode rightchildNode = doPut(curNode.right, key, value);
            curNode.right = rightchildNode;
        }
        updateHeight(curNode); // 更新节点高度
        AVLNode newRootNode = balance(curNode);// 重新检查并平衡AVL树
        return newRootNode;
    }

    public void remove(int key){
        root = doRemove(key,root);
    }

    private AVLNode doRemove(int key, AVLNode curNode){
        if (curNode == null){
            return null;
        }

        if (curNode.key < key){
            curNode.right = doRemove(key, curNode.right);
        }else if (curNode.key > key){
            curNode.left = doRemove(key, curNode.left);
        }else {

            if (curNode.left == null && curNode.right == null){
                // 无子树
                return null;
            } else if (curNode.left == null && curNode.right != null) {
                // 有左子树，无右子树
                curNode = curNode.left;
            } else if (curNode.left != null && curNode.right == null) {
                // 有右子树，无左子树
                curNode = curNode.right;
            }else {
                // 左右子树都存在 -- 参照二叉搜索树的删除节点
                /**
                 * 找到被删除节点的后任节点
                 * 1. 后任节点与被删除节点直接相连
                 * 2. 后任节点与被删除节点不直接相连
                 *      后任节点的子树进行处理
                 *      后任节点替换被删除节点
                 */
                AVLNode nextNode = curNode.right;
                while (nextNode.left != null){
                    nextNode = nextNode.left;
                }
                // 先处理后任节点的子树
                nextNode.right = doRemove(nextNode.key,curNode.right);
                nextNode.left = curNode.left;
                // 替换
                curNode = nextNode;
            }
        }
        //更新AVL树高度，并检查平衡
        updateHeight(curNode);
        return balance(curNode);
    }

}
