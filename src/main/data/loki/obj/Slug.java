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
	
	public String summary() {
		return loki.mockValuesService().fetchString("slug.summary");
	}
	
	public String longSummary() {
		return loki.mockValuesService().resolve("slug.long_summary", this, loki);
	}
	
	public String technicalNotes() {
		return loki.mockValuesService().fetchString("slug.tech_notes");
	}
	
	public String division() {
		return loki.mockValuesService().fetchString("slug.division");
	}
	
	public String country() {
		return loki.mockValuesService().fetchString("slug.country");
	}
	
	public String state() {
		return loki.mockValuesService().fetchString("slug.state");
	}
	
	public String city() {
		return loki.mockValuesService().fetchString("slug.city");
	}
	
	public String county() {
		return loki.mockValuesService().fetchString("slug.county");
	}
	
	public String elements() {
		return loki.mockValuesService().fetchString("slug.elements");
	}
}
