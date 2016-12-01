# java生产项目常用的demo

[TOC]

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
1.`减少锁的覆盖范围（如：表锁->行锁）`
2.`增加锁上可并行的线程数（如：读锁写锁分离，允许并行读取数据）`
3.`选择正确锁的类型（如：悲观锁，乐观锁）`

##四.简单应用
###1.1 高并发秒杀系统（持续更新）
项目目录：https://github.com/asdgh1000/demo/tree/master/secoundKill
###1.2 简单代理模式demo（持续更新）
项目目录：https://github.com/asdgh1000/demo/tree/master/proxyDemo
###1.3 简单spring事务demo（持续更新）
项目目录：https://github.com/asdgh1000/demo/tree/master/springTrancation
##分布式
![Alt text](./distrubuteSystem.png)
###1.4 简单分布式事务实践（持续更新）
###1.5 分布式一致性
####2PC
####3PC
####Basic Paxos
###1.5 分布式调度框架:zk
#### zk基本使用：
Apache开源分布式服务框架，可以使用zk来实现分布式架构中的：
1.数据发布／订阅
2.负载均衡
3.命名服务
4.分布式协调通知
5.master选举
#### zk的简单使用:
zk选master：
项目目录：https://github.com/asdgh1000/demo/tree/master/zookeeper-test
###1.6 分布式服务框架：Dubbo
#### RPC:
远程过程调用协议,它是一种通过网络从远程计算机程序上请求服务，而不需要了解底层网络技术的协议。在分布式环境中，RPC保证了服务器节点之间的消息传输交互，让用户感觉是完全透明的。
#### Dubbo基本使用：
alibaba开源分布式服务框架，可以使用dubbo来处理分布式服务问题
随着网站规模的逐渐扩大分布式服务架构以及流动计算架构势在必行，此时，用于提高业务复用及整合的 分布式服务框架(RPC) 是关键。
#### Dubbo的简单实用：
利用zk作为数据发布订阅中心，实现服务的远程调用：
项目目录：https://github.com/asdgh1000/demo/tree/master/dubbo-test

