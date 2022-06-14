package logic.engineeringclasses.bean;

import logic.model.Role;

import java.io.InputStream;

public class PostBean {

	private String postId;
	private String postUsr;
	private Role postRole;
	private InputStream postImage;
	private String postText;
	
	public String getPostId() {
		return this.postId;
	}

	public void setPostId(String postId) {
		this.postId = postId;
	}

	public void setPostUser(String usr) {
		this.postUsr = usr;
	}
	
	public String getPostUser() {
		return this.postUsr;
	}

	public void setPostRole(Role postRole){this.postRole = postRole;}

	public Role getPostRole(){return this.postRole;}
	
	public void setPostImage(InputStream img) {
		this.postImage = img;
	}
	
	public InputStream getPostImage() {
		return this.postImage;
	}	

	public String getPostText() {
		return postText;
	}

	public void setPostText(String text) {
		this.postText = text;
	}
}
