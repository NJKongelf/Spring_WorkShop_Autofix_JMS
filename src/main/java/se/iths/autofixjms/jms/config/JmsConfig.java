package se.iths.autofixjms.jms.config;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Configuration
@Component
public class JmsConfig {

    @Bean
    public CachingConnectionFactory connectionFactory() {
        if(!(System.getProperty("os.name").equals("Windows 10"))) {
            CachingConnectionFactory factory = new CachingConnectionFactory(System.getenv("RABBIT_BROKER_URL"));
            factory.setPassword(System.getenv("RABBIT_BROKER_PASSWORD"));
            factory.setUsername(System.getenv("RABBIT_BROKER_USERNAME"));
            factory.setVirtualHost(System.getenv("RABBIT_BROKER_VHOST"));
            System.out.println(System.getProperty("os.name"));
            return factory;
        }
        else {
            CachingConnectionFactory factory = new CachingConnectionFactory(System.getProperty("RABBIT_BROKER_URL"));
            factory.setPassword(System.getProperty("RABBIT_BROKER_PASSWORD"));
            factory.setUsername(System.getProperty("RABBIT_BROKER_USERNAME"));
            factory.setVirtualHost(System.getProperty("RABBIT_BROKER_VHOST"));
            return factory;
        }

    }




    @Bean
    public AmqpAdmin amqpAdmin() {
        return new RabbitAdmin(connectionFactory());
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        return new RabbitTemplate(connectionFactory());
    }




//    @Bean
//    public JavaMailSender getJavaMailSender() {
//      //  JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
////        mailSender.setHost(System.getProperty("MAIL_HOST"));
////        mailSender.setPort(Integer.parseInt(System.getProperty("MAIL_PORT")));
////
////        mailSender.setUsername(System.getProperty("MAIL_USERNAME"));
////        mailSender.setPassword("password");
////
////        Properties props = mailSender.getJavaMailProperties();
////        props.put("mail.transport.protocol", "smtp");
////        props.put("mail.smtp.auth", "true");
////        props.put("mail.smtp.starttls.enable", "true");
////        props.put("mail.debug", "true");
//
//        //return mailSender;
//        return new JavaMailSenderImpl();
//    }

}
