package com.SchedulingAlogrithms.PageReplacementAlgorithms;

import java.util.ArrayList;
import java.util.Random;

/**
 * @Author: Bingyu Chen
 * @CreateTime: 2025-10-18
 * @Description: 公共页面定义
 */
public class Page {

    int id;
    int times;  // 在内存中的时间

    public Page(int id) {
        this.id = id;
        this.times = 0;
    }

    /**
     * 标记该页面在内存中时间增加
     */
    public void timeEncr(){
        times++;
    }

    // 页面被置换出内存，时间清零
    public void timeZero(){
        times = 0;
    }

    @Override
    public String toString() {
        return String.valueOf(id);
    }

    /**
     * 生成当前所有的页面
     * @return 页面list集合
     */
    public static ArrayList<Page> totalPages(int pageCount){

        ArrayList<Page> pages = new ArrayList<>();
        for (int i = 0; i < pageCount; i++) {
            pages.add(new Page(i));
        }
        return pages;
    }

    /**
     * 需要访问的页面的顺序
     * @param visitNumber 总共需要访问多少次页面
     * @param pages 所有页面集合
     * @return
     */
    public static int[] visitPages(int visitNumber, ArrayList<Page> pages){
        int[] visits = new int[visitNumber];
        Random random = new Random();

        // 模拟“局部性原理”：短时间内集中访问少量页面
        int currentIndex = random.nextInt(pages.size());
        for (int i = 0; i < visitNumber; i++) {
            // 每隔一段时间，有一定概率切换访问区域
            if (i % 5 == 0 && random.nextDouble() < 0.3) {
                currentIndex = random.nextInt(pages.size());
            }
            // 模拟在局部窗口中的访问
            int localOffset = random.nextInt(3) - 1; // -1, 0, 1
            int pageIndex = (currentIndex + localOffset + pages.size()) % pages.size();
            visits[i] = pages.get(pageIndex).id;
        }

        return visits;
    }

    public static int[] visitPages(int visitNumber, int maxPageId){
        int[] visits = new int[visitNumber];
        Random random = new Random();

        // 模拟“局部性原理”：短时间内集中访问少量页面
        int currentIndex = random.nextInt(maxPageId);
        for (int i = 0; i < visitNumber; i++) {
            // 每隔一段时间，有一定概率切换访问区域
            if (i % 3 == 0 && random.nextDouble() < 0.5) {
                currentIndex = random.nextInt(maxPageId);
            }
            // 模拟在局部窗口中的访问
//            int localOffset = random.nextInt(30) - 15; // -2, -1, 0, 1, 2
            int localOffset = random.nextInt(3) - 1; // -2, -1, 0, 1, 2
            int pageIndex = (currentIndex + localOffset + maxPageId) % maxPageId;
            visits[i] = pageIndex;
        }

        return visits;
    }

}
