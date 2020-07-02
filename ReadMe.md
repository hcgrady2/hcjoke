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



#### 4、定制 Fragment 导航器
1、默认 repleace 会导致生命周期重新走一遍。

2、如果替换成定制的导航器，fragment切换时还能多次打印onCreateView日志。
请检查FixFragmentNavigate的navigate方法中ft.add(id%2Cfragment%2Ctag)+方法，有没有把tag传递进去。

```
    //navController = Navigation.findNavController(this,R.id.nav_host_fragment);
       // NavGraphBuilder.build(navController);
```
这样的代码，每次切换生命周期都要走。

```

        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        navController = NavHostFragment.findNavController(fragment);
        NavGraphBuilder.build(this, fragment.getChildFragmentManager(), navController, fragment.getId());
```

这样生命周期只初始化一次。



### 二、网络和数据库
#### 1、配置允许明文
```
        android:usesCleartextTraffic="true"

```


#### 2、泛型类型
java 5 开始 ,calss 文件中，没有显示明确声明泛型类型的，在编译器编译阶段，就会泛型擦除。

如，new ArrayList<User> 就会被擦除。


但是，new Interface 传递的泛型，可以在运行时获取，因为会编译成 Interface 的匿名内部类。
```
class InnerClass implements Interface<List<User>>{
    ParameterizedType type  = ..
    ///... 
    
}
```



### 三、数据库封装
Room,通过注解来使用数据库，可以和 LiveData,LifeCycle,Paging 融合使用。


#### 1、配置导出 数据库配置文件

@Database 注解中可以选择是否导出配置文件，如果要导出，需要 build.gradle 中进行配置。
 
```
  javaCompileOptions{
            annotationProcessorOptions{
                arguments=["room.schemaLocation":"$projectDir/schemas".toString()]
            }
        }
```

#### 2、注解说明
@Dao : 表示可以操作数据

@ColumnInfo ： 修改表名

@Insert(onConflict = OnConflictStrategy.REPLACE) : 插入冲突策略。


@Query("select * from cache where 'key' = :key") : 查询

@Delete   ： 删除


@Update(onConflict = OnConflictStrategy.REPLACE) 更新

@DataBase : 表示数据库


@Embedded : Bean 中的 Bean 也会生成数据表中的列。


@Ignore : 忽略某个字段



@Index : 


@Relation : 关联查询

@TypeConverter : 类型转换用




#### 3、使用 Room 缓存



### 三、Tab 架构搭建
#### 1、dataBinding
开启
```
dataBinding{
    enabled = true
}
```

BindingAdapter注解
BindingAdapter 和 static 方法中参数必须一一对应。


### 四、其他

paging 的 DiffUtil 原理是 Myers 差分算法。





### 五、分享面板
1、分享面板上的菜单是通过 packagemanger 解析已安装应用来获取的。


2、AlertDialog 需要 AndroidX 下的。


3、




todo:
2、paging demo
3、后台部署




