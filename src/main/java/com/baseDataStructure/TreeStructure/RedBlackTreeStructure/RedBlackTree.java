package com.baseDataStructure.TreeStructure.RedBlackTreeStructure;

/**
 * @Author: Bingyu Chen
 * @CreateTime: 2025-08-17
 * @Description: 红黑树
 */
public class RedBlackTree {

    enum Color{
        RED, BLACK;
    }

    private RedBlackTreeNode root;

    private static class RedBlackTreeNode {
        int key;
        Object value;
        RedBlackTreeNode left;
        RedBlackTreeNode right;
        RedBlackTreeNode parent;
        Color color = Color.RED;

        public RedBlackTreeNode() {
        }

        public RedBlackTreeNode(int key, Object value) {
            this.key = key;
            this.value = value;
        }

        // 是否是左孩子
        boolean isLeftChild(){
            return parent != null && parent.left == this;
        }
        // 找当前节点的叔叔 (父亲节点的亲兄弟节点)
        RedBlackTreeNode uncle(){
            // 有爷爷节点才能有叔叔
            if ( parent == null || parent.parent == null){
                return null;
            }
            if (parent.isLeftChild()){
                // 父亲节点是爷爷节点的左孩子
                return parent.parent.right;
            }else {
                // 父亲节点是爷爷节点的右孩子
                return parent.parent.left;
            }
        }

        // 找当前节点的兄弟
        RedBlackTreeNode sibling(){
            if (parent == null){
                return null;
            }
            if (this.isLeftChild()){
                // 当前节点是父亲节点的左孩子
                return parent.right;
            }else {
                // 当前节点是父亲节点的右孩子
                return parent.left;
            }
        }


    }

    // 判断节点是红色还是黑色
    boolean isRed(RedBlackTreeNode curNode){
        return curNode != null && curNode.color == Color.RED;
    }
    boolean isBlack(RedBlackTreeNode curNode){
        return !isRed(curNode);
    }

    /**
     * 右旋 -- （与AVL大部分相同）
     *      1. 对parent处理 （新增parent指针）
     *      2. 旋转后新根的父子关系在方法内直接处理（与 AVL向外抛，再递归处理 不同）
     * @param father
     *
     *                   GrandFather
     *                     /
     *                  Father（ curNode 假设该节点为不平衡的节点，向右旋转）
     *               /          \
     *          LeftChild       RightChild
     *          /       \
     * LeftGrandson  RightGrandson
     */
    private void rightRotate(RedBlackTreeNode father){

        RedBlackTreeNode grandFather = father.parent;
        RedBlackTreeNode leftChild = father.left;
        RedBlackTreeNode rightChild = father.right;
        RedBlackTreeNode rightGrandson = leftChild.right;

        // 将左孩子的右子树放到新转下来的右孩子的左子树位置
        if (rightGrandson != null){
            rightGrandson.parent = father;
        }
        father.left = rightGrandson;
        // 将左孩子旋转上来
        leftChild.right = father;
        leftChild.parent = grandFather;
        father.parent = leftChild;
        if (grandFather == null){
            // father是root节点
            root = leftChild;
        } else if (grandFather.left == father) {
            grandFather.left = leftChild;
        }else {
            grandFather.right = leftChild;
        }
    }

    /**
     * 左旋 -- （与AVL大部分相同）
     *      1. 对parent处理 （新增parent指针）
     *      2. 旋转后新根的父子关系在方法内直接处理（与 AVL向外抛，再递归处理 不同）
     * @param father
     *
     *                   GrandFather
     *                     /
     *                  Father（ curNode 假设该节点为不平衡的节点，向右旋转）
     *               /          \
     *          LeftChild       RightChild
     *                          /       \
     *                   LeftGrandson  RightGrandson
     */
    private void leftRotate(RedBlackTreeNode father){
        RedBlackTreeNode grandFather = father.parent;
        RedBlackTreeNode rightChild = father.right;
        RedBlackTreeNode leftGrandson = father.left;

        // 将右孩子的左节点挂到新转下来的父节点上
        father.right = leftGrandson;
        if (leftGrandson != null){
            leftGrandson.parent = father;
        }

        // 右孩子转上去
        rightChild.left = father;
        rightChild.parent = grandFather;
        father.parent = rightChild;
        if (grandFather == null){
            // father为root
            root = rightChild;
        } else if (grandFather.left == father){
            grandFather.left = rightChild;
        }else{
            grandFather.right = rightChild;
        }
    }

