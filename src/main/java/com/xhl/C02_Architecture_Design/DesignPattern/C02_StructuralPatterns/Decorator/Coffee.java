package com.xhl.C02_Architecture_Design.DesignPattern.C02_StructuralPatterns.Decorator;

/**
 * @Author: xhl
 * @Date: 2026-06-13 00:27
 * @Description: 装饰者模式（以咖啡店订单系统为例）
 * 装饰者模式允许通过动态包装对象的方式为对象添加新功能，提供比继承更灵活的扩展方式，且遵循开闭原则。
 */
// 1. 组件接口
public interface Coffee {
    String getDescription();
    double cost();
}

// 2. 具体组件
class SimpleCoffee implements Coffee {
    @Override
    public String getDescription() { return "基础咖啡"; }
    @Override
    public double cost() { return 10.0; }
}

// 3. 装饰者基类
abstract class CoffeeDecorator implements Coffee {
    protected final Coffee decoratedCoffee;
    public CoffeeDecorator(Coffee coffee) { this.decoratedCoffee = coffee; }
    @Override
    public String getDescription() { return decoratedCoffee.getDescription(); }
    @Override
    public double cost() { return decoratedCoffee.cost(); }
}

// 4. 具体装饰者
class MilkDecorator extends CoffeeDecorator {
    public MilkDecorator(Coffee coffee) { super(coffee); }
    @Override
    public String getDescription() { return super.getDescription() + ", 加牛奶"; }
    @Override
    public double cost() { return super.cost() + 2.0; }
}
class SugarDecorator extends CoffeeDecorator {
    public SugarDecorator(Coffee coffee) { super(coffee); }
    @Override
    public String getDescription() { return super.getDescription() + ", 加糖"; }
    @Override
    public double cost() { return super.cost() + 1.0; }
}

