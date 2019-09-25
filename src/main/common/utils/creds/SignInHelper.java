package common.utils.creds;

import com.app.creds.CredentialsType;

public class SignInHelper {
	
	public static User getUser(CredentialsType type) {
		UserFactory factory = UserFactory.factory(builder -> {
			builder.add(CredentialsType.BASE, NewsApps::new);
			builder.add(CredentialsType.MIRA, Mira::new);
		});
		return factory.create(type);
	}

}
