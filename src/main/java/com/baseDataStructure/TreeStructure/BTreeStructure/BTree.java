package com.baseDataStructure.TreeStructure.BTreeStructure;

import java.util.Arrays;

/**
 * @Author: Bingyu Chen
 * @CreateTime: 2025-08-20
 * @Description: B树
 */
public class BTree {



    static class BTreeNode {
        int[] keys; // 节点中的关键字
        BTreeNode[] children; // 节点中的孩子
        int keyNumber; // 有效关键字的个数
        boolean leaf = true; // 当前节点是否是叶子节点
        int t; // 最小度数（孩子数）

        public BTreeNode(int t) {
            // 给最小度数赋初值，t>=2
            this.t = t >= 2 ? t : 2;
            // B树定义：最小度数 * 2 = 最多孩子数
            this.children = new BTreeNode[2*t];
            // 关键字数量也因此可以确定为2*t-1
            this.keys = new int[2*t-1];
        }

        public BTreeNode(int[] keys) {
            this.keys = keys;
        }

        @Override
        public String toString() {
            return Arrays.toString(Arrays.copyOfRange(keys, 0, keyNumber));
        }

        // 多路查找
        BTreeNode get(int key){
            int i = 0;
            while (i < keyNumber){
                if (keys[i] == key){
                    // 找到了
                    return this;
                } else if (keys[i] > key) {
                    break;
                }
                i++;
            }
            // 执行到此时 keys[i]>key 或 i==keyNumber
            if (leaf){
                // 没有孩子节点，且该节点也不匹配 -> 没有找到
                return null;
            }
            // 非叶子情况
            return children[i].get(key);
        }

        // 向 keys 指定索引处插入key
        void insertKey(int key, int index){
            System.arraycopy(keys, index, keys, index+1, keyNumber - index);

            keys[index] = key;
            keyNumber++;
        }


        // 向 children 指定索引处插入child
        void insertChildren(BTreeNode child, int index){
            System.arraycopy(children, index, children, index+1, keyNumber - index);
            children[index] = child;
        }

        /// 工具方法
        // 移除指定index的key
        int removeKey(int index){
            int t = keys[index];
            System.arraycopy(
                    keys, index+1, keys,index,--keyNumber-index
            );
            return t;
        }
        // 移除最左边的key
        int removeLeftmostKey(){
            return removeKey(0);
        }
        // 移除最右边的key
        int removeRightmostKey(){
            return removeKey(keyNumber-1);
        }

        // 移除指定index处的child
        BTreeNode removeChild(int index){
            BTreeNode removedChild = children[index];
            System.arraycopy(children,index+1,children,index,keyNumber-index);
            children[keyNumber]=null;
            return removedChild;
        }
        // 移除最左边的child
        BTreeNode removeLeftmostChild(){
            return removeChild(0);
        }
        // 移除最右边的child
        BTreeNode removeRightmostChild(){
            return removeChild(keyNumber);
        }

        // index 孩子处左边的兄弟
        BTreeNode childLeftSibling(int index){
            return index > 0 ? children[index-1] : null;
        }
        // index 孩子处右边的兄弟
        BTreeNode childRightSibling(int index){
            return index == keyNumber ? null : children[index+1];
        }


        // 复制当前节点的所有key和child追加到target节点
        void moveToTarget(BTreeNode target){
            int sourceKeyNumber = this.keyNumber;
            int targetKeyNumber = target.keyNumber;

            if (!leaf){
                for (int i = 0; i <= sourceKeyNumber; i++) {
                    target.children[targetKeyNumber+i] = this.children[i];
                }
            }

            for (int i = 0; i < sourceKeyNumber; i++) {
                target.keys[target.keyNumber++] = this.keys[i];
            }
        }


    }

    BTreeNode root;
    int t; // 树中节点最小度数
    final int MIN_KEY_NUMBER; // 关键字数量最小值 - 最小key数目
    final int MAX_KEY_NUMBER; // 关键字数量最大值 - 最大key数目

    public BTree() {
        this(2);
    }

    public BTree(int t) {
        this.t = t;
        root = new BTreeNode(t);
        MAX_KEY_NUMBER = 2 * t -1;
        MIN_KEY_NUMBER = t-1;
    }

