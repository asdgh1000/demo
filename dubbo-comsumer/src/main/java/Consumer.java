import com.netease.rpc.People;
import com.netease.rpc.SpeakInterface;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Jo on 1/11/17.
 */
public class Consumer {

	public static void main(String[] args) throws Exception {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"classpath*:dubbo-consumer.xml"});
		context.start();

		SpeakInterface demoService = (SpeakInterface)context.getBean("speakInterface"); // 获取远程服务代理
		People people = new People();
		people.setAge(24);
		people.setSex("male");
		String hello = demoService.speak(people); // 执行远程方法

		System.out.println( hello ); // 显示调用结果
	}

}
