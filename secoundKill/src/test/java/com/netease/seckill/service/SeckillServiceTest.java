package com.netease.seckill.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * Created by Jo on 10/19/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml",
		"classpath:spring/spring-service.xml"})
public class SeckillServiceTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Resource
	SeckillService seckillService;

	@Test
	public void testGetSeckillList() throws Exception {

	}

	@Test
	public void testGetById() throws Exception {

	}

	@Test
	public void testExposeSeckillUrl() throws Exception {

	}

	@Test
	public void testExecuteSeckill() throws Exception {

	}
}