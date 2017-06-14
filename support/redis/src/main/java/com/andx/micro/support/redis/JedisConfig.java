package com.andx.micro.support.redis;

/**
 * Jedis 配置
 *
 * @Author andongxu
 * @Create time 16-2-14:下午4:29
 * @Version
 * @Last update time
 */
public class JedisConfig {

    /**
     * 是否采用集群
     */
    private boolean cluster;
    /**
     * 非集群模式master host
     */
    private String masterHost;
    /**
     * 非集群模式master port
     */
    private int masterPort;
    /**
     * 访问redis密码
      */
    private String password;
    /**
     * 链接超时时间
     */
    private int timeout;
    /**
     * sentinel集群master name
     */
    private String masterName;
    /**
     * 集群模式sentinels host
     */
    private String sentinelHost;
    /**
     * 数据库
     */
    private int databaseIndex;

    /**
     * Jedis连接池激活的最大链接数
     */
    private int poolMaxActive = 32;

    /**
     * Jedis连接池允许的最小空闲连接数
     */
    private int poolMinIdle = 24;

    /**
     * 在放弃或者重新初始化链接池之前，获取链接的次数
     */
    private int failedResourceBeforeReconnect = poolMaxActive / 2 + 1;

    /**
     * 重新链接重试次数
     */
    private int reconnectRetryCount = 48;

    /**
     * 每次重新链接等待时间（毫秒）
     */
    private int reconnectRetryWaittime = 5000;

    public boolean isCluster() {
        return cluster;
    }

    public void setCluster(boolean cluster) {
        this.cluster = cluster;
    }

    public String getMasterHost() {
        return masterHost;
    }

    public void setMasterHost(String masterHost) {
        this.masterHost = masterHost;
    }

    public int getMasterPort() {
        return masterPort;
    }

    public void setMasterPort(int masterPort) {
        this.masterPort = masterPort;
    }

    public String getSentinelHost() {
        return sentinelHost;
    }

    public void setSentinelHost(String sentinelHost) {
        this.sentinelHost = sentinelHost;
    }

    public int getPoolMaxActive() {
        return poolMaxActive;
    }

    public void setPoolMaxActive(int poolMaxActive) {
        this.poolMaxActive = poolMaxActive;
    }

    public int getPoolMinIdle() {
        return poolMinIdle;
    }

    public void setPoolMinIdle(int poolMinIdle) {
        this.poolMinIdle = poolMinIdle;
    }

    public int getFailedResourceBeforeReconnect() {
        return failedResourceBeforeReconnect;
    }

    public void setFailedResourceBeforeReconnect(int failedResourceBeforeReconnect) {
        this.failedResourceBeforeReconnect = failedResourceBeforeReconnect;
    }

    public int getReconnectRetryCount() {
        return reconnectRetryCount;
    }

    public void setReconnectRetryCount(int reconnectRetryCount) {
        this.reconnectRetryCount = reconnectRetryCount;
    }

    public int getReconnectRetryWaittime() {
        return reconnectRetryWaittime;
    }

    public int getDatabaseIndex() {
        return databaseIndex;
    }

    public void setDatabaseIndex(int databaseIndex) {
        this.databaseIndex = databaseIndex;
    }

    public String getMasterName() {
        return masterName;
    }

    public void setMasterName(String masterName) {
        this.masterName = masterName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public void setReconnectRetryWaittime(int reconnectRetryWaittime) {
        this.reconnectRetryWaittime = reconnectRetryWaittime;
    }
}
