package com.changtai.distributed.cache.storage;

import com.changtai.distributed.cache.hash.HashStrategy;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 没有虚拟节点一致性hash
 *
 * @author zhaoct
 * @date 2020-07-07 9:25
 */
public class NoVirtualNodeHashLoadBalance implements LoadBalanceStrategy {

    /**
     * hash 环
     */
    TreeMap<Integer, Server> ring ;

    /**
     * hash 方法
     */
    private HashStrategy hashStrategy;

    public NoVirtualNodeHashLoadBalance(List<Server> servers, HashStrategy hashStrategy){
        this.ring = new TreeMap<>();
        this.hashStrategy = hashStrategy;
        // 创建一致性hash环
        this.buildConsistentHashRing(servers);
    }

    private TreeMap<Integer, Server> buildConsistentHashRing(List<Server> servers) {
        for (Server server : servers) {
            ring.put(hashStrategy.getHashCode(server.getName()), server);
        }
        return ring;
    }

    @Override
    public Server getServer(String key) {

        Map.Entry<Integer, Server> entry = ring.ceilingEntry(hashStrategy.getHashCode(key));
        if(entry == null){
            //取第一个
            entry = ring.firstEntry();
        }

        return entry.getValue();
    }
}
