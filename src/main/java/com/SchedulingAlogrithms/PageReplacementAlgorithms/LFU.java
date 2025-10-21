package com.SchedulingAlogrithms.PageReplacementAlgorithms;

/**
 * @Author: Bingyu Chen
 * @CreateTime: 2025-10-19
 * @Description: 最不经常使用页面置换算法
 */
public class LFU implements PageRepalcementPolicy{
    @Override
    public void accessPage(int pageId) {

    }

    @Override
    public void removePage(int pageId) {

    }

    @Override
    public Page getVictim() {
        return null;
    }

    @Override
    public boolean contains(int pageId) {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public String currentState() {
        return "";
    }
}
