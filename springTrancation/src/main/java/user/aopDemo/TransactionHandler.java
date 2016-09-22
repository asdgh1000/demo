package user.aopDemo;

/**
 * Created by Jo on 8/30/16.
 */
import java.lang.reflect.Method;

public class TransactionHandler implements InvocationHandler {

	private Object target;

	public TransactionHandler(Object target) {
		super();
		this.target = target;
	}

	public void invoke(Object o, Method m) {
		System.out.println("开启事务.....");
		try {
			m.invoke(target);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("提交事务.....");
	}

}
