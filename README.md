# Java & Full-Stack Knowledge Base

>💡 Java 后端核心 · 云原生架构 · AI 应用落地 · 个人成长体系

本项目旨在构建一个系统化、结构化的 Java 全栈开发知识库。内容涵盖从 Java 核心基础、JVM 原理、并发编程，到 Java 后端底层原理、企业级架构设计，到 Vue 3 前端生态、AI 大模型应用，以及个人职业成长。
到企业级框架、分布式中间件及架构设计思想。这里不仅是我的学习笔记，也是我在技术进阶路上的里程碑。

## 📂 目录结构 (Directory Structure)

本项目按照技术深度和学习路径进行了模块化分类：

### C01_Java_Core (Java 核心基础)
> 🧱 万丈高楼平地起，这里是所有技术的基石。
- **EffectiveJava**: 《Effective Java》最佳实践与代码规范笔记。
- **IO_NetWork**: Java I/O 模型、NIO 网络编程、零拷贝技术以及Netty核心原理。
- **java_New_Features**: Java 17+ 新特性探索（Records, Sealed Classes, Pattern Matching,虚拟线程等）。
- **JVM_Internals**: 虚拟机内存模型、GC 算法、类加载机制及JVM参数调优实战。
- **Concurrency**: JUC 并发包详解、线程池原理、锁机制与高并发实战。
- **核心API与底层原理**:集合框架源码、反射与动态代理、泛型与类型擦除 深入剖析。理解 Spring 等框架底层机制的必经之路。
- **性能调优**: 内存泄漏排查、JVM 调优、并发瓶颈分析及 APM 监控工具使用。

### C02_Architecture_Design (架构与设计)
> 🏛️ 提升代码质量与系统扩展性的内功心法。
- **DesignPattern**: 23 种设计模式的代码实现与应用场景分析。
- **Refactoring**: 代码重构技巧，识别并消除代码坏味道。
- **Reactive_Programming**: 响应式编程范式（Project Reactor）与异步非阻塞开发。
- **DDD_Practice** (领域驱动设计): 限界上下文划分、聚合根、实体与值对象设计，复杂业务建模方法论。

### C03_Engineering_Infrastructure (工程化基础设施)
> 🛠️ 工欲善其事，必先利其器。打造高效的研发流水线。
- **Build_Tools**: Maven/Gradle 高级配置与多模块项目管理。
- **CI_CD**: Jenkins/GitLab CI 自动化构建与部署流程。
- **Containerization**: Docker 容器化技术与 Kubernetes (K8s) 基础编排。
- **DevOps**: 日志系统 (ELK)、监控告警 (Prometheus+Grafana) 搭建。

### C04_Enterprise_Frameworks (企业级框架)
> ⚙️ 实际工作中构建大型应用的主流技术栈。
- **Spring生态**: Spring Boot/Cloud 生态源码分析与实战配置。
- **ORM与持久层**: MyBatis / JPA (Hibernate) 底层原理、缓存机制与 HikariCP 连接池调优。
- **JavaEE规范**: Servlet/JSP 等传统 Web 规范（理解框架底层原理）。
- **BPMN工作流**: Flowable/Activiti 引擎原理与复杂业务流程建模。
- **工程化与测试**: Maven/Gradle 构建脚本与依赖管理；JUnit 5 / Mockito 单元测试与 TDD（测试驱动开发）实践。

### C05_Database_Store (数据库与存储)
> 💾 数据的持久化与高效检索方案。
- **MySQL**: 索引优化、事务隔离级别、分库分表策略，执行计划分析 (EXPLAIN) 和 慢查询优化。
- **NoSQL**:MongoDB, Redis，图数据库 (Neo4j) 或 时序数据库 (InfluxDB) 等非关系型数据库应用与选型。

### C06_Middleware_Distributed (中间件与分布式)
> ⚡ 解决高并发、高可用、海量数据处理的核心组件。
- **MQ**: 消息队列（RabbitMQ/Kafka/RocketMQ）原理、集群搭建与消息可靠性保障。
- **Redis**: 核心数据结构、持久化机制、分布式锁、缓存一致性设计以及高可用集群方案。
- **搜索引擎**: Elasticsearch 倒排索引原理、DSL 查询优化及海量数据检索实战。
- **分布式核心概念**:  分布式事务、分布式锁、CAP/BASE 理论、RPC 框架（Dubbo/gRPC）及服务网格 (Service Mesh)。

### C07_Algorithm_Skills (算法与技能)
> 🧠 保持逻辑思维敏锐度，应对面试与复杂场景。
- **LeetCode**: 数据结构与算法刷题记录（按题型分类）。
- **DataStructure**: 常见数据结构的手写实现与复杂度分析。
- **SelfSummary**:自我总结 常用的数据结构与算法。

### C08_Frontend_Stack(前端核心技术栈)
> 🎨 现代前端工程化与 Vue 3 生态实践。
- **Vue3_Ecosystem**: Vue 3 + Element Plus + Pinia + Vue Router 中后台实战。
- **Build_Tools**: Vite 构建工具原理、插件开发与性能优化。
- **TypeScript**: 前端类型系统、接口定义与泛型高级用法。
- **CSS_Solutions**: Tailwind CSS 原子化样式与 SCSS 主题定制。
- **AI_Frontend**: 流式输出 (SSE)、Markdown 渲染与对话 UI 组件封装。

### C09_AI_LLM_Stack (大模型应用开发)
> 🤖 拥抱 AI 原生时代，掌握大模型应用的全栈开发与工程化落地。
- **LLM_Core**: 大模型基础原理（Transformer、Token 机制）、Prompt 工程方法论与高质量提示词设计。
- **RAG_Knowledge**: 检索增强生成（RAG）技术栈。涵盖文档解析、文本分块（Chunking）、向量嵌入（Embeddings）及向量数据库（如 Milvus/PGVector/Chroma）实战。
- **AI_Agents**: 智能体（Agent）开发。学习任务规划、记忆管理（Memory）、Function Calling 及多 Agent 协作机制。
- **LLM_Frameworks**: 主流开发框架与工具链。如 LangChain / LlamaIndex 源码级应用、LangGraph 工作流编排、Semantic Kernel 等。
- **AI_Engineering**: 大模型工程化与部署。包括本地模型部署（Ollama/vLLM）、模型微调（Fine-tuning/LoRA）、LLMOps 监控与评估（LangSmith）。
- **AI_Frontend**: AI 应用前端交互。流式输出（SSE）、Markdown 渲染、对话 UI 组件封装及语音/多模态交互。

### C10_Career_Growth (职业成长)
> 🌱 技术之外的软实力与职业规划思考。
- **Interview**: 面试经验总结与高频考题解析。
- **SoftSkills**: 沟通协作、时间管理与职场心得。

---

## 🎯 项目愿景 (Vision)

- **系统性**：拒绝碎片化学习，建立完整的知识图谱。
- **实战性**：不仅记录理论，更注重代码落地与生产环境问题解决。
- **持续性**：保持更新，跟随技术潮流不断迭代。

## 🤝 如何参与 (Contributing)

如果你对本项目感兴趣，或者发现了错误，欢迎通过以下方式参与：
1. **Fork** 本仓库。
2. 创建你的特性分支 (`git checkout -b feature/AmazingFeature`)。
3. 提交你的修改 (`git commit -m 'Add some AmazingFeature'`)。
4. 推送到分支 (`git push origin feature/AmazingFeature`)。
5. 开启一个 **Pull Request**。

## 📄 许可证 (License)

本项目遵循 [MIT License](LICENSE)。

---

<p align="center">Made with ❤️ by [Lxh]</p>
