package se.iths.autofixjms.jms.receiver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class Receiver {

    Logger logger = LoggerFactory.getLogger(Receiver.class);
    RabbitTemplate rabbitTemplate;

    public Receiver(RabbitTemplate rabbitTemplate) {

        this.rabbitTemplate = rabbitTemplate;
    }

   // @Scheduled(fixedRate = 2000)
    @RabbitListener(queues = "TestQueue")
    public void receiveMessage(@Payload String message) {
        logger.info("Message received: "+message);

    }

}
