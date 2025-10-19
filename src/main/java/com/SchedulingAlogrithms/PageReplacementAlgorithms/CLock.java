package com.SchedulingAlogrithms.PageReplacementAlgorithms;

import java.util.ArrayList;

/**
 * @Author: Bingyu Chen
 * @CreateTime: 2025-10-19
 * @Description: 时钟页面置换算法
 */
public class CLock implements PageRepalcementPolicy {

    private final int capcacity;            // 内存容量
    private final ArrayList<Page> frames;   // 内存
    private final int[] refBits;            // 引用位数组
    private int pointer = 0;                // 时钟指针

    public CLock(int capcacity) {
        this.capcacity = capcacity;
        this.frames = new ArrayList<>();
        this.refBits = new int[capcacity];
    }

    /**
     * 访问页面
     * 若访问命中 - 将引用位置1
     * 若未命中 - 调用替换流程
     * @param pageId
     */
    @Override
    public void accessPage(int pageId) {
        for (int i = 0; i < frames.size(); i++) {
            if (frames.get(i).id == pageId){
                refBits[i] = 1;
                return;
            }
        }
        // 发生缺页中断
        if (frames.size() < capcacity){
            // 内存未满，可以填入
            frames.add(new Page(pageId));
            refBits[frames.size() - 1] = 1;
        }else {
            // 内存已满，找替换页
            while (true){
                if (refBits[pointer] == 0){
                    Page victimPage = frames.get(pointer);
                    System.out.println("CLOCK淘汰："+victimPage.toString());
                    frames.set(pointer, new Page(pageId));
                    refBits[pointer] = 1;
                    pointer = (pointer+1)%capcacity;
                    break;
                }else {
                    refBits[pointer] = 0;
                    pointer = (pointer+1)%capcacity;
                }
            }
        }
    }

    @Override
    public void removePage(int pageId) {
        frames.removeIf( p -> p.id == pageId);
    }

    @Override
    public Page getVictim() {
        // 返回但钱指针指向的潜在淘汰页
        if (frames.isEmpty()) return null;
        return frames.get(pointer);
    }

    @Override
    public boolean contains(int pageId) {
        return frames.stream().anyMatch(p -> p.id == pageId);
    }

    @Override
    public int size() {
        return frames.size();
    }

    @Override
    public String currentState() {
        return frames.toString();
    }
}
