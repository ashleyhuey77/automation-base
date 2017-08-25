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
						return new PasswordAuthentication(LocalTest.getCredentials().getEmailServerUN(), 
								LocalTest.getCredentials().getEmailServerUN());
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
            t.connect(LocalTest.getCredentials().getEmailServerUN(), 
					LocalTest.getCredentials().getEmailServerUN());
            t.sendMessage(replyMessage.get(), replyMessage.get().getRecipients(RecipientType.TO));;
            t.close();
            System.out.println("Replied to message successfully ....");
		} catch (Exception e) {
			throw e;
		}
	}
	
	public static void composeReplyEmailMessage(Message messageToReplyTo, String testMessage, String to, String from, String body) throws Exception {
		try {
			Message rMessage = new MimeMessage(session.get());
			rMessage = (MimeMessage) messageToReplyTo.reply(false);
			
			Multipart mp = new MimeMultipart("alternative");
			
			MimeBodyPart htmlPart = new MimeBodyPart();
			htmlPart.setContent("<h1> " + testMessage + " </h1> <br>" + body, "text/html; charset=utf-8");
			
			mp.addBodyPart(htmlPart);
			
			rMessage.setFrom(new InternetAddress(to));
            Address[] add = { new InternetAddress(from) };
            rMessage.setReplyTo(add);
            rMessage.setRecipient(RecipientType.TO, new InternetAddress(from));
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
