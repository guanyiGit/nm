package com.soholy.cb.config;

import javax.jms.Queue;
import javax.jms.Topic;
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
  public ActiveMQConnectionFactory connectionFactory() {
    ActiveMQConnectionFactory bean = new ActiveMQConnectionFactory();
    bean.setBrokerURL(this.brokerUrl);
    bean.setUseAsyncSend(true);
    bean.setClientID(System.getenv("SESSIONNAME"));
    bean.setUserName(this.usrName);
    bean.setPassword(this.password);
    return bean;
  }
  
  @Bean
  public CachingConnectionFactory cachingConnectionFactory(ActiveMQConnectionFactory activeMQConnectionFactory) {
    CachingConnectionFactory bean = new CachingConnectionFactory();
    bean.setTargetConnectionFactory(activeMQConnectionFactory);
    bean.setSessionCacheSize(this.maxConnections);
    return bean;
  }
  
  @Bean
  public SingleConnectionFactory singleConnectionFactory(CachingConnectionFactory cachingConnectionFactory) {
    SingleConnectionFactory bean = new SingleConnectionFactory();
    bean.setTargetConnectionFactory(cachingConnectionFactory);
    return bean;
  }
  
  @Bean
  public JmsMessagingTemplate jmsTemplate(@Qualifier("singleConnectionFactory") SingleConnectionFactory connectionFactory) {
    JmsTemplate jmsTemplate = new JmsTemplate();
    jmsTemplate.setTimeToLive(2L);
    jmsTemplate.setSessionTransacted(true);
    jmsTemplate.setSessionAcknowledgeModeName("CLIENT_ACKNOWLEDGE");
    jmsTemplate.setConnectionFactory(connectionFactory);
    JmsMessagingTemplate bean = new JmsMessagingTemplate();
    bean.setJmsTemplate(jmsTemplate);
    bean.setConnectionFactory(connectionFactory);
    return bean;
  }
  
  @Bean
  public JmsListenerContainerFactory<?> jmsListenerContainerQueue(@Qualifier("singleConnectionFactory") SingleConnectionFactory connectionFactory) {
    DefaultJmsListenerContainerFactory bean = new DefaultJmsListenerContainerFactory();
    bean.setConnectionFactory(connectionFactory);
    return bean;
  }
  
  @Bean
  public JmsListenerContainerFactory<?> jmsListenerContainerTopic(@Qualifier("singleConnectionFactory") SingleConnectionFactory connectionFactory) {
    DefaultJmsListenerContainerFactory bean = new DefaultJmsListenerContainerFactory();
    bean.setPubSubDomain(Boolean.valueOf(true));
    bean.setConnectionFactory(connectionFactory);
    return bean;
  }
  
  @Bean
  public Queue queue() { return new ActiveMQQueue(this.queueName); }
  
  @Bean
  public Topic topic() { return new ActiveMQTopic(this.topicName); }
}
