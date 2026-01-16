package com.OS.PageReplacementAlgorithm;

import org.springframework.data.relational.core.sql.In;

import java.util.HashMap;

/**
 * @Author: Bingyu Chen
 * @CreateTime: 2026-01-15
 * @Description: LFU缓存
 */
public class LFUCache {

    /* 双哈希表
    1. 频率哈希表 frequencyMap：{频率，该频率下的Node链表DoubleLinkedList}
    2. 正常哈希表 NodeMap：{Key，Node}
     */

    class LFUNode{
        int key;
        int value;
        int frequency;

        LFUNode prev;
        LFUNode next;

        public LFUNode(int key, int value) {
            this.key = key;
            this.value = value;
            this.frequency = 1;
        }

        public LFUNode(int key, int value, LFUNode prev, LFUNode next) {
            this.key = key;
            this.value = value;
            this.prev = prev;
            this.next = next;
        }
    }

    class DoubleLinkedList{
        int capacity;
        int size;
        LFUNode DLLHead, DLLTail;

        public DoubleLinkedList(int capacity) {
            this.capacity = capacity;
            this.size = 0;
            this.DLLHead = new LFUNode(-1,-1,null,DLLTail);
            this.DLLTail = new LFUNode(-1,-1,DLLHead,null);
        }

        public void mov2Head(LFUNode curNode){

        }

        public void delNode(){

        }

        public void delTail(){

        }
    }


}
