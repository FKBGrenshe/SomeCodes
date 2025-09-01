package com.designPattern.StructurePattern.AdapterPattern;

/**
 * @Author: Bingyu Chen
 * @CreateTime: 2025-09-01
 * @Description: 客户端
 */
public class Client {

    public static void main(String[] args) {
        ServiceNew serviceNew = new ServiceNew() {
            @Override
            public void requestNew() {
                System.out.println("this is requestNew");
            }
        };
        ServiceNewAdapter serviceNewAdapter = new ServiceNewAdapter(serviceNew);
        // 调用的是老系统服务接口的方法
        serviceNewAdapter.request();


    }
}
