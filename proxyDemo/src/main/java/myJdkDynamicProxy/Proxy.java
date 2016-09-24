package myJdkDynamicProxy;

import org.apache.commons.io.FileUtils;
import proxyStaticComDemo.*;
import proxyStaticComDemo.Move;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * Created by Jo on 9/23/16.
 */
public class Proxy {
	public static Object newProxyInstance(Class infce){
		//代理类的类名为:$proxy0
		String rt = "\r\n";
		String methodStr = "";
		for(Method m : infce.getMethods()){
			methodStr +=
					"	public void "+m.getName()+"() {" +rt+
					"		long start = System.currentTimeMillis();" +rt+
					"		System.out.println(\"start-----\");" +rt+
					"		\"+m.getName()+\".drive();" +rt+
					"		long end = System.currentTimeMillis();" +rt+
					"		System.out.println(\"end------\");" +rt+
					"		System.out.println(\"waste time :\" + (end - start));" +rt+
					"	}";
		}
		String str =
		"package proxyStaticComDemo;" +rt+
		"/**" +rt+
		" * 利用聚合实现静态代理  聚合比继承更适合实现代理" +rt+
		" * Created by Jo on 9/21/16." +rt+
		" */" +rt+
		"public class $Proxy0 implements +"+ infce.getName()+"{" +rt+
		"	public $Proxy0("+ infce.getName()+" move){" +rt+
		"		super();" +rt+
		"		this.move = move;" +rt+
		"	}" +rt+
		"	private "+ infce.getName()+" move;" +rt+
			methodStr+rt+
		"}";
		String filename = System.getProperty("user.dir")+"/bin/myJdkDynamicProxy/$Proxy.java";
		System.out.println(filename);
		File file = new File(filename);
		try {
			FileUtils.writeStringToFile(file,str);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}
}
