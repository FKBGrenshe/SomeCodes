package com.baseDataStructure.QueueStructure.PriorityQueueStructure;


import com.baseDataStructure.QueueStructure.ProrityQueueStructure.Entry;
import com.baseDataStructure.QueueStructure.ProrityQueueStructure.HeapPriorityQueue;
import com.baseDataStructure.QueueStructure.ProrityQueueStructure.Priority;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.annotation.Annotation;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @Author: Bingyu Chen
 * @CreateTime: 2025-08-08
 * @Description: 基于堆的优先级队列测试
 */
public class HeapPriorityQueueTest {


    @Test
    public void poll(){
        HeapPriorityQueue<Entry> queue = new HeapPriorityQueue<>(5);
        queue.offer(new Entry("task1",2));
        queue.offer(new Entry("task2",3));
        queue.offer(new Entry("task3",4));
        queue.offer(new Entry("task4",5));
        queue.offer(new Entry("task5",1));
        Assertions.assertFalse(queue.offer(new Entry("task6",7)));
        System.out.println("offer success!");

        Assertions.assertEquals(5, queue.poll().priority);
        Assertions.assertEquals(4, queue.poll().priority);
        Assertions.assertEquals(3, queue.poll().priority);
        Assertions.assertEquals(2, queue.poll().priority);
        Assertions.assertEquals(1, queue.poll().priority);
    }



}
