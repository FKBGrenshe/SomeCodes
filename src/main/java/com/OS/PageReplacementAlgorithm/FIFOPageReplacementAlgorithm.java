package com.OS.PageReplacementAlgorithm;

import com.SchedulingAlogrithms.PageReplacementAlgorithms.Page;
import com.SchedulingAlogrithms.PageReplacementAlgorithms.PageRepalcementPolicy;

/**
 * @Author: Bingyu Chen
 * @CreateTime: 2026-01-04
 * @Description: 基于先进先出的内存页面置换算法
 */
public class FIFOPageReplacementAlgorithm implements PageRepalcementPolicy {

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
