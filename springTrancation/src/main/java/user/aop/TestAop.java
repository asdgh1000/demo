package user.aop;

import java.lang.reflect.Method;

/**
 * Created by Jo on 8/24/16.
 */
public class TestAop {
	public static void main(String[] args){
		TestAop.printMethod();
	}

//	public void testAop(){
//		CountingBeforeAdvice countingBeforeAdvice = new CountingBeforeAdvice();
//		countingBeforeAdvice.before(printMethod(),null,this);
//		DefaultPointcutAdvisor defaultPointcutAdvisor = new DefaultPointcutAdvisor(new CountingBeforeAdvice());
//	}
	public static String printMethod(){
		System.out.println("this is for test");
		return null;
	}
}
