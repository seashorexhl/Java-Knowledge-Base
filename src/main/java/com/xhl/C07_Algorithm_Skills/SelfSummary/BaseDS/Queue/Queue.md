# 常用队列

    1.Queue  接口 普通队列（单端 FIFO） 
    2.Deque  接口 双端队列
    3.ArrayDeque 常用双端队列 
    4.DelayQueue 延迟队列
    5.PriorityQueue 优先级队列
    6.PriorityBlockingQueue  优先级阻塞队列
    7.ArrayBlockingQueue 标准 数组阻塞队列
    8.LinkedBlockingQueue 标准 链表阻塞队列
    9.SynchronousQueue 线程同步队列
    10.CustomPriorityQueue 自定义优先级队列
    
## 选型建议

    单线程场景：
        普通队列：优先用 ArrayDeque（性能优于 LinkedList）。
        优先级队列：用 PriorityQueue。
    多线程场景：
        严格资源控制：选 ArrayBlockingQueue（有界队列，防 OOM）。
        高吞吐任务缓冲：选 有界 LinkedBlockingQueue（避免默认无界风险）。
        即时响应需求：选 SynchronousQueue（避免任务排队延迟）。
    避坑要点：
        禁用无界队列：LinkedBlockingQueue 默认构造易导致内存溢出。
        监控关键指标：队列长度（getQueue().size()）和活跃线程数（getActiveCount()）。
        拒绝策略需显式配置：避免默认 AbortPolicy 静默丢弃任务311

## 常用方法

    queue.offer() 入队
    queue.poll()  出队
    queue.peek()  查看队首
    
