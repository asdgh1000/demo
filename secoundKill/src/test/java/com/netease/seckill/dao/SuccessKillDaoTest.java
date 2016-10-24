package com.netease.seckill.dao;

import com.netease.seckill.entity.SuccessKill;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * Created by Jo on 10/18/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-dao.xml")
public class SuccessKillDaoTest {

	@Resource
	SuccessKillDao successKillDao;

	@Test
	public void testInsertSuccessKill() throws Exception {
		int insertNumber = successKillDao.insertSuccessKill(1001l,18868831752l);
		System.out.println("insertNumber:"+insertNumber);
	}

	@Test
	public void testQueryByIdWithSeckill() throws Exception {
		SuccessKill successKill = successKillDao.queryByIdWithSeckill(1001l,18868831752l);
		System.out.println(successKill);
		System.out.println(successKill.getSeckill());
	}
}