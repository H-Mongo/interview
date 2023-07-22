#### 一、netty中的ChannelOption<?>模型

第一眼看到这个模型有点很蒙，一个Bootstrap或者ServerBootstrap实例的`option方法`居然可以写入不同的value（可能我比较傻，见到的写法太少了！）。
感觉很牛逼很有意思，就在想这个是怎么做到的呢？在进行了代码调用和查看后大概理解了一点，所以就简单的记录了一下。
> 这里面对常量类进行了接口抽象，不同维度的抽象类都会存在自己的常量池。每个常量类都存在一个唯一的名称和一个唯一的ID（这个ID属于当前这个维度下）
> 而这个option方法采用了泛型化描述，所以可以一个方法搞定option属性的设置！这个常量池化的抽象思想真的很牛逼。当需要获取option所绑定的值时，
> 通过判断实例是否相同即可，在进行数据的获取操作

#### 二、dubbo的服务暴露机制

阅读的dubbo源码的过程中，探究了spi、dubbo协议、client、server、export等组件及特性的加载、实例化、绑定、初始化源码。在阅读的过程中，各个组件源码
之间跳来跳去，似乎有点晕了！我一直在想一个问题：那么dubbo如何进行rpc-provider服务配置、实例化以及暴露呢？对于spring的启动，阅读过源码的话都知道它核心代码就在
`AbstractApplicationContext`对象的`refresh`方法里（这里面有13个方法，具体多少个我也没仔细数，网上都这么说！版本的不同可能方法也会有变化）。

##### 1） dubbo服务配置（与spring进行结合）

+ SpringBootStarter机制
+ spring注解驱动
+ spring配置文件

##### 2） spring识别dubbo维度的bean并完成实例化及注入

要想让spring识别一个dubbo-bean还真是一个简单的事，当时也并非难事！阅读过spring源码的话，应该很清楚spring给我们提供了很多扩展机制来让我们对bean进行
增强处理，常见的手段有：实现Aware相关接口、自定义BeanFactoryPostProcessor、BeanPostProcessor等方式。当然dubbo框架也不列外，也是采用了这种手段
来对dubbo服务相关的bean进行识别后暴露的！
![dubbo源码spring增强模块](../image/dubbo/dubbo-config-spring.png)
这部分模块的代码帮我们详细的描述了如何扫描一个dubbo配置文件（xml），如何进行bean增强处理等与spring进行适配融合相关的代码，很值得去深究和理解！
