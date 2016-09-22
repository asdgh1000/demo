package user.springAopTest;

import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.beans.factory.FactoryBean;

import java.lang.reflect.Method;

/**
 * Created by Jo on 8/25/16.
 */
public class HijackBeforeMethod implements MethodBeforeAdvice
{
	public void before(Method method, Object[] args, Object target)
			throws Throwable {

		System.out.println("HijackBeforeMethod : Before method hijacked!");
	}
}