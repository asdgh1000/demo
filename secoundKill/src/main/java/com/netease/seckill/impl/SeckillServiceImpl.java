package com.netease.seckill.impl;

import com.netease.seckill.dao.SeckillDao;
import com.netease.seckill.dao.SuccessKillDao;
import com.netease.seckill.dto.Exposer;
import com.netease.seckill.dto.SeckillExcution;
import com.netease.seckill.entity.Seckill;
import com.netease.seckill.entity.SeckillStatusEnum;
import com.netease.seckill.entity.SuccessKill;
import com.netease.seckill.exception.RepeatKillException;
import com.netease.seckill.exception.SeckillCloseException;
import com.netease.seckill.exception.SeckillException;
import com.netease.seckill.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by Jo on 10/18/16.
 */
public class SeckillServiceImpl implements SeckillService{

	@Resource
	private SeckillDao seckillDao;
	@Resource
	private SuccessKillDao successKillDao;

	public final String salt = "dfsdfdsf7JHJJ";

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public List<Seckill> getSeckillList() {
		return seckillDao.queryAll(0, 4);
	}

	public Seckill getById(long seckillId) {
		return seckillDao.queryById(seckillId);
	}

	/**
	 * expose seckill url when seckill start,else expose system time and kill time
	 * @param seckillId
	 */
	public Exposer exposeSeckillUrl(long seckillId) {
		Seckill seckill = getById(seckillId);
		if(seckill == null){
			return new Exposer(false,seckillId);
		}
		Date createTime = seckill.getCreateTime();
		Date endTime = seckill.getEndTime();
		Date currentTime = new Date();
		//seckill success
		if(currentTime.after(createTime) && currentTime.before(endTime)){
			//conversion String to special String (can't reverse)
			String md5 = getMd5(seckillId);
			return new Exposer(true,md5,seckillId);
		}
		else{
			return new Exposer(false,seckillId,currentTime.getTime(),createTime.getTime(),endTime.getTime());
		}
	}

	private String getMd5(long seckillId){
		String base = seckillId+"/"+salt;
		String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
		return md5;
	}

	public SeckillExcution executeSeckill(long seckillId, long userPhone, String md5)
			throws SeckillException, RepeatKillException, SeckillCloseException {
		if(md5 == null || !md5.equals(getMd5(seckillId))){
			throw new SeckillException("seckill data rewrite");
		}
		Date currentTime = new Date();
		try {
			//execute seckill:1.reduce product 2.record purchase message
			int updateCount = seckillDao.reduceNumber(seckillId,currentTime);
			//do not update for record
			if(updateCount<=0){
				throw new SeckillCloseException("seckill is close");
			}
			else{
				//record purchase message
				int insertCount = successKillDao.insertSuccessKill(seckillId,userPhone);
				if(insertCount<=0){
					//repeat seckill
					throw new RepeatKillException("seckill repeated");
				}
				else{
					SuccessKill successKill = successKillDao.queryByIdWithSeckill(seckillId,userPhone);
					return new SeckillExcution(seckillId, SeckillStatusEnum.SUCCESS,successKill);
				}
			}
		}catch (SeckillCloseException e1){
			throw e1;
		}catch (RepeatKillException e2){
			throw e2;
		}catch (Exception e){
			//rollback
			logger.error(e.getMessage(),e);
			throw new SeckillException("seckill inner error:"+e.getMessage());
		}
	}
}
