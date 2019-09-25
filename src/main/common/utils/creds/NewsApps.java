package common.utils.creds;

import com.app.Decrypter;
import com.app.creds.CredentialsType;
import com.app.data.DataMapper;

public class NewsApps implements User {

	@Override
	public byte[] getName() throws Exception {
		Decrypter.decrypt(CredentialsType.BASE);
		return DataMapper.getCredentials().name.getBytes();
	}

	@Override
	public byte[] getPassword() throws Exception {
		Decrypter.decrypt(CredentialsType.BASE);
		return DataMapper.getCredentials().password.getBytes();
	}

}
