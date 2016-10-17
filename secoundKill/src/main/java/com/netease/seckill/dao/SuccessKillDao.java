package com.netease.seckill.dao;

import com.netease.seckill.entity.SuccessKill;

/**
 * Created by Jo on 10/17/16.
 */
public interface SuccessKillDao {

	/**
	 * insert success killed detail,can filter repeat
	 * @param seckillId
	 * @param userPhone
	 * @return insert lines
	 */
	int insertSuccessKill(long seckillId,long userPhone);

	/**
	 * query successKill with seckill by Id
	 * @param seckillId
	 * @return
	 */
	SuccessKill queryByIdWithSeckill(long seckillId);



}
