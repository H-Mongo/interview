#### 从业经历

##### 工作内容

##### 工作职责

##### 工作成果

回顾自己三年内的工作中，自己都干了那些比较重大且有意义的项目，以及在项目方案上的成果和经验，如何解决开发中所面临的技术问题等等

+ 场景类消息投递（常规/高峰）
+ 会员一体化接口（基于图模式执行）
+ 携程房源接入
+ 分销房源点评回流
+ 折叠点评需求
+ 产品库存系统重构（切面重构，遇见的事务问题）
+ PMS登录（采用配置化的方式，无需代码变动）
+ 接口共享限流问题
+ 高峰运营的任务分发模型
+ 会员中心查询后台（web）
+ 产品中心后台（web）
+ 预热方案（spring结合，泛型化方法方案的支持）

##### 项目开发中遇见的问题
+ 接口限流值共享问题
+ mybatis 的 double null 判断问题
+ MQ 队列不同渠道数据挤压导致消费延迟问题
+ ES timeout 和更新版本冲突问题
+ spring 通用预热下的泛型数据丢失的问题
+ axios 开发过程中遇见的 delete 参数传递问题
+ 点评回流过程中遇见的事务问题，内部调用导致事务失效
+ 任务分发流程中，任务与本地机器状态绑定问题
+ sharding-jdbc 项目启动后导致无法正确路由库表问题


##### 简历体现的内容

对于简历上的内容应该精炼一些，内容不应过于臃肿，暴露自身的核心信息，能够体现自己能力的数据即可。
必须要体现的内容有：姓名、出生年月或者年龄、邮箱地址、电话号码、性别这些。
其他需要体现的内容有教育经历、专业技能、就业经历、有价值体现的项目、自我评价等内容！

+ 教育经历：宝鸡文理学院，2016-2020，软件工程专业

+ 专业技能：
  + 掌握Java基础，包括Collection框架、并发编程、异常处理、IO/NIO、Socket编程等核心技术，阅读过相关源码；
  + 熟悉JVM实现原理、类加载机制、JMM模型及GC机制，具备线上问题排查能力；
  + 掌握面向对象、切面编程的思想，熟悉基本数据结构、常见设计模式；
  + 掌握MySQL以及存储原理、MVCC、事务原理，SQL优化等，具备ES、MongoDB、PostgreSQL的使用经验；
  + 熟练运用Spring、SpringBoot、MyBatis、ShardingJDBC、Druid等框架，熟知其实现原理且阅读过源码；
  + 熟悉Dubbo、Apollo、Xxl-Job等微服务框架，了解Zookeeper的使用，阅读过Dubbo SPI部分源码；
  + 熟悉RocketMQ、Kafka消息框架及其部署、持久化、事务消息等原理；
  + 掌握Redis、GuavaCache缓存技术的使用，能解决缓存雪崩、缓存穿透、缓存击穿等问题；
  + 熟悉异步开发模式，具有ThreadPool、AsyncHttpClient、EventListening的开发经验；
  + 熟悉Tomcat原理及Jsp、Servlet技术，了解Netty框架的使用；
  + 掌握Linux命令和Git、Idea、Maven、CRT、PostMan、VSCode等开发工具的使用；
  + 对分布式技术有一定认识，如分布式事务、分布式锁、CAP理论等；
  + 具备Golang、Python语言进行项目开发的经验；

> 以下是第一版：
  + 熟悉JVM实现原理、类加载机制、JMM模型及GC机制，了解其基本调优方式，结合JVM指令排查线上问题；
  + 掌握SSH、SSM的web架构模式，对核心原理有一定研究并阅读过相关源码实现；
  + 掌握RPC框架Dubbo的使用，了解其实现原理比如SPI扩展机制、服务发现机制、负载均衡、容错机制等；
  + 熟悉MySQL数据库的使用，了解其底层数据结构，事务、MVCC实现等以及基本SQL调优技能；
  + 熟悉Redis技术的使用，了解其数据结构、数据持久化、内存淘汰机制等原理，并利用它实现滑动窗口、队列、分布式锁；
  + 熟悉使用RocketMQ，了解其实现原理、消息持久化、事务消息、部署方式等；
  + 熟悉JSP、Servlet技术等，了解Tomcat Web容器实现原理；
  + 具备使用Xxl-Job任务调度、Apollo配置中心等框架的工作实践经验；
  + 了解有关分布式、云原生等技术的内容及原理，比如分布式锁、分布式事务、sidecar等；
  + 熟练使用Linux服务器命令，比如文本处理三剑客grep、awk、sed等，以及vim编辑器的使用；
  + 熟悉使用Js,Html,Css,Bootstrap,Vue,ElementPlus等前端技术，开过小型Web工具后台；

