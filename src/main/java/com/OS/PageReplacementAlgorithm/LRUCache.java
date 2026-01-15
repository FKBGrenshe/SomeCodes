package com.OS.PageReplacementAlgorithm;


import java.util.HashMap;

/**
 * @Author: Bingyu Chen
 * @CreateTime: 2026-01-06
 * @Description: LRU缓存
 */
public class LRUCache {

    // 内存节点
    class Node{
        int key;
        int value;

        Node prev;
        Node next;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    // 属性
    int capacity;
    Node head, tail;

    // 查找O(1)缓存
    HashMap<Integer, Node> hashMap;

    public LRUCache(int capacity){
        this.capacity = capacity;
        head = new Node(-1,-1);
        tail = new Node(-2,-2);
        head.next = tail;
        tail.prev = head;
        hashMap = new HashMap<>(capacity);
    }

    public int get(int key){
        Node node = hashMap.get(key);
        if (node == null) return -1;
        put2Head(node);
        return node.value;
    }

    public void put(int key, int value){
        Node node = hashMap.get(key);
        if (node!=null){
            put2Head(node);
            node.value=value;
            return;
        }
        Node newNode = new Node(key, value);
        if (isFull()) {
            deleteTail();
        }
        put2Head(newNode);
        hashMap.put(key,newNode);
    }

    // LRUcache是否为空
    boolean isEmpty(){
        return hashMap.isEmpty();
    }
    // LRUcache是否已满
    boolean isFull(){
        return hashMap.size()==capacity;
    }

    // 将队内任意一个Node，重新放到队列头部
    void put2Head(Node curNode){
        // 从队列中摘出当前Node
        if (curNode.prev != null){
            curNode.prev.next = curNode.next;
            curNode.next.prev = curNode.prev;
        }
        // 将当前Node插入到队列头
        curNode.prev = head;
        curNode.next = head.next;
        head.next = curNode;
        curNode.next.prev = curNode;
    }

    // 删除队尾Node
    boolean deleteTail(){
        Node delNode = tail.prev;
        if (delNode != head){
            delNode.prev.next = tail;
            tail.prev = delNode.prev;
            delNode.prev = null;
            delNode.next = null;

            hashMap.remove(delNode.key);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        Node p = head;
        while (p != tail){
            sb.append("->("+p.key+":"+p.value+")");
            p = p.next;
        }

        return "LRUCache{" +
                "size=" + hashMap.size() + "::" + sb.toString()+
                '}';
    }


    public static void main(String[] args) {
        LRUCache lRUCache = new LRUCache(2);
        System.out.println(lRUCache.toString());
        lRUCache.put(1, 1); // 缓存是 {1=1}
        System.out.println(lRUCache.toString());
        lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
        System.out.println(lRUCache.toString());
        System.out.println(lRUCache.get(1));    // 返回 1
        System.out.println(lRUCache.toString());
        lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
        System.out.println(lRUCache.toString());
        System.out.println(lRUCache.get(2));    // 返回 -1 (未找到)
        System.out.println(lRUCache.toString());
        lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
        System.out.println(lRUCache.toString());
        System.out.println(lRUCache.get(1));    // 返回 -1 (未找到)
        System.out.println(lRUCache.toString());
        System.out.println(lRUCache.get(3));    // 返回 3
        System.out.println(lRUCache.toString());
        System.out.println(lRUCache.get(4));    // 返回 4
        System.out.println(lRUCache.toString());
    }

}

