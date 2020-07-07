package com.changtai.distributed.cache.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 缓存服务器
 *
 * @author zhaoct
 * @date 2020-07-07 9:32
 */
public class Server {

    /**
     * 缓存服务器名称
     */
    private String name;

    /**
     * 虚拟节点个数
     */
    private Integer nodeNumber;

    /**
     * 缓存服务器的虚拟节点
     */
    private List<Node> nodeList;

    /**
     * mock 数据存储
     */
    private Map<String, String> dataMap = null;

    /**
     * 取模hash构造方法
     * @param name
     */
    public Server(String name){
        this.name = name;
        this.dataMap = new ConcurrentHashMap<>();
    }

    /**
     * 一致性hash构造方法
     * @param name
     * @param nodeNumber
     */
    public Server(String name, Integer nodeNumber){
        this.name = name;
        this.nodeNumber = nodeNumber;
        this.nodeList = new ArrayList<>();
        this.dataMap = new ConcurrentHashMap<>();

        //初始化虚拟节点
        for (Integer i = 0; i < nodeNumber; i++) {
            Node node = new Node(name + " : " + i, this);
            nodeList.add(node);
        }
    }

    public void put(String key, String value){
        this.dataMap.put(key, value);
    }

    public String get(String key){
        return dataMap.get(key);
    }

    public Integer getSize(){
        return dataMap.size();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNodeNumber() {
        return nodeNumber;
    }

    public void setNodeNumber(Integer nodeNumber) {
        this.nodeNumber = nodeNumber;
    }

    public List<Node> getNodeList() {
        return nodeList;
    }

    public void setNodeList(List<Node> nodeList) {
        this.nodeList = nodeList;
    }
}
