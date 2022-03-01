package logic.engineeringclasses.bean;

public class UserBean {

	private String usrID;
    private String usrName;
    private String usrSurname;
	private String usrEmail;
	private String usrPwd;
	private String usrRole;
	private String usrOkPwd;
	private String usrAddr;

	public String getUsrID() {
        return usrID;
    }

    public void setUsrID(String userID) {
        this.usrID = userID;
    }
    
    public String getUsrName() {
        return usrName;
    }

    public void setUsrName(String usrName) {
        this.usrName = usrName;
    }

    public String getUsrSurname() {
        return usrSurname;
    }

    public void setUsrSurname(String usrSurname) {
        this.usrSurname = usrSurname;
    }
    
    public String getUsrEmail() {
        return usrEmail;
    }

    public void setUsrEmail(String usrEmail)	{
        this.usrEmail = usrEmail;
    }
    
    public String getUsrPwd() {
        return usrPwd;
    }

    public void setUsrPwd(String usrPwd) {
        this.usrPwd = usrPwd;
    }
    
    public String getOkPassword() {
        return usrOkPwd;
    }

    public void setOkPassword(String okPwd) {
        this.usrOkPwd = okPwd;
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
