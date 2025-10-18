package com.SchedulingAlogrithms.ProcessSchedulingAlgorithms;

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

}