+ 就业经历：
  + 公司名称：途家网网路技术有限公司或者简写为途家网
  + 入职时间：2020.07-至今
  + 职称：Java高级开发工程师
  + 工作职责：
    1) 负责会员&积分、点评UGC、业务中台、CRM、供应链与房源接入等业务系统开发工作；
    2) 积极主动同PM进行项目沟通，协调并推进了折叠点评、体验会员、Ads投放等需求的跨团队合作开发；
    3) 快速定位、排查、解决线上Bug、BadCase、数据不一致等问题，保证业务功能的正确性；
    4) 针对系统的问题和缺陷提供可行性的技术优化方案，比如线程池参数动态化、动态流控、通用化服务预热等。

  + 核心项目
    + 会员系统-用户属性接口重构（2022-10）
      + 项目描述：
        > 伴随着会员业务的拓展，会员所附属的属性与等级信息增多比如体验会员、出行身份、优享家等，API接口呈现多样化，等级转化关系分散。转换关系转换导致了多个故障。
        > 为了避免故障发生，提升会员系统属性接口的稳定性和易用性，对涉及到的接口进行重构并对外部系统提供统一查询API接口。
      + 解决方案：
        > 针对现有的问题进而提出了“一体化”接口的优化方案，利用属性编码与业务定制等模式使数据在会员系统内部自动组装、聚合统一露出。
      + 技术实现：
        > 引入“图论”中的思想，为业务构建服务执行图并根据服务数据依赖关系串行/并行执行，借鉴容错技术中的“舱壁隔离技术”进行用户渠道、用户属性ThreadPool资源隔离，
        > 利用Guava提供Futures模型实现服务异步执行结果的组装和回调功能。
      + 技术栈：
        > Spring、Dubbo、Redis、MySQL、ElasticSearch、AsyncHttp、GraphEngine、ThreadPool、Guava等技术
      + 项目成果：
        1. 等级关系转换统一收口，将其导致的故障量将为0；
        2. 组合Redis与异步Http优化外部接口查询，使接口耗时至少降低了30ms；
        3. 简化了业务方对API的使用，复杂度由N降为1；
        4. 线程池采用“舱壁隔离”机制避免了各渠道资源竞争；
        5. 基于GraphEngine与属性编码码实现业务编排。
      + 项目成果：通过优化改造，再无因优享家等级转化而引发的故障；业务方无需自行依赖多个接口进行服务调用，业务开发复杂度由N降为1；依赖用户属性相关业务耗时下降20ms左右。
      + 遇见的问题及难点：图模型的构造，数据加工与聚合传递问题，future.get()超时问题
    
    + 用户增长业务-场景类消息投递模型建设（2022-04）
      + 背景：
        > 起初为了应对单次活动类消息投递开发了简单的任务模型，但该任务的执行和数据导入严重依赖人工支持，随PM增长类消息投递的需求场景增多、业务复杂度的提升，
        > 在当前任务模型无法支持的情况下，逐步划分出高峰和常规两大类场景，并对造case难、业务代码分散、消息投递效率低下问题进行重构和优化。
        > 
      + 解决方案：
        > 按照消息推送的时效性分为实时和定时拆分，结合消息生命周期长短又分为常规类和高峰类，同时增加对策略降级、频控配置化、数据监控等能力。
      + 技术实现：
        > 针对常规类消息通过xxl-job实现数据同步和消息推送的调度工作，采用redis实现用户策略优先级队列以及rocketmq异步提升消息推送的处理能力，
        > 使用Go实现了召回测试客户端。而高峰类消息则是采用“活动任务分发”模型，利用redis与本地缓存来共同维护任务的分发状态，实现多机并行高峰活动处理！
      + 技术栈：
        > Spring、Dubbo、Redis、MySQL、Xxl-Job、RocketMQ、Hive、Golang、Vue、ElementPlus、TaskDistribution等技术
      + 项目成果：
        1. 全面负责了该项目从0-1的实现与落地过程，弥补了途家在该业务上的空白；
        2. 常规与高峰类消息投递均实现自动化，释放了开发资源；
        3. 该项目带上线后，带动了0.24%的日间夜量转化率；
        4. 利用Go客户端测试工具解决了case难，场景回归复杂等问题；
        5. 结合Redis实现了投递频控、用户优先级队列等核心能力。
      + 项目成果：填补了场景类消息的空白，为常规类场景提供策略降级，优先级队列等能力。GoWeb工具解决了造case难问题。 
      对高峰类场景搭建了管理后台，赋予灰度测试的能力，压测结果显示300W/h，可支持10个活动并行，完成300+活动推送！
      + 遇见的问题及难点：任务分发机制加锁问题，活动与机器IP绑定问题，动态限流问题

    + 点评系统-设计并优化分销房源点评拉取机制（2021-08）
      + 项目描述：
        > 当平台房源在分销渠道（比如携程、同程艺龙等）被售卖，用户下单后的评论数据需要被拉取回来。而旧代码拉取机制不清晰，代码复用性低、拉取逻辑上也存在漏洞。
        > 鉴于点评拉取的机制是相同的，不同点是数据源，因此对点评拉取机制进行重新设计与优化，解决了旧代码所存在的各个问题。
      + 解决方案：
        >
      + 技术实现：
        > 
      + 技术栈：
        > Spring、MySQL、Xxl-Job、RocketMQ、ThreadPool、TaskQueue等技术
      + 项目成果：
        1. 采用模板方法模式对拉取机制进行了抽象封装，提升代码复用性、上手难度低、流程更清晰；
        2. 利用数据快照diff，仅处理变化的点评，提升整体的处理速度；
        3. 将点评图片从点评推送流程剥离解耦，task异步多线程处理；
        4. 新增分销点评拉取开发工时缩短至原来的20%。
      + 项目成果：
      + 遇见的问题及难点：事务问题
      
  + 自我评价
    + 为人诚实谦虚、性格开朗、待人友好，具有良好的人际交往能力和沟通能力；
    + 工作勤奋认真、尽职尽责、吃苦耐劳，具备很好的团队精神，同时也能够承受较大工作压力；
    + 学习能力较强，有良好地阅读习惯，能够积极主动地了解、学习新技术；
    + 生活中具有良好地抗压能力、自控能力和自我判断能力，时刻保持着一个积极向上的状态。


