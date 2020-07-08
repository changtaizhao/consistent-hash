package com.changtai.distributed.cache;

import com.changtai.distributed.cache.storage.Server;

/**
 * Cache 接口
 *
 * @author zhaoct
 * @date 2020-07-07 15:13
 */
public interface Cache {

    /**
     * 向缓存中放数据
     * @param key
     * @param value
     */
    void put(String key, String value);

    /**
     * 从缓存中获取数据
     * @param key
     * @return
     */
    String get(String key);

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
