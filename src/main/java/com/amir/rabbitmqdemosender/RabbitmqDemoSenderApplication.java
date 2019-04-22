package com.amir.rabbitmqdemosender;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RabbitmqDemoSenderApplication implements CommandLineRunner {

	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	public static void main(String[] args) {
		SpringApplication.run(RabbitmqDemoSenderApplication.class, args).close();;
	}


	@Bean
	public ConnectionFactory connectionFactory() {
		CachingConnectionFactory factory =  new CachingConnectionFactory("localhost");
		factory.setPort(5672);
		factory.setUsername("guest");
		factory.setPassword("guest");
		return factory;
	}
	
	@Override
	public void run(String... args) throws Exception {
		System.out.println("message sending started.");
		rabbitTemplate.convertAndSend("ami.direct", "", "First Message from client code");
		System.out.println("message sending finished.");
	}
	
}
