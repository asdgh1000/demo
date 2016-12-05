package com.netease.rpc.test;

import com.netease.rpc.People;
import com.netease.rpc.SpeakInterface;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by Jo on 11/30/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring-dubbo-consumer.xml","classpath*:spring-context.xml" })
public class BestTest {

	@Resource private SpeakInterface speakInterface;

	@Test public void test() {
		People people = new People();
		people.setAge(24);
		people.setSex("male");
		System.out.println(speakInterface.speak(people));
		Assert.assertEquals(true, speakInterface.speak(people).contains(people.getAge() + ""));
	}
}
