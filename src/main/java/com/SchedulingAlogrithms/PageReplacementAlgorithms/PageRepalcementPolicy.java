package com.SchedulingAlogrithms.PageReplacementAlgorithms;

import java.util.ArrayList;

public interface PageRepalcementPolicy {
//    Page apply(ArrayList<Page> frame, int needPageId);
    void accessPage(int pageId);    // 访问页面（命中or缺页）
    void removePage(int pageId);    // 手动移除页面（如：被换出）
    Page getVictim();               // 获取应该被换出的页面
    boolean contains(int pageId);   // 是否命中
    int size();                     // 当前缓存页数
    String currentState();          // 打印当前内存状态
}
