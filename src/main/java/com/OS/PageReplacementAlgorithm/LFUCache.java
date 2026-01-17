package com.OS.PageReplacementAlgorithm;

import com.SchedulingAlogrithms.PageReplacementAlgorithms.LFU;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @Author: Bingyu Chen
 * @CreateTime: 2026-01-15
 * @Description: LFU缓存
 */
public class LFUCache {

    // 保存所有Node的哈希表 <Key, LFUNode>
    Map<Integer, LFUNode> nodeMap;
    // 保存次数及当前使用次数下的List的哈希表
    Map<Integer, DoubleLinkedList> linkedListMap;
    // 整体容量
    int capacity;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        nodeMap = new HashMap<>(capacity);
        linkedListMap = new HashMap<>();
        linkedListMap.put(1,new DoubleLinkedList());
    }

    public int get(int key){
        // 先查找当前key是否有对应Node
        LFUNode curNode = nodeMap.get(key);
        if (Objects.isNull(curNode)){
            return -1;
        }
        int value = curNode.value;
        // 更新当前节点
        freshNode(curNode);

        return value;
    }

    /**
     * 每当put，get操作，更新当前Node
     * 1. 计数器+1
     * 2. 从当前使用次数队列中移出，挪进+1使用次数多队列
     * @param curNode
     */
    private void freshNode(LFUNode curNode) {
        int originalCNT = curNode.count;
        // cnt+1
        curNode.count++;
        // move from original DLK
        DoubleLinkedList originalDLK = linkedListMap.get(originalCNT);
        originalDLK.extractNodeFromDLK(curNode);
        // insert into new DLK
        DoubleLinkedList newDLK = linkedListMap.get(originalCNT + 1);
        if (Objects.isNull(newDLK)){
            // 创建新的DLK
            linkedListMap.put(originalCNT+1, new DoubleLinkedList());
            newDLK = linkedListMap.get(originalCNT + 1);
        }
        newDLK.insertNode2Head(curNode);
    }

    public void put(int key, int value){
        LFUNode curNode = nodeMap.get(key);
        if (Objects.isNull(curNode)){
            // 不存在该Node，走插入流程：如果空间已经满了，需要删除一个Node todo
            if (nodeMap.size()==capacity){
                int miniumCNT = 1;
                DoubleLinkedList initialDLK = linkedListMap.get(miniumCNT);
                while (initialDLK.isEmpty()){
                    initialDLK = linkedListMap.get(++miniumCNT);
                }
                // delete 流程
                LFUNode delNode = initialDLK.deleteTail();
                nodeMap.remove(delNode.key);
            }
            curNode = new LFUNode(key,value);
            nodeMap.put(key,curNode);
            linkedListMap.get(1).insertNode2Head(curNode);
        }else {
            // 当前Node存在，走更新流程
            curNode.value = value;
            freshNode(curNode);
        }
    }

    class LFUNode{
        int key, value, count;
        LFUNode prev, next;

        public LFUNode(int key, int value) {
            this.key = key;
            this.value = value;
            this.prev = null;
            this.next = null;
            this.count = 1;
        }

        public LFUNode(int value, int key, LFUNode prev, LFUNode next) {
            this.value = value;
            this.key = key;
            this.prev = prev;
            this.next = next;
            this.count = 1;
        }
    }
    class DoubleLinkedList{
        LFUNode head, tail;

        int size;

        public DoubleLinkedList(int size) {
            this.size = size;
            head = new LFUNode(-1,-1,null,tail);
            tail = new LFUNode(-1,-1,head,null);
            head.next = tail;
            tail.prev = head;
        }

        public DoubleLinkedList(){
            head = new LFUNode(-1,-1,null,tail);
            tail = new LFUNode(-1,-1,head,null);
            head.next = tail;
            tail.prev = head;
        }

        /**
         * 将新Node插入到队头
         * @param curNode
         */
        public void insertNode2Head(LFUNode curNode){
            curNode.prev = head;
            curNode.next = head.next;
            head.next.prev = curNode;
            head.next = curNode;
        }

        /**
         * 将当前Node从队列中拿出，删除连接关系
         * @param curNode
         */
        public void extractNodeFromDLK(LFUNode curNode){
            curNode.prev.next = curNode.next;
            curNode.next.prev = curNode.prev;
            curNode.next = null;
            curNode.prev = null;
        }

        /**
         * 将当前Node移动至队头
         * @param curNode
         */
        public void moveNode2Head(LFUNode curNode){
            extractNodeFromDLK(curNode);
            insertNode2Head(curNode);
        }

        public LFUNode deleteTail() {
            LFUNode delNode = tail.prev;
            if (delNode == head){
                throw new RuntimeException("算法出错！删除流程走入了空队列");
            }
            // 删除当前最久未使用Node节点
            extractNodeFromDLK(delNode);
            return delNode;
        }

        @Override
        public String toString() {

            StringBuilder sb = new StringBuilder();
            LFUNode p = head;
            while (p != tail){
                p = p.next;
                sb.append("-("+p.key+": "+p.value+")-");
            }

            return "{" +"size=" + size + "||"+
                    "head-"  +
                    sb.toString()+
                    "-tail"  +
                    '}';
        }

        public boolean isEmpty() {
            return head.next == tail;
        }
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        linkedListMap.forEach((key, dlk)->{
            String curStr = "UseCNT: "+key.toString()+" DLK: "+dlk.toString();
            sb.append("\n"+curStr);
        });

        return "LFUCache{" +
                "capacity=" + capacity + "\n" +
                "linkedListMap= \n" + sb.toString() +
                '}';
    }

    public static void main(String[] args) {
        // cnt(x) = 键 x 的使用计数
// cache=[] 将显示最后一次使用的顺序（最左边的元素是最近的）
        LFUCache lfu = new LFUCache(2);
        lfu.put(1, 1);   // cache=[1,_], cnt(1)=1
        System.out.println(lfu.toString());
        lfu.put(2, 2);   // cache=[2,1], cnt(2)=1, cnt(1)=1
        System.out.println(lfu.toString());
        lfu.get(1);      // 返回 1
        // cache=[1,2], cnt(2)=1, cnt(1)=2
        System.out.println(lfu.toString());
        lfu.put(3, 3);   // 去除键 2 ，因为 cnt(2)=1 ，使用计数最小
        // cache=[3,1], cnt(3)=1, cnt(1)=2
        System.out.println(lfu.toString());
        lfu.get(2);      // 返回 -1（未找到）
        System.out.println(lfu.toString());
        lfu.get(3);      // 返回 3
        // cache=[3,1], cnt(3)=2, cnt(1)=2
        System.out.println(lfu.toString());
        lfu.put(4, 4);   // 去除键 1 ，1 和 3 的 cnt 相同，但 1 最久未使用
        // cache=[4,3], cnt(4)=1, cnt(3)=2
        System.out.println(lfu.toString());
        lfu.get(1);      // 返回 -1（未找到）
        System.out.println(lfu.toString());
        lfu.get(3);      // 返回 3
        // cache=[3,4], cnt(4)=1, cnt(3)=3
        System.out.println(lfu.toString());
        lfu.get(4);      // 返回 4
        // cache=[3,4], cnt(4)=2, cnt(3)=3
        System.out.println(lfu.toString());
    }


}
