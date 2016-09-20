package demo3DeclarativeTransaction2AspectJ;

/**
 * Created by Jo on 9/19/16.
 */
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
