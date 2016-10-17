package com.netease.seckill.dao;

import com.netease.seckill.entity.Seckill;

import java.util.Date;
import java.util.List;

/**
 * Created by Jo on 10/17/16.
 */
public interface SeckillDao {

	/**
	 * reduce product number
	 * @param seckillId
	 * @param killTime
	 * @return if effect line number >=1 ,means update lines. if return 0 means reduce fail
	 */
	int reduceNumber(long seckillId,Date killTime);

	/**
	 * query seckill product
	 * @param seckillId
	 * @return
	 */
	Seckill queryById(long seckillId);

	/**
	 * query products from seckill
	 * @param offset
	 * @param limit
	 * @return
	 */
	List<Seckill> queryAll(int offset,int limit);

}
