package com.warnermedia.config.email;

import lombok.extern.slf4j.Slf4j;

import java.util.Properties;
import java.util.logging.Level;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.internet.MimeMessage.RecipientType;

@Slf4j
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
				return new PasswordAuthentication("",
						"");
			}
		});
		log.info("SMTP session started... ");
		session.set(sess);
	}

	public static void sendEmailMessage() {
		try {
			Transport t = session.get().getTransport("smtps");
			String uN = "";
			String pwd = "";
			t.connect("smtp.mail.yahoo.com", uN, pwd);
			t.sendMessage(replyMessage.get(), replyMessage.get().getRecipients(RecipientType.TO));
			t.close();
			log.info("Replied to message successfully... ");
		} catch (Exception e) {
			log.info(e.getMessage(), e);
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
			log.info("Reply message composed... ");
		} catch (Exception e) {
			log.info(e.getMessage(), e);
		}
	}

}
