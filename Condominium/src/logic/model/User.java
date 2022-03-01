package logic.model;

public class User {

	private String usrID;
    private String usrName;
	private String usrEmail;
	private String usrPwd;
    private String usrRole;
	private String usrAddr;
	
	public User(String usrID, String usrName, String usrEmail, String usrPwd, String usrAddr){
		this.setUsrID(usrID);
		this.setUsrName(usrName);
		this.setUsrEmail(usrEmail);
		this.setUsrPwd(usrPwd);
		this.setUsrAddr(usrAddr);
	}

    public User(String usrID, String usrName, String usrEmail, String usrPwd,String usrRole, String usrAddr){
        this.setUsrID(usrID);
        this.setUsrName(usrName);
        this.setUsrEmail(usrEmail);
        this.setUsrPwd(usrPwd);
        this.setUsrRole(usrRole);
        this.setUsrAddr(usrAddr);
    }


	public String getUsrID() {
        return usrID;
    }

    public void setUsrID(String usrID) {
        this.usrID = usrID;
    }
    
    public String getUsrName() {
        return usrName;
    }

    public void setUsrName(String usrName){
        this.usrName = usrName;
    }

    public String getUsrEmail() {
        return usrEmail;
    }

    public void setUsrEmail(String usrEmail){
        this.usrEmail = usrEmail;
    }
    
    public String getUsrPwd() {
        return usrPwd;
    }

    public void setUsrPwd(String usrPwd) {
        this.usrPwd = usrPwd;
    }

    public String getUsrRole() {
        return usrRole;
    }

    public void setUsrRole(String usrRole) {
        this.usrRole = usrRole;
    }
    
    public String getUsrAddr() {
        return usrAddr;
    }

    public void setUsrAddr(String usrAddr) {
        this.usrAddr = usrAddr;
    }
}
