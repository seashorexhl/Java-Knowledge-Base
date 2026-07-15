# DDD_DomainDrivenDesign 领域驱动设计
    
## DDD 模块下 内容
    核心建模（Domain）：
        实体（Entity）与值对象（Value Object）：对比两者的区别（比如 Order 是实体，Money 是值对象）。
        聚合根（Aggregate Root）：演示如何通过聚合根来保证业务规则的一致性（比如 Order.addProduct()
    内部校验库存，而不是在外部 Service 里写 if-else）。
    
    领域服务（Domain Service）：
        当某个业务逻辑不属于任何一个单一实体时，如何将其抽离为领域服务（例如 TransferService 处理两个
    账户间的转账）。
    
    分层架构落地（Infrastructure & Application）：
        仓储模式（Repository）：用接口定义在领域层，用具体实现（如基于 MyBatis 或 JPA）放在基础设施层，
    演示依赖倒置。
        领域事件（Domain Event）：演示当订单创建后，如何发布 OrderCreatedEvent，并让其他模块
    （如积分、短信）去订阅，实现业务解耦。

### 