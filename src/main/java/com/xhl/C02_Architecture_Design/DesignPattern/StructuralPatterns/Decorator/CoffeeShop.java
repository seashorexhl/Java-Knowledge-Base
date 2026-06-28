package com.xhl.C02_Architecture_Design.DesignPattern.StructuralPatterns.Decorator;

// 5. 客户端调用
public class CoffeeShop {
    public static void main(String[] args) {
        Coffee order = new SimpleCoffee();
        order = new MilkDecorator(order); // 动态添加牛奶
        order = new SugarDecorator(order); // 动态添加糖
        System.out.println(order.getDescription() + " 总价: " + order.cost());
        // 输出：基础咖啡, 加牛奶, 加糖 总价: 13.0
    }
}
