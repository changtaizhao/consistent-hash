package com.changtai.distributed.cache.storage;

import com.changtai.distributed.cache.hash.HashStrategy;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 一致性hash
 *
 * @author zhaoct
 * @date 2020-07-07 9:25
 */
public class ConsistentHashLoadBalance implements LoadBalanceStrategy {

    /**
     * hash 环
     */
    TreeMap<Integer, Server> ring ;

    /**
     * hash 方法
     */
    private HashStrategy hashStrategy;

    /**
     * 服务器列表
     */
    private List<Server> servers;

    public ConsistentHashLoadBalance(List<Server> servers, HashStrategy hashStrategy){
        this.ring = new TreeMap<>();
        this.hashStrategy = hashStrategy;
        this.servers = servers;
        // 创建一致性hash环
        this.buildConsistentHashRing(this.servers);
    }

    private TreeMap<Integer, Server> buildConsistentHashRing(List<Server> servers) {
        //添加虚拟节点
        for (Server server : servers) {
            for (Node node : server.getNodeList()) {
                ring.put(hashStrategy.getHashCode(node.getName()), server);
            }
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

    @Override
    public void addServer(Server server) {
        servers.add(server);
        //添加到环上
        for (Node node : server.getNodeList()) {
            ring.put(hashStrategy.getHashCode(node.getName()), server);
        }
    }

    @Override
    public void removeServer(Server server) {
        servers.remove(server);
        //从环上删除
        for (Node node : server.getNodeList()) {
            ring.remove(hashStrategy.getHashCode(node.getName()));
        }
    }
}
