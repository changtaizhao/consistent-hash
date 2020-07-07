package com.changtai.distributed.cache.storage;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 一致性hash环上面的虚拟节点
 *
 * @author zhaoct
 * @date 2020-07-07 10:00
 */
public class Node {

    /**
     * 虚拟节点名称
     */
    private String name;

    /**
     * 缓存服务器
     */
    private Server server;

    /**
     * 虚拟节点上数据数量，用于统计
     */
    private AtomicInteger count;

    public Node(String name, Server server){
        this.name = name;
        this.server = server;
        this.count = new AtomicInteger(0);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Server getServer() {
        return server;
    }

    public void setServer(Server server) {
        this.server = server;
    }

    public void incrementCount(){
        count.incrementAndGet();
    }
}
