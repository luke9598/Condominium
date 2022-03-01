package logic.controller.applicationcontroller;

import javafx.collections.ObservableList;
import logic.engineeringclasses.bean.MeetRequestBean;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

public class EmailController {

    private static final String SMTP_HOST = "smtp.gmail.com";
    private static final String FROM_ADDRESS = "condominium.ispw@gmail.com";
    private static final String PASSWORD = "Ispw_2021";
    private static final String FROM_NAME = "noreply.condominium";

    public boolean send(String[] recipients, String[] bccRecipients, String subject, String message) {
        try {
            Properties props = new Properties();
            props.put("mail.smtp.host", SMTP_HOST);
            props.put("mail.smtp.auth", "true");
            props.put("mail.debug", "false");
            props.put("mail.smtp.ssl.enable", "true");
            Session session = Session.getInstance(props, new SocialAuth());
            Message msg = new MimeMessage(session);
            InternetAddress from = new InternetAddress(FROM_ADDRESS, FROM_NAME);
            msg.setFrom(from);
            InternetAddress[] toAddresses = new InternetAddress[recipients.length];
            for (int i = 0; i < recipients.length; i++) {
                toAddresses[i] = new InternetAddress(recipients[i]);
            }
            msg.setRecipients(Message.RecipientType.TO, toAddresses);
            InternetAddress[] bccAddresses = new InternetAddress[bccRecipients.length];
            for (int j = 0; j < bccRecipients.length; j++) {
                bccAddresses[j] = new InternetAddress(bccRecipients[j]);
            }
            msg.setRecipients(Message.RecipientType.BCC, bccAddresses);
            msg.setSubject(subject);
            msg.setContent(message, "text/plain");
            Transport.send(msg);
            return true;
        } catch (UnsupportedEncodingException | MessagingException ex) {
            return false;
        }
    }

    static class SocialAuth extends Authenticator {
        @Override
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(FROM_ADDRESS, PASSWORD);
        }
    }

    public void meetEmail(ObservableList<String> list, MeetRequestBean bean) {
        for (String email : list) {
            String[] recipient = new String[]{email};
            send(recipient, recipient, bean.getObject(), bean.getTextArea());
        }
    }
}
