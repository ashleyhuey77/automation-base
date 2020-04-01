package com.warnermedia.config.email;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class SMTPAuthenticator extends Authenticator {

	public PasswordAuthentication
		getPasswordAuthentication(String userName, String password) {
			return new PasswordAuthentication(userName, password);
	}
}