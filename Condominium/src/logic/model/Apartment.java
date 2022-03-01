package logic.model;


public class Apartment {

	private String number;
	private String address;
	private String owner;
	private String resident;
	private String fee;

	public Apartment(String number,String address,String owner,String resident,String fee) {
		this.number = number;
		this.address = address;
		this.owner = owner;
		this.resident = resident;
		this.fee = fee;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getNumber() {
		return number;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddress() {
		return address;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getOwner() {
		return owner;
	}

	public void setResident(String resident) {
		this.resident = resident;
	}

	public String getResident() {
		return resident;
	}

	public void setFee(String fee) {
		this.fee = fee;
	}

	public String getFee() {
		return fee;
	}
}
