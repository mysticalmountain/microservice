package com.andx.micro.support.redis;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisSentinelPool;
import redis.clients.jedis.Protocol;
import redis.clients.util.Pool;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by andongxu on 10/27/16.
 */
public class JedisFactory {

    private JedisConfig jedisConfig;

    private Pool<Jedis> jedisPool;

    private static final Object objSync = new Object();

    public JedisFactory(JedisConfig jedisConfig) {
        this.jedisConfig = jedisConfig;
    }

    protected Jedis getJedis() {
        if (jedisPool == null) {
            synchronized (objSync) {
                initConnectionPool();
            }
        }
        Jedis j = getWorkingJedis();
        if (j != null) {
            j.select(jedisConfig.getDatabaseIndex());
            return j;
        }
        synchronized (objSync) {
            jedisPool = null;
            for (int i = 0; i < jedisConfig.getReconnectRetryCount(); i++) {
                shutdownPool();
                initConnectionPool();
                if (jedisPool != null) {
                    Jedis jd = getWorkingJedis();
                    if (jd != null) {
                        jd.select(jedisConfig.getDatabaseIndex());
                        return jd;
                    }
                }
                // 等待获取链接
                if (i < jedisConfig.getReconnectRetryCount() - 1) {
                    try {
                        Thread.sleep(jedisConfig.getReconnectRetryWaittime());
                    } catch (Exception e) {
                    }
                }
            }
        }
        return null;
    }

    protected void shutdownPool() {
        if (jedisPool == null) {
            return;
        }
        jedisPool.destroy();
        jedisPool = null;
    }

    protected void returnJedis(Jedis jedis) {
        if (jedisPool != null) {
            jedisPool.returnResource(jedis);
        }
    }

    protected Jedis getWorkingJedis() {
        for (int i = 0; i < jedisConfig.getFailedResourceBeforeReconnect(); i++) {
            Jedis j = jedisPool.getResource();
            if (j.isConnected()) {
                return j;
            } else {
                jedisPool.returnBrokenResource(j);
            }
        }
        return null;
    }

    protected void initConnectionPool() {
        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
        poolConfig.setMinIdle(jedisConfig.getPoolMinIdle());
        poolConfig.setMaxIdle(jedisConfig.getPoolMinIdle());
        poolConfig.setMaxTotal(jedisConfig.getPoolMaxActive());
        if (jedisConfig.isCluster() == true) {
            Set<String> sentinels = new HashSet<String>();
            String[] sentinels_ = jedisConfig.getSentinelHost().split(",");
            for (String sentinel : sentinels_) {
                sentinels.add(sentinel);
            }
            jedisPool = new JedisSentinelPool(jedisConfig.getMasterName(), sentinels, poolConfig, jedisConfig.getTimeout() == 0 ? Protocol.DEFAULT_TIMEOUT : jedisConfig.getTimeout(), jedisConfig.getPassword(), jedisConfig.getDatabaseIndex() != 0 ? jedisConfig.getDatabaseIndex() : Protocol.DEFAULT_DATABASE);
        } else {
            jedisPool = new JedisPool(poolConfig, jedisConfig.getMasterHost(), jedisConfig.getMasterPort(), jedisConfig.getTimeout() == 0 ? Protocol.DEFAULT_TIMEOUT : jedisConfig.getTimeout(), jedisConfig.getPassword(), jedisConfig.getDatabaseIndex() != 0 ? jedisConfig.getDatabaseIndex() : Protocol.DEFAULT_DATABASE);
        }
    }

    public <T> T withJedisWork(JedisWork<T> jedisWork) {
        Jedis jedis = getJedis();
        T t = jedisWork.execute(jedis);
        returnJedis(jedis);
        return t;
    }

    interface Work<Return, Param> {
        public Return execute(Param param);
    }

    public interface JedisWork<Return> extends Work<Return, Jedis> {
    }
}
