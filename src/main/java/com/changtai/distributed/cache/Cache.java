package com.changtai.distributed.cache;

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
}
