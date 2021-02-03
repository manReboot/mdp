package com.example.demo;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

@Configuration
@Log
public class JmsHelper {

    @Value("${mq.queue.name}")
    String queueName;

    @Autowired
    JmsTemplate jmsTemplate;

    public void sendMessage(String test_message) {
        log.info("in mg helper");

        while (true) {
            jmsTemplate.send(queueName, new MessageCreator() {
                @Override
                public Message createMessage(Session session) throws JMSException {
                    return session.createTextMessage("hello world queue");
                }
            });
        }
        //log.info("ended");
    }
}
