package user.springAopTest;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Created by Jo on 8/25/16.
 */
public class HijackAroundMethod implements MethodInterceptor, Serializable {

	public Object invoke(MethodInvocation methodInvocation) throws Throwable {
		return getObject(methodInvocation);
	}


	private Object getObject(MethodInvocation methodInvocation) throws Throwable {
		print(methodInvocation);
		try {
			// proceed to original method call
			Object result = methodInvocation.proceed();

			// same with AfterReturningAdvice
			System.out.println("HijackAroundMethod : Before after hijacked!");

			return result;

		} catch (IllegalArgumentException e) {
			// same with ThrowsAdvice
			System.out.println("HijackAroundMethod : Throw exception hijacked!");
			throw e;
		}
	}

	private void print(MethodInvocation methodInvocation) {
		System.out.println("Method name : "
				+ methodInvocation.getMethod().getName());
		System.out.println("Method arguments : "
				+ Arrays.toString(methodInvocation.getArguments()));
		// same with MethodBeforeAdvice
		System.out.println("HijackAroundMethod : Before method hijacked!");
	}

}
