# java生产项目常用的demo

文档比较长，有兴趣查看的可以安装一个：
https://chrome.google.com/webstore/detail/github-toc/nalkpgbfaadkpckoadhlkihofnbhfhek
来展示目录

##一.代理模式
###1.静态代理
`继承方式实现` 
`聚合方式实现`
###2.动态代理
`使用jdk proxy代理接口方式实现`
`使用Cglib代理类方式实现`
####自己实现的动态代理
`模仿jdk proxy自己实现动态代理`：
核心：实现动态代理的Proxy类 ，
实现动态代理的InvocationHandler类重写invoke方法实现.
##二.spring
###1.spring ioc
####1.1.spring容器
######1.1.1.BeanFactory
常用实现类：`DefaultListableBeanFactory`	
######1.1.2.ApplicationContext
常用实现类：`FileSystemXmlApplicationContext`
#####1.1.3.启动过程
由于对于不同容器启动过程是类似的，因此在基类`AbstractXmlApplicationContext`中将它们封装好，通过refresh()方法进行调用。
######refresh():
IOC容器初始化具体分为以下三部：
| BeanDefinition的Resource|定位|载入|注册|
| :-------- | --------:|--------:|--------:|
||对不同BeanDefinition Resource的定位|把用户定义的Bean表示成Ioc容器内部数据结构|调用BeanDefinitionRegistry注册BeanDefinition到IOC容器的HashMap中|
此时的ioc容器初始化过程一般`不包含Bean依赖注入`,一般依赖注入是在第一次getBean时才进行（设置了lazyinit的除外）。
######registerBeanDefinition：
```java
DefaultListableBeanFactory:
	this.beanDefinitionMap.put(beanName, beanDefinition);
```
#####1.1.4.ioc容器的依赖注入
```java
AbstractBeanFactory:
	protected <T> T doGetBean(
			final String name, final Class<T> requiredType, final Object[] args, boolean typeCheckOnly)
			throws BeansException 
```
#####1.1.5.Bean实例化
默认使用cglib对java的字节码进行增强。
`SimpleInstantiationStrategy`：
提供两种实例话方案，一种是通过BeanUtils，使用了`JVM反射`。
一种就是通过`cglib`。
#####1.1.6.设置Bean对象的依赖：
```java
AbstractAutowireCapableBeanFactory:
populateBean
```
###2.spring的事务
###2.1.编程式事务
`作为例子，使用场景很少`
###2.2.声明式事务
####使用spring-AspectJ（编译时实现动态代理）
`通过配置切点，切面织入实现动态代理`
####使用spring-AOP动态代理（运行时实现动态代理）
`注解方式（适合中小工程）`
####事务传播行为
####事务隔离级别

##三.数据库事务
###1.1数据库事务调优原则
在不影响业务应用的前提下:
1.`减少锁的覆盖范围（如：表锁->行锁）`<br>
2.`增加锁上可并行的线程数（如：读锁写锁分离，允许并行读取数据）`<br>
3.`选择正确锁的类型（如：悲观锁，乐观锁）`<br>

