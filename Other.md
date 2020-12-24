#### 1、序列化和反序列化示例
```

//对象输入输出流，对象持久化操作，字节流的子类
public class Serializtion {
	public static void main(String[] args) throws IOException {
		Student s1 = new Student("张三", 1, 20, "数据结构");
		Student s2 = new Student("李四", 2, 19, "网络");
		
		List<Student> list=new ArrayList<Student>();
		list.add( s1);
		list.add(s2);
 
		/*ObjectOutputStream 和 ObjectInputStream 分别与 FileOutputStream 和
		FileInputStream 一起使用时，可以为应用程序提供对对象图形的持久存储。*/
		
		// 将学生信息封装到student.txt中
		// 创建一个向 student.txt 的文件中写入数据的文件输出流。
		FileOutputStream fout = new FileOutputStream("student2.txt");
		
		//ObjectOutputStream 将 Java 对象的基本数据类型和图形写入 OutputStream。可以使用 ObjectInputStream 读取（重构）对象。
		ObjectOutputStream out = new ObjectOutputStream(fout);
		
		//writeObject 方法负责写入特定类的对象状态，以便相应的 readObject 方法可以恢复它。
		out.writeObject(list);
		
		
		//FileInputStream 类从文件系统中的一个文件中获取输入字节。
		FileInputStream fin = new FileInputStream("student2.txt");
		//创建从指定 InputStream 读取的 ObjectInputStream。从流读取序列化头部并予以验证。
		ObjectInputStream in = new ObjectInputStream(fin);   
 
		// 捕获异常
		try {
			/* readObject 方法为类重写默认的反序列化
			readObject 方法用于从流读取对象。应该使用 Java 的安全强制转换来获取所需的类型。
			在 Java 中，字符串和数组都是对象，所以在序列化期间将其视为对象。读取时，需要将其强制转换为期望的类型。*/
			List<Student> l= (List<Student>) in.readObject();
			for(  Student s: l){
				System.out.println(   s );
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		// 关闭流
		out.close();
		in.close();
 
	}
}



```

#### 2、