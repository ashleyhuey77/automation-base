package data.loki.obj;

public class Person {

	private final Loki loki;
	private static final String NAME = "person.name";
	
	protected Person(Loki loki) {
		this.loki = loki;
	}
	
	public String firstName() {
		String[] split = loki.mockValuesService().fetchString(NAME).split(" ");
		return split[0];
	}
	
	public String lastName() {
		String[] split = loki.mockValuesService().fetchString(NAME).split(" ");
		return split[1];
	}
	
	public String name() {
		return loki.mockValuesService().fetchString(NAME);
	}
	
	public String phoneNumber() {
		return loki.numerify(loki.mockValuesService().resolve("person.phone_number", this, loki));
	}
	
	public String role() {
		return loki.mockValuesService().resolve("person.role", this, loki);
	}
	
	public String permission() {
		return loki.mockValuesService().resolve("person.permission", this, loki);
	}
	
	public String groupName() {
		return loki.mockValuesService().resolve("person.group_name", this, loki);
	}
	
}
