package com.xhl.Architecture_Design.DesignPattern.BehavioralPatterns.Observer;

/**
 * @Author: xhl
 * @Date: 2026-06-13 00:28
 * @Description: 观察者模式（以股票价格通知为例）
 * 观察者模式定义了对象间的一对多依赖，当一个对象状态改变时，所有依赖它的对象都会收到通知并自动更新，
 * 实现了发布-订阅的解耦。
 */
import java.util.ArrayList;
import java.util.List;

// 1. 观察者接口
public interface Observer {
    void update(double newPrice);
}

// 3. 具体被观察者
class Stock implements Subject {
    private List<Observer> observers = new ArrayList<>();
    private double price;

    @Override
    public void registerObserver(Observer observer) { observers.add(observer); }
    @Override
    public void unregisterObserver(Observer observer) { observers.remove(observer); }
    @Override
    public void notifyObservers(double newPrice) {
        for (Observer observer : observers) { observer.update(newPrice); }
    }
    public void setPrice(double price) {
        this.price = price;
        notifyObservers(price); // 状态变更，触发通知
    }
}

// 4. 具体观察者
class Investor implements Observer {
    private String name;
    public Investor(String name) { this.name = name; }
    @Override
    public void update(double newPrice) {
        System.out.println(name + " 收到通知，最新股价为: " + newPrice);
    }
}

