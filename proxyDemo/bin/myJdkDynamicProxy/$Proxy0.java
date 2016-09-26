package myJdkDynamicProxy;
import myJdkDynamicProxy.InvocationHandler;
import java.lang.reflect.Method;
/**
 * 利用聚合实现静态代理  聚合比继承更适合实现代理
 * Created by Jo on 9/21/16.
 */
public class $Proxy0 implements myJdkDynamicProxy.Move{
public $Proxy0(InvocationHandler h){
super();
this.h = h;
}
private InvocationHandler h;
public void drive() {
try{
Method md = myJdkDynamicProxy.Move.class.getMethod("drive");
h.invoke(this,md);
}catch(Exception e){
e.printStackTrace();}
}
}