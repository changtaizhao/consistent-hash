package com.changtai.distributed.cache.storage;

/**
 *
 * 负载均衡接口
 * @author zhaoct
 * @date 2020-07-07 10:38
 */
public interface LoadBalanceStrategy {

    /**
     * 从分布式缓存中获取服务器
     * @param key
     * @return
     */
    Server getServer(String key);

    /**
     * 添加一台服务器
     * @param server
     */
    void addServer(Server server);

    /**
     * 删除一台服务器
     * @param server
     */
    void removeServer(Server server);

}
