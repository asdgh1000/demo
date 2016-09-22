package user.aop;

import org.springframework.aop.BeforeAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.support.JdkRegexpMethodPointcut;
import org.springframework.aop.support.NameMatchMethodPointcut;

import java.lang.reflect.Method;

/**
 * Created by Jo on 8/24/16.
 */
public class CountingBeforeAdvice extends MethodCounter implements MethodBeforeAdvice {
	//实现before回调接口,这是接口MethodBeforeAdvice的要求
	public void before(Method method, Object[] args, Object target) throws Throwable {
		count(method);
	}
}
