# 关于 高级 Queue

## 并发与特殊场景
    
    ArrayBlockingQueue      (有界，基于数组，加锁)
    LinkedBlockingQueue     (可选有界，基于链表，加锁)
    ConcurrentLinkedQueue   (★新增: 无锁，高性能)
    PriorityBlockingQueue   (带优先级的阻塞队列)
    SynchronousQueue        (不存储元素，直接交接)
    DelayQueue              (延时队列，也可放在base)

##