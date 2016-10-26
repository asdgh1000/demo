package com.netease.seckill.service.impl;

import com.netease.seckill.cache.RedisDao;
import com.netease.seckill.dao.SeckillDao;
import com.netease.seckill.dao.SuccessKillDao;
import com.netease.seckill.dto.Exposer;
import com.netease.seckill.dto.SeckillExcution;
import com.netease.seckill.entity.Seckill;
import com.netease.seckill.enums.SeckillStatusEnum;
import com.netease.seckill.entity.SuccessKill;
import com.netease.seckill.exception.RepeatKillException;
import com.netease.seckill.exception.SeckillCloseException;
import com.netease.seckill.exception.SeckillException;
import com.netease.seckill.service.SeckillService;
import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jo on 10/18/16.
 */

@Service
@PropertySource(value = "classpath:sault.properties")
public class SeckillServiceImpl implements SeckillService{


	@Autowired
	private SuccessKillDao successKillDao;
	@Autowired
	private SeckillDao seckillDao;
	@Value("${sault}")
	public String sault;
	@Autowired
	private RedisDao redisDao;

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
		Seckill seckill = redisDao.getSeckill(seckillId);
		if(null == seckill) {
			seckill = getById(seckillId);
			if (seckill == null) {
				return new Exposer(false, seckillId);
			}
			else{
				redisDao.putSeckill(seckill);
			}
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
		String base = seckillId+"/"+sault;
		String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
		return md5;
	}

	@Transactional  //rollback when runtimeException happend
	public SeckillExcution executeSeckill(long seckillId, long userPhone, String md5)
			throws SeckillException, RepeatKillException, SeckillCloseException {
		if(md5 == null || !md5.equals(getMd5(seckillId))){
			throw new SeckillException("seckill data rewrite");
		}
		Date currentTime = new Date();
		try {
			//record purchase message
			int insertCount = successKillDao.insertSuccessKill(seckillId, userPhone);
			if(insertCount<=0){
				//repeat seckill
				throw new RepeatKillException("seckill repeated");
			}
			else{
				//execute seckill:1.reduce product 2.record purchase message    //热点商品竞争
				int updateCount = seckillDao.reduceNumber(seckillId,currentTime);
				//do not update for record
				if(updateCount<=0){
					throw new SeckillCloseException("seckill is close");
				}
				else{
					//秒杀成功
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

	public SeckillExcution executeSeckillProcedure(long seckillId, long userPhone, String md5)
			throws SeckillException, RepeatKillException, SeckillCloseException {
		 if(md5 == null || md5.equals(getMd5(seckillId))){
			 return new SeckillExcution(seckillId,SeckillStatusEnum.DATA_REWRITE);
		 }
		Date time = new Date();
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("seckillId",seckillId);
		map.put("phone",userPhone);
		map.put("killTime",time);
		map.put("result",null);
		try {
			seckillDao.killByProcedure(map);
			int result = MapUtils.getInteger(map,"result",-2);
			if(result == 1){
				SuccessKill successKill = successKillDao.queryByIdWithSeckill(seckillId,userPhone);
				return new SeckillExcution(seckillId,SeckillStatusEnum.SUCCESS,successKill);
			}
			else{
				return new SeckillExcution(seckillId,SeckillStatusEnum.statusOf(result));
			}
		}catch(Exception e){
			logger.error(e.getMessage(),e);
			return new SeckillExcution(seckillId,SeckillStatusEnum.INNER_ERROR);
		}
	}

}
