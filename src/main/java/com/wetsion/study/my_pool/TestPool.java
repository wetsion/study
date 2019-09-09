package com.wetsion.study.my_pool;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.pool2.impl.DefaultPooledObjectInfo;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.junit.Test;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author weixin
 * @version 1.0
 * @CLassName TestPool
 * @date 2019/7/15 11:34 AM
 */
@Slf4j
public class TestPool {

    @Test
    public void test1() {
        GenericObjectPoolConfig config = new GenericObjectPoolConfig();
        config.setMaxTotal(50);
        GenericObjectPool<MyConnection> pool = new GenericObjectPool<>(new MyConnectionPooledFactory(), config);
        ExecutorService service = Executors.newCachedThreadPool();
        for (int i = 0; i < 15; i++) {
            service.execute(new Runnable() {
                @Override
                public void run() {
                    MyConnection connection = null;
                    try {
                        connection = pool.borrowObject();
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        pool.returnObject(connection);
                    }
                }
            });
        }
        service.shutdown();
        for (DefaultPooledObjectInfo pooledObjectInfo: pool.listAllObjects()) {
            System.out.println(pooledObjectInfo);
        }
    }

}
