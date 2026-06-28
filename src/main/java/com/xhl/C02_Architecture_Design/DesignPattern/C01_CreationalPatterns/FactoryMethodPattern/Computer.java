package com.xhl.C02_Architecture_Design.DesignPattern.C01_CreationalPatterns.FactoryMethodPattern;

/**
 * @Author: xhl
 * @Date: 2026-06-13 00:24
 * @Description:  工厂模式
 * 工厂方法模式定义了一个创建对象的接口，但将具体的实例化延迟到子类，遵循开闭原则，方便新增产品。
 */
// 1. 抽象产品
public interface Computer {
    void displayInfo();
}

// 2. 具体产品
class DesktopComputer implements Computer {
    @Override
    public void displayInfo() {
        System.out.println("这是一台台式电脑");
    }
}
class LaptopComputer implements Computer {
    @Override
    public void displayInfo() {
        System.out.println("这是一台笔记本电脑");
    }
}

// 4. 具体工厂
class DesktopComputerFactory implements ComputerFactory {
    @Override
    public Computer createComputer() {
        return new DesktopComputer();
    }
}
class LaptopComputerFactory implements ComputerFactory {
    @Override
    public Computer createComputer() {
        return new LaptopComputer();
    }
}

