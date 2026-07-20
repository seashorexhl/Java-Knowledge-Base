# C03_Engineering_Infrastructure（工程化基建与DevOps）
    研发流程与运维体系
##  研发流程与运维体系

### 具体内容
    1. C03_01_Testing_Framework (测试体系)
       测试是贯穿整个交付流水线的“质量守护神”，建议按测试金字塔分层建立目录：
       单元测试 (Unit Test)：存放 JUnit 5 + Mockito 的实战代码。重点演示如何隔离外部依赖
    （如 Mock 掉数据库或第三方接口），验证核心业务逻辑。
       接口/集成测试 (Integration Test)：存放 Postman 或 Selenium 的自动化脚本，用于验证\
    模块间交互和契约。
       性能与安全测试：存放 JMeter 或 Gatling 的压测脚本配置。

    2. C03_02_CICD_Pipeline (持续集成与交付)
       存放自动化构建和部署的配置与脚本：
       构建配置：存放 Maven 或 Gradle 的高级配置（如多模块管理、统一版本控制）。
       流水线定义：存放 Jenkinsfile 或 .gitlab-ci.yml。你可以写一个标准的流水线脚本，
    演示“代码提交 -> 自动拉取 -> 单元测试 -> 代码质量扫描(SonarQube) -> 打包构建”的全流程。
    
    3. C03_03_Containerization (容器化与编排)
       存放应用打包和部署的配置文件：
       Docker：存放 Dockerfile 和 docker-compose.yml，演示如何将你的 Java 应用打包成镜像，
    并保证环境一致性。
       Kubernetes (K8s)：存放 K8s 的 YAML 配置文件（如 Deployment, Service, Ingress），
    演示微服务或单体应用的容器化编排与滚动更新。
    
    4. C03_04_Observability (可观测性与监控)
       存放监控、日志和链路追踪的配置与实战代码：
       链路追踪：存放 SkyWalking 或 Zipkin 的探针配置和代码埋点，演示如何追踪微服务调用链路、定位慢查询。
       指标与日志：存放 Prometheus + Grafana 的监控面板配置，以及 ELK 日志收集的配置，演示如何
    实时监控 CPU、内存、接口 QPS 和响应时间。
    
    5. C03_05_IaC (基础设施即代码) (进阶扩展)
       存放 Terraform 或 Ansible 脚本。演示如何用代码来自动化创建云服务器、网络配置和容器集群，
    避免手动配置环境带来的不一致问题。