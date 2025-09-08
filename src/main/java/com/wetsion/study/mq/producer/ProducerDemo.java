package com.wetsion.study.mq.producer;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

public class ProducerDemo {

    public static void main(String[] args) {
        // 发送消息
        // 创建生产者实例
        DefaultMQProducer producer = new DefaultMQProducer("demo_group");

        // 设置NameServer地址
        producer.setNamesrvAddr("192.168.31.89:9876");

        // 启动生产者
        try {
            producer.start();
        } catch (MQClientException e) {
            throw new RuntimeException(e);
        }

        // 发送消息
        Message msg = new Message("TopicTest", "TagA",
                "OrderID188", "Hello RocketMQ".getBytes());

        SendResult sendResult = null;
        try {
            sendResult = producer.send(msg);
        } catch (MQClientException | RemotingException | MQBrokerException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Send Result: " + sendResult);

        // 关闭生产者
        producer.shutdown();
    }
}