    // 1. 是否存在
    public  boolean contains(int key){
        return root.get(key) != null;
    }
    // 2. 新增
    public void put(int key){
        doPut(root, key, null, 0);
    }


    /**
     * @param node
     * @param key
     */
    private void doPut(BTreeNode node, int key, BTreeNode parent, int index) {
        int i = 0;
        while (i < node.keyNumber){
            /* 从有效的keys集合中依次遍历，
            （keys集合是升序排列）
            -- keys[i] < key 说明还没到合适位置
            -- keys[i] == key 说明找到相同key，走更新流程
            -- keys[i] > key 说明找到第一个比key大的keys[i]，进入children[i]继续进行查找
             */
            if (node.keys[i] == key){
                return; // 更新
            } else if (node.keys[i] > key) {
                break;
                // 找到了插入位置
            }else {
                // keys[i] < key，继续向后遍历
                i++;
            }
        }
        // keys[i] > key 说明找到第一个比key大的keys[i]，进入children[i]继续进行查找
        if (node.leaf){
            // 当前节点是叶子节点，没有孩子，可以直接插入
            node.insertKey(key,i);
        }else {
            // 当前节点有孩子，继续进入孩子节点进行查找
            doPut(node.children[i], key, node, i);
        }
        // xxx 如果当前节点key数到达上限，需要进行分裂
        if (node.keyNumber == MAX_KEY_NUMBER){
            split(node,parent,index);
        }
    }

    /**
     * @param left 被分裂节点
     * @param parent 父节点
     * @param index 被分裂节点在父节点的索引位置
     */
    public void split(BTreeNode left, BTreeNode parent, int index){

        if (parent == null){
            // 分裂的是根节点
            BTreeNode newRoot = new BTreeNode(t);
            newRoot.leaf = false;
            newRoot.insertChildren(left,0);
            this.root = newRoot;
            parent = newRoot;
        }

        // 创建right节点（分裂后大于当前left节点），把t以后的key和child拷贝到right节点
        // t-1处的key插入到parent的index处，index指left作为孩子时的索引
        // right节点作为parent的孩子插入到index+1处
        BTreeNode right = new BTreeNode(t);
        // 分裂出来的新节点和原先节点在同一层，因此leaf属性与原节点一致
        right.leaf = left.leaf;
        // 将原节点后半拷贝到right新节点
        System.arraycopy(left.keys, t, right.keys, 0, t-1);
        if (!left.leaf){
        System.arraycopy(left.children, t, right.children, 0, t);
        }

        left.keyNumber = t-1;
        right.keyNumber = t-1;
        // 中间的key(t-1)插入到父节点中
        int middle = left.keys[t - 1];
        parent.insertKey(middle, index);
        // right节点作为父节点的孩子
         parent.insertChildren(right,index+1);


    }



    // 3. 删除
    public void remove(int key){
        doRemove(null,root,0,key);
    }

    /**
     * 递归删除
     * @param parent 当前节点的父节点
     * @param node 当前节点
     * @param index 当前节点在父节点的索引位置
     * @param key 需要删除的key
     */
    private void doRemove(BTreeNode parent, BTreeNode node, int index, int key){
        int i = 0;
        while (i < node.keyNumber){
            if (node.keys[i] >= key){
                // 找到了 1) 找到了key 2）找到了key所在的孩子节点
                break;
            }
            i++;
        }
        if (node.leaf){
            // 叶子节点
            if (!found(node, key, i)){
                // case1：当前节点是叶子节点，没找到key
                return;
            }else {
                // case2：当前节点是叶子节点，找到了key
                node.removeKey(i);
            }
        }else{
            // 非叶子节点
            if (!found(node, key, i)){
                // case3：当前节点不是叶子节点，没找到key
                // 到第i个孩子继续查找
                doRemove(node,node.children[i],i,key);
            }else {
                // case4：当前节点不是叶子节点，找到了key
                // 用当前节点的后继节点代替当前节点
                BTreeNode nextNode = node.children[i+1];
                while (!nextNode.leaf){
                    nextNode = nextNode.children[0];
                }
                // 找到了后继key
                int nextKey = nextNode.keys[0];
                // 替换
                node.keys[i] = nextKey;
                // 删除后继key
                doRemove(node,node.children[i+1],i+1,nextKey);
                    // 为什么不用nextNode，是因为需要逐级进行balance，所以只能使用node.children[i+1]
            }

        }


        if (node.keyNumber < MIN_KEY_NUMBER){
            // case5 & 6：删除后key数目 < key数目下线 -- 不平衡 -- 需要调整
            balance(parent, node, index);
        }



    }

