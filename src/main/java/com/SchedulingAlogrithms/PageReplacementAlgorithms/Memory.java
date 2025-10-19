package com.SchedulingAlogrithms.PageReplacementAlgorithms;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @Author: Bingyu Chen
 * @CreateTime: 2025-10-18
 * @Description: 模拟内存类（策略模式上下文 + 格式化输出）
 */
public class Memory {
    private final int capacity;                   // 内存容量
    private final PageRepalcementPolicy policy;   // 页面置换策略
    private int pageFaults = 0;                   // 缺页次数
    private int time = 0;                         // 模拟步数

    public Memory(int capacity, PageRepalcementPolicy policy) {
        this.capacity = capacity;
        this.policy = policy;
    }

    /**
     * 模拟访问序列
     */
    public double simulate(int[] accessSeq) {
        System.out.println("========== 页面置换模拟开始 ==========");
        System.out.printf("策略类型: %s, 内存容量: %d\n",
                policy.getClass().getSimpleName(), capacity);
        System.out.println("--------------------------------------");

        for (int pid : accessSeq) {
            time++;
            System.out.printf("第 %2d 步 -> 访问页面: %2d | ", time, pid);

            // 命中情况
            if (policy.contains(pid)) {
                System.out.printf("✅ 命中 | ");
                policy.accessPage(pid); // 更新访问状态（例如 LRU）
            }
            else {
                pageFaults++;
                System.out.printf("❌ 缺页 | ");

                if (policy.size() >= capacity) {
                    Page victim = policy.getVictim();
                    System.out.printf("淘汰页面: %2d | ", victim.id);
                    policy.removePage(victim.id);
                } else {
                    System.out.print("空位可用 | ");
                }

                // 加载新页面
                policy.accessPage(pid);
            }

            // 打印当前内存状态
            System.out.printf("当前内存: %s\n", policy.currentState());
        }

        System.out.println("--------------------------------------");
        double pageFaultRate = 100.0 * pageFaults / accessSeq.length;
        System.out.printf("总访问次数: %d, 缺页次数: %d, 缺页率: %.2f%%\n",
                accessSeq.length, pageFaults, pageFaultRate);
        System.out.println("=========== 模拟结束 ===========\n");

        return pageFaultRate;
    }

    /**
     * 测试入口
     */
    public static void main(String[] args) {

        int capacity = 5;

        // 创建 FIFO 策略
        Memory FIFOmemory = new Memory(capacity, new FIFO(capacity));
        // 创建 LRU策略
        Memory LRUmemory = new Memory(capacity, new LRU(capacity));
        // 创建 CLOCK 策略
        Memory CLOCKmemory = new Memory(capacity, new CLock(capacity));

        // 模拟访问序列
        int[] accessSeq = Page.visitPages(3000, Page.totalPages(1500));
        System.out.println("模拟访问序列:" + Arrays.toString(accessSeq));
        double FIFOpageFaultRate = FIFOmemory.simulate(accessSeq);
        double LRUpageFaultRate = LRUmemory.simulate(accessSeq);
        double CLOCKpageFaultRate = CLOCKmemory.simulate(accessSeq);

        System.out.println("FIFO - "+FIFOpageFaultRate);
        System.out.println("LRU - "+LRUpageFaultRate);
        System.out.println("CLOCK - "+CLOCKpageFaultRate);

    }
}