    /**
     * 新增or更新
     * 正常增、遇到红红不平衡要进行调整
     * @param key - 键
     * @param value - 值
     */
    public void put(int key, Object value){
        // 使用AVL新增流程进行新增
        RedBlackTreeNode curNode = root;
        RedBlackTreeNode parent = null;
        while (curNode != null){
            parent = curNode;
            if (curNode.key < key){
                curNode = curNode.right;
            } else if (curNode.key > key) {
                curNode = curNode.left;
            }else{
                // key本身就存在，进行替换
                curNode.value = value;
                return;
            }
        }
        // 找到了新空位
        RedBlackTreeNode inserted = new RedBlackTreeNode(key, value);
        if (parent == null){
            root = inserted;
        } else if (parent.key < key){
            // 插入到父亲节点的右子树
            parent.right = inserted;
            inserted.parent = parent;
        }else {
            // 插入到父亲节点的左子树
            parent.left = inserted;
            inserted.parent = parent;
        }
        // 红红不平衡，进行调整
        /* 为什么不会是黑黑情况，因为新节点默认为红色 */
        fixRedRed(inserted);
    }

    /**
     * 调整红红情况
     * @param inserted 新插入节点
     */
    void fixRedRed(RedBlackTreeNode inserted){
        if (inserted == root){
            // case1：插入节点为root节点，变黑即可
            inserted.color = Color.BLACK;
            return;
        }
        if (isBlack(inserted.parent)) {
            // case2：插入节点的父节点是黑色，无需调整
            return;
        }
        // case3&4：父亲节点为红色-- 红红相邻情况 -- 需要调整

        RedBlackTreeNode father = inserted.parent;
        RedBlackTreeNode uncle = inserted.uncle();
        RedBlackTreeNode grandFather = father.parent;

        if (isRed(uncle)){
            // case3：叔叔为红色
            // 父亲&叔叔变黑，爷爷变红； 对爷爷节点做递归处理
            father.color = Color.BLACK;
            uncle.color = Color.BLACK;
            grandFather.color = Color.RED;
            fixRedRed(grandFather);
            return;
        }
        // case4：叔叔为黑色
        if (father.isLeftChild() && inserted.isLeftChild()){
            // case4.1：父亲为左孩子，插入节点也为左孩子
            // 父亲变黑，爷爷变红；右旋爷爷
            father.color = Color.BLACK;
            grandFather.color = Color.RED;
            rightRotate(grandFather);
        } else if (father.isLeftChild() && !inserted.isLeftChild()) {
            // case4.2：父亲为左孩子，插入节点为右孩子
            // 父节点进行左旋（先变成case4.1，再按照case4.1进行处理）
            leftRotate(father);
            // case4.1流程
            inserted.color = Color.BLACK;
            grandFather.color = Color.RED;
            rightRotate(grandFather);
        } else if (!father.isLeftChild() && !inserted.isLeftChild()) {
            // case4.3：父节点为右孩子，插入节点也为右孩子
            // 父节点变黑，爷爷节点变红；左旋爷爷节点
            father.color = Color.BLACK;
            grandFather.color = Color.RED;
            leftRotate(grandFather);
        }else {
            // case4.4：父节点为右孩子，插入节点为左孩子
            // 右旋父节点，按照case4.3进行处理
            rightRotate(father);
            // case4.3
            inserted.color = Color.BLACK;
            grandFather.color = Color.RED;
            leftRotate(grandFather);
        }
    }

    /**
     * 删除
     * 正常删、会用到李代桃僵技巧、遇到黑黑不平衡进行调整
     * @param key- 键
     */
    public void remove(int key){
        // 找到被删除节点
        RedBlackTreeNode deletedNode = find(key);
        if (deletedNode == null){
            return;
        }
        doRemove(deletedNode);
        // 使用AVL删除流程进行删除

        // 黑黑不平衡进行调整
    }

    private void doRemove(RedBlackTreeNode deleted) {
        RedBlackTreeNode replaced = findReplaced(deleted);
        RedBlackTreeNode parent = deleted.parent;
        if (replaced == null) {
            // 情况1：没有左右子树
            if (deleted == root){
                //  case1.1 被删除节点为root
                root = null;
                return;
            }else {
                // case1.2 被删除节点不是root
                if (isBlack(deleted)){
                    // 复杂调整 -- 双黑 -- 此处传入参数：被删除节点 -- 即调整被删除节点
                    // 执行流程：先调整平衡，再进行删除操作
                    fixDoubleBlack(deleted);
                }else {
                    // 红色叶子，无需任何处理
                }
                if (deleted.isLeftChild()){
                    parent.left = null;
                }else {
                    parent.right = null;
                }
                deleted.parent = null;

            }
            return;
        }
        if (deleted.left == null || deleted.right == null){
            // 情况2：有左子树or右子树
            if (deleted == root){
                //  case2.1 被删除节点为root
                root.key = replaced.key;
                root.value = replaced.value;
                root.left = root.right=null;
                replaced.parent = null;
                return;
            }else {
                // case2.2 被删除节点不是root
                if (deleted.isLeftChild()){
                    parent.left = replaced;
                }else {
                    parent.right = replaced;
                }
                replaced.parent = parent;
                deleted.left = deleted.right = deleted.parent = null;
                // 进行判断，是否需要进入doubleBlack平衡调整
                if (isBlack(deleted) && isBlack(replaced)){
                    // 复杂调整 -- 双黑 -- 此处传入参数：剩余节点 -- 即调整剩余节点
                    // 执行流程：先删除操作，在进行调整平衡
                    fixDoubleBlack(replaced);
                }else {
                    // 删除节点为黑色，其子节点为红色
                    // 最后将子节点变为黑色即可
                    replaced.color = Color.BLACK;
                }
            }
            return;
        }
        // 情况3：有左子树和右子树
        /*“李代桃僵”，
            将后任节点的key&value 与 被删除节点的key&value进行交换，
            则实际上删除的变成了后任节点，
                后任节点只存在：无左右子树or只有一个子树的情况，
                且原来被删除节点的树结构可以不变化，
        简化了删除流程*/
        // 交换后任节点&被删除节点
        int keyBackup = deleted.key;
        deleted.key = replaced.key;
        replaced.key = keyBackup;
        Object valueBackup = deleted.value;
        deleted.value = replaced.value;
        replaced.value = valueBackup;
        // 递归删除后任节点
        doRemove(replaced);

    }

