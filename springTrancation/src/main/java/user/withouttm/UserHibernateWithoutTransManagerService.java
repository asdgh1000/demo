package user.withouttm;

import javassist.util.proxy.ProxyFactory;
import org.aopalliance.aop.Advice;
import org.springframework.aop.BeforeAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.*;
import org.springframework.aop.support.JdkRegexpMethodPointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.interceptor.TransactionProxyFactoryBean;
import pojo.User;

import java.util.List;

@Service("service2")
public class UserHibernateWithoutTransManagerService {
	@Autowired
	private HibernateTemplate hibernateTemplate;

	public void addScore(String userName,int toAdd){
		User user = (User)hibernateTemplate.get(User.class,userName);
		user.setScore(user.getScore()+toAdd);
		hibernateTemplate.update(user);
	}

	public static void main(String[] args) {

		ApplicationContext ctx =
				new ClassPathXmlApplicationContext("/hiberWithoutTransManager.xml");
		UserHibernateWithoutTransManagerService service =
				(UserHibernateWithoutTransManagerService)ctx.getBean("service2");
		HibernateTemplate hibernateTemplate = (HibernateTemplate)ctx.getBean("hibernateTemplate");
//		BasicDataSource basicDataSource = (BasicDataSource)hibernateTemplate.getDataSource();

		//①.检查数据源autoCommit的设置
//		System.out.println("autoCommit:"+ basicDataSource.getDefaultAutoCommit());

		//②.插入一条记录，初始分数为10
		hibernateTemplate.save("INSERT INTO t_user(user_name,password,score) VALUES('tom','123456',10)");

		//③.调用工作在无事务环境下的服务类方法,将分数添加20分
//		service.addScore("tom",20);

		//④.查看此时用户的分数
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT score ");
		sql.append("from t_user ");
		sql.append("WHERE user_name ='tom' ");
		List score = hibernateTemplate.find(sql.toString());
		System.out.println("score:" + score.get(0));

//		hibernateTemplate.execute("DELETE FROM t_user WHERE user_name='tom'");
//		hibernateTemplate.execute();

	}
}