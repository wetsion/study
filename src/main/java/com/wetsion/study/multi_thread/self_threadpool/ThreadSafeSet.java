package com.wetsion.study.multi_thread.self_threadpool;

import java.util.AbstractList;
import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author weixin
 * @version 1.0
 * @CLassName ThreadSafeSet
 * @date 2019/7/2 6:11 PM
 */
public class ThreadSafeSet<T> extends AbstractSet<T> {

    private ConcurrentHashMap<T, Object> map = new ConcurrentHashMap<>();

    private AtomicInteger count = new AtomicInteger();

    @Override
    public Iterator<T> iterator() {
        return map.keySet().iterator();
    }

    @Override
    public int size() {
        return count.get();
    }

    @Override
    public boolean add(T t) {
        count.incrementAndGet();
        return map.put(t, new Object()) == null;
    }

    @Override
    public boolean remove(Object o) {
        count.decrementAndGet();
        return Objects.nonNull(map.remove(o));
    }
}
