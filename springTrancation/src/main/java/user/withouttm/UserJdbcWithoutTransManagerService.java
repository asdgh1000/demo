package user.withouttm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.apache.commons.dbcp.BasicDataSource;

import java.util.List;

//@Service("service1")
public class UserJdbcWithoutTransManagerService {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void addScore(String userName,int toAdd){
		String sql = "UPDATE t_user u SET u.score = u.score + ? WHERE user_name =?";
		jdbcTemplate.update(sql,toAdd,userName);
	}

	public static void main(String[] args) {
		ApplicationContext ctx =
				new ClassPathXmlApplicationContext("/UserJdbcWithoutTransManager.xml");
		UserJdbcWithoutTransManagerService service =
				(UserJdbcWithoutTransManagerService)ctx.getBean("service1");
		JdbcTemplate jdbcTemplate = (JdbcTemplate)ctx.getBean("jdbcTemplate");
		BasicDataSource basicDataSource = (BasicDataSource)jdbcTemplate.getDataSource();

		//①.检查数据源autoCommit的设置
		System.out.println("autoCommit:"+ basicDataSource.getDefaultAutoCommit());

		//②.插入一条记录，初始分数为10
		jdbcTemplate.execute(
				"INSERT INTO t_user(user_name,password,score) VALUES('tom','123456',10)");

		//③.调用工作在无事务环境下的服务类方法,将分数添加20分
		service.addScore("tom",20);

		//④.查看此时用户的分数
		List score = jdbcTemplate.queryForList("SELECT score FROM t_user WHERE user_name ='tom'");
		System.out.println("score:"+score.get(0));
		jdbcTemplate.execute("DELETE FROM t_user WHERE user_name='tom'");
	}
}