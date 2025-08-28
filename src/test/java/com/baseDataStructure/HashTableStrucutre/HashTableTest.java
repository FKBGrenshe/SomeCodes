package com.baseDataStructure.HashTableStrucutre;

import com.baseDataStructure.HashTableStructure.HashTable;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * @Author: Bingyu Chen
 * @CreateTime: 2025-08-25
 * @Description: 哈希表的测试方法
 */
public class HashTableTest {


    @Test
    void putTest(){
        HashTable table = new HashTable();
        table.put(1, "zhang", "张三"); // 1 % 16 == 1
        table.put(17, "li", "李四");   // 17 % 16 == 1
        table.put(2, "wang", "王五");  // 2

        assertEquals(3, table.size);
        assertEquals("张三", table.table[1].value);
        assertEquals("李四", table.table[1].next.value);
        assertEquals("王五", table.table[2].value);

        table.put(1, "zhang", "张4");
        table.put(17, "li", "李5");
        assertEquals("张4", table.table[1].value);
        assertEquals("李5", table.table[1].next.value);
    }

    @Test
    void remove1() {
        HashTable table = new HashTable();
        table.put(1, "zhang", "张三");  // 1
        table.put(17, "li", "李四");    // 1
        table.put(2, "wang", "王五");

        table.remove(1, "zhang");
        assertEquals(2, table.size);
        assertEquals("李四", table.table[1].value);
        assertNull(table.table[1].next);
    }

    @Test
    void remove2() {
        HashTable table = new HashTable();
        table.put(1, "zhang", "张三"); // 1
        table.put(17, "li", "李四");   // 1
        table.put(2, "wang", "王五");

        table.remove(17, "li");
        assertEquals(2, table.size);
        assertEquals("张三", table.table[1].value);
        assertNull(table.table[1].next);
    }

}
