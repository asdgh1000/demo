package user.withouttm;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionTemplate;

import javax.transaction.TransactionManager;

/**
 * Created by Jo on 8/18/16.
 */
public class UserJdbcWithTransaction {
	public static void main(String[] args){
		ApplicationContext ctx =
				new ClassPathXmlApplicationContext("/Transaction.xml");

		PlatformTransactionManager txManager = (PlatformTransactionManager)ctx.getBean("txManager");
		TransactionDefinition txDefinition = (TransactionDefinition)ctx.getBean("txDefinition");
		TransactionStatus txStatus = txManager.getTransaction(txDefinition);
//		TransactionTemplate
//		boolean result = false;
//		try{
//
//		}
	}
}
