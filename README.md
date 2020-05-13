# xwell-rpc

### 简介
模仿spring实现自己的容器框架，取其精华用之。
xwell-spring实现了控制反转、依赖注入、web请求处理、切面等功能，可通过该项目更好的理解spring源码

### 流程
+ 配置阶段
    + 设置web.xml
    + 设置init-param
    + 设置url-pattern
    + 配置注解
+ 初始化阶段
    + 入口servlet中初始化IOC容器与springMVC组件
    + IOC容器初始化
    + 扫描相关类
    + IOC实例化并保存至容器map中
    + DI操作
    + 初始化HandlerMapping
+ 运行阶段
    + webmvc服务匹配HanderMapping
    + 反射调用方法
    + 返回写入浏览器

### 主要类与职责
+ IOC & DI
    + ApplicationContex：xwell-spring的主入口
    + BeanDefinitionReader：读取配置文件并解析，
    + BeanDefinition：保存配置的元信息
    + BeanWrapper：保存原实例对象的应用
    
+ MVC
    + HandlerMapping：保存URL于method关系的组件
    + HanderAdapter：运行阶段动态匹配参数的组件
    + ModeAndView:用来存储方法调用之后的参数和返回值
    + ViewResolvers:根据ModeAndView信息决定读取对应模板页面，并且调用模板引擎解析
    + View：完成渲染后的字符串（HTML) 调用response.getWriter.write("...");
    
+ AOP
    + 