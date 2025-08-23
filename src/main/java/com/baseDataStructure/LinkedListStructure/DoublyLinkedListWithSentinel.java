package com.baseDataStructure.LinkedListStructure;

/**
 * @Author: Bingyu Chen
 * @CreateTime: 2025-08-02
 * @Description: 双向环形链表带哨兵
 */
public class DoublyLinkedListWithSentinel {

    private static class Node{

        Node prev;
        Node next;
        int value;

        public Node(Node prev, Node next, int value) {
            this.prev = prev;
            this.next = next;
            this.value = value;
        }
    }

    private Node sentinel = new Node(null,null, -1);

    public DoublyLinkedListWithSentinel() {
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
    }

    public void addFirst(int value){
        Node newNode = new Node(null, null, value);
        newNode.next = sentinel.next;
        newNode.prev = sentinel;
        sentinel.next = newNode;
        sentinel.prev.prev = newNode;
    }



}