##四.简单应用
###1.1 高并发秒杀系统（持续更新）
项目目录：https://github.com/asdgh1000/demo/tree/master/secoundKill
###1.2 简单代理模式demo（持续更新）
项目目录：https://github.com/asdgh1000/demo/tree/master/proxyDemo
###1.3 简单spring事务demo（持续更新）
项目目录：https://github.com/asdgh1000/demo/tree/master/springTrancation
##五.分布式
1.分布式协作配置管理系统（zookeeper）<br>
2.分布式缓存系统<br>
3.分布式持久化存储<br>
4.分布式消息系统<br>
5.搜索引擎<br>
6.CDN<br>
7.负载均衡系统<br>
8.运维自动化系统<br>
9.实时计算系统<br>
10.离线计算系统<br>
11.分布式文件系统<br>
12.日志收集系统<br>
13.监控系统<br>
14.数据仓库<br>
### 难点
1.缺乏全局时钟<br>
2.面对故障独立性<br>
3.处理单点故障<br>
4.事务的挑战<br>
###1.1 分布式相关算法
####1.1.1 负载均衡算法
##### 1.Round Robin(轮询算法)
存在问题：轮询策略的目的在于，希望做到请求转移的完全均衡，但为了pos变量修改的互斥性，需要引入重量悲观锁，sychronized，将会导致该轮询代码的并发吞吐量明显下降。
##### 2.Random (随机算法)
当请求量大的时候，接近于平均访问，并且不需要使用悲观锁，吞吐量比轮询算法高，但是可能会出现局部数据过热等问题。
##### 3.Hash (源地址hash)
源地址哈希的思想是获取客户端访问Ip地址值，经过哈希计算得到一个值，用该值对服务器列表大小进行取模运算，得到的结果便是要访问的服务器序号。<br>
优势：同意客户端Ip，当后端服务器列表不变时，它每次都会被映射到同一台后端服务器访问。（根据此特征，可以在服务消费者与服务提供者之间建立有状态的session会话）。
##### 4.Weight Round Ronbin (加权轮询法)
##### 5.Weight Random (加权随机法)	
加权可以防止机器的抗压能力不同造成负载问题。
##### 6.Least Connections (最小连接数法)
####1.1.2 一致性hash算法
例如使用源地址ip Hash来进行负载均衡，通常只使用Hash算法会造成若一个机器挂掉，大量的数据会因为`hash算法`而进行节点之间的迁移，会造成很高的`成本开销`。所以使用`一致性hash`来避免这种问题。<br>
一致性哈希所带来的最大变化是把节点对应的哈希值变成了一个范围，而不再是离散的。在一致性哈希中，整个哈希值的范围定义非常大，然后把这个范围分配给现有节点。<br>
##### 一般的一致性哈希算法
如果有节点加入，那么这个新节点会从原有的某个节点上分管一部分范围哈希值；<br>
如果有节点退出，那么这个节点原本管理的哈希值会给它的下一个节点管理。<br>
###### 存在问题
每当新增一个节点，除了新增节点外，只有一个节点受影响，这个新增节点和受影响的节点负载是明显比其他节点低的。<br>
减少一个节点时，除了减去的节点外，只有一个节点受到影响，它要承担自己原来的和减去的节点的工作，压力要高于其它节点。似乎要增加一倍的节点或减去一倍的节点才能保持各个节点的负载均衡。
##### 虚拟节点对一致性哈希的改进
引入虚拟节点，即4个物理节点可以变为很多虚拟节点，每个虚拟节点支持连续哈希环上的一段。<br>
这时如果加入一个物理节点，就会相应加入很多虚拟节点。这些虚拟节点是相对均匀的插入到整个哈希环上的。这样就能够很好的分担现有物理节点的压力。如果减少一个物理节点，对应的很多虚拟节点就会失效，这样就会有很多剩余的虚拟节点承担之前虚拟节点的工作，胆识对于物理节点来说，增加的负载相对是均衡的。所以通过一个物理节点对应增加很多的虚拟节点，并且同一个物理节点的虚拟节点尽量均匀分布的方式来解决增加或减少节点时负载不均匀的问题。


### 1.2 分布式系统间通讯
#### 1 java 自身技术实现消息方式的系统间通讯
##### 1 基于消息方式实现系统间通讯
###### 1 TCP/IP+BIO
###### 2 TCP/IP+NIO
###### 3 TCP/IP+AIO
##### 2 开源框架实现系统间通讯
###### 1 Mina

