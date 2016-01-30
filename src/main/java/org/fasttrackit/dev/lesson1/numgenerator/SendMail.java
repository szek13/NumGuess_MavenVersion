package org.fasttrackit.dev.lesson1.numgenerator;


        import javax.mail.*;
        import javax.mail.internet.InternetAddress;
        import javax.mail.internet.MimeMessage;
        import java.util.Properties;
        import java.util.logging.Level;
        import java.util.logging.Logger;

/**
 * Created by Admin on 28.03.2015.
 */
public class SendMail implements Runnable{


    private int numberOfTries;
    private int guessedNumber;
    private double time;
    private String toEmail;

    private static final Logger LOGGER = Logger.getLogger( SendMail.class.getName() );


    public SendMail(int numberOfTries, int guessedNumber, double time, String toEmail ){
        this.numberOfTries=numberOfTries;
        this.guessedNumber=guessedNumber;
        this.time=time;
        this.toEmail=toEmail;
    }

    public void sendEmail() {
        Thread t =new Thread(this);
        t.start();
    }

    public void run(){


        System.out.println("calling gmail...start...");
        final String username = System.getenv("GM_U");
        final String password = System.getenv("GM_P");
        System.out.println("u/p="+username+"/"+password);

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
            //message.setFrom(new InternetAddress("ionel.condor@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(toEmail));
            message.setSubject("Num-guess");
            message.setText("Congratulation! You won! \nYou guessed the number: " + guessedNumber + " after " + numberOfTries + " tries.\nYour time:" + time);

            Transport.send(message);

            System.out.println("gmail done, email sent ok");

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "There are some troubles while sending emails ...", e);
        }
    }

    public static void main(String[] a) {

        SendMail sm = new SendMail(1,1,1.0, "ionel_condor@yahoo.com");
        sm.sendEmail();
    }

}