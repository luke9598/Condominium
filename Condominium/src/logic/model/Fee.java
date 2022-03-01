package logic.model;

public class Fee {

    private String apt;
    private Double admin;
    private Double park;
    private Double elevator;
    private Double pet;
    private Double wifi;
    private boolean availablePark;
    private boolean availableElevator;
    private boolean availablePet;
    private boolean availableWifi;


    public Fee(String apt, Double admin, Double park, Double elevator, Double pet, Double wifi) {
        this.apt = apt;
        this.admin = admin;
        this.park = park;
        this.elevator = elevator;
        this.pet = pet;
        this.wifi = wifi;
    }

    public Fee(boolean park,boolean elevator,boolean pet,boolean wifi){
        this.availablePark = park;
        this.availableElevator = elevator;
        this.availablePet = pet;
        this.availableWifi = wifi;
    }

    public String getApt() {
        return apt;
    }

    public void setApt(String apt) {
        this.apt = apt;
    }

    public Double getAdmin() {
        return admin;
    }

    public void setAdmin(Double admin) {
        this.admin = admin;
    }

    public Double getPark() {
        return park;
    }

    public void setPark(Double park) {
        this.park = park;
    }

    public Double getElevator() {
        return elevator;
    }

    public void setElevator(Double elevator) {
        this.elevator = elevator;
    }

    public Double getPet() {
        return pet;
    }

    public void setPet(Double pet) {
        this.pet = pet;
    }

    public Double getWifi() {
        return wifi;
    }

    public void setWifi(Double wifi) {
        this.wifi = wifi;
    }

    public boolean getAvailablePark(){
        return availablePark;
    }
    public boolean getAvailableElevator(){
        return availableElevator;
    }
    public boolean getAvailablePet(){
        return availablePet;
    }
    public boolean getAvailableWifi(){
        return availableWifi;
    }
}
