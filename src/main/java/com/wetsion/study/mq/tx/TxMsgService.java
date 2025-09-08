package com.wetsion.study.mq.tx;

import java.util.concurrent.ConcurrentHashMap;

public class TxMsgService {

    public static final ConcurrentHashMap<String, Object> msgStore = new ConcurrentHashMap<>();

    public static final ConcurrentHashMap<String, Boolean> msgCompleteStore = new ConcurrentHashMap<>();

    public static void storeMsg(String msgId, Object msg) {
        msgStore.put(msgId, msg);
    }

    public static void completeMsg(String msgId) {
        msgCompleteStore.put(msgId, true);
    }
}
