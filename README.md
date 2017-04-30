## 概要
1. Catalina 是 Tomcat 中 Servlet 容器的代号；
2. 连接器（connector）是用来“连接”容器里边的请求的，即接收每一个 HTTP 请求构造一个 request 和 response 对象，然后把流程传递给容器；
3. 容器（container）从连接器接收 request 和 response 对象之后调用 servlet 的 service 方法用于响应；
4. 加载器模块用于加载 servlet 类；
5. 管理模块用于处理用户会话，把它们管理起来；