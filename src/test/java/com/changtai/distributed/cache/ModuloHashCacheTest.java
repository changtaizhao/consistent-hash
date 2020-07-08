package com.changtai.distributed.cache;

import com.changtai.distributed.cache.hash.FnvHash;
import com.changtai.distributed.cache.storage.ModuloHashLoadBalance;
import com.changtai.distributed.cache.storage.Server;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * 缓存测试用例
 *
 * @author zhaoct
 * @date 2020-07-07 15:44
 */
public class ModuloHashCacheTest {

    private Cache cache;

    private List<Server> servers = new ArrayList<>();

    private List<String> keys = new ArrayList<>();

    @Before
    public void init(){

        //10台服务器
        servers.addAll(
                Arrays.asList(
                        new Server("服务器1"),
                        new Server("服务器2"),
                        new Server("服务器3"),
                        new Server("服务器4"),
                        new Server("服务器5"),
                        new Server("服务器6"),
                        new Server("服务器7"),
                        new Server("服务器8"),
                        new Server("服务器9"),
                        new Server("服务器10")
                ));
        cache = new DistributedCache(new ModuloHashLoadBalance(servers, new FnvHash()));

        //100w数据测试
        for(int i=0; i<1000000; i++){
            String key = UUID.randomUUID().toString();
            cache.put(key, "data" + i);
            keys.add(key);
        }
    }

    @Test
    public void testCache(){
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

    @Test
    public void testHitRate(){
        //添加一台服务器，看命中率的变化
        cache.addServer(new Server("服务器11"));

        int hit = 0;
        for(String key : keys){
            if(cache.get(key) != null){
                hit ++;
            }
        }

        System.out.println("添加一个节点缓存命中率:" + hit*100/keys.size() + "%");
    }

}
