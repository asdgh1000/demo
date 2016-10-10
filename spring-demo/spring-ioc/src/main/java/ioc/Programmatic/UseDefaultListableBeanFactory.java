package ioc.Programmatic;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * Created by Jo on 10/9/16.
 */
public class UseDefaultListableBeanFactory {
	public void initXmlBeanDefinitionreader(String xmlFile) {
//		Resource
//		ClassPathXmlApplicationContext
//		FileSystemXmlApplicationContext

		ClassPathResource resource = new ClassPathResource(xmlFile);//包含了beanDefinition的抽象资源
		//DefaultListableBeanFactory是很多容器的基本容器
		DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();  //BeanFactory
		XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(defaultListableBeanFactory);//为BeanDefinition读取器设置BeanFactory
		xmlBeanDefinitionReader.loadBeanDefinitions(resource);//载入BeanDefinition并通过回调配置给BeanFactory
	}
}
