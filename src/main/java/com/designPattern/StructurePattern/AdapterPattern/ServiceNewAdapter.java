package com.designPattern.StructurePattern.AdapterPattern;

/**
 * @Author: Bingyu Chen
 * @CreateTime: 2025-09-01
 * @Description: 适配器类，让ServiceB看起来像ServiceA
 */
public class ServiceNewAdapter implements ServiceOld{

    private ServiceNew serviceNew;

    public ServiceNewAdapter(ServiceNew serviceNew) {
        this.serviceNew = serviceNew;
    }

    @Override
    public void request() {
        System.out.println("适配器将 新系统服务接口new 转换为 老系统服务接口 old");
        serviceNew.requestNew();
    }
}
