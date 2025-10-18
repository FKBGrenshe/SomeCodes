package com.SchedulingAlogrithms.ProcessSchedulingAlgorithms;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @Author: Bingyu Chen
 * @CreateTime: 2025-10-18
 * @Description: 先来先服务
 */
public class FirstComeFirstServerd {

    public static void main(String[] args) {
        List<Process> list = Arrays.asList(
                new Process("P1", 0, 4, 2),
                new Process("P2", 1, 3, 1),
                new Process("P3", 2, 1, 3)
        );

        // 按到达时间排队
        list.sort(Comparator.comparingInt(p -> p.arrivalTime));
        int currentTime = 0;
        for (Process p : list) {
            if (currentTime < p.arrivalTime){
                currentTime = p.arrivalTime;
            }
            p.waitingTime = currentTime - p.arrivalTime;
            p.run();
            currentTime += p.burstTime;
            p.turnaroundTime = p.waitingTime + p.burstTime;
        }

        System.out.println("== FCFS 调度结果 ==");
        for (Process p : list)
            System.out.printf("%s 等待:%d 周转:%d%n", p.id, p.waitingTime, p.turnaroundTime);
    }

}
