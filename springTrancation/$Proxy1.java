package user.aopDemo;
import java.lang.reflect.Method;
public class $Proxy1 implements user.aopDemo.UserMgr{
    public $Proxy1(InvocationHandler h) {
        this.h = h;
    }
    user.aopDemo.InvocationHandler h;
    @Override
    public  void addUser() {
        try {
        Method md = user.aopDemo.UserMgr.class.getMethod("addUser");
        h.invoke(this, md);
        }catch(Exception e) {e.printStackTrace();}
    }
    @Override
    public  void delUser() {
        try {
        Method md = user.aopDemo.UserMgr.class.getMethod("delUser");
        h.invoke(this, md);
        }catch(Exception e) {e.printStackTrace();}
    }

}