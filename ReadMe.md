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
make project ，理论上会在 asstes 下生成文件。随便修改注解的名字，destination.json 这个文件会自动生成。



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


### 六、




### 七、WorkManager
可以自动维护后台任务的执行时机，执行顺序和执行状态。

比 Service 和 InterService 更轻量，兼容 api 14。

支持异步、链式调用，同时支持搭配 liveData。


支持任务执行次序，任务之间依赖关系。

```
WorkContinuation left,right;

left = workManager.beginWith(A).then(B);
right = workManager.beginWith(C).then(D);

WorkContinuation.combine(Arrays.asList(left,right)).then(E).Enqueue(); 

```


####  任务状态：

+ BLOCKED
+ ENQUEUED
+ RUNNING (SUCCESSED,CANCELED,FAILED)
+ FINISHED


#### 任务控制：

cancelWorkById(UUID) : 取消一个

cancelAllWokByTag(String): 通过 Tag 取消所有任务

cancelUniqueWork(String) : 通过名字取消一个唯一任务


#### 核心类
+ Worker
抽象类，继承此类执行任务。

+ WorkRequest
有两个子类，OneTimeWorkRequest 和 PeriodicWorkRequest，用来指定让哪个 Worker 执行任务，指定执行环境和执行顺序等。

+ WorkManager
管理任务请求和任务队列，发起的 WorkRequest 会进入它的任务队列。

+ WorkStatus
包含任务的状态和任务的信息，以 LiveData 的形式提供给观察者。


#### WorkManager 的使用
https://juejin.cn/post/6844903953570725896#heading-26
https://blog.csdn.net/qq_15988951/article/details/105867817


#####   使用步骤

1、

继承 Worker ，实现里面的 doWork 方法处理异步任务

```
public class UploadFileWorker extends Worker {

    public UploadFileWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        Data inputData = getInputData();
        String filePath = inputData.getString("file");
        String fileUrl = FileUploadManager.upload(filePath);
        if (TextUtils.isEmpty(fileUrl)){
            return Result.failure();
        }else{
            Data outputData = new Data.Builder().putString("fileUrl", fileUrl)
                    .build();
            return Result.success(outputData);
        }
    }
}
```

2、定义 WorkRequest 定义 Work 执行的约束条件
```
        OneTimeWorkRequest request = new OneTimeWorkRequest
                .Builder(UploadFileWorker.class)
                .setInputData(inputData)
                .setConstraints(constraints)
//                .setConstraints(constraints)
//                //设置一个拦截器，在任务执行之前 可以做一次拦截，去修改入参的数据然后返回新的数据交由worker使用
//                .setInputMerger(null)
//                //当一个任务被调度失败后，所要采取的重试策略，可以通过BackoffPolicy来执行具体的策略
//                .setBackoffCriteria(BackoffPolicy.EXPONENTIAL, 10, TimeUnit.SECONDS)
//                //任务被调度执行的延迟时间
//                .setInitialDelay(10, TimeUnit.SECONDS)
//                //设置该任务尝试执行的最大次数
//                .setInitialRunAttemptCount(2)
//                //设置这个任务开始执行的时间
//                //System.currentTimeMillis()
//                .setPeriodStartTime(0, TimeUnit.SECONDS)
//                //指定该任务被调度的时间
//                .setScheduleRequestedAt(0, TimeUnit.SECONDS)
//                //当一个任务执行状态编程finish时，又没有后续的观察者来消费这个结果，难么workamnager会在
//                //内存中保留一段时间的该任务的结果。超过这个时间，这个结果就会被存储到数据库中
//                //下次想要查询该任务的结果时，会触发workmanager的数据库查询操作，可以通过uuid来查询任务的状态
//                .keepResultsForAtLeast(10, TimeUnit.SECONDS)
                .build();


```


3、将 request 加入队列等待调度
```


        UUID uploadRequestId= request.getId();
        //每一个request对应一个UUID,通过这个ID，可以监听该任务的一些状态及执行结果


        WorkContinuation workContinuation = WorkManager.getInstance(StudyMainActivity.this)
                .beginWith(request);



        workContinuation.enqueue();

```

4、通过 LiveData 监听执行结果
```


//通过返回的LiveData，监听任务的执行结果及UI更新
        workContinuation.getWorkInfosLiveData().observe(StudyMainActivity.this, new Observer<List<WorkInfo>>() {
            @Override
            public void onChanged(List<WorkInfo> workInfos) {
                //block runing enuqued failed susscess finish
                for (WorkInfo workInfo : workInfos) {
                    WorkInfo.State state = workInfo.getState();
                    Data outputData = workInfo.getOutputData();
                    UUID uuid = workInfo.getId();

                    if (state == WorkInfo.State.FAILED) {
                        if (uuid.equals(uploadRequestId)) {

                            Log.i(TAG, "onChanged: 文件上传任务失败了");

                        }
                    } else if (state == WorkInfo.State.SUCCEEDED) {
                        String fileUrl = outputData.getString("fileUrl");
                        if (uuid.equals(uploadRequestId)) {

                            Log.i(TAG, "onChanged: 文件上传任务成功了");

                        }
                    }
                }
            }
        });

```





