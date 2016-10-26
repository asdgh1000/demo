package com.netease.seckill.service;

import com.netease.seckill.dto.Exposer;
import com.netease.seckill.dto.SeckillExcution;
import com.netease.seckill.entity.Seckill;
import com.netease.seckill.exception.RepeatKillException;
import com.netease.seckill.exception.SeckillCloseException;
import com.netease.seckill.exception.SeckillException;

import java.util.List;

/**
 * Created by Jo on 10/18/16.
 */
public interface SeckillService {

	/**
	 * get all seckill
	 * @return
	 */
	List<Seckill> getSeckillList();

	/**
	 * get seckill by id
	 * @param seckillId
	 * @return
	 */
	Seckill getById(long seckillId);

	/**
	 * expose seckill url when seckill start,else expose system time and kill time
	 * @param seckillId
	 */
	Exposer exposeSeckillUrl(long seckillId);

	/**
	 * execute seckill
	 * @param seckillId
	 * @param userPhone
	 * @param md5
	 */
	SeckillExcution executeSeckill(long seckillId,long userPhone,String md5) throws SeckillException,RepeatKillException,SeckillCloseException;

	SeckillExcution executeSeckillProcedure(long seckillId,long userPhone,String md5) throws SeckillException,RepeatKillException,SeckillCloseException;
}
