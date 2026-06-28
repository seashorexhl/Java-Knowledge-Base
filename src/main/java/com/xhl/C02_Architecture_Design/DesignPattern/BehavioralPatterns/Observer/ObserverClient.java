package com.xhl.Architecture_Design.DesignPattern.BehavioralPatterns.Observer;

// 5. 客户端调用
public class ObserverClient {
    public static void main(String[] args) {
        Stock stock = new Stock();
        Observer investor1 = new Investor("投资者A");
        Observer investor2 = new Investor("投资者B");

        stock.registerObserver(investor1);
        stock.registerObserver(investor2);
        stock.setPrice(105.5); // 触发通知
    }
}
