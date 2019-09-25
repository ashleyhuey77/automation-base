package common.utils.helpers;

import java.text.*;
import java.util.*;
import java.util.logging.Level;
import javax.mail.*;
import javax.mail.Authenticator;
import javax.mail.internet.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.*;
import common.utils.*;
import common.utils.enums.Protocol;
import common.utils.managers.*;
import log.Log;
import log.TestException;

public class IMAPHelper {

	private IMAPHelper() {

	}

	private static ThreadLocal<Session> session = new ThreadLocal<>();
	private static ThreadLocal<Store> store = new ThreadLocal<>();
	private static ThreadLocal<Folder> folder = new ThreadLocal<>();
	private static ThreadLocal<Message> message = new ThreadLocal<>();
	private static ThreadLocal<String> to = new ThreadLocal<>();
	private static ThreadLocal<String> from = new ThreadLocal<>();
	private static ThreadLocal<String> body = new ThreadLocal<>();

	public static void setUpIMAPServer() {
		EmailReceiver e = new EmailReceiver(Protocol.imap, "imap.mail.yahoo.com", "993", true);
		Properties properties = e.property.get();
		String un = "";
		String pwd = "";
		Session sess = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(un, pwd);
			}
		});
		Log.get().log(Level.INFO, "IMAP session started... ");
		session.set(sess);
	}

	public static void setUpTheStoreAndOpenTheFolder(String folderPath) {
		try {
			Store st = session.get().getStore("imap");
			store.set(st);
			String un = "";
			String pwd = "";
			store.get().connect("imap.mail.yahoo.com", un, pwd);
			Log.get().log(Level.INFO, "Store opened... ");
			Folder f = store.get().getFolder(folderPath);
			folder.set(f);
			if (!folder.get().exists()) {
				Log.get().log(Level.SEVERE, "{0} was not found.", folderPath);
				System.exit(0);
			}
			folder.get().open(Folder.READ_ONLY);
			Log.get().log(Level.INFO, "Folder {0} opened... ", folderPath);
		} catch (Exception e) {
			Log.get().log(Level.SEVERE, e.getMessage(), e);
		}
	}

	public static void getTheSpecifiedEmailAndRetrieveContents(String expectedDate, String expectedSubject,
			String expectedBody) throws TestException {
		waitForMessageToBeAvailable(expectedBody, expectedDate, expectedSubject);
	}

	private static void waitForMessageToBeAvailable(String expectedBody, String expectedDate, String expectedSubject)
			throws TestException {
		try {
			WebDriverWait wait = new WebDriverWait(LocalDriver.getDriver(), 200);

			wait.until((WebDriver driver) -> {
				Boolean result = false;
				try {
					Message[] messages = folder.get().getMessages();
					result = getMessages(messages, expectedBody, expectedDate, expectedSubject);

				} catch (Exception e) {
					result = false;
				}
				return result;
			});
		} catch (Exception e) {
			throw new TestException("Unable to find message in the test user's email with the specified criteria. "
					+ "Make sure the required email is present in the specified folder.");
		}
	}

	private static Boolean getMessages(Message[] messages, String expectedBody, String expectedDate,
			String expectedSubject) throws TestException {
		Boolean result = false;
		try {
			for (int i = 0; i < messages.length; i++) {

				Message m = messages[i];
				Date d = m.getSentDate();
				DateFormat formatDate = new SimpleDateFormat("MM/dd/yyyy");
				String actualDate = formatDate.format(d);
				String actualSubject = m.getSubject();
				String actualBody = getText(m);
				if (TestUtils.isNullOrBlank(expectedBody)) {
					if (actualDate.equals(expectedDate)
							&& actualSubject.toLowerCase().trim().contains(expectedSubject.toLowerCase().trim())) {
						Log.get().log(Level.INFO, "Existing email message found... ");
						setRequiredValues(m, actualBody);
						result = true;
						break;
					}
				} else {
					if (actualDate.equals(expectedDate)
							&& actualSubject.toLowerCase().trim().contains(expectedSubject.toLowerCase().trim())
							&& actualBody.toLowerCase().trim().contains(expectedBody.toLowerCase().trim())) {
						Log.get().log(Level.INFO, "Existing email message found... ");
						setRequiredValues(m, actualBody);
						result = true;
						break;
					}
				}

			}
		} catch (Exception e) {
			Log.get().log(Level.SEVERE, e.getMessage(), e);
		}
		return result;
	}

	public static void closeStoreAndFolder() {
		try {
			if (store.get().isConnected()) {
				store.get().close();
			}
			if (folder.get().isOpen()) {
				folder.get().close(false);
			}
		} catch (Exception e) {
			Log.get().log(Level.SEVERE, e.getMessage(), e);
		}
	}

	public static Message getMessage() {
		return message.get();
	}

	public static String getTo() {
		return to.get();
	}

	public static String getFrom() {
		return from.get();
	}

	public static String getBody() {
		return body.get();
	}

	private static String getText(Message m) {
		String result = null;
		try {
			if (m.isMimeType("text/plain")) {
				return m.getContent().toString();
			} else if (m.isMimeType("text/html")) {
				String html = (String) m.getContent();
				result = html;
				return result;
			} else if (m.isMimeType("multipart/*")) {
				result = "";
				MimeMultipart mimeMultipart = (MimeMultipart) m.getContent();
				int count = mimeMultipart.getCount();
				for (int i = 0; i < count; i++) {
					BodyPart bodyPart = mimeMultipart.getBodyPart(i);
					if (bodyPart.isMimeType("text/plain")) {
						result = result + "\n" + bodyPart.getContent();
					} else if (bodyPart.isMimeType("text/html")) {
						String html = (String) bodyPart.getContent();
						result = html;
					} else if (bodyPart.isMimeType("multipart/*")) {
						MimeMultipart mimeMP2 = (MimeMultipart) bodyPart.getContent();
						int count2 = mimeMP2.getCount();
						for (int b = 0; i < count2; i++) {
							BodyPart bP2 = mimeMP2.getBodyPart(b);
							if (bP2.isMimeType("text/plain")) {
								result = result + "\n" + bP2.getContent();
							} else if (bP2.isMimeType("text/html")) {
								String html = (String) bP2.getContent();
								result = html;
							}
						}
					}
				}
				return result;
			}
			return "";
		} catch (Exception e) {
			Log.get().log(Level.SEVERE, e.getMessage(), e);
		}
		return result;
	}

	private static void setRequiredValues(Message mess, String actualBody) {
		try {
			message.set(mess);
			to.set(InternetAddress.toString(mess.getRecipients(Message.RecipientType.TO)));
			from.set(InternetAddress.toString(mess.getFrom()));
			body.set(actualBody);
		} catch (Exception e) {
			Log.get().log(Level.SEVERE, e.getMessage(), e);
		}
	}

}
