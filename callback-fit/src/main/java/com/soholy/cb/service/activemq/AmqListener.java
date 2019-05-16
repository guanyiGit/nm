package com.soholy.cb.service.activemq;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

@Component
public class AmqListener {
    private static final Logger log = Logger.getLogger(AmqListener.class.getName());

    private ExecutorService threadPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    @JmsListener(destination = "${spring.activemq.ActiveMQTopic}", containerFactory = "jmsListenerContainerTopic")
    public void topicConsumer(String message) {
        System.out.println(message);
    }

    @JmsListener(destination = "${spring.activemq.ActiveMQQueue}", containerFactory = "jmsListenerContainerQueue")
    public void queueConsumer(Message message) {
        System.out.println(message.getPayload());
        System.out.println(message.getHeaders());
        this.threadPool.execute(new AmqConsumer(message.getPayload().toString()));
        System.out.println("当前消费线程为 : " + Thread.currentThread().getName() + ", 消费消息: {" + message.getPayload().toString() + "}");
    }
}
