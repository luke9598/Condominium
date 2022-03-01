package logic.model;

public class MeetRequest {

    private String id;
    private String name;
    private String address;
    private String object;
    private String text;

    public MeetRequest(String id, String name, String address, String object, String text){
        this.setId(id);
        this.setName(name);
        this.setAddress(address);
        this.setObject(object);
        this.setText(text);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