    private void balance(BTreeNode parent, BTreeNode curNode, int index){
        if (curNode == root){
            // case 6 root节点 -- root节点不受t下线的束缚，但是root节点的key不能一个都没有
            if (root.keyNumber == 0 && root.children[0] != null){
                // 由于case5的某系旋转or合并的操作 -- root节点没有key，但是有左孩子
                root = root.children[0];
            }
            // 全删完了，root节点没有key，也没有孩子 -- 啥也不用做
            return;
        }
        BTreeNode left = parent.childLeftSibling(index);
        BTreeNode right = parent.childRightSibling(index);
        if (left != null && left.keyNumber > MIN_KEY_NUMBER){
            // case 5.1 左边兄弟节点数富裕，可以通过右旋，增加右边节点的key数量
            // 1st：父节点的key[index-1]旋转到右孩子节点的key[0]
            int rightRotateKey = parent.keys[index - 1];
            curNode.insertKey(rightRotateKey,0);
            /* 左边兄弟节点因为少了一个key，所以孩子的数量也要跟着-1
            左兄弟节点的最右边的孩子要挂到右兄弟节点的最左边 */
            if (!left.leaf){
                BTreeNode removeRightmostChild = left.removeRightmostChild();
                curNode.insertChildren(removeRightmostChild,0);
            }
            // 2nd：左边兄弟节点的key[keyNumber-1]旋转到父节点
            int removeRightmostKey = left.removeRightmostKey();
            parent.keys[index-1] = removeRightmostKey;
            return;
        }
        if (right != null && right.keyNumber > MIN_KEY_NUMBER){
            // case 5.2 右边兄弟节点数富裕，可以通过左旋，增加左边节点的key数量
            // a） 父节点中后继key旋转下来
            int leftRotateKey = parent.keys[index];
            curNode.insertKey(leftRotateKey, curNode.keyNumber);
            // b） right中最小的孩子换爹
            if (!right.leaf){
                BTreeNode removeLeftmostChild = right.removeLeftmostChild();
                curNode.insertChildren(removeLeftmostChild, curNode.keyNumber);
            }
            // c） right中最小的key旋转上去
            int removeLeftmostKey = right.removeLeftmostKey();
            parent.keys[index] = removeLeftmostKey;
            return;
        }
        // case 5.3 两边节点都不够，将root节点、右兄弟节点全部合并到左兄弟节点，删除右兄弟节点
        if (left != null){
            // 有左兄弟节点，则向左兄弟节点进行合并
            // 将被调整节点从父节点中删除
            parent.removeChild(index); // 不用赋值给新索引，因为被删除的节点就是当前节点，curNode就是他的索引
            // 先合并父节点keys[index-1]到左兄弟节点的末尾
            left.insertKey(parent.removeKey(index-1), left.keyNumber);
            // 再合并被删除的右节点
            curNode.moveToTarget(left);
        }else {
            // 没有左兄弟节点，自己就是左兄弟节点。向自己合并
            // 移除右兄弟，向自己合并
            parent.removeChild(index + 1); // 不用赋值给新索引，因为被删除的当前节点就是right
            // 先合并父节点keys[index]到左兄弟节点的末尾
            curNode.insertKey(parent.removeKey(index), curNode.keyNumber);
            // 再合并被删除的右节点
            right.moveToTarget(curNode);
        }
    }

    private static boolean found(BTreeNode node, int key, int i) {
        return i < node.keyNumber && node.keys[i] == key;
    }


    public void travel() {
        doTravel(root);
    }

    public void doTravel(BTreeNode node) {
        if (node == null) {
            return;
        }
        int i = 0;
        for (; i < node.keyNumber; i++) {
            doTravel(node.children[i]);
            System.out.println(node.keys[i]);
        }
        doTravel(node.children[i]);
    }

}
