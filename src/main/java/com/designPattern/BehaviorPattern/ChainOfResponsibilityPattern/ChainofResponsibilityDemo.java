package com.designPattern.BehaviorPattern.ChainOfResponsibilityPattern;

/**
 * @Author: Bingyu Chen
 * @CreateTime: 2026-03-01
 * @Description: 行为型-责任链模式demo
 */
public class ChainofResponsibilityDemo {

    // 请求对象
    static class LeaveRequest{
        final String name;
        final int days;
        final String reason;

        public LeaveRequest(String name, int days, String reason) {
            this.name = name;
            this.days = days;
            this.reason = reason;
        }
    }

    // 处理者抽象类
    static abstract class Handler{
        private Handler next;

        public Handler setNext(Handler next) {
            this.next = next;
            return next; // 方便链式拼接
        }

        public final void handle(LeaveRequest req){
            if (canHandle(req)){
                doHandle(req);
            } else if (next != null) {
                next.handle(req);
            }else {
                System.out.println("[Result] No handler can process the request: " + req.days + " days");
            }
        }

        protected abstract boolean canHandle(LeaveRequest request);
        protected abstract void doHandle(LeaveRequest request);
    }

    // 具体处理器
    static class TeamLeader extends Handler{

        @Override
        protected boolean canHandle(LeaveRequest request) {
            return request.days <= 1;
        }

        @Override
        protected void doHandle(LeaveRequest req) {
            System.out.println("[TeamLead] Approved " + req.name + " for " + req.days + " day(s). Reason: " + req.reason);
        }
    }

    // 具体处理者：经理
    static class Manager extends Handler {
        @Override
        protected boolean canHandle(LeaveRequest req) {
            return req.days <= 3;
        }

        @Override
        protected void doHandle(LeaveRequest req) {
            System.out.println("[Manager] Approved " + req.name + " for " + req.days + " day(s). Reason: " + req.reason);
        }
    }

    // 具体处理者：HR
    static class HR extends Handler {
        @Override
        protected boolean canHandle(LeaveRequest req) {
            return req.days > 3; // 兜底
        }

        @Override
        protected void doHandle(LeaveRequest req) {
            System.out.println("[HR] Approved " + req.name + " for " + req.days + " day(s). Reason: " + req.reason);
        }
    }


    public static void main(String[] args) {
        // 组装责任链：组长 -> 经理 -> HR
        TeamLeader chain = new TeamLeader();
        chain.setNext(new Manager()).setNext(new HR());

        chain.handle(new LeaveRequest("Alice", 1, "Personal"));
        chain.handle(new LeaveRequest("Bob", 3, "Sick"));
        chain.handle(new LeaveRequest("Cathy", 7, "Travel"));
    }



}
