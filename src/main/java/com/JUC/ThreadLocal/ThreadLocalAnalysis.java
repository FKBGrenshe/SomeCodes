package com.JUC.ThreadLocal;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Bingyu Chen
 * @CreateTime: 2025-10-17
 * @Description: 线程副本
 */
public class ThreadLocalAnalysis {

    private List<String> messages = new ArrayList<>();
    public static final ThreadLocal<ThreadLocalAnalysis> holder = ThreadLocal.withInitial(ThreadLocalAnalysis::new);

    public static void add(String message){
        holder.get().messages.add(message);
    }

    public static List<String> clear(){
        List<String> messages = holder.get().messages;
        holder.remove();

        System.out.println("size: "+holder.get().messages.size());
        return messages;
    }

    public static void main(String[] args) {
        ThreadLocalAnalysis.add("cby");
        System.out.println(holder.get().messages);
        ThreadLocalAnalysis.clear();
    }

}
