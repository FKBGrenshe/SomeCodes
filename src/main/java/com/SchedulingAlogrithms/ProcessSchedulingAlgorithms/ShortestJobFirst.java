package com.SchedulingAlogrithms.ProcessSchedulingAlgorithms;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

/**
 * @Author: Bingyu Chen
 * @CreateTime: 2025-10-18
 * @Description: 最短作业优先
 */
public class ShortestJobFirst {

    public static void main(String[] args) {
        ArrayList<Process> processArrayList = Process.processArrayList(4);

//        ArrayList<Process> done = new ArrayList<>();
        int time = 0;
        List<Process> sortedList = new ArrayList<>(processArrayList.stream().sorted(Comparator.comparingInt(p -> p.burstTime)).toList());

        while (!sortedList.isEmpty()){

            Process next = sortedList.remove(0);
            next.waitingTime = time - next.arrivalTime;
            time += next.burstTime;
            next.turnaroundTime = next.waitingTime + next.burstTime;
            next.run();
        }

    }


}
