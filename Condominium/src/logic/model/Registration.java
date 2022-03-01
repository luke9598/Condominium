package logic.model;

public class Registration {

    private String regID;
    private String regName;
	private String regEmail;
	private String regPwd;
	private String regRole;
	private String regAddr;
    private String regApt;
	
	public Registration(String regID, String regName, String regEmail, String regPwd, String regRole, String regAddr, String regApt){
		this.setRegID(regID);
        this.setRegName(regName);
		this.setRegEmail(regEmail);
		this.setRegPwd(regPwd);
		this.setRegRole(regRole);
		this.setRegAddr(regAddr);
        this.setRegApt(regApt);
	}

    public String getRegID(){return regID;}

    public void setRegID(String regID){this.regID = regID;}

    public String getRegName() {
        return regName;
    }

    public void setRegName(String regName){
        this.regName = regName;
    }

    public String getRegEmail() {
        return regEmail;
    }

    public void setRegEmail(String regEmail){
        this.regEmail = regEmail;
    }
    
    public String getRegPwd() {
        return regPwd;
    }

    public void setRegPwd(String regPwd) {
        this.regPwd = regPwd;
    }
    
    public String getRegRole() {
        return regRole;
    }

    public void setRegRole(String regRole) {
        this.regRole = regRole;
    }
    
    public String getRegAddr() {
        return regAddr;
    }

    public void setRegAddr(String regAddr) {
        this.regAddr = regAddr;
    }

    private void setRegApt(String regApt) {
        this.regApt = regApt;
    }

    public String getRegApt(){
        return regApt;
    }
}
