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
+ 初始化阶段1（容器部分）
    + 入口servlet中初始化IOC容器与springMVC组件
        + 通过配置文件找到扫描包
        + 扫描类，将类信息注册到bean定义map中，此时未实例化对象
        + 调用getBean()生成实例对象，懒加载除外
    + IOC实例化并保存至容器map中
        + 实例化对象本质就是调用getBean()方法，通过反射创建对象
    + DI操作
        + 先将注册的对象实例化，查看该类是否有被标注自动注入的注解
        + 如果有注解，并且该字段被xwell-spring容器容器托管，则创建实例，如果已经有实例，则从缓存中获取
        + 重复第二步操作，递归去查询注入对象中是否还有需要注入的对象，一步步创建对象通过反射注入
+ 初始化阶段2（MVC部分） 
    + 初始化multipartResolver
        + 用于处理上传的组件
    + 初始化initLocaleResolver
        + 用于解析语言环境、国际化
    + 初始化themeResolver
        + 主题模板解析器
    + 初始化handlerMapping
        + 处理器映射器，用于保存URL的映射关系
    + 初始化handlerAdapter
        + 用于动态参数适配，让servlet调用controller进行handle
    + 初始化handlerExceptionResolvers
        + 负责MVC中的异常处理、拦截
    + 初始化requestToViewNameTranslator
        + 视图提取器，从请求中获取视图名称
    + 初始化viewResolvers
        + 视图解析转换到模板引擎
    + 初始化flashMapManager
        + 缓存参数，例如strust2中的值栈
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