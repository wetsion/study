package com.wetsion.study.my_pool;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

/**
 * @author weixin
 * @version 1.0
 * @CLassName MyConnectionPooledFactory
 * @date 2019/7/15 11:49 AM
 */
public class MyConnectionPooledFactory extends BasePooledObjectFactory<MyConnection> {
    @Override
    public MyConnection create() throws Exception {
        MyConnection connection = new MyConnection();
        connection.setName("name" + System.currentTimeMillis());
        return connection;
    }

    @Override
    public PooledObject<MyConnection> wrap(MyConnection obj) {
        return new DefaultPooledObject<>(obj);
    }
}
