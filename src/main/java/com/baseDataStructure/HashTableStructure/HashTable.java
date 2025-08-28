package com.baseDataStructure.HashTableStructure;

/**
 * @Author: Bingyu Chen
 * @CreateTime: 2025-08-25
 * @Description: 哈希表
 */
public class HashTable {

    // 节点类
    public static class Entry{
        int hash; // 哈希码
        Object key;
        public Object value; // 键值

        public Entry next; // next指针

        public Entry(int hash, Object key, Object value) {
            this.hash = hash;
            this.key = key;
            this.value = value;
        }
    }

    public Entry[] table = new Entry[1 << 4]; //  entry 数组用于存放entry
    public int size = 0; // 元素个数
    float loadFactor = 0.75f;
    int threshold = (int) (loadFactor*table.length);

    /*
    求模运算 <-- 替换 <-- 位运算
        1. 速度更快
        2. 要求：数组长度为2^n
        3. 方式：hash % 数组长度 === hash & (数组长度 - 1)
     */

    int getIndexByHash(int hash){
        return hash & (table.length - 1);
    }

    /**
     * 查询 -- 根据 哈希码 和 key 获取 value
     * @param hash
     * @param key
     * @return value
     */
    Object get(int hash, Object key){

        int index = getIndexByHash(hash);
        if (table[index] == null){
            return null;
        }
        Entry entry = table[index];
        while (entry != null){
            if (key.equals(entry.key)){
                return entry.value;
            }
            entry = entry.next;
        }
        return null;

    }

    /**
     * 新增 or 修改
     * @param hash
     * @param key
     * @param value
     */
    public void put(int hash, Object key, Object value){

        int index = getIndexByHash(hash);
        if (table[index] == null){
            table[index] = new Entry(hash,key,value);
        }else {
            Entry entry = table[index];
            Entry prv = null;
            while (entry != null){
                if (key.equals(entry.key)){
                    // 更新操作
                    entry.value = value;
                    return;
                }
                prv = entry;
                entry = entry.next;
            }
            // 新增
            Entry newEntry = new Entry(hash, key, value);
            prv.next = newEntry;
        }
        size++;
        if (size > threshold){
            resize();
        }

    }

    private void resize() {
        Entry[] newTable = new Entry[table.length << 1];
        for (int i = 0; i < table.length; i++) {
            Entry entry = table[i]; // 拿到哈希表的每个位置，该位置有可能只有一个值，也有可能是一个链表（有多个值需要重新放位置）
            if ( entry != null){
                // 拆分链表移动到新数组
                /*
                拆分规律
                     原来哈希表中某个位置的一个链表最多拆成新哈希表中两个位置的新链表
                        hash & table.length == 0 -- A链表 -- 在新哈希表中的位置 = 原来位置
                        hash & table.length != 0 -- B链表 -- 在新哈希表中的位置 = 原来位置 + 旧哈希表长度
                 */
                Entry newPointAHead = null; // a链表头指针
                Entry newPointATail = null; // a链表尾指针
                Entry newPointBHead = null; // b链表头指针
                Entry newPointBTail = null; // b链表尾指针
                while (entry != null){
                    // 逐一放置元素
                    if ((entry.hash & table.length) == 0){
                        if (newPointATail != null){
                            newPointATail.next = entry;
                        }else {
                            // 记录A链表头节点位置
                            newPointAHead = entry;
                        }
                        newPointATail = entry;
                    }else {
                        if (newPointBTail != null){
                            newPointBTail.next = entry;
                        }else {
                            // 记录B链表头节点位置
                            newPointBHead = entry;
                        }
                        newPointBTail = entry;
                    }
                    entry = entry.next;
                }

                //收尾工作
                if (newPointATail != null){
                    newPointATail.next = null;
                }
                if (newPointBTail != null){
                    newPointBTail.next = null;
                }

                //将AB两个链表挂到新哈希表上
                newTable[i] = newPointAHead;
                newTable[i+ table.length] = newPointBHead;

            }
        }

        table = newTable;
        threshold = (int)(loadFactor * table.length);
    }

    /**
     * 根据 哈希码 和 key 进行删除
     * @param hash
     * @param key
     * @return
     */
    public Object remove(int hash, Object key){
        int index = getIndexByHash(hash);
        if (table[index] == null){
            return null;
        }else {
            Entry entry = table[index];
            Entry prv = null;
            while (entry != null){
                if (key.equals(entry.key)){
                    // 找到了，进行删除
                    Object deletedValue = entry.value;
                    if (prv == null){
                        table[index] = entry.next;
                    }else {
                        prv.next = entry.next;
                    }
                    size--;
                    return deletedValue;
                }
                prv = entry;
                entry = entry.next;
            }
            // 没找到相应的key，无法删除
            return null;
        }
    }

    /*
    负载因子 一般为 0.75
        load factor =  n / m
                     哈希表元素个数 / 哈希表数组长度
     */







}
