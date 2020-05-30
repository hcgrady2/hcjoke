## HCPPJoke
#### 一、创建自定义 navigation 注解处理器

利用注解处理器，在编译时收集每一个页面标记的注解信息，
收集到map集合中，由此生成destination.json文件。 

网络上大多是JAVA文件的创建，然而Assets目录JSON文件的创建需要一些奇技淫巧。先创建一个文件，位于编译后的class文件目录。
然后再把该文件的绝对路径做截取，
便能得到项目在电脑上的绝对路径(每个同学的项目在电脑上路径都不一样)
。再拼接上src/main/assets便能实现 Assets目录JSON文件的创建了。



```
//filer.createResource()意思是创建源文件
//我们可以指定为class文件输出的地方，
//StandardLocation.CLASS_OUTPUT：java文件生成class文件的位置，/app/build/intermediates/javac/debug/classes/目录下
//StandardLocation.SOURCE_OUTPUT：java文件的位置，一般在/ppjoke/app/build/generated/source/apt/目录下
//StandardLocation.CLASS_PATH 和 StandardLocation.SOURCE_PATH用的不多，指的了这个参数，就要指定生成文件的pkg包名了 
FileObject resource = filer.createResource(StandardLocation.CLASS_OUTPUT, "", OUTPUT_FILE_NAME); 
String resourcePath = resource.toUri().getPath(); 
messager.printMessage(Diagnostic.Kind.NOTE, "resourcePath:" + resourcePath); 

//由于我们想要把json文件生成在app/src/main/assets/目录下,所以这里可以对字符串做一个截取，
//以此便能准确获取项目在每个电脑上的 /app/src/main/assets/的路径
String appPath = resourcePath.substring(0, resourcePath.indexOf("app") + 4);
String assetsPath = appPath + "src/main/assets/";
```

xml 里面的 navGraph 最终也是生成了 navGrap 给 controller 

步骤：
#### 1、准备
创建两个 javalib ，libnavannotaion 和 libnavcompiler


两个库升级为 java8

需要把工程的 gradle-wrapper 和 gradle-plugin 分别降级为 4.10.1 和 3.2.0(AutoService与gradle版本不兼容)

如果不想降级，则额外添加依赖(别用 api)：
```
annotationProcessor 'com.google.auto.service:auto-service:1.0-rc6'
```


#### 2、创建两个注解处理器
创建 FragmentDestination 和 ActivityDestination 。

 
 之所以创建两个，是因为打开 Fragment 和 Activity 都需要对应的 controller 来处理。
 
 
 
#### 3、创建 Processor
libnavcompiler 下新建 NavProcessro 
创建完成之后，app 依赖自定义注解处理器。


同时指定编译的 java 版本。

#### 4、使用
通过注解的方式使用
```
@FragmentDestination(pageUrl =  "main/tabs/home",asStarter = true)

```
make project ，理论上会在 asstes 下生成文件。