    // 双黑平衡调整
    private void fixDoubleBlack(RedBlackTreeNode curNode){
        if (curNode == root){
            return;
        }

        RedBlackTreeNode parent = curNode.parent;
        RedBlackTreeNode sibling = curNode.sibling();
        if (isRed(sibling)){
            // case3：兄弟节点是红色,通过左旋or右旋过度到case4、5
             if (curNode.isLeftChild()){
                 leftRotate(parent);
             }else {
                 rightRotate(parent);
             }
             // 父节点 & 兄弟节点进行换色 -- 该情况下，父节点必为黑色，兄弟节点必为红色
             parent.color = Color.RED;
             sibling.color = Color.BLACK;
             // 递归调用，进入case4、5
            fixDoubleBlack(curNode);
            return;
        }
        if (sibling!= null){
            // 表示兄弟是黑色，case4、5
            if (isBlack(sibling.left) && isBlack(sibling.right)){
            /* case4：被调整节点的兄弟为黑，两个侄子都为黑色
            1. 将兄弟变红 -> 目的：将删除节点和兄弟那边的黑色高度同时减一，保持一致
            2. 判断父亲的颜色
                父亲为红：将父亲变黑 -> 目的：避免双红，同时该路径补充一个黑，使得黑路同
                父亲为黑：无法再补充一个黑，触发双黑，递归调用当前父节点
             */
                sibling.color = Color.RED;
                if (isRed(parent)){
                    parent.color = Color.BLACK;
                }else {
                    fixDoubleBlack(parent);
                }
            }else {
                /* case5：被调整节点为黑，两个侄子节点至少有一个是红
                兄弟是左孩子，左侄子是红，LL不平衡
                兄弟是左孩子，右侄子是红，LR不平衡
                兄弟是右孩子，左侄子是红，RR不平衡
                兄弟是右孩子，右侄子是红，RL不平衡
                 */
                if (sibling.isLeftChild() && isRed(sibling.left)){
                    // LL - 对父节点做右旋
                    rightRotate(parent);
                    // 变色，新转上来的兄弟节点变为父节点的颜色、父节点变为黑色、左侄子节点变为黑色
                    sibling.color = parent.color;
                    parent.color = Color.BLACK;
                    sibling.left.color = Color.BLACK;
                } else if (sibling.isLeftChild() && isRed(sibling.right)) {
                    // LR - 先对兄弟节点进行左旋（变为LL），再对父节点做右旋
                    sibling.right.color = parent.color;; // 提前变色，防止旋转后找不到该右孩子
                    leftRotate(sibling);
                    rightRotate(parent);
                    // 变色，新转上来的兄弟的右孩子节点变为父节点的颜色、父节点变为黑色、兄弟节点变为黑色（不用变，本来就是黑色）
                    parent.color = Color.BLACK;
                }

            }
        }else{
            // 无兄弟节点，双黑递归父节点
            fixDoubleBlack(parent);
        }


    }


    // 查找删除节点
    RedBlackTreeNode find(int key){
        RedBlackTreeNode finded = root;
        while (finded != null) {

            if (finded.key < key){
                finded = finded.right;
            }else if (finded.key > key){
                finded = finded.left;
            }else {
                return finded;
            }

        }
        return null;
    }

    // 查找剩余节点 (参考AVL树删除的四种情况：没有左右子树、左右子树有一个、左右子树都存在)
    RedBlackTreeNode findReplaced(RedBlackTreeNode deleted){
        if (deleted.left == null && deleted.right == null){
            return null;
        }
        if (deleted.left == null){
            return deleted.right;
        }
        if (deleted.right == null){
            return deleted.left;
        }
        // 左右子树都存在，找后任节点，后任节点就作为剩余节点的“根节点返回”
        // 找到后任节点
        RedBlackTreeNode nextNode = deleted.right;
        while (nextNode.left != null){
            nextNode = nextNode.left;
        }
        return nextNode;
    }



}
