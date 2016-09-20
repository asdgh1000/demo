package demo2DeclarativeTransacation1;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

/**
 * Created by Jo on 9/19/16.
 */
public class AccountDaoImpl extends JdbcDaoSupport implements AccountDao {

	public void outMoney(String out, double money) {
		String sql = "update account set money = money - ? where name = ?";
		getJdbcTemplate().update(sql, money, out);
	}

	public void inMoney(String in, double money) {
		String sql = "update account set money = money + ? where name =?";
		getJdbcTemplate().update(sql,money,in);
	}
}
