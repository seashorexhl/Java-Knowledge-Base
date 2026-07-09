# DS 和 AL 
    以 LeetCode、labuladong 基础 算法 为例

##  学习 数据结构 与 算法 顺序
    
        学习整理顺序：
        一、基础数据结构

            Array 数组  
            String 字符串
            Stack 栈
            Queue 队列
            Deque 双端队列
            HashMap 哈希表
            LinkedList 链表 
            ArrayList 线性表
        
        二、基础算法

            Sort 排序
            Greedy 贪心
            Recursion 递归
            Cycle 循环
            Slide Window 滑动窗口
            Base conversion 进制转换
            Bitwise 位运算
            Binary Search 二分查找
            Simulate   模拟

        三、进阶 数据结构

            Graph 图
            Tree 树
            Binary Tree 二叉树
            Binary Search Tree 二叉搜索树
            AVL  平衡二叉树
            Tries 字典树
            DFS 深度优先搜索
            BFS 广度优先搜索

        四、进阶算法

            DP 动态规划 
            Prefix Sum 前缀和
            Permutations and Combinations 排列组合
            Matrix 矩阵
            Two Pointers 双指针
            Backtracking 回朔
            State Machine 状态机
            Union-Find 并查集
            RegExp 正则表达式
            Enum 枚举
            Divide and Conquer 分治
            Statistics 统计

        五、扩展 数据结构

            RB 红黑树
            B  树
            B+ 树
            Trie 字典树
            Segment Tree 线段树
            Balanced Binary Tree 平衡二叉树
            Self-balancing Binary Search Tree 自平衡二叉搜索树

        六、扩展 算法

            A.高级算法
             高级动态规划：状态压缩 DP（State Compression DP）数位 DP（Digit DP）、树形 DP 与换根 DP、DP 优化
             高级图论：网络流（Network Flow）、强连通分量（SCC）与 2-SAT、图的匹配问题 
             高级DS:树链剖分（Heavy-Light Decomposition,LCT（Link-Cut Tree）
             字符串高级：KMP / Z-Algorithm、后缀数组（Suffix Array）与 后缀自动机（SAM）、Manacher 算法
            
            B.数学基础（AI/算法岗的核心壁垒）
                数学与计算几何：数论进阶：中国剩余定理（CRT）、扩展欧几里得、快速傅里叶变换（FFT / NTT，用于多项式极速乘法）。
                计算几何：凸包（Convex Hull）、半平面交、旋转卡壳（Rotating Calipers）、闵可夫斯基和。
                这是区分普通开发与算法工程师的关键。你需要重点攻克以下三大数学领域：
                线性代数：矩阵运算、特征值分解、向量内积等（深度学习模型的基础）。
                概率论与数理统计：贝叶斯定理、期望与方差、常见概率分布等（理解机器学习模型和损失函数的基石）。
                微积分：导数、偏导数、链式法则（理解梯度下降和神经网络反向传播的核心）。

            C.机器学习与深度学习理论
                在具备编程和数学基础后，需要系统学习具体的算法模型：
                传统机器学习：逻辑回归、决策树、支持向量机(SVM)、聚类算法等。
                深度学习框架与模型：熟练使用 PyTorch 或 TensorFlow，掌握卷积神经网络(CNN)、循环神经网络(RNN/LSTM)等主流架构的原理与调优。
            
            D.工程落地与数据处理能力
                优秀的算法不能只停留在纸面或本地测试，必须具备将其转化为实际产品的能力：
                数据处理与分析：掌握 SQL，熟悉 Pandas/NumPy 等工具，能够完成数据清洗、特征工程等脏活累活。
                工程化部署：了解 Linux 操作系统，掌握如何将训练好的模型封装为 API（如 Flask/Django），并使用 Docker 等技术部署到服务器或云端集群（如 Hadoop/Spark）。

##  自我判断 (必考)

    1. 必须死磕的核心（高频考点）
        基础数据结构：Array、String（极高频，OD 最爱考字符串处理与正则）、Stack、Queue、HashMap。
        基础算法：Sort、Greedy、Slide Window、Binary Search、Two Pointers、Base conversion、Bitwise。
        进阶算法与数据结构：DFS、BFS、DP（背包、路径规划等）、Prefix Sum、Backtracking、Union-Find、Graph
        （最短路径）、Tree（二叉树遍历）。
        其他：RegExp（正则表达式在字符串题中非常实用）、Statistics（统计频率）。

    2. 建议战略性放弃或仅作了解（低性价比考点）
        扩展数据结构：RB 红黑树、B 树、B+ 树、Segment Tree 线段树。这些在 OD 机考中几乎不考，
        手撕代码难度过大，建议直接跳过，把时间留给 DP 和 DFS/BFS。
        Divide and Conquer 分治、State Machine 状态机：了解思想即可，通常可以归并到 DP 
        或递归中解决。
        Matrix 矩阵：掌握基础的二维数组遍历和方向数组（上下左右移动）即可，不需要深究复杂的矩阵算法。
    
### 针对 OD 建议 ⭐⭐⭐⭐⭐
    
    ① 科学刷题（分类有序 理清重点）

        滑动窗口与双指针（定长/不定长/单序列/双序列/三指针）
        二分算法（二分答案/最小化最大值/最大化最小值/第K小）
        单调栈（基础/矩形面积/贡献法/最小字典序）
        网格图（DFS/BFS/综合应用）
        位运算（基础/性质/拆位/试填/恒等式/思维）
        图论算法（DFS/BFS/拓扑排序/最短路/最小生成树/二分图/基环树/欧拉路径）
        动态规划（入门/背包/状态机/划分/区间/状压/数位/数据结构优化/树形/博弈/概率期望）
        常用数据结构（前缀和/差分/栈/队列/堆/字典树/并查集/树状数组/线段树）
        数学算法（数论/组合/概率期望/博弈/计算几何/随机算法）
        贪心与思维（基本贪心策略/反悔/区间/字典序/数学/思维/脑筋急转弯/构造）
        链表、二叉树与一般树（前后指针/快慢指针/DFS/BFS/直径/LCA）
        字符串（KMP/Z函数/Manacher/字符串哈希/AC自动机/后缀数组/子序列自动机）
        
    ② 具体问题 具体分析
        重点刷 DFS/BFS（网格图搜索、树的遍历）、滑动窗口（最长子串问题）、背包问题（01背包、完全背包）、
    以及并查集。
        1.必会部分知识点倾向于出现在100分题中，进阶知识点倾向于出现在200分题中。建议必会部分优先掌握1-10
    知识点，进阶部分优先掌握1-5知识点，这部分出现频次高，短时间内刷题性价比高。

        2. 对于进阶部分，图往往伴随着深度优先和广度优先出现，我建议优先广度优先深度优先、二叉树的遍历
    （能应付二叉树路径统计等题型）。其余有精力再准备。对于链表、广度优先和深度优先，LeetCode和牛客上
    有很多现成的答题模板，大家可以当做公式一样进行参考。

    ③ 注意 组合 算法
        真题极少纯粹考查单一模板，而是偏向于“多模块组合”。
        充电站布置 = 差分 + 滑动窗口 + 贪心。
        文件合并最小成本 = 区间 DP + 前缀和。
        有效子数组统计 = 滑动窗口 + 哈希 + 多条件判断。
     掌握基础代码实现后，一定要按照「模块 → 变种 → 组合」的顺序进行刷题，培养解决复杂业务逻辑的能力。   
    
    ④ 注意 边界条件，警惕死循环 和 无效操作 