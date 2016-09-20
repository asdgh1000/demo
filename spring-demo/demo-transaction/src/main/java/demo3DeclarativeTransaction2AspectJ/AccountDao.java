package demo3DeclarativeTransaction2AspectJ;

/**
 * Created by Jo on 9/19/16.
 */
public interface AccountDao {

	/**
	 * 转钱出去
	 * @param out 转出账号
	 * @param money 金额
	 */
	void outMoney(String out, double money);

	/**
	 * 将钱转入
	 * @param in 转出张哈
	 * @param money 金额
	 */
	void inMoney(String in, double money);
}
