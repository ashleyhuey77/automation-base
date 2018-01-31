package common.utils.builders;

public class TestSettings {
	
	private Environment environment;
	private Credentials credentials;
	
	public TestSettings(Environment environment, Credentials credentials) {
		this.environment = environment;
		this.credentials = credentials;
	}
	
	public Environment getEnvironment() {
		return environment;
	}
	
	public Credentials getCredentials() {
		return credentials;
	}

}
