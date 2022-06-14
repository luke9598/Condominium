package logic.engineeringclasses.bean;

public class MeetRequestBean{

    private String meetId;
    private String meetName;
    private String meetAddress;
    private String meetObject;
    private String meetTextArea;

    public String getMeetId() {
        return meetId;
    }

    public void setMeetId(String meetId) {
        this.meetId = meetId;
    }

    public String getMeetName() {
        return meetName;
    }

    public void setMeetName(String meetName) {
        this.meetName = meetName;
    }

    public String getMeetAddress() {
        return meetAddress;
    }

    public void setMeetAddress(String meetAddress) {
        this.meetAddress = meetAddress;
    }

    public String getMeetObject() {
        return meetObject;
    }

    public void setMeetObject(String meetObject) {
        this.meetObject = meetObject;
    }

    public String getMeetTextArea() {
        return meetTextArea;
    }

    public void setMeetTextArea(String meetTextArea) {
        this.meetTextArea = meetTextArea;
    }
}
