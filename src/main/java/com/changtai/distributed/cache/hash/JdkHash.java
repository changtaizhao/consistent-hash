package com.changtai.distributed.cache.hash;

/**
 * JDK hash
 *
 * @author zhaoct
 * @date 2020-07-07 15:22
 */
public class JdkHash implements HashStrategy{

    @Override
    public int getHashCode(String str) {
        return str.hashCode();
    }
}
