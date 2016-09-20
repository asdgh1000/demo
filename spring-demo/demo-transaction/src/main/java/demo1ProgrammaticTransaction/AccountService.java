package demo1ProgrammaticTransaction;

/**
 * Created by Jo on 9/19/16.
 */
public interface AccountService {

	/**
	 *
	 * @param out 转出账号
	 * @param in  转入账号
	 * @param money 金额
	 */
	void transfer(String out,String in, double money);
}
