package com.github.gescof.springbootrabbitmqconcept.service;

import com.github.gescof.springbootrabbitmqconcept.config.RabbitMqConfig;
import com.github.gescof.springbootrabbitmqconcept.model.MessageDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class MessageConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageConsumer.class);

    private final RabbitMqConfig rabbitMqConfig;

    public MessageConsumer(RabbitMqConfig rabbitMqConfig) {
        this.rabbitMqConfig = rabbitMqConfig;
    }

    @RabbitListener(queues = "#{rabbitMqConfig.getQueueName()}")
    public void consumeMessage(MessageDto message) {
        LOGGER.info("Received message from queue '{}': {}", rabbitMqConfig.getQueueName(), message);
        // Process the message here
        LOGGER.info("Message processed successfully from queue '{}'", rabbitMqConfig.getQueueName());
    }
}
