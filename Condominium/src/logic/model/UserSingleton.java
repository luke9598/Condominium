package logic.model;

public class UserSingleton {

	private Administrator administrator = null;
	private Resident resident = null;
	private Owner owner = null;	
	private String usrId;
	private Role role;

	private Fee fee;
	private Fee pastfee;
	private String address;
	private Request req;

	private static UserSingleton instance = null;

	private UserSingleton() {
	}

	public static UserSingleton getInstance() {
		if (instance == null)
			instance = new UserSingleton();
		return instance;
	}
	
	public void setUserID(String usrId) {
		this.usrId = usrId;
	}
	
	public String getUserID() {
		return this.usrId;
	}
	
	public Resident getResident() {
		return resident;
	}

	public void setResident(Resident resident) {
		this.resident = resident;
	}

	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}

	public void setAdministrator(Administrator user) {
		this.administrator = user;
	}

	public Administrator getAdministrator() {
		return this.administrator;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddress(){return this.address;}

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Request getReq() {
		return this.req;
	}

	public void setReq(Request req) {
		this.req = req;
	}
	
	
	
	public void clearState() {
		this.setUserID(null);
		this.setAddress(null);
		this.setRole(null);
		this.setAdministrator(null);
		this.setResident(null);
		this.setOwner(null);
		this.setReq(null);
	}

	public Fee getFee() {
		return fee;
	}

	public void setFee(Fee fee) {
		this.fee = fee;
	}

	public Fee getPastfee() {return pastfee;}

	public void setPastfee(Fee pastfee) {this.pastfee = pastfee;}
}
