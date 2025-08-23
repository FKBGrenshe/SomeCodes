package com.baseDataStructure.QueueStructure;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * @Author: Bingyu Chen
 * @CreateTime: 2025-08-04
 * @Description: 基于环形链表的队列测试
 */
public class ArrayQueue3Test {

    @Test
    void generic(){
        ArrayQueue3<String> queue = new ArrayQueue3<>(4);
        queue.offer("a");
        queue.offer("b");
        queue.offer("c");
        queue.offer("d");

        Assertions.assertIterableEquals(List.of("a","b","c","d"),queue);
    }

}
