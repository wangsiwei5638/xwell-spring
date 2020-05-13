+ 容器框架 主要功能模块 AOP IOC DI MVC 

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