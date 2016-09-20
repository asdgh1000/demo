package demo1ProgrammaticTransaction;

import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * Created by Jo on 9/19/16.
 */
public class AccountServiceImpl implements AccountService{

	private AccountDao accountDao;


	private TransactionTemplate transactionTemplate;
	//注入accountDao bean
	public void setAccountDao(AccountDao accountDao) {
		this.accountDao = accountDao;
	}
	//注入transactionTemplate bean
	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	/**
	 * 事实上这种方式的事务处理方式很少使用,因为需要对service层进行改造
	 * @param out 转出账号
	 * @param in  转入账号
	 * @param money 金额
	 */
	//使用TransactionCallback 匿名内部类 内部类中doInTransaction方法使用了外部类的变量,因此外部类变量需要加上final
	public void transfer(final String out,final String in, final double money) {
		transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			@Override protected void doInTransactionWithoutResult(TransactionStatus status) {
				accountDao.outMoney(out, money);
//						int a =1/0;   //测试在转账过程中出现异常的情况
				accountDao.inMoney(in,money);
			}
		});

	}
}