##### 数据交互
通过 Data 进行
```
  Data inputData = new Data.Builder()
                .putString("file", filePath)
                .build();

        OneTimeWorkRequest request = new OneTimeWorkRequest.Builder(UploadFileWorker.class)
                .setInputData(inputData)
                .build();
```


```
  Data inputData = getInputData();
        String filePath = inputData.getString("file");
```



#### 设置约束条件
```

OneTimeWorkRequest request = new OneTimeWorkRequest
                .Builder(UploadFileWorker.class)
                .setInputData(inputData)
                .setConstraints(constraints)
//                .setConstraints(constraints)
//                //设置一个拦截器，在任务执行之前 可以做一次拦截，去修改入参的数据然后返回新的数据交由worker使用
//                .setInputMerger(null)
//                //当一个任务被调度失败后，所要采取的重试策略，可以通过BackoffPolicy来执行具体的策略
//                .setBackoffCriteria(BackoffPolicy.EXPONENTIAL, 10, TimeUnit.SECONDS)
//                //任务被调度执行的延迟时间
//                .setInitialDelay(10, TimeUnit.SECONDS)
//                //设置该任务尝试执行的最大次数
//                .setInitialRunAttemptCount(2)
//                //设置这个任务开始执行的时间
//                //System.currentTimeMillis()
//                .setPeriodStartTime(0, TimeUnit.SECONDS)
//                //指定该任务被调度的时间
//                .setScheduleRequestedAt(0, TimeUnit.SECONDS)
//                //当一个任务执行状态编程finish时，又没有后续的观察者来消费这个结果，难么workamnager会在
//                //内存中保留一段时间的该任务的结果。超过这个时间，这个结果就会被存储到数据库中
//                //下次想要查询该任务的结果时，会触发workmanager的数据库查询操作，可以通过uuid来查询任务的状态
//                .keepResultsForAtLeast(10, TimeUnit.SECONDS)
                .build();
                
                
```



##### 按照 a->b->c 顺序执行
```

      OneTimeWorkRequest requestA = new OneTimeWorkRequest.Builder(UploadFileWorker.class)
                .build();
        OneTimeWorkRequest requestB = new OneTimeWorkRequest.Builder(UploadFileWorker.class)
                .build();
        OneTimeWorkRequest requestC = new OneTimeWorkRequest.Builder(UploadFileWorker.class)
                .build();
        
        WorkManager.getInstance(PublishActivity.this).beginWith(requestA).then(requestB).then(requestC).enqueue();



```



##### ab -> cd ->e 执行
```

 OneTimeWorkRequest requestA = new OneTimeWorkRequest.Builder(UploadFileWorker.class)
                .build();
                
        OneTimeWorkRequest requestB = new OneTimeWorkRequest.Builder(UploadFileWorker.class)
                .build();
                
        OneTimeWorkRequest requestC = new OneTimeWorkRequest.Builder(UploadFileWorker.class)
                .build();
                
        OneTimeWorkRequest requestD = new OneTimeWorkRequest.Builder(UploadFileWorker.class)
                .build();
                
        OneTimeWorkRequest requestE = new OneTimeWorkRequest.Builder(UploadFileWorker.class)
                .build();

        WorkContinuation chainA = WorkManager.getInstance(PublishActivity.this).beginWith(requestA).then(requestB);
        WorkContinuation chainB = WorkManager.getInstance(PublishActivity.this).beginWith(requestC).then(requestD);

        List<WorkContinuation> chains =new ArrayList<>();
        chains.add(chainA);
        chains.add(chainB);
        
     WorkContinuation.combine(chains).then(requestE).enqueue();
     
     
     
```

### 总结复习
根据注解生成了 destination.json 文件，里面保存了 pageUrl 和 name 。
在 NavGraphBuilder 会解析这个 json 文件，将 url 和 Fragment 进行关联。

```
        FixFragmentNavigator.Destination destination = fragmentNavigator.createDestination();
                destination.setId(node.id);
                destination.setClassName(node.className);
                destination.addDeepLink(node.pageUrl);
                navGraph.addDestination(destination);
                
```



#### 1、底部导航
BottomNavigationVi  ew: 底部的导航

NavHostFragment ： 跳转


#### 2、databing
启用 databing 之后，布局文件，就是  layout + data 标签的结构。
data 标签可以指定数据 bean
```
<data>

        <import type="PeopleBean"/>

        <variable
            name="data"
            type="PeopleBean" />

        <variable
            name="data2"
            type="PeopleBean" />

        <variable
            name="data3"
            type="PeopleBean" />

</data>
```
使用这个 bean:

```
            android:text="@{String.valueOf(data.age)}"

```
使用 databinding，并设置数据，设置的数据就会显示到界面上
```
 //实力化ActivityMainBinding
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        //实力化PeopleBean
        peopleBean = new PeopleBean("NorthernBrain", 25);

        //布局中variable标签中的name是data，所以这里是setData
        activityMainBinding.setData(peopleBean);
```

赋值和使用默认值
```
activityMainBinding.textView2.setText("我是新的值");

android:text="@{data.name,default = HelloWorld}"

```

