package com.soholy.service.activemq;

import lombok.extern.java.Log;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.jms.Queue;

@Service
@Log
public class AcmqServiceImpl implements AcmqService {

    @Autowired
    private JmsMessagingTemplate jmsTemplate;

    @Autowired
    private Queue queue;

    /**
     * 位置数据推入队列
     *
     * @param message
     * @return
     */
    @Override
    public void dataPushMq(String message) {
        //位置数据推入队列
        if (StringUtils.isNotBlank(message)) {
            try {
                log.info("当前生产线程为 ：  " + Thread.currentThread().getName() + ", 生产消息： \" " + message + " \"");
                jmsTemplate.convertAndSend(queue, message);
            } catch (Exception e) {
                log.warning("mq消息推送异常！");
                e.printStackTrace();
            }
        }

    }
}
