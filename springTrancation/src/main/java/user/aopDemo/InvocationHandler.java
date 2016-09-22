package user.aopDemo;

import java.lang.reflect.Method;
/**
 * Created by Jo on 8/30/16.
 */
public interface InvocationHandler {
	public void invoke(Object o, Method m);
}