#### 2 基于远程调用方式实现系统间通讯
##### 1 RMI(Remote Method Invocation)
客户端只有服务端提供的接口，通过此接口实现对远程服务端的调用。
##### 2 webService
##### 3 其他开源框架
###### 1 spring RMI
###### 2 CXF 
### 1.3序列化方案
#### 1 java内置序列化
#### 2 hessian 序列化方式
代码地址:<br>
https://github.com/asdgh1000/demo/tree/master/serialize-test/serializeMethod
###1.4 简单分布式事务实践（持续更新）
#### 1 XA 
XA是由X/Open组织提出的分布式事务的规范。XA规范主要定义了（全局）事务管理器（Transaction Manager）和（局部）资源管理器（Resource Manager）之间的接口。<br>
XA接口是双向的系统接口，在事务管理器（Transaction Manager）以及一个或多个资源管理器（Resource Manager）之间形成通讯桥梁。<br>
XA之所以需要引入事务管理器是因为：在分布式系统中，从理论上讲，两台机器理论上无法达到一致的状态，需要引入一个单点进行协调。<br>
事务管理器控制着全局事务，管理事务生命周期，并协调资源。资源管理器负责控制和管理实际资源（如数据库或JMS队列）。<br>
#### DTP (Distributed Transaction Processing Reference Model) 分布式事务处理模型
##### (Application Program) AP
应用程序：<br>
可以理解为使用DTP模型的程序，它定义了事务边界，并定义了构成该事务的应用程序的特定操作。<br>
AP和
##### (Resource Manager) RM
资源管理器：<br>
可以理解为DBMS系统，或者消息服务管理系统。应用程序通过资源管理器对资源进行控制，资源必须实现XA定义的接口，资源管理器提供了存储共享资源支持。<br>
##### (Transaction Manager) TM
事务管理器：<br>
负责协调和管理事务，提供给Ap应用程序编程接口并管理资源管理器。事务管理器向事务指定标识，监听它们的进程，并负责处理事务的完成和失败。<br>
######  XID
事务分支识别：<br>
由TM指定，以标识一个RM内的全局事务和特定分支。它是TM中日志与RM中日志之间的相关标记。两阶段提交或回滚需要XID，以便在系统启动时执行再同步操作（也称为再同步（resync）），或在需要时允许管理员执行试探操作（也成为手工干预）。
##### 事务
一个事务是一个完整的工作单元，由多个独立的计算任务组成，这多个任务在逻辑上是原子的。
##### 全局事务
一次性操作多个资源管理器的事务就是全局事务。
##### 分支事务
在全局事务中，每一个资源管理器有自己独立的任务，这些任务的集合是这个资源管理器的分支事务。
##### 控制线程
用来表示一个工作线程，主要是关联AP，TM和RM这三者的线程，也就是事务上下文环境，简单的说就是用来标识全局事务和分支事务关系的线程。

###1.5 分布式一致性
####2PC
两阶段提交是相对于单库的事务提交方式来说的。在单库上完成相关的数据操作后，就会直接提交或者回滚，而在分布式系统中，在提交前增加了准备阶段，所以称为两阶段提交。
##### 引入问题
1.由于事务管理器自身的稳定性，可用性的影响，以及网络通讯中可能产生的问题，出现的情况会很多。<br>
2.事务管理器在多个资源之间进行协调，它自身要进行很多日志记录工作。<br>
3.网络上的交互次数的增多以及引入事务管理器的开销，是使用两阶段提交协议使分布式事务的开销增大的两个方面。<br>
因此在进行垂直拆分或水平拆分后，需要想清楚是否一定要引入两阶段提交的分布式事务，有必要才使用。<br>
####3PC
####Basic Paxos
#### 最终一致
#### CAP
##### C(Consistency) 数据一致性
当数据写入成功后，所有的节点会同时看到这个新的数据。
##### A(Availability) 数据可用性
保证无论是成功还是失败，每个请求都能收到一个反馈，重点是系统一定要有响应。
##### P(Partition-Tolerance) 容错性
即使系统中有部分问题或者消息丢失，但系统仍然能够继续运行，也就是系统的一部分出现问题时，系统仍能继续工作。
##### 存在问题
CAP三个属性是不能同时完全满足的，通常来说需要进行权衡。<br>
1.选择CA:放弃分区容忍性，加强一致性和可用性。这其实就是传统单机数据库的选择。<br>
2.选择AP:放弃一致性，追求分区容忍性及可用性。这是很多分布式系统在设计时的选择，例如很多NoSql就是如此。<br> 
3.选择CP:放弃可用性，追求一致性和分区容忍性。这种选择下的可用性会比较低，网络的问题会直接让整个系统不可用。<br>
#### BASE
##### Basically Available 基本可用
允许分区失败
##### Soft state 软状态
接受一段时间内的状态不同步
##### Eventtually consistent 最终一致性
保证最终数据状态是一致的
#### Quorum
N:数据复制节点数量<br>
R:成功读操作的最小节点数<br>
W:成功写操作的最小节点数<br>
如果W+R>N是可以保证强一致性的，如果W+R<=N，是能保证最终一致性的.<br>
根据CAP理论，需要在一致性，可用性，容错性方面进行权衡。例如让W=N且R=1，就会大大降低可用性，但是一致性是最好的.<br>
#### Vector Clock
Vector Clock的思路是对同一份数据的每一次修改都加上<修改者，版本号>这样的一个信息，用于记录修改者的信息及版本号。<br>
算法为每一个商议结果附上一个时间戳，当结果改变时，更新时间戳。<br>

