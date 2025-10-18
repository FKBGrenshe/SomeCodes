package com.SchedulingAlogrithms.ProcessSchedulingAlgorithms;

import java.util.ArrayList;
import java.util.Random;

/**
 * @Author: Bingyu Chen
 * @CreateTime: 2025-10-18
 * @Description: 通用进程类
 */
class Process {
    String id;        // 进程ID
    int arrivalTime;  // 到达时间
    int burstTime;    // 服务时间
    int priority;     // 优先级（越小越高）
    int remainingTime;
    int waitingTime;
    int turnaroundTime;

    public Process(String id, int arrivalTime, int burstTime, int priority) {
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.remainingTime = burstTime;
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "Process{" +
                "id='" + id + '\'' +
                ", arrivalTime=" + arrivalTime +
                ", burstTime=" + burstTime +
                ", priority=" + priority +
                ", remainingTime=" + remainingTime +
                ", waitingTime=" + waitingTime +
                ", turnaroundTime=" + turnaroundTime +
                '}';
    }

    public void run(){
        System.out.println("running..." + this.toString());
    }


    /**
     * 随机生成一批进程对象
     * @param count 进程数量
     * @return ArrayList<Process>
     */
    public static ArrayList<Process> processArrayList(int count) {
        ArrayList<Process> processes = new ArrayList<>();
        Random random = new Random();

        for (int i = 1; i <= count; i++) {
            String id = "P" + i;
            int arrivalTime = random.nextInt(10);      // 到达时间 [0, 9]
            int burstTime = random.nextInt(8) + 1;     // 执行时间 [1, 8]
            int priority = random.nextInt(5) + 1;      // 优先级 [1, 5]
            processes.add(new Process(id, arrivalTime, burstTime, priority));
        }

        System.out.println("已生成以下进程");
        for (Process p : processes) {
            System.out.println(p.toString());
        }
        System.out.println("生成完成");
        return processes;
    }

}

