package data.loki.obj;

public class WorkOrder {
	
	private final Loki loki;
	
	protected WorkOrder(Loki loki) {
		this.loki = loki;
	}
	
	public String featureFilm() {
		return loki.mockValuesService().fetchString("work_order.feature_film");
	}
	
	public String series() {
		return loki.mockValuesService().fetchString("work_order.series");
	}
	
	public String distributor() {
		return loki.mockValuesService().fetchString("work_order.distributor");
	}
	
	public String network() {
		return loki.mockValuesService().fetchString("work_order.network");
	}
	
	public String department() {
		return loki.mockValuesService().fetchString("work_order.department");
	}
	
	public String type() {
		return loki.mockValuesService().fetchString("work_order.type");
	}
	
	public String videoType() {
		return loki.mockValuesService().fetchString("work_order.video_type");
	}
	
	public String aspectRatio() {
		return loki.mockValuesService().fetchString("work_order.aspect_ratio");
	}
	
	public String trackProfile() {
		return loki.mockValuesService().fetchString("work_order.track_profile");
	}
	
	public String trackLanguage() {
		return loki.mockValuesService().fetchString("work_order.track_language");
	}
	
	public String customTrackTypes() {
		return loki.mockValuesService().fetchString("work_order.custom");
	}

}
