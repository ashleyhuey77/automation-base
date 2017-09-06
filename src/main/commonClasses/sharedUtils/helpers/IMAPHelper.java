package commonClasses.sharedUtils.helpers;

import java.text.*;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import commonClasses.sharedUtils.*;
import commonClasses.sharedUtils.enums.Protocol;
import commonClasses.sharedUtils.managers.LocalDriver;
import commonClasses.sharedUtils.managers.LocalTest;

public class IMAPHelper {
	
	private static ThreadLocal<Session> session = new ThreadLocal<Session>();
	private static ThreadLocal<Store> store = new ThreadLocal<Store>();
	private static ThreadLocal<Folder> folder = new ThreadLocal<Folder>();
	private static ThreadLocal<Message> message = new ThreadLocal<Message>();
	private static ThreadLocal<String> to = new ThreadLocal<String>();
	private static ThreadLocal<String> from = new ThreadLocal<String>();
	private static ThreadLocal<String> body = new ThreadLocal<String>();

	public static void setUpIMAPServer() throws Exception {
		try {
			EmailReceiver e = new EmailReceiver(Protocol.imap, "outlook.office365.com", "993", true);
		      Properties properties = e.property.get();

		      Session sess = Session.getInstance(properties,
		    		  new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(LocalTest.getEmailCredentials().getEmailServerUN(), 
								LocalTest.getEmailCredentials().getEmailServerPWD());
					}
				  });
		      
		      //sess.setDebug(true);
		      System.out.println("IMAP session started... ");
		      session.set(sess);
		} catch (Exception e) {
			throw e;
		}
	}
	
	public static void setUpTheStoreAndOpenTheFolder(String folderPath) throws Exception {
		try {
			 Store st = session.get().getStore("imap");
			 store.set(st);
	         store.get().connect("outlook.office365.com", LocalTest.getCredentials().getEmailServerUN(), 
						LocalTest.getCredentials().getEmailServerUN());
	         System.out.println("Store opened... ");
	         Folder f = store.get().getFolder(folderPath);
	         folder.set(f);
	         if (!folder.get().exists()) {
	            System.out.println(folderPath + " was not found");
	               System.exit(0);
	         }
	         folder.get().open(Folder.READ_ONLY);
	         System.out.println("Folder " + folderPath + " opened... ");
		} catch (Exception e) {
			throw e;
		}
	}
	
	public static void getTheSpecifiedEmailAndRetrieveContents(String expectedDate, String expectedSubject, String expectedBody) throws Exception {
		try {
			Message[] messages = folder.get().getMessages();
			waitForMessageToBeAvailable(messages, expectedBody, expectedDate, expectedSubject);
		} catch (Exception e) {
			throw e;
		}
	}
	
	private static void waitForMessageToBeAvailable(Message[] messages, String expectedBody, String expectedDate, String expectedSubject) throws Exception {
		try {
			WebDriverWait wait = new WebDriverWait(LocalDriver.getDriver(), 200);

            wait.until(new ExpectedCondition < Boolean > () {
                public Boolean apply(WebDriver driver) {
                	Boolean result = false;
					for (int i=0; i < messages.length; i++) {
						Message m = messages[i];
						Date d = null;
						try {
							d = m.getSentDate();
						} catch (MessagingException e2) {
						}
						DateFormat formatDate = new SimpleDateFormat("MM/dd/YYYY");
						String actualDate = formatDate.format(d).toString();
						String actualSubject = null;
						try {
							actualSubject = m.getSubject();
						} catch (MessagingException e1) {
						}
						String actualBody = null;
						try {
							actualBody = getText(m);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if (TestUtils.isNullOrBlank(expectedBody)) {
							if (actualDate.equals(expectedDate)
									&& actualSubject.toLowerCase().trim()
										.contains(expectedSubject.toLowerCase().trim())) {
								System.out.println("Existing email message found... ");
								try {
									setRequiredValues(m, actualBody);
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								result = true;
								break;
							}
						} else {
							if (actualDate.equals(expectedDate)
									&& actualSubject.toLowerCase().trim().contains(expectedSubject
											.toLowerCase().trim())
									&& actualBody.toLowerCase().trim().contains(expectedBody
											.toLowerCase().trim())) {
								System.out.println("Existing email message found... ");
								try {
									setRequiredValues(m, actualBody);
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								result = true;
								break;
							}
						}
					}
					return result;
                }
           });	
		} catch (Exception e) {
			throw new Exception("Unable to find message in the test user's email with the specified criteria. "
					+ "Make sure the required email is present in the specified folder.");
		}
	}
	
	public static void closeStoreAndFolder() throws Exception {
		try {
			if (store.get().isConnected()) {
				store.get().close();
			}
			if (folder.get().isOpen()) {
				folder.get().close();
			}
		} catch (Exception e) {
			throw e;
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

	private static String getText(Message m) throws Exception {
		if (m.isMimeType("text/plain")) {
	        return m.getContent().toString();
	    } else if (m.isMimeType("text/html")) {
	    	String result = null;
	    	String html = (String) m.getContent();
            result = html;
            return result;
	    } else if (m.isMimeType("multipart/*")) {
	        String result = "";
	        MimeMultipart mimeMultipart = (MimeMultipart)m.getContent();
	        int count = mimeMultipart.getCount();
	        for (int i = 0; i < count; i ++) {
	            BodyPart bodyPart = mimeMultipart.getBodyPart(i);
	            if (bodyPart.isMimeType("text/plain")) {
	                result = result + "\n" + bodyPart.getContent();
	                //break;
	            } else if (bodyPart.isMimeType("text/html")) {
	                String html = (String) bodyPart.getContent();
	                result = html;
	            }
	        }
	        return result;
	    }
	    return "";
	}
	
	private static void setRequiredValues(Message mess, String actualBody) throws Exception {
		try {
			message.set(mess);
			to.set(InternetAddress.toString(mess
					.getRecipients(Message.RecipientType.TO)));
			from.set(InternetAddress.toString(mess.getFrom()));
			body.set(actualBody);
		} catch (Exception e) {
			throw e;
		}
	}
	
}