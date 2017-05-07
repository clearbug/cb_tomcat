## 概要
1. Catalina 是 Tomcat 中 Servlet 容器的代号；
2. 连接器（connector）是用来“连接”容器里边的请求的，即接收每一个 HTTP 请求构造一个 request 和 response 对象，然后把流程传递给容器；
3. 容器（container）从连接器接收 request 和 response 对象之后调用 servlet 的 service 方法用于响应；
4. 加载器模块用于加载 servlet 类；
5. 管理模块用于处理用户会话，把它们管理起来；

## 第一章：一个简单的 Web 服务器
### 相关协议
1. RFC2616，HTTP/1.1
2. RFC2396，统一资源定位器 URL

## 第二章：一个简单的 Servlet 容器

## 第三章：连接器
### Servlet 规范版本
1. Servlet 2.3 和 2.4

### Tomcat 版本
1. Tomcat 4（本章建立的连接器是第 4 章讨论的 Tomcat4 的默认连接器的一个简化版本。虽然这个连接器在 Tomcat 4 中是不推荐使用的，但它仍然可以作为一个非常棒的学习工具。）

### StringManager 类
1. 一个像 Tomcat 这样的大型应用需要仔细的处理错误信息。
2. 在 Tomcat 中，错误信息对于系统管理员和 servlet 程序员都是有用的。
3. StringManager 类使用了单例模式（singleton）。