package com.pauloelienay.springrabbitmq.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
	@Bean
	public Queue pendenciesQueue() {
		return new Queue("pendencies");
	}

	@Bean
	public Queue tasksQueue() {
		return new Queue("tasks");
	}
}
