package commonClasses.sharedUtils.builders;

import commonClasses.sharedUtils.helpers.SecurityHelper;

public class Credentials {
	
	private static String miraEncryptedUserName;
	private static String miraEncryptedPassword;
	private static String newstronEncryptedUserName;
	private static String newstronEncryptedPassword;
	
	public String getMiraUN() {
		return miraEncryptedUserName;
	}
	
	public String getMiraPWord() {
		return miraEncryptedPassword;
	}
	
	public String getNewstronUN() {
		return newstronEncryptedUserName;
	}
	
	public String getNewstronPWord() {
		return newstronEncryptedPassword;
	}
	
	public Credentials(String mUser, String mPword, String nUser, String nPword) throws Exception {
		if (mUser.length() > 3)
		{
			String decryptedMUN = SecurityHelper.decrypt(mUser);
			String decryptedMPword = SecurityHelper.decrypt(mPword);
			Credentials.miraEncryptedUserName = decryptedMUN;
			Credentials.miraEncryptedPassword = decryptedMPword;
		}
		if (nUser.length() > 3)
		{
			String decryptedNUN = SecurityHelper.decrypt(nUser);
			String decryptedNPword = SecurityHelper.decrypt(nPword);
			Credentials.newstronEncryptedUserName = decryptedNUN;
			Credentials.newstronEncryptedPassword = decryptedNPword;
		}
	}
	
}
