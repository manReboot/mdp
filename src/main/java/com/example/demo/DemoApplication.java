package com.example.demo;

import lombok.extern.java.Log;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.jms.annotation.EnableJms;

import javax.jms.Queue;

@Log
@SpringBootApplication
public class DemoApplication<pubic> {

	public static void main(String[] args) {

		ConfigurableApplicationContext context = SpringApplication.run(DemoApplication.class, args);
		JmsHelper helper = context.getBean(JmsHelper.class);
		helper.sendMessage("test message");

	}




	@Bean
	public Queue queue()
	{
		return new ActiveMQQueue("java2blog.queue");
	}


	/*@EventListener(ApplicationReadyEvent.class)
	public void doStartup()  {
		log.info("sending message");
		jmsHelper.sendMessage("test message");
		log.info("sending message end");
	}*/

}
