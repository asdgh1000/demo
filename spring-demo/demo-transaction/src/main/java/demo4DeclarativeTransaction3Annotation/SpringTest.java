package demo4DeclarativeTransaction3Annotation;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Spring编程式事务:基于注解的事务管理方式
 * Created by Jo on 9/19/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext4.xml")
public class  SpringTest {

	/**
	 * 动态代理实现的事务AOP对业务逻辑没有侵入
	 */
	@Resource(name="accountService")
	private AccountService accountService;

	/**
	 * 转账案例
	 */
	@Test
	public void test(){
		accountService.transfer("aaa","bbb",400);
	}

}
