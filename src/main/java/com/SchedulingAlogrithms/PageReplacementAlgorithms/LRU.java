package com.SchedulingAlogrithms.PageReplacementAlgorithms;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * @Author: Bingyu Chen
 * @CreateTime: 2025-10-19
 * @Description: LRU 最近最久未使用
 */
public class LRU implements PageRepalcementPolicy {

    private final int capacity;
    // 存储页号，对象
    private final LinkedHashMap<Integer,Page> cache;

    public LRU(int capacity) {
        this.capacity = capacity;
        this.cache = new LinkedHashMap<>(capacity,0.75f,true);
    }

    @Override
    public void accessPage(int pageId) {
        if (cache.containsKey(pageId)) cache.get(pageId);
        else {
            // 缺页：若已满 -> 移除最久未使用的页面
            if (cache.size() >= capacity) {
                Page victimPage = cache.remove(this.getVictimId());
                System.out.println("LRU 淘汰页面："+victimPage.toString());
            }
            cache.put(pageId,new Page(pageId));
        }

    }

    @Override
    public void removePage(int pageId) {
        cache.remove(pageId);
    }

    @Override
    public Page getVictim() {
        if (cache.isEmpty()) return null;
        Integer victimKey = cache.keySet().iterator().next();
        return cache.get(victimKey);
    }

    public Integer getVictimId(){
        if (cache.isEmpty()) return null;
        return cache.keySet().iterator().next();
    }

    @Override
    public boolean contains(int pageId) {
        return cache.containsKey(pageId);
    }

    @Override
    public int size() {
        return cache.size();
    }

    @Override
    public String currentState() {
        return cache.keySet().toString();
    }
}
