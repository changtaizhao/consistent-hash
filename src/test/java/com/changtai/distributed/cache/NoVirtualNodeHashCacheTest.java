package com.changtai.distributed.cache;

import com.changtai.distributed.cache.hash.FnvHash;
import com.changtai.distributed.cache.storage.ConsistentHashLoadBalance;
import com.changtai.distributed.cache.storage.NoVirtualNodeHashLoadBalance;
import com.changtai.distributed.cache.storage.Server;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * 缓存测试用例
 *
 * @author zhaoct
 * @date 2020-07-07 15:44
 */
public class NoVirtualNodeHashCacheTest {

    private Cache cache;

    private List<Server> servers;

    @Before
    public void init(){

        int nodeNumber = 0;
        //10台服务器，没有虚拟节点
        servers = Arrays.asList(
                new Server("服务器1", nodeNumber),
                new Server("服务器2", nodeNumber),
                new Server("服务器3", nodeNumber),
                new Server("服务器4", nodeNumber),
                new Server("服务器5", nodeNumber),
                new Server("服务器6", nodeNumber),
                new Server("服务器7", nodeNumber),
                new Server("服务器8", nodeNumber),
                new Server("服务器9", nodeNumber),
                new Server("服务器10", nodeNumber)
        );
        cache = new DistributedCache(new NoVirtualNodeHashLoadBalance(servers, new FnvHash()));
    }

    @Test
    public void testCache(){
        //100w数据测试
        for(int i=0; i<1000000; i++){
            cache.put(UUID.randomUUID().toString(), "data" + i);
        }
    }

    @After
    public void summary(){
        //打印元素个数
        for(Server server : servers){
            System.out.println(server.getName() + " 有 " + server.getSize() + " 个元素 ");
        }
        //统计标准差
        //1. 平均值
        int sum = 0;
        for(Server server : servers){
           sum += server.getSize();
        }
        int average = sum / servers.size();
        System.out.println(" 平均值是 " + average);
        //标准差
        double temp = 0;
        for(Server server : servers){
            temp += Math.pow(server.getSize() - average , 2);
        }
        double sd = Math.sqrt(temp/ servers.size());
        System.out.println(" 标准差是 " + sd);
    }

}
