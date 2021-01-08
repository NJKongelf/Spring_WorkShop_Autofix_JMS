package se.iths.autofixjms.jms.sender;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class Sender {

    Logger logger = LoggerFactory.getLogger(Sender.class);
    RabbitTemplate rabbitTemplate;

    public Sender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

   // @Scheduled(fixedRate = 2000)
    public void sendMessage() {
        logger.info("Message sent: Hello world");
        rabbitTemplate.convertAndSend("TestQueue", "Hello, world!");

    }

}
