package com.xhl.C02_Architecture_Design.DesignPattern.C03_BehavioralPatterns.Observer;

// 2. 被观察者（主题）接口
public interface Subject {
    void registerObserver(Observer observer);
    void unregisterObserver(Observer observer);
    void notifyObservers(double newPrice);
}