###1.6 分布式调度框架:zk
zookeeper核心其实类似一个精简的文件系统。
#### zk基本使用：
Apache开源分布式服务框架，可以使用zk来实现分布式架构中的：<br>
1.数据发布／订阅<br>
2.负载均衡<br>
3.命名服务<br>
4.分布式协调通知<br>
5.master选举<br>
#### zk的简单使用:
zk选master：<br>
项目目录：https://github.com/asdgh1000/demo/tree/master/zookeeper-test
###1.7 分布式服务框架：Dubbo
#### RPC:
远程过程调用协议,它是一种通过网络从远程计算机程序上请求服务，而不需要了解底层网络技术的协议。在分布式环境中，RPC保证了服务器节点之间的消息传输交互，让用户感觉是完全透明的。
#### Dubbo基本使用：
alibaba开源分布式服务框架，可以使用dubbo来处理分布式服务问题
随着网站规模的逐渐扩大分布式服务架构以及流动计算架构势在必行，此时，用于提高业务复用及整合的 分布式服务框架(RPC) 是关键。
#### Dubbo的简单实用：
利用zk作为数据发布订阅中心，实现服务的远程调用：<br>
项目目录：https://github.com/asdgh1000/demo/tree/master/dubbo-test
###1.8 分表分库方案
#### 1分表
#### 分表优势
对于大量数据存储在同一张表，会降低查询效率，此时分表能使查询效率加快很多。（随数据增长，查询速度不是线性下降，而是指数下降。）
##### 分表常见方案：
通常来说可以使用id作为分表字段，例如一张大表要分成200张表，可以使用（id%200）这样的方式确定是应该存储在张表。不过这样无论什么样的查询都需要加上id作为查询条件，否则会无法定位到相应的分表。
##### 分表瓶颈：
分表能解决单表数量过大带来的查询效率下降的问题，但是没办法给数据库的并发性能提供帮助，因为仅有Master节点能进行修改操作。面对高并发的读写操作，当数据库Master服务无法承载写操作压力时，不管如何扩展slave服务器，都没有帮助。

#### 2分库
#### 分库优势
对数据库拆分可以提高写的能力，从而提高系统的并发处理能力。
##### 分库常见方案
与分表策略类似，分库也可以采用通过一个关键字取模的方式来对数据访问进行路由。
例如分成200个库，则使用（id%200）的方式确定具体在哪个库.
##### 分库瓶颈 
单纯进行分库操作而不分表无法解决海量数据存储在一张表的问题。此时可以进行分表分库方案。
##### 数据库垂直拆分／水平拆分
###### 垂直拆分
把一个数据库中不同业务单元的数据分布到不同数据库中。(仍然有存在局部过热的情况)
####### 带来的问题
1.单机ACID保证被打破了，要么放弃原本了单机事务，要么引入分布式事务。<br>
2.一些join操作变的很困难，因为数据可能已经在两个数据库中了，所以不能很方便的利用数据库自身的join，需要其他方式解决。<br>
3.靠外键进行约束的场景会受影响。<br>
###### 水平拆分
把一个数据库中相同业务单元的数据分布到不同数据库中。(引入的问题会更加复杂)
####### 带来的问题
1.同样有可能有ACID被打破的情况。
2.同样可能有Join操作被影响的情况。
3.靠外键去进行约束场景会有影响。
4.依赖单库的自增序列生成唯一ID会受影响。
5.针对单个逻辑意义上的表的查询要跨库了。


