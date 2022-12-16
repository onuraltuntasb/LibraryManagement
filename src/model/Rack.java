package model;

public class Rack {
	private long id;
	private int number;
	private String locationIdentifer;
	
	public Rack(long id, int number, String locationIdentifer) {
		super();
		this.id = id;
		this.number = number;
		this.locationIdentifer = locationIdentifer;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getLocationIdentifer() {
		return locationIdentifer;
	}

	public void setLocationIdentifer(String locationIdentifer) {
		this.locationIdentifer = locationIdentifer;
	}

}
