package logic.model;

public class Request {
	
	private String id;
	private String usr;
	private String reason;
	private int pos;
			
	public Request(String id,String usr,String reason,int pos){
		this.setId(id);
		this.setUser(usr);
		this.setReason(reason);
		this.setPos(pos);
	}
	
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setUser(String usr) {
		this.usr = usr;
	}
	
	public String getUser() {
		return this.usr;
	}

	public String getReason() {
		return this.reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public int getPos() {
		return pos;
	}

	public void setPos(int pos) {
		this.pos = pos;
	}
}
