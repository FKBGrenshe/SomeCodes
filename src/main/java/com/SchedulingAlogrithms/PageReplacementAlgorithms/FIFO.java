package com.SchedulingAlogrithms.PageReplacementAlgorithms;

import java.util.ArrayList;

/**
 * @Author: Bingyu Chen
 * @CreateTime: 2025-10-18
 * @Description: 先进先出页面置换
 */
public class FIFO implements PageRepalcementPolicy{
    @Override
    public Page apply(ArrayList<Page> frame, int needPageId) {

        int oldestPageTime = Integer.MIN_VALUE;
        int oldestPageIndex = -1;
        int curPageTime = -1;
        for (int i = frame.size() - 1; i >= 0; i--) {
            curPageTime = frame.get(i).times;
            if (oldestPageTime <= curPageTime){
                oldestPageIndex = i;
                oldestPageTime = curPageTime;
            }
        }
        return frame.get(oldestPageIndex);
    }
}
