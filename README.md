# iRPC
## 1. 项目说明
### 1.1 实现目标
+ 实现一个rpc框架，包括服务端，客户端和注册中心

## 2. 项目工作执行流程
### 2.1 服务端`Client`
+ `Proxy`动态代理类
  + 服务发现
  + 建立连接
  + 序列化调用的接口类、方法、参数
  + 接收结果、反序列化

### 2.2 服务端`Server`
+ 实现一些小的服务
  + 1. 计算类
  + 2. 服务类(例如数据库操作)
+ 提供服务(需要自己注册服务到注册中心)
+ 监听端口(监听RPC请求)
+ 返回操作结果

### 2.3 注册中心`Registry`
+ Registry注册类
  + 注册服务
  + 提供服务(根据服务类名, 方法名提供服务器的地址)

### 2.4 公共类`Common`
+ 序列化和反序列化
+ 线程池
+ 其他的工具类

## 3. 项目优化
### 3.1 跨语言客户端或者服务端
+ 实现多语言直接调用


