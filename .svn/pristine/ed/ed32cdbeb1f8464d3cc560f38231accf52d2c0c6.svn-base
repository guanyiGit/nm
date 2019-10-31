package com.soholy.cb.service.impl;

import com.soholy.cb.service.AcmqService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.jms.Queue;
import java.util.logging.Logger;

@Service
public class AcmqServiceImpl implements AcmqService {
    private static final Logger log = Logger.getLogger(AcmqServiceImpl.class.getName());

    @Autowired
    private JmsMessagingTemplate jmsTemplate;

    @Autowired
    private Queue queue;

    public void dataPushMq(String message) {
        if (StringUtils.isNotBlank(message))
            try {
                log.info("当前生产线程为 ：  " + Thread.currentThread().getName() + ", 生产消息： " + message);
                this.jmsTemplate.convertAndSend(this.queue, message);
            } catch (Exception e) {
                log.warning("mq消息推送异常！");
                e.printStackTrace();
            }
    }
}
