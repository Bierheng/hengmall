package com.hengmall.goods.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.util.Properties;

public class JedisUtil {

    private static final Logger logger = LoggerFactory.getLogger(JedisUtil.class);

    private static JedisPool pool = null;

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
                //加载properties配置文件
                prop.load(JedisUtil.class.getClassLoader().getResourceAsStream("redis.properties"));
            } catch (IOException e) {
                e.printStackTrace();
            }

            //控制一个pool可分配多少个jedis实例，通过pool.getResource()来获取；
            //如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
            config.setMaxTotal(Integer.valueOf(prop.getProperty("jedis.pool.maxTotal").trim()));
            //控制一个pool最多有多少个状态为idle(空闲的)的jedis实例。
            config.setMaxIdle(Integer.valueOf(prop.getProperty("jedis.pool.maxIdle").trim()));
            //表示当borrow(引入)一个jedis实例时，最大的等待时间，如果超过等待时间，则直接抛出JedisConnectionException；
            config.setMaxWaitMillis(Integer.parseInt(prop.getProperty("jedis.pool.maxWaitMillis")));
            //在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
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
     * @param
     * @return 成功返回1 如果存在 和 发生异常 返回 0
     */
    public static Long setnx(String key, String value, int seconds) {
        Jedis jedis = null;
        try {
            jedis = getJedis();

            Long setnx = jedis.setnx(key, value);
            jedis.expire(key, seconds);
            return setnx;
        } catch (Exception e) {
            if (jedis != null) {
                jedis.close();
            }
            e.printStackTrace();
            return 0l;
        } finally {
            //返还到连接池
            returnResource(jedis);
        }
    }


    /**
     * 将对象序列化后缓存
     *
     * @param key     键
     * @param value   value值
     * @param seconds 设置过期时间
     */
    public static void redisCache(String key, Object value, int seconds) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            logger.info("set key: {}, value: {}, seconds: {}", key, value, seconds);

            byte[] serialize = SerializeUtil.serialize(value);
            jedis.set(key.getBytes(), serialize);
            jedis.expire(key, seconds);
        } catch (Exception e) {
            if (jedis != null) jedis.close();
            e.printStackTrace();
        } finally {
            //返还到连接池
            returnResource(jedis);
        }
    }

    /**
     * 根据key获取value
     *
     * @param key
     * @return
     */
    public static Object getUnSerializeValue(String key) {
        Jedis jedis = null;
        try {
            jedis = getJedis();

            byte[] bytes = jedis.get(key.getBytes());
            if (PublicUtil.isEmpty(bytes)) return null;

            return SerializeUtil.unSerialize(bytes);
        } catch (Exception e) {
            if (jedis != null) jedis.close();
            e.printStackTrace();
        } finally {
            //返还到连接池
            returnResource(jedis);
        }

        return null;
    }


    /**
     * key存在即删除
     *
     * @param key
     * @return
     */
    public static void existsDel(String key) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            Boolean exists = jedis.exists(key);
            if (exists) jedis.del(key);
        } catch (Exception e) {
            if (jedis != null) jedis.close();
            e.printStackTrace();
        } finally {
            returnResource(jedis);
        }
    }


    /**
     *
     * @param key       键
     * @param value     value值
     * @param seconds  设置过期时间
     */
    /*public static void set(String key, String value,int seconds) {
        if(jedisPool == null) return;
        try (Jedis jedis = jedisPool.getResource()) {
            logger.info("set key: {}, value: {}", key, value);
            jedis.set(key, value);
            jedis.expire(key, seconds);
        }catch (Exception e) {
            logger.warn("set key failed , the message is " + e.getMessage());
        }
    }*/

    /**
     * 根据key获取value
     * @param key
     * @return
     */
 /*   public static String get(String key) {
        if(jedisPool == null) return null;
        try (Jedis jedis = jedisPool.getResource()) {
            String value = jedis.get(key);
            logger.info("get key: {}, value: {}", key, value);
            return value;
        }catch (Exception e) {
            logger.warn("get key failed , the message is " + e.getMessage());
        }
        return null;
    }*/


}