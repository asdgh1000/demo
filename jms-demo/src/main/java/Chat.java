import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import java.util.Properties;

/**
 * Created by Jo on 2/20/17.
 */
public class Chat implements MessageListener {

	private TopicSession topicSession;

	private TopicPublisher topicPublisher;

	private TopicConnection topicConnection;

	private String username;

	public Chat(String topicFactory,String topicName,String username)throws Exception{

		Properties env = new Properties();
		env.put(Context.SECURITY_PRINCIPAL,"system");
		env.put(Context.SECURITY_CREDENTIALS,"manager");
		env.put(Context.INITIAL_CONTEXT_FACTORY,"org.apache.activemq.jndi.ActiveMQInitialContextFactory");
		env.put(Context.PROVIDER_URL,"tcp://localhost:61616");
		InitialContext ctx = new InitialContext(env);
		TopicConnectionFactory connectionFactory = (TopicConnectionFactory)ctx.lookup(topicFactory);
		TopicConnection connection = connectionFactory.createTopicConnection();

		TopicSession pubSession = connection.createTopicSession(false,Session.AUTO_ACKNOWLEDGE);
		TopicSession subSession = connection.createTopicSession(false,Session.AUTO_ACKNOWLEDGE);
		Topic chatTopic = (Topic)ctx.lookup(topicName);

		TopicPublisher publisher = pubSession.createPublisher(chatTopic);
		TopicSubscriber subscriber = subSession.createSubscriber(chatTopic,null,true);
		//设置一个JMS消息侦听器
		subscriber.setMessageListener(this);
		this.topicConnection = connection;
		this.topicPublisher = publisher;
		this.topicSession = subSession;
		this.username = username;
		connection.start();
	}

	public void onMessage(Message message) {

	}
}
