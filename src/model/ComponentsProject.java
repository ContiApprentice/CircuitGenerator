package model;

public class ComponentsProject {

	private String nameAndVersion;
	private String version;
	private String type;
	private int listPosition;

	//constructors
	public ComponentsProject() {
		super();
	}

	public ComponentsProject(String nameAndVersion, String version, String type) {
		super();
		this.nameAndVersion = nameAndVersion;
		this.version = version;
		this.type = type;
	}

	//getters/setters
	public String getnameAndVersion() {
		return nameAndVersion;
	}

	public void setnameAndVersion(String nameAndVersion) {
		this.nameAndVersion = nameAndVersion;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getListPosition() {
		return listPosition;
	}

	public void setListPosition(int listPosition) {
		this.listPosition = listPosition;
	}





}