databinding 的单向数据绑定，有三种方式：


+ BaseObservable


```
notifyPropertyChanged(); 只会刷新属于它的UI，就如代码，他只会更新name。
notifyChange(); 会刷新所有UI


public class PeopleBean extends BaseObservable {

    @Bindable
    public String name;

    private int age;

    public PeopleBean(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    public void setName2(String name){
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
        notifyChange();
    }
}





```


+ ObservableField
```
public class PeopleBean extends BaseObservable {

    public final ObservableField<String> name;
    public final ObservableField<Integer> age;

    public PeopleBean(ObservableField<String> name, ObservableField<Integer> age) {
        this.name = name;
        this.age = age;
    }

    public ObservableField<String> getName() {
        return name;
    }

    public ObservableField<Integer> getAge() {
        return age;
    }
}

```



+ OvervableCollection
更新集合的，同样 DataBinding 还提供了 ObservableMap 和 ObservableList。


databinding 双向数据绑定：也就是 ui 更新之后，数据也需要更新。

```
android:text="@={databean.data}"

```


#### 3、Paging
学习参考：
http://littlecurl.xyz:8080/articles/2020/01/12/1578831362377.html

+ PagedList        ：  从 DataSource 获取数据。
+ PagedList        ：  使用 PagedListAdapter。
+ AsyncListUtil    ：  可以异步加载内容。
+ DataSouce        :   数据源，用来获取数据。
+ PageList         :   承载数据，代表一页数据。
+ PagedListAdapter :   配合 PagedList 使用的 Adapter ，不能直接使用 RecyclerView 的 Adapter  。



一般框架：
1、定义一个 DataSource

里面定义初始化加载数据和加载更多数据的方法，Paging 框架自动回调。

DataSource 可以是 ItemKeyedDataSource 或者 PositonalDataSource 。


2、继承 DataSource.Factory 来返回 DataSource ,提供 Paging 调用



3、创建 ViewwModel

viewModel 里面，通过 LivePagedListBuilder 来返回 LiveData<PagedList> 可观测对象
```
        convertList = new LivePagedListBuilder<>(concertFactory, 20).build();
```


4、Activity 使用
```
     ConcertViewModel viewModel =
                ViewModelProviders.of(this).get(ConcertViewModel.class);

        viewModel.getConvertList().observe(this, concerts -> adapter.submitList(concerts));

```



#### 4、LiveData & MutableLiveData

+ LiveData 可被观察，可感知他们属于的界面的生命周期 。
+ LiveData 的数据来源一般都是 ViewModel 。
+ LiveData 需要检查观察者的状态，当是 actie 状态时，才去更新。
+ LiveData 是一个抽象累，不能直接使用，MutableLiveData 是最简单的一个实现。


MultableLiveData

1、在 ViewModel 里面定义 MutableLiveData

```
public class ViewModelWithLiveDate extends ViewModel {

    private MutableLiveData<Integer> mutableLiveData ;

    public MutableLiveData<Integer> getMutableLiveData() {
        if(mutableLiveData == null){
            mutableLiveData = new MutableLiveData<>();
            mutableLiveData.setValue(0);
        }

        return mutableLiveData;
    }

    public void addMutablelivedata(int n ){
        mutableLiveData.setValue(mutableLiveData.getValue()+n );
    }

}
```

2、viewModel 获取这个 LiveData 并监听

```
      viewModelWithLiveDate = ViewModelProviders.of(this).get(ViewModelWithLiveDate.class); 
        viewModelWithLiveDate.getMutableLiveData().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                textView.setText(String.valueOf(integer));
            }
        });
```



LiveData 就是数据 Bean

```
public class DemoData extends LiveData<DemoData> {
    private int tag1;
    private int tag2;
    
    public int getTag1() {
        return tag1;

    }
    public void setTag1(int tag1) {
        this.tag1 = tag1;
        postValue(this);
    }

    public int getTag2() {
        return tag2;
    }

    public void setTag2(int tag2) {
        this.tag2 = tag2;
        postValue(this);
    }
}
```



3、setValue 和 postValue
setValue 只能在主线程调用，postValue 可以在任何线程调用。



4、Transformations#map() 和 Transformations#switchMap
Transformations#map() 可以进行 LiveData 的数据转换。
Transformations#switchmap 可以选择不同的数据源。

```
var triggerLiveData = MutableLiveData<Boolean>()
val targetLiveData = Transformations.switchMap(triggerLiveData){
	if(it){
		repository.getTargetLiveDataFromKK()
	}else{
		repository.getTargetLiveDataFromJJ()
	}
}

```

#### 5、AndroidViewModel 和 ViewModel

ViewModel 可以感知生命周期，管理数据，数据一直保持在内存中，如屏幕旋转之后，数据可以继续保存。

onSaveInstanceState() 也可以保存和恢复数据，但是只能保存少量的数据。

AndroidViewModel 创建时会加入 Application 。


ViewModel 一般都和 LiveData 一起使用。

两个 Fragment 可以使用他们所属于的 Activity 的 ViewModel 来实现数据通信。





