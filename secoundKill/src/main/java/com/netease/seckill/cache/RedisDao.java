package com.netease.seckill.cache;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import com.netease.seckill.entity.Seckill;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Jo on 10/25/16.
 */
public class RedisDao {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private JedisPool jedisPool;
	private int port;
	private String ip;
	LinkedList linkedList = new LinkedList();
	ArrayList arrayList = new ArrayList();
	public RedisDao(String ip, int port) {
		HashMap map = new HashMap();
		CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();
		logger.info("---------------------------------ip:{},port:{}",ip,port);
		this.port = port;
		this.ip = ip;
	}

	//Serialize function
	private RuntimeSchema<Seckill> schema = RuntimeSchema.createFrom(Seckill.class);

	public Seckill getSeckill(long seckillId) {
		LinkedList linkedList = new LinkedList();
		jedisPool = new JedisPool(ip, port);
		//redis operate
		try {
			Jedis jedis = jedisPool.getResource();
			try {
				String key = "seckill:" + seckillId;
				//由于redis内部没有实现序列化方法,而且jdk自带的implaments Serializable比较慢,会影响并发,因此需要使用第三方序列化方法.
				byte[] bytes = jedis.get(key.getBytes());
				if(null != bytes){
					Seckill seckill = schema.newMessage();
					ProtostuffIOUtil.mergeFrom(bytes,seckill,schema);
					//reSerialize
					return seckill;
				}
			} finally {
				jedisPool.close();
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}

		return null;
	}

	public String putSeckill(Seckill seckill) {
		jedisPool = new JedisPool(ip, port);
		//set Object(seckill) ->Serialize -> byte[]
		try{
			Jedis jedis = jedisPool.getResource();
			try{
				String key = "seckill:"+seckill.getSeckillId();
				byte[] bytes = ProtostuffIOUtil.toByteArray(seckill, schema, LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
				//time out  cache
				int timeout = 60*60;
				String result = jedis.setex(key.getBytes(),timeout,bytes);
				return result;
			}finally {
				jedisPool.close();
			}
		}catch (Exception e){
			logger.error(e.getMessage(),e);
		}
		return null;
	}
}
