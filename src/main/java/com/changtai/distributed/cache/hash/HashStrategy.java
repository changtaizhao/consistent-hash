package com.changtai.distributed.cache.hash;

/**
 * hash方法接口
 *
 * @author zhaoct
 * @date 2020-07-07 15:20
 */
public interface HashStrategy {

    /**
     * 计算 hash code
     * @param str
     * @return
     */
    int getHashCode(String str);

}
