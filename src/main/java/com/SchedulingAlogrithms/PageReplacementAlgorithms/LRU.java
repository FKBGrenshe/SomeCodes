package com.SchedulingAlogrithms.PageReplacementAlgorithms;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * @Author: Bingyu Chen
 * @CreateTime: 2025-10-19
 * @Description: LRU 最近最久未使用
 */
public class LRU implements PageRepalcementPolicy {

    /*内存容量*/
    private final int capacity;
    /*LinkedHashmap：自动维护访问顺序（accessOrder = true）*/
    private final LinkedHashMap<Integer,Page> cache;

    public LRU(int capacity){
        this.capacity = capacity;
        /*accessOrder=true表示每次访问都将元素移动到末尾*/
        this.cache = new LinkedHashMap<>(capacity, 0.75f, true);
    }

    @Override
    public Page apply(ArrayList<Page> frame, int needPageId) {

        // 如果命中（cache中存在），自动更新访问顺序
        if (cache.containsKey(needPageId)){
            return cache.get(needPageId); // 访问操作会将其移动到链表末尾
        }

        // 缺页
        if (cache.size() >= capacity){
            // 淘汰最久未使用的页面（LinkedHashMap的第一个元素）
            Integer victimKey = cache.keySet().iterator().next();
            cache.remove(victimKey);
            frame.removeIf(p -> p.id == victimKey);
        }

        // 加入新页面



        return null;
    }
}
