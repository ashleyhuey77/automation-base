package common.utils.builders;

import java.io.IOException;
import java.security.GeneralSecurityException;
import com.app.SecurityHelper;

public class Credentials {

    private static String miraEncryptedUserName;
    private static String miraEncryptedPassword;
    private static String newstronEncryptedUserName;
    private static String newstronEncryptedPassword;
    private static String emailServerUN;
    private static String emailServerPWD;

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
    
    public void setNewstronUN(String value) {
       newstronEncryptedUserName = value;
    }

    public void setNewstronPWord(String value) {
        newstronEncryptedPassword = value;
    }
    
    public String getEmailServerUN() {
    	return emailServerUN;
    }
    
    public String getEmailServerPWD() {
    	return emailServerPWD;
    }

    public Credentials(String mUser, String mPword, String nUser, String nPword) throws GeneralSecurityException, IOException {
        if (mUser.length() > 3) {
            String decryptedMUN = SecurityHelper.decrypt(mUser);
            String decryptedMPword = SecurityHelper.decrypt(mPword);
            Credentials.miraEncryptedUserName = decryptedMUN;
            Credentials.miraEncryptedPassword = decryptedMPword;
        }
        if (nUser.length() > 3) {
            Credentials.newstronEncryptedUserName = nUser;
            Credentials.newstronEncryptedPassword = nPword;
        }
    }
    
    public Credentials(String user, String pwd) {
    	Credentials.emailServerUN = user;
    	Credentials.emailServerPWD = pwd;
    }

}
