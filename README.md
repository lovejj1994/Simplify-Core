# Simplify-Core

 
Simplify 为简化重复的JAVA代码而生，基于JDK8，无其它jar包依赖，提供序列化，json parse/generator，日期处理,asm && jdkproxy 实现动态代理功能 等常见操作。

Simplify was built to Simplify repetitive Java code. It is JDK8 based and has no dependencies on other JAR packages. Simplify provides common operations such as serialization, JSON parse/generator, date handling, and ASM && JDKProxy for dynamic proxy functionality.

<br />

> **历史版本(history):**
> - 2017.6.27 1.0.0.M1 发版  
> - 2017.7.8 1.0.0.M2 发版
> - 2017.8.28 1.0.0.M3 发版 
> - 2017.9.24 1.0.0.M4 发版 
<br />

> **开发日志(DevLog):**
> - 2017.7.8 这版主要做的还是json parse的完善，下一版重点是日期和其他一些小众类型的优化支持
> - 2017.8.28  1.0.0.M3版对json的功能封版，1.0.0.M4 开始开发 代理功能的小工具，以模仿实现spring aop 中的jdkProxy和cglib中的功能
> - 2017.8.29  做 1.0.0.M4版 发现了新领域，cglib的原理是asm字节码对class文件的干预实现代理，所以要开始学asm和字节码相关的知识了，自己加油~！
> - 2017.9.24  用asm框架实现自己的动态代理框架。至此Simplify-Core封装了jdkProxy和asm动态代理功能。
<br />

> **Maven:**
>```
><dependency>
>    <groupId>cn.xxywithpq</groupId>
>    <artifactId>Simplify-Core</artifactId>
>    <version>1.0.0.M4</version>
></dependency>
>```

> **使用说明(instructions):**  
> - 每个功能在源代码中都有完整的测试用例，详情见test源码。后期会补充在README中

<br />

> **其它(others):**  
> - 欢迎访问 [我的博客](http://blog.csdn.net/lovejj1994)
> - Welcome to [my blog](http://blog.csdn.net/lovejj1994)