package user.aop;

import org.aopalliance.aop.Advice;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractGenericPointcutAdvisor;

import java.io.Serializable;

/**
 * Created by Jo on 8/24/16.
 */
public class DefaultPointcutAdvisor extends AbstractGenericPointcutAdvisor implements Serializable {
	private Pointcut pointcut = Pointcut.TRUE;

	public DefaultPointcutAdvisor() {
	}

	public DefaultPointcutAdvisor(Advice advice) {
		this(Pointcut.TRUE,advice);
	}

	public DefaultPointcutAdvisor(Pointcut pointcut,Advice advice){
		this.pointcut = pointcut;
		this.setAdvice(advice);
	}


	public void setPointcut(Pointcut pointcut){
		this.pointcut = (pointcut != null ? pointcut : Pointcut.TRUE);
	}

	public Pointcut getPointcut(){
		return this.pointcut;
	}

	public String toString(){
		return getClass().getName() + ":pointcut["+getPointcut()+"]; advice["+getAdvice()+"]";
	}
}
