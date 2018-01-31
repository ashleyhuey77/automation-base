package common.utils.helpers;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.internet.MimeMessage.RecipientType;
import common.utils.enums.Protocol;
import common.utils.managers.LocalTest;

public class SMTPHelper {
	
	private static ThreadLocal<Session> session = new ThreadLocal<Session>();
	private static ThreadLocal<Message> replyMessage = new ThreadLocal<Message>();
	
	public static void setUpSMTPServer() throws Exception {
		try {
			EmailReceiver e2 = new EmailReceiver(Protocol.smtp, "smtp.mail.yahoo.com", "587", false);
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
			Transport t = session.get().getTransport("smtps");
			String uN = LocalTest.getEmailCredentials().getEmailServerUN();
			String pwd = LocalTest.getEmailCredentials().getEmailServerPWD();
            t.connect("smtp.mail.yahoo.com", uN, pwd);
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
			
			BodyPart bp = new MimeBodyPart();
			bp.setText("<p id='testReply'> " + testMessage + " </p> <br>");
			System.out.println(bp.getContent());
			
			BodyPart bp2 = new MimeBodyPart();
			bp2.setText(IMAPHelper.getBody());
			System.out.println(bp2.getContent());
			
			Multipart mp = new MimeMultipart();
			mp.addBodyPart(bp);
			mp.addBodyPart(bp2);
			
			rMessage.setFrom(new InternetAddress(IMAPHelper.getTo()));
            Address[] add = { new InternetAddress(IMAPHelper.getFrom()) };
            rMessage.setReplyTo(add);
            rMessage.setRecipient(RecipientType.TO, new InternetAddress(IMAPHelper.getFrom()));
            rMessage.saveChanges();
            rMessage.setContent(mp, "text/html; charset=utf-8");
            rMessage.saveChanges();
            
            replyMessage.set(rMessage);
            System.out.println("Reply message composed... ");
		} catch (Exception e) {
			throw e;
		}
	}

}
