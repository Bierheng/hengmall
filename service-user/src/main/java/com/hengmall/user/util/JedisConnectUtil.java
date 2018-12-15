package com.hengmall.user.util;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisConnectUtil {

	private static JedisPool pool = null;
	
    @Autowired
	private static JedisCluster jedisCluster = null;
	

	public JedisCluster getJedisCluster() {
		return jedisCluster;
	}
	
	/**
	 * 获取jedispool 连接池
	 * 
	 * @return
	 */
	private static JedisPool getPool() {
		if (pool == null) {
			JedisPoolConfig config = new JedisPoolConfig();
			Properties prop = new Properties();
			try {
				// 加载properties配置文件
				prop.load(JedisConnectUtil.class.getClassLoader().getResourceAsStream("redis.properties"));
			} catch (IOException e) {
				e.printStackTrace();
			}

			// 控制一个pool可分配多少个jedis实例，通过pool.getResource()来获取；
			// 如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
			config.setMaxTotal(Integer.valueOf(prop.getProperty("jedis.pool.maxTotal").trim()));
			// 控制一个pool最多有多少个状态为idle(空闲的)的jedis实例。
			config.setMaxIdle(Integer.valueOf(prop.getProperty("jedis.pool.maxIdle").trim()));
			// 表示当borrow(引入)一个jedis实例时，最大的等待时间，如果超过等待时间，则直接抛出JedisConnectionException；
			config.setMaxWaitMillis(Integer.parseInt(prop.getProperty("jedis.pool.maxWaitMillis")));
			// 在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
			config.setTestOnBorrow(true);

			pool = new JedisPool(config, prop.getProperty("redis.ip"), Integer.parseInt(prop.getProperty("redis.port")),
					Integer.parseInt(prop.getProperty("redis.timeout")), prop.getProperty("redis.pass"));
		}
		return pool;
	}

	/**
	 * 返回资源
	 * 
	 * @param jedis
	 */
	public static void returnResource(Jedis jedis) {
		if (jedis != null) {
			jedis.close();
		}
	}

	/**
	 * 获取jedis 实例
	 * 
	 * @return
	 * @throws Exception
	 */
	public static Jedis getJedis() throws Exception {
		Jedis jedis = null;
		pool = getPool();
		jedis = pool.getResource();
		return jedis;
	}

	/**
	 * 得到 key 值
	 * 
	 * @param key
	 * @return
	 */
	public static String getnx(String key) {
		String value = null;
		Jedis jedis = null;
		try {
			jedis = getJedis();
			value = jedis.get(key);
		} catch (Exception e) {
			if (jedis != null) {
				jedis.close();
			}
			e.printStackTrace();
		} finally {
			returnResource(jedis);
		}
		return value;
	}

	/***
	 * 设置key value,如果key已经存在则返回0,nx==> not exist
	 * 
	 * @param
	 * @return 成功返回1 如果存在 和 发生异常 返回 0
	 */
	public static Long setnx(String key, String value) {
		Jedis jedis = null;
		try {
			jedis = getJedis();
			return jedis.setnx(key, value);
		} catch (Exception e) {
			if (jedis != null) {
				jedis.close();
			}
			e.printStackTrace();
			return 0l;
		} finally {
			// 返还到连接池
			returnResource(jedis);
		}
	}
	
	
	/**
	 * 判断key是否存在
	 * @param key
	 * @return
	 */
	public Boolean exists(String key) {
		Jedis jedis = null;
		Boolean result = false;
		try {
			jedis = getJedis();
			result = jedis.exists(key);
			return result;
		} catch (Exception e) {
			if (jedis != null) {
				jedis.close();
			}
			e.printStackTrace();
		} finally {
			returnResource(jedis);
		}
		return result;
	}
	
	public Long expire(String key, int seconds) {
		Jedis jedis = null;
		Long result = null;
		try {
			jedis = getJedis();
			result = jedis.expire(key, seconds);
			return result;
		} catch (Exception e) {
			if (jedis != null) {
				jedis.close();
			}
			e.printStackTrace();
		} finally {
			returnResource(jedis);
		}
		return result;
	}

	public Long del(String key) {
		Jedis jedis = null;
		Long result = null;
		try {
			jedis = getJedis();
			result = jedis.del(key);
			return result;
		} catch (Exception e) {
			if (jedis != null) {
				jedis.close();
			}
			e.printStackTrace();
		} finally {
			returnResource(jedis);
		}
		return result;
	}

	public static Long setList(String key, Set<String> permissionsSet) {
		Jedis jedis = null;
		try {
			jedis = getJedis();
			Long i = null;
			for(String permissions : permissionsSet){
				 i = jedis.rpush(key, permissions);
			}
			return i ;
		} catch (Exception e) {
			if (jedis != null) {
				jedis.close();
			}
			e.printStackTrace();
			return 0l;
		} finally {
			// 返还到连接池
			returnResource(jedis);
		}
	}
	
	public static List<String> getList(String key) {
		Jedis jedis = null;
		try {
			jedis = getJedis();
			List<String> list = jedis.lrange(key, 0, -1);
			return list;
		} catch (Exception e) {
			if (jedis != null) {
				jedis.close();
			}
			e.printStackTrace();
			return null;
		} finally {
			// 返还到连接池
			returnResource(jedis);
		}
	}
	
	
	/**
	 * 判断hash是否存在
	 * @param key redis的key
	 * @param field hash表的key
	 * @return
	 */
	public static Boolean hexists(String key, String field) {
		Jedis jedis = null;
		try {
			jedis = getJedis();
			return jedis.hexists(key,field);
		} catch (Exception e) {
			if (jedis != null) {
				jedis.close();
			}
			e.printStackTrace();
			return null;
		} finally {
			// 返还到连接池
			returnResource(jedis);
		}
	}
	
	
	/**
	 * 添加hash
	 * @param key key
	 * @param field hash的key
	 * @param value hash的value
	 * @return
	 */
	public static Long hsetnx(String key, String field, String value) {
		Jedis jedis = null;
		try {
			jedis = getJedis();
			return jedis.hsetnx(key,field,value);
		} catch (Exception e) {
			if (jedis != null) {
				jedis.close();
			}
			e.printStackTrace();
			return null;
		} finally {
			// 返还到连接池
			returnResource(jedis);
		}
	}
	
	
	/**
	 * 删除hash
	 * @param key key
	 * @param fields hash的keys
	 * @return
	 */
	public static Long hdel(String key, String... fields) {
		Jedis jedis = null;
		try {
			jedis = getJedis();
			return jedis.hdel(key,fields);
		} catch (Exception e) {
			if (jedis != null) {
				jedis.close();
			}
			e.printStackTrace();
			return null;
		} finally {
			// 返还到连接池
			returnResource(jedis);
		}
	};
	
	
	/**
	 * 获取hash
	 * @param key
	 * @param field
	 * @return
	 */
	public static String hget(String key, String field) {
		Jedis jedis = null;
		try {
			jedis = getJedis();
			return jedis.hget(key,field);
		} catch (Exception e) {
			if (jedis != null) {
				jedis.close();
			}
			e.printStackTrace();
			return null;
		} finally {
			// 返还到连接池
			returnResource(jedis);
		}
	}
	
	
	/**
	 * 删除hash
	 * @param key
	 * @return
	 */
	public static Long hdel(String key) {
		Jedis jedis = null;
		try {
			jedis = getJedis();
			return jedis.del(key);
		} catch (Exception e) {
			if (jedis != null) {
				jedis.close();
			}
			e.printStackTrace();
			return null;
		} finally {
			returnResource(jedis);
		}
	}
	
	
	/**
	 * 将哈希表 key 中的字段 field 的值设为 value
	 * @param key
	 * @param field
	 * @param value
	 * @return
	 */
	public static Long hset(String key,String field, String value) {
		Jedis jedis = null;
		try {
			jedis = getJedis();
			return jedis.hset(key,field,value);
		} catch (Exception e) {
			if (jedis != null) {
				jedis.close();
			}
			e.printStackTrace();
			return null;
		} finally {
			returnResource(jedis);
		}
	}

	/**
	 * 检查key是否存在
	 * @return 如果存在返回true，不存在返回false
	 */
	public static boolean existsKey(String key) {
		Jedis jedis = null;
		try {
			jedis = getJedis();
			return jedis.exists(key);
		} catch (Exception e) {
			if (jedis != null) {
				jedis.close();
			}
			e.printStackTrace();
			return false;
		} finally {
			returnResource(jedis);
		}
	}
	
	/**
	 * 获取所有hash
	 * @return
	 */
	public static Map<String,String> hgetAll(String key){
		Jedis jedis = null;
		try {
			jedis = getJedis();
			return jedis.hgetAll(key);
		} catch (Exception e) {
			if (jedis != null) {
				jedis.close();
			}
			e.printStackTrace();
			return null;
		} finally {
			returnResource(jedis);
		}
	}
	
}