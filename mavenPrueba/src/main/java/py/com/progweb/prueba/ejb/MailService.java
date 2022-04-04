//package py.com.progweb.prueba.ejb;
//
//
//import javax.ejb.Asynchronous;
//import javax.ejb.Lock;
//import javax.ejb.LockType;
//import javax.ejb.Singleton;
//import javax.enterprise.event.Observes;
//import javax.enterprise.event.TransactionPhase;
//import javax.inject.Inject;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;
//import javax.mail.Session;
//import javax.mail.Address;
//import javax.mail.Message;
//import javax.mail.MessagingException;
//import javax.mail.Transport;
//
//@Singleton
//public class MailService {
//
//    @Inject
//    private Session mailSession; //more on this later
//
//    @Asynchronous
//    @Lock(LockType.READ)
//    public void sendMail(@Observes(during = TransactionPhase.AFTER_SUCCESS) MailEvent event) {
//        try {
//            MimeMessage m = new MimeMessage(mailSession);
//            Address[] to = new InternetAddress[] {new InternetAddress(event.getTo())};
//
//            m.setRecipients(Message.RecipientType.TO, to);
//            m.setSubject(event.getSubject());
//            m.setSentDate(new java.util.Date());
//            m.setContent(event.getMessage(),"text/plain");
//
//            Transport.send(m);
//            System.out.println("Email to " + event.getTo() + " has been sent!");
//        } catch (MessagingException e) {
//            throw new RuntimeException(e);
//        }
//    }
//}
