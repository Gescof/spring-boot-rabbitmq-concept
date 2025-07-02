package com.github.gescof.springbootrabbitmqconcept.service;

import com.github.gescof.springbootrabbitmqconcept.config.RabbitMqConfig;
import com.github.gescof.springbootrabbitmqconcept.model.MessageDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class MessageProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageProducer.class);

    private final RabbitTemplate rabbitTemplate;
    private final RabbitMqConfig rabbitMqConfig;

    public MessageProducer(RabbitTemplate rabbitTemplate, RabbitMqConfig rabbitMqConfig) {
        this.rabbitTemplate = rabbitTemplate;
        this.rabbitMqConfig = rabbitMqConfig;
    }

    public void sendMessage(String message, String sender) {
        MessageDto messageDto = new MessageDto(message, sender, LocalDateTime.now().toEpochSecond(ZoneOffset.UTC));
        LOGGER.info("Sending message: {}", messageDto);
        rabbitTemplate.convertAndSend(rabbitMqConfig.getExchangeName(), rabbitMqConfig.getRoutingKey(), messageDto);
    }
}
