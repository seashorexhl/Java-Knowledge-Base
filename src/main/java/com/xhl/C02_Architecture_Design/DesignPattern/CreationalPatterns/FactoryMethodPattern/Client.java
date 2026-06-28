package com.xhl.C02_Architecture_Design.DesignPattern.CreationalPatterns.FactoryMethodPattern;

// 5. 客户端调用
public class Client {
    public static void main(String[] args) {
        ComputerFactory factory = new LaptopComputerFactory();
        Computer computer = factory.createComputer();
        computer.displayInfo(); // 输出：这是一台笔记本电脑
    }
}
