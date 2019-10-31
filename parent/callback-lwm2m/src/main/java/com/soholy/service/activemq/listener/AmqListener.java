package com.soholy.service.activemq.listener;

import com.soholy.service.activemq.consumer.AmqConsumer;
import lombok.extern.java.Log;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Log
@Component
public class AmqListener {


    private ExecutorService threadPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    @JmsListener(destination = "${spring.activemq.ActiveMQTopic}", containerFactory = "jmsListenerContainerTopic")
    public void topicConsumer(String message) {
        System.out.println(message);
    }

    //@SendTo
    @JmsListener(destination = "${spring.activemq.ActiveMQQueue}", containerFactory = "jmsListenerContainerQueue")
    public void queueConsumer(Message message) {
        System.out.println(message.getPayload());
        System.out.println(message.getHeaders());
        threadPool.execute(new AmqConsumer(message.getPayload().toString()));
        System.out.println("当前消费线程为 : " + Thread.currentThread().getName() + ", 消费消息: {\" " + message.getPayload().toString() + " \"}");
    }


}