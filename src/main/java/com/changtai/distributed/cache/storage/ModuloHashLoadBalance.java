package com.changtai.distributed.cache.storage;

import com.changtai.distributed.cache.hash.HashStrategy;

import java.util.List;

/**
 * 取模 hash
 *
 * @author zhaoct
 * @date 2020-07-07 9:25
 */
public class ModuloHashLoadBalance implements LoadBalanceStrategy{

    /**
     * hash 方法
     */
    private HashStrategy hashStrategy;

    /**
     * 服务器列表
     */
    private List<Server> servers;

    public ModuloHashLoadBalance(List<Server> servers, HashStrategy hashStrategy){
        this.hashStrategy = hashStrategy;
        this.servers = servers;
    }

    @Override
    public Server getServer(String key) {
        return servers.get(hashStrategy.getHashCode(key) % servers.size());
    }

    @Override
    public void addServer(Server server) {
        servers.add(server);
    }

    @Override
    public void removeServer(Server server) {
        servers.remove(server);
    }

}
