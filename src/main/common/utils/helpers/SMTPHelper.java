package common.utils.helpers;

import java.util.Properties;
import java.util.logging.Level;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.internet.MimeMessage.RecipientType;
import common.utils.enums.Protocol;
import common.utils.managers.LocalTest;
import log.Log;

public class SMTPHelper {

	private SMTPHelper() {

	}

	private static ThreadLocal<Session> session = new ThreadLocal<>();
	private static ThreadLocal<Message> replyMessage = new ThreadLocal<>();

	public static void setUpSMTPServer() {
		EmailReceiver e2 = new EmailReceiver(Protocol.smtp, "smtp.mail.yahoo.com", "587", false);
		Properties properties2 = e2.property.get();
		Session sess = Session.getInstance(properties2, new javax.mail.Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(LocalTest.getEmailCredentials().getEmailServerUN(),
						LocalTest.getEmailCredentials().getEmailServerPWD());
			}
		});
		Log.get().log(Level.INFO, "SMTP session started... ");
		session.set(sess);
	}

	public static void sendEmailMessage() {
		try {
			Transport t = session.get().getTransport("smtps");
			String uN = LocalTest.getEmailCredentials().getEmailServerUN();
			String pwd = LocalTest.getEmailCredentials().getEmailServerPWD();
			t.connect("smtp.mail.yahoo.com", uN, pwd);
			t.sendMessage(replyMessage.get(), replyMessage.get().getRecipients(RecipientType.TO));
			t.close();
			Log.get().log(Level.INFO, "Replied to message successfully... ");
		} catch (Exception e) {
			Log.get().log(Level.SEVERE, e.getMessage(), e);
		}
	}

	public static void composeReplyEmailMessage(String testMessage) {
		try {
			Message rMessage = new MimeMessage(session.get());
			rMessage = IMAPHelper.getMessage().reply(false);

			BodyPart bp = new MimeBodyPart();
			bp.setText("<p id='testReply'> " + testMessage + " </p> <br>");

			BodyPart bp2 = new MimeBodyPart();
			bp2.setText(IMAPHelper.getBody());

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
			Log.get().log(Level.INFO, "Reply message composed... ");
		} catch (Exception e) {
			Log.get().log(Level.SEVERE, e.getMessage(), e);
		}
	}

}
