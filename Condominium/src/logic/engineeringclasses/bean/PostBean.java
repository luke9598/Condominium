package logic.engineeringclasses.bean;

import logic.model.Role;

import java.io.InputStream;

public class PostBean {

	private String id;
	private String usr;
	private Role role;
	private InputStream img;
	private String text;
	
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

	public void setRole(Role role){this.role = role;}

	public Role getRole(){return this.role;}
	
	public void setImage(InputStream img) {
		this.img = img;
	}
	
	public InputStream getImage() {
		return this.img;
	}	

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
