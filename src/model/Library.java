package model;

public class Library {

	private long id;
	private String name;
	private long addressId;
	
	public Library() {};
	
	public Library(long id, String name, long addressId) {
		super();
		this.id = id;
		this.name = name;
		this.addressId = addressId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getAddressId() {
		return addressId;
	}

	public void setAddressId(long addressId) {
		this.addressId = addressId;
	}
	
	
	
}
