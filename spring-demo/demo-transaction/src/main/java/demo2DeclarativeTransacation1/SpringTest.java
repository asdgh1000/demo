package demo2DeclarativeTransacation1;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Spring编程式事务
 * Created by Jo on 9/19/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext2.xml")
public class  SpringTest {

	/**
	 * 注入事务增强后的代理类
	 */
	@Resource(name="accountServiceProxy")
	private AccountService accountService;

	@Test
	public void test(){
		accountService.transfer("aaa","bbb",400);
	}

}
