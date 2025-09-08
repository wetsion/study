package com.wetsion.study.mq.tx;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.Function;

@Service
public class BizService {

    @Autowired
    RocketMQTemplate rocketMQTemplate;

    public void biz() {
        // do something

    }

    private void sendMsg(String bizNo) {
        // 发送事务消息
        Message<String> message = MessageBuilder.withPayload("hello world").build();
        Function<String, Boolean> f = (s) -> doBiz(bizNo);
        rocketMQTemplate.sendMessageInTransaction("tx-topic", message, f);
    }

    @Transactional
    public boolean doBiz(String bizNo) {
        // do something
        return true;
    }
}
