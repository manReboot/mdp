package com.example.demo;

import lombok.extern.java.Log;
import org.apache.activemq.memory.list.MessageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@Log
@Component
public class JmsListenerActive{


    @JmsListener( destination = "java2blog.queue", containerFactory = "jmsListenerContainerFactory")
    public void onMessage(Message message) {

        if (message instanceof TextMessage) {
            try {
                String msg = ((TextMessage) message).getText();
                log.info("Message has been consumed : " + msg);
            } catch (JMSException ex) {
                throw new RuntimeException(ex);
            }
        } else {
            throw new IllegalArgumentException("Message Error");
        }

    }
}
