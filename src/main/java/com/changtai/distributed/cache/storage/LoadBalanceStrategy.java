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

}
