# IO 流

    字节 字符流
    输入输出流
    节点 过滤流

## 核心流体系与常见实现
    
    1.字节流：
        顶层抽象父类：InputStream（读）、OutputStream（写）。
        基础节点流：FileInputStream / FileOutputStream，用于文件的字节读写。
        增强过滤流：BufferedInputStream / BufferedOutputStream（自带缓冲区，大幅提升读写效率）；   
    DataInputStream / DataOutputStream（专门读写 Java 8种基本数据类型）。

    2.字符流：
        顶层抽象父类：Reader（读）、Writer（写）。
        基础节点流：FileReader / FileWriter，用于简单的文本读写。
        增强过滤流：BufferedReader（提供 readLine() 按行读取，性能高）/ BufferedWriter。

    3.对象流与转换流：
        对象流（ObjectInputStream / ObjectOutputStream）：用于对象的序列化与反序列化，
    要求对象所属类必须实现 Serializable 标记接口。
        转换流（桥转流）（InputStreamReader / OutputStreamWriter）：作为字节流与字符流
    之间的桥梁，允许开发者手动指定字符编码格式，有效解决中文乱码问题。
    
##  开发最佳实践与避坑指南

    1.流的选型：非文本文件必用字节流；纯文本文件优先使用字符流；需要指定编码或进行流类型转换时使用桥转流。
    2.性能优化：只要涉及文件读写，强烈建议都使用 Buffered 缓冲流进行包装。缓冲流会先将数据读入内存缓冲区，
    减少频繁的磁盘 IO 访问，显著提升性能。
    3.资源释放：流一旦打开就必须关闭以释放资源。推荐使用 JDK 7 引入的 try-with-resources 
    语法实现自动关闭；若使用传统方式，必须将 close() 方法放在 finally 语句块中。对于多层包装流，
    只需关闭最外层流即可。

##  Java IO 模型
    
    BIO（Blocking IO，阻塞 IO）：同步且阻塞，一个连接需要一个线程处理，适合连接数少且固定的传统单机应用。
    NIO（Non-Blocking IO，非阻塞 IO）：同步非阻塞，基于 Channel（通道）、Buffer（缓冲区）和Selector（多路复用器），适合高并发场景。
    AIO（Asynchronous IO，异步 IO）：异步非阻塞，由操作系统完成 IO 操作后主动通知应用线程处理。