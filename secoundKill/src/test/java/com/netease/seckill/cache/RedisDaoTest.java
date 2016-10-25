package com.netease.seckill.cache;

import com.netease.seckill.dao.SeckillDao;
import com.netease.seckill.entity.Seckill;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * Created by Jo on 10/25/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml" })
public class RedisDaoTest {
	private long id = 1004;

	@Autowired
	private RedisDao redisDao;

	@Autowired
	private SeckillDao seckillDao;

	@Test public void testSeckill() throws Exception {

		Seckill seckill = redisDao.getSeckill(id);
		if (seckill == null) {
			seckill = seckillDao.queryById(id);
			if (seckill != null) {
				String result = redisDao.putSeckill(seckill);
				System.out.println(result);
				seckill = redisDao.getSeckill(id);
				System.out.println(seckill);
			}
		}
		System.out.println(seckill);
	}
}