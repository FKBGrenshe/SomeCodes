package com.baseDataStructure.QueueStructure.test;

import com.baseDataStructure.QueueStructure.LinkedListQueue;

import java.util.Iterator;

/**
 * @Author: Bingyu Chen
 * @CreateTime: 2025-08-02
 * @Description:
 */
public class LinkedListQueueTest {

    public static void main(String[] args) {
        testOffer();

    }


    public static void testOffer(){
        LinkedListQueue<Integer> queue = new LinkedListQueue<>();
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        queue.offer(4);
        queue.offer(5);

        Iterator<Integer> iterator = queue.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }

        while (!queue.isEmpty()){
            Integer poll = queue.poll();
            System.out.println(poll);
        }
    }
}
