package com.example.demo;

import lombok.Getter;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ActiveMQPrefetchPolicy;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.SimpleJmsListenerContainerFactory;
import org.springframework.jms.config.SimpleJmsListenerEndpoint;
import org.springframework.jms.connection.SingleConnectionFactory;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.validation.annotation.Validated;

import javax.jms.ConnectionFactory;
import javax.jms.Queue;


@Configuration
@EnableJms
public class JmsConfiguration {

    @Value("${spring.activemq.broker-url}")
    String url;

    @Value("${mq.queue.name}")
    String queueName;


    @Bean
    public DefaultJmsListenerContainerFactory  jmsListenerContainerFactory() {
        DefaultJmsListenerContainerFactory   factory
                = new DefaultJmsListenerContainerFactory ();

        factory.setConnectionFactory(adminActiveMQConnectionFactory());
        factory.setConcurrency("5-6");
       /*SimpleJmsListenerEndpoint endpoint =
               new SimpleJmsListenerEndpoint();
       endpoint.setMessageListener(new JmsListenerActive());
       endpoint.setDestination(queueName);
        factory.createListenerContainer(endpoint);*/
        return factory;
    }

    @Bean
    public ActiveMQConnectionFactory adminActiveMQConnectionFactory() {
        //String url = properties.getJms().getUrl();

        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setBrokerURL(url);

        connectionFactory.setClientIDPrefix("C2MON-SERVER-CLIENT");
        //connectionFactory.setConnectionIDPrefix(properties.getJms().getConnectionIDPrefix() + properties.getJms().getClientIdPrefix());
        ActiveMQPrefetchPolicy prefetchPolicy = new ActiveMQPrefetchPolicy();
        prefetchPolicy.setQueuePrefetch(0);
        connectionFactory.setPrefetchPolicy(prefetchPolicy);
        return connectionFactory;
    }

    public ConnectionFactory connectionFactory() {
        return new SingleConnectionFactory( adminActiveMQConnectionFactory());
    }

}
