package user.SpringAopTest1;

/**
 * Created by Jo on 8/26/16.
 */
import java.lang.reflect.Method;
import org.springframework.aop.MethodBeforeAdvice;
public class BeforeAdvice implements MethodBeforeAdvice {
	public void before(Method arg0, Object[] arg1, Object arg2)
			throws Throwable {
		System.out.println("before logging");
	}
}