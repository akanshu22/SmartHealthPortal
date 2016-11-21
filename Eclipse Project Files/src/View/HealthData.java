package View;

public class HealthData {
	protected String date;
	protected String property;
	protected String value;
	
	public HealthData(String date, String property, String value){
		this.date = date;
		this.property = property;
		this.value = value;
	}

	public String getDate() {
		return date;
	}

	public String getProperty() {
		return property;
	}

	public String getValue() {
		return value;
	}
}
