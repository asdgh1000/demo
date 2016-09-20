package demo4DeclarativeTransaction3Annotation;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Jo on 9/19/16.
 */
/**
 *propagation :事务的传播行为
 * isolation :事务的隔离级别
 * readOnly:只读信息
 * rollbackFor:发生那些异常回滚
 * noRollbackFor:发生那些异常不回滚
 */
@Transactional(propagation = Propagation.REQUIRED)
public class AccountServiceImpl implements AccountService {

	private AccountDao accountDao;

	//注入accountDao bean
	public void setAccountDao(AccountDao accountDao) {
		this.accountDao = accountDao;
	}


	public void transfer(String out,String in,double money) {
		accountDao.outMoney(out, money);
//		int a =1/0;   //测试在转账过程中出现异常的情况
		accountDao.inMoney(in, money);
	}
}
