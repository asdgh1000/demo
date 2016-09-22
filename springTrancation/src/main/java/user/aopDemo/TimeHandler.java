package user.aopDemo;

import java.lang.reflect.Method;
import java.util.Date;

/**
 * Created by Jo on 8/30/16.
 */
public class TimeHandler implements InvocationHandler{
	private Object target;

	public TimeHandler(Object target) {
		super();
		this.target = target;
	}

	public void invoke(Object o, Method m) {
		System.out.println("开启时间:"+new Date());
		try {
			m.invoke(target);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("结束时间:"+new Date());
	}

}
