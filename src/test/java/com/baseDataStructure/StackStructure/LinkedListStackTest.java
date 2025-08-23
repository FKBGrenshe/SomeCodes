package com.baseDataStructure.StackStructure;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * @Author: Bingyu Chen
 * @CreateTime: 2025-08-04
 * @Description: 基于链表的栈测试
 */
public class LinkedListStackTest {

    @Test
    void test(){

        LinkedListStack<Integer> stack = new LinkedListStack<Integer>(3);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        Assertions.assertFalse(stack.push(4));

        Assertions.assertIterableEquals(List.of(3,2,1),stack);

        Assertions.assertEquals(3, stack.pop());
        stack.pop();
        stack.pop();

    }



}
