package demo1ProgrammaticTransaction;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Spring 编程式事务
 * Created by Jo on 9/19/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class SpringTest {

	@Resource(name="accountService")
	private AccountService accountService;
	@Test
	public void test(){
		accountService.transfer("aaa","bbb",400);
	}

}
