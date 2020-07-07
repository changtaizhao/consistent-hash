package com.changtai.distributed.cache;

import com.changtai.distributed.cache.storage.LoadBalanceStrategy;
import com.changtai.distributed.cache.storage.Server;

/**
 * 分布式cache
 *
 * @author zhaoct
 * @date 2020-07-07 15:15
 */
public class DistributedCache implements Cache{

    private LoadBalanceStrategy loadBalanceStrategy;

    public DistributedCache(LoadBalanceStrategy loadBalanceStrategy){
        this.loadBalanceStrategy = loadBalanceStrategy;
    }

    @Override
    public void put(String key, String value) {
        Server server = loadBalanceStrategy.getServer(key);
        server.put(key, value);
        //System.out.println(" key: " + key + " 写入到缓存服务器 " + server.getName());
    }

    @Override
    public String get(String key) {
        Server server = loadBalanceStrategy.getServer(key);
        //System.out.println(" key: " + key + " 从缓存服务器读取 " + server.getName());
        return server.get(key);
    }
}
