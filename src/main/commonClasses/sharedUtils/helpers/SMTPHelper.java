package commonClasses.sharedUtils.helpers;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.internet.MimeMessage.RecipientType;

import commonClasses.sharedUtils.enums.Protocol;
import commonClasses.sharedUtils.managers.LocalTest;

public class SMTPHelper {
	
	private static ThreadLocal<Session> session = new ThreadLocal<Session>();
	private static ThreadLocal<Message> replyMessage = new ThreadLocal<Message>();
	
	public static void setUpSMTPServer() throws Exception {
		try {
			EmailReceiver e2 = new EmailReceiver(Protocol.smtp, "localhost", "1025", false);
		    Properties properties2 = e2.property.get();
		      Session sess = Session.getInstance(properties2, new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(LocalTest.getEmailCredentials().getEmailServerUN(), 
								LocalTest.getEmailCredentials().getEmailServerPWD());
					}
				  });

		      //sess.setDebug(true);
		      System.out.println("SMTP session started... ");
		      session.set(sess);
		} catch (Exception e) {
			throw e;
		}
	}
	
	public static void sendEmailMessage() throws Exception {
		try {
			Transport t = session.get().getTransport();
            t.connect(LocalTest.getEmailCredentials().getEmailServerUN(), 
					LocalTest.getEmailCredentials().getEmailServerPWD());
            t.sendMessage(replyMessage.get(), replyMessage.get().getRecipients(RecipientType.TO));;
            t.close();
            System.out.println("Replied to message successfully ....");
		} catch (Exception e) {
			throw e;
		}
	}
	
	public static void composeReplyEmailMessage(String testMessage) throws Exception {
		try {
			Message rMessage = new MimeMessage(session.get());
			rMessage = (MimeMessage) IMAPHelper.getMessage().reply(false);
			
			Multipart mp = new MimeMultipart("alternative");
			
			MimeBodyPart htmlPart = new MimeBodyPart();
			htmlPart.setContent("<h1> " + testMessage + " </h1> <br>" + IMAPHelper.getBody(), "text/html; charset=utf-8");
			
			mp.addBodyPart(htmlPart);
			
			rMessage.setFrom(new InternetAddress(IMAPHelper.getTo()));
            Address[] add = { new InternetAddress(IMAPHelper.getFrom()) };
            rMessage.setReplyTo(add);
            rMessage.setRecipient(RecipientType.TO, new InternetAddress(IMAPHelper.getFrom()));
            rMessage.saveChanges();
            rMessage.setContent(mp, "text/html");
            rMessage.saveChanges();
            
            replyMessage.set(rMessage);
            System.out.println("Reply message composed... ");
		} catch (Exception e) {
			throw e;
		}
	}

}
