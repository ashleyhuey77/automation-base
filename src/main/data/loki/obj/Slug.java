package data.loki.obj;

public class Slug {

	private final Loki loki;
	
	protected Slug(Loki loki) {
		this.loki = loki;
	}
	
	public String source() {
		return loki.mockValuesService().fetchString("slug.source");
	}
	
	public String type() {
		return loki.mockValuesService().fetchString("slug.type");
	}
	
	public String forProgram() {
		return loki.mockValuesService().fetchString("slug.for_program");
	}
	
	public String nativeAspectRatio() {
		return loki.mockValuesService().fetchString("slug.native_aspect_ratio");
	}
	
	public String reporter() {
		return loki.mockValuesService().fetchString("slug.reporter");
	}
	
	public String restrictions() {
		return loki.mockValuesService().fetchString("slug.restrictions");
	}
	
	public String alerts() {
		return loki.mockValuesService().fetchString("slug.alerts");
	}
	
	public String shortText() {
		return loki.mockValuesService().fetchString("slug.short_text");
	}
	
	public String longText() {
		return loki.mockValuesService().resolve("slug.long_text", this, loki);
	}
	
	public String division() {
		return loki.mockValuesService().fetchString("slug.division");
	}
	
	public String country() {
		return loki.mockValuesService().fetchString("slug.country");
	}
	
	public String countryAbbr() {
		return loki.mockValuesService().fetchString("slug.country_abbr");
	}
	
	public String state() {
		return loki.mockValuesService().fetchString("slug.state");
	}
	
	public String stateAbbr() {
		return loki.mockValuesService().fetchString("slug.state_abbr");
	}
	
	public String city() {
		return loki.mockValuesService().fetchString("slug.city");
	}
	
	public String county() {
		return loki.mockValuesService().fetchString("slug.county");
	}
}
