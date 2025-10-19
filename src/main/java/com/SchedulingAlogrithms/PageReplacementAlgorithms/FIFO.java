package com.SchedulingAlogrithms.PageReplacementAlgorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author: Bingyu Chen
 * @CreateTime: 2025-10-18
 * @Description: 先进先出页面置换
 */
public class FIFO implements PageRepalcementPolicy{

    private final int capacity;
    private final Queue<Page> queue = new LinkedList<>();

    public FIFO(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public void accessPage(int pageId) {
        if (contains(pageId)) return;
        if (queue.size() >= capacity) queue.poll();
        queue.offer(new Page(pageId));
    }

    @Override
    public void removePage(int pageId) {
        queue.removeIf(p->p.id == pageId);
    }

    @Override
    public Page getVictim() {
        return queue.peek();
    }

    @Override
    public boolean contains(int pageId) {
        return queue.stream().anyMatch(p -> p.id == pageId);
    }

    @Override
    public int size() {
        return queue.size();
    }

    @Override
    public String currentState() {
        return queue.toString();
    }
}
