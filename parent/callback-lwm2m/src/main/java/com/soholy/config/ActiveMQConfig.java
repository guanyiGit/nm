package com.soholy.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.connection.SingleConnectionFactory;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.Queue;
import javax.jms.Topic;


@Configuration
public class ActiveMQConfig {
    @Value("${spring.activemq.ActiveMQQueue}")
    private String queueName;

    @Value("${spring.activemq.ActiveMQTopic}")
    private String topicName;

    @Value("${spring.activemq.user}")
    private String usrName;

    @Value("${spring.activemq.password}")
    private String password;

    @Value("${spring.activemq.broker-url}")
    private String brokerUrl;

    @Value("${spring.activemq.pool.max-connections}")
    private int maxConnections;

    @Bean
    //真正可以产生Connection的ConnectionFactory，由对应的 JMS服务厂商提供
    public ActiveMQConnectionFactory connectionFactory() {
        ActiveMQConnectionFactory bean = new ActiveMQConnectionFactory();
        bean.setBrokerURL(brokerUrl);
        bean.setUseAsyncSend(true);
        bean.setClientID(System.getenv("SESSIONNAME"));
        bean.setUserName(usrName);
        bean.setPassword(password);
        return bean;
    }

    @Bean
    //activemq 连接池
    public CachingConnectionFactory cachingConnectionFactory(ActiveMQConnectionFactory activeMQConnectionFactory) {
        CachingConnectionFactory bean = new CachingConnectionFactory();
        bean.setTargetConnectionFactory(activeMQConnectionFactory);
        bean.setSessionCacheSize(maxConnections);
        return bean;
    }

    @Bean
    //Spring用于管理真正的activemq 连接池
    public SingleConnectionFactory singleConnectionFactory(CachingConnectionFactory cachingConnectionFactory) {
        SingleConnectionFactory bean = new SingleConnectionFactory();
        bean.setTargetConnectionFactory(cachingConnectionFactory);
        return bean;
    }

    @Bean
    //JMS工具类，它可以进行消息发送、接收等
    public JmsMessagingTemplate jmsTemplate(@Qualifier("singleConnectionFactory") SingleConnectionFactory connectionFactory) {
        JmsTemplate jmsTemplate = new JmsTemplate();
        //NON_PERSISTENT非持久化 1 ，PERSISTENT持久化 2
        jmsTemplate.setTimeToLive(2);
        //开启事务
        jmsTemplate.setSessionTransacted(true);
        //手动确认消息
        jmsTemplate.setSessionAcknowledgeModeName("CLIENT_ACKNOWLEDGE");
        jmsTemplate.setConnectionFactory(connectionFactory);

        JmsMessagingTemplate bean = new JmsMessagingTemplate();
        bean.setJmsTemplate(jmsTemplate);
        bean.setConnectionFactory(connectionFactory);
        return bean;
    }

    @Bean
    //队列目的地，点对点的
    public JmsListenerContainerFactory<?> jmsListenerContainerQueue(@Qualifier("singleConnectionFactory") SingleConnectionFactory connectionFactory) {
        DefaultJmsListenerContainerFactory bean = new DefaultJmsListenerContainerFactory();
        bean.setConnectionFactory(connectionFactory);
        return bean;
    }

    @Bean
    //主题目的地，一对多的
    public JmsListenerContainerFactory<?> jmsListenerContainerTopic(@Qualifier("singleConnectionFactory") SingleConnectionFactory connectionFactory) {
        DefaultJmsListenerContainerFactory bean = new DefaultJmsListenerContainerFactory();
        //设置为发布订阅方式, 默认情况下使用的生产消费者方式
        bean.setPubSubDomain(true);
        bean.setConnectionFactory(connectionFactory);
        return bean;
    }

    @Bean
    //队列目的地，点对点的
    public Queue queue() {
        return new ActiveMQQueue(queueName);
    }

    @Bean
    //主题目的地，一对多的
    public Topic topic() {
        return new ActiveMQTopic(topicName);
    }


}
