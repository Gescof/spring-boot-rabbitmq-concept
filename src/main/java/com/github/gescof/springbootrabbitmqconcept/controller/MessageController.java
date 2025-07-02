package com.github.gescof.springbootrabbitmqconcept.controller;

import com.github.gescof.springbootrabbitmqconcept.service.MessageProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    private final MessageProducer messageProducer;

    public MessageController(MessageProducer messageProducer) {
        this.messageProducer = messageProducer;
    }

    @PostMapping
    public ResponseEntity<String> sendMessage(@RequestParam String message,
                                              @RequestParam(defaultValue = "Anonymous") String sender) {
        messageProducer.sendMessage(message, sender);
        return ResponseEntity.ok("Message sent to RabbitMQ");
    }
}
