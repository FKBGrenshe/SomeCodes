package com.SchedulingAlogrithms.PageReplacementAlgorithms;

import java.util.ArrayList;
import java.util.function.Predicate;

/**
 * @Author: Bingyu Chen
 * @CreateTime: 2025-10-18
 * @Description: 模拟内存类
 */
public class Memory {

    public int capacity;                   // 内存容量
    public ArrayList<Page> frames;         // 当前在内存中的页面
    public int time;                       // 当前时间（模拟访问步数）
    public int totalTimeCost;              // 累积的时间消耗
    public int pageFaults;                 // 缺页次数
    public PageRepalcementPolicy policy;   // 置换策略
    public ArrayList<Page> pages;          // 磁盘中的所有会访问到的页面

    public void setPolicy(PageRepalcementPolicy policy) {
        this.policy = policy;
    }

    public Memory(int capacity) {
        this.capacity = capacity;
        this.frames = new ArrayList<>();
        this.time = 0;
        this.totalTimeCost = 0;
        this.pageFaults = 0;
    }


    /**
     * 换入换出页面
     * @param pageId
     */
    public void accessPage(int pageId){
        Page needPage = pages.stream().filter(page -> page.id == pageId).findFirst().get();
        // 当前内存中是否存在该页面
        if (!containPage(pageId)) {
            // 产生缺页中断
            System.out.println("产生缺页中断："+pageId);
            System.out.println("当前内存中页面："+frames.toString());
            // 换入换出页面
            Page removePage = policy.apply(this.frames,pageId);
            removePage.timeZero();
            int removeIndex = frames.indexOf(removePage);
            frames.set(removeIndex,needPage);
            // 换入换出页面时间消耗
            time++;
            totalTimeCost += time;
            pageFaults++;
            System.out.println("换出页面："+removePage.id);
            System.out.println("当前时间："+time+"累计时间消耗:"+totalTimeCost+"缺页次数："+pageFaults);
        }
    }

    /**
     * 查看某页是否在内存中
     * @param pageId
     * @return
     */
    public boolean containPage(int pageId){
        boolean anyMatch = this.frames.stream().anyMatch(new Predicate<Page>() {
            @Override
            public boolean test(Page page) {
                return page.id == pageId;
            }
        });
        return anyMatch;
    }

    /**
     * 将页面放置进入内存
     * @param pageId
     * @return
     */
    public void putPage(int pageId){
        // 如果内存中已经存在 -> 直接访问
        if (!containPage(pageId)) {
            if (memoryIsFull()){
                // 内存已满 -> 进行换入换出
                accessPage(pageId);
            }else {
                // 内存未满 -> 直接放入内存中
                Page fromDisk = findFromDisk(pageId);
                frames.add(fromDisk);
            }
        }
    }

    // 从磁盘中找出页面
    public Page findFromDisk(int pageId){
        Page page = pages.stream().filter(p -> p.id == pageId).findFirst().get();
        return page;
    }

    // 判断内存是否已满
    public boolean memoryIsFull(){
        return frames.size() == capacity;
    }


    public void start(){
        int totalPageCount = 10;
        ArrayList<Page> totalPages = Page.totalPages(totalPageCount);
        this.pages = totalPages;
        int[] visitPages = Page.visitPages(30,totalPages);

        for (int i = 0; i < visitPages.length; i++) {
            // 每一轮访问，内存中所有页面的时间++
            for (Page page : frames) {
                page.timeEncr();
            }
            // 进行当前访问
            putPage(visitPages[i]);
        }
    }

    public static void main(String[] args) {
        Memory memory = new Memory(3);
        memory.setPolicy(new FIFO());
        memory.start();
    }

}
