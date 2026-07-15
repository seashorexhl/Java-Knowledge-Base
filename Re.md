# Java-Knowledge-Base 项目
#  Java Backend Knowledge Base

>  **Java 后端开发知识体系与实战笔记**

本项目旨在构建一个系统化、结构化的 Java 后端知识库。内容涵盖从 Java 核心基础、JVM 原理、并发编程，到企业级框架、分布式中间件、工程化基建及架构设计思想。这里不仅是我的学习笔记，也是我在技术进阶路上的里程碑。

##  目录结构 (Directory Structure)

本项目按照技术深度和学习路径进行了模块化分类：

### 01_Java_Core (Java 核心基础)
>  万丈高楼平地起，这里是所有技术的基石。
- **EffectiveJava**: 《Effective Java》最佳实践与代码规范笔记。
- **IO与网络**: Java I/O 模型、NIO 网络编程、零拷贝技术以及 Netty 核心原理。
- **Java17**: Java 17+ 新特性探索（Records, Sealed Classes, Pattern Matching, 虚拟线程等）。
- **JVM**: 虚拟机内存模型、GC 算法、类加载机制及 JVM 参数调优实战。
- **Multithreading**: JUC 并发包详解、线程池原理、锁机制与高并发实战。
- **核心API与底层原理**: 集合框架源码、反射与动态代理、泛型与类型擦除深入剖析。理解 Spring 等框架底层机制的必经之路。
- **性能调优**: 内存泄漏排查、JVM 调优、并发瓶颈分析及 APM 监控工具使用。

### 02_Architecture_Design (架构与设计)
> ️ 提升代码质量与系统扩展性的内功心法。
- **DesignPattern**: 23 种设计模式的代码实现与应用场景分析。
- **Refactoring**: 代码重构技巧，识别并消除代码坏味道。
- **Reactor**: 响应式编程范式（Project Reactor）与异步非阻塞开发。
- **DDD** (领域驱动设计): 限界上下文划分、聚合根、实体与值对象设计，复杂业务建模方法论。

### 03_Engineering_Infrastructure (工程化基建与DevOps)
> ️ 保障代码质量与系统稳定性的“护栏”与“交通监控系统”。
- **Testing_Framework**: 测试金字塔实践，包含 JUnit 5 + Mockito 单元测试、接口自动化测试及 JMeter 性能压测。
- **CICD_Pipeline**: 持续集成与交付，涵盖 Maven/Gradle 高级构建配置与 Jenkins/GitLab CI 自动化流水线。
- **Containerization**: 容器化与编排，Docker 镜像构建与 K8s (Kubernetes) 基础编排、服务发现。
- **Observability**: 可观测性三大支柱实践，Metrics (Prometheus)、Logging (ELK/Loki) 与 Tracing (SkyWalking/Zipkin)。
- **IaC** (基础设施即代码): Terraform / Ansible 脚本实践，实现云资源的自动化配置与管理。

### 04_Enterprise_Frameworks (企业级框架)
> ️ 实际工作中构建大型应用的主流技术栈。
- **Spring生态**: Spring Boot/Cloud 生态源码分析与实战配置。
- **ORM与持久层**: MyBatis / JPA (Hibernate) 底层原理、缓存机制与 HikariCP 连接池调优。
- **JavaEE规范**: Servlet/JSP 等传统 Web 规范（理解框架底层原理）。
- **BPMN工作流**: Flowable/Activiti 引擎原理与复杂业务流程建模。

### 05_Middleware_Distributed (中间件与分布式)
>  解决高并发、高可用、海量数据处理的核心组件。
- **MQ**: 消息队列（RabbitMQ/Kafka/RocketMQ）原理、集群搭建与消息可靠性保障。
- **Redis**: 核心数据结构、持久化机制、分布式锁、缓存一致性设计以及高可用集群方案。
- **搜索引擎**: Elasticsearch 倒排索引原理、DSL 查询优化及海量数据检索实战。
- **分布式核心概念**: 分布式事务、分布式锁、CAP/BASE 理论、RPC 框架（Dubbo/gRPC）及服务网格 (Service Mesh)。

### 06_Algorithm_Skills (算法与技能)
>  保持逻辑思维敏锐度，应对面试与复杂场景。
- **LeetCode**: 数据结构与算法刷题记录（按题型分类）。
- **DataStructure**: 常见数据结构的手写实现与复杂度分析。

### 07_Database_Store (数据库与存储)
>  数据的持久化与高效检索方案。
- **MySQL**: 索引优化、事务隔离级别、分库分表策略，执行计划分析 (EXPLAIN) 和慢查询优化。
- **NoSQL**: MongoDB, Redis，图数据库 (Neo4j) 或时序数据库 (InfluxDB) 等非关系型数据库应用与选型。

### 08_Cloud_Native_Microservices (云原生与微服务治理)
> ️ 拥抱现代化部署与高可用架构体系。
- **微服务治理**: API 网关、服务注册发现、配置中心、限流熔断与降级策略。

### 09_Career_Growth (职业成长)
>  技术之外的软实力与职业规划思考。
- **Interview**: 面试经验总结与高频考题解析。
- **SoftSkills**: 沟通协作、时间管理与职场心得。

---

##  项目愿景 (Vision)

- **系统性**：拒绝碎片化学习，建立完整的知识图谱。
- **实战性**：不仅记录理论，更注重代码落地与生产环境问题解决。
- **持续性**：保持更新，跟随技术潮流不断迭代。

##  如何参与 (Contributing)

如果你对本项目感兴趣，或者发现了错误，欢迎通过以下方式参与：
1. **Fork** 本仓库。
2. 创建你的特性分支 (`git checkout -b feature/AmazingFeature`)。
3. 提交你的修改 (`git commit -m 'Add some AmazingFeature'`)。
4. 推送到分支 (`git push origin feature/AmazingFeature`)。
5. 开启一个 **Pull Request**。

##  许可证 (License)

本项目遵循 [MIT License](LICENSE)。

---

<p align="center">Made with ️ by [Lxh]</p>