##### 项目阐述
对于目前的面试反馈结果来看，在项目阐述上还是有很大的问题，不能够让对方很清晰地获取到有效信息。
所以对于项目的介绍要简练不罗嗦，突出a重点，体现价值，主要从以下几点：
+ 项目的背景是什么？
> 这里重点讲述一下为什么做这个项目，目前所存在的问题点是什么，项目所涉及到的业务方（上下游关系），这里其实可以通过场景进行介绍！
+ 项目开发中的难点是什么？
> 在时间过程中遇见了什么问题，如何选择技术方案并解决的。
+ 做完这个项目的价值体现在哪里？
> 要说明白，说清楚这个项目的价值体现在哪里，有那些提升优化。

###### 一体化改造接口：
+ 背景：属性数据接口分散化，优享家转换分散（业务中间层和报价营销系统），同业务多次触发内部服务调用
+ 难点：对属性自动编排查询的能力支持
+ 价值：属性数据接口标准化；优享家转换收口，其故障率为0；业务方使用复杂度由N降为1；
内部服务调用减低；业务方的耗时至少降低30ms；

###### 消息投递模型：
+ 背景：为单次业务而开发，功能简单，依赖人工
+ 难点：数据自动化导入、第三方API的限流值共享问题、case回归测试难问题
+ 价值：构建三个体系：实时、定时、高峰；伸缩共享限流方案；go客户端解决了case回归测试难问题