package se.iths.autofixjms.jms.email;



public interface EmailService {
    void sendSimpleMessage(String to,
                           String subject,
                           String text);

}
