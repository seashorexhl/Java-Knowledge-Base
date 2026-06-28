package com.xhl.Architecture_Design.DesignPattern.BehavioralPatterns.Observer;

// 2. 被观察者（主题）接口
public interface Subject {
    void registerObserver(Observer observer);
    void unregisterObserver(Observer observer);
    void notifyObservers(double newPrice);
}
