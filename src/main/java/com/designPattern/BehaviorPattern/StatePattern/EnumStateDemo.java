package com.designPattern.BehaviorPattern.StatePattern;

/**
 * @Author: Bingyu Chen
 * @CreateTime: 2026-03-01
 * @Description: 状态模式demo
 */
import java.util.EnumMap;
import java.util.Map;

public class EnumStateDemo {

    enum Event { PAY, SHIP, DELIVER, CANCEL }

    static class Order {
        String id;
        State state = State.PENDING_PAYMENT;
        Order(String id) { this.id = id; }
    }

    enum State {
        PENDING_PAYMENT {
            @Override State on(Order o, Event e) {
                return switch (e) {
                    case PAY -> { System.out.println("charge " + o.id); yield PAID; }
                    case CANCEL -> { System.out.println("cancel " + o.id); yield CANCELED; }
                    default -> reject(o, e);
                };
            }
        },
        PAID {
            @Override State on(Order o, Event e) {
                return switch (e) {
                    case SHIP -> { System.out.println("ship " + o.id); yield SHIPPED; }
                    default -> reject(o, e);
                };
            }
        },
        SHIPPED {
            @Override State on(Order o, Event e) {
                return switch (e) {
                    case DELIVER -> { System.out.println("deliver " + o.id); yield COMPLETED; }
                    default -> reject(o, e);
                };
            }
        },
        COMPLETED {
            @Override State on(Order o, Event e) { return reject(o, e); }
        },
        CANCELED {
            @Override State on(Order o, Event e) { return reject(o, e); }
        };

        abstract State on(Order o, Event e);

        static State reject(Order o, Event e) {
            System.out.println("[Rejected] " + o.state + " cannot handle " + e);
            return o.state; // 保持不变（或抛异常）
        }
    }

    public static void main(String[] args) {
        Order o = new Order("B2002");

        fire(o, Event.SHIP);
        fire(o, Event.PAY);
        fire(o, Event.SHIP);
        fire(o, Event.DELIVER);
        fire(o, Event.CANCEL);
    }

    static void fire(Order o, Event e) {
        State prev = o.state;
        o.state = o.state.on(o, e);
        if (o.state != prev) {
            System.out.println("[FSM] " + prev + " --" + e + "--> " + o.state);
        }
    }
}
