package myJdkDynamicProxy;

import org.apache.commons.io.FileUtils;
import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * Created by Jo on 9/23/16.
 */
public class Proxy {
	public static Object newProxyInstance(Class infce,InvocationHandler invocationHandler) throws Exception{
		//代理类的类名为:$proxy0
		String rt = "\r\n";
		String methodStr = "";
		for(Method m : infce.getMethods()){
			methodStr +=
					"public void "+m.getName()+"() {" +rt+
						"try{"+rt+
						"Method md = "+infce.getName()+".class.getMethod(\"" + m.getName() + "\");"+rt+
						"h.invoke(this,md);"+rt+
						"}catch(Exception e){"+rt+
							"e.printStackTrace();}"+rt+
					"}";
		}
		String str =
		"package myJdkDynamicProxy;" +rt+
		"import myJdkDynamicProxy.InvocationHandler;"+rt+
		"import java.lang.reflect.Method;"+rt+
		"/**" +rt+
		" * 利用聚合实现静态代理  聚合比继承更适合实现代理" +rt+
		" * Created by Jo on 9/21/16." +rt+
		" */" +rt+
		"public class $Proxy0 implements "+ infce.getName()+"{" +rt+
			"public $Proxy0(InvocationHandler h){" +rt+
				"super();" +rt+
				"this.h = h;" +rt+
			"}" +rt+
		"private InvocationHandler h;"+rt+
			methodStr+rt+
		"}";
		String filename = System.getProperty("user.dir")+"/bin/myJdkDynamicProxy/$Proxy0.java";
		System.out.println(filename);
		//产生代理类的java文件
		File file = new File(filename);
		try {
			FileUtils.writeStringToFile(file,str);
		} catch (IOException e) {
			e.printStackTrace();
		}
		//编译
		//拿到编译器
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		//文件管理者
		StandardJavaFileManager javaFileManager = compiler.getStandardFileManager(null, null, null);
		//获取文件
		Iterable it = javaFileManager.getJavaFileObjects(filename);
		//编译任务
		JavaCompiler.CompilationTask task = compiler.getTask(null, javaFileManager, null, null, null, it);
		//进行编译
		task.call();
		try {
			javaFileManager.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//将编译好的class文件加载到内存
		ClassLoader classLoader = ClassLoader.getSystemClassLoader();
		Class c = classLoader.loadClass("myJdkDynamicProxy.$Proxy0");
		//创建构造器
		Constructor ctr = c.getConstructor(InvocationHandler.class);
		return ctr.newInstance(invocationHandler);
	}
}
