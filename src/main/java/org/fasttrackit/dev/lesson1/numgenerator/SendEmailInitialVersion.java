package org.fasttrackit.dev.lesson1.numgenerator;


import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;


public class SendEmailInitialVersion {

    private static final Logger LOGGER = Logger.getLogger(SendMail.class.getName());


    /**
     * very important: pls make sure your Gmail account has lowered the security settings
     * to allow less secure apps to acess your account;
     * you would better create a new account for this exersise
     */
    public static void sendEmailUsingGmail(int numberOfTries, int guessedNumber, double time, String toEmail) {

        LOGGER.log(Level.FINE, "calling gmail...start...");

        final String username = "user_gmail"; // change this to reflect your own account
        final String password = "user_password";  // change this to reflect your own account

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(toEmail));
            message.setSubject("Num-guess");
            message.setText("Congratulation! You won! \nYou guessed the number: " + guessedNumber + " after " + numberOfTries + " tries.\nYour time:" + time);
            Transport.send(message);
            LOGGER.log(Level.FINE, "gmail done, email sent ok");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "There are some troubles while sending emails ...", e);
        }
    }



    public static void sampleSendEmailUsingGoogle() {

        final String username = "user_gmail"; // change this to reflect your own account
        final String password = "user_password";  // change this to reflect your own account
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        try {
            Message message = new MimeMessage(session);
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("ionel_condor@yahoo.com"));
            message.setSubject("Subject of the email");
            message.setText("Text of the message");
            Transport.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] a) {
        SendEmailInitialVersion.sendEmailUsingGmail(1, 1, 1.0, "ionel_condor@yahoo.com");
    }

}