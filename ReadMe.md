## HCPPJoke

### 一、搭建 APP 基础架构
##### 1、创建自定义 navigation 注解处理器

利用注解处理器，在编译时收集每一个页面标记的注解信息，
收集到map集合中，由此生成destination.json文件。 

xml 里面的 navGraph 最终也是生成了 navGrap 给 controller 


步骤：
#####  1.1 、准备
创建两个 javalib ，libnavannotaion 和 libnavcompiler


两个库升级为 java8

需要把工程的 gradle-wrapper 和 gradle-plugin 分别降级为 4.10.1 和 3.2.0(AutoService与gradle版本不兼容)

如果不想降级，则额外添加依赖(别用 api)：
```
annotationProcessor 'com.google.auto.service:auto-service:1.0-rc6'
```


#####  1.2、创建两个注解处理器
创建 FragmentDestination 和 ActivityDestination 。

 
 之所以创建两个，是因为打开 Fragment 和 Activity 都需要对应的 controller 来处理。
 
 
 
##### 1.3、创建 Processor
libnavcompiler 下新建 NavProcessro 
创建完成之后，app 依赖自定义注解处理器。


同时指定编译的 java 版本。

##### 1.4、使用
通过注解的方式使用
```
@FragmentDestination(pageUrl =  "main/tabs/home",asStarter = true)

```
make project ，理论上会在 asstes 下生成文件。



有了这个 json ，会把这个 json 和 controller 关联，这样就达到了页面切换的目的。



#### 2、构建页面路由导航图
1、解析 json 生成对象。

2、创建 NavGraph 

3、 NavGraphBuilder.build() 进行应用，这样 xml 中，就不需要使用 nagGraph 了。 
```
        app:navGraph="@navigation/mobile_navigation" //不需要
```



#### 3、构建底部导航栏