#### 3分表分库
##### 分库分表优势
同时扩展系统的并发处理能力，又提升表的查询性能。
##### 分表分库常见方案
常见路由策略如下：<br>
中间变量＝id%(库数量*每个库的表数量)；<br>
库＝取整(中间变量/每个库的表数量)<br>
表＝中间变量%每个库的表数量<br>
#### 存在问题
1.原本表的事务上升成为分布式事务。<br>
2.由于纪录被切分到不同的库与不同的表中，难以进行多表关联查询
###1.9 分布式／集群session管理
#### 1 Session Sticky
##### 方案
利用类似源ip地址hash的方式使，每个相同客户端的请求总是访问一个相同的服务。
##### 瓶颈
1.如果任意一台web服务器宕机或重启，那这台机器上的会话就会丢失，如果会话中有登录状态数据，那么用户就要重新登录了。<br>
2.会话标识是应用层的信息，那么负载均衡器将要一个会话的请求都保存到同一个web服务器上的话，要进行应用层（第7层）的解析，这个开销比第四层大。<br>
3.负载均衡器变成了一个有状态的节点，要将会话保存到具体web服务器的映射。和无状态的节点相比，内存消耗会更大，容灾方面会更麻烦。<br>
#### 2 Session Replication
##### 方案
使用会话数据同步，通过同步保证不同web服务之间的session数据一致。
##### 瓶颈
1.同步session数据造成了网络带宽开销，只要session数据有变化，就需要将数据同步到所有机器，机器越多同步带来的网络开销就越大。<br>
2.没台web服务器都要保存session数据，如果整个集群的session数据很多，没太服务器保存的session内容占用会很多。<br>
#### 3 Session 数据集中存储
##### 方案
使用缓存或数据库集中存储起来，然后不同web服务器从同样的地方来获取session。
##### 瓶颈
1.读写session数据引入了网络操作，这相对于本机数据来说，问题就在于存在时延和不稳定性，不过我们的通讯基本都是发生在内网，问题不大。
2.如果集中存储Session的机器或集群有问题，就会影响我们的应用。
#### 4 Cookie Based
##### 方案
将Session数据放在Cookie中，然后在web服务器上从Cookie中生成对应的session数据。相比前面的集中存储，这个方案不会依赖外部的一个存储系统，也不存在从外部系统获取，写入Session网络延时。
##### 瓶颈
1.Cookie长度限制，会限制Session长度限制。<br>
2.安全性：Session数据本来都是服务端数据，而这个方案是让这些服务端数据到了外部网络及客户端，因此存在安全性问题。我们可以对写入Cookie的Session数据进行加密，不过对于安全来说，物理上不能接触才是安全的。<br>
3.消耗带宽，这里指的不是内部web服务器之间的带宽消耗，而是我们数据中心的整体外部带宽消耗。<br>
4.性能影响。每次HTTP请求和响应都带有Session数据，对web服务器来说，在同样处理情况下，响应的结果输出越少，支持的并发请求就会越多。<br>

###10 中间件
#### 1 远程过程调用和对象访问中间件
主要解决分布式环境下应用的互相访问问题，这也是支撑应用服务化的基础
#### 2 消息中间件
解决应用之间的消息传递，解耦，异步等问题。
##### 消息发送一致性定义
消息发送一致性是指产生消息的业务动作与消息发送的一致，就是说如果业务操作成功了，那么由这个操作产生的消息一定要发送出去，否则就丢消息了。另一方面，如果这个业务行为没有发生或者失败，就不应该把消息发送出去。
###### 存在的一致性问题
无论是先发消息后执行业务逻辑，还是先执行业务逻辑后发消息，都会产生消息不一致性问题（消息系统可能挂掉）
##### JMS (Java Message Service)
###### 概念
1.Destination:消息所走通道的定义，也就是用来定义消息从发送端发出后要走的通道，而不是最终接收方。Destination属于管理类的对象。<br>
2.ConnectionFactory:用于创建连接的对象。ConnectionFactory属于管理类的对象。<br>
3.Connection:连接接口,所负责的工作主要是创建Session。<br>
4.Session:会话接口，消息的发送者，接受者以及消息对象本身都由这个会话创建。<br>
5.MessageConsumer:消息的消费者，订阅消息并处理消息的对象。<br>
6.MessageProducer:消息的生产者，用来发送消息的都喜庆。<br>
7.XXXMessage:包括（BytesMessage,MapMessage,ObjectMessage,StreamMessage,TextMessage）五种。<br>
###### XA接口
ConnectionFactory,Connection,Session有着对应的XA接口，因为事务控制实在Session层面上的，而Session是通过Connection创建的，Connection是通过ConnectionFactory创建的，所以这三个接口需要有对应的XA接口。（Session，Connection，ConnectionFactory在Queue和Topic模型下对应的各个接口也存在XA系列接口）。

#### 3 数据访问中间件
主要解决应用访问数据库的共性问题的组件。






