package com.wetsion.study.factorybean;

public interface ILog extends IPrint<Integer> {

    default void log(Integer level, Object... msg) {
        print(msg.length > 0 ? (String) msg[0] : null);
    }

    void print(String msg);
}
