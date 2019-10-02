package com.warnermedia.config.settings;

import com.app.Decrypter;
import com.app.creds.CredentialsType;
import com.app.data.DataMapper;

public class SignInHelper {

	public static byte[] getName(CredentialsType type) throws Exception {
		Decrypter.decrypt(type);
		return DataMapper.getCredentials().name.getBytes();
	}

	public static byte[] getPassword(CredentialsType type) throws Exception {
		Decrypter.decrypt(type);
		return DataMapper.getCredentials().password.getBytes();
	}

}
