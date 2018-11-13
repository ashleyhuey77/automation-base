package data.loki.service;

public class TestEnvironment {
	
	private Environments environments;
	
	public TestEnvironment(Environments env) {
		this.environments = env;
	}
	
	public Environments getEnvironment() {
		return this.environments;
	}

}
