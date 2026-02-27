package com.JUC.ThreadLocal;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Bingyu Chen
 * @CreateTime: 2026-02-27
 * @Description:
 */
public class ThreadLocalTest {

    private List<String> messages = new ArrayList<>();

    public static final ThreadLocal<ThreadLocalTest> holder = ThreadLocal.withInitial(ThreadLocalTest::new);

    public static void add(String message){
        holder.get().messages.add(message);
    }

    public static List<String> clear(){
        List<String> messages = holder.get().messages;
        holder.remove();

        System.out.println("after remove:" + holder.get().messages.size());
        return messages;
    }

    public static void main(String[] args) {
        ThreadLocalTest.add("11111");
        System.out.println(holder.get().messages);
        ThreadLocalTest.clear();
        System.out.println(holder.get().messages);
    }

}
