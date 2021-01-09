package se.iths.autofixjms.jms.receiver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import se.iths.autofixjms.jms.email.EmailServiceImpl;
import se.iths.autofixjms.jms.model.MessageObject;

import java.time.LocalDateTime;

@Component
public class Receiver {

    private Logger logger = LoggerFactory.getLogger(Receiver.class);
    private RabbitTemplate rabbitTemplate;
    private EmailServiceImpl emailService;

    public Receiver(RabbitTemplate rabbitTemplate, EmailServiceImpl emailService) {
        this.rabbitTemplate = rabbitTemplate;
        this.emailService=emailService;
    }

//    @Scheduled(fixedRate = 2000)
    @RabbitListener(queues = "TestQueue")
    public void receiveMessage(@Payload String message) {
        logger.info("Message received: "+message);

    }
    @RabbitListener(queues = "MailQueue")
    public void receiveMessageMail(@Payload MessageObject messageObject) {
        logger.info("Message Mail received: "+messageObject.getEmail());
        emailService.sendSimpleMessage(messageObject.getEmail(),"Welcome to Autofix "+messageObject.getFirstname(), messageObject.getMessage());
    }
}
