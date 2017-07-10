package commonClasses.sharedUtils;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.AlgorithmParameters;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class Security {

	    private static SecretKeySpec createSecretKey() throws NoSuchAlgorithmException, InvalidKeySpecException {
	    	byte[] salt = new String("12345678").getBytes();
	        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
	        PBEKeySpec keySpec = new PBEKeySpec("1Hbfh667adfDEJ78".toCharArray(), salt, 40000, 128);
	        SecretKey keyTmp = keyFactory.generateSecret(keySpec);
	        return new SecretKeySpec(keyTmp.getEncoded(), "AES");
	    }

	    public static String encrypt(String property) throws GeneralSecurityException, UnsupportedEncodingException {
	        Cipher pbeCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
	        pbeCipher.init(Cipher.ENCRYPT_MODE, createSecretKey());
	        AlgorithmParameters parameters = pbeCipher.getParameters();
	        IvParameterSpec ivParameterSpec = parameters.getParameterSpec(IvParameterSpec.class);
	        byte[] cryptoText = pbeCipher.doFinal(property.getBytes("UTF-8"));
	        byte[] iv = ivParameterSpec.getIV();
	        return base64Encode(iv) + ":" + base64Encode(cryptoText);
	    }

	    private static String base64Encode(byte[] bytes) {
	        return Base64.getEncoder().encodeToString(bytes);
	    }

	    public static String decrypt(String string) throws GeneralSecurityException, IOException {
	        String iv = string.split(":")[0];
	        String property = string.split(":")[1];
	        Cipher pbeCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
	        pbeCipher.init(Cipher.DECRYPT_MODE, createSecretKey(), new IvParameterSpec(base64Decode(iv)));
	        return new String(pbeCipher.doFinal(base64Decode(property)), "UTF-8");
	    }

	    private static byte[] base64Decode(String property) throws IOException {
	        return Base64.getDecoder().decode(property);
	    }
	}

/*	private static final String ALGORITHM = "AES";
    private static final String KEY = "1Hbfh667adfDEJ78";
    
    public static String encrypt(String value) throws Exception
    {
    	Key key = generateKey();
        Cipher cipher = Cipher.getInstance(Security.ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte [] encryptedByteValue = cipher.doFinal(value.getBytes("utf-8"));
        String encryptedValue64 = Base64.encodeBase64(encryptedByteValue).toString();
        return encryptedValue64;
    }
    
    public static String decrypt(String value) throws Exception
    {
    	Key key = generateKey();
        Cipher cipher = Cipher.getInstance(Security.ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte [] decryptedValue64 = Base64.decodeBase64(value);
        byte [] decryptedByteValue = cipher.doFinal(decryptedValue64);
        String decryptedValue = new String(decryptedByteValue,"utf-8");
        return decryptedValue;
    }
    
    private static Key generateKey() throws Exception 
    {
        Key key = new SecretKeySpec(Security.KEY.getBytes(), Security.ALGORITHM);
        return key;
    }*/
