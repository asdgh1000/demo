package demo3DeclarativeTransaction2AspectJ;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Spring编程式事务:基于AspectJ的XML方式
 * Created by Jo on 9/19/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext3.xml")
public class  SpringTest {

	/**
	 * 静态代理实现的事务AOP对业务逻辑没有侵入
	 */
	@Resource(name="accountService")
	private AccountService accountService;

	@Test
	public void test(){
		accountService.transfer("aaa","bbb",400);
	}

}
