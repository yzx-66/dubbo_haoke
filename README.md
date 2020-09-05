# dubbo微服务租房项目

#### 介绍
练习的综合项目 主要熟悉微服务框架Dubbo与练习其他技术

#### 部分截图
![](https://gitee.com/yzx66/haoke/raw/master/1.png)

![](https://gitee.com/yzx66/haoke/raw/master/2.png)

![](https://gitee.com/yzx66/haoke/raw/master/3.png)

![](https://gitee.com/yzx66/haoke/raw/master/4.png)
![](https://gitee.com/yzx66/haoke/raw/master/5.png)
![](https://gitee.com/yzx66/haoke/raw/master/6.png)

#### 使用技术

* 后端：SpringBoot + Dubbo + Zookeeper + MybatisPlus + MongoDB + Redis + ElasticSearch + GraphQL + RocketMQ + WebScoket + OSS

* 前端：React + 微信小程序

#### 笔记
**项目中所记（时间 2019.10，部分博客或其内容不一定与项目相关，是发散出来的知识）**
* 网络爬虫框架：Webmagic：https://blog.csdn.net/weixin_43934607/article/details/101646256
* TKMybatis和MybatisPlus、Lomback：https://blog.csdn.net/weixin_43934607/article/details/102540483
* API查询语言GraphQL（一）：https://blog.csdn.net/weixin_43934607/article/details/102625390
* API查询语言GraphQL（二）：https://blog.csdn.net/weixin_43934607/article/details/102668893
* Redis搭建三主三从的两种方式与SpringBoot整合Redis集群：https://blog.csdn.net/weixin_43934607/article/details/102668918
* WebSocket快速上手的用法：https://blog.csdn.net/weixin_43934607/article/details/102674350
* RocketMQ使用Docker搭建二主二从集群：https://blog.csdn.net/weixin_43934607/article/details/102734663
* RocketMQTemplate的比较使用：https://blog.csdn.net/weixin_43934607/article/details/102749018
* 在SpringBoot中使用RocketMQ原生API：https://blog.csdn.net/weixin_43934607/article/details/102756379
* Elasticsearch分词器与进阶知识：https://blog.csdn.net/weixin_43934607/article/details/102788229
* MongoDB地理位置索引：https://blog.csdn.net/weixin_43934607/article/details/102752532
* MySQL集群（一）主从复制、PXC集群：https://blog.csdn.net/weixin_43934607/article/details/102674350
* MySQL集群（二）：MyCat中间件、Haproxy负载均衡：https://blog.csdn.net/weixin_43934607/article/details/102769179
* MySQL集群（三）：搭建混合集群综合应用：https://blog.csdn.net/weixin_43934607/article/details/102784120
* Mongodb集群之复制集和分片集：https://blog.csdn.net/weixin_43934607/article/details/102792808
* Elasticsearch集群搭建与相关知识：https://blog.csdn.net/weixin_43934607/article/details/103077157


**其余非项目时间的相关技术笔记**
* SpringBoot（一）：https://blog.csdn.net/weixin_43934607/article/details/100055620
* SpringBoot（二）https://blog.csdn.net/weixin_43934607/article/details/100111858
* SpringBoot（三）https://blog.csdn.net/weixin_43934607/article/details/100115270
* Zookeeper特性与分布式锁：https://blog.csdn.net/weixin_43934607/article/details/100141396
* Dubbo及高级特性：https://blog.csdn.net/weixin_43934607/article/details/100141435
* Redis作分布式锁与集群搭建：https://blog.csdn.net/weixin_43934607/article/details/100141334
* Redis命令与二级缓存和分布式Session：https://blog.csdn.net/weixin_43934607/article/details/100141311
* SpringBoot整合WebSocket与Socketjs心跳重连实现：https://blog.csdn.net/weixin_43934607/article/details/100149396
* RocketMQ核心知识：https://blog.csdn.net/weixin_43934607/article/details/100141464
* SpringBoot整合MangoDB查询与特性：https://blog.csdn.net/weixin_43934607/article/details/100149391
* Elasticsearch命令：https://blog.csdn.net/weixin_43934607/article/details/100141845
* SpringBoot整合Elasticsearch：https://blog.csdn.net/weixin_43934607/article/details/100149363
* 文件服务器（OSS、Nginx）与 Nginx负载均衡：https://blog.csdn.net/weixin_43934607/article/details/103059810

#### 做完该项目的总结
* 从项目本身来说，这个项目涉及的业务逻辑并不多，主要在使用技术本身，所以作为练习项目还是具有较高的性价比。
* 从技术角度来说，接触的全新技术有GraphQL和Webmagic，虽然新技术不多，但是学到的集群搭建知识颇多，而且均以Docker为主，所以还可以较好的习惯Docker。
* 从选型的对比来说，Dubbo在使用中明显能感觉到比SpringCloud少挺多组件，比如网关、断路器、配置中心等等，同时对服务降级、服务监控这些微服务框架基本要求感觉支持不够，但基于RPC的调用比基于Http的rest还是有一定优势，不过以后如果小团队开发，还是要选SpringCloud，尤其现在阿里也在开发SpringCloud组件，所以以后可能还是SpringCloud为主流。


#### 视频教程
链接：https://pan.baidu.com/s/154P9LN5kOBylObjoA3ohmw 
提取码：s9bp
